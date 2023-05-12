package GUI.monitoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MonitoringsGUI extends JFrame {
    private JTable table;
    private DatabaseTableModel model;
    private Timer timer;
    private final int screenWidth = 786;
    private final int screenHeight = 576;
    private final int[] NerdyGadgetsDonkerBlauw = {35, 35 ,47};
    private final int[] NerdyGadgetsLichterBlauw = {35, 35 ,80};

    public MonitoringsGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Monitoring");
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        model = new DatabaseTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

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
        setVisible(true);
    }
}
