import java.sql.*;

public class DB {

    public static Connection databaseConnection;

    private static final String dbUrl = "jdbc:mariadb://localhost:3306/monitoringstest";
    private static final String user = "root";
    private static final String password = "";

    private DB(){}

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
    public void components(){
        try {
            String queryComponenten = "SELECT * FROM componenten";
            Connection connection = DB.getConnection();
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(queryComponenten);
            /*while(resultSet.next()){
                //int aanbevolenid = resultSet.getInt("aanbevolenID");
                //int stockitem = resultSet.getInt("StockItemID");
                String hostnaam = resultSet.getString("hostnaam");
                //System.out.println(aanbevolenid + " " + stockitem);
                System.out.println(hostnaam + " gelukt");
            }*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void componentsTable(){

    }

    public static void main(String[] args) {
        DB db = new DB();
        db.components();
    }
}
//component = new Component(resultSet.getInt("beschikbareopslag"), resultSet.getInt("upTime"), resultSet.getInt("processorBelasting"), resultSet.getString("afbeelding"), resultSet.getString("hostnaam"), resultSet.getBoolean("beschikbaarheidsstatus"));
