// contributors - matthew verge (base), joshua youden (added onto base)
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

	// public boolean viewProducts(Connection connect){
	// 	String query = "SELECT * FROM Products WHERE seller=?";
	// 	try (PreparedStatement statement = connect.prepareStatement(query)) {
	// 		statement.setString(1, this.username);
			
	// 		try (ResultSet resultSet = statement.executeQuery()) {
	// 			boolean results = false;
	// 			while (resultSet.next()) {
	// 				results = true;
	// 				System.out.println("Product Name: " + resultSet.getString("pName"));
	// 				System.out.println("Description: " + resultSet.getString("pDesc"));
	// 				System.out.println("Price: " + resultSet.getDouble("price"));
	// 			}
	// 			return results;
	// 		}
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 		return false;
	// 	}
	// }

	
	 /**
     * View products by a specific seller using command-line input.
     */
    public boolean viewProducts(Connection connect) {
        String query = "SELECT * FROM Products WHERE seller LIKE ?";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the seller's username to view their products: ");
            String sellerQuery = scanner.nextLine().trim();
            sellerQuery = "%" + sellerQuery + "%"; // Wildcards for partial matches

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
	

	// public boolean searchProducts(String pName, Connection connect){
	// 	String query = "SELECT * FROM Products WHERE pName=?";
	// 	try (PreparedStatement statement = connect.prepareStatement(query)) {
	// 		statement.setString(1, pName);
			
	// 		try (ResultSet resultSet = statement.executeQuery()) {
	// 			boolean results = false;
	// 			while (resultSet.next()) {
	// 				results = true;
	// 				System.out.println("Product Name: " + resultSet.getString("pName"));
	// 				System.out.println("Description: " + resultSet.getString("pDesc"));
	// 				System.out.println("Price: " + resultSet.getDouble("price"));
	// 			}
	// 			if (!results) {
	// 				System.out.println("No products found.");
	// 			}
				
	// 			return results;
	// 		}
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 		return false;
	// 	}
	// }

	
	/**
     * Search for products by name using command-line input.
     */
    public boolean searchProducts(Connection connect) {
        String query = "SELECT * FROM Products WHERE pName LIKE ?";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the product name to search for: ");
            String searchQuery = scanner.nextLine().trim();
            searchQuery = "%" + searchQuery + "%"; // Wildcards for partial matches

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

	// public boolean productInfo(Connection connect, String pName){
	// 	String query = "SELECT * FROM Products WHERE pName=?";
	// 	try (PreparedStatement statement = connect.prepareStatement(query)) {
	// 		statement.setString(1, pName);
			
	// 		try (ResultSet resultSet = statement.executeQuery()) {
	// 			boolean found = false;
	// 			while (resultSet.next()) {
	// 				found = true;
	// 				System.out.println("Product Name: " + resultSet.getString("pName"));
	// 				System.out.println("Description: " + resultSet.getString("pDesc"));
	// 				System.out.println("Price: " + resultSet.getDouble("price"));
	// 			}
	// 			if (!found) {
	// 				System.out.println("Product not found.");
	// 			}

	// 			return found;
	// 		}
	// 	} catch (SQLException e) {
	// 		e.printStackTrace();
	// 		return false;
	// 	}
	// }


	 /**
     * Get detailed information for a specific product using command-line input.
     */
    public boolean productInfo(Connection connect) {
        String query = "SELECT * FROM Products WHERE pName = ?";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the product name to view details: ");
            String productName = scanner.nextLine().trim();

            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, productName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean found = false;
                    System.out.println("------ Product Information ------");
                    while (resultSet.next()) {
                        found = true;
                        System.out.println("Product Name: " + resultSet.getString("pName"));
                        System.out.println("Description: " + resultSet.getString("pDesc"));
                        System.out.printf("Price: $%.2f%n", resultSet.getDouble("price"));
                        System.out.println("Seller: " + resultSet.getString("seller"));
                        System.out.println("--------------------------------------");
                    }
                    if (!found) {
                        System.out.println("Product not found.");
                    }
                    return found;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching product information: " + e.getMessage());
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
