 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi_db;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author krisn
 */
public class koneksi {
    private static Connection cekkoneksi;
    
    public static Connection getCekkoneksi(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("Server.properties"));
        } catch (IOException e) {
            System.out.println(e);
        }
        String ip = prop.getProperty("ip");
        String nmdb = prop.getProperty("nmdb");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("pass");
        
        if(cekkoneksi==null){
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                cekkoneksi = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+nmdb+"",""+user+"",""+pass+"");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        
        return cekkoneksi;
    }
}
