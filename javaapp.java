import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductAvailabilityChecker {
    private static final String URL = "jdbc:mysql://localhost:3306/ProductDB";
    private static final String USERNAME = "Anish";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try {
            // Example usage of the methods
            insertProduct("New Product", "Goa", 100.00, "image_url");
            deleteProduct(1); // Assuming product ID 1 exists
            updateProduct(1, "Updated Product Name", "New Location", 200.00);

            // Fetching products after updates
            fetchProducts("Goa", "Apple", "XYZ Motors");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProduct(String productName, String location, double price, String image) throws SQLException {
        String query = "INSERT INTO Products (productName, location, price, Image) VALUES (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setString(2, location);
            statement.setDouble(3, price);
            statement.setString(4, image);
            statement.executeUpdate();
        }
    }

    public static void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM Products WHERE id =?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

    public static void updateProduct(int productId, String productName, String location, double price) throws SQLException {
        String query = "UPDATE Products SET productName =?, location =?, price =? WHERE id =?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            statement.setString(2, location);
            statement.setDouble(3, price);
            statement.setInt(4, productId);
            statement.executeUpdate();
        }
    }

    public static void fetchProducts(String location, String manufacturer, String dealer) throws SQLException {
        String query = "SELECT * FROM Products WHERE location =? AND manufacturer =? AND dealer =?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, location);
            statement.setString(2, manufacturer);
            statement.setString(3, dealer);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                double price = resultSet.getDouble("price");
                System.out.println("Product Name: " + productName + ", Price: " + price);
            }
        }
    }
}
