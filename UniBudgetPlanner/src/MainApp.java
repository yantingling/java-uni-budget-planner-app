/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

public class MainApp {
    public static void main(String[] args) {

        /* FlatLaf is a modern open-source cross-platform Look and Feel for Java Swing desktop applications
        https://www.formdev.com/flatlaf/ */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } 
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        // Create Splash Screen
        new Splash();  
    }
}
