package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {
    private JButton btnCRUD;
    private JButton btnReport;

    public AdminView(){
        setTitle("Admin");
        setSize(250, 100);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        btnCRUD = new JButton("CRUD on Employees");
        btnReport = new JButton("Report on Activities");
        add(btnCRUD);
        add(btnReport);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setCRUDButtonListener(ActionListener CRUDButtonListener) {
        btnCRUD.addActionListener(CRUDButtonListener);
    }
    public void setReportButtonListener(ActionListener reportButtonListener) {
        btnReport.addActionListener(reportButtonListener);
    }
    public void setVisible() {
        this.setVisible(true);
    }
    public void setInvisible() {this.setVisible(false);}

}
