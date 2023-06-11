package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC的事务机制
 *  1. 自动提交：
 *      只要执行任意一条DML语句，就会自动提交一次（JDBC默认的事务行为）
 *      但是实际业务中，都是由多条DML语句共同联合才能完成
 *      必须保证同一个事务中的DML语句同时成功或失败
 *
 */
public class JDBCTest10 {
    public static void main(String[] args) {
        // JDBC代码
        Connection con = null;
        PreparedStatement ps = null;
        try{
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            // 3. 获取预编译的数据库操作对象
            String sql = "update dept set dname = ? where deptno = ?";
            ps = con.prepareStatement(sql);
            // 第一次给占位符传值
            ps.setString(1, "x部门");
            ps.setInt(2, 30);
            int count = ps.executeUpdate();
            System.out.println(count);
            // 重新给占位符传值
            ps.setString(1, "y部门");
            ps.setInt(2, 20);
            count = ps.executeUpdate();
            System.out.println(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            // 6. 释放资源
            if(ps != null){
                try{
                    ps.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(con != null){
                try{
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
