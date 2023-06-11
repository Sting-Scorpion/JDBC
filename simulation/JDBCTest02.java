import java.sql.*;

/**
 * JDBC完成Delete
 */
public class JDBCTest02 {
    public static void main(String[] args) {
        Connection con = null;
        Statement sta = null;
        try{
            // 1. 注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // 2. 获取链接
            String URL = "jdbc:mysql://localhost:3306/bjpowernode";
            String userName = "root";
            String password = "333";
            con = DriverManager.getConnection(URL, userName, password);
            System.out.println("数据库连接对象 = " + con);
            // 3. 获取数据库操作对象
            sta = con.createStatement();
            // 4. 执行SQL语句
            String sql = "delete from dept where deptno = 40";
            int count = sta.executeUpdate(sql);
            System.out.println(count == 1 ? "删除成功": "删除失败");
            // 5. 处理查询结果集

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            // 6. 释放资源
            if(sta != null){
                try{
                    sta.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }

            if(con != null){
                try{
                    con.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
