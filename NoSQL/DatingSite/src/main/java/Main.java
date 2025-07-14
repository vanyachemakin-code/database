
public class Main {

    public static final String ADDRESS = "redis://127.0.0.1:6379";

    public static void main(String[] args) throws InterruptedException {
        DatingSite datingSite = new DatingSite(ADDRESS);
        datingSite.print();
    }
}
