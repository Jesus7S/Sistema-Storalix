
package Control;



import Modelo.Configuracion;
import Modelo.LoginDAO;
import Vista.Sistema;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;


public class ConfigControl implements MouseListener {
 
 
    //instanciaremos el panel donde tenemos el sistema
    private Configuracion cof;
    private LoginDAO cDAO;
    private Sistema vista;
    //Ahora crearemos un constructor donde pondremos a la escucha a todos los leabel

    public ConfigControl(Configuracion cof,LoginDAO cDAO,Sistema vista) {
        this.cof = cof;
        this.cDAO = cDAO;
        this.vista = vista;
      //Parte de introducir Ticket
    //    this.vista.jLabelIntroducirTick.addMouseListener(this);
      
       
        //Parte de producto
        this.vista.jLabelConsultarProd.addMouseListener(this);
        this.vista.jLabelAgregarProd.addMouseListener(this);
        
        //Parte de Cliente
        this.vista.jLabelConsultarClien.addMouseListener(this);
        this.vista.jLabelAgregarClien.addMouseListener(this);
         this.vista.jLabelVendedor.addMouseListener(this);
          this.vista.jLabelTransportista.addMouseListener(this);

        //Parte de opciones
        this.vista.jLabelUsu.addMouseListener(this);
        this.vista.jLabelConfig.addMouseListener(this);
        this.vista.jLabelCerrarSesi.addMouseListener(this);
        
        
        
       
        
        
        
    }
    
    
      
    
  
            
            
    @Override
    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == vista.jLabelAgregarProd){
            vista.jTabbedPane1.setSelectedIndex(1);
        }else if (e.getSource() == vista.jLabelConsultarProd){
            vista.jTabbedPane1.setSelectedIndex(0);
       }else if (e.getSource() == vista.jLabelUsu){
            vista.jTabbedPane1.setSelectedIndex(4);
       }else if(e.getSource() == vista.jLabelAgregarClien){
            vista.jTabbedPane1.setSelectedIndex(3);
        }else if(e.getSource() == vista.jLabelConsultarClien){
            vista.jTabbedPane1.setSelectedIndex(2);
        }else if(e.getSource() == vista.jLabelConfig){
            vista.jTabbedPane1.setSelectedIndex(5);
        }else if(e.getSource() == vista.jLabelVendedor){
            vista.jTabbedPane1.setSelectedIndex(7);
        }else if(e.getSource() == vista.jLabelTransportista){
            vista.jTabbedPane1.setSelectedIndex(8);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
//Aqui cambiamos los coloeres de los cuadros de seleccion del sistema ( los que se encuentran a la izquierda
    @Override
    public void mouseEntered(MouseEvent e) {
      /*   if(e.getSource()== vista.jLabelIntroducirTick ){
            vista.jPanelIntroducirTicket.setBackground(new Color(255,51,51));
      
        
        }else*/ if(e.getSource()== vista.jLabelConsultarProd ){
            vista.jPanelConsultarProducto.setBackground(new Color(255,51,51));
        
        }else if(e.getSource()== vista.jLabelAgregarProd ){
            vista.jPanelAgregarProducto.setBackground(new Color(255,51,51));
        
            
        }else if(e.getSource()== vista.jLabelConsultarClien ){
            vista.jPanelConsultarCliente.setBackground(new Color(255,51,51));
        
        }else if(e.getSource()== vista.jLabelAgregarClien ){
            vista.jPanelAgregarCliente.setBackground(new Color(255,51,51));
        
        
        }else if(e.getSource()== vista.jLabelUsu ){
            vista.jPanelUsuario.setBackground(new Color(255,51,51));
        
        }else if(e.getSource()== vista.jLabelConfig ){
            vista.jPanelConfiguracion.setBackground(new Color(255,51,51));
        
        }else if(e.getSource()== vista.jLabelCerrarSesi ){
            vista.jPanelCerrarSesion.setBackground(new Color(255,51,51));
            
        }else if(e.getSource()== vista.jLabelVendedor ){
            vista.jPanelVendedor.setBackground(new Color(255,51,51));
            
        }else if(e.getSource()== vista.jLabelTransportista ){
            vista.jPanelTransportista.setBackground(new Color(255,51,51));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       /*  if(e.getSource()== vista.jLabelIntroducirTick ){
            vista.jPanelIntroducirTicket.setBackground(new Color(66,135,230));
       
        }else*/ if(e.getSource()== vista.jLabelConsultarProd ){
            vista.jPanelConsultarProducto.setBackground(new Color(66,135,230));
        
        }else if(e.getSource()== vista.jLabelAgregarProd ){
            vista.jPanelAgregarProducto.setBackground(new Color(66,135,230));
        
  
        
        }else if(e.getSource()== vista.jLabelConsultarClien ){
            vista.jPanelConsultarCliente.setBackground(new Color(66,135,230));
        
        }else if(e.getSource()== vista.jLabelAgregarClien ){
            vista.jPanelAgregarCliente.setBackground(new Color(66,135,230));

            
        
        }else if(e.getSource()== vista.jLabelUsu ){
            vista.jPanelUsuario.setBackground(new Color(66,135,230));
        
        }else if(e.getSource()== vista.jLabelConfig ){
            vista.jPanelConfiguracion.setBackground(new Color(66,135,230));
        
        }else if(e.getSource()== vista.jLabelCerrarSesi ){
            vista.jPanelCerrarSesion.setBackground(new Color(66,135,230));
            
         }else if(e.getSource()== vista.jLabelVendedor ){
            vista.jPanelVendedor.setBackground(new Color(66,135,230));
            
        }else if(e.getSource()== vista.jLabelTransportista ){
            vista.jPanelTransportista.setBackground(new Color(66,135,230));
        }
    }

 
    }