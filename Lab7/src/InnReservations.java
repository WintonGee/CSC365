import java.sql.*;

public class InnReservations {

    public static void main(String[] args) {
        try {
            demo1();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } catch (Exception e2) {
            System.err.println("Exception: " + e2.getMessage());
        }
    }

    private static void demo1() throws SQLException {
        // Step 1: Establish connection to RDBMS
        try (Connection conn = DriverManager.getConnection(
                ConnectionData.JDBC_URL.s,
                ConnectionData.DB_USER.s,
                ConnectionData.DB_PASSWORD.s)) {
            Statement statement = conn.createStatement();
            statement.execute("show tables");
            ResultSet rs = statement.executeQuery("SELECT * FROM airlines");
            while (rs.next()) {
                String id = rs.getString("Id");
                String airline = rs.getString("Airline");
                String abbreviation = rs.getString("Abbreviation");
                String country = rs.getString("Country");
                System.out.println(id + ", " + airline + ", " + abbreviation + ", " + country);
            }
        }
    }

}