package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.jar.JarFile;

public class TransferMoneyView extends JFrame {

    private JComboBox cbFrom;
    private JComboBox cbTo;
    private JTextField tfAmount;
    private JLabel lFrom;
    private JLabel lTo;
    private JLabel lAmount;
    private JButton btnTransfer;

    public TransferMoneyView(){
        setTitle("Transfer money");
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(0,2));
        add(lFrom);add(cbFrom);
        add(lTo);add(cbTo);
        add(lAmount);add(tfAmount);
        add(btnTransfer);
    }

    private void initializeFields() {
        lFrom = new JLabel("From:");
        lTo = new JLabel("To:");
        lAmount = new JLabel("Amount: ");
        cbFrom = new JComboBox();
        cbTo = new JComboBox();
        tfAmount = new JTextField("");
        btnTransfer = new JButton("Transfer");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public String getFrom(){return Objects.requireNonNull(cbFrom.getSelectedItem()).toString();}
    public String getTo(){return Objects.requireNonNull(cbTo.getSelectedItem()).toString();}
    public double getAmount(){return Double.parseDouble(tfAmount.getText());}
    public void setTransferButtonListener(ActionListener transferButtonListener) {
        btnTransfer.addActionListener(transferButtonListener);
    }
    public void setVisible() {
        this.setVisible(true);
    }
    public void setInvisible() {this.setVisible(false);}
}
