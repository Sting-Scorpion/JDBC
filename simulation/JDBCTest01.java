import java.sql.*;
/**
 * JDBC编程六步
 */
public class JDBCTest01{
    public static void main(String[] args) {
        Connection con = null;
        Statement sta = null;
        try{
            // 1. 注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // 2. 获取链接
            String URL = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
            String userName = "root";
            String password = "333";
            con = DriverManager.getConnection(URL, userName, password);
            System.out.println("数据库连接对象 = " + con);
            // 3. 获取数据库操作对象
            sta = con.createStatement(); // Statement是专门执行sql语句的
            // 4. 执行SQL语句
            String sql = "insert into dept(deptno, dname, loc) values(50, '人事部', '北京')";
            // 专门执行DML语句的（insert delete update）
            // 返回值为“影响数据库中的记录条数”
            int count = sta.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功": "保存失败");
            // 5. 处理查询结果集

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            // 6. 释放资源
            // 为了保证资源一定释放，在finally块中关闭资源
            // 遵循 从小到大 依次关闭
            // 分别try catch，出问题互不影响
            try{
                if(sta != null){
                    sta.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }

            try{
                if(con != null){
                    con.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}