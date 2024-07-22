import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";
    private static final String PASSWORD = "QwerTYfahad&1";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                                + "name VARCHAR(50) NOT NULL, "
                                + "age INT NOT NULL)";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create table.");
            e.printStackTrace();
        }
    }

    public static void insertData() {
        String insertSQL = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, "Alice Johnson");
            preparedStatement.setInt(2, 22);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Bob Smith");
            preparedStatement.setInt(2, 25);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Carol Davis");
            preparedStatement.setInt(2, 20);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to insert data.");
            e.printStackTrace();
        }
    }

    public static void updateData() {
        String updateSQL = "UPDATE students SET age = ? WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setInt(1, 23);
            preparedStatement.setString(2, "Alice Johnson");
            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update data.");
            e.printStackTrace();
        }
    }

    public static void deleteData() {
        String deleteSQL = "DELETE FROM students WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, "Bob Smith");
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to delete data.");
            e.printStackTrace();
        }
    }

    public static void displayData() {
        String selectSQL = "SELECT * FROM students";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("\nCurrent data in the students table:");
            System.out.printf("%-5s %-20s %-3s\n", "ID", "Name", "Age");
            System.out.println("-----------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.printf("%-5d %-20s %-3d\n", id, name, age);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve data.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createTable();
        insertData();
        System.out.println("Data after insertion:");
        displayData();
        updateData();
        System.out.println("Data after update:");
        displayData();
        deleteData();
        System.out.println("Data after deletion:");
        displayData();
    }
}
