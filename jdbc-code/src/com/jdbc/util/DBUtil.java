package com.jdbc.util;

import java.sql.*;

/**
 * JDBC工具类，简化JDBC编程
 * 封装方法
 */
public class DBUtil {
    /**
     * 工具类中构造方法一般是私有的
     * 其他方法多为静态方法，不需要new对象，直接类名调用
     */
    private DBUtil(){}

    // 静态代码块在类加载时执行，且只执行一次
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 返回连接对象
     * @throws SQLException 异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
    }

    /**
     * 关闭资源
     * @param con 连接对象
     * @param st 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection con, Statement st, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(st != null){
            try{
                st.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(con != null){
            try{
                con.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
