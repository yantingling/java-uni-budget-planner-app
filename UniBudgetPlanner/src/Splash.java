/*
TMF2954 Java Programming (Project - UNI Budget Planner)
Lecturer: Dr Tan Ping Ping
Due Date: 26 June 2023
Created by Ling Yan Ting
*/

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Splash {

    // variables declaration
    private JFrame frame = new JFrame();
    private JPanel jpanelSplash = new JPanel();
    private JLabel jlabelSplash = new JLabel();
    private ImageIcon imageiconSplash = new ImageIcon(getClass().getResource("Assets/Splash.png"));
    // end of variables declaration

    public Splash()
    {
        initComponents();

        try {
            // only show the screen for 1200ms, then dispose and redirect to HomePage
            Thread.sleep(1200);
            frame.dispose();
            new HomePage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // initComponets for GUI design
    private void initComponents()
    {
        jlabelSplash.setIcon(imageiconSplash);

        GroupLayout layoutSplashPanel = new GroupLayout(jpanelSplash);
        jpanelSplash.setLayout(layoutSplashPanel);
        layoutSplashPanel.setHorizontalGroup(
            layoutSplashPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSplash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layoutSplashPanel.setVerticalGroup(
            layoutSplashPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jlabelSplash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout layoutSplashFrame = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layoutSplashFrame);
        layoutSplashFrame.setHorizontalGroup(
            layoutSplashFrame.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelSplash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layoutSplashFrame.setVerticalGroup(
            layoutSplashFrame.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jpanelSplash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null); // make the jframe in the center of the screen
        frame.setResizable(false); // restrict the user to resize the frame
        frame.setVisible(true);
        frame.setTitle("UNI Budget Planner");
    }
}
