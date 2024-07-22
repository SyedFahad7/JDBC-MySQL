import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/employee";
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

            preparedStatement.setString(1, "Aisha Khan");
            preparedStatement.setInt(2, 27);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Hassan Ali");
            preparedStatement.setInt(2, 30);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Fatima Ahmed");
            preparedStatement.setInt(2, 24);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Omar Malik");
            preparedStatement.setInt(2, 32);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Zara Siddiqui");
            preparedStatement.setInt(2, 29);
            preparedStatement.executeUpdate();


            preparedStatement.setString(1, "Aarti Sharma");
            preparedStatement.setInt(2, 26);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Ravi Patel");
            preparedStatement.setInt(2, 31);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Pooja Reddy");
            preparedStatement.setInt(2, 28);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Vikram Singh");
            preparedStatement.setInt(2, 35);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Meera Gupta");
            preparedStatement.setInt(2, 33);
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
            

            preparedStatement.setInt(1, 22);
            preparedStatement.setString(2, "Aisha Khan");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 29);
            preparedStatement.setString(2, "Hassan Ali");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 25);
            preparedStatement.setString(2, "Fatima Ahmed");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 33);
            preparedStatement.setString(2, "Omar Malik");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 30);
            preparedStatement.setString(2, "Zara Siddiqui");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 27);
            preparedStatement.setString(2, "Aarti Sharma");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 32);
            preparedStatement.setString(2, "Ravi Patel");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 29);
            preparedStatement.setString(2, "Pooja Reddy");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 36);
            preparedStatement.setString(2, "Vikram Singh");
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 34);
            preparedStatement.setString(2, "Meera Gupta");
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
            

            preparedStatement.setString(1, "Alice Johnson");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Bob Smith");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Pooja Reddy");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Vikram Singh");
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
                ResultSet resultSet = statement.executeQuery(selectSQL);
                FileWriter fileWriter = new FileWriter("output.txt");
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Print header to console and file
            String header = "Current data in the students table:\n" +
                    String.format("%-5s %-20s %-3s\n", "ID", "Name", "Age") +
                    "-----------------------------";
            System.out.println(header);
            printWriter.println(header);

            // Print data rows to console and file
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String row = String.format("%-5d %-20s %-3d\n", id, name, age);
                System.out.print(row);
                printWriter.print(row);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve data.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to write to file.");
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
