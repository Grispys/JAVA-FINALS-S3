// contributors - joshua youden (entire file)
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserService extends Users {
    public Service(String username, String password, String email, String role) {
        super(username, password, email, role);
    }

    public boolean RegisterUser(Connection connect ,String username, String password, String email, String role) throws SQLException {
        String query = "INSERT INTO Users(username, password, email, role) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, this.username);
            statement.setString(2, this.password);
            statement.setString(3, this.email);
            statement.setString(4, this.role);

            int insertRow = statement.executeUpdate();
            return insertRow > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String authenticateUser(Connection connect) throws SQLException {
        String query = "SELECT psssword, role FROM Users WHERE username = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, this.username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    String role = resultSet.getString("role");

                    if (BCrypt.checkpw(this.password, storedPassword)) {
                        System.out.println("Login successful! Role: " + role);
                        return role;
                    } else {
                        System.out.println("Invalid password.");
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
    
    public static Users menu(String username, String email, String password, String role) {
        switch (role) {
            case "buyer":
                return System.out.println();
            case "seller":
                return System.out.println();
            case "admin":
                return System.out.println();
            default:
                return null;
        }
    }
}
