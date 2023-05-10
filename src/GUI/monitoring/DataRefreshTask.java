package GUI.monitoring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataRefreshTask implements Runnable {
    private ResultSet resultSet;
    private Statement statement;
    private DatabaseTableModel model;

    public DataRefreshTask(Statement statement, DatabaseTableModel model) {
      //  this.statement = statement;
        this.model = model;
    }
    @Override
    public void run() {
        try {
           // Statement statement = model.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM componenten");
            resultSet = statement.executeQuery("SELECT * FROM componenten");
            model.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}