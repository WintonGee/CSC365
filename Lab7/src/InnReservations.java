import objects.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class InnReservations {

    public static void main(String[] args) {
        try {
            getDatabaseRooms().forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } catch (Exception e2) {
            System.err.println("Exception: " + e2.getMessage());
        }

        try {
            Date.valueOf("2019-01-02");
        } catch (Exception e) {
            System.out.println("Date Conversion Error");
        }
    }

    // TODO on init get all the values of lab7_rooms and lab7_reservations
    // - used for checking if a certain command is valid

    // Test
    private static void demo1() throws SQLException {
        // Step 1: Establish connection to RDBMS
        try (Connection conn = DriverManager.getConnection(
                ConnectionData.JDBC_URL.s,
                ConnectionData.DB_USER.s,
                ConnectionData.DB_PASSWORD.s)) {
            Statement statement = conn.createStatement();
//            statement.execute("show tables");
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

    private static void demo2() throws SQLException {
        // Step 1: Establish connection to RDBMS
        try (Connection conn = DriverManager.getConnection(
                ConnectionData.JDBC_URL.s,
                ConnectionData.DB_USER.s,
                ConnectionData.DB_PASSWORD.s)) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM lab7_rooms");
            while (rs.next()) {
                /*
                RoomCode char(5) PRIMARY KEY,
                RoomName varchar(30) NOT NULL,
                Beds int(11) NOT NULL,
                bedType varchar(8) NOT NULL,
                maxOcc int(11) NOT NULL,
                basePrice DECIMAL(6,2) NOT NULL,
                decor varchar(20) NOT NULL,
                UNIQUE (RoomName)
                 */
                String RoomCode = rs.getString("RoomCode");
                String RoomName = rs.getString("RoomName");
                String Beds = rs.getString("Beds");
                String bedType = rs.getString("bedType");
                String maxOcc = rs.getString("maxOcc");
                String basePrice = rs.getString("basePrice");
                String decor = rs.getString("decor");
                System.out.println(RoomCode + ", " + RoomName + ", " + Beds + ", " + bedType + ", " +
                        maxOcc + ", " + basePrice + ", " + decor);
            }
        }
    }

    // Return: An arraylist of Room values in the lab 7 database
    public static ArrayList<Room> getDatabaseRooms() throws SQLException {
        ArrayList<Room> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                ConnectionData.JDBC_URL.s,
                ConnectionData.DB_USER.s,
                ConnectionData.DB_PASSWORD.s)) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM lab7_rooms");
            while (rs.next()) {
                String RoomCode = rs.getString("RoomCode");
                String RoomName = rs.getString("RoomName");
                String Beds = rs.getString("Beds");
                String bedType = rs.getString("bedType");
                String maxOcc = rs.getString("maxOcc");
                String basePrice = rs.getString("basePrice");
                String decor = rs.getString("decor");
                Room newRoom = new Room(RoomCode, RoomName, Integer.parseInt(Beds),
                        bedType, Integer.parseInt(maxOcc), new BigDecimal(basePrice), decor);
                list.add(newRoom);
            }
        }

        return list;
    }

}