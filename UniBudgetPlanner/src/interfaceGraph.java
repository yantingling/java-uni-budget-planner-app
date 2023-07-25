import java.util.HashMap;

//interface for graph
public interface interfaceGraph {
    //monthly
    void showGraph(int month);
    HashMap<String, Double> retrieveMonthlyRecords(String filepath, int dateIndex, int amountIndex, int month);

    //daily
    void showGraph(String selectedDate);
    double retrieveDailyRecords(String filepath, int dateIndex, int amountIndex, String selectedDate);
}
