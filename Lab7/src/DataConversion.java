import java.sql.Date;

// This class will be used for converting ANSI SQL Data Type to Java Type
// PreCondition for all functions in this class: All strings are valid for the data type
public class DataConversion {

    public static Date toDate(String s) {
        return Date.valueOf(s);
    }

}
