package src.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static void main(String[] args) {

	}

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://101.43.234.153:1300/qcby?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
	String user = "root";
	String password = "root";

	public Connection conn;

	public DBConnection() {

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
