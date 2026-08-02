
package Vista;

import Control.BarrasControl;
import Control.ClienteControl;
import Control.ConfigControl;
import Control.DarAltayBajaUsuarioControl;
import Control.EmpleadoControl;
import Control.TransportadorControl;

import Control.UsuarioControl;
import Modelo.Barra;
import Modelo.BarraDAO;
import Modelo.Persona;
import Modelo.PersonaDAO;
import Modelo.Conexion;
import Modelo.Configuracion;
import Modelo.DarAltayBajaUsuario;
import Modelo.DarAltayBajaUsuarioDao;
import Modelo.DetalleFactura;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Eventos;
import Modelo.Login;
import Modelo.LoginDAO;
import Modelo.Transportista;
import Modelo.TransportistaDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import Reportes.Excel;
import Reportes.Grafico;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.CallableStatement;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Sistema extends javax.swing.JFrame {

    //Para la fecha
 Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
    
    
    
    //Instanciamos las clases
    Login lo = new Login ();
    LoginDAO loDAO = new LoginDAO();
    
    Persona cl = new Persona();
    PersonaDAO clDAO = new PersonaDAO ();
    
    Barra br = new Barra();
    BarraDAO brDAO = new BarraDAO();
    


    Conexion con = new Conexion ();
    Connection cn = con.getConnection();
    
    DarAltayBajaUsuario ba = new DarAltayBajaUsuario();
    DarAltayBajaUsuarioDao baDAO = new DarAltayBajaUsuarioDao();
    
    
    Venta vn = new Venta ();
    VentaDAO vnDAO = new VentaDAO();
    
    DetalleFactura df = new DetalleFactura();

     
    Configuracion cof = new Configuracion ();
    
    Eventos eve = new Eventos ();//Los eventos que se ejecutaran en algunos JTextFile ( ya sea permitir solo numero o solo texto)
    
    Empleado emp = new Empleado();
    EmpleadoDAO empDAO = new EmpleadoDAO();

    
    Transportista tr = new Transportista();
    TransportistaDAO trDAO = new TransportistaDAO();


   
    public Sistema() {
 
 
       
    }

    //Privilegios al momento de acceder 
    public Sistema(int id, String nombre, String rol) {
     initComponents();
  
      this.setLocationRelativeTo(null);
   //  Midate.setDate(fechaVenta); //Para que la fecha de el ese mismo dia aparesca en la casilla que se le esta especificando al momento de ejecutar el sistema.  

         
     
     
     //Lenar los combobox

     brDAO.ConsultarTienda(cbxCodTiendaArticulo);
     brDAO.ConsultarTienda(cbxTienda_ConsultarArticulo);
      
        
   
        
     //JTextFile que no se deben ver
        txtIDUsuario.setVisible(false);
        this.txtIDUser.setVisible(false);
        txtNumeroIdentificacion.setVisible(false);
        txtIdCV.setVisible(false);
        txtTelefonoventa.setVisible(false);
        txtDireccionventa.setVisible(false);
        txtCuentaCli.setVisible(false);
        txtpisoCliente.setVisible(false);
        jTabbedPane1.setEnabled(true);
        txtIva.setVisible(false);
        jLabel45.setVisible(false);
        txtCodigoIDemp.setVisible(false);
        txtCargoEmp.setVisible(false);
        txtDepartamentoEmp.setVisible(false);
        txtNoCajaTransp.setVisible(false);
        txtCodigoIDTransp.setVisible(false);
        txtTransportadorTransp.setVisible(false);
        txtCodigoPersoAuto.setVisible(false);
        txtCargoPersoAuto.setVisible(false);
        
        
        
        
        //Botones que no estaran habilitados
         btnRegistrarUser.setEnabled(false);
         btnAgregarClie.setEnabled(false);
         ACTUALIZAR.setEnabled(false);
         
        //Aqui llamaremos a las clases Controles
        ConfigControl confi= new ConfigControl(cof, loDAO,this);
        UsuarioControl usuco= new UsuarioControl(lo, loDAO,this);
       ClienteControl clico = new ClienteControl(cl,clDAO,this);
        BarrasControl brco = new BarrasControl(br,brDAO, this);
        DarAltayBajaUsuarioControl darAyB = new DarAltayBajaUsuarioControl(ba,baDAO, this);
        EmpleadoControl empCont = new EmpleadoControl(emp,empDAO, this);
        TransportadorControl traCont = new TransportadorControl(tr,trDAO, this);
        
     txtIDUsuario.setText("" + id);
     LabelUsuri.setText("" + nombre);
      
     ListarConfig();// para mostrar en el sistema la informacion de configuracion
      
    
      
      if ((rol).equals("USER")) {
           
         //Usuario 
          jLabelUsu.setVisible(false);
            jPanelUsuario.setVisible(false);
            
           //Configuracion 
            ACTUALIZAR.setVisible(false);
            
       //  Empleado
          btnAgregarEmpl.setVisible(false);
          btnModificarEmpl1.setVisible(false);
          btnEliminarEmpl1.setVisible(false);
          
        //Trasportista  
          btnAgregarTransp.setVisible(false);
          btnModificaTrans.setVisible(false);
          btnEliminarTransp.setVisible(false);
          
        }
      
    }
    
    
    

    
    
     //Metodo para validar el correo 
    
    //Metodo para el correo en usuario
       public  void validarCorreoUsuario (){
        if(!txtCorreoUser.getText().contains("@gmail.com")  && !txtCorreoUser.getText().contains("@hotmail.com") || txtCorreoUser.getText().contains(" ") || txtCorreoUser.getText().contains("..com")){
             lbcorreo.setText("*Correo Invalido*");
        }else{
            lbcorreo.setText("");
        }
        if(lbcorreo.getText().equals("*Correo Invalido*")){
             btnRegistrarUser.setEnabled(false);
        }else{
             btnRegistrarUser.setEnabled(true);
        }
    }
       
       
       //Metodo para el correo en cliente
        public  void validarCorreoCliente (){
            
        if(!txtCorreoCLie.getText().contains("@gmail.com") &&  !txtCorreoCLie.getText().contains("@hotmail.com") || txtCorreoCLie.getText().contains(" ") || txtCorreoCLie.getText().contains("..com")){
             lbCliente.setText("*Correo Invalido*");
        }else{
            lbCliente.setText("");
        }

        if(lbCliente.getText().equals("*Correo Invalido*")){
             btnAgregarClie.setEnabled(false);
        }else{
             btnAgregarClie.setEnabled(true);
        }
    }
        
          //Metodo para el correo en Configuracion
        public  void validarCorreoConfiguracion (){
        if(!txtCorreoConfig.getText().contains("@gmail.com") &&  !txtCorreoConfig.getText().contains("@hotmail.com") || txtCorreoConfig.getText().contains(" ") || txtCorreoConfig.getText().contains("..com")){
             lbConfiguracion.setText("*Correo Invalido*");
        }else{
            lbConfiguracion.setText("");
        }
        if( lbConfiguracion.getText().equals("*Correo Invalido*")){
             ACTUALIZAR.setEnabled(false);
        }else{
             ACTUALIZAR.setEnabled(true);
        }
    }
    
        
        
        
        
    //Metodo para mostrar en la parte de configuracion la informacion almacenada en la tabla tienda 
    
      public void ListarConfig() {
        cof = brDAO.BuscarDatos();
        txtRucConfig.setText("" + cof.getEmpresa_id());
        txtNombreConfig.setText("" + cof.getEmpresa_nombre());
        txtTelefonoConfig.setText("" + cof.getEmpresa_telefono());
        txtDireccionConfig.setText("" + cof.getEmpresa_direccion());
        txtCorreoConfig.setText("" + cof.getEmpresa_email());
        txtCiudadConfig.setText("" + cof.getEmpresa_ciudad());
        txtDptoConfig.setText("" + cof.getEmpresa_dpto());
        txtMensaje.setText("" + cof.getEmpresa_mensaje());
        
       }
     
       

         

       
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopuUsuario = new javax.swing.JPopupMenu();
        JMenuEliminarUser = new javax.swing.JMenuItem();
        JMenuReingresarUser = new javax.swing.JMenuItem();
        jPopuHabilitarUsu = new javax.swing.JPopupMenu();
        JMenuReingresarUsu = new javax.swing.JMenuItem();
        dialogoconfiguracion = new javax.swing.JDialog();
        jLabel45 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelConsultarProducto = new javax.swing.JPanel();
        jLabelConsultarProd = new javax.swing.JLabel();
        jPanelAgregarProducto = new javax.swing.JPanel();
        jLabelAgregarProd = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelConsultarCliente = new javax.swing.JPanel();
        jLabelConsultarClien = new javax.swing.JLabel();
        jPanelAgregarCliente = new javax.swing.JPanel();
        jLabelAgregarClien = new javax.swing.JLabel();
        jPanelCerrarSesion = new javax.swing.JPanel();
        jLabelCerrarSesi = new javax.swing.JLabel();
        jPanelConfiguracion = new javax.swing.JPanel();
        jLabelConfig = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanelUsuario = new javax.swing.JPanel();
        jLabelUsu = new javax.swing.JLabel();
        jPanelVendedor = new javax.swing.JPanel();
        jLabelVendedor = new javax.swing.JLabel();
        jPanelTransportista = new javax.swing.JPanel();
        jLabelTransportista = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LabelUsuri = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtIDUsuario = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelTabConsultarProdu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtApellido1venta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtNombreClienteArticulo = new javax.swing.JTextField();
        txtRucVenta = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProduct = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtCodigoBarras_ModificarArticulo1 = new javax.swing.JTextField();
        txtMarcaPantalla2 = new javax.swing.JTextField();
        txtMarcaAdaptador2 = new javax.swing.JTextField();
        txtMarcaRaton2 = new javax.swing.JTextField();
        btnAgregar_ModificarArticulo1 = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();
        cbxTienda_ConsultarArticulo = new javax.swing.JComboBox();
        txtTecPantalla2 = new javax.swing.JTextField();
        txtMarcaTeclado2 = new javax.swing.JTextField();
        txtNumeroActas2 = new javax.swing.JTextField();
        txtMarcaDiademas2 = new javax.swing.JTextField();
        txtFecha2 = new javax.swing.JTextField();
        txtMarcaCamara2 = new javax.swing.JTextField();
        txtMarcaTorre2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTecTorre2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnGenerarArticulo = new javax.swing.JButton();
        txtTecPantalla3 = new javax.swing.JTextField();
        txtTecPantalla4 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtIdCV = new javax.swing.JTextField();
        txtTelefonoventa = new javax.swing.JTextField();
        txtDireccionventa = new javax.swing.JTextField();
        txtCuentaCli = new javax.swing.JTextField();
        txtpisoCliente = new javax.swing.JTextField();
        txtNumeroIdentificacion = new javax.swing.JTextField();
        txtCodigoEmp = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txtNombreEmp = new javax.swing.JTextField();
        txtCargoEmp = new javax.swing.JTextField();
        txtDepartamentoEmp = new javax.swing.JTextField();
        txtNoCajaTransp = new javax.swing.JTextField();
        txtTransportadorTransp = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        txtBuscarCodigoProd = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        txtCodigoIDemp = new javax.swing.JTextField();
        txtCodigoIDTransp = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        txtNombreTransp = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        txtCodigoTransp = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        txtNombrPersoAuto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoPersoAuto = new javax.swing.JTextField();
        txtCargoPersoAuto = new javax.swing.JTextField();
        jPanelTabAgregarProducto = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtCodigoBarra_AgregarNuevoPro = new javax.swing.JTextField();
        txtMarcaPantalla = new javax.swing.JTextField();
        txtTecPantalla = new javax.swing.JTextField();
        txtTecTorre = new javax.swing.JTextField();
        btnAgregar_AgregarNuevoPro = new javax.swing.JButton();
        cbxCodTiendaArticulo = new javax.swing.JComboBox<>();
        JLabelCodigoArticulo = new javax.swing.JLabel();
        txtMarcaTorre = new javax.swing.JTextField();
        txtMarcaRaton = new javax.swing.JTextField();
        txtMarcaCamara = new javax.swing.JTextField();
        txtMarcaTeclado = new javax.swing.JTextField();
        txtMarcaDiademas = new javax.swing.JTextField();
        txtMarcaAdaptador = new javax.swing.JTextField();
        txtNumeroActa = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtAgreTecPantalla3 = new javax.swing.JTextField();
        txtAgreTecPantalla2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txtNombreModifi = new javax.swing.JTextField();
        txtIDModiClie1 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txtApellidoModifi = new javax.swing.JTextField();
        txtTipoClienteModifi = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        txtTipoCedulaModifi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        txtCorreoModiClie1 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        txtDireccionModiClie1 = new javax.swing.JTextField();
        btnModificarModifi = new javax.swing.JButton();
        btnEliminarClient = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        cbxGeneroModifi = new javax.swing.JComboBox<>();
        jLabel98 = new javax.swing.JLabel();
        txtTelefonoModiClie1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableConsultarClienteModifi = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        txtNombreClie = new javax.swing.JTextField();
        txtIDClienteClie = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtApellidoClie = new javax.swing.JTextField();
        txtTipoCedulaCli = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtCorreoCLie = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtDireccionClie = new javax.swing.JTextField();
        btnAgregarClie = new javax.swing.JButton();
        lbCliente = new javax.swing.JLabel();
        txt_TelefonoCLIE = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        cbxTipoClienteCLI = new javax.swing.JComboBox<>();
        cbxGeneroCliente = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtUsuarioUser = new javax.swing.JTextField();
        txtCorreoUser = new javax.swing.JTextField();
        txtContraseñaUser = new javax.swing.JPasswordField();
        cbxtipo_cli_idUser = new javax.swing.JComboBox<>();
        txtIDUser = new javax.swing.JTextField();
        txtBuscarUser = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        btnNuevoUser = new javax.swing.JButton();
        btnRegistrarUser = new javax.swing.JButton();
        btnModificarUser = new javax.swing.JButton();
        lbcorreo = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableUser = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        ACTUALIZAR = new javax.swing.JButton();
        txtMensaje = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtCorreoConfig = new javax.swing.JTextField();
        txtCiudadConfig = new javax.swing.JTextField();
        txtDptoConfig = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        txtDireccionConfig = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtTelefonoConfig = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        txtNombreConfig = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtRucConfig = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        lbConfiguracion = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        txtUsuarioInabilitado = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        TableUserEliminado = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtNombreEmpleado1 = new javax.swing.JTextField();
        txtIDEmpleado1 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtDepartamentoEmpl1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cbxCargoEmplea1 = new javax.swing.JComboBox<>();
        btnAgregarEmpl = new javax.swing.JButton();
        btnEliminarEmpl1 = new javax.swing.JButton();
        btnModificarEmpl1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableEmpleado = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txtTransportadora = new javax.swing.JTextField();
        txtIDTransportista = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        txtNoCaja = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnAgregarTransp = new javax.swing.JButton();
        btnEliminarTransp = new javax.swing.JButton();
        txtNombreComple = new javax.swing.JTextField();
        btnModificaTrans = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableTransportista = new javax.swing.JTable();

        JMenuEliminarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        JMenuEliminarUser.setText("Eliminar");
        jPopuUsuario.add(JMenuEliminarUser);

        JMenuReingresarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/repetir.png"))); // NOI18N
        JMenuReingresarUser.setText("Reingresar");
        JMenuReingresarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuReingresarUserActionPerformed(evt);
            }
        });
        jPopuUsuario.add(JMenuReingresarUser);

        JMenuReingresarUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/repetir.png"))); // NOI18N
        JMenuReingresarUsu.setText("ReingresarUsuario");
        JMenuReingresarUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuReingresarUsuActionPerformed(evt);
            }
        });
        jPopuHabilitarUsu.add(JMenuReingresarUsu);

        jLabel45.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel45.setText("IVA %");

        jLabel60.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel60.setText("DESCUENTO %");

        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaKeyTyped(evt);
            }
        });

        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jButton2.setText("ACEPTAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogoconfiguracionLayout = new javax.swing.GroupLayout(dialogoconfiguracion.getContentPane());
        dialogoconfiguracion.getContentPane().setLayout(dialogoconfiguracionLayout);
        dialogoconfiguracionLayout.setHorizontalGroup(
            dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogoconfiguracionLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel60))
                .addGap(28, 28, 28)
                .addGroup(dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogoconfiguracionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        dialogoconfiguracionLayout.setVerticalGroup(
            dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogoconfiguracionLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogoconfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(66, 135, 230));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.orange);
        jLabel3.setText("GESTIÓN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanelConsultarProducto.setBackground(new java.awt.Color(66, 135, 230));
        jPanelConsultarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelConsultarProd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelConsultarProd.setForeground(new java.awt.Color(0, 0, 51));
        jLabelConsultarProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConsultarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/3.png"))); // NOI18N
        jLabelConsultarProd.setText("CONSULTAR ARTICULO");

        javax.swing.GroupLayout jPanelConsultarProductoLayout = new javax.swing.GroupLayout(jPanelConsultarProducto);
        jPanelConsultarProducto.setLayout(jPanelConsultarProductoLayout);
        jPanelConsultarProductoLayout.setHorizontalGroup(
            jPanelConsultarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConsultarProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanelConsultarProductoLayout.setVerticalGroup(
            jPanelConsultarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConsultarProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelConsultarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 40, 210, 30));

        jPanelAgregarProducto.setBackground(new java.awt.Color(66, 135, 230));
        jPanelAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelAgregarProd.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAgregarProd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelAgregarProd.setForeground(new java.awt.Color(0, 0, 51));
        jLabelAgregarProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAgregarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/4.png"))); // NOI18N
        jLabelAgregarProd.setText("AGREGAR ARTICULO");
        jLabelAgregarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAgregarProdMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgregarProductoLayout = new javax.swing.GroupLayout(jPanelAgregarProducto);
        jPanelAgregarProducto.setLayout(jPanelAgregarProductoLayout);
        jPanelAgregarProductoLayout.setHorizontalGroup(
            jPanelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgregarProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanelAgregarProductoLayout.setVerticalGroup(
            jPanelAgregarProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgregarProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 30));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.orange);
        jLabel4.setText("PERSONA");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jPanelConsultarCliente.setBackground(new java.awt.Color(66, 135, 230));
        jPanelConsultarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelConsultarClien.setBackground(new java.awt.Color(255, 255, 255));
        jLabelConsultarClien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelConsultarClien.setForeground(new java.awt.Color(0, 0, 51));
        jLabelConsultarClien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConsultarClien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/6.png"))); // NOI18N
        jLabelConsultarClien.setText("CONSULTAR PERSONA");

        javax.swing.GroupLayout jPanelConsultarClienteLayout = new javax.swing.GroupLayout(jPanelConsultarCliente);
        jPanelConsultarCliente.setLayout(jPanelConsultarClienteLayout);
        jPanelConsultarClienteLayout.setHorizontalGroup(
            jPanelConsultarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConsultarClien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelConsultarClienteLayout.setVerticalGroup(
            jPanelConsultarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConsultarClien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelConsultarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, 30));

        jPanelAgregarCliente.setBackground(new java.awt.Color(66, 135, 230));
        jPanelAgregarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelAgregarClien.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAgregarClien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelAgregarClien.setForeground(new java.awt.Color(0, 0, 51));
        jLabelAgregarClien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAgregarClien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/7.png"))); // NOI18N
        jLabelAgregarClien.setText("AGREGAR PERSONA");

        javax.swing.GroupLayout jPanelAgregarClienteLayout = new javax.swing.GroupLayout(jPanelAgregarCliente);
        jPanelAgregarCliente.setLayout(jPanelAgregarClienteLayout);
        jPanelAgregarClienteLayout.setHorizontalGroup(
            jPanelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgregarClien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanelAgregarClienteLayout.setVerticalGroup(
            jPanelAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgregarClien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, 30));

        jPanelCerrarSesion.setBackground(new java.awt.Color(66, 135, 230));
        jPanelCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelCerrarSesi.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCerrarSesi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelCerrarSesi.setForeground(new java.awt.Color(0, 0, 51));
        jLabelCerrarSesi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCerrarSesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/9.png"))); // NOI18N
        jLabelCerrarSesi.setText("CERRAR SESIÓN ");
        jLabelCerrarSesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCerrarSesiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelCerrarSesionLayout = new javax.swing.GroupLayout(jPanelCerrarSesion);
        jPanelCerrarSesion.setLayout(jPanelCerrarSesionLayout);
        jPanelCerrarSesionLayout.setHorizontalGroup(
            jPanelCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCerrarSesi, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        jPanelCerrarSesionLayout.setVerticalGroup(
            jPanelCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCerrarSesi, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, -1, 30));

        jPanelConfiguracion.setBackground(new java.awt.Color(66, 135, 230));
        jPanelConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelConfig.setBackground(new java.awt.Color(255, 255, 255));
        jLabelConfig.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelConfig.setForeground(new java.awt.Color(0, 0, 51));
        jLabelConfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/20.png"))); // NOI18N
        jLabelConfig.setText("CONFIGURACIÓN");
        jLabelConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelConfigMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfiguracionLayout = new javax.swing.GroupLayout(jPanelConfiguracion);
        jPanelConfiguracion.setLayout(jPanelConfiguracionLayout);
        jPanelConfiguracionLayout.setHorizontalGroup(
            jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        jPanelConfiguracionLayout.setVerticalGroup(
            jPanelConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(244, 162, 97));
        jLabel5.setText("OPCIONES");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        jPanelUsuario.setBackground(new java.awt.Color(66, 135, 230));
        jPanelUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabelUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelUsu.setForeground(new java.awt.Color(0, 0, 51));
        jLabelUsu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/8.png"))); // NOI18N
        jLabelUsu.setText("USUARIOS");
        jLabelUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelUsuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addComponent(jLabelUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 200, 30));

        jPanelVendedor.setBackground(new java.awt.Color(66, 135, 230));

        jLabelVendedor.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelVendedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/7.png"))); // NOI18N
        jLabelVendedor.setText("EMPLEADO");
        jLabelVendedor.setToolTipText("");
        jLabelVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelVendedorLayout = new javax.swing.GroupLayout(jPanelVendedor);
        jPanelVendedor.setLayout(jPanelVendedorLayout);
        jPanelVendedorLayout.setHorizontalGroup(
            jPanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelVendedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanelVendedorLayout.setVerticalGroup(
            jPanelVendedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelVendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 210, 30));

        jPanelTransportista.setBackground(new java.awt.Color(66, 135, 230));

        jLabelTransportista.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabelTransportista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTransportista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/7.png"))); // NOI18N
        jLabelTransportista.setText("TRANSPORTISTA");
        jLabelTransportista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelTransportistaLayout = new javax.swing.GroupLayout(jPanelTransportista);
        jPanelTransportista.setLayout(jPanelTransportistaLayout);
        jPanelTransportistaLayout.setHorizontalGroup(
            jPanelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTransportista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanelTransportistaLayout.setVerticalGroup(
            jPanelTransportistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTransportista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelTransportista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 220, 560));

        jPanel2.setBackground(new java.awt.Color(66, 135, 230));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Storalix_160x160(2).png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 310, 110));

        LabelUsuri.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        LabelUsuri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(LabelUsuri, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, 20));

        jLabel61.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 51));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Bienvenido(a):");
        jPanel2.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 160));

        jPanel3.setBackground(new java.awt.Color(66, 135, 230));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtIDUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 20, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 50));

        jPanelTabConsultarProdu.setBackground(new java.awt.Color(66, 61, 210));
        jPanelTabConsultarProdu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtApellido1venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellido1ventaKeyPressed(evt);
            }
        });
        jPanel4.add(txtApellido1venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 180, 30));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("APELLIDO DEL CLIENTE");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel40.setText("NOMBRE DEL CLIENTE");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        txtNombreClienteArticulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNombreClienteArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreClienteArticuloKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteArticuloKeyTyped(evt);
            }
        });
        jPanel4.add(txtNombreClienteArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 160, 30));

        txtRucVenta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtRucVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucVentaKeyTyped(evt);
            }
        });
        jPanel4.add(txtRucVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 116, 30));

        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel39.setText("CODIGO");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanelTabConsultarProdu.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 520, 60));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        TableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Pantalla", "Tec-Pantalla", "tec-Pantalla2", "tec-Pantalla3", "Tec-Torre", "Torre", "Raton", "Teclado", "Diadema", "Camara", "Adaptador", "Fecha", "Acta", "Tienda"
            }
        ));
        jScrollPane1.setViewportView(TableProduct);

        jPanelTabConsultarProdu.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 1070, 100));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CONSULTAR ARTICULO");
        jPanelTabConsultarProdu.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jPanel26.setBackground(new java.awt.Color(102, 204, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel47.setText("MODIFICAR ARTICULO ");
        jPanel26.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 20));

        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel48.setText("CODIGO DE ARTICULO ");
        jPanel26.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        jLabel81.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel81.setText("CAMARA");
        jPanel26.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel83.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel83.setText("Pantalla");
        jPanel26.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jLabel84.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel84.setText("DIADEMAS");
        jPanel26.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, -1, -1));

        jLabel85.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel85.setText("Acta");
        jPanel26.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, -1, -1));

        jLabel86.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel86.setText("TORRE");
        jPanel26.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jLabel87.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel87.setText("RATON");
        jPanel26.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jLabel88.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel88.setText("TECLADO");
        jPanel26.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, -1, -1));

        jLabel89.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel89.setText("TIENDA ");
        jPanel26.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        txt.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txt.setText("FECHA ");
        jPanel26.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        jLabel91.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel91.setText("ADAPTADOR");
        jPanel26.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        txtCodigoBarras_ModificarArticulo1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCodigoBarras_ModificarArticulo1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                txtCodigoBarras_ModificarArticulo1AncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        txtCodigoBarras_ModificarArticulo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarras_ModificarArticulo1KeyTyped(evt);
            }
        });
        jPanel26.add(txtCodigoBarras_ModificarArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, 30));

        txtMarcaPantalla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaPantalla2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaPantalla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 140, 30));

        txtMarcaAdaptador2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtMarcaAdaptador2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaAdaptador2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaAdaptador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 140, 30));

        txtMarcaRaton2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtMarcaRaton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaRaton2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaRaton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 140, 30));

        btnAgregar_ModificarArticulo1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregar_ModificarArticulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnAgregar_ModificarArticulo1.setText("ACTUALIZAR");
        btnAgregar_ModificarArticulo1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel26.add(btnAgregar_ModificarArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 170, 40));

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnExcel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        jPanel26.add(btnExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 170, 40));

        btnEliminarProd.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarProd.setText("ELIMINAR");
        btnEliminarProd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });
        jPanel26.add(btnEliminarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 170, 40));

        cbxTienda_ConsultarArticulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxTienda_ConsultarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTienda_ConsultarArticuloActionPerformed(evt);
            }
        });
        jPanel26.add(cbxTienda_ConsultarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 140, 30));

        txtTecPantalla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecPantalla2KeyTyped(evt);
            }
        });
        jPanel26.add(txtTecPantalla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 140, 30));

        txtMarcaTeclado2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaTeclado2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaTeclado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 140, 30));

        txtNumeroActas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroActas2KeyTyped(evt);
            }
        });
        jPanel26.add(txtNumeroActas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 140, 30));

        txtMarcaDiademas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaDiademas2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaDiademas2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 140, 30));
        jPanel26.add(txtFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 140, 30));

        txtMarcaCamara2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaCamara2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaCamara2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 140, 30));

        txtMarcaTorre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaTorre2KeyTyped(evt);
            }
        });
        jPanel26.add(txtMarcaTorre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 140, 30));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("TEC-PANTALLA  ");
        jPanel26.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        txtTecTorre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecTorre2KeyTyped(evt);
            }
        });
        jPanel26.add(txtTecTorre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, 30));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("TEC-TORRE");
        jPanel26.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        btnGenerarArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btnGenerarArticulo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGenerarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarArticuloActionPerformed(evt);
            }
        });
        jPanel26.add(btnGenerarArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 230, 170, 40));

        txtTecPantalla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecPantalla3KeyTyped(evt);
            }
        });
        jPanel26.add(txtTecPantalla3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 140, 30));

        txtTecPantalla4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecPantalla4KeyTyped(evt);
            }
        });
        jPanel26.add(txtTecPantalla4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 140, 30));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setText("TEC-PANTALLA2");
        jPanel26.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel31.setText("TEC-PANTALLA3");
        jPanel26.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, 20));

        jPanelTabConsultarProdu.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 1070, 310));

        jPanel6.setBackground(new java.awt.Color(102, 204, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(txtIdCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 26, -1, -1));
        jPanel6.add(txtTelefonoventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 25, -1, -1));
        jPanel6.add(txtDireccionventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 25, -1, -1));
        jPanel6.add(txtCuentaCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 25, 10, -1));
        jPanel6.add(txtpisoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 25, 10, -1));
        jPanel6.add(txtNumeroIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        txtCodigoEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoEmpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoEmpKeyTyped(evt);
            }
        });
        jPanel6.add(txtCodigoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 30));

        jLabel42.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel42.setText("CODIGO EMPLEADO");
        jPanel6.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel90.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel90.setText("NOMBRE EMPLEADO");
        jPanel6.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        txtNombreEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreEmpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpKeyTyped(evt);
            }
        });
        jPanel6.add(txtNombreEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 180, 30));

        jPanelTabConsultarProdu.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 390, 80));
        jPanelTabConsultarProdu.add(txtCargoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));
        jPanelTabConsultarProdu.add(txtDepartamentoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, -1, -1));
        jPanelTabConsultarProdu.add(txtNoCajaTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, -1));
        jPanelTabConsultarProdu.add(txtTransportadorTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, -1, -1));

        jPanel16.setBackground(new java.awt.Color(102, 204, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel101.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel101.setText("CODIGO DE ARTICULO");
        jPanel16.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 30));

        txtBuscarCodigoProd.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtBuscarCodigoProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarCodigoProdKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarCodigoProdKeyTyped(evt);
            }
        });
        jPanel16.add(txtBuscarCodigoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 10, 240, 30));

        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, 30));

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel16.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 50, 30));

        jPanelTabConsultarProdu.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 490, 50));
        jPanelTabConsultarProdu.add(txtCodigoIDemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));
        jPanelTabConsultarProdu.add(txtCodigoIDTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        jPanel24.setBackground(new java.awt.Color(102, 204, 255));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreTransp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreTranspKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreTranspKeyTyped(evt);
            }
        });
        jPanel24.add(txtNombreTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 180, 30));

        jLabel97.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel97.setText("NOMBRE TRANSPORTADOR");
        jPanel24.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel96.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel96.setText("CODIGO TRANSPORTADOR");
        jPanel24.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtCodigoTransp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoTranspKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoTranspKeyTyped(evt);
            }
        });
        jPanel24.add(txtCodigoTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 30));

        jPanelTabConsultarProdu.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 420, 80));

        jPanel25.setBackground(new java.awt.Color(102, 204, 255));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombrPersoAuto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombrPersoAutoKeyPressed(evt);
            }
        });
        jPanel25.add(txtNombrPersoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 30));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("PERSONA QUE AUTORIZA");
        jPanel25.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));
        jPanel25.add(txtCodigoPersoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 31));
        jPanel25.add(txtCargoPersoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jPanelTabConsultarProdu.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, 240, 80));

        jTabbedPane1.addTab("Consultar Producto", jPanelTabConsultarProdu);

        jPanelTabAgregarProducto.setBackground(new java.awt.Color(66, 61, 210));
        jPanelTabAgregarProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(102, 204, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setText("CODIGO DE ARTICULO ");
        jPanel15.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setText("RATON");
        jPanel15.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, -1));

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setText("PANTALLA");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel22.setText("CAMARA");
        jPanel15.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, -1, -1));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel23.setText("TECLADO");
        jPanel15.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, -1, -1));

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setText("TEC-PANTALLA  ");
        jPanel15.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setText("TEC-TORRE");
        jPanel15.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel26.setText("TORRE");
        jPanel15.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setText("TIENDA ");
        jPanel15.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, -1));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setText("ADAPTADOR");
        jPanel15.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setText("DIADEMAS");
        jPanel15.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, 20));

        txtCodigoBarra_AgregarNuevoPro.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCodigoBarra_AgregarNuevoPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarra_AgregarNuevoProKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarra_AgregarNuevoProKeyTyped(evt);
            }
        });
        jPanel15.add(txtCodigoBarra_AgregarNuevoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 140, 30));

        txtMarcaPantalla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtMarcaPantalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaPantallaKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 140, 30));

        txtTecPantalla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtTecPantalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecPantallaKeyTyped(evt);
            }
        });
        jPanel15.add(txtTecPantalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 140, 30));

        txtTecTorre.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtTecTorre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTecTorreKeyTyped(evt);
            }
        });
        jPanel15.add(txtTecTorre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 140, 30));

        btnAgregar_AgregarNuevoPro.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregar_AgregarNuevoPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnAgregar_AgregarNuevoPro.setText("AGREGAR ");
        jPanel15.add(btnAgregar_AgregarNuevoPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, 210, 40));

        cbxCodTiendaArticulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCodTiendaArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCodTiendaArticuloActionPerformed(evt);
            }
        });
        jPanel15.add(cbxCodTiendaArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 140, 30));

        JLabelCodigoArticulo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        JLabelCodigoArticulo.setForeground(new java.awt.Color(255, 0, 0));
        jPanel15.add(JLabelCodigoArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        txtMarcaTorre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaTorreKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaTorre, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 140, 30));

        txtMarcaRaton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaRatonKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaRaton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 140, 30));

        txtMarcaCamara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaCamaraKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaCamara, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 140, 30));

        txtMarcaTeclado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaTecladoKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaTeclado, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, 140, 30));

        txtMarcaDiademas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaDiademasKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaDiademas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 140, 30));

        txtMarcaAdaptador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaAdaptadorKeyTyped(evt);
            }
        });
        jPanel15.add(txtMarcaAdaptador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 140, 30));

        txtNumeroActa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroActaKeyTyped(evt);
            }
        });
        jPanel15.add(txtNumeroActa, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 140, 30));

        txtFecha.setText("YYYY-MES-DIA");
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaFocusLost(evt);
            }
        });
        jPanel15.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 140, 30));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("ACTA");
        jPanel15.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("FECHA ");
        jPanel15.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, -1, -1));

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setText("TEC-PANTALLA2");
        jPanel15.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel33.setText("TEC-PANTALLA3");
        jPanel15.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        txtAgreTecPantalla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgreTecPantalla3KeyTyped(evt);
            }
        });
        jPanel15.add(txtAgreTecPantalla3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 140, 30));

        txtAgreTecPantalla2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgreTecPantalla2ActionPerformed(evt);
            }
        });
        txtAgreTecPantalla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAgreTecPantalla2KeyTyped(evt);
            }
        });
        jPanel15.add(txtAgreTecPantalla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 140, 30));

        jPanelTabAgregarProducto.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 990, 430));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("AGREGAR UN NUEVO ARTICULO ");
        jPanelTabAgregarProducto.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, 30));

        jTabbedPane1.addTab("Agregar Prodcuto", jPanelTabAgregarProducto);

        jPanel7.setBackground(new java.awt.Color(66, 61, 210));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("CONSULTAR PERSONA");
        jPanel7.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jPanel27.setBackground(new java.awt.Color(102, 204, 255));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel93.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel93.setText("ID CLIENTE");
        jPanel27.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel94.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel94.setText("NOMBRE");
        jPanel27.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        txtNombreModifi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNombreModifi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreModifiKeyTyped(evt);
            }
        });
        jPanel27.add(txtNombreModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 30));

        txtIDModiClie1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtIDModiClie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDModiClie1KeyTyped(evt);
            }
        });
        jPanel27.add(txtIDModiClie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 30));

        jLabel95.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel95.setText("APELLIDO");
        jPanel27.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        txtApellidoModifi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtApellidoModifi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoModifiKeyTyped(evt);
            }
        });
        jPanel27.add(txtApellidoModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 130, 30));

        txtTipoClienteModifi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTipoClienteModifi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "14", "16", "19", "20" }));
        jPanel27.add(txtTipoClienteModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 80, 30));

        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel27.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, 30));

        jLabel53.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel53.setText("PISO");
        jPanel27.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        txtTipoCedulaModifi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoCedulaModifiKeyTyped(evt);
            }
        });
        jPanel27.add(txtTipoCedulaModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 180, 30));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("# IDENTIFICACION");
        jPanel27.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, 30));

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel27.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 40, 30));

        jPanel7.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 590, 170));

        jPanel29.setBackground(new java.awt.Color(102, 204, 255));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel99.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel99.setText("CORREO ");
        jPanel29.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        txtCorreoModiClie1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel29.add(txtCorreoModiClie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 360, 30));

        jLabel100.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel100.setText("DIRECCIÓN ");
        jPanel29.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, -1));

        txtDireccionModiClie1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel29.add(txtDireccionModiClie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 370, 30));

        btnModificarModifi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificarModifi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnModificarModifi.setText("ACTUALIZAR");
        btnModificarModifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarModifiActionPerformed(evt);
            }
        });
        jPanel29.add(btnModificarModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, -1, 40));

        btnEliminarClient.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminarClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarClient.setText("ELIMINAR");
        btnEliminarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClientActionPerformed(evt);
            }
        });
        jPanel29.add(btnEliminarClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, -1, 40));

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel44.setText("CUENTA");
        jPanel29.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        cbxGeneroModifi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxGeneroModifi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Netuno", "FreesPack", "Stord", "Eshipping", "Us Foods", "Gampac", "Standar Login", "NTG", "Source alliance ", "Kinsgs gate", "OTR", "Armstrong", " " }));
        jPanel29.add(cbxGeneroModifi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 30));

        jLabel98.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel98.setText(" TELÉFONO");
        jPanel29.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        txtTelefonoModiClie1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtTelefonoModiClie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoModiClie1ActionPerformed(evt);
            }
        });
        txtTelefonoModiClie1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoModiClie1KeyTyped(evt);
            }
        });
        jPanel29.add(txtTelefonoModiClie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 220, 30));

        jPanel7.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 1070, 160));

        TableConsultarClienteModifi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        TableConsultarClienteModifi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLI_TIPO_ID", "NOMBRE", "APELLIDO", "PISO", "CUENTA", "TELEFONO", "CORREO", "DIRECCION"
            }
        ));
        jScrollPane2.setViewportView(TableConsultarClienteModifi);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1090, 210));

        jTabbedPane1.addTab("Consultar Cliente", jPanel7);

        jPanel8.setBackground(new java.awt.Color(66, 61, 210));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setBackground(new java.awt.Color(255, 255, 255));
        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("AGREGAR PERSONA");
        jPanel8.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        jPanel18.setBackground(new java.awt.Color(102, 204, 255));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("ID CLIENTE");
        jPanel18.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("NOMBRE");
        jPanel18.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        txtNombreClie.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNombreClie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClieKeyTyped(evt);
            }
        });
        jPanel18.add(txtNombreClie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 190, 30));

        txtIDClienteClie.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtIDClienteClie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDClienteClieKeyTyped(evt);
            }
        });
        jPanel18.add(txtIDClienteClie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, 30));

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("APELLIDO");
        jPanel18.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        txtApellidoClie.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtApellidoClie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoClieKeyTyped(evt);
            }
        });
        jPanel18.add(txtApellidoClie, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 200, 30));

        txtTipoCedulaCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoCedulaCliKeyTyped(evt);
            }
        });
        jPanel18.add(txtTipoCedulaCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 150, 30));

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("# IDENTIFICACION");
        jPanel18.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jPanel8.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 460, 210));

        jPanel20.setBackground(new java.awt.Color(102, 204, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel56.setBackground(new java.awt.Color(255, 255, 255));
        jLabel56.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("CORREO ");
        jPanel20.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCorreoCLie.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCorreoCLie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoCLieKeyReleased(evt);
            }
        });
        jPanel20.add(txtCorreoCLie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 320, 30));

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("DIRECCIÓN ");
        jPanel20.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtDireccionClie.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel20.add(txtDireccionClie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 320, 30));

        btnAgregarClie.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregarClie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnAgregarClie.setText("AGREGAR ");
        btnAgregarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClieActionPerformed(evt);
            }
        });
        jPanel20.add(btnAgregarClie, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, 40));

        lbCliente.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbCliente.setForeground(new java.awt.Color(255, 0, 0));
        jPanel20.add(lbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        txt_TelefonoCLIE.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt_TelefonoCLIE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TelefonoCLIEActionPerformed(evt);
            }
        });
        txt_TelefonoCLIE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TelefonoCLIEKeyTyped(evt);
            }
        });
        jPanel20.add(txt_TelefonoCLIE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 190, 30));

        jLabel55.setBackground(new java.awt.Color(255, 255, 255));
        jLabel55.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText(" TELÉFONO");
        jPanel20.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 30));

        cbxTipoClienteCLI.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxTipoClienteCLI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "14", "16", "19", "20" }));
        jPanel20.add(cbxTipoClienteCLI, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 100, 30));

        cbxGeneroCliente.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxGeneroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Netuno", "FreesPack", "Stord", "Eshipping", "Us Foods", "Gampac", "Standar Login", "NTG", "Source alliance ", "Kinsgs gate", "OTR", "Armstrong", " " }));
        jPanel20.add(cbxGeneroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 150, 30));

        jLabel76.setBackground(new java.awt.Color(255, 255, 255));
        jLabel76.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Cuenta");
        jPanel20.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Piso");
        jPanel20.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        jPanel8.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 790, 200));

        jTabbedPane1.addTab("Agregar Cliente", jPanel8);

        jPanel10.setBackground(new java.awt.Color(66, 61, 210));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(102, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO USUARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel71.setText("NOMBRE ");
        jPanel13.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 30));

        jLabel72.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel72.setText("CORREO ");
        jPanel13.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));

        jLabel73.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel73.setText("CONTRASEÑA");
        jPanel13.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 279, -1, 20));

        jLabel74.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel74.setText("ROL");
        jPanel13.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, 30));

        txtUsuarioUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtUsuarioUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioUserKeyTyped(evt);
            }
        });
        jPanel13.add(txtUsuarioUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 180, 30));

        txtCorreoUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCorreoUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoUserKeyReleased(evt);
            }
        });
        jPanel13.add(txtCorreoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 180, 30));

        txtContraseñaUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtContraseñaUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaUserKeyTyped(evt);
            }
        });
        jPanel13.add(txtContraseñaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 180, 30));

        cbxtipo_cli_idUser.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cbxtipo_cli_idUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMI", "USER" }));
        jPanel13.add(cbxtipo_cli_idUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 110, 30));
        jPanel13.add(txtIDUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, 150, -1));

        txtBuscarUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtBuscarUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarUserKeyTyped(evt);
            }
        });
        jPanel13.add(txtBuscarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 180, 30));

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel13.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, 30));

        btnNuevoUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnNuevoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btnNuevoUser.setText("NUEVO");
        jPanel13.add(btnNuevoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 130, 35));

        btnRegistrarUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnRegistrarUser.setText("REGISTRAR");
        btnRegistrarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarUserMouseClicked(evt);
            }
        });
        jPanel13.add(btnRegistrarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 150, 35));

        btnModificarUser.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnModificarUser.setText("ACTUALIZAR");
        btnModificarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUserActionPerformed(evt);
            }
        });
        jPanel13.add(btnModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, -1, 35));

        lbcorreo.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbcorreo.setForeground(new java.awt.Color(204, 0, 51));
        jPanel13.add(lbcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        jPanel10.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, 530));

        TableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CORREO", "ROL", "Estado"
            }
        ));
        TableUser.setComponentPopupMenu(jPopuUsuario);
        TableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUserMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableUser);

        jPanel10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 710, 530));

        jTabbedPane1.addTab("Consultar Usuario", jPanel10);

        jPanel11.setBackground(new java.awt.Color(66, 61, 210));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(102, 204, 255));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ACTUALIZAR.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ACTUALIZAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });
        jPanel21.add(ACTUALIZAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 580, -1, 35));

        txtMensaje.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 520, 30));

        jLabel64.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel64.setText("MENSAJE");
        jPanel21.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));

        txtCorreoConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCorreoConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoConfigKeyReleased(evt);
            }
        });
        jPanel21.add(txtCorreoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 240, 30));

        txtCiudadConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtCiudadConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 150, 30));

        txtDptoConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtDptoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 220, 30));

        jLabel65.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel65.setText("CORREO ");
        jPanel21.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 100, -1));

        jLabel66.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel66.setText("CIUDAD");
        jPanel21.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 68, -1));

        jLabel67.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel67.setText("DPTO");
        jPanel21.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        txtDireccionConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtDireccionConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 430, 30));

        jLabel68.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel68.setText("DIRECCIÓN");
        jPanel21.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, -1));

        txtTelefonoConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtTelefonoConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoConfigKeyTyped(evt);
            }
        });
        jPanel21.add(txtTelefonoConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 218, 30));

        jLabel69.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel69.setText("TELÉFONO");
        jPanel21.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 100, -1));

        txtNombreConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtNombreConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 220, 30));

        jLabel70.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel70.setText("NOMBRE");
        jPanel21.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 90, -1));

        txtRucConfig.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel21.add(txtRucConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 30));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setText("ID");
        jPanel21.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 60, -1));

        lbConfiguracion.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbConfiguracion.setForeground(new java.awt.Color(255, 0, 0));
        jPanel21.add(lbConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Leansolution2222433.jpg"))); // NOI18N
        jPanel21.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 140));

        jPanel11.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 570, 620));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lean-solutions-group-23.jpg"))); // NOI18N
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, 340));

        jPanel14.setBackground(new java.awt.Color(102, 204, 255));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("BIENVENIDOS A ");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 153, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("LEAN SOLUTIONS ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE))
        );

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 500, -1));

        jTabbedPane1.addTab("Configuracion", jPanel11);

        jPanel17.setBackground(new java.awt.Color(66, 61, 210));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(102, 204, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO USUARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12))); // NOI18N
        jPanel22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel22.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 30));

        txtUsuarioInabilitado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtUsuarioInabilitado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioInabilitadoKeyTyped(evt);
            }
        });
        jPanel22.add(txtUsuarioInabilitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 200, 30));

        jPanel17.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 460, 120));

        TableUserEliminado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CORREO", "ROL", "Estado", "FECHA"
            }
        ));
        TableUserEliminado.setComponentPopupMenu(jPopuHabilitarUsu);
        TableUserEliminado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableUserEliminadoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TableUserEliminado);

        jPanel17.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1070, 360));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab9", jPanel9);

        jPanel5.setBackground(new java.awt.Color(66, 61, 210));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("AGREGAR EMPLEADO");
        jPanel5.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jPanel19.setBackground(new java.awt.Color(102, 204, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setBackground(new java.awt.Color(255, 255, 255));
        jLabel59.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("ID EMPLEADO");
        jPanel19.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel62.setBackground(new java.awt.Color(255, 255, 255));
        jLabel62.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("NOMBRE COMPLETO");
        jPanel19.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtNombreEmpleado1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNombreEmpleado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpleado1KeyTyped(evt);
            }
        });
        jPanel19.add(txtNombreEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 300, 30));

        txtIDEmpleado1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtIDEmpleado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDEmpleado1KeyTyped(evt);
            }
        });
        jPanel19.add(txtIDEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, 30));

        jLabel63.setBackground(new java.awt.Color(255, 255, 255));
        jLabel63.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("DEPARTAMENTO");
        jPanel19.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        txtDepartamentoEmpl1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel19.add(txtDepartamentoEmpl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 300, 30));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("CARGO");
        jPanel19.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        cbxCargoEmplea1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxCargoEmplea1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT SUPPORT", "OPERATIONS MANAGER", " " }));
        jPanel19.add(cbxCargoEmplea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 180, 30));

        btnAgregarEmpl.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregarEmpl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnAgregarEmpl.setText("AGREGAR ");
        btnAgregarEmpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmplActionPerformed(evt);
            }
        });
        jPanel19.add(btnAgregarEmpl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 150, 40));

        btnEliminarEmpl1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminarEmpl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarEmpl1.setText("ELIMINAR");
        btnEliminarEmpl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpl1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnEliminarEmpl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, 160, 40));

        btnModificarEmpl1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificarEmpl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnModificarEmpl1.setText("ACTUALIZAR");
        btnModificarEmpl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpl1ActionPerformed(evt);
            }
        });
        jPanel19.add(btnModificarEmpl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 160, 40));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel19.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 350, 540));

        TableEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID VENDEDOR", "NOMBRE COMPLETO", "CARGO", "DEPARTAMENTO"
            }
        ));
        jScrollPane5.setViewportView(TableEmpleado);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 640, 550));

        jTabbedPane1.addTab("tab8", jPanel5);

        jPanel12.setBackground(new java.awt.Color(66, 61, 210));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("AGREGAR TRANSPORTISTA");
        jPanel12.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, -1, -1));

        jPanel23.setBackground(new java.awt.Color(102, 204, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setBackground(new java.awt.Color(255, 255, 255));
        jLabel77.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("ID TRANSPORTISTA");
        jPanel23.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel78.setBackground(new java.awt.Color(255, 255, 255));
        jLabel78.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("NOMBRE COMPLETO");
        jPanel23.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtTransportadora.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel23.add(txtTransportadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 300, 30));

        txtIDTransportista.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtIDTransportista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDTransportistaKeyTyped(evt);
            }
        });
        jPanel23.add(txtIDTransportista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        jLabel79.setBackground(new java.awt.Color(255, 255, 255));
        jLabel79.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("NO. CAJA");
        jPanel23.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 80, -1));

        txtNoCaja.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNoCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoCajaKeyTyped(evt);
            }
        });
        jPanel23.add(txtNoCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 140, 30));

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("TRANSPORTADORA");
        jPanel23.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btnAgregarTransp.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAgregarTransp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btnAgregarTransp.setText("AGREGAR ");
        btnAgregarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTranspActionPerformed(evt);
            }
        });
        jPanel23.add(btnAgregarTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 160, 40));

        btnEliminarTransp.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminarTransp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarTransp.setText("ELIMINAR");
        btnEliminarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTranspActionPerformed(evt);
            }
        });
        jPanel23.add(btnEliminarTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 160, 40));

        txtNombreComple.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jPanel23.add(txtNombreComple, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 300, 30));

        btnModificaTrans.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificaTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btnModificaTrans.setText("MODIFICAR");
        jPanel23.add(btnModificaTrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 160, 40));

        jPanel12.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 320, 460));

        TableTransportista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID TRANSPORTISTA", "NOMBRE COMPLETO", "NO. CAJA", "TRANSPORTADORA"
            }
        ));
        jScrollPane6.setViewportView(TableTransportista);

        jPanel12.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 640, 550));

        jTabbedPane1.addTab("tab9", jPanel12);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 1130, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCerrarSesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCerrarSesiMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCerrarSesiMouseClicked

    private void jLabelConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelConfigMouseClicked
       ACTUALIZAR.setEnabled(true);
    }//GEN-LAST:event_jLabelConfigMouseClicked

    private void jLabelAgregarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgregarProdMouseClicked
     btnAgregarClie.setEnabled(true);
    }//GEN-LAST:event_jLabelAgregarProdMouseClicked

    private void jLabelUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelUsuMouseClicked
        btnRegistrarUser.setEnabled(true);
    }//GEN-LAST:event_jLabelUsuMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
       //Codigo para especificar la cantidad de caracteres (Lo que realizara es leer el numero de careteres y dejar permitir los que nosotros queramos )
       if (txtIva.getText().length() >= 2){
           evt.consume();
           Toolkit.getDefaultToolkit().beep(); // esto lo que realizara es traer un sonido cuando exedamos el limite de caracteres que ponemos en el JTextfile
       }
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
         //Codigo para especificar la cantidad de caracteres (Lo que realizara es leer el numero de careteres y dejar permitir los que nosotros queramos )
       if (txtDescuento.getText().length() >= 2){
           evt.consume();
           Toolkit.getDefaultToolkit().beep(); // esto lo que realizara es traer un sonido cuando exedamos el limite de caracteres que ponemos en el JTextfile
       }
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void JMenuReingresarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuReingresarUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMenuReingresarUserActionPerformed

    private void JMenuReingresarUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuReingresarUsuActionPerformed
                                                    
      // TODO add your handling code here:
    }//GEN-LAST:event_JMenuReingresarUsuActionPerformed

    private void TableUserEliminadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUserEliminadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TableUserEliminadoMouseClicked

    private void txtCorreoConfigKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoConfigKeyReleased
        validarCorreoConfiguracion ();
    }//GEN-LAST:event_txtCorreoConfigKeyReleased

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed
        // Metodo para actualizar la informacion que esta guardada en la configuracion
        if (!"".equals(txtRucConfig.getText()) || !"".equals(txtNombreConfig.getText()) || !"".equals(txtTelefonoConfig.getText()) || !"".equals(txtDireccionConfig.getText())) {
            cof.setEmpresa_id(txtRucConfig.getText());
            cof.setEmpresa_nombre(txtNombreConfig.getText());
            cof.setEmpresa_telefono(txtTelefonoConfig.getText());
            cof.setEmpresa_direccion(txtDireccionConfig.getText());
            cof.setEmpresa_email(txtCorreoConfig.getText());
            cof.setEmpresa_ciudad(txtCiudadConfig.getText());
            cof.setEmpresa_dpto(txtDptoConfig.getText());
            cof.setEmpresa_mensaje(txtMensaje.getText());

            brDAO.ModificarDatos(cof);
            JOptionPane.showMessageDialog(null, "Datos de la empresa modificado");
            ListarConfig();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_ACTUALIZARActionPerformed

    private void TableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableUserMouseClicked
        btnRegistrarUser.setEnabled(true);
    }//GEN-LAST:event_TableUserMouseClicked

    private void btnModificarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarUserActionPerformed

    private void btnRegistrarUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarUserMouseClicked

    private void txtContraseñaUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaUserKeyTyped

    }//GEN-LAST:event_txtContraseñaUserKeyTyped

    private void txtCorreoUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoUserKeyReleased
        validarCorreoUsuario ();
    }//GEN-LAST:event_txtCorreoUserKeyReleased

    private void txtUsuarioUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioUserKeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtUsuarioUserKeyTyped

    private void txt_TelefonoCLIEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoCLIEKeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txt_TelefonoCLIEKeyTyped

    private void txt_TelefonoCLIEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TelefonoCLIEActionPerformed

    }//GEN-LAST:event_txt_TelefonoCLIEActionPerformed

    private void btnAgregarClieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClieActionPerformed

    }//GEN-LAST:event_btnAgregarClieActionPerformed

    private void txtCorreoCLieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoCLieKeyReleased

        validarCorreoCliente ();
    }//GEN-LAST:event_txtCorreoCLieKeyReleased

    private void txtIDClienteClieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDClienteClieKeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtIDClienteClieKeyTyped

    private void txtTelefonoModiClie1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoModiClie1KeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTelefonoModiClie1KeyTyped

    private void txtTelefonoModiClie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoModiClie1ActionPerformed

    }//GEN-LAST:event_txtTelefonoModiClie1ActionPerformed

    private void btnEliminarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarClientActionPerformed

    private void btnModificarModifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarModifiActionPerformed

    }//GEN-LAST:event_btnModificarModifiActionPerformed

    private void txtIDModiClie1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDModiClie1KeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtIDModiClie1KeyTyped

    private void cbxCodTiendaArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodTiendaArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCodTiendaArticuloActionPerformed

    private void txtTecTorreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecTorreKeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecTorreKeyTyped

    private void txtTecPantallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecPantallaKeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecPantallaKeyTyped

    private void txtCodigoBarra_AgregarNuevoProKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarra_AgregarNuevoProKeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtCodigoBarra_AgregarNuevoProKeyTyped

    private void txtCodigoBarra_AgregarNuevoProKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarra_AgregarNuevoProKeyReleased

    }//GEN-LAST:event_txtCodigoBarra_AgregarNuevoProKeyReleased

    private void txtNombreClienteArticuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteArticuloKeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreClienteArticuloKeyTyped

    private void txtNombreClienteArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteArticuloKeyPressed
           // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreClienteArticulo.getText())) {
                String cliNom = txtNombreClienteArticulo.getText();
                cl = clDAO.BuscarclienteNombre(cliNom);
                if (cl.getId()!= null) {
                    txtRucVenta.setText("" + cl.getId());
                    txtApellido1venta.setText("" + cl.getCli_apellido());
                    txtTelefonoventa.setText("" + cl.getCli_celular());
                    txtNumeroIdentificacion.setText(""+ cl.getCli_tipo_id());
                    txtDireccionventa.setText("" + cl.getCli_direccion());
                    txtCuentaCli.setText(""+ cl.getCli_cuenta());
                    txtpisoCliente.setText("" + cl.getCli_piso());
                    txtNombreClienteArticulo.setText("" + cl.getCli_nombre());
                } else {
                    txtNombreClienteArticulo.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    LimpiarRegistroVentaCliente();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del cliente");
                txtNombreClienteArticulo.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreClienteArticuloKeyPressed

    private void txtRucVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyTyped

        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtRucVentaKeyTyped

    private void txtRucVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucVentaKeyPressed
        // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtRucVenta.getText())) {
                String dni = txtRucVenta.getText();
                cl = clDAO.Buscarcliente(dni);
                if (cl.getCli_nombre()!= null) {
                    txtNombreClienteArticulo.setText("" + cl.getCli_nombre());
                    txtApellido1venta.setText("" + cl.getCli_apellido());
                     txtNumeroIdentificacion.setText("" + cl.getCli_tipo_id());
                    txtTelefonoventa.setText("" + cl.getCli_celular());
                    txtDireccionventa.setText("" + cl.getCli_direccion());
                     txtCuentaCli.setText("" + cl.getCli_cuenta());
                     txtpisoCliente.setText("" + cl.getCli_piso());
                    txtIdCV.setText("" + cl.getId());
                } else {
                    txtRucVenta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    LimpiarRegistroVentaCliente();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del cliente");
                txtRucVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtRucVentaKeyPressed

    private void btnGenerarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarArticuloActionPerformed
        // TODO add your handling code here:
        if (TableProduct.getRowCount() > 0) {
            if (!"".equals(txtRucVenta.getText())&& !"".equals(txtCodigoEmp.getText())&& !"".equals(txtCodigoTransp.getText())&& !"".equals(txtBuscarCodigoProd.getText())&& !"".equals(txtCodigoPersoAuto.getText())) {

                pdf(); // para llamar al metodo de pdf

            } else {
                JOptionPane.showMessageDialog(null, "Por favor escoger:" + "\n"+ "1.Los articulos a enviar" + "\n"+"2.La persona a quien se le envia" + "\n"+ "3.La persona quien lo transporta " + "\n"+ "4.La persona que esta realizando la entrega y"+ "\n"+ "5.La persona que autoriza la entrega"+ "\n"+ "Nota: "+ " Presione Enter al momento de indicar el nombre de la perosna que autoiza" );
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay articulos en la lista");
        }
    }//GEN-LAST:event_btnGenerarArticuloActionPerformed

    private void txtTecTorre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecTorre2KeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecTorre2KeyTyped

    private void txtMarcaTorre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaTorre2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaTorre2KeyTyped

    private void txtMarcaCamara2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaCamara2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaCamara2KeyTyped

    private void txtMarcaDiademas2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaDiademas2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaDiademas2KeyTyped

    private void txtMarcaTeclado2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaTeclado2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaTeclado2KeyTyped

    private void txtTecPantalla2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecPantalla2KeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecPantalla2KeyTyped

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // LLamando el metodo para genera el repote en excel

        Excel.reporte();
    }//GEN-LAST:event_btnExcelActionPerformed

    private void txtMarcaRaton2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaRaton2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaRaton2KeyTyped

    private void txtMarcaAdaptador2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaAdaptador2KeyTyped

    }//GEN-LAST:event_txtMarcaAdaptador2KeyTyped

    private void txtMarcaPantalla2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaPantalla2KeyTyped
        eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaPantalla2KeyTyped

    private void txtCodigoBarras_ModificarArticulo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarras_ModificarArticulo1KeyTyped
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtCodigoBarras_ModificarArticulo1KeyTyped

    private void txtCodigoBarras_ModificarArticulo1AncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txtCodigoBarras_ModificarArticulo1AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarras_ModificarArticulo1AncestorMoved

    private void txtTipoCedulaCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoCedulaCliKeyTyped
        // TODO add your handling code here:
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTipoCedulaCliKeyTyped

    private void txtTipoCedulaModifiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoCedulaModifiKeyTyped
        // TODO add your handling code here:
        eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTipoCedulaModifiKeyTyped

    private void txtAgreTecPantalla2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgreTecPantalla2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgreTecPantalla2ActionPerformed

    private void cbxTienda_ConsultarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTienda_ConsultarArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTienda_ConsultarArticuloActionPerformed

    private void txtIDEmpleado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDEmpleado1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDEmpleado1KeyTyped

    private void btnAgregarEmplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmplActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarEmplActionPerformed

    private void btnEliminarEmpl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpl1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarEmpl1ActionPerformed

    private void btnModificarEmpl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpl1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarEmpl1ActionPerformed

    private void txtIDTransportistaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTransportistaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTransportistaKeyTyped

    private void btnAgregarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTranspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarTranspActionPerformed

    private void btnEliminarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTranspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarTranspActionPerformed

    private void txtCodigoEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoEmpKeyPressed
        // TODO add your handling code here:
         // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoEmp.getText())) {
                String dni = txtCodigoEmp.getText();
                emp = empDAO.BuscarEmpleado(dni);
                if (emp.getNombre()!= null) {
                    txtNombreEmp.setText("" + emp.getNombre());
                    txtCargoEmp.setText("" + emp.getCargo());
                     txtDepartamentoEmp.setText("" + emp.getDepartamento());
                    txtCodigoIDemp.setText("" + emp.getId_Empleado());
                } else {
                    txtCodigoEmp.setText("");
                    JOptionPane.showMessageDialog(null, "La persona no existe");
                    LimpiarRegistroPersona();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo de la persona");
                txtCodigoEmp.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoEmpKeyPressed

    private void txtCodigoTranspKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTranspKeyPressed
        // TODO add your handling code here:
        
          // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoTransp.getText())) {
                String dni = txtCodigoTransp.getText();
                tr = trDAO.BuscarTransportador(dni);
                if (tr.getTra_Nombre()!= null) {
                    txtNombreTransp.setText("" + tr.getTra_Nombre());
                    txtNoCajaTransp.setText("" + tr.getTra_NoCaja());
                     txtTransportadorTransp.setText("" + tr.getTra_Transportadora());
                    txtCodigoIDTransp.setText("" + tr.getId_Transportista());
                } else {
                    txtCodigoTransp.setText("");
                    JOptionPane.showMessageDialog(null, "El transportista no existe");
                    LimpiarRegistroTransporte();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del transportista");
                txtCodigoTransp.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCodigoTranspKeyPressed

    private void txtNombreEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpKeyPressed
        // TODO add your handling code here:
    
         // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreEmp.getText())) {
                String dni = txtNombreEmp.getText();
                emp = empDAO.BuscarEmpleadoNombre(dni);
                if (emp.getId_Empleado()!= null) {
                    txtCodigoEmp.setText("" + emp.getId_Empleado());
                    txtCargoEmp.setText("" + emp.getCargo());
                     txtDepartamentoEmp.setText("" + emp.getDepartamento());
                     txtNombreEmp.setText("" + emp.getNombre());
                } else {
                    txtNombreEmp.setText("");
                    JOptionPane.showMessageDialog(null, "La persona no existe");
                    LimpiarRegistroPersona();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de la persona");
                txtNombreEmp.requestFocus();
            }
        }
    }//GEN-LAST:event_txtNombreEmpKeyPressed

    private void txtNombreTranspKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreTranspKeyPressed
        // TODO add your handling code here:
        
        
        // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombreTransp.getText())) {
                String dni = txtNombreTransp.getText();
                tr = trDAO.BuscarTransportadorNombre(dni);
                if (tr.getTra_Nombre()!= null) {
                    txtCodigoTransp.setText("" + tr.getId_Transportista());
                    txtNoCajaTransp.setText("" + tr.getTra_NoCaja());
                     txtTransportadorTransp.setText("" + tr.getTra_Transportadora());    
                     txtNombreTransp.setText("" + tr.getTra_Nombre());
                } else {
                    txtNombreTransp.setText("");
                    JOptionPane.showMessageDialog(null, "El transportista no existe");
                    LimpiarRegistroTransporte();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del transportista");
                txtNombreTransp.requestFocus();
            }
        }
        
    }//GEN-LAST:event_txtNombreTranspKeyPressed

    private void txtBuscarCodigoProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCodigoProdKeyPressed
        // TODO add your handling code here:

        // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtBuscarCodigoProd.getText())) {
                String dni = txtBuscarCodigoProd.getText();
                br = brDAO.BuscarPro(dni);
                if (br.getMarca_Pantalla()!= null) {
                    txtCodigoBarras_ModificarArticulo1.setText("" + br.getId_Articulo());
                    txtMarcaPantalla2.setText("" + br.getMarca_Pantalla());
                     txtTecPantalla2.setText("" + br.getTec_Pantalla());    
                     txtTecPantalla3.setText("" + br.getTec_Pantalla2());
                     txtTecPantalla4.setText("" + br.getTec_Pantalla3());
                     txtTecTorre2.setText("" + br.getTec_Torre());
                     txtBuscarCodigoProd.setText("" + br.getId_Articulo());
                     txtMarcaTorre2.setText("" + br.getMarca_Torre());
                     txtMarcaRaton2.setText("" + br.getMarca_Raton());
                     txtMarcaTeclado2.setText("" + br.getMarca_Teclado());
                     txtMarcaDiademas2.setText("" + br.getMarca_Diademas());
                     txtMarcaCamara2.setText("" + br.getMarca_Camara());
                     txtMarcaAdaptador2.setText("" + br.getAdaptador());
                     txtFecha2.setText("" + br.getFecha());
                     txtNumeroActas2.setText("" + br.getActa());
                     cbxTienda_ConsultarArticulo.setSelectedItem("" + br.getTienda_id());
                } else {
                    txtBuscarCodigoProd.setText("");
                    JOptionPane.showMessageDialog(null, "El articulo no existe");
                    LimpiarRegistroArticulo();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo del articulo");
                txtBuscarCodigoProd.requestFocus();
            }
        }
    }//GEN-LAST:event_txtBuscarCodigoProdKeyPressed

    private void txtCodigoEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoEmpKeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtCodigoEmpKeyTyped

    private void txtCodigoTranspKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoTranspKeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtCodigoTranspKeyTyped

    private void txtNombreEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreEmpKeyTyped

    private void txtNombreTranspKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreTranspKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreTranspKeyTyped

    private void txtBuscarCodigoProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCodigoProdKeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtBuscarCodigoProdKeyTyped

    private void txtTecPantalla3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecPantalla3KeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecPantalla3KeyTyped

    private void txtTecPantalla4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTecPantalla4KeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTecPantalla4KeyTyped

    private void txtNumeroActas2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroActas2KeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtNumeroActas2KeyTyped

    private void txtMarcaPantallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaPantallaKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaPantallaKeyTyped

    private void txtAgreTecPantalla2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgreTecPantalla2KeyTyped
        // TODO add your handling code here:
        
         eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtAgreTecPantalla2KeyTyped

    private void txtAgreTecPantalla3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgreTecPantalla3KeyTyped
        // TODO add your handling code here:
        
         eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtAgreTecPantalla3KeyTyped

    private void txtMarcaTorreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaTorreKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaTorreKeyTyped

    private void txtMarcaRatonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaRatonKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaRatonKeyTyped

    private void txtMarcaCamaraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaCamaraKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaCamaraKeyTyped

    private void txtMarcaTecladoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaTecladoKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaTecladoKeyTyped

    private void txtMarcaDiademasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaDiademasKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaDiademasKeyTyped

    private void txtMarcaAdaptadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaAdaptadorKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtMarcaAdaptadorKeyTyped

    private void txtNumeroActaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroActaKeyTyped
        // TODO add your handling code here:
        
         eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtNumeroActaKeyTyped

    private void txtNombreModifiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreModifiKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreModifiKeyTyped

    private void txtApellidoModifiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoModifiKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtApellidoModifiKeyTyped

    private void txtNombreClieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClieKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreClieKeyTyped

    private void txtApellidoClieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoClieKeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtApellidoClieKeyTyped

    private void txtBuscarUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUserKeyTyped
        // TODO add your handling code here:
        
           eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtBuscarUserKeyTyped

    private void txtTelefonoConfigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoConfigKeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtTelefonoConfigKeyTyped

    private void txtUsuarioInabilitadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioInabilitadoKeyTyped
        // TODO add your handling code here:
        
          eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtUsuarioInabilitadoKeyTyped

    private void txtNombreEmpleado1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleado1KeyTyped
        // TODO add your handling code here:
        
         eve.textKeyPress(evt);//Solo texto
    }//GEN-LAST:event_txtNombreEmpleado1KeyTyped

    private void txtNoCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoCajaKeyTyped
        // TODO add your handling code here:
        
         eve.numberKeyPress(evt);//Solo numeros
    }//GEN-LAST:event_txtNoCajaKeyTyped

    private void txtNombrPersoAutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrPersoAutoKeyPressed
        // TODO add your handling code here:
        
        
         // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtNombrPersoAuto.getText())) {
                String dni = txtNombrPersoAuto.getText();
                emp = empDAO.BuscarEmpleadoNombre2(dni);
                if (emp.getId_Empleado()!= null) {
                    txtCodigoPersoAuto.setText("" + emp.getId_Empleado());
                    txtCargoPersoAuto.setText("" + emp.getCargo());                  
                     txtNombrPersoAuto.setText("" + emp.getNombre());
                } else {
                    txtNombrPersoAuto.setText("");
                    JOptionPane.showMessageDialog(null, "La persona no existe");
                    LimpiarRegistroVentaEmple2();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de la persona");
                txtNombrPersoAuto.requestFocus();
            }
        }
        
    }//GEN-LAST:event_txtNombrPersoAutoKeyPressed

    private void txtApellido1ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido1ventaKeyPressed
        // TODO add your handling code here:
        
        
          // Metodo que se aplicara ára buscar el codigo del cliente
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtApellido1venta.getText())) {
                String cliNom = txtApellido1venta.getText();
                cl = clDAO.BuscarPersonaApellido(cliNom);
                if (cl.getId()!= null) {
                    txtRucVenta.setText("" + cl.getId());
                    txtApellido1venta.setText("" + cl.getCli_apellido());
                    txtTelefonoventa.setText("" + cl.getCli_celular());
                    txtNumeroIdentificacion.setText(""+ cl.getCli_tipo_id());
                    txtDireccionventa.setText("" + cl.getCli_direccion());
                    txtCuentaCli.setText(""+ cl.getCli_cuenta());
                    txtpisoCliente.setText("" + cl.getCli_piso());
                    txtNombreClienteArticulo.setText("" + cl.getCli_nombre());
                } else {
                    txtApellido1venta.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    LimpiarRegistroVentaCliente();
                }
            }else {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del cliente");
                txtApellido1venta.requestFocus();
            }
        }
    }//GEN-LAST:event_txtApellido1ventaKeyPressed

    private void txtFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusGained
        // TODO add your handling code here:
        
         // TODO add your handling code here:
        if(txtFecha.getText().equals("YYYY-MES-DIA"))
        {
            txtFecha.setText(" ");
            txtFecha.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txtFechaFocusGained

    private void txtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusLost

         // TODO add your handling code here:
         if(txtFecha.getText().equals(" "))
        {
            txtFecha.setText("YYYY-MES-DIA");
            txtFecha.setForeground(new Color(153,153,153));
        }
        
        
    }//GEN-LAST:event_txtFechaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JLabel JLabelCodigoArticulo;
    public javax.swing.JMenuItem JMenuEliminarUser;
    public javax.swing.JMenuItem JMenuReingresarUser;
    public javax.swing.JMenuItem JMenuReingresarUsu;
    private javax.swing.JLabel LabelUsuri;
    public javax.swing.JTable TableConsultarClienteModifi;
    public javax.swing.JTable TableEmpleado;
    public javax.swing.JTable TableProduct;
    public javax.swing.JTable TableTransportista;
    public javax.swing.JTable TableUser;
    public javax.swing.JTable TableUserEliminado;
    public javax.swing.JButton btnAgregarClie;
    public javax.swing.JButton btnAgregarEmpl;
    public javax.swing.JButton btnAgregarTransp;
    public javax.swing.JButton btnAgregar_AgregarNuevoPro;
    public javax.swing.JButton btnAgregar_ModificarArticulo1;
    public javax.swing.JButton btnEliminarClient;
    public javax.swing.JButton btnEliminarEmpl1;
    public javax.swing.JButton btnEliminarProd;
    public javax.swing.JButton btnEliminarTransp;
    public javax.swing.JButton btnExcel;
    public javax.swing.JButton btnGenerarArticulo;
    public javax.swing.JButton btnModificaTrans;
    public javax.swing.JButton btnModificarEmpl1;
    public javax.swing.JButton btnModificarModifi;
    public javax.swing.JButton btnModificarUser;
    public javax.swing.JButton btnNuevoUser;
    public javax.swing.JButton btnRegistrarUser;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<String> cbxCargoEmplea1;
    public javax.swing.JComboBox<Object> cbxCodTiendaArticulo;
    public javax.swing.JComboBox<String> cbxGeneroCliente;
    public javax.swing.JComboBox<String> cbxGeneroModifi;
    public javax.swing.JComboBox cbxTienda_ConsultarArticulo;
    public javax.swing.JComboBox<String> cbxTipoClienteCLI;
    public javax.swing.JComboBox<String> cbxtipo_cli_idUser;
    private javax.swing.JDialog dialogoconfiguracion;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    public javax.swing.JLabel jLabel101;
    public javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    public javax.swing.JLabel jLabelAgregarClien;
    public javax.swing.JLabel jLabelAgregarProd;
    public javax.swing.JLabel jLabelCerrarSesi;
    public javax.swing.JLabel jLabelConfig;
    public javax.swing.JLabel jLabelConsultarClien;
    public javax.swing.JLabel jLabelConsultarProd;
    public javax.swing.JLabel jLabelTransportista;
    public javax.swing.JLabel jLabelUsu;
    public javax.swing.JLabel jLabelVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    public javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JPanel jPanelAgregarCliente;
    public javax.swing.JPanel jPanelAgregarProducto;
    public javax.swing.JPanel jPanelCerrarSesion;
    public javax.swing.JPanel jPanelConfiguracion;
    public javax.swing.JPanel jPanelConsultarCliente;
    public javax.swing.JPanel jPanelConsultarProducto;
    private javax.swing.JPanel jPanelTabAgregarProducto;
    private javax.swing.JPanel jPanelTabConsultarProdu;
    public javax.swing.JPanel jPanelTransportista;
    public javax.swing.JPanel jPanelUsuario;
    public javax.swing.JPanel jPanelVendedor;
    private javax.swing.JPopupMenu jPopuHabilitarUsu;
    private javax.swing.JPopupMenu jPopuUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbCliente;
    public javax.swing.JLabel lbConfiguracion;
    private javax.swing.JLabel lbcorreo;
    private javax.swing.JLabel txt;
    public javax.swing.JTextField txtAgreTecPantalla2;
    public javax.swing.JTextField txtAgreTecPantalla3;
    public javax.swing.JTextField txtApellido1venta;
    public javax.swing.JTextField txtApellidoClie;
    public javax.swing.JTextField txtApellidoModifi;
    public javax.swing.JTextField txtBuscarCodigoProd;
    public javax.swing.JTextField txtBuscarUser;
    private javax.swing.JTextField txtCargoEmp;
    public javax.swing.JTextField txtCargoPersoAuto;
    private javax.swing.JTextField txtCiudadConfig;
    public javax.swing.JTextField txtCodigoBarra_AgregarNuevoPro;
    public javax.swing.JTextField txtCodigoBarras_ModificarArticulo1;
    private javax.swing.JTextField txtCodigoEmp;
    private javax.swing.JTextField txtCodigoIDTransp;
    private javax.swing.JTextField txtCodigoIDemp;
    public javax.swing.JTextField txtCodigoPersoAuto;
    private javax.swing.JTextField txtCodigoTransp;
    public javax.swing.JPasswordField txtContraseñaUser;
    public javax.swing.JTextField txtCorreoCLie;
    private javax.swing.JTextField txtCorreoConfig;
    public javax.swing.JTextField txtCorreoModiClie1;
    public javax.swing.JTextField txtCorreoUser;
    public javax.swing.JTextField txtCuentaCli;
    private javax.swing.JTextField txtDepartamentoEmp;
    public javax.swing.JTextField txtDepartamentoEmpl1;
    public javax.swing.JTextField txtDescuento;
    public javax.swing.JTextField txtDireccionClie;
    private javax.swing.JTextField txtDireccionConfig;
    public javax.swing.JTextField txtDireccionModiClie1;
    public javax.swing.JTextField txtDireccionventa;
    private javax.swing.JTextField txtDptoConfig;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtFecha2;
    public javax.swing.JTextField txtIDClienteClie;
    public javax.swing.JTextField txtIDEmpleado1;
    public javax.swing.JTextField txtIDModiClie1;
    public javax.swing.JTextField txtIDTransportista;
    public javax.swing.JTextField txtIDUser;
    private javax.swing.JTextField txtIDUsuario;
    public javax.swing.JTextField txtIdCV;
    public javax.swing.JTextField txtIva;
    public javax.swing.JTextField txtMarcaAdaptador;
    public javax.swing.JTextField txtMarcaAdaptador2;
    public javax.swing.JTextField txtMarcaCamara;
    public javax.swing.JTextField txtMarcaCamara2;
    public javax.swing.JTextField txtMarcaDiademas;
    public javax.swing.JTextField txtMarcaDiademas2;
    public javax.swing.JTextField txtMarcaPantalla;
    public javax.swing.JTextField txtMarcaPantalla2;
    public javax.swing.JTextField txtMarcaRaton;
    public javax.swing.JTextField txtMarcaRaton2;
    public javax.swing.JTextField txtMarcaTeclado;
    public javax.swing.JTextField txtMarcaTeclado2;
    public javax.swing.JTextField txtMarcaTorre;
    public javax.swing.JTextField txtMarcaTorre2;
    private javax.swing.JTextField txtMensaje;
    public javax.swing.JTextField txtNoCaja;
    private javax.swing.JTextField txtNoCajaTransp;
    public javax.swing.JTextField txtNombrPersoAuto;
    public javax.swing.JTextField txtNombreClie;
    public javax.swing.JTextField txtNombreClienteArticulo;
    public javax.swing.JTextField txtNombreComple;
    private javax.swing.JTextField txtNombreConfig;
    private javax.swing.JTextField txtNombreEmp;
    public javax.swing.JTextField txtNombreEmpleado1;
    public javax.swing.JTextField txtNombreModifi;
    private javax.swing.JTextField txtNombreTransp;
    public javax.swing.JTextField txtNumeroActa;
    public javax.swing.JTextField txtNumeroActas2;
    public javax.swing.JTextField txtNumeroIdentificacion;
    private javax.swing.JTextField txtRucConfig;
    public javax.swing.JTextField txtRucVenta;
    public javax.swing.JTextField txtTecPantalla;
    public javax.swing.JTextField txtTecPantalla2;
    public javax.swing.JTextField txtTecPantalla3;
    public javax.swing.JTextField txtTecPantalla4;
    public javax.swing.JTextField txtTecTorre;
    public javax.swing.JTextField txtTecTorre2;
    private javax.swing.JTextField txtTelefonoConfig;
    public javax.swing.JTextField txtTelefonoModiClie1;
    public javax.swing.JTextField txtTelefonoventa;
    public javax.swing.JTextField txtTipoCedulaCli;
    public javax.swing.JTextField txtTipoCedulaModifi;
    public javax.swing.JComboBox<String> txtTipoClienteModifi;
    private javax.swing.JTextField txtTransportadorTransp;
    public javax.swing.JTextField txtTransportadora;
    public javax.swing.JTextField txtUsuarioInabilitado;
    public javax.swing.JTextField txtUsuarioUser;
    public javax.swing.JTextField txt_TelefonoCLIE;
    public javax.swing.JTextField txtpisoCliente;
    // End of variables declaration//GEN-END:variables


         
          private void LimpiarUser() {
         txtIDUser.setText("");
        txtUsuarioUser.setText("");
        txtCorreoUser.setText("");
        txtContraseñaUser.setText("");
       
    }
        private void LimpiarRegistroVentaCliente(){
              txtRucVenta.setText("");
              txtNombreClienteArticulo.setText("");
              txtIdCV.setText("");
              txtApellido1venta.setText("");
              txtTelefonoventa.setText("");
              txtDireccionventa.setText("");
              txtCuentaCli.setText("");
              txtpisoCliente.setText("");
          }
          
        
        
               private void LimpiarRegistroVentaEmple2(){
              txtNombrPersoAuto.setText("");
              txtCodigoPersoAuto.setText("");
              txtCargoPersoAuto.setText("");
             
          }
          
                 private void LimpiarRegistroTransporte(){
              txtCodigoIDTransp.setText("");
               txtTransportadorTransp.setText("");
              txtNoCajaTransp.setText("");
             
          }
               
                 
                 
                         private void LimpiarRegistroPersona(){
              txtNombreEmp.setText("");
               txtCargoEmp.setText("");
              txtDepartamentoEmp.setText("");
             txtCodigoIDemp.setText("");
             txtCodigoEmp.setText(""); 
          }
                         
                         
                         
                         
                         
         private void LimpiarRegistroArticulo(){
              txtCodigoBarras_ModificarArticulo1.setText("");
              txtMarcaPantalla2.setText("");
              txtTecPantalla2.setText("");
              txtTecPantalla3.setText("");
              txtTecPantalla4.setText("");
              txtTecTorre2.setText("");
              txtBuscarCodigoProd.setText("");
              txtMarcaTorre2.setText("");
              
              
               txtMarcaRaton2.setText("");
               txtMarcaTeclado2.setText("");
               txtMarcaDiademas2.setText("");
               txtMarcaCamara2.setText("");
               txtMarcaAdaptador2.setText("");
               txtFecha2.setText("");
               txtNumeroActas2.setText("");
               cbxTienda_ConsultarArticulo.setSelectedItem("");
               txtBuscarCodigoProd.setText("");
              
          }
                         
          private void pdf(){
            try{
                
                int id =brDAO.Ticket();
                String sede="CARTAGENA";
                String negocio = "LEAN STAFFING";
                
              FileOutputStream  archivo;
             String fileName = "registro numero "+id+" de LeanSolution";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".pdf");
              archivo = new FileOutputStream (file);
              Document doc = new Document();
              PdfWriter.getInstance(doc, archivo);
              doc.open();
              Image img = Image.getInstance("src/Img/channels4_profile.png");
             
               Paragraph fecha = new Paragraph();
               //Aqui le explicamos el tipo de funte, que tendra negrita y el color de base que tendra nuestro pdf
               Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD, BaseColor.BLUE);
               fecha.add(Chunk.NEWLINE);
               Date date = new Date();
               fecha.add("Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(date)  + "\n" + "SEDE: "+ sede  + "\n"+ "NEGOCIO: " + negocio + "\n\n");
               
                //----------------------------------------------------------------------------------------------------------------------------------------------------
                
                
                
               //Ahora crearemos la tabla que tendra los datos de el producto para la elaboracion de la factura
                PdfPTable Encabesado = new PdfPTable(4);
                 
                  Encabesado.setWidthPercentage(100); //Para especificarle el tamaño del encabezado
                 
                  Encabesado.getDefaultCell().setBorder(2); // Con esto le quitaremos el borde a la tabla
                 
                  float[] ColumnEncabezado = new float[]{20f, 10f, 90f, 60f};//Le pondremos el tamaño para cada celda
                  
                  Encabesado.setWidths(ColumnEncabezado);//Con esto le pasamos las especificaciones de el tamaño al encabesado
                 
                  Encabesado.setHorizontalAlignment(Element.ALIGN_LEFT);//Aqui especificamos la pocision
              
                   //----------------------------------------------------------------------------------------------------------------------------------------------------
                   
                   
                  //Primero agregamos la imagen del logo de la tienda
                   Encabesado.addCell(img);
                     String Titulo1 = "ACTA DE ENTREGA DE ELEMENTOS DE EQUIPO\n"; 
                     String subtitulo = "SISTEMA INTEGRADO DE GESTION";
                     
                  
                          
              Encabesado.addCell(" ");
              Encabesado.addCell( Titulo1 +  " \n" + subtitulo + " \n" );
              Encabesado.addCell(fecha);
              //Ahora agregaremos las celdas al documento
              doc.add(Encabesado);
              
            
                 
                  //----------------------------------------------------------------------------------------------------------------------------------------------------
              //Una vez realizado el paragran, realizamos otro para clientes
               
               Paragraph Info2 = new Paragraph();
               Info2.add(Chunk.NEWLINE);
               Info2.add("Reconozco que recibo los siguientes elementos y equipos de parte de la empresa:\n");
               Info2.add("PERFILES Y SOLUCIONES LOGISTICA SAS.com"  + " \n\n");
               doc.add(Info2);
               
               
               
                   Paragraph Persona3 = new Paragraph();
               Persona3.add(Chunk.NEWLINE);
               Persona3.add("Informe de los Articulos de la persona\n\n");
               Persona3.setAlignment(Element.ALIGN_CENTER);
               doc.add(Persona3);
               
               
               //Ahora se agrega los productos
                 
                  PdfPTable TablaProd1 = new PdfPTable(6);
                TablaProd1.setWidthPercentage(100);
                
                TablaProd1.getDefaultCell().setBorderWidthTop(1);
                TablaProd1.getDefaultCell().setBorderWidthBottom(1);
                TablaProd1.getDefaultCell().setBorderWidthRight(1);
                TablaProd1.getDefaultCell().setBorderWidthLeft(1);
               float[] ColumnProductos = new float[]{40f, 40f, 50f, 40f, 40f, 40f };
                TablaProd1.setWidths(ColumnProductos);
                TablaProd1.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell prod1 = new  PdfPCell (new Phrase("id_Articulo",negrita));
                PdfPCell prod2 = new  PdfPCell (new Phrase("Mar_Pantalla",negrita));
                PdfPCell prod3 = new  PdfPCell (new Phrase("Tec_Pantalla",negrita));
                PdfPCell prod4 = new  PdfPCell (new Phrase("Tec_Pantalla2",negrita));
                PdfPCell prod5 = new  PdfPCell (new Phrase("Tec_Pantalla3",negrita));
                PdfPCell prod6 = new  PdfPCell (new Phrase("Tec_Torre",negrita));
 
                
               
                
                
                //Crearemos un Backgraund para los titulos 
                prod1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod2.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                prod3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod6.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
                
                //Agregamos las celdas a la tabla
                TablaProd1.addCell(prod1);
                TablaProd1.addCell(prod2); 
                TablaProd1.addCell(prod3);
                TablaProd1.addCell(prod4);
                TablaProd1.addCell(prod5);
                TablaProd1.addCell(prod6);
                  
                TablaProd1.addCell(txtCodigoBarras_ModificarArticulo1.getText());
                TablaProd1.addCell(txtMarcaPantalla2.getText());
                TablaProd1.addCell(txtTecPantalla2.getText()); 
                TablaProd1.addCell(txtTecPantalla3.getText()); 
                TablaProd1.addCell(txtTecPantalla4.getText());
                TablaProd1.addCell(txtTecTorre2.getText());
                  
              
               doc.add(TablaProd1);

          
          //----------------------------------------------------------------------------------------------------------------------------------------------------
               
               


                //Ahora se agrega los productos
                 
                  PdfPTable TablaProd2 = new PdfPTable(6);
                TablaProd2.setWidthPercentage(100);
                
                TablaProd2.getDefaultCell().setBorderWidthTop(1);
                TablaProd2.getDefaultCell().setBorderWidthBottom(1);
                TablaProd2.getDefaultCell().setBorderWidthRight(1);
                TablaProd2.getDefaultCell().setBorderWidthLeft(1);
               float[] ColumnProductos2 = new float[]{40f, 40f, 50f, 40f, 40f, 40f };
                TablaProd2.setWidths(ColumnProductos2);
                TablaProd2.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell prod7 = new  PdfPCell (new Phrase("Marc_Torre",negrita));
                PdfPCell prod8 = new  PdfPCell (new Phrase("Mar_Raton",negrita));
                PdfPCell prod9 = new  PdfPCell (new Phrase("Mar_Teclado",negrita));
                PdfPCell prod10 = new  PdfPCell (new Phrase("Mar_Diademas",negrita));
                PdfPCell prod11 = new  PdfPCell (new Phrase("Mar_Camara",negrita));
                PdfPCell prod12= new  PdfPCell (new Phrase("Adaptador",negrita));
 
                
             
                
                
                //Crearemos un Backgraund para los titulos 
                prod7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod8.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                prod9.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod10.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod12.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
                
                //Agregamos las celdas a la tabla
                TablaProd2.addCell(prod7);
                TablaProd2.addCell(prod8); 
                TablaProd2.addCell(prod9);
                TablaProd2.addCell(prod10);
                TablaProd2.addCell(prod11);
                TablaProd2.addCell(prod12);
                  
                TablaProd2.addCell(txtMarcaTorre2.getText());
                TablaProd2.addCell(txtMarcaRaton2.getText());
                TablaProd2.addCell(txtMarcaTeclado2.getText()); 
                TablaProd2.addCell(txtMarcaDiademas2.getText()); 
                TablaProd2.addCell(txtMarcaCamara2.getText());
                TablaProd2.addCell(txtMarcaAdaptador2.getText());
                  
              
               doc.add(TablaProd2);
               
               
                //----------------------------------------------------------------------------------------------------------------------------------------------------
              


                //Ahora se agrega los productos
                 
                  PdfPTable TablaProd3 = new PdfPTable(3);
                TablaProd3.setWidthPercentage(100);
                
                TablaProd3.getDefaultCell().setBorderWidthTop(1);
                TablaProd3.getDefaultCell().setBorderWidthBottom(1);
                TablaProd3.getDefaultCell().setBorderWidthRight(1);
                TablaProd3.getDefaultCell().setBorderWidthLeft(1);
               float[] ColumnProductos3 = new float[]{40f, 40f, 50f};
                TablaProd3.setWidths(ColumnProductos3);
                TablaProd3.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell prod13 = new  PdfPCell (new Phrase("Fecha",negrita));
                PdfPCell prod14 = new  PdfPCell (new Phrase("Acta",negrita));
                PdfPCell prod15 = new  PdfPCell (new Phrase("Tienda",negrita));
 
 
                
             
                
                
                //Crearemos un Backgraund para los titulos 
                prod13.setBackgroundColor(BaseColor.LIGHT_GRAY);
                prod14.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                prod15.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
                
                //Agregamos las celdas a la tabla
                TablaProd3.addCell(prod13);
                TablaProd3.addCell(prod14); 
                TablaProd3.addCell(prod15);
               
                  
                TablaProd3.addCell(txtFecha2.getText());
                TablaProd3.addCell(txtNumeroActas2.getText());
                TablaProd3.addCell(cbxTienda_ConsultarArticulo.getSelectedItem().toString()); 
                  
              
               doc.add(TablaProd3);


               //----------------------------------------------------------------------------------------------------------------------------------------------------
               
      
               
               
                 Paragraph Empleado2 = new Paragraph();
               Empleado2.add(Chunk.NEWLINE);
               Empleado2.add("\n Datos de quien autoriza la entrega\n\n");
               Empleado2.setAlignment(Element.ALIGN_CENTER);
               doc.add(Empleado2);
               
               
               
               
                PdfPTable TablaEmpleado2 = new PdfPTable(3);
                TablaEmpleado2.setWidthPercentage(100);
                TablaEmpleado2.getDefaultCell().setBorderWidthTop(1);
                TablaEmpleado2.getDefaultCell().setBorderWidthBottom(1);
                TablaEmpleado2.getDefaultCell().setBorderWidthRight(1);
                TablaEmpleado2.getDefaultCell().setBorderWidthLeft(1);
               
                
               float[] ColumnEmpleado2 = new float[]{40f, 40f, 40f };
                TablaEmpleado2.setWidths(ColumnEmpleado2);
                TablaEmpleado2.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell vend6 = new  PdfPCell (new Phrase("Nombre Completo",negrita));               
                PdfPCell vend7 = new  PdfPCell (new Phrase("Cargo",negrita));
                PdfPCell vend8 = new  PdfPCell (new Phrase("Firma",negrita));
               
               
                
               
                        //Crearemos un Backgraund para los titulos 
                vend6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vend7.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                vend8.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
               
                
                
                //Agregamos las celdas a la tabla
                TablaEmpleado2.addCell(vend6);
                TablaEmpleado2.addCell(vend7); 
                TablaEmpleado2.addCell(vend8);
               
               
                
           
                   TablaEmpleado2.addCell(txtNombrPersoAuto.getText());
                   TablaEmpleado2.addCell(txtCargoPersoAuto.getText());
                   TablaEmpleado2.addCell(" ");
                   
                   

                 
                 
                 doc.add(TablaEmpleado2);
                 
                 
                 
                 
                       
      
                 
                   //----------------------------------------------------------------------------------------------------------------------------------------------------
               
      
               
               
                 Paragraph Empleado = new Paragraph();
               Empleado.add(Chunk.NEWLINE);
               Empleado.add("\n Datos de quien entrega\n\n");
               Empleado.setAlignment(Element.ALIGN_CENTER);
               doc.add(Empleado);
               
               
               
               
                PdfPTable TablaEmpleado = new PdfPTable(5);
                TablaEmpleado.setWidthPercentage(100);
                TablaEmpleado.getDefaultCell().setBorderWidthTop(1);
                TablaEmpleado.getDefaultCell().setBorderWidthBottom(1);
                TablaEmpleado.getDefaultCell().setBorderWidthRight(1);
                TablaEmpleado.getDefaultCell().setBorderWidthLeft(1);
               
                
               float[] ColumnEmpleado = new float[]{40f, 40f, 30f, 30f, 20f };
                TablaEmpleado.setWidths(ColumnEmpleado);
                TablaEmpleado.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell vend1 = new  PdfPCell (new Phrase("Nombre Completo",negrita));
                PdfPCell vend2 = new  PdfPCell (new Phrase("Firma",negrita));
                PdfPCell vend3 = new  PdfPCell (new Phrase("Cargo",negrita));
                PdfPCell vend4 = new  PdfPCell (new Phrase("Departamento",negrita));
                PdfPCell vend5 = new  PdfPCell (new Phrase("Fecha",negrita));
               
                
               
                        //Crearemos un Backgraund para los titulos 
                vend1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vend2.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                vend3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vend4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                vend5.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
                
                
                //Agregamos las celdas a la tabla
                TablaEmpleado.addCell(vend1);
                TablaEmpleado.addCell(vend2); 
                TablaEmpleado.addCell(vend3);
                TablaEmpleado.addCell(vend4);
                TablaEmpleado.addCell(vend5);
               
                
           
                   TablaEmpleado.addCell(txtNombreEmp.getText());
                   TablaEmpleado.addCell(" ");
                   TablaEmpleado.addCell(txtCargoEmp.getText());
                   TablaEmpleado.addCell(txtDepartamentoEmp.getText());
                   TablaEmpleado.addCell(txtFecha2.getText());
                   

                 
                 
                 doc.add(TablaEmpleado);
                 
                 
                 
                 
                       //----------------------------------------------------------------------------------------------------------------------------------------------------
               
      
               
               
                 Paragraph Transportista = new Paragraph();
               Transportista.add(Chunk.NEWLINE);
               Transportista.add("\n Datos del Transportista(Si Aplica)\n\n");
               Transportista.setAlignment(Element.ALIGN_CENTER);
               doc.add(Transportista);
               
               
               
               
                PdfPTable TablaTrasporte = new PdfPTable(4);
                TablaTrasporte.setWidthPercentage(100);
                TablaTrasporte.getDefaultCell().setBorderWidthTop(1);
                TablaTrasporte.getDefaultCell().setBorderWidthBottom(1);
                TablaTrasporte.getDefaultCell().setBorderWidthRight(1);
                TablaTrasporte.getDefaultCell().setBorderWidthLeft(1);
               
                
               float[] ColumnTrasporte = new float[]{50f, 40f, 20f, 50f };
                TablaTrasporte.setWidths(ColumnTrasporte);
                TablaTrasporte.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell tras1 = new  PdfPCell (new Phrase("Nombre Completo",negrita));
                PdfPCell tras2 = new  PdfPCell (new Phrase("Transportadora",negrita));
                PdfPCell tras3 = new  PdfPCell (new Phrase("No.de Caja",negrita));
                PdfPCell tras4 = new  PdfPCell (new Phrase("Firma Trasnportista",negrita));
              
               
                
               
                        //Crearemos un Backgraund para los titulos 
                tras1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tras2.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                tras3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tras4.setBackgroundColor(BaseColor.LIGHT_GRAY);
               
               
                
                
                //Agregamos las celdas a la tabla
                TablaTrasporte.addCell(tras1);
                TablaTrasporte.addCell(tras2); 
                TablaTrasporte.addCell(tras3);
                TablaTrasporte.addCell(tras4);
             
               
                
           
                   TablaTrasporte.addCell(txtNombreTransp.getText());      
                   TablaTrasporte.addCell(txtTransportadorTransp.getText());
                   TablaTrasporte.addCell(txtNoCajaTransp.getText());
                   TablaTrasporte.addCell(" ");
                   

                 
                 
                 doc.add(TablaTrasporte);
                 
                 
                 
                //----------------------------------------------------------------------------------------------------------------------------------------------------
               
      
               
               
                 Paragraph cliente = new Paragraph();
               cliente.add(Chunk.NEWLINE);
               cliente.add("\nDatos de quien recibe\n\n");
               cliente.setAlignment(Element.ALIGN_CENTER);
               doc.add(cliente);
               
               
               
               
                PdfPTable TablaClient = new PdfPTable(7);
                TablaClient.setWidthPercentage(100);
                TablaClient.getDefaultCell().setBorderWidthTop(1);
                TablaClient.getDefaultCell().setBorderWidthBottom(1);
                TablaClient.getDefaultCell().setBorderWidthRight(1);
                TablaClient.getDefaultCell().setBorderWidthLeft(1);
               
                
               float[] ColumnCliente = new float[]{30f, 30f, 50f, 40f, 40f, 30f,30f };
                TablaClient.setWidths(ColumnCliente);
                TablaClient.setHorizontalAlignment(Element.ALIGN_LEFT);
                
                PdfPCell cli1 = new  PdfPCell (new Phrase("Nombre",negrita));
                PdfPCell cli2 = new  PdfPCell (new Phrase("Apellido",negrita));
                PdfPCell cli3 = new  PdfPCell (new Phrase("#Identificacion",negrita));
                PdfPCell cli4 = new  PdfPCell (new Phrase("Telefono",negrita));
                PdfPCell cli5 = new  PdfPCell (new Phrase("Direccion",negrita));
                PdfPCell cli6 = new  PdfPCell (new Phrase("Cuenta",negrita));
                PdfPCell cli7 = new  PdfPCell (new Phrase("Piso",negrita));
                
               
                        //Crearemos un Backgraund para los titulos 
                cli1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cli2.setBackgroundColor(BaseColor.LIGHT_GRAY); 
                cli3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cli4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cli5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cli6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cli7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                
                //Agregamos las celdas a la tabla
                TablaClient.addCell(cli1);
                TablaClient.addCell(cli2); 
                TablaClient.addCell(cli3);
                TablaClient.addCell(cli4);
                TablaClient.addCell(cli5);
                TablaClient.addCell(cli6);
                TablaClient.addCell(cli7);
                
           
                            
                TablaClient.addCell(txtNombreClienteArticulo.getText());
                TablaClient.addCell(txtApellido1venta.getText()); 
                TablaClient.addCell(txtNumeroIdentificacion.getText()); 
                TablaClient.addCell(txtTelefonoventa.getText());
                TablaClient.addCell(txtDireccionventa.getText());
                TablaClient.addCell(txtCuentaCli.getText());
                TablaClient.addCell(txtpisoCliente.getText());
                 
                 
                 doc.add(TablaClient);
                 
                 
                 
               
              //Para la Firma
                 Paragraph Firma = new Paragraph();
               Firma.add(Chunk.NEWLINE);
                Firma.add("\n\n");
               Firma.add("Firma de quien recibe los equipos " + "-------------" + "        " +"Huella de quien recibe los equipos  " + "--------------"  );
               Firma.setAlignment(Element.ALIGN_LEFT);
               doc.add(Firma); 
                 
               
              doc.close();
              archivo.close();
              
              Desktop.getDesktop().open(file);
              
            }catch (DocumentException | IOException e){
                System.out.println(e.toString());
            }
        }
            
}