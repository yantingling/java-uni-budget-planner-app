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

public class EditExpenses extends Record implements interfaceTransactionOperations{

    // Variables declaration
    private JFrame frame = new JFrame();                   
    private JButton jbtnEECncl = new JButton();
    private JButton jbtnEEImage = new JButton();
    private JButton jbtnEESave = new JButton();

    private JComboBox<String> jcomboboxEECategory = new JComboBox<>();
    private JDateChooser jdatechooserEE  = new JDateChooser();

    private JLabel jlabelEEAmount = new JLabel();
    private JLabel jlabelEECategory = new JLabel();
    private JLabel jlabelEEDate = new JLabel();
    private JLabel jlabelEEHeader = new JLabel();
    private JLabel jlabelEEImage = new JLabel();
    private JLabel jlabelEENotes = new JLabel();
    private JLabel jlabelEERequired = new JLabel();

    private JPanel jpanelEESaveCncl = new JPanel();
    private JPanel jpanelEditExpenses = new JPanel();

    private JTextField jtextfieldEEAmount = new JTextField();
    private JTextField jtextfieldEENotes = new JTextField();

    //private double inputAmount;
    private String eNotes;
    private Date eDate;
    private String eAmount;
    private String eCategory;
    private String error;
    private int toEditLine;

    //for save image
    private String imageFilePathDB = ""; //to store image file path and list in db
    private String imageFileName = "";
    private String imageFileExtension = "";
    private int width = 50;    //width of the image
    private int height = 75;   //height of the image
    private BufferedImage image = null;

    //End of variables declaration  

    public EditExpenses() {
        initComponents();
    }

    //Constructor with parameters (overloading)
    public EditExpenses(String date, String category, String notes, String amount, String img, int lineNum) {
        initComponents();
        Date date2;
        Object obj = category;

        try 
        {
            //format the date and set it in the JCateChooser field
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            jdatechooserEE.setDate(date2);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }

        //set the passed values into the respective JComboBox and JTextField
        jcomboboxEECategory.setSelectedItem(obj);
        jtextfieldEEAmount.setText(amount);
        jtextfieldEENotes.setText(notes);

        imageFilePathDB = img;

        //if there is image passed here
        if(!img.equals("No image"))
        {
            //change the text on image button (from "upload image" to "view image")
            jbtnEEImage.setText("View Image");
        }

        toEditLine = lineNum;
    }

    //GUI design                      
    private void initComponents() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jpanelEditExpenses.setBackground(Color.WHITE);

        jlabelEEHeader.setFont(new Font("Helvetica Neue", 1, 24)); 
        jlabelEEHeader.setText("Edit Expenses");

        jlabelEEDate.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelEEDate.setText("<html>Date<font color='red'>*</font></html>");

        jdatechooserEE.setDateFormatString("dd/MM/y");
        jdatechooserEE.setMinimumSize(new Dimension(40, 50));
        jdatechooserEE.setPreferredSize(new Dimension(40, 50));

        jlabelEECategory.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelEECategory.setText("<html>Category<font color='red'>*</font></html>");

        jlabelEERequired.setFont(new Font("Helvetica Neue", 2, 12)); 
        jlabelEERequired.setForeground(new Color(255, 0, 0));
        jlabelEERequired.setText("*Required");

        jcomboboxEECategory.setModel(new DefaultComboBoxModel<>(new String[] {  "Food", "Rental", "Transport", "Groceries", "Fees", "College", "Healthcare" }));

        jlabelEENotes.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelEENotes.setText("Notes");

        jlabelEEAmount.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelEEAmount.setText("<html>Amount (RM)<font color='red'>*</font></html>");

        jlabelEEImage.setFont(new Font("Helvetica Neue", 0, 14)); 
        jlabelEEImage.setText("Image");

