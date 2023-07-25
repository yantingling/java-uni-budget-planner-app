/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

public class HomePage implements interfaceAllRecords {

    // Variables declaration 
    private JFrame frame = new JFrame();   

    private JButton jbtnAdd = new JButton();
    private JButton jbtnDelete = new JButton();
    private JButton jbtnEdit = new JButton();
    private JButton jbtnGraph = new JButton();
    private JButton jbtnHome = new JButton();
    private JButton jbtnSummary = new JButton();

    private JComboBox<String> jcomboboxType = new JComboBox<>();

    private JLabel jlabelHomeExpenses  = new JLabel();
    private JLabel jlabelHomeExpensesAmount  = new JLabel();
    private JLabel jlabelHomeBalance  = new JLabel();
    private JLabel jlabelHomeBalanceAmount  = new JLabel();
    private JLabel jlabelHomeHeader  = new JLabel();
    private JLabel jlabelHomeIncome  = new JLabel();
    private JLabel jlabelHomeIncomeAmount  = new JLabel();

    private JPanel jpanelHome = new JPanel();
    private JPanel jpanelHomeBottomRow  = new JPanel();
    private JPanel jpanelHomeTotalFund = new JPanel();

    private JScrollPane jscrollpaneTransactions = new JScrollPane();
    private JTable jtableTransactions = new JTable();

    private String toEditDate = "";
    private String toEditCategory = "";
    private String toEditNotes = "";
    private String toEditAmount = "";
    private String toEditImage = "";
    private int lineNum = -1;
    // End of variables declaration
    
    public HomePage() {
        initComponents();

        //once home page is open, sort the expenses and income db
        sortTransactionRecord("expensesDB.txt");
        sortTransactionRecord("incomeDB.txt");

        // display list of expenses and total fund on default
        viewRecord("expensesDB.txt");
        displayTotalFund();
    }

    //constructor with extra parameter of int type
    //will be called after edit record (0 = expenses, 1 = income)
    public HomePage(int type) {
        initComponents();

        //once home page is open, sort the expenses and income db
        sortTransactionRecord("expensesDB.txt");
        sortTransactionRecord("incomeDB.txt");

        // display list of expenses and total fund on default
        if(type == 0)
        {
            jcomboboxType.setSelectedIndex(0);
            viewRecord("expensesDB.txt");
        }
        else if(type == 1)
        {
            jcomboboxType.setSelectedIndex(1);
            viewRecord("incomeDB.txt");
        }
        
        displayTotalFund();
    }
           
    // GUI design
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanelHome.setBackground(Color.WHITE);

        jlabelHomeHeader.setFont(new Font("Helvetica Neue", 1, 24));
        jlabelHomeHeader.setText("Home");

        jpanelHomeTotalFund.setBackground(new Color(232, 238, 241));
        jpanelHomeTotalFund.setBorder(BorderFactory.createEtchedBorder());

        jlabelHomeBalance.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelHomeBalance.setText("Balance");

        jlabelHomeBalanceAmount.setFont(new Font("Helvetica Neue", 1, 24)); 
        jlabelHomeBalanceAmount.setText("RM0.00");

        jlabelHomeIncome.setText("Income");

        jlabelHomeIncomeAmount.setText("RM0.00");

        jlabelHomeExpenses.setText("Expenses");

        jlabelHomeExpensesAmount.setText("RM0.00");

