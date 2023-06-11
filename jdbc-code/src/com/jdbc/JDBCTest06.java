package com.jdbc;

import java.sql.*;
import java.util.*;

/**
 * 实现功能：
 *  1. 需求：模拟用户登录功能
 *  2. 描述：
 *      程序运行时，提供一个输入的入口，让用户输入用户名与密码
 *      用户输入后提交信息，java程序收集到用户信息
 *      程序链接数据库验证用户名与密码是否合法
 *          合法：显示登录成功
 *          不合法：显示登录失败
 *  3. 数据的准备：
 *      实际开发中，表的设计会使用专业的建模工具，这里使用PowerDesigner
 *  4. 存在的问题：
 *      用户名：fdsa
 *      密码：fdsa' or '1'='1
 *      * 这种现象被称作SQL注入
 *  5. SQL注入的原因
 *      输入信息中含有SQL语句的关键字，且关键字参与SQL语句的编译
 *      导致原SQL语句的意义被扭曲，造成SQL注入
 */
public class JDBCTest06 {
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
        // 单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPassword");
        // JDBC代码
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            // 3. 获取数据库操作对象
            stmt = con.createStatement();
            // 4. 执行SQL
            String sql = "select * from t_user where loginName = '" + loginName
                    + "' and loginPwd = '" + loginPwd + "'"; // 完成sql语句拼接
            rs = stmt.executeQuery(sql); // 发送给DBMS进行编译。会把“非法信息”也进行编译
            // 5. 处理结果集
            if(rs.next()){
                loginSuccess = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            // 6. 释放资源
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try{
                    stmt.close();
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