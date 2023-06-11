import java.sql.*;
import java.util.ResourceBundle;

/**
 * 处理查询结果集
 */
public class JDBCTest05 {
    public static void main(String[] args) {
        // 资源绑定器绑定属性配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String URL = rb.getString("URL");
        String userName = rb.getString("userName");
        String password = rb.getString("password");

        Connection con = null;
        Statement sta = null;
        ResultSet rs = null;
        try{
            // 1. 注册驱动
            Class.forName(driver);
            // 2. 获取链接
            con = DriverManager.getConnection(URL, userName, password);
            System.out.println("数据库连接对象 = " + con);
            // 3. 获取数据库操作对象
            sta = con.createStatement();
            // 4. 执行SQL语句
            String sql = "select empno, ename, sal from emp";
            rs = sta.executeQuery(sql); // 专门执行DQL语句的方法
            // 5. 处理查询结果集
            while(rs.next()){
                // 光标指向的行有数据，则从中取数据
                // getString的特点是一切类型都以String返回
                // 除了可以通过String类型取出，还可以以特定的类型取出（如int double等）
                String empno = rs.getString("empno"); // JDBC中下标从1开始。可以用下标，也可以用列名（推荐）
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                System.out.println(empno + ", " + ename + ", " + sal);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // 6. 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (sta != null) {
                try {
                    sta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
