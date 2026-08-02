package Control;

import Modelo.Login;
import Modelo.LoginDAO;
import Modelo.Tables;
import Vista.*;
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


public class UsuarioControl implements ActionListener, MouseListener, KeyListener{

    private Login lo;
    private LoginDAO loDAO;
    private Sistema vista ;

    DefaultTableModel modelo = new DefaultTableModel(); //--> para la tabla 
    
    public UsuarioControl(Login lo, LoginDAO loDAO, Sistema vista) {
        this.lo = lo;
        this.loDAO = loDAO;
        this.vista = vista;
        this.vista.btnRegistrarUser.addActionListener(this);
        this.vista.btnModificarUser.addActionListener(this);
        this.vista.JMenuEliminarUser.addActionListener(this);
        this.vista.JMenuReingresarUser.addActionListener(this);
        this.vista.btnNuevoUser.addActionListener(this);
        this.vista.txtBuscarUser.addKeyListener(this);
         this.vista.TableUser.addMouseListener(this);
         
        listarUsuarios();
    }
    
    // getpassword se coloca de esta manera String.valueOf(vista.txtContraseñaUser.getPassword()).equals("") 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrarUser){
            if(vista.txtUsuarioUser.getText().equals("") 
                || vista.txtCorreoUser.getText().equals("") 
                    ||  String.valueOf(vista.txtContraseñaUser.getPassword()).equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                lo.setNombre(vista.txtUsuarioUser.getText());
                lo.setCorreo(vista.txtCorreoUser.getText());
                lo.setPass(String.valueOf(vista.txtContraseñaUser.getPassword()));
                lo.setRol(vista.cbxtipo_cli_idUser.getSelectedItem().toString());
                
                if(loDAO.insertar(lo)){
                    
                   limpiarTable();
                   listarUsuarios();
                   limpiar();
                   
                    JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar el usuario");
                }
            }
        }else{
              if(e.getSource() == vista.btnModificarUser){
            if(vista.txtUsuarioUser.getText().equals("") 
                || vista.txtCorreoUser.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                lo.setNombre(vista.txtUsuarioUser.getText());
                lo.setCorreo(vista.txtCorreoUser.getText());
                lo.setRol(vista.cbxtipo_cli_idUser.getSelectedItem().toString());
                lo.setId(Integer.parseInt(vista.txtIDUser.getText()));
                
                if(loDAO.Modificar(lo)){
                    limpiarTable();
                    listarUsuarios();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Usuario Modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al modificar el usuario");
                }
            }
        }else if (e.getSource() == vista.JMenuEliminarUser){
            if (vista.txtIDUser.getText().equals("")){
               
                JOptionPane.showMessageDialog(null, "Selecione una fila para eliminar");
            
            }else{
               int  id = Integer.parseInt(vista.txtIDUser.getText());
               if (loDAO.accion("Inactivo", id)){
                   limpiarTable();  
                   loDAO.EliminarUsuario(id);
                   listarUsuarios();
                   limpiar();
                   JOptionPane.showMessageDialog(null, "Usuario eliminado");
               
               }else{
                   JOptionPane.showMessageDialog(null, "Erro al eliminar el usuario");
               }
            }
                  
              }else if (e.getSource() == vista.JMenuReingresarUser){
                limpiarTable();
                 listarUsuarios(); 
                 vista.jTabbedPane1.setSelectedIndex(6);
                  limpiar();
                  
              }else{
                  limpiar();
              }
        }
    }
    
    public void  listarUsuarios(){
        Tables color = new Tables ();
        vista.TableUser.setDefaultRenderer(vista.TableUser.getColumnClass(0), color);
        List<Login> lista = loDAO.ListarUsuarios(vista.txtBuscarUser.getText());
        modelo = (DefaultTableModel) vista.TableUser.getModel();
        Object[] ob = new Object[5];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getCorreo();   
            ob[3] = lista.get(i).getRol();
            ob[4] = lista.get(i).getEstado();
             modelo.addRow(ob);
        }
        vista.TableUser.setModel(modelo);
        
        JTableHeader header = vista.TableUser.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }
    
    
    
    public void limpiarTable (){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =i - 1 ;        }
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.TableUser){
            int fila = vista.TableUser.rowAtPoint(e.getPoint());
            vista.txtIDUser.setText(vista.TableUser.getValueAt(fila, 0).toString());
            vista.txtUsuarioUser.setText(vista.TableUser.getValueAt(fila, 1).toString());
            vista.txtCorreoUser.setText(vista.TableUser.getValueAt(fila, 2).toString());
            vista.cbxtipo_cli_idUser.setSelectedItem(vista.TableUser.getValueAt(fila, 3).toString());
            
        
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
        if (e.getSource() == vista.txtBuscarUser){
           limpiarTable();
           listarUsuarios();
            
        }
    }
    
    private void limpiar(){
        vista.txtIDUser.setText("");
        vista.txtUsuarioUser.setText("");
        vista.txtCorreoUser.setText("");
        vista.txtContraseñaUser.setText("");
    }
    
  
}
