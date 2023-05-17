package GUI.monitoring;

import GUI.Styling;
import controllers.InfrastructureDesignController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MonitoringsGUI extends JDialog {
    private JTable table;
    private DatabaseTableModel model;
    private Timer timer;
    private InfrastructureDesignController controller;
    private final int screenWidth = 786;
    private final int screenHeight = 576;
    public MonitoringsGUI(JFrame parent) {
        super(parent, false);
        setTitle("Monitoring");
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        //controller.checkServer("loaclhost", 80);

        model = new DatabaseTableModel();
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);
        model.DatabaseTableModel();

        this.table.setBackground(Styling.COLOR_LIGHT_BLUE);
        this.table.setForeground(Color.WHITE);
        // create a timer to refresh the data every 3 seconds
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // update the table data
                model.DatabaseTableModel();
            }
        });
        timer.start();
        pack();
    }
}
