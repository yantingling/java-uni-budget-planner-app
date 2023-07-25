import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import java.math.RoundingMode;

public class AddTransaction extends Record implements interfaceTransactionOperations
{
    // Variables declaration
    // Ae = add expenses
    // Ai = add income      
    private JFrame frame = new JFrame();
    private JLabel jlabelAeHeader = new JLabel();
    private JLabel jlabelAeRequired = new JLabel();  
    private JLabel jlabelAeDate = new JLabel();
    private JLabel jlabelAeCategory = new JLabel();
    private JLabel jlabelAeNotes = new JLabel();
    private JLabel jlabelAeAmount = new JLabel();
    private JLabel jlabelAeImage = new JLabel();

    private JDateChooser jdatechooserAe = new JDateChooser();
    private JComboBox<String> jcomboboxAeCategory = new JComboBox<>();
    private JTextField jtextfieldAeNotes = new JTextField();
    private JTextField jtextfieldAeAmount = new JTextField();
    private JButton jbtnAeImage = new JButton();
    private JButton jbtnAeCncl = new JButton();
    private JButton jbtnAeSave = new JButton();
   
    private JLabel jlabelAiHeader = new JLabel(); 
    private JLabel jlabelAiRequired = new JLabel();
    private JLabel jlabelAiDate = new JLabel();
    private JLabel jlabelAiCategory = new JLabel();
    private JLabel jlabelAiNotes = new JLabel();
    private JLabel jlabelAiAmount = new JLabel();
    private JLabel jlabelAiImage = new JLabel();

    private JDateChooser jdatechooserAi = new JDateChooser();
    private JComboBox<String> jcomboboxAiCategory = new JComboBox<>();
    private JTextField jtextfieldAiNotes = new JTextField();
    private JTextField jtextfieldAiAmount = new JTextField();
    private JButton jbtnAiImage = new JButton();
    private JButton jbtnAiCncl = new JButton();
    private JButton jbtnAiSave = new JButton();
    
    private JPanel jpanelAe = new JPanel();
    private JPanel jpanelAi = new JPanel();
    private JPanel jpanelAeSaveCncl = new JPanel();
    private JPanel jpanelAiSaveCncl = new JPanel();
    private JTabbedPane jtabbedpaneAddTransaction = new JTabbedPane();

    private String category; //to store entered/selected category
    private String notes; //to store entered notes
    private String sAmount; //to store entered amount 
    private Date date; //to store entered date

    private String imageFilePathDB = ""; //to store image file path and list in db
    private String imageFileName = "";
    private String imageFileExtension = "";
    private int width = 50; // width of the image
    private int height = 75; //height of the image
    private BufferedImage image = null;
    // End of variables declaration  

    public AddTransaction()
    {
        initComponents();
    }

    //constructor with extra parameter of int type
    //will be called user click on the add icon on homepage (and depends on the type of transaction currently selected)
    //0 = expenses, 1 = income
    public AddTransaction(int type)
    {
        initComponents();

        if(type == 0)
        {
            jtabbedpaneAddTransaction.setSelectedIndex(0);
        }
        else if(type == 1)
        {
            jtabbedpaneAddTransaction.setSelectedIndex(1);
        }
    }

    //GUI Design
    private void initComponents()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jtabbedpaneAddTransaction.setBackground(Color.WHITE);

        jpanelAe.setBackground(Color.WHITE);

        jlabelAeHeader.setFont(new Font("Helvetica Neue", 1, 24));
        jlabelAeHeader.setText("Add Expenses");

        /* Set different color in a line of text
        Reference: https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java */
        jlabelAeDate.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAeDate.setText("<html>Date<font color='red'>*</font></html>");

        jdatechooserAe.setDateFormatString("dd/MM/y");
        jdatechooserAe.setMinimumSize(new Dimension(40, 50));
        jdatechooserAe.setPreferredSize(new Dimension(40, 50));

        jcomboboxAeCategory.setModel(new DefaultComboBoxModel<>(new String[] { "Food", "Rental", "Transport", "Groceries", "Fees", "College", "Healthcare" }));

        jlabelAeCategory.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAeCategory.setText("<html>Category<font color='red'>*</font></html>");

        jlabelAeNotes.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAeNotes.setText("Notes");

        jlabelAeAmount.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAeAmount.setText("<html>Amount (RM)<font color='red'>*</font></html>");

        jlabelAeImage.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAeImage.setText("Image");

