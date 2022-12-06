import objects.Room;

import java.sql.*;
import java.util.ArrayList;

// This class is used for functional requirement 2
// Precondition: data provided in constructor will be valid to be formatted
public class ReservationGenerator {

    String firstName, lastName;
    String desiredRoomCode, desiredBedType; // Could be blank, indicating any
    Date checkInDate, checkOutDate;
    int numberOfChildren, numberOfAdults;

    public ReservationGenerator(
            String newFirstName, String newLastName,
            String newDesiredRoomCode, String newDesiredBedType,
            Date newCheckInDate, Date newCheckOutDate,
            int newNumberOfChildren, int newNumberOfAdults) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.desiredRoomCode = newDesiredRoomCode;
        this.desiredBedType = newDesiredBedType;
        this.checkInDate = newCheckInDate;
        this.checkOutDate = newCheckOutDate;
        this.numberOfChildren = newNumberOfChildren;
        this.numberOfAdults = newNumberOfAdults;
    }


    // Return: Max number of occupations allowed
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
                String maxOcc = rs.getString(1);

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
