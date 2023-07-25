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

/* Task contributions:

--------------------------------------------------------
Ling Yan Ting (78115)

1.	Created and implemented:
    a.	Class MainApp
    b.	Class Summary
    c.	Class Graph
    d.	Interface interfaceSummary
    e.	Interface interfaceGraph

2.	Tested and debug:
    a.	Class AddTransaction
    b.	Class EditExpenses
    c.	Class EditIncome
    d.	Interface interfaceTransactionOperations

--------------------------------------------------------
Juwie Karen Christney (79725)

1.	Created and implemented:
    a.	Class Splash
    b.	Class HomePage
    c.	Interface interfaceAllRecords
    d.	Interface interfaceTransactionOperations

2.	Tested and debug:
    a.	Class Summary
    b.	Class EditExpenses
    c.	Class EditIncome
    d.	Interface interfaceSummary

--------------------------------------------------------
Nik Nur Maiza Arisha Shamira binti Sakri (78319)

1.	Created and implemented:
    a.	Class Record
    b.	Class AddTransaction
    c.	Interface interfaceAllRecords
    d.	Interface interfaceTransactionOperations

2.	Tested and debug:
    a.	Class Splash
    b.	Class Graph
    c.	Interface interfaceGraph

--------------------------------------------------------
Marchell Dwayne Anak Damien (79963)

1.	Created and implemented:
    a.	Class EditExpenses
    b.	Class EditIncome
    c.	Interface interfaceAllRecords
    d.	Interface interfaceTransactionOperations

2.	Tested and debug:
    a.	Class HomePage
    b.	Class Record
    c.	Class AddTransaction
*/