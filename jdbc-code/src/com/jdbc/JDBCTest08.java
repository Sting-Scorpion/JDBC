package com.jdbc;

import java.sql.*;
import java.util.Scanner;

/**
 * 需要使用Statement的特殊情况
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // 输入dsc降序排列，输入asc升序排列
        System.out.println("请输入dsc或asc");
        System.out.println("请输入：");
        String keywords = s.nextLine();

        // 执行SQL
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            stmt = con.createStatement();
            String sql = "select * fromo emp order by ename " + keywords;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("ename"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
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
    }
}
