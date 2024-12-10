// contributors -matthew verge / joshua youden / abdul reeves

// debugging by matthew
//
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Buyers extends Users{

	public Buyers(String username, String email, String password, String role){
		super(username, password, email, role, true);
	}

	
	 /**
     * View products by a specific seller using command-line input.
     */
    public boolean searchProductsbySeller(Connection connect) {
        String query = "SELECT * FROM Products WHERE seller=?";
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        try { 
            System.out.print("Enter the seller's username to view their products: ");
            String sellerQuery = scanner.nextLine().trim();

            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, sellerQuery);

                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean results = false;
                    System.out.println("------ Products by Seller ------");
                    while (resultSet.next()) {
                        results = true;
                        System.out.println("Product Name: " + resultSet.getString("pName"));
                        System.out.println("Description: " + resultSet.getString("pDesc"));
                        System.out.printf("Price: $%.2f%n", resultSet.getDouble("price"));
                        System.out.println("--------------------------------------");
                    }
                    if (!results) {
                        System.out.println("No products found for the given seller.");
                    }
                    return results;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while searching for seller's products: " + e.getMessage());
            return false;
        }
    }
	
	
	/**
     * Search for products by name using command-line input.
     */
    public boolean searchProductsbyName(Connection connect) {
        String query = "SELECT * FROM Products WHERE pName=?";
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); // had to move scanner from inside try to static - matthew
        // i also changed the query from pname LIKE ? to pname=? because? i have no idea what LIKE does in queries. 
        try{
            System.out.print("Enter the product name to search for: ");
            String searchQuery = scanner.nextLine().trim();

            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, searchQuery);

                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean results = false;
                    System.out.println("------ Search Results ------");
                    while (resultSet.next()) {
                        results = true;
                        System.out.println("Product Name: " + resultSet.getString("pName"));
                        System.out.println("Description: " + resultSet.getString("pDesc"));
                        System.out.printf("Price: $%.2f%n", resultSet.getDouble("price"));
                        System.out.println("--------------------------------------");
                    }
                    if (!results) {
                        System.out.println("No products found matching the name.");
                    }
                    return results;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while searching for products: " + e.getMessage());
            return false;
        }
    }


	 /**
     * Get detailed information for a specific product using command-line input.
     */
    public boolean viewAllProducts(Connection connect) throws SQLException {
		String query = "SELECT * from Products";
		
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			ResultSet rs = statement.executeQuery();
			
			System.out.println("List of all Products: ");
			
			while (rs.next()) {
				String pname = rs.getString("pname");
				String pdesc = rs.getString("pdesc");
				double price = rs.getDouble("price");
				String seller = rs.getString("seller");  
	
				
				System.out.println("Product Name: " + pname);
				System.out.println("Description: " + pdesc);
                System.out.printf("Price: $%.2f%n", price);
				System.out.println("Seller: " + seller);  
				System.out.println();  
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
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
