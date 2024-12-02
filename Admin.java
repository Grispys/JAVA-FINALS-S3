import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends Users{

	public Admin(String username, String email, String password, String role){
		super(username, password, email, role);
	}

	public boolean viewUsers(Connection connect){
		String query = "SELECT * from Users";
	}

	public boolean deleteUser(String username, Connection connect){
		String query = "DELETE FROM Users WHERE username=?";
	}

	public boolean viewAllProducts(Connection connect){
		// should show not only all products, but also their attached seller
		String query = "SELECT * from Products";
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
