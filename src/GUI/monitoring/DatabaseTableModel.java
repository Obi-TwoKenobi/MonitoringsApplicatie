package GUI.monitoring;
import java.sql.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTableModel extends AbstractTableModel{
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Object[][] data = {};
    private String[] columnNames = {"Hostnaam", "cpu load", "Totale opslag", "Gebruikte opslag", "vrije opslag", "uptime"};
    
    public void DatabaseTableModel() {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM componenten");

                List<Object[]> rows = new ArrayList<>();
                while (resultSet.next()) {
                    Object[] row = new Object[columnNames.length];
                    row[0] = resultSet.getString("hostnaam");
                    row[1] = resultSet.getInt("cpu_load");
                    row[2] = resultSet.getInt("disk_total");
                    row[3] = resultSet.getInt("disk_used");
                    row[4] = resultSet.getInt("disk_free");
                    row[5] = resultSet.getInt("uptime");
                    rows.add(row);
                }
                data = rows.toArray(new Object[0][]);
                fireTableDataChanged();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0) {
            return getValueAt(0, columnIndex).getClass();
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = data[rowIndex][columnIndex];
        if (value == null){
            return "";
        } else {
            return value;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
