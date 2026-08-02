/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;

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


public class EmpleadoControl implements ActionListener, MouseListener, KeyListener {
    private Empleado ep;
    private EmpleadoDAO epDAO;
    private Sistema vista ;
    
    
     DefaultTableModel modelo = new DefaultTableModel();
 public EmpleadoControl(Empleado emp, EmpleadoDAO empDAO, Sistema vista) {
        this.ep = emp;
        this.epDAO = empDAO;
        this.vista = vista;
        this.vista.btnAgregarEmpl.addActionListener(this);
        this.vista.btnModificarEmpl1.addActionListener(this);
        this.vista.txtIDEmpleado1.addKeyListener(this);
        this.vista.TableEmpleado.addMouseListener(this);
        this.vista.btnEliminarEmpl1.addActionListener(this);
    
        ListarEmpleado();     
     
 }
     
       public void ListarEmpleado(){
      Tables color = new Tables ();
        vista.TableEmpleado.setDefaultRenderer(vista.TableEmpleado.getColumnClass(0), color);
        List<Empleado> lista = epDAO.ListarEmpleado(vista.txtIDEmpleado1.getText());
        modelo = (DefaultTableModel) vista.TableEmpleado.getModel();
        Object[] ob = new Object[4];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId_Empleado();
            ob[1] = lista.get(i).getNombre();      
            ob[2] = lista.get(i).getCargo();
            ob[3] = lista.get(i).getDepartamento();
           

            
             modelo.addRow(ob);
        }
        vista.TableEmpleado.setModel(modelo);
        
        JTableHeader header = vista.TableEmpleado.getTableHeader();
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
        if(e.getSource() == vista.btnAgregarEmpl){
            if(vista.txtIDEmpleado1.getText().equals("") 
               || vista.txtNombreEmpleado1.getText().equals("") 
                || vista.cbxCargoEmplea1.getSelectedItem().equals("") 
                      || vista.txtDepartamentoEmpl1.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                ep.setId_Empleado(vista.txtIDEmpleado1.getText());
                ep.setNombre(vista.txtNombreEmpleado1.getText());           
                ep.setCargo(vista.cbxCargoEmplea1.getSelectedItem().toString());
                ep.setDepartamento(vista.txtDepartamentoEmpl1.getText());
               
                
                if(epDAO.Registrar(ep)){
                     LimpiarTable();
                    ListarEmpleado();
                    limpiarRegistro();

                    JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar el Empleado");
                }
            }
        }else{
              if(e.getSource() == vista.btnModificarEmpl1){
            if(vista.txtIDEmpleado1.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                ep.setId_Empleado(vista.txtIDEmpleado1.getText());
                ep.setNombre(vista.txtNombreEmpleado1.getText());           
                ep.setCargo(vista.cbxCargoEmplea1.getSelectedItem().toString());
                ep.setDepartamento(vista.txtDepartamentoEmpl1.getText());
                
                
                if(epDAO.Modificar(ep)){
                    ListarEmpleado();
                    LimpiarTable();
                    limpiarConsulta();
                   
                    JOptionPane.showMessageDialog(null, "Empleado Modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al modificar el Empleado");
                }
            }
        }else{
                   if(e.getSource() == vista.btnEliminarEmpl1){
                   if (!"".equals(vista.txtIDEmpleado1.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar","Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                String id = vista.txtIDEmpleado1.getText();
                epDAO.EliminarEmpleado(id);
                 LimpiarTable();   
                 limpiarConsulta();
            }
        }else if ("".equals(vista.txtIDEmpleado1.getText())){
                
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
          if (e.getSource() == vista.txtIDEmpleado1){
           limpiarTable();
           ListarEmpleado();
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.TableEmpleado){
            int fila = vista.TableEmpleado.rowAtPoint(e.getPoint());
            vista.txtIDEmpleado1.setText(vista.TableEmpleado.getValueAt(fila, 0).toString());
            vista.txtNombreEmpleado1.setText(vista.TableEmpleado.getValueAt(fila, 1).toString());
            vista.cbxCargoEmplea1.setSelectedItem(vista.TableEmpleado.getValueAt(fila, 2).toString());
            vista.txtDepartamentoEmpl1.setText(vista.TableEmpleado.getValueAt(fila, 3).toString());
          
            
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
        vista.txtIDEmpleado1.setText("");
        vista.cbxCargoEmplea1.setSelectedItem(" ");
        vista.txtNombreEmpleado1.setText("");
        vista.txtDepartamentoEmpl1.setText("");
         
             
    }
        
        private void limpiarRegistro(){
        vista.txtIDEmpleado1.setText("");
        vista.cbxCargoEmplea1.setSelectedItem(" ");
        vista.txtNombreEmpleado1.setText("");
        vista.txtDepartamentoEmpl1.setText("");
        
    }
        
        
}
