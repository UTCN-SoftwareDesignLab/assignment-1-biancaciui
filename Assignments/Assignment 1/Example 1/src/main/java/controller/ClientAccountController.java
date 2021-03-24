package controller;

import database.Constants;
import launcher.ComponentFactory;
import launcher.Launcher;
import model.Account;
import model.Client;
import model.User;
import model.builder.ActionBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.action.ActionRepository;
import repository.client.ClientRepository;
import repository.user.UserRepository;
import view.ClientAccountView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ClientAccountController {
    private final ClientAccountView clientAccountView;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final ActionRepository actionRepository;

    public ClientAccountController(ClientAccountView clientAccountView, AccountRepository accountRepository, ClientRepository clientRepository, ActionRepository actionRepository) {
        this.clientAccountView = clientAccountView;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.actionRepository = actionRepository;
        clientAccountView.setViewButtonListener(new ViewButtonListener());
        clientAccountView.setCreateButtonListener(new CreateButtonListener());
        clientAccountView.setUpdateButtonListener(new UpdateButtonLisntener());
        clientAccountView.setDeleteButtonListener(new DeleteButtonListener());
    }
    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("view_client");
            Account account= null;
            if(!clientAccountView.getUsername().equals("")){
                try {
                    account = accountRepository.findByUsername(clientAccountView.getName());
                    clientAccountView.setCardNr(account.getId().toString());
                    clientAccountView.setAmount(String.valueOf(account.getAmount()));
                    clientAccountView.setTypeAccount(account.getCurrency());
                    clientAccountView.setDateOfCreation(account.getDateOfCreation().toString());
                    actionRepository.save(new ActionBuilder().setActionName(Constants.Rights.VIEW_CLIENT).setUser(Launcher.user).setDateTime(LocalDateTime.now()).build());
                } catch (EntityNotFoundException entityNotFoundException) {
                    JOptionPane.showMessageDialog(clientAccountView.getContentPane(), "Wrong username!");
                }
            }
            else{
                JOptionPane.showMessageDialog(clientAccountView.getContentPane(), "Please provide a username!");
            }
        }
    }
    private class CreateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    }
    private class UpdateButtonLisntener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    }
    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validation
            System.out.println("button pressed");
        }
    }
}
