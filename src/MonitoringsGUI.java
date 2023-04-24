import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class MonitoringsGUI extends JDialog implements ActionListener {
    private final int screenWidth = 786;
    private final int screenHeight = 576;
    private final int[] NerdyGadgetsDonkerBlauw = {35, 35 ,47};
    private final int[] NerdyGadgetsLichterBlauw = {35, 35 ,80};
    public static Connection databaseConnection;

    private static final String dbUrl = "jdbc:mariadb://localhost:3306/nerdygadgets";
    private static final String user = "root";
    private static final String password = "";
    public MonitoringsGUI(JFrame frame, boolean modal){
        super(frame, modal);
        setTitle("Monitoring");
        setLayout(null);
        setSize(new Dimension(screenWidth,screenHeight));
        setBackground(new Color(NerdyGadgetsDonkerBlauw[0], NerdyGadgetsDonkerBlauw[1], NerdyGadgetsDonkerBlauw[2]));
        this.getContentPane().setBackground(new Color(NerdyGadgetsDonkerBlauw[0], NerdyGadgetsDonkerBlauw[1], NerdyGadgetsDonkerBlauw[2]));

        String[] columnNames = {"Icon", "Hostnaam", "Beschikbaarheid", "Uptime", "Processorbelasting", "Beschikbare opslag"};
        Object[][] data = {
                {"Paul", "mcmillis", "Project manager", "5", "fale", "oke"},
                {"Paul", "mcmillis", "Project manager", "5", "fale", "ple"}};
        JTable table = new JTable(data, columnNames);
        table.getTableHeader().setBounds(50,220,700,20);
        table.setBounds(50,240,700,200);
        table.setBackground(new Color(NerdyGadgetsLichterBlauw[0],NerdyGadgetsLichterBlauw[1],NerdyGadgetsLichterBlauw[2]));
        table.getTableHeader().setBackground(new Color(NerdyGadgetsLichterBlauw[0],NerdyGadgetsLichterBlauw[1],NerdyGadgetsLichterBlauw[2]));
        table.setForeground(Color.white);
        table.getTableHeader().setForeground(Color.white);
        this.add(table.getTableHeader());
        this.add(table);

        // Het dialoog zichtbaar maken.
        setVisible(true);

    }
    public void components(){
        try {
            String queryComponenten = "SELECT * FROM aanbevolen";
            Statement stm = databaseConnection.createStatement();
            ResultSet resultSet = stm.executeQuery(queryComponenten);
            while(resultSet.next()){
               int aanbevolenid = resultSet.getInt("aanbevolenID");
               int stockitem = resultSet.getInt("StockItemID");
               System.out.println(aanbevolenid + " " + stockitem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            if(databaseConnection != null){
                return databaseConnection;
            }else{
                databaseConnection = DriverManager.getConnection(dbUrl, user, password);
                databaseConnection.setAutoCommit(true);
                return databaseConnection;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
            System.out.println("Error connecting to database");
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
