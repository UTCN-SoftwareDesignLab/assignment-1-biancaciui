package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Alex on 18/03/2017.
 */
public class LoginView extends JFrame {

    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btnLogin;
    private JButton btnRegisterAdmin;
    private JButton btnRegisterEmployee;

    public LoginView() throws HeadlessException {
        setTitle("Login / Register");
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(tfUsername);
        add(tfPassword);
        add(btnLogin);
        add(btnRegisterAdmin);
        add(btnRegisterEmployee);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        tfUsername = new JTextField("andrei_pr@yahoo.com");
        tfPassword = new JTextField("Opar0l@!3");
        btnLogin = new JButton("Login");
        btnRegisterAdmin = new JButton("Register Admin");
        btnRegisterEmployee = new JButton("Register Employee");
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }
    public void setUsername(String s) {
        tfUsername.setText(s);
    }

    public void setPassword(String s) {
        tfPassword.setText(s);
    }
    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }

    public void setRegisterAdminButtonListener(ActionListener registerAdminButtonListener) {
        btnRegisterAdmin.addActionListener(registerAdminButtonListener);
    }

    public void setRegisterEmployeeButtonListener(ActionListener registerEmployeeButtonListener) {
        btnRegisterEmployee.addActionListener(registerEmployeeButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public void setInvisible() {this.setVisible(false);}
}
