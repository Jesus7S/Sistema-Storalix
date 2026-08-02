
package Control;

import Modelo.DarAltayBajaUsuario;
import Modelo.DarAltayBajaUsuarioDao;
import Modelo.Tables;
import Vista.Sistema;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DarAltayBajaUsuarioControl implements ActionListener,  MouseListener, KeyListener{
    
    private DarAltayBajaUsuario Dar;
    private DarAltayBajaUsuarioDao DarDao;
    private Sistema vista ;

    DefaultTableModel modelo = new DefaultTableModel(); //--> para la tabla 

    public DarAltayBajaUsuarioControl(DarAltayBajaUsuario Dar, DarAltayBajaUsuarioDao DarDao, Sistema vista) {
        this.Dar = Dar;
        this.DarDao = DarDao;
        this.vista = vista;
        this.vista.txtUsuarioInabilitado.addKeyListener(this);
         this.vista.TableUserEliminado.addMouseListener(this);
         this.vista.JMenuReingresarUsu.addActionListener(this);
        listarUsuarios();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource() == vista.JMenuReingresarUsu){
            if (vista.txtUsuarioInabilitado.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Selecione una fila para reingresar");
            
            }else{
               int  id = Integer.parseInt(vista.txtUsuarioInabilitado.getText());
               
               if (DarDao.accion("Activo",id)){
                   limpiarTable();           
                   DarDao.EliminarUsuario(id);   
                    listarUsuarios();
                  vista.jTabbedPane1.setSelectedIndex(4);
                   limpiar();
                   JOptionPane.showMessageDialog(null, "Usuario Reingresado");                
               
               }else{
                   JOptionPane.showMessageDialog(null, "Erro al reingresar el usuario");
               }
            }
                  
              }else {
                  limpiar();
              }
    }
       public void  listarUsuarios(){
        Tables color = new Tables ();
        vista.TableUserEliminado.setDefaultRenderer(vista.TableUserEliminado.getColumnClass(0), color);
        List<DarAltayBajaUsuario> lista = DarDao.ListarUsuariosBajayAlta(vista.txtUsuarioInabilitado.getText());
        modelo = (DefaultTableModel) vista.TableUserEliminado.getModel();
        Object[] ob = new Object[6];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getCorreo();   
            ob[3] = lista.get(i).getRol();
            ob[4] = lista.get(i).getEstado();
            ob[5] = lista.get(i).getFecha();
             modelo.addRow(ob);
        }
        vista.TableUserEliminado.setModel(modelo);
        
        JTableHeader header = vista.TableUserEliminado.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }
       
       
      public void limpiarTable (){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =i - 1 ;        }
    }
      
          private void limpiar(){
        vista.txtUsuarioInabilitado.setText("");
  
    }

    @Override
    public void mouseClicked(MouseEvent e) {
              if(e.getSource() == vista.TableUserEliminado){
            int fila = vista.TableUserEliminado.rowAtPoint(e.getPoint());
            vista.txtUsuarioInabilitado.setText(vista.TableUserEliminado.getValueAt(fila, 0).toString());
           
            
        
        }
  }

    @Override
    public void mousePressed(MouseEvent e) {
   }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
   }

    @Override
    public void mouseExited(MouseEvent e) {
   }

    @Override
    public void keyTyped(KeyEvent e) {
   }

    @Override
    public void keyPressed(KeyEvent e) {
  }

    @Override
    public void keyReleased(KeyEvent e) {
          if (e.getSource() == vista.txtUsuarioInabilitado){
           limpiarTable();
           listarUsuarios();
            
        }
   }
    
}
