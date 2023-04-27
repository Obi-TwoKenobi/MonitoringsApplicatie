import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class DatabaseTableGUI extends JFrame {
    private JTable table;
    private DatabaseTableModel model;
    private final int screenWidth = 786;
    private final int screenHeight = 576;
    private final int[] NerdyGadgetsDonkerBlauw = {35, 35 ,47};
    private final int[] NerdyGadgetsLichterBlauw = {35, 35 ,80};

    public DatabaseTableGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Monitoring");
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        model = new DatabaseTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        setVisible(true);
    }
}
