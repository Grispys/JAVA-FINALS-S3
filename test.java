import java.sql.Connection;
import java.sql.*;

public class test {
	public static void main(String[] args) throws SQLException {
		Seller user = new Seller("test", "taete@gmail.com", "null", "seller");
		try (Connection connection = DatabaseConnection.getCon()){
			if (user.saveUser(connection)){
				System.out.println("saved");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}




	}
}
