package GUI.monitoring;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

import controllers.InfrastructureDesignController;
//import org.apache.commons.net.SocketFactory;

import java.io.IOException;

public class DatabaseTableModel extends AbstractTableModel{
    private InfrastructureDesignController controller;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String ip = "localhost";
    private Object[][] data = {};
    private String[] columnNames = {"Hostnaam", "cpu load", "Totale opslag", "Gebruikte opslag", "vrije opslag", "uptime", "beschikbaar"};


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
                    String server = resultSet.getString("server");
                    if (server.equals("webserver")){
                        boolean isAvailable = checkWebserverAvailability(ip, 80);
                        row[6] = isAvailable ? "Yes" : "No";  // Set "beschikbaar" column value
                        System.out.println("test");
                    } else if (server.equals("database")) {
                        /*boolean isAvailable = checkDatabaseserverAvailability(ip, 3306);
                        row[6] = isAvailable ? "Yes" : "No";  // Set "beschikbaar" column value
                        System.out.println("test");*/
                    }
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
            System.out.println("Verbinding gemaakt met de webserver op " + ipAddress + ":" + port);
            socket.close();
            return true;
        } catch (IOException e) {
            System.out.println("Kon geen verbinding maken met de webserver op " + ipAddress + ":" + port);
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkDatabaseserverAvailability(String ipAddress, int port){
        try{
            //Thread.sleep(3000);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monitoringstest", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM componenten");
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkFirewallAvailability(String ipAddress, int port){
        return false;
    }
}
