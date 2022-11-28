package Lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Name: Winton Gee
 * Date: 09/21/2022
 * Assignment: schoolsearch, Lab1
 */
public class schoolsearch {

    // If the students.txt is not available, or has the wrong format - exit the program.
    // If an unrecognized search instruction is provided, go back to the prompt.
    public static void main(String[] args) {
        SearchQuery searchQuery = new SearchQuery();
        // Error Handling -> Check if students.txt is available and valid
        if (!searchQuery.isValid()) {
            System.out.println("Error with file: students.txt");
            return;
        }

        // Information about the commands
        System.out.println("Commands: ");
        System.out.println("[S] Show student's info, Input: Student's last Name");
        System.out.println("[SB] Show student's name and bus route, Input: Student's last name");
        System.out.println("[T] Show students with teacher, Input: Teacher's last name");
        System.out.println("[G] Show students at grade level, Input: Grade Level");
        System.out.println("[B] Show students with bus route, Input: Bus route");
        System.out.println("[GHL] Show info of student with lowest/highest GPA at Grade Level," +
                " Input: Grade Level, Highest or Lowest");
        System.out.println("[A] Show average GPA at grade level, Input: Grade Level");
        System.out.println("[I] Show number of students at every grade level");
        System.out.println("[Q] Quit the program");

        String currentTask = "";
        Scanner myObj = new Scanner(System.in);
        while (!currentTask.equals("Q")) {
            System.out.println("");
            currentTask = myObj.nextLine();
            nextTask(currentTask, searchQuery);

        }
    }

    private static void nextTask(String task, SearchQuery searchQuery) {
        Scanner scanner = new Scanner(System.in);
        switch (task) {
            case "S": // R4. S[tudent]: <lastname>
                System.out.println("Student's last name: ");
                searchQuery.printStudentInfoWithLastName(scanner.next());
                return;

            case "SB": // R5. S[tudent]: <lastname> B[us]
                System.out.println("Student's last name: ");
                String studentLastName = scanner.next();
                searchQuery.printStudentAndBusWithLastName(studentLastName);
                return;

            case "T": // R6. T[eacher]: <lastname>
                System.out.println("Teacher's last name: ");
                searchQuery.printStudentsWithTeacher(scanner.next());
                return;

            case "G": // R7. G[rade]: <Number>
                System.out.println("Grade Level: ");
                searchQuery.printStudentsAtGradeLevel(scanner.nextInt());
                return;

            case "B": // R8. B[us]: <Number>
                System.out.println("Bus Number: ");
                int busNumber = scanner.nextInt();
                searchQuery.getStudentsWithBusRoute(busNumber);
                return;

            case "GHL": // R9. G[rade]: <Number> H[igh] or G[rade]: <Number> L[ow]
                System.out.println("Grade Level: ");
                int gradeLevel = scanner.nextInt();
                System.out.println("Type H for highest, L for lowest: ");
                String input = scanner.next();
                boolean highest = input.equals("H");
                searchQuery.printStudentGpaAtGradeLevel(gradeLevel, highest);
                return;

            case "A": // R10. A[verage]: <Number>
                System.out.println("Grade Level: ");
                searchQuery.printGradeLevelAverageGpa(scanner.nextInt());
                return;

            case "I": // R11. I[nfo]
                searchQuery.printInfo();
                return;

            case "Q": // R12. Q[uit]
                // Note: Nothing needed, program will end.
                return;

            default:
                System.out.println("Unable to determine task");
        }
    }

}
