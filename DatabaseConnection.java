import java.sql.*;
public class DatabaseConnection {
	private static final String url = "jdbc:postgresql://localhost:5432/SPRINT";
	private static final String user = "postgres";
	private static final String password = "persona";

	public static Connection getCon(){
		Connection connection = null;
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
}
