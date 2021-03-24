package controller;

import database.Constants;
import launcher.ComponentFactory;
import launcher.Launcher;
import model.Role;
import model.User;
import model.validation.Notification;
import repository.user.UserRepository;
import services.user.AuthenticationService;
import view.AdminView;
import view.LoginView;
import view.RegularEmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginController {
    private final LoginView loginView;
    private AdminView adminView;
    private RegularEmployeeView employeeView;

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public LoginController(LoginView loginView, AuthenticationService authenticationService,UserRepository userRepository) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterAdminButtonListener(new RegisterAdminButtonListener());
        loginView.setRegisterEmployeeButtonListener(new RegisterEmployeeButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username, password);
            ComponentFactory componentFactory = ComponentFactory.instance(false);

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");

                Launcher.user = userRepository.findByUsernameAndPassword(username,password).getResult();
                Launcher.user.setId(userRepository.findIdByUsername(Launcher.user.getUsername()));
                List<Role> roleList = Launcher.user.getRoles();

                for(Role r: roleList){
                    if(r.getRole().equals(Constants.Roles.ADMINISTRATOR)){
                        componentFactory.getAdminView().setVisible();
                        componentFactory.getRegularEmployeeView().setVisible();
                    }
                    if(r.getRole().equals(Constants.Roles.EMPLOYEE)){
                        componentFactory.getRegularEmployeeView().setVisible();
                    }
                }
                componentFactory.getLoginView().setInvisible();
            }
        }
    }

    private class RegisterAdminButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Long id = userRepository.findIdByUsername(username);
            if(id!=-1L){
                Notification<Boolean> registerNotification = authenticationService.register(username, password, Constants.Roles.ADMINISTRATOR);

                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                    } else {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Username already exists in the database!");
                loginView.setUsername("");
            }
        }
    }
    private class RegisterEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Long id = userRepository.findIdByUsername(username);
            if(id!=-1L){
                Notification<Boolean> registerNotification = authenticationService.register(username, password,Constants.Roles.EMPLOYEE);

                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                    } else {
                        JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(loginView.getContentPane(), "Username already exists in the database!");
                loginView.setUsername("");
            }
        }
    }
}
