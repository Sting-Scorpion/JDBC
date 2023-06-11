package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 负责修改记录，检查被锁定的记录能否被修改。
 */
public class JDBCTest13 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();
            // 开启事务
            con.setAutoCommit(false);

            String sql = "update emp set sal = sal * 1.1 where job = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "MANAGER");
            int count = ps.executeUpdate();
            System.out.println(count);

            // 提交事务
            con.commit();
        } catch (SQLException e) {
            if(con != null){
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(con, ps, null);
        }
    }
}
