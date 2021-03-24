package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportActivityView extends JFrame {

    public JComboBox cbName;
    private JTextField tfStartDate;
    private JTextField tfEndDate;
    private JLabel lName;
    private JLabel lStartDate;
    private JLabel lEndDate;
    private JButton btnGetReport;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReportActivityView(){
        setTitle("Report on Activity");
        setSize(300, 400);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(0, 2));
        add(lName);add(cbName);
        add(lStartDate);add(tfStartDate);
        add(lEndDate);add(tfEndDate);
        add(btnGetReport);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        lName = new JLabel("Name: ");
        lStartDate= new JLabel("Start date:");
        lEndDate = new JLabel("End date:");
        cbName = new JComboBox();
        tfStartDate = new JTextField();
        tfEndDate = new JTextField();
        btnGetReport = new JButton("Get Report");
    }
    public LocalDateTime getStartDate(){return LocalDateTime.parse(tfStartDate.getText(),formatter);}
    public LocalDateTime getEndDate(){return LocalDateTime.parse(tfEndDate.getText(),formatter);}
    public String getStartDateString(){return tfStartDate.getText();}
    public String getEndDateString(){return tfEndDate.getText();}
    public String getName(){return cbName.getSelectedItem().toString();}

    public void setCbNameValues(List<String> names){
        for(String n: names)
            cbName.addItem(n);
    }

    public void setGetReportButtonListener(ActionListener getReportButtonListener) {
        btnGetReport.addActionListener(getReportButtonListener);
    }
    public void setVisible() {
        this.setVisible(true);
    }
    public void setInvisible() {this.setVisible(false);}
}
