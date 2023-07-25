/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

public class EditIncome extends Record implements interfaceTransactionOperations{

    // Variables declaration - do not modify   
    private JFrame frame = new JFrame();                  
    private JButton jbtnEICncl = new JButton();
    private JButton jbtnEIImage = new JButton();
    private JButton jbtnEISave = new JButton();

    private JComboBox<String> jcomboboxEICategory = new JComboBox<>();
    private JDateChooser jdatechooserEI  = new JDateChooser();

    private JLabel jlabelEIAmount = new JLabel();
    private JLabel jlabelEICategory = new JLabel();
    private JLabel jlabelEIDate = new JLabel();
    private JLabel JlabelEIHeader = new JLabel();
    private JLabel jlabelEIImage = new JLabel();
    private JLabel jlabelEINotes = new JLabel();
    private JLabel jlabelEIRequired = new JLabel();

    private JPanel jpanelEISaveCncl = new JPanel();
    private JPanel jpanelEditIncome = new JPanel();

    private JTextField jtextfieldEIAmount = new JTextField();
    private JTextField jtextfieldEINotes = new JTextField();

    //private double inputAmount;
    private String iNotes;
    private Date iDate;
    private String iAmount;
    private String iCategory;
    private int toEditLine;

    //for save image
    private String imageFilePathDB = ""; //to store image file path and list in db
    private String imageFileName = "";
    private String imageFileExtension = "";
    private int width = 50;    //width of the image
    private int height = 75;   //height of the image
    private BufferedImage image = null;

    // End of variables declaration  

    public EditIncome() {
        initComponents();
    }

    //Constructor with parameters (overloading)
    public EditIncome(String date, String category, String notes, String amount, String img, int lineNum) {
        initComponents();
        Date date2;
        Object obj = category;

        try 
        {
            //format the date and set it in the JCateChooser field
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            jdatechooserEI.setDate(date2);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }

        //set the passed values into the respective JComboBox and JTextField
        jcomboboxEICategory.setSelectedItem(obj);
        jtextfieldEIAmount.setText(amount);
        jtextfieldEINotes.setText(notes);

        imageFilePathDB = img;

        //if there is image passed here
        if(!img.equals("No image"))
        {
            //change the text on image button (from "upload image" to "view image")
            jbtnEIImage.setText("View Image");
        }

        toEditLine = lineNum;
    }
           
    //GUI Design
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jpanelEditIncome.setBackground(Color.white);

        JlabelEIHeader.setFont(new Font("Helvetica Neue", 1, 24)); // NOI18N
        JlabelEIHeader.setText("Edit Income");

