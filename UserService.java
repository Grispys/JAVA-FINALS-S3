// contributors - joshua youden (entire file)
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserService extends Users {
    public Service(String username, String password, String email, String role) {
        super(username, password, email, role);
    }

    public boolean RegisterUser(Connection connect) throws SQLException {}

    public String authenticateUser(Connection connect) throws SQLException {}
    
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
