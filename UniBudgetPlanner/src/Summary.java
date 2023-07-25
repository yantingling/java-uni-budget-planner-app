import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.HashMap;

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
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

public class Summary implements interfaceSummary
{
    // Variables declaration
    private JFrame frame = new JFrame();                    
    private JButton jbtnSEAdd = new JButton();
    private JButton jbtnSEGraph = new JButton();
    private JButton jbtnSEHome = new JButton();
    private JButton jbtnSESummary  = new JButton();
    private JButton jbtnSIAdd = new JButton();
    private JButton jbtnSIGraph = new JButton();
    private JButton jbtnSIHome = new JButton();
    private JButton jbtnSISummary = new JButton();
    private JButton jbtnSECheck = new JButton();
    private JButton jbtnSICheck = new JButton();

    private JComboBox<String> jcomboboxSE = new JComboBox<>();
    private JComboBox<String> jcomboboxSI = new JComboBox<>();

    private JDateChooser jdatechooserSE = new JDateChooser();
    private JDateChooser jdatechooserSI = new JDateChooser();
    private JMonthChooser jmonthchooserSE = new JMonthChooser();
    private JMonthChooser jmonthchooserSI = new JMonthChooser();

    private JLabel jlabelCollegeAmount = new JLabel();
    private JLabel jlabelCollegeIcon = new JLabel();
    private JLabel jlabelCollegeText = new JLabel();
    private JLabel jlabelFeesAmount = new JLabel();
    private JLabel jlabelFeesIcon = new JLabel();
    private JLabel jlabelFeesText = new JLabel();
    private JLabel jlabelFoodAmount = new JLabel();
    private JLabel jlabelFoodIcon = new JLabel();
    private JLabel jlabelFoodText = new JLabel();
    private JLabel jlabelGroceriesAmount = new JLabel();
    private JLabel jlabelGroceriesIcon = new JLabel();
    private JLabel jlabelGroceriesText = new JLabel();
    private JLabel jlabelHealthcareAmount = new JLabel();
    private JLabel jlabelHealthcareIcon = new JLabel();
    private JLabel jlabelHealthcareText = new JLabel();
    private JLabel jlabelLoanAmount = new JLabel();
    private JLabel jlabelLoanIcon = new JLabel();
    private JLabel jlabelLoanText = new JLabel();
    private JLabel jlabelPTJobsAmount = new JLabel();
    private JLabel jlabelPTJobsIcon = new JLabel();
    private JLabel jlabelPTJobsText = new JLabel();
    private JLabel jlabelRentalAmount = new JLabel();
    private JLabel jlabelRentalIcon = new JLabel();
    private JLabel jlabelRentalText = new JLabel();
    private JLabel jlabelSE = new JLabel();
    private JLabel jlabelSI = new JLabel();
    private JLabel jlabelScholarshipAmount = new JLabel();
    private JLabel jlabelScholarshipIcon = new JLabel();
    private JLabel jlabelScholarshipText = new JLabel();
    private JLabel jlabelTransportAmount = new JLabel();
    private JLabel jlabelTransportIcon = new JLabel();
    private JLabel jlabelTransportText = new JLabel();
    private JLabel jlabelSETotalAmount = new JLabel();
    private JLabel jlabelSITotalAmount = new JLabel();

    private JPanel jpanelCollege = new JPanel();
    private JPanel jpanelFees = new JPanel();
    private JPanel jpanelFood = new JPanel();
    private JPanel jpanelGroceries = new JPanel();
    private JPanel jpanelHealthcare = new JPanel();
    private JPanel jpanelLoan = new JPanel();
    private JPanel jpanelPTJobs = new JPanel();
    private JPanel jpanelRental = new JPanel();
    private JPanel jpanelSE = new JPanel();
    private JPanel jpanelSEBottomBtns = new JPanel();
    private JPanel jpanelSERecord = new JPanel();
    private JPanel jpanelSI = new JPanel();
    private JPanel jpanelSIBottomBtns = new JPanel();
    private JPanel jpanelSIRecord = new JPanel();
    private JPanel jpanelScholarship = new JPanel();
    private JPanel jpanelTransport = new JPanel();
    private JPanel jpanelSETotal = new JPanel();
    private JPanel jpanelSITotal = new JPanel();

    private JProgressBar jprogressbarCollege = new JProgressBar();
    private JProgressBar jprogressbarFees = new JProgressBar();
    private JProgressBar jprogressbarFood = new JProgressBar();
    private JProgressBar jprogressbarGroceries = new JProgressBar();
    private JProgressBar jprogressbarHealthcare = new JProgressBar();
    private JProgressBar jprogressbarLoan = new JProgressBar();
    private JProgressBar jprogressbarPTJobs = new JProgressBar();
    private JProgressBar jprogressbarRental = new JProgressBar();
    private JProgressBar jprogressbarScholarship = new JProgressBar();
    private JProgressBar jprogressbarTransport = new JProgressBar();

