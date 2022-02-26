package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

		private final static String URL = "jdbc:mysql://localhost:3306/foods";
		private final static String UN = "root";
		private final static String PW = "romania1983SQL";
		private static Connection connection;
		private static DBConnection instance;
		
		private DBConnection(Connection connection) {
			this.connection = connection;
		}
		
		public static Connection getConnection() {
			if (instance == null) {
				try {
					connection = DriverManager.getConnection(URL, UN, PW);
					instance = new DBConnection(connection);
					System.out.println("Connection Successful.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			return DBConnection.connection;
		}
		
}
