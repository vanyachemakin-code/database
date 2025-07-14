import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {

    private static ResultSet resultSet;
    private static Statement statement;
    private static java.sql.Connection connection;

    public Connection(String url, String user, String pass) {
        getConnection(url, user, pass);
        getTable(statement);
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    private void getConnection(String url, String user, String pass) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public static void close() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void getTable(Statement statement) {
        try {
            resultSet = statement.executeQuery(
                    "SELECT pl.course_name, pl.subscription_date " +
                            "FROM PurchaseList pl " +
                            "ORDER BY pl.course_name, pl.subscription_date;"
            );
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
