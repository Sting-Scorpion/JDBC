package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * sql脚本：
 *  drop table if exists t_act;
 *  create table t_act(
 *      actno bigint,
 *      balance double(7, 2) // 7表示有效数字的个数，2表示小数位的个数
 *  );
 *  insert into t_act (actno, balance) values(111, 20000);
 *  insert into t_act (actno, balance) values(222, 0);
 *  commit;
 *  select * from t_act;
 *
 * 重点：
 *  con.setAutoCommit(false); //开启手动提交
 *  con.commit(); // 结束事务，手动提交
 *  con.rollback(); // 出错，执行回滚
 */
public class JDBCTest11 {
    public static void main(String[] args) {
        // JDBC代码
        Connection con = null;
        PreparedStatement ps = null;
        try{
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_learning", "root", "333");
            // 将自动提交机制设置为手动提交
            con.setAutoCommit(false);
            // 3. 获取预编译的数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = con.prepareStatement(sql);
            // 给?传值
            ps.setDouble(1, 10000);
            ps.setInt(2, 111);
            int count = ps.executeUpdate();

            // 人为添加异常
//            String s = null;
//            s.toString();

            // 再给?传值
            ps.setDouble(1, 10000);
            ps.setInt(2, 222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功": "转账失败");

            // 当程序走到这里，说明以上程序没有异常
            // 结束事务，手动提交数据
            con.commit();
        }
        catch(Exception e){
            // 手动回滚
            if(con != null){
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
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
