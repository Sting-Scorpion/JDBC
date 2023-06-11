package com.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 1. 解决SQL注入的问题
 *  方法：只要用户提供的信息不参与SQL语句的编译过程
 *      即使有SQL语句关键字，不参与编译便不会起作用
 *  手段：使用java.sql.PreparedStatement类
 *      是预编译的操作对象，预先对SQL语句的框架编译，再进行传值
 *      对比：
 *          Statement存在SQL注入，PreparedStatement解决了SQL注入的问题
 *          Statement每执行一次需要编译一次，PreparedStatement编译一次可执行多次，效率更高
 *          PreparedStatement会在编译阶段进行安全检查
 *      当业务方面要求支持SQL注入、需要SQL拼接时，必须使用Statement
 */
public class JDBCTest07 {
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            // 3. 获取预编译的数据库操作对象
            // 需要先写SQL语句的框架
            String sql = "select * from t_user where loginName = ? and loginPwd = ?"; // ? 代表占位符，若是'?'则表示字符串
            ps = con.prepareStatement(sql); // 将SQL语句框架发送给DBMS，进行预编译
            // 编译完框架，需要给占位符传值（下标从1开始）
            ps.setString(1, loginName);
            ps.setString(2, loginPwd);
            // 4. 执行SQL
            rs = ps.executeQuery();
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
