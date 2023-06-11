/*
 * MySQL厂家负则编写接口的实现类
 */
public class MySQL implements JDBC{
	public void getConnection(){
		// 具体MySQL数据库底层实现
		// 对于程序员没有关系
		System.out.println("链接MySQL数据库成功！");
	}
}