        GroupLayout jpanelHomeTotalFundLayout = new GroupLayout(jpanelHomeTotalFund);
        jpanelHomeTotalFund.setLayout(jpanelHomeTotalFundLayout);
        jpanelHomeTotalFundLayout.setHorizontalGroup(
            jpanelHomeTotalFundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelHomeTotalFundLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpanelHomeTotalFundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelHomeTotalFundLayout.createSequentialGroup()
                        .addGroup(jpanelHomeTotalFundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelHomeBalance)
                            .addComponent(jlabelHomeBalanceAmount, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpanelHomeTotalFundLayout.createSequentialGroup()
                        .addComponent(jlabelHomeIncome)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelHomeIncomeAmount, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlabelHomeExpenses)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelHomeExpensesAmount, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpanelHomeTotalFundLayout.setVerticalGroup(
            jpanelHomeTotalFundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelHomeTotalFundLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jlabelHomeBalance)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelHomeBalanceAmount, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanelHomeTotalFundLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelHomeIncome)
                    .addComponent(jlabelHomeIncomeAmount)
                    .addComponent(jlabelHomeExpenses)
                    .addComponent(jlabelHomeExpensesAmount))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jpanelHomeBottomRow.setBackground(Color.WHITE);
        jpanelHomeBottomRow.setLayout(new GridLayout(1, 4, 15, 0));

        jbtnHome.setIcon(new ImageIcon(getClass().getResource("Assets/home.png"))); 
        jpanelHomeBottomRow.add(jbtnHome);

        jbtnAdd.setIcon(new ImageIcon(getClass().getResource("Assets/add.png"))); 
        jbtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });
        jpanelHomeBottomRow.add(jbtnAdd);

        jbtnSummary.setIcon(new ImageIcon(getClass().getResource("Assets/coins.png"))); 
        jbtnSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSummaryActionPerformed(evt);
            }
        });
        jpanelHomeBottomRow.add(jbtnSummary);

        jbtnGraph.setIcon(new ImageIcon(getClass().getResource("Assets/chart-line-up.png"))); 
        jbtnGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnGraphActionPerformed(evt);
            }
        });
        jpanelHomeBottomRow.add(jbtnGraph);

        jcomboboxType.setModel(new DefaultComboBoxModel<>(new String[] { "Expenses", "Income" }));
        jcomboboxType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jcomboboxTypeActionPerformed(evt);
            }
        });

        jscrollpaneTransactions.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        jtableTransactions.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><b>Date</b></html>", "<html><b>Category</b></html>", "<html><b>Notes</b></html>", "<html><b>Amount</b></html>", "<html><b>Image</b></html>"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtableTransactions.setAutoscrolls(false);
        jtableTransactions.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jtableTransactions.setShowGrid(true);
        jtableTransactions.getTableHeader().setReorderingAllowed(false);
        jtableTransactions.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jtableTransactionsMouseClicked(evt);
            }
        });
        jscrollpaneTransactions.setViewportView(jtableTransactions);

        jbtnEdit.setIcon(new ImageIcon(getClass().getResource("Assets/edit.png"))); 
        jbtnEdit.setText("Edit");
        jbtnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEditActionPerformed(evt);
            }
        });

        jbtnDelete.setIcon(new ImageIcon(getClass().getResource("Assets/trash.png"))); 
        jbtnDelete.setText("Delete");
        jbtnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });

        GroupLayout jpanelHomeLayout = new GroupLayout(jpanelHome);
        jpanelHome.setLayout(jpanelHomeLayout);
        jpanelHomeLayout.setHorizontalGroup(
            jpanelHomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelHomeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpanelHomeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlabelHomeHeader, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanelHomeTotalFund, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanelHomeBottomRow, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jscrollpaneTransactions, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jpanelHomeLayout.createSequentialGroup()
                        .addComponent(jcomboboxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnEdit)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnDelete)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jpanelHomeLayout.setVerticalGroup(
            jpanelHomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelHomeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlabelHomeHeader, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelHomeTotalFund, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelHomeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jcomboboxType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnEdit)
                    .addComponent(jbtnDelete, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jscrollpaneTransactions, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelHomeBottomRow, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelHome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelHome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }                 

    // redirect to [Graph] page when the Graph button is clicked
    private void jbtnGraphActionPerformed(ActionEvent evt) {  
        frame.dispose();
        new Graph();                                        
    }                                         

    // redirect to [Add Transaction] page when the + button is clicked
    private void jbtnAddActionPerformed(ActionEvent evt) {                                        
        frame.dispose();
        if(jcomboboxType.getSelectedIndex()==0) 
        {
            //add expenses
            new AddTransaction(0);
        }
        else if(jcomboboxType.getSelectedIndex()==1)
        {
            //add income
            new AddTransaction(1);
        }
    }                                                                          

    // redirect to [Summary] page when the summary button is clicked
    private void jbtnSummaryActionPerformed(ActionEvent evt) {                                            
        frame.dispose();
        new Summary();
    }                                           

    // display the records based on the type chosen (expenses/income)
    private void jcomboboxTypeActionPerformed(ActionEvent evt) {                                              
        String type = jcomboboxType.getSelectedItem().toString();

        if(type.equals("Expenses"))
        {
            viewRecord("expensesDB.txt");
        }
        else if(type.equals("Income"))
        {
            viewRecord("incomeDB.txt");
        }
    }                                             

    // select a row of data by clicking the table
    private void jtableTransactionsMouseClicked(MouseEvent evt) {                                                
        selectRecord();
    }      
    
    // delete a record from expenses/income (CRUD) - click the delete button
    private void jbtnDeleteActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jtableTransactions.getModel();

        if(jtableTransactions.getSelectedRowCount() == 1)
        {
            // prompt user to confirm deletion of record
            int result = JOptionPane.showConfirmDialog(null, "Confirm to delete the data?", "Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            // if confirm [YES], remove the data from the DB and table, then calculate and display new total fund
            if(result == JOptionPane.YES_OPTION)
            {
                if(jcomboboxType.getSelectedItem().toString().equals("Expenses")) //Expenses record
                {
                    removeTransaction("expensesDB.txt", lineNum);
                }
                else
                {
                    removeTransaction("incomeDB.txt", lineNum); //Income record
                }

                model.removeRow(jtableTransactions.getSelectedRow());
                displayTotalFund();
            }
        }
        else 
        {
            if (jtableTransactions.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(null, "The table is empty!");
            }    
            else
            {
                JOptionPane.showMessageDialog(null, "Please select a row of data to be deleted!");
            }
        }
    }

    // edit a record from expenses/income (CRUD)
    private void jbtnEditActionPerformed(ActionEvent evt) {  
        
        //if a row is selected, redirect the user to EditExpenses or EditIncome page
        if(jtableTransactions.getSelectedRowCount() == 1) 
        {
            frame.dispose();

            String type = jcomboboxType.getSelectedItem().toString();

            if(type.equals("Expenses"))
            {
                new EditExpenses(toEditDate, toEditCategory, toEditNotes, toEditAmount, toEditImage, lineNum);
            }
            else if(type.equals("Income"))
            {
                new EditIncome(toEditDate, toEditCategory, toEditNotes, toEditAmount, toEditImage, lineNum);
            }
        }
        else // a row is not selected
        {
            //if the table is empty
            if(jtableTransactions.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(null, "The table is empty!");
            }
            else //not empty
            {
                JOptionPane.showMessageDialog(null, "Please select a row of data to be edited!");
            }
        }                                      
    }                                        

    // select and assign the record chosen
    private void selectRecord()
    {      
        DefaultTableModel model = (DefaultTableModel) jtableTransactions.getModel();
        //set data to textfield when row is selected
        toEditDate = model.getValueAt(jtableTransactions.getSelectedRow(), 0).toString();
        toEditCategory = model.getValueAt(jtableTransactions.getSelectedRow(), 1).toString();
        toEditNotes = model.getValueAt(jtableTransactions.getSelectedRow(), 2).toString();
        toEditAmount = model.getValueAt(jtableTransactions.getSelectedRow(), 3).toString();
        toEditImage = model.getValueAt(jtableTransactions.getSelectedRow(), 4).toString();
        lineNum = jtableTransactions.getSelectedRow()+1;
    }

    //display all record for expenses or income (CRUD) 
    @Override
    public void viewRecord(String filepath)
    {
        try 
        {
            // Reference: https://youtu.be/Ix9Y0XWvZL4
            // Retrieved data from the txt file selected and add to the JTable
            File f = new File(filepath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            DefaultTableModel model = (DefaultTableModel)jtableTransactions.getModel();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();

            Object[] object = br.lines().toArray();

            for (int i=0; i<object.length; i++) {
                if(object[i].toString().equals(""))
                {
                    break;
                }
                else
                {
                    String[] data = object[i].toString().split(",");
                    model.addRow(data);
                }
            }

            fr.close();
            br.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File is not found!");
        }
    }   
    
    //delete a record from expenses/income (CRUD) - method to be called in the jbtnDeleteActionPerformed
    @Override
    public void removeTransaction(String filepath, int deleteLine)
    {
        try 
        {
            File dbfile = new File(filepath); //selected file

            //read the selected file
            FileReader fr = new FileReader(dbfile);
            BufferedReader br = new BufferedReader(fr);

            String currentLine = br.readLine();
            int line = 1;
            String newContent = "";

            while(currentLine != null)
            {
                if(deleteLine != line)
                {
                    //concatenate the contents of selected file (without the to-be-deleted one)
                    newContent += (currentLine +  System.lineSeparator());
                }
                line++;
                currentLine = br.readLine();
            }

            FileWriter fw = new FileWriter(dbfile);
            BufferedWriter bw = new BufferedWriter(fw);

            //rewrite the file with new content
            bw.write(newContent);
            br.close();
            bw.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to delete record!");
        }
    }

    //method to sort the records in txt file/DB (date order)
    @Override
    public void sortTransactionRecord(String filepath)
    {
        try {
            // Read the contents of the text file into a list
            List<String> lines = Files.readAllLines(Paths.get(filepath));

            // Sort the lines using a custom comparator
            lines.sort(new Comparator<String>() 
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                public int compare(String line1, String line2) {
                    try {
                        Date date1 = dateFormat.parse(line1);
                        Date date2 = dateFormat.parse(line2);
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });

            // Write the sorted lines back to the text file
            Files.write(Paths.get(filepath), lines);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    //retrieve all the amount from the DB
    @Override
    public List<Double> getAllAmount(String filePath, int columnIndex)
    {
        // Declare an arraylist to store the amount
        List<Double> columnData = new ArrayList<>();
        try
        {
            FileReader fr = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] columns = line.split(","); // Split line by comma
                if (columnIndex >= 0 && columnIndex < columns.length) 
                {
                    try 
                    {
                        //parse the String amount to double
                        double columnValue = Double.parseDouble(columns[columnIndex]);
                        columnData.add(columnValue);
                    }
                    catch (NumberFormatException e)
                    {
                        // Handle if the column value cannot be parsed as a double
                        e.printStackTrace();
                    }
                }
            }

            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return columnData;
    }

    //calculate total fund of income/expenses
    @Override
    public double calculateTotalFund(String filepath)
    {
        List<Double> columnData = new ArrayList<>();
        columnData = getAllAmount(filepath, 3);
        double total = 0.0;

        for (double value : columnData)
        {
            total += value;
        }

        return total;
    }

    //display the total fund on homepage
    @Override
    public void displayTotalFund()
    {
        //calculate total expenses, total income, and balance
        double totalExpenses = calculateTotalFund("expensesDB.txt");
        double totalIncome = calculateTotalFund("incomeDB.txt");
        double balanceOfFund = (totalIncome - totalExpenses);

        //format the value in the form of 0.00
        String sTE = String.format("%.2f",totalExpenses);
        String sTI = String.format("%.2f", totalIncome);
        String sBF = String.format("%.2f", balanceOfFund);

        //set the values as text
        jlabelHomeExpensesAmount.setText("RM" + sTE);
        jlabelHomeIncomeAmount.setText("RM" + sTI);

        if(balanceOfFund < 0)
        {
            //if the balance is negative, display in red and with -ve sign before RM
            sBF = sBF.replace("-", "");
            jlabelHomeBalanceAmount.setText("- RM" + sBF);
            jlabelHomeBalanceAmount.setForeground(Color.RED);
        }
        else if (balanceOfFund > 0)
        {
            //if the balance is positive, display in green
            jlabelHomeBalanceAmount.setText("RM" + sBF);
            jlabelHomeBalanceAmount.setForeground(new Color(53, 94, 59));
        }
        else if (balanceOfFund == 0)
        {
            jlabelHomeBalanceAmount.setText("RM0.00");
            jlabelHomeBalanceAmount.setForeground(Color.BLACK);
        }
    }

}
