// contributors - matthew verge

import java.sql.*;
public class Seller extends Users {

	public Seller(String username, String email, String password, String role){
		super(username, password, email, role);
	}
	// inserts parameters into 
	public boolean addProduct(String pName, String pDesc, double price, Connection connect) throws SQLException{
		String query = "INSERT INTO Products(pName, pDesc, price, seller) " + "Values(?,?,?,?)"; // the last ? will be a this.username
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, pName);
			statement.setString(2, pDesc);
			statement.setDouble(3, price);
			statement.setString(4, this.username);

			int updatedRows = statement.executeUpdate();
			return updatedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	

	public boolean updateProduct(String pChanging, String pName, String pDesc, int price, Connection connect){
		String query = "UPDATE Products set pName=?, set pDesc=?, set price=? WHERE pName =? AND username =?"; //this ones a little weird. needs new name, desc and price, but also the current name
																											  //and the username to look for

	}

	public boolean deleteProduct(String productName, Connection connect){
		String query = "DELETE FROM Products WHERE productName=? AND username=?";//username=? will be set as this.username later
	}

	public boolean viewProducts(){
		String query = "INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)";
	}
	



	@Override
	public boolean saveUser(Connection connect) throws SQLException {
		String query = "INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)";

		try (PreparedStatement statement = connect.prepareStatement(query)) {
		
			statement.setString(1, this.username);
			statement.setString(2, this.password);
			statement.setString(3, this.email);
			statement.setString(4, this.role);
	
			
			int updatedRows = statement.executeUpdate();
			return updatedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
}
