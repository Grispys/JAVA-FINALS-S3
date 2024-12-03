// Abdul Reeves 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new product to the database
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (pname, pdesc, price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getPname());
            stmt.setString(2, product.getDesc());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();
        }
    }

    // Update an existing product in the database
    public void updateProduct(int productId, Product product) throws SQLException {
        String query = "UPDATE products SET pname = ?, pdesc = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getPname());
            stmt.setString(2, product.getDesc());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, productId);
            stmt.executeUpdate();
        }
    }

    // Delete a product from the database by its ID
    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    // Retrieve a product by its ID
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("pname"),
                        rs.getString("pdesc"),
                        rs.getDouble("price")
                );
            }
        }
        return null;
    }

    // Retrieve all products from the database
    public List<Product> getAllProducts() throws SQLException {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("pname"),
                        rs.getString("pdesc"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
        }
        return products;
    }
}
