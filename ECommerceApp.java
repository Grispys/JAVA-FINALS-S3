import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ECommerceApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SPRINT", "postgres", "persona");

            while (true) {
                System.out.println("\n--- E-Commerce Platform ---");
                System.out.println("1. Register");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        System.out.println("Exiting the application. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerUser() throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Role (buyer/seller/admin): ");
        String role = scanner.nextLine();

        Users newUser;
        switch (role) {
            // i found the problem - the construcotr is username, email, password, role. initally it was username, PASSWORD, EMAIL, role. so the email was getting hashed and put into the wrong slot
            // it's fixed now! -matthew
            case "buyer":
                newUser = new Buyers(username, email, password, role);
                break;
            case "seller":
                newUser = new Seller(username, email, password, role);
                break;
            case "admin":
                newUser = new Admin(username, email, password, role);
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return;
        }

        if (newUser.saveUser(connection)) {
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Registration failed. Try again.");
        }
    }

    private static void loginUser() throws SQLException {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        UserService userService = new UserService(username, password, "", "");
        String role = userService.authenticateUser(connection);

        if (role == null) {
            System.out.println("Invalid credentials. Try again.");
        } else {
            System.out.println("Login successful. Welcome, " + username + "!");
            switch (role) {
                case "buyer":
                    buyerMenu(username);
                    break;
                case "seller":
                    sellerMenu(username);
                    break;
                case "admin":
                    adminMenu();
                    break;
                default:
                    System.out.println("Role not recognized. Logging out.");
            }
        }
    }

    private static void buyerMenu(String username) throws SQLException {
        Buyers buyers = new Buyers(username, "", "", "buyer");
        while (true) {
            System.out.println("\n--- Buyer Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Search Product by Name");
            System.out.println("3. Search Product by Seller");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buyers.viewProducts(connection);
                    break;
                case 2:
                    System.out.print("Enter Product Name: ");
                    String pname = scanner.nextLine();
                    buyers.searchProducts(username, connection);
                    break;
                case 3:
                    System.out.print("Prduct Infomation: ");
                    String name= scanner.nextLine();
                    buyers.productInfo(connection, name);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void sellerMenu(String username) throws SQLException {
        Seller seller = new Seller(username, "", "", "seller");
        while (true) {
            System.out.println("\n--- Seller Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Your Products");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    seller.addProduct(connection);
                    break;
                case 2:
                    seller.updateProduct(connection);
                    break;
                case 3:
                    seller.deleteProduct(connection);
                    break;
                case 4:
                    seller.viewProducts(connection);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu() throws SQLException {
        Admin admin = new Admin("", "", "", "admin");
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Delete a User");
            System.out.println("3. View All Products + Sellers");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.viewUsers(connection);
                    break;
                case 2:
                    admin.deleteUser(connection);
                    break;
                case 3:
                    admin.viewAllProducts(connection);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
