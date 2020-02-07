package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
public Connection Get_Connection() throws Exception{
	try {
		String connectionURL = "jdbc:mysql://localhost:3306/javadb";
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(connectionURL,"root","root");
		return connection;
	}
	catch(SQLException e){
		throw e;
	}
	catch(Exception e) {
		throw e;
	}
}
}
