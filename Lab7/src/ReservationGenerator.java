import objects.Room;

import java.sql.*;
import java.util.ArrayList;

// This class is used for functional requirement 2
// Precondition: add data provided will be valid
public class ReservationGenerator {

    String firstName, lastName;
    String desiredRoomCode, desiredBedType;

    public ReservationGenerator(
            String newFirstName, String newLastName,
            String newDesiredRoomCode, String newDesiredBedType) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.desiredRoomCode = newDesiredRoomCode;
        this.desiredBedType = newDesiredBedType;
    }


    // TODO test
    // Send a sql query to get the max occupation
    public int getMaxOccupation() throws SQLException {
        try (Connection conn = DriverManager.getConnection(
                ConnectionData.JDBC_URL.s,
                ConnectionData.DB_USER.s,
                ConnectionData.DB_PASSWORD.s)) {

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT MAX(maxOcc)" +
                    " FROM " + Database.Table.LAB7_ROOMS.s);
            if (rs.next()) {
                String maxOcc = rs.getString("maxOcc");
                return Integer.parseInt(maxOcc);
            }
        }
        return 0;
    }

    public Reservation getReservation() {
        return null;
    }

    public ArrayList<Reservation> getReservations() throws SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();

        ArrayList<Room> roomsInDatabase = Database.getRooms();
        ArrayList<Reservation> reservationsInDatabase = Database.getReservations();

        return reservations;
    }

    // Generates 5 reservations that are similar, but not an exact match
    public ArrayList<Reservation> generateSimilar() {
        ArrayList<Reservation> reservations = new ArrayList<>();

        return reservations;
    }

}
