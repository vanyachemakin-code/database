public class Statistics {

    private static final String USER = "user";
    private static final String PASS = "some pass";
    private static final String URL = "jdbc:mysql://localhost:3306/database";

    public static void main(String[] args) {
        Connection connection = new Connection(URL, USER, PASS);
        Subscriptions subscriptions = new Subscriptions(Connection.getResultSet());
        SalesPerMonth salesPerMonth = new SalesPerMonth(subscriptions.getSubscriptionToTheCourse());
        System.out.println(salesPerMonth.getAverageSalesForCourses());
        Connection.close();
    }
}
