import java.sql.*;

public class InnReservations {

    public static void main(String[] args) {

        try {
            InnReservations hp = new InnReservations();
//            int demoNum = Integer.parseInt(args[0]);
            int demoNum = 1;
            switch (demoNum) {
                case 1 -> hp.demo1();
//                case 2 -> hp.demo2();
//                case 3 -> hp.demo3();
//                case 4 -> hp.demo4();
//                case 5 -> hp.demo5();
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } catch (Exception e2) {
            System.err.println("Exception: " + e2.getMessage());
        }
    }

    private void demo1() throws SQLException {
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