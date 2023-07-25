/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

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
