package view;

import controller.ReportActivityController;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class ReportEmployeeActivity extends JFrame {
    private JTextField tfActivities;

    public ReportEmployeeActivity(String name, Date startDate, Date endDate){
        setTitle(""+name+": "+startDate+" - "+endDate);
        setSize(500, 400);
        setLocationRelativeTo(null);
        tfActivities = new JTextField();

        //setLayout(new GridLayout(0, 2));
        add(tfActivities);
        //TODO: aici am ramas
        //String s = ReportActivityController.getActivities(name,startDate,endDate);
       // setTfActivities(s);
    }
    public void setTfActivities(){

        //tfActivities.setText(activities);
    }
    public void setVisible() {
        this.setVisible(true);
    }
}
