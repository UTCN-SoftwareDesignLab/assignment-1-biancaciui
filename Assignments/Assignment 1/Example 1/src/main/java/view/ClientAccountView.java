package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class ClientAccountView extends JFrame {
    private JTextField tfUsername;
    private JTextField tfCardNr;
    private JTextField tfType;
    private JTextField tfAmount;
    private JTextField tfDateCreation;
    private JLabel lUsername;
    private JLabel lCardNr;
    private JLabel lType;
    private JLabel lAmount;
    private JLabel lDateCreation;
    private JButton btnCreate;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnView;

    public ClientAccountView() {
        setTitle("Client Account");
        setSize(300, 400);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(0, 2));
        add(lUsername);
        add(tfUsername);
        add(lCardNr);
        add(tfCardNr);
        add(lType);
        add(tfType);
        add(lAmount);
        add(tfAmount);
        add(lDateCreation);
        add(tfDateCreation);
        add(btnView);
        add(btnCreate);
        add(btnUpdate);
        add(btnDelete);
    }

    private void initializeFields() {
        lUsername = new JLabel("Username:");
        lCardNr = new JLabel("Card nr:");
        lType = new JLabel("Type:");
        lAmount = new JLabel("Amount: ");
        lDateCreation = new JLabel("Date of creation:");
        tfUsername = new JTextField();
        tfType = new JTextField();
        tfCardNr = new JTextField();
        tfAmount = new JTextField();
        tfDateCreation = new JTextField();
        btnView = new JButton("View");
        btnCreate = new JButton("Create");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public String getUsername() { return tfUsername.getText(); }
    public String getCardNr(){ return tfCardNr.getText(); }
    public String getTypeAccount(){ return tfCardNr.getText(); }
    public double getAmount(){return Double.parseDouble(tfAmount.getText());}
    public Date getDateOfCreation(){return Date.valueOf(tfDateCreation.getText());}
    public void setUsername(String s) { tfUsername.setText(s); }
    public void setCardNr(String s){ tfCardNr.setText(s); }
    public void setTypeAccount(String s){tfCardNr.setText(s); }
    public void setAmount(String s){ tfAmount.setText(s);}
    public void setDateOfCreation(String s){ tfDateCreation.setText(s);}

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
    public void setInvisible() {this.setVisible(false);}
}
