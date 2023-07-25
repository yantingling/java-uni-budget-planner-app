import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;


public class Graph implements interfaceGraph{

    //variables declaration
    private JFrame frame = new JFrame();
    private JPanel jpanelGraph = new JPanel();
    private JLabel jlabelAnalysis = new JLabel();
    private JPanel jpanelPutGraph = new JPanel();
    private JPanel jpanelAsBottomBtns = new JPanel();
    private JButton jbtnAsHome = new JButton();
    private JButton jbtnAsAdd = new JButton();
    private JButton jtbnAsSummary = new JButton();
    private JButton jbtnAsGraph = new JButton();
    private JComboBox<String> jcomboboxAnalysis = new JComboBox<>();
    private JDateChooser jdatechooserA = new JDateChooser();
    private JMonthChooser jmonthchooserA = new JMonthChooser();
    private JButton jbtnAsCheck = new JButton();

    private int currentYear = Year.now().getValue();
    private double dailyExpenses;
    private double dailyIncome;
    HashMap<String, Double> monthlyExpensesHM;
    HashMap<String, Double> monthlyIncomeHM;
    //end of variables declaration
    
    public Graph() {
        initComponents();
        showGraph(jmonthchooserA.getMonth()+1);
    }

    //Constructor with parameter (integer month) - for monthly graph
    public Graph(int month) {
        initComponents();
        showGraph(month);
        jmonthchooserA.setMonth(month-1);
    }

