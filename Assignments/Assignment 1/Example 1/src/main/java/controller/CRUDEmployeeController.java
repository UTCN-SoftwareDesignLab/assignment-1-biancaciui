package controller;

import database.Constants;
import launcher.ComponentFactory;
import launcher.Launcher;
import model.Role;
import model.User;
import model.builder.ActionBuilder;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.EntityNotFoundException;
import repository.action.ActionRepository;
import repository.user.UserRepository;
import services.user.AuthenticationService;
import view.CRUDEmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class CRUDEmployeeController {

    private final CRUDEmployeeView crudEmployeeView;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final ActionRepository actionRepository;

    public CRUDEmployeeController(CRUDEmployeeView crudEmployeeView, UserRepository userRepository,AuthenticationService authenticationService, ActionRepository actionRepository) {
        this.crudEmployeeView = crudEmployeeView;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.actionRepository = actionRepository;
        crudEmployeeView.setViewButtonListener(new ViewButtonListener());
        crudEmployeeView.setCreateButtonListener(new CreateButtonListener());
        crudEmployeeView.setUpdateButtonListener(new UpdateButtonListener());
        crudEmployeeView.setDeleteButtonListener(new DeleteButtonListener());
    }
    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("view_employee");
            User user = null;
            if(!crudEmployeeView.getName().equals("")){
                try {
                    user = userRepository.findByUsername(crudEmployeeView.getName());
                    crudEmployeeView.setAddress(user.getAddress());
                    crudEmployeeView.setPersonalId(user.getCNP());
                    crudEmployeeView.setDateOfEmployment(user.getDateOfEmployment());
                    crudEmployeeView.setPhoneNr(user.getPhoneNr());
                    actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.VIEW_EMPLOYEE).setUser(user).setDateTime(LocalDateTime.now()).build());
                } catch (EntityNotFoundException entityNotFoundException) {
                    //entityNotFoundException.printStackTrace();
                    JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Wrong username!");
                    crudEmployeeView.setName("");
                }
            }
            else{
                JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Wrong username!");
            }
        }
    }
    private class CreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Create_employee");
            String username = crudEmployeeView.getName();
            String password = crudEmployeeView.getPassword();
            String address = crudEmployeeView.getAddress();
            String CNP = crudEmployeeView.getPersonalId();
            String phoneNr = crudEmployeeView.getPhoneNr();

            Long id = userRepository.findIdByUsername(username);

            if(id ==-1L){
                Notification<Boolean> registerNotification = authenticationService.createEmployee(username, password, address, CNP, phoneNr);

                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if(!registerNotification.getResult()){
                        JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(),"Something went wrong!");
                    }else{
                        JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Employee successfully created!");
                        crudEmployeeView.setName("");crudEmployeeView.setPassword("");crudEmployeeView.setAddress("");crudEmployeeView.setPhoneNr("");crudEmployeeView.setPersonalId("");
                        actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.CREATE_EMPLOYEE).setUser(Launcher.user).setDateTime(LocalDateTime.now()).build());
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Username already exists in the database!");
                crudEmployeeView.setName("");
            }
        }
    }
    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("update_employee");
            if(crudEmployeeView.getName().equals(""))
                JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Please fill the Username field before performing this action!");
            else{
                Long id = -1L;
                id = userRepository.findIdByUsername(crudEmployeeView.getName());
                if(id == -1L)
                    JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Wrong username!");
                else{
                    String username = crudEmployeeView.getName();
                    String password = crudEmployeeView.getPassword();
                    String address = crudEmployeeView.getAddress();
                    String CNP = crudEmployeeView.getPersonalId();
                    String phoneNr = crudEmployeeView.getPhoneNr();
                    Notification<Boolean> registerNotification = null;
                    try {
                        registerNotification = authenticationService.updateEmployee(username, password, address, CNP, phoneNr);
                    } catch (EntityNotFoundException entityNotFoundException) {
                        entityNotFoundException.printStackTrace();
                    }
                    if (registerNotification.hasErrors()) {
                        JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), registerNotification.getFormattedErrors());
                    } else {
                        userRepository.update(id,crudEmployeeView.getAddress(),crudEmployeeView.getPassword(),crudEmployeeView.getPersonalId(),crudEmployeeView.getPhoneNr());
                        JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Employee "+ crudEmployeeView.getName() +" updated successfully!");
                        actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.UPDATE_EMPLOYEE).setUser(Launcher.user).setDateTime(LocalDateTime.now()).build());
                    }
                }
            }
        }
    }
    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("delete_employee");
            if(crudEmployeeView.getName().equals(""))
                JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Please fill the Username field before performing this action!");
            else{
                Long id = -1L;
                id = userRepository.findIdByUsername(crudEmployeeView.getName());
                if(id == -1L)
                    JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Wrong username!");
                else{
                    userRepository.delete(id);
                    JOptionPane.showMessageDialog(crudEmployeeView.getContentPane(), "Employee "+ crudEmployeeView.getName() +" deleted successfully!");
                    crudEmployeeView.setName("");
                    actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.DELETE_EMPLOYEE).setUser(Launcher.user).setDateTime(LocalDateTime.now()).build());
                }
            }
        }
    }
}
