/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp4420s
 */
public class Learn {
    private static DefaultTableModel tableModel;
            
    public static DefaultTableModel test_array(){
        String data[][] = {
            {"Australia","5","1"},
            {"US","10","2"},
            {"Canada","9","3"},
            {"India","7","4"},
            {"Poland","2","5"},
            {"SriLanka","5","6"}
        };
        String col [] = {"Team","Selected Players","Rank"};
        tableModel = new DefaultTableModel(data,col);
        return tableModel;
        
//        JTable table = new JTable(tableModel);
//        
//        Object ob = tableModel.getValueAt(3, 2);
//        System.out.println("Value = "+ob);
    }
    
    public static Object ini(int r, int c){
        return test_array().getValueAt(r, c);
    }
    
    public static void main(String[] args) {        
        Object ob = test_array().getValueAt(3, 2);
        System.out.println("Value = "+ob);
        System.out.println(ini(2,0));
    }

    
}
