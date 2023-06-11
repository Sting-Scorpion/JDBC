import java.util.*;

/**
 * Java程序员角色
 * 不关心具体是哪个数据库，只需要面向JDBC接口写代码
 * 面向接口，面向抽象编程，不要面向具体编程
 */
public class JavaProgrammer{
	public static void main(String[] args) throws Exception {
		// 从这里修改接口的具体实现
//		JDBC jdbc = new MySQL();
//		JDBC jdbc = new Oracle();
//		JDBC jdbc = new SqlServer();

		// 通过反射机制创建对象
//		Class c = Class.forName("MySQL");
//		Class c = Class.forName("Oracle");
//		Class c = Class.forName("SqlServer");
		// 用配置文件
		ResourceBundle rb = ResourceBundle.getBundle("jdbc");
		/*
		 * 配置文件中：
		 * className=Oracle
		 */
		String className = rb.getString("className");
		Class c = Class.forName(className);
		JDBC jdbc = (JDBC) c.newInstance();

		// 以下都是面向接口调用，不用修改
		jdbc.getConnection();
	}
}
