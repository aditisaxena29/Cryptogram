/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditi
 */
public class DbConnection {
    public static Connection conn ;
       static
       {
           try
           {
             Class.forName("com.mysql.cj.jdbc.Driver");
                 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/crypto", "root", "SQLroot@29");
               
              
                    
            
           }
           catch(Exception sq)
           {
           JOptionPane.showMessageDialog(null,"Exception in opening connection to the Database!!!");     
           }
       
    
    }
     public static Connection getConnection() {
        return conn;
    }
}

