// contributors - Matthew verge

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
public abstract class Users {
	protected String username;
	protected String password;
	protected String email;
	protected String role;

	public Users(String username, String password, String email, String role){
		this.username =username;
		this.password = securePass(password);
		this.email = email;
		this.role = role;
	}

	public Users(String username, String email, String role){
		this.username = username;
		this.email = email;
		this.role = role;
	}

	private String securePass(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	

	public abstract boolean saveUser(Connection connect) throws SQLException;

}
