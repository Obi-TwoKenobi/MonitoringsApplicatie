package GUI.monitoring;

import data.DB;

import java.sql.*;

public class DataRefreshTask implements Runnable {
    private DatabaseTableModel model;

    public DataRefreshTask(DatabaseTableModel model) {
      //  this.statement = statement;
        this.model = model;
    }
    @Override
    public void run() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM componenten")) {

            model.fireTableDataChanged();
            System.out.println("run");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}