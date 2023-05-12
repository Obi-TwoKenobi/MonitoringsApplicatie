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

        MainScreen mainScreen = new MainScreen(mainScreenFrame);
        mainScreenFrame.add(mainScreen);
        mainScreenFrame.pack();

        mainScreenFrame.setLocationRelativeTo(null);
        mainScreenFrame.setVisible(true);
    }
}