    //Constructor with parameter (String selectedDate) - for daily graph
    public Graph(String selectedDate) {
        initComponents();
        showGraph(selectedDate);
        jcomboboxAnalysis.setSelectedItem("Daily");
        Date date;

        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate);
            jdatechooserA.setVisible(true);
            jdatechooserA.setDate(date);
            jmonthchooserA.setVisible(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //GUI Design                         
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jpanelGraph.setBackground(Color.WHITE);

        jlabelAnalysis.setFont(new Font("Helvetica Neue", 1, 24)); 
        jlabelAnalysis.setText("Analysis");

        jpanelPutGraph.setBackground(Color.WHITE);
        jpanelPutGraph.setBorder(BorderFactory.createEtchedBorder());
        jpanelPutGraph.setLayout(new BorderLayout());

        jpanelAsBottomBtns.setBackground(new Color(255, 255, 255));
        jpanelAsBottomBtns.setLayout(new GridLayout(1, 4, 15, 0));

        jbtnAsHome.setIcon(new ImageIcon(getClass().getResource("Assets/home.png"))); 
        jbtnAsHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAsHomeActionPerformed(evt);
            }
        });
        jpanelAsBottomBtns.add(jbtnAsHome);

        jbtnAsAdd.setIcon(new ImageIcon(getClass().getResource("Assets/add.png"))); 
        jbtnAsAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAsAddActionPerformed(evt);
            }
        });
        jpanelAsBottomBtns.add(jbtnAsAdd);

        jtbnAsSummary.setIcon(new ImageIcon(getClass().getResource("Assets/coins.png"))); 
        jtbnAsSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jtbnAsSummaryActionPerformed(evt);
            }
        });
        jpanelAsBottomBtns.add(jtbnAsSummary);

        jbtnAsGraph.setIcon(new ImageIcon(getClass().getResource("Assets/chart-line-up.png"))); 
        jpanelAsBottomBtns.add(jbtnAsGraph);

        jcomboboxAnalysis.setModel(new DefaultComboBoxModel<>(new String[] { "Monthly", "Daily" }));
        jcomboboxAnalysis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jcomboboxAnalysisActionPerformed(evt);
            }
        });

        jdatechooserA.setVisible(false);
        jdatechooserA.setDateFormatString("dd/MM/y");
        jmonthchooserA.setBorder(BorderFactory.createEtchedBorder());

        jbtnAsCheck.setIcon(new ImageIcon(getClass().getResource("Assets/check.png"))); 
        jbtnAsCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAsCheckActionPerformed(evt);
            }
        });

        GroupLayout jpanelGraphLayout = new GroupLayout(jpanelGraph);
        jpanelGraph.setLayout(jpanelGraphLayout);
        jpanelGraphLayout.setHorizontalGroup(
            jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelGraphLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpanelGraphLayout.createSequentialGroup()
                        .addGroup(jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(jpanelGraphLayout.createSequentialGroup()
                                .addComponent(jlabelAnalysis, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpanelGraphLayout.createSequentialGroup()
                                .addComponent(jcomboboxAnalysis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jdatechooserA, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jmonthchooserA, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jbtnAsCheck, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpanelPutGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanelAsBottomBtns, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpanelGraphLayout.setVerticalGroup(
            jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelGraphLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlabelAnalysis, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelGraphLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnAsCheck, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jdatechooserA, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jmonthchooserA, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcomboboxAnalysis, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelPutGraph, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpanelAsBottomBtns, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
       frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelGraph, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("UNI Budget Planner");
        frame.setResizable(false);
        frame.setVisible(true);
    }                       

    //redirect to HomePage
    private void jbtnAsHomeActionPerformed(ActionEvent evt)
    {
        frame.dispose();
        new HomePage();
    }

    //redirect to AddTransaction page
    private void jbtnAsAddActionPerformed(ActionEvent evt)
    {
        frame.dispose();
        new AddTransaction();
    }

    //redirect to Summary page
    private void jtbnAsSummaryActionPerformed(ActionEvent evt)
    {
        frame.dispose();
        new Summary();
    }

    private void jcomboboxAnalysisActionPerformed(ActionEvent evt) {                                                  
        if(jcomboboxAnalysis.getSelectedItem().toString().equals("Monthly"))
        {
            jdatechooserA.setVisible(false);
            jmonthchooserA.setVisible(true);
        }
        else
        {
            jmonthchooserA.setVisible(false);
            jdatechooserA.setVisible(true);
        }
    }  

    private void jbtnAsCheckActionPerformed(ActionEvent evt) {                                                  
        if(jcomboboxAnalysis.getSelectedItem().toString().equals("Monthly"))
        {
            int month = jmonthchooserA.getMonth() + 1;
            frame.dispose();
            new Graph(month);
        }
        else if(jcomboboxAnalysis.getSelectedItem().toString().equals("Daily"))
        {
            if(jdatechooserA.getDate() != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(jdatechooserA.getDate());
                frame.dispose();
                new Graph(date);
            }
        }
    }  

    //show [MONTHLY] graph
    @Override
    public void showGraph(int month)
    {
        monthlyExpensesHM = retrieveMonthlyRecords("expensesDB.txt", 0, 3, month);
        monthlyIncomeHM = retrieveMonthlyRecords("incomeDB.txt", 0, 3, month);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //loop the hashmap
        for (String i: monthlyExpensesHM.keySet())
        {
            dataset.setValue(monthlyExpensesHM.get(i), "Expenses", i);
        }       
        
        for (String j: monthlyIncomeHM.keySet())
        {
            dataset.setValue(monthlyIncomeHM.get(j), "Income", j);
        }   

        //declare an arraylist to store all the sorted values from dataset
        List<String> sortDataset = new ArrayList<>();

        //get the row count and column count of the dataset
        //series count = 2 (expenses and income)
        //category count = depends on the number of spending days in that particular month
        int seriesCount = dataset.getRowCount();
        int categoryCount = dataset.getColumnCount();

        //nested for-loop
        for (int series = 0; series < seriesCount; series++) 
        {
            for (int category = 0; category < categoryCount; category++) 
            {
                //get the seriesKey and categoryKey
                Comparable<?> seriesKey = dataset.getRowKey(series);
                Comparable<?> categoryKey = dataset.getColumnKey(category);

                //if value exists from the dataset
                if(dataset.getValue(series, category)!= null)
                {
                    //concatenate the categoryKey(spending day), seriesKey(expenses/income), values
                    //add the line to the array list (sortDataset)
                    String line = (categoryKey + "," + seriesKey  + "," + dataset.getValue(series, category));
                    sortDataset.add(line);
                }
            }
        }

        //sort the list in natural order (ascending order)
        Collections.sort(sortDataset, Comparator.naturalOrder());

        //clear the original dataset
        dataset.clear();

        //reassign the dataset with the values sorted and stored in the array list (sortDataset)
        for(int i=0; i<sortDataset.size(); i++)
        {
            String[] splittedThing = sortDataset.get(i).split(",");
            double amount = Double.parseDouble(splittedThing[2]);

            dataset.setValue(amount, splittedThing[1], splittedThing[0]);
        }

        //to name the month
        String selectedMonth = "";
        
        switch(month)
        {
            case 1: selectedMonth = "January"; break;
            case 2: selectedMonth = "February"; break;
            case 3: selectedMonth = "March"; break;
            case 4: selectedMonth = "April"; break;
            case 5: selectedMonth = "May"; break;
            case 6: selectedMonth = "June"; break;
            case 7: selectedMonth = "July"; break;
            case 8: selectedMonth = "August"; break;
            case 9: selectedMonth = "September"; break;
            case 10: selectedMonth = "October"; break;
            case 11: selectedMonth = "November"; break;
            case 12: selectedMonth = "November"; break;
        }

        String chartTitle = (selectedMonth + "\'s Record");
        String xAxisLabel = ("Spending Days in " + selectedMonth + " " + currentYear);

        //create a bar chart using JFreeChart (chartTitle, xAxisLabel, yAxisLabel, dataset)
        //customize the chart appearance (Reference: http://tatiyants.com/how-to-customize-jfree-charts/)
        JFreeChart chart = ChartFactory.createBarChart(chartTitle, xAxisLabel, "Total Amount (RM)", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.black);
        plot.setOutlineVisible(false);

        renderer.setGradientPaintTransformer(null);
        renderer.setBarPainter(new StandardBarPainter());

        Paint[] colors = {new Color(30,65,138), new Color(0,156,102)};
        
        // change the default colors
        for (int i = 0; i < 2; i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }

        chart.getTitle().setFont(new Font("Helvetica Neue", 1, 17));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(310, 450));

        //add the graph to the JPanel created
        jpanelPutGraph.add(chartPanel, BorderLayout.CENTER);
    }

    //retrieve [MONTHLY] records to be later added into the graph dataset
    //we assume that monthly records can only be retrieved for the current year (2023)
    @Override
    public HashMap<String, Double> retrieveMonthlyRecords(String filepath, int dateIndex, int amountIndex, int month)
    {
        HashMap<String, Double> record = new HashMap<String, Double>();

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] columns = line.split(","); // Split line by comma

                if (dateIndex >= 0 && dateIndex < columns.length)
                {
                    String[] splitDate = columns[dateIndex].split("/");
                    int selectedMonth = Integer.parseInt(splitDate[1]);
                    int selectedYear = Integer.parseInt(splitDate[2]);
                    String day = splitDate[0];

                    if(selectedMonth == month && selectedYear == currentYear) //if month in the DB equals to the selected month
                    {
                        double amount = Double.parseDouble(columns[amountIndex]);

                        //if the hashmap contain the day as key
                        if(record.containsKey(columns[dateIndex]))
                        {
                            double oriAmount = record.get(day);
                            amount += oriAmount;
                            record.put(day, amount);
                        }
                        else
                        {
                            record.put(day, amount);
                        }
                    }
                }
            }

            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return record;
    }

    //show [DAILY] graph
    @Override
    public void showGraph(String selectedDate)
    {
        dailyExpenses = retrieveDailyRecords("expensesDB.txt", 0, 3, selectedDate);
        dailyIncome = retrieveDailyRecords("incomeDB.txt", 0, 3, selectedDate);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(dailyExpenses, "Expenses", selectedDate);
        dataset.setValue(dailyIncome, "Income", selectedDate);
        
        String chartTitle = ("Daily Record of " + selectedDate);
        String xAxisLabel = "Chosen Date";

        //create a bar chart using JFreeChart (chartTitle, xAxisLabel, yAxisLabel, dataset)
        //customize the chart appearance (Reference: http://tatiyants.com/how-to-customize-jfree-charts/)
        JFreeChart chart = ChartFactory.createBarChart(chartTitle, xAxisLabel, "Total Amount (RM)", dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.black);
        plot.setOutlineVisible(false);

        renderer.setGradientPaintTransformer(null);
        renderer.setBarPainter(new StandardBarPainter());

        Paint[] colors = {new Color(30,65,138), new Color(0,156,102)};
        
        // change the default colors
        for (int i = 0; i < 2; i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }

        chart.getTitle().setFont(new Font("Helvetica Neue", 1, 17));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(310, 450));

        //add the graph to the JPanel created
        jpanelPutGraph.add(chartPanel, BorderLayout.CENTER);
    }
    
    //retrieve [DAILY] records to be later added into the graph dataset
    @Override
    public double retrieveDailyRecords(String filepath, int dateIndex, int amountIndex, String selectedDate)
    {
        double record = 0.0;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) 
            {
                String[] columns = line.split(","); // Split line by comma

                if (dateIndex >= 0 && dateIndex < columns.length)
                {
                    //if date in the DB equals to the selected date 
                    if(columns[0].equals(selectedDate))
                    {
                        double amount = Double.parseDouble(columns[amountIndex]);

                        //calculate the sum of amount of the same date
                        record += amount;
                    }
                }
            }

            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return record;
    }
}

