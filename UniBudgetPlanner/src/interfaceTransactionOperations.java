import java.util.Date;

//interface for the transaction operations (add and edit record)
public interface interfaceTransactionOperations {
    void saveRecord(Date date, String category, String notes, String filename, String type, String dbFolder);
    void uploadImage();
}
