package employee.management.sytem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    public Connection connection;
    public Statement statement;

    public Connect() {
        try {
            // Replace these with your database URL, username, and password
            String url = "jdbc:mysql://localhost:3306/employeemanagement";
            String user = "root";
            String password = "MySQL workbench password";

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);

            // Initialize the statement
            statement = connection.createStatement();
            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }
}
