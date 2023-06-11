import java.sql.*;
import java.util.*;
/**
 * 将所有连接数据库的信息配置到配置文件中
 */
public class JDBCTest04 {
    public static void main(String[] args) {
        // 资源绑定器绑定属性配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("driver");
        String URL = rb.getString("URL");
        String userName = rb.getString("userName");
        String password = rb.getString("password");

        Connection con = null;
        Statement sta = null;
        try{
            // 1. 注册驱动
            Class.forName(driver);
            // 2. 获取链接
            con = DriverManager.getConnection(URL, userName, password);
            System.out.println("数据库连接对象 = " + con);
            // 3. 获取数据库操作对象
            sta = con.createStatement();
            // 4. 执行SQL语句
            String sql = "update dept set dname = '销售部', loc = '天津' where deptno = 20";
            int count = sta.executeUpdate(sql);
            System.out.println(count == 1 ? "修改成功": "修改失败");
            // 5. 处理查询结果集

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            // 6. 释放资源
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
