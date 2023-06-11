package com.jdbc;

import com.jdbc.util.DBUtil;

import java.sql.*;

/**
 * 任务一：测试DBUtil是否好用
 * 任务二：完成模糊查询的实现
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            con = DBUtil.getConnection();
            // 获取预编译的数据库操作对象
            String sql = "select ename from emp where ename like ?"; // 不能在里面写成'_?%'，会被认定为字符串
            ps = con.prepareStatement(sql);
            // 名字第二个字符为A
            ps.setString(1, "_A%");
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            // 释放资源
            DBUtil.close(con, ps, rs);
        }
    }
}
