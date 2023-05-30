package GUI.monitoring;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class DatabaseTableModel extends AbstractTableModel{
    private JDialog jDialog;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String ip;
    int timeoutSeconds = 3;
    boolean reachable = true;
    private Object[][] data = {};
    private String[] columnNames = {"Hostnaam", "cpu load", "Totale opslag", "Gebruikte opslag", "vrije opslag", "uptime", "beschikbaar"};


    public DatabaseTableModel(JDialog jDialog) {
        this.jDialog = jDialog;
    }

    public void tableModel(){
            try {
                Thread executionThread = new Thread(()-> {
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.3.2:3306/monitoring", "HAProxy", "ICTM2O2!#gangsters");
                         Statement statement = connection.createStatement()) {
                        statement.setQueryTimeout(timeoutSeconds);
                        resultSet = statement.executeQuery("SELECT * FROM componenten");

                        List<Object[]> rows = new ArrayList<>();
                        while (resultSet.next()) {
                            Object[] row = new Object[columnNames.length];
                            row[0] = resultSet.getString("hostnaam");
                            row[1] = resultSet.getDouble("cpu_load");
                            row[2] = (resultSet.getDouble("disk_total") / Math.pow(1024, 3));
                            row[3] = (resultSet.getDouble("disk_used") / Math.pow(1024, 3));
                            row[4] = (resultSet.getDouble("disk_free") / Math.pow(1024, 3));
                            row[5] = (resultSet.getDouble("uptime") / 3600);
                            String server = resultSet.getString("server");
                            ip = resultSet.getString("ipadres");
                            if (server.equals("webserver")){
                                boolean isAvailable = checkWebserverAvailability(ip, 80);
                                row[6] = isAvailable ? "Yes" : "No";  // Set "beschikbaar" column value
                            } else if (server.equals("database")) {
                                boolean isAvailable = checkDatabaseserverAvailability(ip, 3306);
                                row[6] = isAvailable ? "Yes" : "No";  // Set "beschikbaar" column value
                            }
                            rows.add(row);
                        }
                        data = rows.toArray(new Object[0][]);
                        reachable = true;
                        fireTableDataChanged();
                    } catch (SQLException e) {
                        reachable = false;
                        e.printStackTrace();
                    }
                });
                executionThread.start();
                executionThread.join(timeoutSeconds * 1000);
                if (executionThread.isAlive()){
                    executionThread.interrupt();
                    JOptionPane.showMessageDialog(jDialog, "Databaseserver niet bereikbaar!", "Fout", JOptionPane.ERROR_MESSAGE);
                    reachable = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                reachable = false;
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
         if (value == null) {
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
    public boolean checkWebserverAvailability(String ipAddress, int port){
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            Socket socket = new Socket(inetAddress, port);
            socket.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkDatabaseserverAvailability(String ipAddress, int port) throws SQLException {
        try{
            connection = DriverManager.getConnection("jdbc:mysql://192.168.3.2:3306/monitoring", "HAProxy", "ICTM2O2!#gangsters");
            connection.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jDialog, "Fout bij het controleren van de databaseserver!", "Fout", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            connection.close();
            return false;
        }
    }
    public boolean checkFirewallAvailability(String ipAddress, int port){
        return false;
    }
    public boolean getReachable(){
        return this.reachable;
    }
}
