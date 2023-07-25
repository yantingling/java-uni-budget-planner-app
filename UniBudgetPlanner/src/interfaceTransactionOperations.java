/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import java.util.Date;

//interface for the transaction operations (add and edit record)
public interface interfaceTransactionOperations {
    void saveRecord(Date date, String category, String notes, String filename, String type, String dbFolder);
    void uploadImage();
}
