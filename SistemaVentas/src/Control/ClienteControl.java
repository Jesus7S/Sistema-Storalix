
package Control;

import Modelo.Persona;
import Modelo.PersonaDAO;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



public class ClienteControl implements ActionListener, MouseListener, KeyListener{
    
    private Persona cl;
    private PersonaDAO clDAO;
    private Sistema vista ;
    
     DefaultTableModel modelo = new DefaultTableModel();

    public ClienteControl(Persona cl, PersonaDAO clDAO, Sistema vista) {
        this.cl = cl;
        this.clDAO = clDAO;
        this.vista = vista;
        this.vista.btnAgregarClie.addActionListener(this);
        this.vista.btnModificarModifi.addActionListener(this);
        this.vista.txtIDModiClie1.addKeyListener(this);
        this.vista.TableConsultarClienteModifi.addMouseListener(this);
        this.vista.btnEliminarClient.addActionListener(this);
    
        ListarCliente();
        
    }
    
     public void ListarCliente(){
      Tables color = new Tables ();
        vista.TableConsultarClienteModifi.setDefaultRenderer(vista.TableConsultarClienteModifi.getColumnClass(0), color);
        List<Persona> lista = clDAO.ListarCliente(vista.txtIDModiClie1.getText());
        modelo = (DefaultTableModel) vista.TableConsultarClienteModifi.getModel();
        Object[] ob = new Object[10];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getCli_tipo_id();      
            ob[2] = lista.get(i).getCli_nombre();
            ob[3] = lista.get(i).getCli_apellido();
            ob[4] = lista.get(i).getCli_piso(); 
            ob[5] = lista.get(i).getCli_cuenta();
            ob[6] = lista.get(i).getCli_celular();
            ob[7] = lista.get(i).getCli_correo();
            ob[8] = lista.get(i).getCli_direccion();

            
             modelo.addRow(ob);
        }
        vista.TableConsultarClienteModifi.setModel(modelo);
        
        JTableHeader header = vista.TableConsultarClienteModifi.getTableHeader();
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
        if(e.getSource() == vista.btnAgregarClie){
            if(vista.txtIDClienteClie.getText().equals("") 
               || vista.txtNombreClie.getText().equals("") 
                || vista.txtApellidoClie.getText().equals("") 
                      || vista.txt_TelefonoCLIE.getText().equals("") 
                       || vista.txtCorreoCLie.getText().equals("") 
                        || vista.txtDireccionClie.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                cl.setId(vista.txtIDClienteClie.getText());
                cl.setCli_tipo_id(vista.txtTipoCedulaCli.getText());           
                cl.setCli_nombre(vista.txtNombreClie.getText());
                cl.setCli_apellido(vista.txtApellidoClie.getText());
                cl.setCli_piso(vista.cbxTipoClienteCLI.getSelectedItem().toString());
                cl.setCli_cuenta(vista.cbxGeneroCliente.getSelectedItem().toString());
                cl.setCli_celular(vista.txt_TelefonoCLIE.getText());
                cl.setCli_correo(vista.txtCorreoCLie.getText());
                cl.setCli_direccion(vista.txtDireccionClie.getText());
                
                if(clDAO.Registrar(cl)){
                     limpiarTable();
                    ListarCliente();
                    limpiarRegistro();

                    JOptionPane.showMessageDialog(null, "Cliente registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar el Cliente");
                }
            }
        }else{
              if(e.getSource() == vista.btnModificarModifi){
            if(vista.txtIDModiClie1.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                cl.setId(vista.txtIDModiClie1.getText());
                cl.setCli_tipo_id(vista.txtTipoCedulaModifi.getText());
                cl.setCli_nombre(vista.txtNombreModifi.getText());
                cl.setCli_apellido(vista.txtApellidoModifi.getText());
                cl.setCli_piso(vista.txtTipoClienteModifi.getSelectedItem().toString());
                cl.setCli_cuenta(vista.cbxGeneroModifi.getSelectedItem().toString());
                cl.setCli_celular(vista.txtTelefonoModiClie1.getText());
                cl.setCli_correo(vista.txtCorreoModiClie1.getText());
                cl.setCli_direccion(vista.txtDireccionModiClie1.getText());

                
                if(clDAO.Modificar(cl)){
                    ListarCliente();
                    limpiarTable();
                    limpiarConsulta();
                   
                    JOptionPane.showMessageDialog(null, "Cliente Modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al modificar el Cliente");
                }
            }
        }else{
                   if(e.getSource() == vista.btnEliminarClient){
                   if (!"".equals(vista.txtIDModiClie1.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar","Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                String id = vista.txtIDModiClie1.getText();
                clDAO.EliminarCliente(id);
                 LimpiarTable();   
                 limpiarConsulta();
            }
        }else if ("".equals(vista.txtIDModiClie1.getText())){
                
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
          if (e.getSource() == vista.txtIDModiClie1){
           limpiarTable();
           ListarCliente();
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.TableConsultarClienteModifi){
            int fila = vista.TableConsultarClienteModifi.rowAtPoint(e.getPoint());
            vista.txtIDModiClie1.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 0).toString());
            vista.txtTipoCedulaModifi.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 1).toString());
            vista.txtNombreModifi.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 2).toString());
            vista.txtApellidoModifi.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 3).toString());
            vista.txtTipoClienteModifi.setSelectedItem(vista.TableConsultarClienteModifi.getValueAt(fila, 4).toString());
            vista.cbxGeneroModifi.setSelectedItem(vista.TableConsultarClienteModifi.getValueAt(fila, 5).toString());
            vista.txtTelefonoModiClie1.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 6).toString());
            vista.txtCorreoModiClie1.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 7).toString());
            vista.txtDireccionModiClie1.setText(vista.TableConsultarClienteModifi.getValueAt(fila, 8).toString());
            
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
        vista.txtIDModiClie1.setText("");
        vista.txtTipoCedulaModifi.setText("");
        vista.txtNombreModifi.setText("");
        vista.txtApellidoModifi.setText("");
          vista.txtTelefonoModiClie1.setText("");
           vista.txtCorreoModiClie1.setText("");
            vista.txtDireccionModiClie1.setText("");
             
    }
        
        private void limpiarRegistro(){
        vista.txtIDClienteClie.setText("");
        vista.txtTipoCedulaCli.setText("");
        vista.txtNombreClie.setText("");
        vista.txtApellidoClie.setText("");
        vista.txt_TelefonoCLIE.setText("");
        vista.txtCorreoCLie.setText("");
        vista.txtDireccionClie.setText("");
        
    }
        
        
}


