import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
public class DatabaseTableModel extends AbstractTableModel{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String[] columnNames = {"Hostnaam", "cpu load", "Totale opslag", "Gebruikte opslag", "vrije opslag", "uptime"};

    public DatabaseTableModel() {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM componenten");
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
        return 6; // assuming 3 columns in the table
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
