package utils;

import commons.Routes;

import java.sql.*;


public class DBPlug {

    public static String getEmail;
    public static String getPassword;





    public static Connection connectDatabase() throws SQLException {

        return DriverManager.getConnection(Routes.DB_URL, Routes.DB_USERNAME, Routes.DB_PASSWORD);
    }

    public static void insertUser(String email, String password) throws SQLException {

        try (Connection connection = connectDatabase()) {

            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO RestAssured_users_exs (email, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Generated record key: " + generatedKeys.getInt(1));
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error inserting value: " + e.getMessage());

            }
        }
    }

    public static void getLoginDetails(String userEmail) throws SQLException {

        try (Connection connection = connectDatabase()) {

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM RestAssured_users_exs WHERE email = ?")) {
                ps.setString(1, userEmail);

                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        getEmail = resultSet.getString("email");
                        getPassword = resultSet.getString("password");
                        System.out.println("Email from DB: " + getEmail + ", Password from DB: " + getPassword);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error executing query: " + e.getMessage());
            }
        }
    }

    public static void updatePassword(String newPassword) throws SQLException {

        try (Connection connection = connectDatabase()) {

            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE RestAssured_users_exs SET password = ? WHERE email = ?")) {

                ps.setString(1, newPassword);
                ps.setString(2, getEmail);

                ps.executeUpdate();

                getPassword = newPassword;

            } catch (SQLException e) {
                System.out.println("Error updating password: " + e.getMessage());
            }
        }
    }


}//END OF CLASS
