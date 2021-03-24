package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CRUDEmployeeView extends JFrame {

    private JTextField cbName;
    private JTextField tfAddress;
    private JTextField tfPassword;
    private JTextField tfPersonalID;
    private JTextField tfDateEmployment;
    private JTextField tfPhoneNr;
    private JLabel lName;
    private JLabel lPassword;
    private JLabel lPersonalID;
    private JLabel lAddress;
    private JLabel lDateEmployment;
    private JLabel lPhoneNr;
    private JButton btnView;
    private JButton btnCreate;
    private JButton btnUpdate;
    private JButton btnDelete;

    public CRUDEmployeeView() {
        setTitle("CRUD on Employees");
        setSize(400, 400);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(0, 2));
        add(lName);
        add(cbName);
        add(lAddress);
        add(tfAddress);
        add(lPassword);
        add(tfPassword);
        add(lPersonalID);
        add(tfPersonalID);
        add(lDateEmployment);
        add(tfDateEmployment);
        add(lPhoneNr);
        add(tfPhoneNr);
        add(btnView);
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        lName = new JLabel("Username:");
        lPassword = new JLabel("Password:");
        lPersonalID = new JLabel("Personal ID:");
        lAddress = new JLabel("Address: ");
        lDateEmployment = new JLabel("Date of employment:");
        lPhoneNr = new JLabel("Phone nr:");
        cbName = new JTextField();
        tfPhoneNr= new JTextField();
        tfDateEmployment = new JTextField();
        tfPersonalID = new JTextField();
        tfPassword = new JTextField();
        tfAddress = new JTextField();
        btnView = new JButton("View");
        btnCreate = new JButton("Create");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
    }

    public String getName() { return cbName.getText(); }
    public String getPersonalId(){ return tfPersonalID.getText(); }
    public String getAddress(){ return tfAddress.getText(); }
    public String getPassword(){return tfPassword.getText();}
    public Date getDateOfEmployment(){return Date.valueOf(tfDateEmployment.getText());}
    public String getPhoneNr(){return tfPhoneNr.getText();}

    public void setName(String s) { cbName.setText(s); }
    public void setPersonalId(String s){ tfPersonalID.setText(s); }
    public void setAddress(String s){tfAddress.setText(s); }
    public void setPassword(String s){tfPassword.setText(s);}
    public void setPhoneNr(String s){tfPhoneNr.setText(s);}
    public void setDateOfEmployment(Date dateOfEmployment) {
        tfDateEmployment.setText(dateOfEmployment.toString());
    }
    public void setNoDateOfEmployment() {
        tfDateEmployment.setText("");
    }
    public void setViewButtonListener(ActionListener viewButtonListener) {
        btnView.addActionListener(viewButtonListener);
    }
    public void setCreateButtonListener(ActionListener createButtonListener) {
        btnCreate.addActionListener(createButtonListener);
    }
    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        btnUpdate.addActionListener(updateButtonListener);
    }
    public void setDeleteButtonListener(ActionListener deleteButtonListener) {
        btnDelete.addActionListener(deleteButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }


}
