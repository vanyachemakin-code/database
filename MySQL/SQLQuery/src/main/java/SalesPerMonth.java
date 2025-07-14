import java.util.*;

public class SalesPerMonth {

    private final List<String> averageSalesForCourses = new ArrayList<>();

    public SalesPerMonth(Map<String, List<Date>> subscriptions) {
        getAverage(subscriptions);
    }

    public List<String> getAverageSalesForCourses() {
        return averageSalesForCourses;
    }

    private void getAverage(Map<String, List<Date>> subscriptions) {
        Set<String> courses = subscriptions.keySet();

        for (String course : courses) {
            getPeriod(subscriptions, course);
            float averageSales = Math.round
                    ((float) subscriptions.get(course).size() / getPeriod(subscriptions, course) * 100) / 100.f;
            averageSalesForCourses.add(course + " - " + averageSales);
        }
    }

    private int getPeriod(Map<String, List<Date>> subscriptions, String course) {
        int firstMonth = subscriptions.get(course).getFirst().getMonth() + 1;
        int lastMonth = subscriptions.get(course).getLast().getMonth() + 1;
        return lastMonth - firstMonth + 1;
    }
}