    private JTabbedPane jtabbedpaneSummary = new JTabbedPane();

    private int currentYear = Year.now().getValue();
    // End of variables declaration   

    public Summary() {
        initComponents();

        //show overall summary on default
        HashMap<String, Double> hm = calculateSummary(1, 3);
        displaySummary(hm);
    }
         
    //GUI Design
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtabbedpaneSummary.setBackground(Color.WHITE);
        jtabbedpaneSummary.setPreferredSize(new Dimension(360, 640));
        jtabbedpaneSummary.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                jtabbedpaneSummaryStateChanged(evt);
            }
        });

        jpanelSE.setBackground(Color.white);

        jlabelSE.setFont(new Font("Helvetica Neue", 1, 24)); 
        jlabelSE.setText("Expenses Summary");

        jcomboboxSE.setModel(new DefaultComboBoxModel<>(new String[] { "Overall", "Daily", "Monthly" }));
        jcomboboxSE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jcomboboxSEActionPerformed(evt);
            }
        });

        jpanelSERecord.setBackground(Color.white);
        jpanelSERecord.setPreferredSize(new Dimension(320, 418));
        jpanelSERecord.setLayout(new GridLayout(3, 3, 5, 5));

        jpanelFood.setBackground(new Color(232, 238, 241));
        jpanelFood.setBorder(BorderFactory.createEtchedBorder());

        jlabelFoodIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFoodIcon.setIcon(new ImageIcon(getClass().getResource("Assets/food.png"))); 

        jlabelFoodText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelFoodText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFoodText.setText("Food");

        jlabelFoodAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelFoodAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFoodAmount.setText("RM0.00");

        jprogressbarFood.setBackground(Color.white);
        jprogressbarFood.setForeground(Color.red);

        GroupLayout jpanelFoodLayout = new GroupLayout(jpanelFood);
        jpanelFood.setLayout(jpanelFoodLayout);
        jpanelFoodLayout.setHorizontalGroup(
            jpanelFoodLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelFoodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelFoodLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarFood, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelFoodText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelFoodAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelFoodIcon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelFoodLayout.setVerticalGroup(
            jpanelFoodLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelFoodLayout.createSequentialGroup()
                .addComponent(jlabelFoodIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelFoodText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelFoodAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarFood, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpanelSERecord.add(jpanelFood);

        jpanelRental.setBackground(new Color(232, 238, 241));
        jpanelRental.setBorder(BorderFactory.createEtchedBorder());

        jlabelRentalIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelRentalIcon.setIcon(new ImageIcon(getClass().getResource("Assets/rental.png"))); 

        jlabelRentalText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelRentalText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelRentalText.setText("Rental");

        jlabelRentalAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelRentalAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelRentalAmount.setText("RM0.00");

        jprogressbarRental.setBackground(Color.white);
        jprogressbarRental.setForeground(new Color(255, 124, 29));

        GroupLayout jpanelRentalLayout = new GroupLayout(jpanelRental);
        jpanelRental.setLayout(jpanelRentalLayout);
        jpanelRentalLayout.setHorizontalGroup(
            jpanelRentalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelRentalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelRentalLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarRental, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelRentalText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelRentalIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelRentalAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelRentalLayout.setVerticalGroup(
            jpanelRentalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelRentalLayout.createSequentialGroup()
                .addComponent(jlabelRentalIcon, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelRentalText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelRentalAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarRental, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSERecord.add(jpanelRental);

        jpanelTransport.setBackground(new Color(232, 238, 241));
        jpanelTransport.setBorder(BorderFactory.createEtchedBorder());

        jprogressbarTransport.setBackground(Color.white);
        jprogressbarTransport.setForeground(Color.orange);

        jlabelTransportAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelTransportAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelTransportAmount.setText("RM0.00");

        jlabelTransportText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelTransportText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelTransportText.setText("Transport");

        jlabelTransportIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelTransportIcon.setIcon(new ImageIcon(getClass().getResource("Assets/car.png"))); 

        GroupLayout jpanelTransportLayout = new GroupLayout(jpanelTransport);
        jpanelTransport.setLayout(jpanelTransportLayout);
        jpanelTransportLayout.setHorizontalGroup(
            jpanelTransportLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelTransportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelTransportLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarTransport, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelTransportText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelTransportIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelTransportAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelTransportLayout.setVerticalGroup(
            jpanelTransportLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelTransportLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jlabelTransportIcon, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelTransportText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelTransportAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarTransport, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSERecord.add(jpanelTransport);

        jpanelGroceries.setBackground(new Color(232, 238, 241));
        jpanelGroceries.setBorder(BorderFactory.createEtchedBorder());

        jprogressbarGroceries.setBackground(Color.white);

        jlabelGroceriesAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelGroceriesAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelGroceriesAmount.setText("RM0.00");

        jlabelGroceriesText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelGroceriesText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelGroceriesText.setText("Groceries");

        jlabelGroceriesIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelGroceriesIcon.setIcon(new ImageIcon(getClass().getResource("Assets/groceries.png"))); 

        GroupLayout jpanelGroceriesLayout = new GroupLayout(jpanelGroceries);
        jpanelGroceries.setLayout(jpanelGroceriesLayout);
        jpanelGroceriesLayout.setHorizontalGroup(
            jpanelGroceriesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelGroceriesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelGroceriesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarGroceries, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelGroceriesText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelGroceriesIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelGroceriesAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelGroceriesLayout.setVerticalGroup(
            jpanelGroceriesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelGroceriesLayout.createSequentialGroup()
                .addComponent(jlabelGroceriesIcon, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelGroceriesText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlabelGroceriesAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarGroceries, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jpanelSERecord.add(jpanelGroceries);

        jpanelFees.setBackground(new Color(232, 238, 241));
        jpanelFees.setBorder(BorderFactory.createEtchedBorder());

        jprogressbarFees.setBackground(Color.white);
        jprogressbarFees.setForeground(new Color(7, 156, 52));

        jlabelFeesAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelFeesAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFeesAmount.setText("RM0.00");

        jlabelFeesText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelFeesText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFeesText.setText("Fees");

        jlabelFeesIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelFeesIcon.setIcon(new ImageIcon(getClass().getResource("Assets/fees.png"))); 

        GroupLayout jpanelFeesLayout = new GroupLayout(jpanelFees);
        jpanelFees.setLayout(jpanelFeesLayout);
        jpanelFeesLayout.setHorizontalGroup(
            jpanelFeesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelFeesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelFeesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarFees, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelFeesText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelFeesIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelFeesAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelFeesLayout.setVerticalGroup(
            jpanelFeesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelFeesLayout.createSequentialGroup()
                .addComponent(jlabelFeesIcon, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelFeesText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelFeesAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarFees, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSERecord.add(jpanelFees);

        jpanelCollege.setBackground(new Color(232, 238, 241));
        jpanelCollege.setBorder(BorderFactory.createEtchedBorder());

        jprogressbarCollege.setBackground(Color.white);
        jprogressbarCollege.setForeground(new Color(148, 0, 211));

        jlabelCollegeAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelCollegeAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelCollegeAmount.setText("RM0.00");

        jlabelCollegeText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelCollegeText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelCollegeText.setText("College");

        jlabelCollegeIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelCollegeIcon.setIcon(new ImageIcon(getClass().getResource("Assets/college.png"))); 

        GroupLayout jpanelCollegeLayout = new GroupLayout(jpanelCollege);
        jpanelCollege.setLayout(jpanelCollegeLayout);
        jpanelCollegeLayout.setHorizontalGroup(
            jpanelCollegeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelCollegeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelCollegeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarCollege, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelCollegeText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelCollegeIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelCollegeAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelCollegeLayout.setVerticalGroup(
            jpanelCollegeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCollegeLayout.createSequentialGroup()
                .addComponent(jlabelCollegeIcon, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelCollegeText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelCollegeAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarCollege, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSERecord.add(jpanelCollege);

        jpanelHealthcare.setBackground(new Color(232, 238, 241));
        jpanelHealthcare.setBorder(BorderFactory.createEtchedBorder());

        jprogressbarHealthcare.setBackground(Color.white);
        jprogressbarHealthcare.setForeground(new Color(102, 0, 0));

        jlabelHealthcareAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelHealthcareAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelHealthcareAmount.setText("RM0.00");

        jlabelHealthcareText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelHealthcareText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelHealthcareText.setText("Healthcare");

        jlabelHealthcareIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelHealthcareIcon.setIcon(new ImageIcon(getClass().getResource("Assets/healthcare.png"))); 

        GroupLayout jpanelHealthcareLayout = new GroupLayout(jpanelHealthcare);
        jpanelHealthcare.setLayout(jpanelHealthcareLayout);
        jpanelHealthcareLayout.setHorizontalGroup(
            jpanelHealthcareLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jpanelHealthcareLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelHealthcareLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jprogressbarHealthcare, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlabelHealthcareText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelHealthcareIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelHealthcareAmount, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpanelHealthcareLayout.setVerticalGroup(
            jpanelHealthcareLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelHealthcareLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelHealthcareIcon, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelHealthcareText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlabelHealthcareAmount)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarHealthcare, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSERecord.add(jpanelHealthcare);

        jpanelSEBottomBtns.setBackground(new Color(255, 255, 255));
        jpanelSEBottomBtns.setLayout(new GridLayout(1, 4, 15, 0));

        jbtnSEHome.setIcon(new ImageIcon(getClass().getResource("Assets/home.png"))); 
        jbtnSEHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSEHomeActionPerformed(evt);
            }
        });
        jpanelSEBottomBtns.add(jbtnSEHome);

        jbtnSEAdd.setIcon(new ImageIcon(getClass().getResource("Assets/add.png"))); 
        jbtnSEAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSEAddActionPerformed(evt);
            }
        });
        jpanelSEBottomBtns.add(jbtnSEAdd);

        jbtnSESummary.setIcon(new ImageIcon(getClass().getResource("Assets/coins.png"))); 
        jpanelSEBottomBtns.add(jbtnSESummary);

        jbtnSEGraph.setIcon(new ImageIcon(getClass().getResource("Assets/chart-line-up.png"))); 
        jbtnSEGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSEGraphActionPerformed(evt);
            }
        });
        jpanelSEBottomBtns.add(jbtnSEGraph);

        jbtnSECheck.setIcon(new ImageIcon(getClass().getResource("Assets/check.png")));
        jbtnSECheck.setVisible(false);
        jbtnSECheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSECheckActionPerformed(evt);
            }
        });

        jdatechooserSE.setVisible(false);
        jdatechooserSE.setDateFormatString("dd/MM/y");
        jmonthchooserSE.setBorder(BorderFactory.createEtchedBorder());
        jmonthchooserSE.setVisible(false);

        jpanelSETotal.setBackground(Color.WHITE);
        jpanelSETotal.setBorder(BorderFactory.createEtchedBorder());
        jpanelSETotal.setPreferredSize(new Dimension(175, 33));

        jlabelSETotalAmount.setFont(new Font("Helvetica Neue", 1, 15)); 
        jlabelSETotalAmount.setForeground(new Color(53, 94, 59));
        jlabelSETotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelSETotalAmount.setText("Total Expenses: RM0.00");

        GroupLayout jpanelSETotalLayout = new GroupLayout(jpanelSETotal);
        jpanelSETotal.setLayout(jpanelSETotalLayout);
        jpanelSETotalLayout.setHorizontalGroup(
            jpanelSETotalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSETotalAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanelSETotalLayout.setVerticalGroup(
            jpanelSETotalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSETotalAmount, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        GroupLayout jpanelSELayout = new GroupLayout(jpanelSE);
        jpanelSE.setLayout(jpanelSELayout);
        jpanelSELayout.setHorizontalGroup(
            jpanelSELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSELayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpanelSELayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpanelSERecord, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jpanelSELayout.createSequentialGroup()
                        .addComponent(jcomboboxSE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jmonthchooserSE, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jdatechooserSE, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSECheck, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpanelSEBottomBtns, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelSE, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpanelSETotal, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpanelSELayout.setVerticalGroup(
            jpanelSELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSELayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jlabelSE, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelSELayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnSECheck, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jmonthchooserSE, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jdatechooserSE, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jcomboboxSE, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelSETotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelSERecord, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jpanelSEBottomBtns, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jtabbedpaneSummary.addTab("Expenses", jpanelSE);

        jpanelSI.setBackground(Color.white);

        jlabelSI.setFont(new Font("Helvetica Neue", 1, 24)); 
        jlabelSI.setText("Income Summary");

        jcomboboxSI.setModel(new DefaultComboBoxModel<>(new String[] { "Overall", "Daily", "Monthly" }));
        jcomboboxSI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jcomboboxSIActionPerformed(evt);
            }
        });

        jpanelSIRecord.setBackground(Color.white);
        jpanelSIRecord.setLayout(new GridLayout(3, 1, 0, 5));

        jpanelScholarship.setBackground(new Color(232, 238, 241));
        jpanelScholarship.setBorder(BorderFactory.createEtchedBorder());

        jlabelScholarshipIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelScholarshipIcon.setIcon(new ImageIcon(getClass().getResource("Assets/scholarship.png"))); 

        jprogressbarScholarship.setBackground(Color.white);
        jprogressbarScholarship.setForeground(new Color(14, 134, 212));

        jlabelScholarshipText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelScholarshipText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelScholarshipText.setText("Scholarship");

        jlabelScholarshipAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelScholarshipAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelScholarshipAmount.setText("RM0.00");

        GroupLayout jpanelScholarshipLayout = new GroupLayout(jpanelScholarship);
        jpanelScholarship.setLayout(jpanelScholarshipLayout);
        jpanelScholarshipLayout.setHorizontalGroup(
            jpanelScholarshipLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelScholarshipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelScholarshipLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jprogressbarScholarship, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(jpanelScholarshipLayout.createSequentialGroup()
                        .addComponent(jlabelScholarshipIcon, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelScholarshipLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelScholarshipText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabelScholarshipAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jpanelScholarshipLayout.setVerticalGroup(
            jpanelScholarshipLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelScholarshipLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelScholarshipLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelScholarshipIcon, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelScholarshipLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jlabelScholarshipText)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelScholarshipAmount)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jprogressbarScholarship, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSIRecord.add(jpanelScholarship);

        jpanelLoan.setBackground(new Color(232, 238, 241));
        jpanelLoan.setBorder(BorderFactory.createEtchedBorder());

        jlabelLoanAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelLoanAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelLoanAmount.setText("RM0.00");

        jlabelLoanText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelLoanText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelLoanText.setText("Loan");

        jlabelLoanIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelLoanIcon.setIcon(new ImageIcon(getClass().getResource("Assets/loan.png"))); 

        jprogressbarLoan.setBackground(Color.white);
        jprogressbarLoan.setForeground(new Color(71, 140, 92));
        jprogressbarLoan.setToolTipText("");

        GroupLayout jpanelLoanLayout = new GroupLayout(jpanelLoan);
        jpanelLoan.setLayout(jpanelLoanLayout);
        jpanelLoanLayout.setHorizontalGroup(
            jpanelLoanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLoanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLoanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLoanLayout.createSequentialGroup()
                        .addComponent(jlabelLoanIcon, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLoanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelLoanText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabelLoanAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, jpanelLoanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jprogressbarLoan, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpanelLoanLayout.setVerticalGroup(
            jpanelLoanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLoanLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jpanelLoanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelLoanIcon, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelLoanLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jlabelLoanText)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelLoanAmount)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jprogressbarLoan, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jpanelSIRecord.add(jpanelLoan);

        jpanelPTJobs.setBackground(new Color(232, 238, 241));
        jpanelPTJobs.setBorder(BorderFactory.createEtchedBorder());

        jlabelPTJobsAmount.setFont(new Font("Helvetica Neue", 1, 13)); 
        jlabelPTJobsAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelPTJobsAmount.setText("RM0.00");

        jlabelPTJobsText.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelPTJobsText.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelPTJobsText.setText("Part-time Jobs");

        jlabelPTJobsIcon.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelPTJobsIcon.setIcon(new ImageIcon(getClass().getResource("Assets/partTimeJobs.png"))); 

        jprogressbarPTJobs.setBackground(Color.white);
        jprogressbarPTJobs.setForeground(new Color(254, 204, 0));

        GroupLayout jpanelPTJobsLayout = new GroupLayout(jpanelPTJobs);
        jpanelPTJobs.setLayout(jpanelPTJobsLayout);
        jpanelPTJobsLayout.setHorizontalGroup(
            jpanelPTJobsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelPTJobsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelPTJobsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jprogressbarPTJobs, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(jpanelPTJobsLayout.createSequentialGroup()
                        .addComponent(jlabelPTJobsIcon, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelPTJobsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jlabelPTJobsText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabelPTJobsAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jpanelPTJobsLayout.setVerticalGroup(
            jpanelPTJobsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelPTJobsLayout.createSequentialGroup()
                .addGroup(jpanelPTJobsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelPTJobsIcon, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelPTJobsLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jlabelPTJobsText)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlabelPTJobsAmount)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jprogressbarPTJobs, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpanelSIRecord.add(jpanelPTJobs);

        jmonthchooserSI.setBorder(BorderFactory.createEtchedBorder());
        jmonthchooserSI.setVisible(false);
        jdatechooserSI.setVisible(false);
        jdatechooserSI.setDateFormatString("dd/MM/y");

        jpanelSIBottomBtns.setBackground(new Color(255, 255, 255));
        jpanelSIBottomBtns.setLayout(new GridLayout(1, 4, 15, 0));

        jbtnSIHome.setIcon(new ImageIcon(getClass().getResource("Assets/home.png"))); 
        jbtnSIHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSIHomeActionPerformed(evt);
            }
        });
        jpanelSIBottomBtns.add(jbtnSIHome);

        jbtnSIAdd.setIcon(new ImageIcon(getClass().getResource("Assets/add.png"))); 
        jbtnSIAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSIAddActionPerformed(evt);
            }
        });
        jpanelSIBottomBtns.add(jbtnSIAdd);

        jbtnSISummary.setIcon(new ImageIcon(getClass().getResource("Assets/coins.png"))); 
        jpanelSIBottomBtns.add(jbtnSISummary);

        jbtnSIGraph.setIcon(new ImageIcon(getClass().getResource("Assets/chart-line-up.png"))); 
        jbtnSIGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSIGraphActionPerformed(evt);
            }
        });
        jpanelSIBottomBtns.add(jbtnSIGraph);

        jbtnSICheck.setIcon(new ImageIcon(getClass().getResource("Assets/check.png")));
        jbtnSICheck.setVisible(false);
        jbtnSICheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnSICheckActionPerformed(evt);
            }
        });

        jpanelSITotal.setBackground(Color.WHITE);
        jpanelSITotal.setBorder(BorderFactory.createEtchedBorder());
        jpanelSITotal.setPreferredSize(new Dimension(320, 33));

        jlabelSITotalAmount.setFont(new Font("Helvetica Neue", 1, 15)); // NOI18N
        jlabelSITotalAmount.setForeground(new Color(53, 94, 59));
        jlabelSITotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
        jlabelSITotalAmount.setText("Total Income: RM0.00");


        GroupLayout jpanelSITotalLayout = new GroupLayout(jpanelSITotal);
        jpanelSITotal.setLayout(jpanelSITotalLayout);
        jpanelSITotalLayout.setHorizontalGroup(
            jpanelSITotalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSITotalAmount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpanelSITotalLayout.setVerticalGroup(
            jpanelSITotalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSITotalAmount, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        GroupLayout jpanelSILayout = new GroupLayout(jpanelSI);
        jpanelSI.setLayout(jpanelSILayout);
        jpanelSILayout.setHorizontalGroup(
            jpanelSILayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSILayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpanelSILayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelSI, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelSILayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jpanelSIBottomBtns, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpanelSILayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.LEADING, jpanelSILayout.createSequentialGroup()
                                .addComponent(jcomboboxSI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jdatechooserSI, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jmonthchooserSI, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnSICheck, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, jpanelSILayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jpanelSITotal, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jpanelSIRecord, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpanelSILayout.setVerticalGroup(
            jpanelSILayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelSILayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jlabelSI, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelSILayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdatechooserSI, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jmonthchooserSI, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jcomboboxSI,  GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jbtnSICheck, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelSITotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanelSIRecord, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jpanelSIBottomBtns, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jtabbedpaneSummary.addTab("Income", jpanelSI);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jtabbedpaneSummary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jtabbedpaneSummary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }// </editor-fold>                        

    private void jcomboboxSEActionPerformed(ActionEvent evt) 
    {          
        //if user choose to view overall summary
        if(jcomboboxSE.getSelectedItem().toString().equals("Overall"))
        {
            jdatechooserSE.setVisible(false);
            jmonthchooserSE.setVisible(false);
            jbtnSECheck.setVisible(false);
            resetSummary(); 
            HashMap<String, Double> hm = calculateSummary(1, 3);                                          
            displaySummary(hm);
        }
        else if (jcomboboxSE.getSelectedItem().toString().equals("Daily")) //daily summary
        {
            jdatechooserSE.setVisible(true);
            jmonthchooserSE.setVisible(false);
            jbtnSECheck.setVisible(true);
            resetSummary();
        }
        else if (jcomboboxSE.getSelectedItem().toString().equals("Monthly")) //monthly summary
        {
            jdatechooserSE.setVisible(false);
            jmonthchooserSE.setVisible(true);
            jbtnSECheck.setVisible(true);
            resetSummary(); 
        }
    }    

    private void jbtnSECheckActionPerformed(ActionEvent evt) {                                            
        if(jcomboboxSE.getSelectedItem().toString().equals("Daily"))
        {
            if(jdatechooserSE.getDate()!=null)
            {
                resetSummary(); 
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(jdatechooserSE.getDate());
                HashMap<String, Double> dailySummary = calculateSummary(0, 1, 3, date);
                displaySummary(dailySummary);
            }
        }
        else if (jcomboboxSE.getSelectedItem().toString().equals("Monthly"))
        {
            resetSummary();
            int month = jmonthchooserSE.getMonth() + 1;
            HashMap<String, Double> monthlySummary = calculateSummary(0, 1, 3, month);
            displaySummary(monthlySummary);
        }
    }

    private void jcomboboxSIActionPerformed(ActionEvent evt) {                                                      
        if(jcomboboxSI.getSelectedItem().toString().equals("Overall"))
        {
            jdatechooserSI.setVisible(false);
            jmonthchooserSI.setVisible(false);
            jbtnSICheck.setVisible(false);
            resetSummary(); 
            HashMap<String, Double> hm = calculateSummary(1, 3);                                           
            displaySummary(hm);
 
        }
        else if (jcomboboxSI.getSelectedItem().toString().equals("Daily"))
        {
            jdatechooserSI.setVisible(true);
            jmonthchooserSI.setVisible(false);
            jbtnSICheck.setVisible(true);
            resetSummary(); 
        }
        else if (jcomboboxSI.getSelectedItem().toString().equals("Monthly"))
        {
            jdatechooserSI.setVisible(false);
            jmonthchooserSI.setVisible(true);
            jbtnSICheck.setVisible(true);
            resetSummary(); 
        }
    }   
    
    private void jbtnSICheckActionPerformed(ActionEvent evt) {                                            
        if(jcomboboxSI.getSelectedItem().toString().equals("Daily"))
        {
            if(jdatechooserSI.getDate()!=null)
            {
                resetSummary(); 
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(jdatechooserSI.getDate());
                HashMap<String, Double> dailySummary = calculateSummary(0, 1, 3, date);
                displaySummary(dailySummary);
            }
        }
        else if (jcomboboxSI.getSelectedItem().toString().equals("Monthly"))
        {
            resetSummary();
            int month = jmonthchooserSI.getMonth() + 1;
            HashMap<String, Double> monthlySummary = calculateSummary(0, 1, 3, month);
            displaySummary(monthlySummary);
        }
    }

    //redirect to homepage
    private void jbtnSIHomeActionPerformed(ActionEvent evt) {                                           
        frame.dispose();
        new HomePage();
    }                                          

    private void jbtnSEHomeActionPerformed(ActionEvent evt) {                                           
        frame.dispose();
        new HomePage();
    }  

    //redirect to AddTransaction page
    private void jbtnSIAddActionPerformed(ActionEvent evt) {                                          
        frame.dispose();
        new AddTransaction();
    }                                                               

    private void jbtnSEAddActionPerformed(ActionEvent evt) {                                          
        frame.dispose();
        new AddTransaction();
    }   

    //redirect to Graph page
    private void jbtnSIGraphActionPerformed(ActionEvent evt) {                                            
        frame.dispose();
        new Graph();       
    }                                           

    private void jbtnSEGraphActionPerformed(ActionEvent evt) {                                            
        frame.dispose();
        new Graph();       
    }                                            

    private void jtabbedpaneSummaryStateChanged(ChangeEvent evt) { 
        if(jtabbedpaneSummary.getSelectedIndex() == 0)
        {
            if(jcomboboxSE.getSelectedItem().toString().equals("Overall"))  
            {
                resetSummary(); 
                HashMap<String, Double> hm = calculateSummary(1, 3);                                            
                displaySummary(hm);
            }
        }
        else
        {
            if(jcomboboxSI.getSelectedItem().toString().equals("Overall"))  
            {
                resetSummary(); 
                HashMap<String, Double> hm = calculateSummary(1, 3);                                            
                displaySummary(hm);
            }
        }
    } 

    //calculate [OVERALL] summary according to categories
    @Override
    public HashMap<String, Double> calculateSummary(int categoryIndex, int amountIndex)
    {
        String filepath;

        //index 0 is Expenses, index 1 is Income
        if(jtabbedpaneSummary.getSelectedIndex() == 0)
        {
            filepath = "expensesDB.txt";
        }
        else
        {
            filepath = "incomeDB.txt";
        }

        HashMap<String, Double> summary = new HashMap<String, Double>();

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            //read every line in the file
            while ((line = reader.readLine()) != null) 
            {
                String[] columns = line.split(","); // Split line by comma

                if (categoryIndex >= 0 && categoryIndex < columns.length) 
                {
                    double amount = Double.parseDouble(columns[amountIndex]);

                    //if hashmap summary already has the particular category as key
                    if(summary.containsKey(columns[categoryIndex]))
                    {
                        //retrieve the old amount and add it with the new one, and put it pack
                        double oriAmount = summary.get(columns[categoryIndex]);
                        amount += oriAmount;
                        summary.put(columns[categoryIndex], amount);
                    }
                    else
                    {
                        summary.put(columns[categoryIndex], amount);
                    }
                }
            }

            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return summary;
    }

    //calculate [DAILY] summary according to categories
    @Override
    public HashMap<String, Double> calculateSummary(int dateIndex, int categoryIndex, int amountIndex, String date)
    {
        String filepath;

        if(jtabbedpaneSummary.getSelectedIndex() == 0)
        {
            filepath = "expensesDB.txt";
        }
        else
        {
            filepath = "incomeDB.txt";
        }

        HashMap<String, Double> summary = new HashMap<String, Double>();

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
                    if(columns[dateIndex].equals(date)) //if the date in file is equal to the selected date
                    {
                        double amount = Double.parseDouble(columns[amountIndex]);

                        if(summary.containsKey(columns[categoryIndex]))
                        {
                            double oriAmount = summary.get(columns[categoryIndex]);
                            amount += oriAmount;
                            summary.put(columns[categoryIndex], amount);
                        }
                        else
                        {
                            summary.put(columns[categoryIndex], amount);
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

        return summary;
    }

    //calculate [MONTHLY] summary according to categories
    //we assume that monthly summary can only be calculated for the current year (2023)
    @Override
    public HashMap<String, Double> calculateSummary(int dateIndex, int categoryIndex, int amountIndex, int month)
    {
        String filepath;

        if(jtabbedpaneSummary.getSelectedIndex() == 0)
        {
            filepath = "expensesDB.txt";
        }
        else
        {
            filepath = "incomeDB.txt";
        }

        HashMap<String, Double> summary = new HashMap<String, Double>();

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

                    //if the month in the file is equal to the selected month and is current year
                    if(selectedMonth == month && selectedYear == currentYear)
                    {
                        double amount = Double.parseDouble(columns[amountIndex]);
                        if(summary.containsKey(columns[categoryIndex]))
                        {
                            double oriAmount = summary.get(columns[categoryIndex]);
                            amount += oriAmount;
                            summary.put(columns[categoryIndex], amount);
                        }
                        else
                        {
                            summary.put(columns[categoryIndex], amount);
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

        return summary;
    }

    //display the total amount according to the categories chosen
    @Override
    public void displaySummary(HashMap<String, Double> summary)
    {
        double max = 0.00;
        double sum = 0.00;
        String strSum;

        if(summary.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "No transaction made");
        }

        for(Double j : summary.values())
        {
            //calculate the sum of amounts of all categories
            sum += j;

            //determine the maximum total amount among all categories
            if(j>=max)
            {
                max = j;
            }
        }

        //format the sum in format 0.00
        strSum = String.format("%.2f", sum);

        if(jtabbedpaneSummary.getSelectedIndex() == 0) //If at [Expenses] tab
        {    
            jlabelSETotalAmount.setText("Total Expenses: RM" +strSum);

            //categories available are food, rental, transport, groceries, fees, college, healthcare
            for(String i : summary.keySet())
            {
                //calculate the progress bar value
                double value = summary.get(i);
                double bar = value/max*100;
                int iBar = (int) bar;

                //format the total amount in format of 0.00
                String sValue = String.format("%.2f", value);

                //switch case(category)
                switch(i)
                {
                    case "Food":
                        jlabelFoodAmount.setText("RM" + sValue);
                        jprogressbarFood.setValue(iBar);
                        break;
                    
                    case "Rental":
                        jlabelRentalAmount.setText("RM" + sValue);
                        jprogressbarRental.setValue(iBar);
                        break;

                    case "Transport":
                        jlabelTransportAmount.setText("RM" + sValue);
                        jprogressbarTransport.setValue(iBar);
                        break;
                    
                    case "Groceries":
                        jlabelGroceriesAmount.setText("RM" + sValue);
                        jprogressbarGroceries.setValue(iBar);
                        break;

                    case "Fees":
                        jlabelFeesAmount.setText("RM" + sValue);
                        jprogressbarFees.setValue(iBar);
                        break;
                    
                    case "College":
                        jlabelCollegeAmount.setText("RM" + sValue);
                        jprogressbarCollege.setValue(iBar);
                        break;

                    case "Healthcare":
                        jlabelHealthcareAmount.setText("RM" + sValue);
                        jprogressbarHealthcare.setValue(iBar);
                        break;
                }
            }
        }
        else //else, at [Income] tab
        {
            jlabelSITotalAmount.setText("Total Income: RM" + strSum);

            //categories available are food, rental, transport, groceries, fees, college, healthcare
            for(String i : summary.keySet())
            {
                //calculate the progress bar value
                double value = summary.get(i);
                double bar = value/max*100;
                int iBar = (int) bar;

                //format the total amount in format of 0.00
                String sValue = String.format("%.2f", value);

                //switch case(category)
                switch(i)
                {
                    case "Scholarship":
                        jlabelScholarshipAmount.setText("RM" + sValue);
                        jprogressbarScholarship.setValue(iBar);
                        break;
                    
                    case "Loan":
                        jlabelLoanAmount.setText("RM" + sValue);
                        jprogressbarLoan.setValue(iBar);
                        break;

                    case "Part-time jobs":
                        jlabelPTJobsAmount.setText("RM" + sValue);
                        jprogressbarPTJobs.setValue(iBar);
                        break;
                }
            }
        }
    }

    //reset the displayed summary to zero
    @Override
    public void resetSummary()
    {
        jlabelFoodAmount.setText("RM0.00");
        jlabelRentalAmount.setText("RM0.00");
        jlabelTransportAmount.setText("RM0.00");
        jlabelGroceriesAmount.setText("RM0.00");
        jlabelFeesAmount.setText("RM0.00");
        jlabelCollegeAmount.setText("RM0.00");
        jlabelHealthcareAmount.setText("RM0.00");
        jlabelScholarshipAmount.setText("RM0.00");
        jlabelLoanAmount.setText("RM0.00");
        jlabelPTJobsAmount.setText("RM0.00");
        jlabelSETotalAmount.setText("Total Expenses: RM0.00");
        jlabelSITotalAmount.setText("Total Income: RM0.00");

        jprogressbarFood.setValue(0);
        jprogressbarRental.setValue(0);
        jprogressbarTransport.setValue(0);
        jprogressbarGroceries.setValue(0);
        jprogressbarFees.setValue(0);
        jprogressbarCollege.setValue(0);
        jprogressbarHealthcare.setValue(0);
        jprogressbarScholarship.setValue(0);
        jprogressbarLoan.setValue(0);
        jprogressbarPTJobs.setValue(0);
    }
}
