package controller;

import launcher.ComponentFactory;
import repository.user.UserRepository;
import view.AdminView;
import view.ClientInfoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientInfoController {

    private final ClientInfoView clientInfoView;

    private final UserRepository userRepository;

    public ClientInfoController(ClientInfoView clientInfoView, UserRepository userRepository) {
        this.clientInfoView = clientInfoView;
        this.userRepository = userRepository;
        clientInfoView.setViewButtonListener(new ViewButtonListener());
        clientInfoView.setAddButtonListener(new AddButtonListener());
        clientInfoView.setUpdateButtonListener(new UpdateButtonListener());
    }
    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    }
    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    } private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    }
}
