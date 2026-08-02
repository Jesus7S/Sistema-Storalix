package Control;


import Vista.FrmLogin;
import Modelo.*;
import Vista.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class LoginControl implements ActionListener {
    
    private Login l;
    private LoginDAO lDAO;
    private FrmLogin vista;

    public LoginControl(Login l, LoginDAO lDAO, FrmLogin vista) {
        this.l = l;
        this.lDAO = lDAO;
        this.vista = vista;
        this.vista.btnIniciar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
         //Esto es para centrar el Login(la vista)
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Usaremos un if para saber que boton se a precionado
        if (e.getSource() == vista.btnIniciar){
            //Se realizara una validacion primero por si los campos estan vacios
            if(vista.txtCorreo.getText().equals("") || String.valueOf(vista.txtContraseña.getPassword()).equals("")){
                
                //Si se encuentra vacio mostraremos un mensaje que diga que los campos estan vacios
                JOptionPane.showMessageDialog(null, "Los campos estan vacios");
            }else{
                //Aqui accederemos a el metodo Login que creamos anterirmente
                String correo = vista.txtCorreo.getText();
                String pass = String.valueOf(vista.txtContraseña.getPassword());
                l = lDAO.log(correo, pass);
                
                //Con un if usaremos para verficar si
                if(l.getCorreo() != null){
                    Sistema si = new Sistema(l.getId(),l.getNombre(),l.getRol());
                    si.setVisible(true);
                    this.vista.dispose();
               
                }else{
                
                 JOptionPane.showMessageDialog(null, "Correo o Contraseña incorrecta");
                 
                }
            }
        }else{
            //Esto lo realizaremos para el boton salir 
             int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas salir?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // Y con un if pondremos que se salga del sistema
            if(pregunta == 0){
                System.exit(0);
            }
            
            }
            
            
        }
        
    }
       

