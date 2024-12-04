// contributors - Matthew verge

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
public abstract class Users {
	protected String username;
	protected String password;
	protected String email;
	protected String role;




	public Users(String username, String password, String email, String role, boolean hashPass){
		this.username =username;
		this.email = email;
		this.role = role;

		if(hashPass) {
			this.password = securePass(password);
		} else{
			this.password = password;
		}
	}



	public Users(String username, String email, String role){
		this.username = username;
		this.email = email;
		this.role = role;
	}
// this securepass goes unused
	private String securePass(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	

	public abstract boolean saveUser(Connection connect) throws SQLException;

}
