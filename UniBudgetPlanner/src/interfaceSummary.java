import java.util.HashMap;

//interface for summary
public interface interfaceSummary {
    //overall
    HashMap<String, Double> calculateSummary(int categoryIndex, int amountIndex);

    //daily
    HashMap<String, Double> calculateSummary(int dateIndex, int categoryIndex, int amountIndex, String date);

    //monthly
    HashMap<String, Double> calculateSummary(int dateIndex, int categoryIndex, int amountIndex, int month);

    void displaySummary(HashMap<String, Double> summary);
    void resetSummary();
}
