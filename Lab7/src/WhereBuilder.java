import java.util.ArrayList;

// This class is used for building the where condition, which will be used for sending queries.
public class WhereBuilder {

    ArrayList<String> conditions = new ArrayList<>();

    public String build() {
        if (conditions.size() == 0)
            return "";
        String string = " WHERE ";
        string = string + String.join(" AND ", conditions);

        return string;
    }

    public void addCondition(String condition) {
        this.conditions.add(condition);
    }


}
