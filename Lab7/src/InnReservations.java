import objects.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InnReservations {

    public static void main(String[] args) {
        try {
            Database.getReservations().forEach(System.out::println);
            chooseTask();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } catch (Exception e2) {
            System.err.println("Exception: " + e2.getMessage());
        }
    }

    // Selects a task
    public static void chooseTask() throws SQLException {

        System.out.println("(1) Rooms and Rates");
        System.out.println("(2) Reservations");
        System.out.println("(3) Reservation Change");
        System.out.println("(4) Reservation Cancellation");
        System.out.println("(5) Detailed Reservation Information");
        System.out.println("(6) Revenue");
        System.out.println("Choose an option:");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (!DataChecker.isValidInteger(option)) {
            System.out.println("Invalid Integer");
            return;
        }
        switch (Integer.parseInt(option)) {
            case 1:
                FunctionalRequirements.roomAndRates_1();
                return;
            case 2:
                FunctionalRequirements.reservations_2();
                return;
            case 3:
                FunctionalRequirements.reservationChange_3();
                return;
            case 4:
                FunctionalRequirements.reservationCancellation_4();
                return;
            case 5:
                FunctionalRequirements.detailedReservationInformation_5();
                return;
            case 6:
                FunctionalRequirements.revenue_6();
                return;
            default:
                System.out.println("Invalid Option Number");
        }
    }


}