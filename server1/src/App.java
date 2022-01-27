import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/bdx";
        final String user = "root";
        final String pass = "2001";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] arg){
        Connection connection = getJDBCConnection();
            if(connection != null){
                System.out.println("thanhcong");
            }else {
                System.out.println("thatbai");
            }
        }
    }