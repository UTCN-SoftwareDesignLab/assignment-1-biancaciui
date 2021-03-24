package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class ClientInfoView extends JFrame {
    private JTextField cbUsername;
    private JTextField tfCardNr;
    private JTextField tfPassword;
    private JTextField tfPersonalID;
    private JTextField tfAddress;
    private JLabel lUsername;
    private JLabel lCardNr;
    private JLabel lPassword;
    private JLabel lPersonalID;
    private JLabel lAddress;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnView;

    public ClientInfoView() {
        setTitle("Client Info");
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(0,2));
        add(lUsername);add(cbUsername);
        add(lPassword);add(tfPassword);
        add(lCardNr);add(tfCardNr);
        add(lPersonalID);add(tfPersonalID);
        add(lAddress);add(tfAddress);
        add(btnView);
        add(btnAdd);add(btnUpdate);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void initializeFields() {
        lUsername = new JLabel("Username:");
        lCardNr = new JLabel("Card nr:");
        lPassword = new JLabel("Password");
        lPersonalID = new JLabel("PersonalID");
        lAddress = new JLabel("Address");
        cbUsername = new JTextField();
        tfPassword = new JTextField();
        tfCardNr = new JTextField();
        tfPersonalID = new JTextField();
        tfAddress = new JTextField();
        btnView = new JButton("View");
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
    }
    public String getUsername() { return cbUsername.getText(); }
    public String getPassword() {
        return tfPassword.getText();
    }
    public String getCardNr(){ return tfCardNr.getText(); }
    public String getPersonalID(){ return tfPersonalID.getText(); }
    public String getAddress(){ return tfAddress.getText(); }

    public void setViewButtonListener(ActionListener viewButtonListener) {
        btnView.addActionListener(viewButtonListener);
    }
    public void setAddButtonListener(ActionListener addButtonListener) {
        btnAdd.addActionListener(addButtonListener);
    }
    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdate.addActionListener(updateButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }
}
