
public class Statistics {

    private static final String USER = "root";
    private static final String PASS = "ajfj39jfk124jdka";
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox";

    public static void main(String[] args) {
        Connection connection = new Connection(URL, USER, PASS);
        Subscriptions subscriptions = new Subscriptions(Connection.getResultSet());
        SalesPerMonth salesPerMonth = new SalesPerMonth(subscriptions.getSubscriptionToTheCourse());
        System.out.println(salesPerMonth.getAverageSalesForCourses());
        Connection.close();
    }
}
