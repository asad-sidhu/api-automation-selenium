package MiscFunctions;

import Config.ReadPropertyFile;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Config_DB {

	static Connection con = null;
	private static Statement stmt;	
    public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

	public static String DB_UserName = null;
	public static String DB_Password = null;
	public static String DB_Name = null;

	@BeforeTest
	public static void test() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String DB_URL = "jdbc:sqlserver://ancera-asql-001.database.windows.net;databaseName=" + DB_Name + ";user=" + DB_UserName + ";Password=" + DB_Password;
			System.out.println(DB_URL);
			Connection con = DriverManager.getConnection(DB_URL, DB_UserName, DB_Password);
			setStmt(con.createStatement());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@AfterTest
	public static void tearDown() throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		DB_Config_DB.stmt = stmt;
	}

}
