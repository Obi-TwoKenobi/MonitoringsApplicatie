package GUI.monitoring;

import java.sql.*;
import javax.swing.table.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatabaseTableModel extends AbstractTableModel{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String[] columnNames = {"Hostnaam", "cpu load", "Totale opslag", "Gebruikte opslag", "vrije opslag", "uptime"};

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private static final int REFRESH_INTERVAL_SECONDS = 3;

    public DatabaseTableModel(Connection connection, Statement statement) {
            try {
                //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
                //statement = connection.createStatement();
                this.statement = statement;
                resultSet = statement.executeQuery("SELECT * FROM componenten");

                executor.scheduleAtFixedRate(new DataRefreshTask(this.statement, this), 0, REFRESH_INTERVAL_SECONDS, TimeUnit.SECONDS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public int getRowCount() {
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            resultSet.absolute(rowIndex + 1);
            return resultSet.getObject(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

}
