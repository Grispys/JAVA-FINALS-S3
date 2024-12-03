import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    
    public void addProduct(String pName, String pDesc, double price) {
        Product product = new Product(pName, pDesc, price);
        try {
            productDAO.addProduct(product);
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
    }

    
    public void updateProduct(int productId, String pName, String pDesc, double price) {
        Product product = new Product(pName, pDesc, price);
        try {
            productDAO.updateProduct(productId, product);
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
    }

    
    public void deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }


    public void getProductById(int productId) {
        try {
            Product product = productDAO.getProductById(productId);
            if (product != null) {
                System.out.println("Product Details:");
                System.out.println("Name: " + product.getPname());
                System.out.println("Description: " + product.getDesc());
                System.out.println("Price: " + product.getPrice());
            } else {
                System.out.println("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving product: " + e.getMessage());
        }
    }

    
    public void listAllProducts() {
        try {
            List<Product> products = productDAO.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("No products available.");
            } else {
                System.out.println("Available Products:");
                for (Product product : products) {
                    System.out.println("------------------------");
                    System.out.println("Name: " + product.getPname());
                    System.out.println("Description: " + product.getDesc());
                    System.out.println("Price: $" + product.getPrice());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }
    }
}
