package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.*;

/**
 * 开启一个事务，专门进行查询，并且使用悲观锁/行级锁，锁住相关的数据记录。
 */
public class JDBCTest14 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            // 开启事务
            con.setAutoCommit(false);

            String sql = "select ename, job, sal from emp where job = ? for update";
            ps = con.prepareStatement(sql);
            ps.setString(1, "MANAGER");

            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("ename") + ", "
                        + rs.getString("job") + ", "
                        + rs.getDouble("sal"));
            }
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
            DBUtil.close(con, ps, rs);
        }
    }
}
