/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.mysql.cj.jdbc.Driver;


public class config {    
    private static Connection mysqlconfig;
    public static Connection configDB()throws SQLException{
        try {
//            static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//            Class.forName(JDBC_DRIVER);
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName(JDBC_DRIVER);
            
            String url="jdbc:mysql://localhost:3306/mahasiswa_javaprj"; //url database
            String user="root"; //user database
            String pass="toor"; //password database
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            mysqlconfig=DriverManager.getConnection(url, user, pass);   
            System.out.println(mysqlconfig);
        } catch (SQLException e) {
            System.err.println("koneksi gagal. "+e.getMessage()); //perintah menampilkan error pada koneksi
        }
        return mysqlconfig;
    }    
}