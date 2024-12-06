// contributors - matthew verge (base), joshua youden (added onto base)
//  had to update the addProduct and Update to use the command line - matthew
// debugging by matthew
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends Users {

	public Seller(String username, String email, String password, String role){
		super(username, password, email, role, true);
	}
	// inserts parameters into - matthew
	public boolean addProduct(Connection connect) throws SQLException{
		ArrayList<Product> newItem = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		try {
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
		}finally{
			
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






	public boolean updateProduct(Connection connect){
		ArrayList<Product> updatedItem = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		try {
			int option = 0;
			
			while(option!=-1){
				System.out.println("Enter Product to be updated: ");
				String pChanging = in.nextLine();
				System.out.println("Enter New Product Name: ");
				String pName = in.nextLine();
				System.out.println("Enter New Product Description: ");
				String pDesc = in.nextLine();
				System.out.println("Enter New Product Price : ");
				double price = in.nextDouble();
				in.nextLine();

				updatedItem.add(new Product(pName, pDesc, price, pChanging));
				
				System.out.println("Enter -1 to stop. Any other number to enter a new product.");
				option = in.nextInt();
				// in.nextLine();
				
			}in.nextLine();
		}finally{
			
		}
		
		String query = "UPDATE Products SET pName=?, pDesc=?, price=? WHERE pName =? AND seller =?"; //this ones a little weird. needs new name, desc and price, but also the current name
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			for(int i=0; i<updatedItem.size();i++){
				statement.setString(1, updatedItem.get(i).getPname());
				statement.setString(2, updatedItem.get(i).getDesc());
				statement.setDouble(3, updatedItem.get(i).getPrice());
				statement.setString(4, updatedItem.get(i).getPchanging());
				statement.setString(5, this.username);
			
				statement.executeUpdate();
			}						   
		
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}																									  

	}








	public boolean deleteProduct(Connection connect){
		ArrayList<Product> deletedItem = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		try {
			int option = 0;
			
			while(option!=-1){
				System.out.println("Enter Product Name: ");
				String pName = in.nextLine();
				String pDesc = null;                   //this null string are here just so that the constructor can be used
				Double price = 0.0;				   // doubles can't be null because java has an aneurysm otherwise so its set to 0
				in.nextLine();
				
				deletedItem.add(new Product(pName, pDesc, price));
				
				System.out.println("Enter -1 to stop. Any other number to enter a new product.");
				option = in.nextInt();
				in.nextLine();
			}
		}finally{
			
		}
		String query = "DELETE FROM Products WHERE pname=? AND seller=?";//username=? will be set as this.username later

		try (PreparedStatement statement = connect.prepareStatement(query)) {
			for(int i=0; i<deletedItem.size();i++){
				statement.setString(1, deletedItem.get(i).getPname());
				statement.setString(2, this.username);
	
				statement.executeUpdate();
				
			}
			return true;

			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean viewProducts(Connection connect){
		String query = "SELECT * FROM Products WHERE seller=?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, this.username);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				boolean results = false;
				while (resultSet.next()) {
					results = true;
					System.out.println();
					System.out.println("Product Name: " + resultSet.getString("pName"));
					System.out.println("Description: " + resultSet.getString("pDesc"));
					System.out.println("Price: " + resultSet.getDouble("price"));
					System.out.println();
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
