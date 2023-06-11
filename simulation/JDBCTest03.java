import java.sql.*;

/**
 * 类加载的方式注册驱动
 */
public class JDBCTest03 {
    public static void main(String[] args) {
        Connection con = null;
        try{
            // 1. 注册驱动
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver"); // 参数为字符串，能写进配置文件中，更常用！
            // 2. 获取链接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode", "root", "333");
            System.out.println("数据库连接对象 = " + con);

            // ......
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            // ......
        }
    }
}
