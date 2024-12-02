// contributors - matthew verge (base), joshua youden (added onto base)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Buyers extends Users{

	public Buyers(String username, String email, String password, String role){
		super(username, password, email, role);
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
				return results;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean searchProducts(String pName, Connection connect){
		String query = "SELECT * FROM Products WHERE pName=?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, pName);
			
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

	public boolean productInfo(Connection connect, String pName){
		String query = "SELECT * FROM Products WHERE pName=?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setString(1, pName);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println("Product Name: " + resultSet.getString("pName"));
					System.out.println("Description: " + resultSet.getString("pDesc"));
					System.out.println("Price: " + resultSet.getDouble("price"));
				}
				if (!found) {
					System.out.println("Product not found.");
				}

				return found;
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
