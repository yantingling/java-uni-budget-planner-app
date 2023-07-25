/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

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
