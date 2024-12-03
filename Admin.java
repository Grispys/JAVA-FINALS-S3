// contributors - matthew verge

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Users{

	public Admin(String username, String email, String password, String role){
		super(username, password, email, role);
	}

	public boolean viewUsers(Connection connect)throws SQLException{
		ArrayList<Users> userList = new ArrayList<>();
		String query = "SELECT * from Users";
		
		try(PreparedStatement statement = connect.prepareStatement(query)){
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				String username = rs.getString("username");
				String email = rs.getString("email");
				String role = rs.getString("role");

				userList.add(new Users(username,email,role) {

					@Override
					public boolean saveUser(Connection connect) throws SQLException {
						return true;
						// ignore this function completely. Users is abstract so when i make a list with it
						// to display all users, save user has to be implemented. ignore it for now
					}});
			}
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		System.out.println("List of all Users: ");
		for (Users user : userList){
			System.out.println("Username: " + user.username + ", Email: " + user.email + ", Role: " + user.role);
		}
		return true;
	}

	public boolean deleteUser(Connection connect)throws SQLException{
		Scanner in = new Scanner(System.in);
		try {
			int option = 0;

			while (option!=-1){
				System.out.println("Enter the User Name to delete: ");
				String user = in.nextLine();



				String query = "DELETE FROM Users WHERE username=?";
				try(PreparedStatement statement = connect.prepareStatement(query)){
					statement.setString(1, user);
					statement.executeUpdate();
					
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}

				System.out.println("Enter -1 to stop. Any other number to enter a new product.");;
				option = in.nextInt();
				in.nextLine();
			}
		}finally{

		}
		return true;
		
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
