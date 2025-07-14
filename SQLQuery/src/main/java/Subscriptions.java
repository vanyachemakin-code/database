import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class Subscriptions {

    private final LinkedHashMap<String, List<Date>> subscriptionToTheCourse = new LinkedHashMap<>();

    public Subscriptions(ResultSet resultSet) {
        getSubscriptions(resultSet);
    }

    public LinkedHashMap<String, List<Date>> getSubscriptionToTheCourse() {
        return subscriptionToTheCourse;
    }

    private void getSubscriptions(ResultSet resultSet) {
        List<String> name = new ArrayList<>();
        List<Date> date = new ArrayList<>();

        try {
            while (resultSet.next()) {
                name.add(resultSet.getString("course_name"));

                if (!name.getFirst().equals(resultSet.getString("course_name"))) {
                    subscriptionToTheCourse.put(name.getFirst(), date);
                    name = new ArrayList<>();
                    date = new ArrayList<>();
                }
                date.add(resultSet.getDate("subscription_date"));

                if (resultSet.isLast()) {
                    subscriptionToTheCourse.put(name.getFirst(), date);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