        jbtnEEImage.setText("Upload Image");
        jbtnEEImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEEImageActionPerformed(evt);
            }
        });

        jpanelEESaveCncl.setBackground(Color.white);
        jpanelEESaveCncl.setLayout(new GridLayout(1, 2, 30, 0));

        jbtnEECncl.setBackground(new Color(255, 102, 102));
        jbtnEECncl.setFont(new Font("Helvetica Neue", 1, 13)); 
        jbtnEECncl.setText("Cancel");
        jbtnEECncl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEECnclActionPerformed(evt);
            }
        });
        jpanelEESaveCncl.add(jbtnEECncl);

        jbtnEESave.setBackground(new Color(204, 255, 153));
        jbtnEESave.setFont(new Font("Helvetica Neue", 1, 13)); 
        jbtnEESave.setText("Save");
        jbtnEESave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnEESaveActionPerformed(evt);
            }
        });
        jpanelEESaveCncl.add(jbtnEESave);

        GroupLayout jpanelEditExpensesLayout = new GroupLayout(jpanelEditExpenses);
        jpanelEditExpenses.setLayout(jpanelEditExpensesLayout);
        jpanelEditExpensesLayout.setHorizontalGroup(
            jpanelEditExpensesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelEditExpensesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpanelEditExpensesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelEEImage)
                    .addComponent(jlabelEEAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelEENotes)
                    .addComponent(jlabelEEDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelEECategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelEditExpensesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jpanelEditExpensesLayout.createSequentialGroup()
                            .addComponent(jlabelEEHeader, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlabelEERequired))
                        .addComponent(jdatechooserEE, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcomboboxEECategory, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextfieldEENotes, GroupLayout.Alignment.LEADING)
                        .addComponent(jtextfieldEEAmount, GroupLayout.Alignment.LEADING)
                        .addComponent(jbtnEEImage, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpanelEESaveCncl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jpanelEditExpensesLayout.setVerticalGroup(
            jpanelEditExpensesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelEditExpensesLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jpanelEditExpensesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelEEHeader)
                    .addComponent(jlabelEERequired))
                .addGap(29, 29, 29)
                .addComponent(jlabelEEDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdatechooserEE, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEECategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxEECategory, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEENotes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldEENotes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelEEAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldEEAmount, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabelEEImage)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnEEImage, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jpanelEESaveCncl, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelEditExpenses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelEditExpenses, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }                     

    private void jbtnEEImageActionPerformed(ActionEvent evt) {                                            
        if(evt.getSource()==jbtnEEImage)
        {
            uploadImage();
        }
    }                                           

    private void jbtnEECnclActionPerformed(ActionEvent evt) {                                           
        frame.dispose();
        new HomePage();
    }                                          

    private void jbtnEESaveActionPerformed(ActionEvent evt) 
    { 
        // get the input values (methods inherit from Class Record)  
        eDate = getInputDate(jdatechooserEE);
        eCategory = getCategory(jcomboboxEECategory);
        eNotes = getNotes(jtextfieldEENotes);
        eAmount = getInputAmount(jtextfieldEEAmount);

        //validate the input (method inherit from Class Record) 
        error = validateInput(eDate, eNotes, eAmount);

        //if no error on the inputs
        if(error.equals(""))
        {
            //update and save the modified record in the DB
            saveRecord(eDate, eCategory, eNotes, "expensesDB.txt", "expenses", "expensesImgDB");
        }
        else
        {
            //display error message
            JOptionPane.showMessageDialog(null, error);
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
            new HomePage(0); //redirect to homepage
        }
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Failed to modify the " + type + "!");
        }
    }

    //upload image function (with additional view image feature)
    @Override
    public void uploadImage()
    {
        if(jbtnEEImage.getText().toString().equals("Upload Image"))
        {
            /* Create an upload button using JFileChooser and restrict file types to png, jpg, jpeg only
            References:
            1. https://youtu.be/YZ_tQFTMYoQ
            2. https://stackoverflow.com/questions/18575655/how-to-restrict-file-choosers-in-java-to-specific-files */
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
            JFileChooser file_upload = new JFileChooser();
            file_upload.setPreferredSize(new Dimension(310, 350));
            file_upload.setFileFilter(filter);

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
                    jbtnEEImage.setText(imageFileName);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Upload failed");
                }
            }
        }
        else if(jbtnEEImage.getText().toString().equals("View Image")) //if there's an image passed to this page
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
                jbtnEEImage.setText("Upload Image");
            }
        }
    }
}
