package GUI.monitoring;

import GUI.Styling;
import controllers.InfrastructureDesignController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringsGUI extends JDialog {
    private JTable table;
    private DatabaseTableModel model;
    private Timer timer;
    private final int screenWidth = 786;
    private final int screenHeight = 576;
    public MonitoringsGUI(JFrame parent) {
        super(parent, false);
        setTitle("Monitoring");
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        model = new DatabaseTableModel(this);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        model.tableModel();

        this.table.setBackground(Styling.COLOR_LIGHT_BLUE);
        this.table.setForeground(Color.WHITE);
        // create a timer to refresh the data every 3 seconds
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // update the table data
                model.tableModel();
            }
        });
        timer.start();
        pack();
    }

    @Override
    public void dispose() {
        timer.stop();
        super.dispose();
    }
}
