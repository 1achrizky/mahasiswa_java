/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
//import java.util.logging.Level;
//import java.util.logging.Logger;

import config.config;
import java.awt.HeadlessException;
import java.util.Arrays;



public class MahasiswaModel {
    
    public static Statement stmt;
    public static ResultSet rs;
    
    private static DefaultTableModel tmAllMhs;
    public static DefaultTableModel getAllData(){
        try {
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT mhs.*, j.namaJurusan FROM mahasiswa_m mhs"
                    + " LEFT JOIN jurusan_m j ON j.kodeJurusan=mhs.jurusan";
//            String sql = "insert into mahasiswa_m (nama) values ('"+txNama.getText()+"')";
//            Statement stmt = cn.createStatement();
//            rs = 

//            String[] title = {"NIM","NAMA","JURUSAN"};
            tmAllMhs = new DefaultTableModel(new String[]{"NIM","NAMA","JURUSAN"},0);
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            
            tmAllMhs.getDataVector().removeAllElements();
            tmAllMhs.fireTableDataChanged();
            tmAllMhs.setRowCount(0);
            String[][] row = new String[100][100];
            int j=0;
            String[] title = {"id","nim","nama","jurusan"};
            
            while (rs.next()) {                
                Object[] data = {
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jurusan")
                };
//                System.out.println(data[1]);
//                row[j] = data;

                for (int i = 0; i < title.length; i++) {
                    row[j][i] = rs.getString(i+1);
                    System.out.println(rs.getString(i+1));//                    
                }
                System.out.println("++");
                
//                System.out.println(rs.getString("nama"));
//                System.out.println(Arrays(data));
//                tmAllMhs.addRow(data);              
                j++;
            }
            
            System.out.println("---");
//            System.out.println(Arrays(row));
            System.out.println(row[4][2]);

            
            String col [] = {"ID","NIM","NAMA","JURUSAN"};
            tmAllMhs = new DefaultTableModel(row,col);
            System.out.println(tmAllMhs.getValueAt(3, 2));
            
        } catch (Exception e) {
        }     
        
        return tmAllMhs;
    }
    
    private static PreparedStatement pst;
    public static PreparedStatement insert_mhs(int nim, String nama, String jurusan){
        try {
            Connection cn = (Connection)config.configDB();
//            String sql = "insert into mahasiswa_m (nim,nama) values ("+txtNim.getText()+",'"+txtNama.getText()+"')";
            
////            BISA
//            String sql = "insert into mahasiswa_m (nim,nama) values ("+nim+",'"+nama+"')";
//            pst = cn.prepareStatement(sql);
//            pst.execute();

            String sql = "INSERT into mahasiswa_m (nim,nama,jurusan) VALUES (?,?,?)";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, nim);
            pst.setString(2, nama);
            pst.setString(3, jurusan);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            cn.close();
        } catch (HeadlessException | SQLException e) {
//            Logger.getLogger(FormMhs.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, e);
        }
        return pst;
    }
    
    public static int getArrIndex(String[] arr, String keyword){
        int index = -1;
        for (int i = 0; i < arr.length; i++)
            if(arr[i]==keyword){ index = i; break;}        
        return index;
    }
    
    public static ResultSet selectDB(){
        try{
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT mhs.*, j.namaJurusan FROM mahasiswa_m mhs"
                    + " LEFT JOIN jurusan_m j ON j.kodeJurusan=mhs.jurusan";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
//    public static void main(String[] args) {    
//////        TEST INSERT
////        Object ob = insert_mhs(6,"Yossy", "");
////        System.out.println("Value = "+ob);
//        
//        
////      READ data
//        System.out.println("fx="+getAllData().getValueAt(3, 2));
//
////      getArrIndex
//        String[] title = {"id","nim","nama","jurusan"};
//        System.err.println(String.valueOf(getArrIndex( title, "nama") ) );
//        
//    }

    
    
    
    
}
