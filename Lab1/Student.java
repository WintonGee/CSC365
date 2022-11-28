package Lab1;

public class Student {

    // StLastName, StFirstName, Grade, Classroom, Bus, GPA, TLastName, TFirstName
    // DROP, SHERMAN, 0, 104, 51, 2.65, NIBLER,  JERLENE

    // This will be set to false if any invalid data is detected.
    // Used for ending the program if any invalid data is present
    public boolean validData = true;

    public String StLastName, StFirstName;
    public int Grade, Classroom, Bus;
    public double GPA;
    public String TLastName, TFirstName;

    public Student(String stLastName, String stFirstName, int grade, int classroom, int bus, double gpa, String tLastName, String tFirstName) {
        this.StLastName = stLastName;
        this.StFirstName = stFirstName;
        this.Grade = grade;
        this.Classroom = classroom;
        this.Bus = bus;
        this.GPA = gpa;
        this.TLastName = tLastName;
        this.TFirstName = tFirstName;
    }

    public Student(String dataLine) {
        String[] data = dataLine.split(",");
        if (data.length != 8) {
            System.out.println("Incorrect Number of Commas: " + dataLine);
            validData = false;
            return;
        }

        this.StLastName = data[0];
        this.StFirstName = data[1];

        // Error checking and handling
        if (!isNumeric(data[2]) || !isNumeric(data[3]) || !isNumeric(data[4]) || !isNumeric(data[5])) {
            System.out.println("Unable to parse: " + dataLine);
            validData = false;
            return;
        }

        this.Grade = Integer.parseInt(data[2]);
        this.Classroom = Integer.parseInt(data[3]);
        this.Bus = Integer.parseInt(data[4]);
        this.GPA = Double.parseDouble(data[5]);

        this.TLastName = data[6];
        this.TFirstName = data[7];
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String getGrade() {
        return switch (Grade) {
            case 0 -> "Kindergarten";
            case 1 -> "First Grade";
            case 2 -> "Second Grade";
            case 3 -> "Third Grade";
            case 4 -> "Fourth Grade";
            case 5 -> "Fifth Grade";
            case 6 -> "Sixth Grade";
            default -> "Unknown";
        };
    }

    public void printStudentFirstLastName() {
        System.out.println(StFirstName + " " + StLastName);
    }

    public void printLn() {
        System.out.println(StFirstName + " " + StLastName
                + ", who takes bus route: " + Bus
                + ", is a " + getGrade()
                + " assigned to the class of " + TFirstName + ", " + TLastName
                + " in the classroom " + Classroom
                + ". " + StFirstName + "  has a GPA of " + GPA + ".");
    }

}
