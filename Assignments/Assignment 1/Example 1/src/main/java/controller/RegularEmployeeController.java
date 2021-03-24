package controller;

import launcher.ComponentFactory;
import repository.user.UserRepository;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegularEmployeeController {
    private final RegularEmployeeView regularEmployeeView;
    private final UserRepository userRepository;

    public RegularEmployeeController(RegularEmployeeView employeeView, UserRepository userRepository) {
        this.regularEmployeeView = employeeView;
        this.userRepository = userRepository;
        regularEmployeeView.setClientAccountButtonListener(new ClientAccountButtonListener());
        regularEmployeeView.setClientInfoButtonListener(new clientInfoButtonListener());
        regularEmployeeView.setTransferButtonListener(new transferButtonListener());
        regularEmployeeView.setUtilityBillsButtonListener(new utilityBillsButtonListener());

    }
    private class ClientAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getClientAccountView().setVisible();
        }
    }
    private class clientInfoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getClientInfoView().setVisible();
        }
    }
    private class transferButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getTransferMoneyView().setVisible();
        }
    }
    private class utilityBillsButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getUtilityBillsProcessView().setVisible();
        }
    }
}
