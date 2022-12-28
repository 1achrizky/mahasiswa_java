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
    public static Connection cnn;
    public static Statement stmt;
    public static ResultSet rs;
    public static ResultSet rsAll;
    
    private static String[] title = {"id","kodeJurusan","namaJurusan"};
    private static String[] titleLbl = {"ID","Kode Jurusan","Nama Jurusan"};
    
    private String username;
    public void setUsername(String username){
        this.username = username;
    }
    
    public static void setRs(ResultSet rs){
        JurusanModel.rs = rs;
    }
    
    public static ResultSet getRs(){
        return JurusanModel.rs;
    }
    
    public static int cnt=0;
    public static void setCnt(int cnt){
        JurusanModel.cnt = cnt;
    }
    
    public static int getCnt(){
        try {
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT COUNT(*) FROM jurusan_m";            
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {                
                JurusanModel.cnt = rs.getInt(1);
            }            
//            Statement stmtt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = stmtt.executeQuery(sql);
            
        }catch(SQLException e){
            
        }
        return JurusanModel.cnt;
    }
    
    
    public static ResultSet selectDB(){
        try{
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT * FROM jurusan_m";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
//            cn.close(); // error kalau di beri close()
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rs;
    }
    
    public static DefaultTableModel tmAll;
    public static DefaultTableModel getAllData(){
        try {
            JurusanModel.cnt = getCnt();
                    
            Connection cn = (Connection)config.configDB();
            String sql = "SELECT * FROM jurusan_m";
            
            tmAll = new DefaultTableModel(JurusanModel.titleLbl,0);
            stmt = cn.createStatement();
            JurusanModel.rsAll = stmt.executeQuery(sql);
            
            
//            JurusanModel.setRs(rs);
//            System.out.println("gRow="+rs.getRow());  //check ok 
            
            tmAll.getDataVector().removeAllElements();
            tmAll.fireTableDataChanged();
            tmAll.setRowCount(0);
            
//          initial length array
            String[][] row = new String[JurusanModel.cnt][JurusanModel.title.length];            
//            System.out.println("cek==="+JurusanModel.cnt+"__"+title.length);  //check ok 
            
            int j=0;
            
            while (JurusanModel.rsAll.next()) {                
//                Object[] data = {
//                    rs.getString("nim"),
//                    rs.getString("nama"),
//                    rs.getString("jurusan")
//                };
//                System.out.println(data[1]);
//                row[j] = data;

                for (int i = 0; i < title.length; i++) {
                    row[j][i] = JurusanModel.rsAll.getString(i+1);
//                    System.out.println(rs.getString(i+1));//   //check ok                 
                }
//                System.out.println("++"); //check ok
                
//                System.out.println(rs.getString("nama"));
//                System.out.println(Arrays(data));
//                tmAllMhs.addRow(data);              
                j++;
            }
//            System.out.println("gRowJ="+j);  //check ok
//            
//            System.out.println("---");  //check ok
////            System.out.println(Arrays(row));
//            System.out.println(row[2][1]); //check ok

            
            String col [] = {"ID","JURUSAN","KODE JURUSAN"};
            tmAll = new DefaultTableModel(row,col);
//            System.out.println("tmAll=="+tmAll.getValueAt(0, 1)); //check ok
            
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
    
//    public void update(int id, String kodeJurusan, String namaJurusan){
    public static PreparedStatement update(int id, String kodeJurusan, String namaJurusan){
        try {
            Connection cn = (Connection)config.configDB();

            String sql = "UPDATE jurusan_m SET "
                    + "kodeJurusan=?,"
                    + "namaJurusan=? WHERE id=?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, kodeJurusan);
            pst.setString(2, namaJurusan);
            pst.setInt(3, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Update Berhasil");
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pst;
    }
    
    
    public static PreparedStatement delete(int id){
        try {
            Connection cn = (Connection)config.configDB();

            String sql = "DELETE FROM jurusan_m WHERE id=?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Delete Berhasil");
            cn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pst;
    }
    
//    public static void main(String[] args) {    
//////        TEST INSERT
////        Object ob = insert_mhs(6,"Yossy", "");
////        System.out.println("Value = "+ob);
//        
//        
////      READ data
//        System.out.println("fx="+getAllData().getValueAt(0, 2));
////        System.out.println("cnt="+getCnt());        
//    }
    
}
