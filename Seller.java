// contributors - matthew verge (base), joshua youden (added onto base)

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends Users {

	public Seller(String username, String email, String password, String role){
		super(username, password, email, role);
	}
	// inserts parameters into 
	public boolean addProduct(Connection connect) throws SQLException{
		ArrayList<Product> newItem = new ArrayList<>();
		try (Scanner in = new Scanner(System.in)) {
			int option = 0;
			
			while(option!=-1){
				System.out.println("Enter Product Name: ");
				String pName = in.nextLine();
				System.out.println("Enter Product Description: ");
				String pDesc = in.nextLine();
				System.out.println("Enter Product Price : ");
				double price = in.nextDouble();
				in.nextLine();

				newItem.add(new Product(pName, pDesc, price));
				
				System.out.println("Enter -1 to stop. Any other number to enter a new product.");
				option = in.nextInt();
				// in.nextLine();
				
			}in.nextLine();
		}
		String query = "INSERT INTO Products(pName, pDesc, price, seller) " + "Values(?,?,?,?)"; // the last ? will be a this.username
		
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			for(int i =0; i<newItem.size();i++){
				statement.setString(1, newItem.get(i).getPname());
				statement.setString(2, newItem.get(i).getDesc());
				statement.setDouble(3, newItem.get(i).getPrice());
				statement.setString(4, this.username);
	
				statement.executeUpdate();
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	

	public boolean updateProduct(String pChanging, String pName, String pDesc, int price, Connection connect){
		String query = "UPDATE Products SET pName=?, pDesc=?, price=? WHERE pName =? AND username =?"; //this ones a little weird. needs new name, desc and price, but also the current name
		try (PreparedStatement statement = connect.prepareStatement(query)) {						   //and the username to look for
			statement.setString(1, pName);
			statement.setString(2, pDesc);
			statement.setInt(3, price);
			statement.setString(4, pChanging);
			statement.setString(5, this.username);

			int updatedRows = statement.executeUpdate();
			return updatedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}																									  

	}

	public boolean deleteProduct(String productName, Connection connect){
		String query = "DELETE FROM Products WHERE productName=? AND username=?";//username=? will be set as this.username later

		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, productName);
			statement.setString(2, this.username);

			int updatedRows = statement.executeUpdate();
			return updatedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean viewProducts(Connection connect){
		String query = "SELECT * FROM Products WHERE username=?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, this.username);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				boolean results = false;
				while (resultSet.next()) {
					results = true;
					System.out.println("Product Name: " + resultSet.getString("pName"));
					System.out.println("Description: " + resultSet.getString("pDesc"));
					System.out.println("Price: " + resultSet.getDouble("price"));
				}
				if (!results) {
					System.out.println("No products found.");
				}
				return results;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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
