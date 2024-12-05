// contributors - matthew verge
// this file was just used to test the buyer/seller/admin methods. Not relevant to the rest of the app

import java.sql.Connection;
import java.sql.*;

public class test {
	public static void main(String[] args) throws SQLException {
		Seller user = new Seller("test", "taete@gmail.com", "null", "seller");
		Seller user2 = new Seller("tost", "ta2qasaete@gmail.com", "null", "seller");
		Buyers user3 = new Buyers("tist", "taet3tate@gmail.com", "null", "buyer");
		Admin user4 = new Admin("Admin", "taeasde@gmail.com", "null", "seller");
		// test that admin view products works
		try (Connection connection = DatabaseConnection.getCon()){
			if (user4.viewAllProducts(connection)){
				System.out.println("viewed");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		// testing that creating users and adding them to the database works
		
		try (Connection connection = DatabaseConnection.getCon()){
			if (user3.saveUser(connection)){
				System.out.println("saved");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		// testing that admins can view every user
		try (Connection connection = DatabaseConnection.getCon()){
			if (user4.viewUsers(connection)){
				System.out.println("displayed correctly");
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


		try (Connection connection = DatabaseConnection.getCon()){
			if (user4.deleteUser(connection)){
				System.out.println("user deleted");
			}else{
				System.err.println("fail");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}






	}
}
