import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Buyers extends Users{

	public Buyers(String username, String email, String password, String role){
		super(username, password, email, role);
	}

	public boolean viewProducts(Connection connect){
		String query = "INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)";
	}

	public boolean searchProducts(String pName, Connection connect){
		String query = "SELECT FROM Products WHERE pname=?";
	}

	public boolean productInfo(Connection connect){

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
