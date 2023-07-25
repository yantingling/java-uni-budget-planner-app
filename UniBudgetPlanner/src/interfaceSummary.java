/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

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
