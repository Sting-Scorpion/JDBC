package com.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 使用PreparedStatement进行增、删、改
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        // 初始化一个界面
        Map<String, String> userLoginInfo = initUI();
        // 验证用户名密码
        boolean loginSuccess = login(userLoginInfo);
        // 输出结果
        System.out.println(loginSuccess ? "登录成功": "登录失败");

    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return 是否登录成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        boolean loginSuccess = false;
        // JDBC代码
        Connection con = null;
        PreparedStatement ps = null;
        try{
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            // 3. 获取预编译的数据库操作对象
            // 需要先写SQL语句的框架
            /*
            // 增
            String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, 60);
            ps.setString(2, "销售部");
            ps.setString(3, "上海");
            */
            /*
            // 改
            String sql = "update dept set dname = ?, loc = ?, where deptno = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "研发一部");
            ps.setString(2, "北京");
            ps.setInt(3, 60);
             */
            // 删
            String sql = "delete from dept where deptno = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, 60);
            // 4. 执行SQL
            int count = ps.executeUpdate();
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名与密码等登录信息
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);

        System.out.println("用户名：");
        String userName = s.nextLine();

        System.out.println("密码：");
        String password = s.nextLine();

        Map<String, String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", userName);
        userLoginInfo.put("loginPassword", password);

        return userLoginInfo;
    }
}
