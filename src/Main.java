import GUI.monitoring.DataRefreshTask;
import GUI.monitoring.DatabaseTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        JFrame mainScreenFrame = new JFrame();
        mainScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreenFrame.setResizable(false);
        mainScreenFrame.setTitle("Hoofdscherm");

        //try {
          //  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
            //Statement statement = connection.createStatement();

            //DatabaseTableModel tableModel = new DatabaseTableModel(connection, statement);

        MainScreen mainScreen = new MainScreen(mainScreenFrame);
        mainScreenFrame.add(mainScreen);
        mainScreenFrame.pack();

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
                Statement statement = connection.createStatement();

                DatabaseTableModel tableModel = new DatabaseTableModel(connection, statement);


                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            int refreshInterval = 3;
            executor.scheduleAtFixedRate(new DataRefreshTask(statement, tableModel), 0, refreshInterval, TimeUnit.SECONDS);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        mainScreenFrame.setLocationRelativeTo(null);
        mainScreenFrame.setVisible(true);

    }
}