        jbtnAeImage.setText("Upload Image");
        jbtnAeImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAeImageActionPerformed(evt);
            }
        });

        jpanelAeSaveCncl.setBackground(Color.white);
        jpanelAeSaveCncl.setLayout(new GridLayout(1, 2, 30, 0));

        jbtnAeCncl.setBackground(new Color(255, 102, 102));
        jbtnAeCncl.setFont(new Font("Helvetica Neue", 1, 13));
        jbtnAeCncl.setText("Cancel");
        jbtnAeCncl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAeCnclActionPerformed(evt);
            }
        });
        jpanelAeSaveCncl.add(jbtnAeCncl);

        jbtnAeSave.setBackground(new Color(204, 255, 153));
        jbtnAeSave.setFont(new Font("Helvetica Neue", 1, 13));
        jbtnAeSave.setText("Save");
        jbtnAeSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAeSaveActionPerformed(evt);
            }
        });
        jpanelAeSaveCncl.add(jbtnAeSave);

        jlabelAeRequired.setFont(new Font("Helvetica Neue", 2, 12));
        jlabelAeRequired.setForeground(Color.RED);
        jlabelAeRequired.setText("*Required");

        GroupLayout jpanelAeLayout = new GroupLayout(jpanelAe);
        jpanelAe.setLayout(jpanelAeLayout);
        jpanelAeLayout.setHorizontalGroup(
            jpanelAeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpanelAeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlabelAeImage)
                    .addComponent(jlabelAeAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelAeNotes)
                    .addComponent(jlabelAeDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelAeLayout.createSequentialGroup()
                        .addComponent(jlabelAeHeader)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(jlabelAeRequired))
                    .addComponent(jdatechooserAe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabelAeCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcomboboxAeCategory, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtextfieldAeNotes)
                    .addComponent(jtextfieldAeAmount)
                    .addComponent(jbtnAeImage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanelAeSaveCncl, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jpanelAeLayout.setVerticalGroup(
            jpanelAeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAeLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jpanelAeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelAeHeader)
                    .addComponent(jlabelAeRequired))
                .addGap(29, 29, 29)
                .addComponent(jlabelAeDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdatechooserAe, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAeCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxAeCategory, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAeNotes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldAeNotes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAeAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldAeAmount, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabelAeImage)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAeImage, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jpanelAeSaveCncl, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jtabbedpaneAddTransaction.addTab("Expenses", jpanelAe);

        jpanelAi.setBackground(Color.WHITE);

        jlabelAiHeader.setFont(new Font("Helvetica Neue", 1, 24));
        jlabelAiHeader.setText("Add Income");

        jlabelAiDate.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAiDate.setText("<html>Date<font color='red'>*</font></html>");

        jdatechooserAi.setDateFormatString("dd/MM/y");
        jdatechooserAi.setMinimumSize(new Dimension(40, 50));
        jdatechooserAi.setPreferredSize(new Dimension(40, 50));

        jlabelAiCategory.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAiCategory.setText("<html>Category<font color='red'>*</font></html>");

        jcomboboxAiCategory.setModel(new DefaultComboBoxModel<>(new String[] { "Scholarship", "Loan", "Part-time jobs" }));

        jlabelAiNotes.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAiNotes.setText("Notes");

        jlabelAiAmount.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAiAmount.setText("<html>Amount (RM)<font color='red'>*</font></html>");

        jlabelAiImage.setFont(new Font("Helvetica Neue", 0, 14));
        jlabelAiImage.setText("Image");

        jbtnAiImage.setText("Upload Image");
        jbtnAiImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAiImageActionPerformed(evt);
            }
        });

        jpanelAiSaveCncl.setBackground(Color.white);
        jpanelAiSaveCncl.setLayout(new GridLayout(1, 2, 30, 0));

        jbtnAiCncl.setBackground(new Color(255, 102, 102));
        jbtnAiCncl.setFont(new Font("Helvetica Neue", 1, 13));
        jbtnAiCncl.setText("Cancel");
        jbtnAiCncl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAiCnclActionPerformed(evt);
            }
        });
        jpanelAiSaveCncl.add(jbtnAiCncl);

        jbtnAiSave.setBackground(new Color(204, 255, 153));
        jbtnAiSave.setFont(new Font("Helvetica Neue", 1, 13));
        jbtnAiSave.setText("Save");
        jbtnAiSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jbtnAiSaveActionPerformed(evt);
            }
        });
        jpanelAiSaveCncl.add(jbtnAiSave);

        jlabelAiRequired.setFont(new Font("Helvetica Neue", 2, 12));
        jlabelAiRequired.setForeground(Color.RED);
        jlabelAiRequired.setText("*Required");

        GroupLayout jpanelAiLayout = new GroupLayout(jpanelAi);
        jpanelAi.setLayout(jpanelAiLayout);
        jpanelAiLayout.setHorizontalGroup(
            jpanelAiLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpanelAiLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelAiImage)
                    .addComponent(jlabelAiAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelAiNotes)
                    .addComponent(jlabelAiDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelAiCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelAiLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addGroup(GroupLayout.Alignment.LEADING, jpanelAiLayout.createSequentialGroup()
                            .addComponent(jlabelAiHeader)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                            .addComponent(jlabelAiRequired))
                        .addComponent(jdatechooserAi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcomboboxAiCategory, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextfieldAiNotes, GroupLayout.Alignment.LEADING)
                        .addComponent(jtextfieldAiAmount, GroupLayout.Alignment.LEADING)
                        .addComponent(jbtnAiImage, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpanelAiSaveCncl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jpanelAiLayout.setVerticalGroup(
            jpanelAiLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jpanelAiLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jpanelAiLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabelAiHeader)
                    .addComponent(jlabelAiRequired))
                .addGap(29, 29, 29)
                .addComponent(jlabelAiDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdatechooserAi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAiCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxAiCategory, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAiNotes)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldAiNotes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlabelAiAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldAiAmount, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlabelAiImage)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAiImage, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jpanelAiSaveCncl, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jtabbedpaneAddTransaction.addTab("Income", jpanelAi);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jtabbedpaneAddTransaction)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jtabbedpaneAddTransaction)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }

    private void jbtnAeImageActionPerformed(ActionEvent evt) 
    {                                                                        
        if(evt.getSource()==jbtnAeImage)
        {
            uploadImage();
            jbtnAeImage.setText(imageFileName);
        }
    }                                          

    private void jbtnAeCnclActionPerformed(ActionEvent evt) 
    {                                          
        frame.dispose();
        new HomePage();
    }                                         

    //add and save new Expenses record (CRUD)
    private void jbtnAeSaveActionPerformed(ActionEvent evt) 
    {
        // get the input values (methods inherit from Class Record)         
        date = getInputDate(jdatechooserAe);
        category = getCategory(jcomboboxAeCategory);
        notes = getNotes(jtextfieldAeNotes);
        sAmount = getInputAmount(jtextfieldAeAmount);

        //validate the input (method inherit from Class Record)   
        String err = validateInput(date, notes, sAmount);  
        
        //if no error on the inputs
        if(err.equals(""))
        {
            //save the record and add it in the DB
            saveRecord(date, category, notes, "expensesDB.txt", "expenses", "expensesImgDB");
        }
        else
        {
            //display error message
            JOptionPane.showMessageDialog(null, err);
        }
    }                                         

    private void jbtnAiImageActionPerformed(ActionEvent evt) 
    {                                        
        if(evt.getSource()==jbtnAiImage)
        {
            uploadImage();
            jbtnAiImage.setText(imageFileName);
        }
    }                                          

    private void jbtnAiCnclActionPerformed(ActionEvent evt) {                                          
        frame.dispose();
        new HomePage();
    }                                         

    //add and save new Income record (CRUD)
    private void jbtnAiSaveActionPerformed(ActionEvent evt) 
    {      
        // get the input values (methods inherit from Class Record)   
        date = getInputDate(jdatechooserAi);  
        category = getCategory(jcomboboxAiCategory);
        notes = getNotes(jtextfieldAiNotes);
        sAmount = getInputAmount(jtextfieldAiAmount);

        //validate the input (method inherit from Class Record)   
        String err = validateInput(date, notes, sAmount);

        //if no error on the inputs
        if(err.equals(""))
        {
            //save the record and add it in the DB
            saveRecord(date, category, notes, "incomeDB.txt", "income", "incomeImgDB");
        }
        else
        {
            //display error message
            JOptionPane.showMessageDialog(null, err);
        }
    }   

    //Save new record to DB (CRUD)
    @Override
    public void saveRecord(Date date, String category, String notes, String filename, String type, String dbFolder)
    {
        /* to insert data into txt file
        References: https://youtu.be/b8_7N3P6mic */
        try {
            
            /* to format the date in dd/MM/yyyy format (e.g. 02/02/2002)
            References used:
            1. https://stackoverflow.com/questions/69342332/how-to-get-date-from-jdatechooser-and-format-it
            2. https://www.digitalocean.com/community/tutorials/java-simpledateformat-java-date-format */
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = sdf.format(date);

            // empty extra notes/remark entered
            if(notes.equals(""))
            {
                notes = "No notes";
            }

            if(imageFileName.equals("")) // no image is uploaded
            {
                imageFilePathDB = "No image";
            }
            else
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
            //append new record
            FileWriter writer = new FileWriter(filename, true);
            BufferedWriter myWriter = new BufferedWriter(writer);
            myWriter.write(sDate + "," + category + "," + notes + "," + df.format(convertedAmount) + "," + imageFilePathDB + System.lineSeparator());
            myWriter.close();

            //sucess message
            JOptionPane.showMessageDialog(null, "New " + type + " is added successfully!");

            //dispose the page and back to homepage
            frame.dispose();
            new HomePage();
        }
        catch (Exception e) 
        {
            //display error message
            JOptionPane.showMessageDialog(null, "Failed to add new " + type + "!");
        }
    }

    //method to upload image
    @Override
    public void uploadImage()
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

        //an image is selected/open/uploaded
        if(res == JFileChooser.APPROVE_OPTION)
        {
            try 
            {
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

                //success message
                JOptionPane.showMessageDialog(null, "Image uploaded successfully!");
            } 
            catch (IOException e) 
            {
                //error message
                JOptionPane.showMessageDialog(null, "Upload failed");
            }
        }
    }
}
