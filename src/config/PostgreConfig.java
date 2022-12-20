/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class PostgreConfig {
    private Connection conn;
    private String kelasname = "org.postgresql.Driver";
    
    public PostgreConfig(){
        konek();
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void setConnection(String Db, String usr, String passw){
        try {
            if(conn !=null){
                conn.close();
            }
            Class.forName(kelasname);
            conn = DriverManager.getConnection(Db, usr, passw);
        } catch (Exception e) {
            String connectMsg = "Gagal connect db. "+ e.getMessage()+" "+e.getLocalizedMessage();
            JOptionPane.showMessageDialog(null, connectMsg);                    
        }        
    }
    
    private Connection konek(){
        try {
            if(conn != null){
                conn.close();
            }
            Class.forName(kelasname);
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mahasiswa_javaprj");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432", "postgres", "root");
        } catch (Exception e) {
            String connectMsg = "Gagal connect db. "+ e.getMessage()+" "+e.getLocalizedMessage();
            JOptionPane.showMessageDialog(null, connectMsg);
        }
        return conn;
    }
    
    public static void main(String[] args) {
        PostgreConfig con = new PostgreConfig();
        con.konek();
        if(con.conn==null){
            JOptionPane.showMessageDialog(null, "Gagal Connect DB.");
        }else{
            JOptionPane.showMessageDialog(null, "Berhasil Connect DB.");
        }
    }
}
