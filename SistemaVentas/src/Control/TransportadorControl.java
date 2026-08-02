/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Empleado;
import Modelo.Tables;
import Modelo.Transportista;
import Modelo.TransportistaDAO;
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



public class TransportadorControl implements ActionListener, MouseListener, KeyListener {
 
   private Transportista tr;
   private TransportistaDAO trDAO;
   private Sistema vista ;
 
   
     DefaultTableModel modelo = new DefaultTableModel();
    
    public TransportadorControl (Transportista tr,TransportistaDAO trDAO, Sistema vista){
         this.tr = tr;
         this.trDAO = trDAO;
         this.vista = vista;
         this.vista.btnAgregarTransp.addActionListener(this);
         this.vista.btnModificaTrans.addActionListener(this);
         this.vista.btnEliminarTransp.addActionListener(this);
         this.vista.txtIDTransportista.addKeyListener(this);
         this.vista.TableTransportista.addMouseListener(this);
         
         ListarTransportista();
     }
     
        
       public void ListarTransportista(){
      Tables color = new Tables ();
        vista.TableTransportista.setDefaultRenderer(vista.TableTransportista.getColumnClass(0), color);
        List<Transportista> lista = trDAO.ListarTransportista(vista.txtIDEmpleado1.getText());
        modelo = (DefaultTableModel) vista.TableTransportista.getModel();
        Object[] ob = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId_Transportista();
            ob[1] = lista.get(i).getTra_Nombre();      
            ob[2] = lista.get(i).getTra_NoCaja();
            ob[3] = lista.get(i).getTra_Transportadora();
           

            
             modelo.addRow(ob);
        }
        vista.TableTransportista.setModel(modelo);
        
        JTableHeader header = vista.TableTransportista.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }
       
       
                public void LimpiarTable() {
        //Creamos un bucle for para poder recorer todo el modelo ( las tablas )
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
                
                
                
                
                 public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnAgregarTransp){
            if(vista.txtIDTransportista.getText().equals("") 
               || vista.txtNombreComple.getText().equals("") 
                || vista.txtNoCaja.getText().equals("") 
                      || vista.txtTransportadora.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                tr.setId_Transportista(vista.txtIDTransportista.getText());
                tr.setTra_Nombre(vista.txtNombreComple.getText());           
                tr.setTra_NoCaja(vista.txtNoCaja.getText());
                tr.setTra_Transportadora(vista.txtTransportadora.getText());
               
                
                if(trDAO.Registrar(tr)){
                     LimpiarTable();
                    ListarTransportista();
                    limpiarRegistro();

                    JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar el Empleado");
                }
            }
        }else{
              if(e.getSource() == vista.btnModificaTrans){
            if(vista.txtIDTransportista.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                tr.setId_Transportista(vista.txtIDTransportista.getText());
                tr.setTra_Nombre(vista.txtNombreComple.getText());           
                tr.setTra_NoCaja(vista.txtNoCaja.getText());
                tr.setTra_Transportadora(vista.txtTransportadora.getText());
                
                
                if(trDAO.ModificarTrans(tr)){
                    ListarTransportista();
                    LimpiarTable();
                    limpiarConsulta();
                   
                    JOptionPane.showMessageDialog(null, "Empleado Modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al modificar el Empleado");
                }
            }
        }else{
                   if(e.getSource() == vista.btnEliminarTransp){
                   if (!"".equals(vista.txtIDTransportista.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar","Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                String id = vista.txtIDTransportista.getText();
                trDAO.EliminarTransportista(id);
                 LimpiarTable();   
                 limpiarConsulta();
            }
        }else if ("".equals(vista.txtIDTransportista.getText())){
                
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }
              }
                    
        
        }
    }
        
    }
                 
             public void limpiarTable (){
        for(int i = 0; i < modelo.getRowCount(); i++){
            modelo.removeRow(i);
            i =i - 1 ;        }
    }      
          
  
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
          if (e.getSource() == vista.txtIDTransportista){
           limpiarTable();
           ListarTransportista();
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.TableTransportista){
            int fila = vista.TableTransportista.rowAtPoint(e.getPoint());
            vista.txtIDTransportista.setText(vista.TableTransportista.getValueAt(fila, 0).toString());
            vista.txtNombreComple.setText(vista.TableTransportista.getValueAt(fila, 1).toString());
            vista.txtNoCaja.setText(vista.TableTransportista.getValueAt(fila, 2).toString());
            vista.txtTransportadora.setText(vista.TableTransportista.getValueAt(fila, 3).toString());
          
            
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

    
    
    private void limpiarConsulta(){ 
         vista.txtIDTransportista.setText("");
        vista.txtNombreComple.setText("");
        vista.txtNoCaja.setText("");
        vista.txtTransportadora.setText("");
    }
    
    
       private void limpiarRegistro(){
          vista.txtIDTransportista.setText("");
        vista.txtNombreComple.setText("");
        vista.txtNoCaja.setText("");
        vista.txtTransportadora.setText("");
        
    }
}

