import objects.Reservation;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class FunctionalRequirements {

    // TODO
    // FR1: Rooms and Rates
    // Steps -> output a list of rooms to the user sorted by popularity, highest to lowest
    // - Room popularity score: number of days the room has been occupied during
    //   the previous 180 days divided by 180 (round to two decimal places)
    // - Next available check-in date
    // - Length in days and check out date of the most recent (completed) stay in the room
    public static void roomAndRates_1() {

    }


    // TODO
    // FR2: Reservations
    // Steps -> system shall accept from the user the following information:
    // - First name
    // - Last name
    // - A room code to indicate the specific room desired (or “Any” to indicate no preference)
    // - A desired bed type (or “Any” to indicate no preference)
    // - Begin date of stay
    // - End date of stay
    // - Number of children
    // - Number of adults
    // Condition:
    // - maximum room occupancy must be considered
    // - the dates must not overlap with another existing reservation
    // Output:
    // (Available) a numbered list of available rooms w/ booking by option number
    // (None) 5 possibilities should be chosen based on similarity to the desired reservation
    public static void reservations_2() {

    }


    // FR3: Reservation Change
    // Status: Complete and tested
    // Allow the user to provide a new value or to indicate “no change” for a given field
    // Steps -> system shall accept from the user reservation code and any of the following information:
    // - First name
    // - Last name
    // - Begin date: Check if this new begin date is valid
    // - End date: Check if new end date is valid
    // - Number of children
    // - Number of adults
    public static void reservationChange_3() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        // Get reservation code
        System.out.println("Enter Reservation Code: ");
        String reservationCode = scanner.nextLine();
        if (!DataChecker.isValidInteger(reservationCode)) {
            System.out.println("Invalid Reservation Code: " + reservationCode);
            return;
        }

        // Check if reservation code exists in the database, end if it does not
        Optional<Reservation> reservation = Database.getReservations().stream()
                .filter(c -> c.CODE == Integer.parseInt(reservationCode)).findFirst();
        if (reservation.isEmpty()) {
            System.out.println("Reservation code does not exist in database: " + reservationCode);
            return;
        }

        String change;
        SetBuilder setBuilder = new SetBuilder();
        // - First name
        System.out.println("Change First Name (Y/N): ");
        change = scanner.nextLine();
        if (change.equalsIgnoreCase("Y")) {
            System.out.println("New First Name: ");
            String newValue = scanner.nextLine();
            setBuilder.addSet("FirstName", newValue, true);
        }

        // - Last name
        System.out.println("Change Last Name (Y/N): ");
        change = scanner.nextLine();
        if (change.equalsIgnoreCase("Y")) {
            System.out.println("New Last Name: ");
            String newValue = scanner.nextLine();
            setBuilder.addSet("LastName", newValue, true);
        }

        // - Begin date: Check if this new begin date is valid
        System.out.println("Change Begin Date (Y/N): ");
        String changeBeginDate = scanner.nextLine();
        String newBeginDate = "";
        if (changeBeginDate.equalsIgnoreCase("Y")) {
            System.out.println("New Begin Date (YYYY-MM-DD): ");
            newBeginDate = scanner.nextLine();
            if (!DataChecker.isValidDate(newBeginDate)) {
                System.out.println("Invalid Date or Formatting");
                return;
            }
            setBuilder.addSet("CheckIn", newBeginDate, true);
        }

        // - End date: Check if new end date is valid
        System.out.println("Change End Date (Y/N): ");
        String changeEndDate = scanner.nextLine();
        String newEndDate = "";
        if (changeEndDate.equalsIgnoreCase("Y")) {
            System.out.println("New End Date (YYYY-MM-DD): ");
            newEndDate = scanner.nextLine();
            if (!DataChecker.isValidDate(newEndDate)) {
                System.out.println("Invalid Date or Formatting");
                return;
            }
            setBuilder.addSet("CheckOut", newEndDate, true);
        }

        // Check if the date range will be valid
        String beginDateToCheck = changeBeginDate.equalsIgnoreCase("Y") ? newBeginDate
                : reservation.get().CheckIn.toString();
        String endDateToCheck = changeEndDate.equalsIgnoreCase("Y") ? newEndDate
                : reservation.get().CheckOut.toString();
        boolean isValidDateRange = DataChecker.isValidDateRange(beginDateToCheck, endDateToCheck);
        if (!isValidDateRange) {
            System.out.println("Invalid Date Range");
            return;
        }

        // - Number of children
        System.out.println("Change Number of children (Y/N): ");
        change = scanner.nextLine();
        if (change.equalsIgnoreCase("Y")) {
            System.out.println("New number of children: ");
            String newValue = scanner.nextLine();
            if (!DataChecker.isValidInteger(newValue)) {
                System.out.println("Invalid Integer");
                return;
            }
            setBuilder.addSet("Kids", newValue, false);
        }

        // - Number of adults
        System.out.println("Change Number of adults (Y/N): ");
        change = scanner.nextLine();
        if (change.equalsIgnoreCase("Y")) {
            System.out.println("New number of adults: ");
            String newValue = scanner.nextLine();
            if (!DataChecker.isValidInteger(newValue)) {
                System.out.println("Invalid Integer");
                return;
            }
            setBuilder.addSet("Adults", newValue, false);
        }

        // No changes
        if (setBuilder.sets.size() == 0) {
            System.out.println("No changes being made, skipping reservation change");
            return;
        }

        // Execute query
        WhereBuilder whereBuilder = new WhereBuilder();
        whereBuilder.addCondition("CODE = " + reservationCode);

        Database.execute("UPDATE " + Database.Table.LAB7_RESERVATIONS.s
                + setBuilder.build() + whereBuilder.build());
    }


    // FR4: Reservation Cancellation
    // Status: Complete and tested
    // Steps -> system shall accept from the user reservation code
    // - confirm cancellation
    // - remove from database
    public static void reservationCancellation_4() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        // Get reservation code
        System.out.println("Enter Reservation Code: ");
        String reservationCode = scanner.nextLine();
        if (!DataChecker.isValidInteger(reservationCode)) {
            System.out.println("Invalid Reservation Code: " + reservationCode);
            return;
        }

        // Get Cancellation Confirmation
        System.out.println("Confirm Cancellation (Y/N): ");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Failed to confirm Cancellation");
            return;
        }

        // Check if reservation code exists in the database, end if it does not
        boolean inDatabase = Database.getReservations().stream()
                .anyMatch(c -> c.CODE == Integer.parseInt(reservationCode));
        if (!inDatabase) {
            System.out.println("Reservation code does not exist in database: " + reservationCode);
            return;
        }

        // Remove from database
        // DELETE EXAMPLE: DELETE FROM table_name WHERE condition;
        WhereBuilder where = new WhereBuilder();
        where.addCondition("CODE = " + reservationCode);
        Database.execute("DELETE FROM " + Database.Table.LAB7_RESERVATIONS.s + where.build());
    }


    // TODO
    // FR5: Detailed Reservation Information
    public static void detailedReservationInformation_5() {

    }



    // TODO
    // FR6: Revenue
    public static void revenue_6() {

    }
}
