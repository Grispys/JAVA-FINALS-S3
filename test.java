// contributors - matthew verge

import java.sql.Connection;
import java.sql.*;

public class test {
	public static void main(String[] args) throws SQLException {
		Seller user = new Seller("test", "taete@gmail.com", "null", "seller");
		// testing that creating users and adding them to the database works
		try (Connection connection = DatabaseConnection.getCon()){
			if (user.saveUser(connection)){
				System.out.println("saved");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		// testing that addproduct works 
		try (Connection connection = DatabaseConnection.getCon()){
			if (user.addProduct(connection)){
				System.out.println("saved");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}





	}
}
