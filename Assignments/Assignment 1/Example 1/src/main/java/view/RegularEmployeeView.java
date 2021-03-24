package view;

import javax.swing.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class RegularEmployeeView extends JFrame {
    private JButton btnClientInfo;
    private JButton btnClientAccount;
    private JButton btnTransfer;
    private JButton btnProcessUtilities;

    public RegularEmployeeView(){
        setTitle("Employee");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        btnClientAccount = new JButton("Client Account");
        btnClientInfo = new JButton("Client Information");
        btnTransfer= new JButton("Transfers");
        btnProcessUtilities = new JButton("Process Utility Bills");
        add(btnClientInfo);add(btnClientAccount);add(btnTransfer);add(btnProcessUtilities);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setClientInfoButtonListener(ActionListener clientInfoButtonListener) {
        btnClientInfo.addActionListener(clientInfoButtonListener);
    }
    public void setClientAccountButtonListener(ActionListener clientAccountButtonListener) {
        btnClientAccount.addActionListener(clientAccountButtonListener);
    }
    public void setTransferButtonListener(ActionListener transferButtonListener) {
        btnTransfer.addActionListener(transferButtonListener);
    }
    public void setUtilityBillsButtonListener(ActionListener utilityBillsButtonListener) {
        btnProcessUtilities.addActionListener(utilityBillsButtonListener);
    }
    public void setVisible() {
        this.setVisible(true);
    }
    public void setInvisible() {this.setVisible(false);}
}
