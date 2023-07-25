import java.util.List;

//interface for the overall records on homepage
public interface interfaceAllRecords {
    void viewRecord(String filepath);
    void removeTransaction(String filepath, int deleteLine);
    void sortTransactionRecord(String filepath);
    List<Double> getAllAmount(String filepath, int columnIndex);
    double calculateTotalFund(String filepath);
    void displayTotalFund();
}