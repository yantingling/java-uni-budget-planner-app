/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

//Superclass Record
//its subclasses are AddTransaction, EditExpenses, EditIncome
public class Record {
    public double convertedAmount;

    public Date getInputDate(JDateChooser jdc)
    {
        Date date = jdc.getDate();
        return date;
    }

    public String getCategory(JComboBox<String> jcb)
    {
        String category = jcb.getSelectedItem().toString();
        return category;
    }

    public String getNotes(JTextField jtf)
    {
        String notes = jtf.getText();
        return notes;
    }

    public String getInputAmount(JTextField jtf)
    {
        String sAmount = jtf.getText();
        return sAmount;
    }

    public String validateInput(Date date, String notes, String sAmount)
    {
        //String to store error message
        String err = "";

        if(date==null && sAmount.equals("")) //empty date and amount
        {
            err = "Error: Date and Amount are empty!";
        }
        else if(date==null) //empty date
        {
            err = "Error: No date is chosen!";
        }
        else if(sAmount.equals("")) //empty amount
        {
            err = "Error: No amount is entered!";
        }
        else
        {
            try
            {
                convertedAmount = Double.parseDouble(sAmount); //convert String amount to double
                if(!notes.equals("")) //if notes or remark is not empty
                {
                    if(!notes.matches("^[a-zA-Z0-9\s()]+$")) //only accept digits, alpahabets, white sapce, parantheses
                    {
                        err = "Error: No symbols for Notes!";
                    }
                }
            }
            catch(NumberFormatException e) //if failed to parse the String amount to double (means containing alphabet, or any irrelevant symbols)
            {
                err = "Error: Only number for Amount!";
            }
        }

        return err;
    } 

}
