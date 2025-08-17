package database;

public class Main {
    public static void main(String[] args) {
        Connection connection = new Connection();
        connection.openSession();
        ConvertPurchaseList convertData = new ConvertPurchaseList();
        convertData.setIds();
        connection.closeSession();
    }
}
