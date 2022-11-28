package Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;


public class SearchQuery {

    // Load an Arraylist with Student data
    // Cache this data so the program does not need to read the file for every command.
    public ArrayList<Student> students;

    // Will be set to false if students.txt has invalid formatting
    public boolean validQuery = true;

    public SearchQuery() {
        this.students = getStudents();
    }

    public boolean isValid() {
        return validQuery && students.size() > 0;
    }

    public ArrayList<Student> getStudents() {
        // Load an Arraylist with Student data
        ArrayList<Student> students = new ArrayList<>();
        try {
            String filePath = System.getProperty("user.dir") + File.separator + "students.txt";
            System.out.println("Checking File Path for students.txt: " + filePath);
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                Student student = new Student(myReader.nextLine());
                if (!student.validData) { // Error handling, if student data is invalid
                    validQuery = false;
                    return students;
                }
                students.add(student);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return students;
    }


    // R4. S[tudent]: <lastname>
    // For each entry found, print the last name, first name, grade and classroom assignment
    // for each student found and the name of their teacher (last and first name).
    //Given a student’s last name, find the student’s grade, classroom and teacher
    //(if there is more than one student with the same last name, find this information for all students);
    public void printStudentInfoWithLastName(String studentLastName) {
        students.stream()
                .filter(s -> s.StLastName.equalsIgnoreCase(studentLastName))
                .forEach(Student::printLn);
    }

    // R5. S[tudent]: <lastname> B[us]
    // For each entry found, print the last name, first name and the bus route the student takes.
    //Given a student’s last name, find the bus route the student takes
    //(if there is more than one student with the same last name, find this information for all matching students);
    public void printStudentAndBusWithLastName(String studentLastName) {
        students.stream()
                .filter(s -> s.StLastName.equalsIgnoreCase(studentLastName))
                .forEach(c -> {
                    System.out.println(c.StFirstName + " " + c.StLastName
                            + ", takes bus route " + c.Bus);
                });
    }

    // R6. T[eacher]: <lastname>
    // For each entry found, print the last and the first name of the student.
    //Given a teacher, find the list of students in his/her class;
    public void printStudentsWithTeacher(String teacherLastName) {
        students.stream()
                .filter(s -> s.TLastName.equalsIgnoreCase(teacherLastName))
                .forEach(Student::printStudentFirstLastName);
    }

    // R7. G[rade]: <Number>
    // For each entry, output the name (last and first) of the student.
    //Find all students at a specified grade level.
    public void printStudentsAtGradeLevel(int gradeLevel) {
        students.stream()
                .filter(s -> s.Grade == gradeLevel)
                .forEach(Student::printStudentFirstLastName);
    }

    // R8. B[us]: <Number>
    // For each such entry, output the name of the student (last and first) and their grade and classroom.
    //Given a bus route, find all students who take it;
    public void getStudentsWithBusRoute(int busRoute) {
        students.stream()
                .filter(s -> s.Bus == busRoute)
                .forEach(c -> {
                    System.out.println(c.StLastName + " " + c.StFirstName
                            + " is in " + c.getGrade()
                            + " and in classroom: " + c.Classroom
                    );
                });
    }

    // R9. G[rade]: <Number> H[igh] or G[rade]: <Number> L[ow]
    // For a given grade level find the average GPA of students in that grade.
    //In a given grade level find the student with the highest (lowest) GPA.
    public void printStudentGpaAtGradeLevel(int gradeLevel, boolean highest) {
        ArrayList<Student> studentsAtGradeLevel = students.stream()
                .filter(s -> s.Grade == gradeLevel)
                .collect(Collectors.toCollection(ArrayList::new));
        if (studentsAtGradeLevel.size() == 0) {
            System.out.println("There are no students with grade level: " + gradeLevel);
            return;
        }

        Student currentStudent = studentsAtGradeLevel.get(0);

        // Determining the student with either the lowest or highest GPA
        // Depending on the value provided in the parameters
        for (int i = 1; i < studentsAtGradeLevel.size(); i++) {
            Student nextStudent = studentsAtGradeLevel.get(i);
            boolean nextStudentHigherGPA = currentStudent.GPA < nextStudent.GPA;
            if (highest) { // Checking for higher GPA
                if (nextStudentHigherGPA) {
                    currentStudent = nextStudent;
                }
            } else if (!nextStudentHigherGPA) { // Checking for lower GPA
                currentStudent = nextStudent;
            }

        }

        // Print the details of current student
        currentStudent.printLn();
    }

    // R10. A[verage]: <Number>
    // the entries where the student’s grade matches the number provided
    // Compute the average GPA score for the entries found.
    // Output the grade level (the number provided in command) and the average GPA score computed.
    //For a given grade level find the average GPA of students in that grade.
    public void printGradeLevelAverageGpa(int gradeLevel) {
        ArrayList<Student> studentsAtGradeLevel = students.stream()
                .filter(s -> s.Grade == gradeLevel)
                .collect(Collectors.toCollection(ArrayList::new));
        double total = studentsAtGradeLevel.stream().mapToDouble(c -> c.GPA).sum();
        double average = total / studentsAtGradeLevel.size();
        System.out.println("The average GPA of students in grade: " + gradeLevel + " is " + average);
    }

    // R11. I[nfo]
    // When this instruction is issued your program shall perform the following actions:
    // For each grade (from 0 to 6) compute the total number of students in that grade.
    // Report the number of students in each grade in the format: <Grade>: <Number of Students>
    // sorted in ascending order by grade.
    public void printInfo() {
        for (int i = 0; i <= 6; i++) {
            int finalI = i;
            long numStudents = students.stream().filter(student -> student.Grade == finalI).count();
            System.out.println(i + ": " + numStudents);
        }
    }

}