        jlabelEIDate.setFont(new Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabelEIDate.setText("<html>Date<font color='red'>*</font></html>");

        jdatechooserEI.setDateFormatString("dd/MM/y");
        jdatechooserEI.setMinimumSize(new Dimension(40, 50));
        jdatechooserEI.setPreferredSize(new Dimension(40, 50));

        jlabelEICategory.setFont(new Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabelEICategory.setText("<html>Category<font color='red'>*</font></html>");

        jlabelEIRequired.setFont(new Font("Helvetica Neue", 2, 12)); // NOI18N
        jlabelEIRequired.setForeground(new Color(255, 0, 0));
        jlabelEIRequired.setText("*Required");

        jcomboboxEICategory.setModel(new DefaultComboBoxModel<>(new String[] { "Scholarship", "Loan", "Part-time jobs" }));

        jlabelEINotes.setFont(new Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabelEINotes.setText("Notes");

        jlabelEIAmount.setFont(new Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabelEIAmount.setText("<html>Amount (RM)<font color='red'>*</font></html>");

        jlabelEIImage.setFont(new Font("Helvetica Neue", 0, 14)); // NOI18N
        jlabelEIImage.setText("Image");

        jbtnEIImage.setText("Upload Image");
        jbtnEIImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEIImageActionPerformed(evt);
            }
        });

        jpanelEISaveCncl.setBackground(Color.white);
        jpanelEISaveCncl.setLayout(new GridLayout(1, 2, 30, 0));

        jbtnEICncl.setBackground(new Color(255, 102, 102));
        jbtnEICncl.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N
        jbtnEICncl.setText("Cancel");
        jbtnEICncl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEICnclActionPerformed(evt);
            }
        });
        jpanelEISaveCncl.add(jbtnEICncl);

        jbtnEISave.setBackground(new Color(204, 255, 153));
        jbtnEISave.setFont(new Font("Helvetica Neue", 1, 13)); // NOI18N
        jbtnEISave.setText("Save");
        jbtnEISave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEISaveActionPerformed(evt);
            }
        });
        jpanelEISaveCncl.add(jbtnEISave);

        GroupLayout jpanelEditIncomeLayout = new GroupLayout(jpanelEditIncome);
        jpanelEditIncome.setLayout(jpanelEditIncomeLayout);
        jpanelEditIncomeLayout.setHorizontalGroup(
            jpanelEditIncomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelEditIncomeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpanelEditIncomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelEIImage)
                    .addComponent(jlabelEIAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelEINotes)
                    .addComponent(jlabelEIDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelEICategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelEditIncomeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jpanelEditIncomeLayout.createSequentialGroup()
                            .addComponent(JlabelEIHeader, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabelEIRequired))
                        .addComponent(jdatechooserEI, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcomboboxEICategory, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextfieldEINotes, GroupLayout.Alignment.LEADING)
                        .addComponent(jtextfieldEIAmount, GroupLayout.Alignment.LEADING)
                        .addComponent(jbtnEIImage, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpanelEISaveCncl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jpanelEditIncomeLayout.setVerticalGroup(
            jpanelEditIncomeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelEditIncomeLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jpanelEditIncomeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(JlabelEIHeader)
                    .addComponent(jlabelEIRequired))
                .addGap(29, 29, 29)
                .addComponent(jlabelEIDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdatechooserEI, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEICategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxEICategory, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEINotes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldEINotes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEIAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldEIAmount, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabelEIImage)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEIImage, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jpanelEISaveCncl, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelEditIncome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelEditIncome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }                       

    private void jbtnEIImageActionPerformed(ActionEvent evt) {                                            
        if(evt.getSource()==jbtnEIImage)
        {
            uploadImage();
        }
    }                                           

    private void jbtnEICnclActionPerformed(ActionEvent evt) {                                           
        frame.dispose();
        new HomePage();
    }                                          

    private void jbtnEISaveActionPerformed(ActionEvent evt) 
    { 
        // get the input values (methods inherit from Class Record)
        iDate = getInputDate(jdatechooserEI);
        iCategory = getCategory(jcomboboxEICategory);
        iNotes = getNotes(jtextfieldEINotes);
        iAmount = getInputAmount(jtextfieldEIAmount);

        //validate the input (method inherit from Class Record) 
        String err = validateInput(iDate, iNotes, iAmount);

        //if no error on the inputs
        if(err.equals(""))
        {
            //update and save the modified record in the DB
            saveRecord(iDate, iCategory, iNotes, "incomeDB.txt", "income", "incomeImgDB");
        }
        else
        {
            //display error message
            JOptionPane.showMessageDialog(null, err);
        }                                          
    }

    //save or update the modified record into DB (CRUD)
    @Override
    public void saveRecord(Date date, String category, String notes, String filepath, String type, String dbFolder)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String sDate = sdf.format(date);
        
        /* to insert data into txt file
        References: https://youtu.be/b8_7N3P6mic */
        try 
        {
            if(notes.equals(""))
            {
                notes = "No notes";
            }
            
            if(!imageFileName.equals(""))
            {
                /* To save images into the project
                Reference: https://dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java */
                File file_path = new File("src/ImageDB/"+dbFolder+"/"+imageFileName);  //output file path
                ImageIO.write(image, imageFileExtension, file_path);
                imageFilePathDB = "src/ImageDB/"+dbFolder+"/"+imageFileName; //to save the file_path in db
            }

            /* Display double in 2 decimal places (using DecimalFormat)
            Reference: https://mkyong.com/java/java-display-double-in-2-decimal-points/ */
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.UP);

            //convertedAmount is get from the superclass Record
            String newString = (sDate + "," + category + "," + notes + "," + df.format(convertedAmount) + "," + imageFilePathDB);

            File fileToBeModified = new File(filepath);
            String newContent = "";
            BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
            
            //Reading all the lines of input text file and replace the specific line into newContent
            String line = reader.readLine();
            int lineNo = 1;

            while (line != null) 
            {
                if(toEditLine == lineNo)
                {
                    newContent += (newString + System.lineSeparator());
                }
                else
                {
                    newContent += (line + System.lineSeparator());
                }
                line = reader.readLine();
                lineNo++;
            }
        
            //Rewriting the input text file with newContent
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToBeModified));
            writer.write(newContent);

            //Closing the resources
            reader.close();
            writer.close();

            //success message
            JOptionPane.showMessageDialog(null, "The " + type + " is modified successfully!");
            frame.dispose();
            new HomePage(1); //redirect to homepage
        }
        catch (IOException e) 
        {
            //error message
            JOptionPane.showMessageDialog(null, "Failed to modify the " + type + "!");
        }
    }

    //upload image function (with additional view image feature)
    @Override
    public void uploadImage()
    {
        if(jbtnEIImage.getText().toString().equals("Upload Image"))
        {
            /* Create an upload button using JFileChooser and restrict file types to png, jpg, jpeg only
            References:
            1. https://youtu.be/YZ_tQFTMYoQ
            2. https://stackoverflow.com/questions/18575655/how-to-restrict-file-choosers-in-java-to-specific-files */
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
            JFileChooser file_upload = new JFileChooser();
            file_upload.setPreferredSize(new Dimension(310, 350));
            file_upload.setFileFilter(filter);

            //file_upload.showOpenDialog(null);
            int res = file_upload.showOpenDialog(null);

            if(res == JFileChooser.APPROVE_OPTION)
            {
                try {
                    /* To save images into the project
                    References:
                    1. https://dyclassroom.com/image-processing-project/how-to-read-and-write-image-file-in-java
                    2. https://www.geeksforgeeks.org/file-getname-method-in-java-with-examples/
                    3. https://stackoverflow.com/questions/24503361/how-to-split-a-string-after-a-dot-in-java */
                    File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());
                    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    image = ImageIO.read(file_path);

                    
                    imageFileName = file_upload.getSelectedFile().getName();
                    imageFileExtension = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);

                    JOptionPane.showMessageDialog(null, "Image uploaded successfully!");
                    jbtnEIImage.setText(imageFileName);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Upload failed");
                }
            }
        }
        else if(jbtnEIImage.getText().toString().equals("View Image"))
        {
            //Reference (to change img size): https://stackoverflow.com/questions/57737385/how-to-change-icon-size-of-uimanager-geticonoptionpane-erroricon
            //set icon on joptionpane: https://youtu.be/Gy3odNyYzhg
            //yes or no joptionpane: https://www.tutorialspoint.com/swingexamples/show_confirm_dialog_with_yesno.htm
            ImageIcon showImg = new ImageIcon(imageFilePathDB);
            JLabel icon;

            //resize to 40% of the original image size
            if(showImg.getIconWidth() <=280 && showImg.getIconHeight() <=380)
            {
                icon = new JLabel(showImg);
            }
            else
            {
                Image img = showImg.getImage();
                Image scaledImage = img.getScaledInstance(280, 380, Image.SCALE_DEFAULT);
                Icon scaledIcon = new ImageIcon(scaledImage);
                icon = new JLabel(scaledIcon);
            }

            JLabel text = new JLabel("Do you want to upload a new image?");
            ImageIcon icon2 = new ImageIcon();

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 30));
            panel.add(icon, BorderLayout.CENTER);
            panel.add(text, BorderLayout.SOUTH);
            panel.setPreferredSize(new Dimension(300, 400));

            //if the user choose to upload a new image, the text of button will change to "upload image" and they can perform the feature above
            int result = JOptionPane.showConfirmDialog(null, panel, "View Image", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon2);
            if(result == JOptionPane.YES_OPTION)
            {
                jbtnEIImage.setText("Upload Image");
            }
        }
    }
}
