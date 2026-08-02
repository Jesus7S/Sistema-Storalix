
package Modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Tables extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(
            JTable jtable, Object o, boolean bln, boolean blnl, int row, int col) {
        super.getTableCellRendererComponent(jtable, o, bln, blnl, row, col); 
       
        if (jtable.getValueAt(row, col).toString().equals("Inactivo")){
            
            setBackground(Color.red);
            setForeground(Color.white);
       
        }else{
             setBackground(Color.white);
             setForeground(Color.black);
        }
        return this;
    }
    
   
}
