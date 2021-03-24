package controller;

import launcher.ComponentFactory;
import repository.user.UserRepository;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {

    private final AdminView adminView;

    private final UserRepository userRepository;

    public AdminController(AdminView adminView, UserRepository userRepository) {
        this.adminView = adminView;
        this.userRepository = userRepository;
        adminView.setCRUDButtonListener(new CRUDButtonListener());
        adminView.setReportButtonListener(new reportButtonListener());
    }
    private class CRUDButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getCrudEmployeeView().setVisible();
        }
    }
    private class reportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            componentFactory.getReportActivityView().setVisible();
        }
    }
}
