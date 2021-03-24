package controller;

import model.Action;
import model.User;
import repository.EntityNotFoundException;
import repository.action.ActionRepository;
import repository.user.UserRepository;
import view.ReportActivityView;
import view.ReportEmployeeActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.*;

public class ReportActivityController {
    private final ReportActivityView reportActivityView;
    private final UserRepository userRepository;
    private final ActionRepository actionRepository;
    public ReportActivityController(ReportActivityView reportActivityView, UserRepository userRepository,ActionRepository actionRepository) {
        this.reportActivityView = reportActivityView;
        this.userRepository = userRepository;
        this.actionRepository = actionRepository;
        reportActivityView.setGetReportButtonListener(new getReportButtonListener());
        //set the combobox with the usernames extracted from the database
        reportActivityView.setCbNameValues(getNamesOfEmployees());
    }
    private class getReportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: validarea fieldurilor
           /* String username = reportActivityView.getName();
            Date startDate = reportActivityView.getStartDate();
            Date endDate = reportActivityView.getEndDate();*/

           // ReportEmployeeActivity reportEmployeeActivity = new ReportEmployeeActivity(username,startDate,endDate);
            //Long s = getActivities("andrei_pr@yahoo.com"/*,startDate,endDate*/);
            ///System.out.println(s);
           // reportEmployeeActivity.setVisible();
            LocalDateTime startDate = null, endDate=null;
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                startDate = LocalDateTime.parse(reportActivityView.getStartDateString(),format);
                System.out.println(startDate.toString());
                endDate = LocalDateTime.parse(reportActivityView.getEndDateString(),format);

                getActivities(reportActivityView.getName(),startDate,endDate);
                //TODO: FA SA MEARGA ASTA CA PREA MULT TIMP AM CHERT AICI

            } catch (DateTimeParseException | EntityNotFoundException ex){
                JOptionPane.showMessageDialog(reportActivityView.getContentPane(), "Date doesn't respect the \"yyy/MM/dd HH\" format!");
            }
        }
    }
    public void getActivities(String name, LocalDateTime startDate, LocalDateTime endDate) throws EntityNotFoundException {
        User user = userRepository.findByUsername(name);
        //System.out.println(id);
        List<Action> actions = actionRepository.findBetweenDates(user,startDate,endDate);
       // System.out.println(actions.size()+" sized");
        System.out.println(actions.toString());
    }
    public List<String> getNamesOfEmployees(){
        return userRepository.getNamesOfEmployees();
    }
}
