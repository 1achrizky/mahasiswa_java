/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import config.config;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp4420s
 */
public class JurusanModel {
    public static Statement stmt;
    public static ResultSet rs;
    
    private static DefaultTableModel tmAll;
    public static DefaultTableModel getAllData(){
        try {
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT * FROM mahasiswa_m";
//            String sql = "insert into mahasiswa_m (nama) values ('"+txNama.getText()+"')";
//            Statement stmt = cn.createStatement();
//            rs = 

//            String[] title = {"NIM","NAMA","JURUSAN"};
            tmAll = new DefaultTableModel(new String[]{"NIM","NAMA","JURUSAN"},0);
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            
            tmAll.getDataVector().removeAllElements();
            tmAll.fireTableDataChanged();
            tmAll.setRowCount(0);
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
            tmAll = new DefaultTableModel(row,col);
            System.out.println(tmAll.getValueAt(3, 2));
            
        } catch (Exception e) {
        }     
        
        return tmAll;
    }
    
    private static PreparedStatement pst;
    public static PreparedStatement insert(String kodeJurusan, String namaJurusan){
        try {
            Connection cn = (Connection)config.configDB();

            String sql = "INSERT into jurusan_m (kodeJurusan,namaJurusan) VALUES (?,?)";
            pst = cn.prepareStatement(sql);
            pst.setString(1, kodeJurusan);
            pst.setString(2, namaJurusan);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            cn.close();
        } catch (HeadlessException | SQLException e) {
//            Logger.getLogger(FormMhs.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, e);
        }
        return pst;
    }
    
    public static void main(String[] args) {    
////        TEST INSERT
//        Object ob = insert_mhs(6,"Yossy", "");
//        System.out.println("Value = "+ob);
        
        
//      READ data
//        System.out.println("fx="+getAllData().getValueAt(3, 2));

        
    }
}
