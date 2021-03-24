package controller;

import launcher.ComponentFactory;
import repository.user.UserRepository;
import view.RegularEmployeeView;
import view.TransferMoneyView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoneyController {
    private final TransferMoneyView transferMoneyView;
    private final UserRepository userRepository;
    public TransferMoneyController(TransferMoneyView transferMoneyView, UserRepository userRepository) {
        this.transferMoneyView = transferMoneyView;
        this.userRepository = userRepository;
        transferMoneyView.setTransferButtonListener(new TransferButtonListener());
    }
    private class TransferButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validate
            if(transferMoneyView.getFrom().equals("") || transferMoneyView.getTo().equals(""))
                JOptionPane.showMessageDialog(transferMoneyView.getContentPane(), "Please provide the required information for the transfer to be dne!");

            System.out.println("Transfer_money");
        }
    }
}
