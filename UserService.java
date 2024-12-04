// contributors - joshua youden (entire file) - matthew (slight debugging with logging in)
// i added a new flag to the user constructor - hashpass. if true, hash the password. if not, don't. did that so that when this userService is used to compare passwords, it takes the PLAINTEXT
// password instead of hashing it with it's super. should work now - matthew
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserService extends Users {
    public UserService(String username, String password, String email, String role) {
        super(username, password, email, role, false);
    }

    public boolean RegisterUser(Connection connect ,String username, String password, String email, String role) throws SQLException {
        String query = "INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, role);

            int insertRow = statement.executeUpdate();
            return insertRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String authenticateUser(Connection connect) throws SQLException {
        String query = "SELECT password, role FROM Users WHERE username = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String trimmedPass = password.trim();
                    if (BCrypt.checkpw(trimmedPass, storedPassword)) {
                        System.out.println("Login successful! Role: " + role);
                        return role;
                    } else {
                        System.out.println("Invalid password.");
                        System.out.println("correct: " + storedPassword + ", you gave: " + password);
                    }
                } else {
                    System.out.println("User not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
