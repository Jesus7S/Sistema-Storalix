
/*package Control;


import Modelo.DetalleTraslado;
import Modelo.DetalleTrasladoDAO;
import Vista.Sistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class DetalleTrasladoControl implements ActionListener {
    private DetalleTraslado detatras;
    private DetalleTrasladoDAO detatrasDAO;
    private Sistema vista;
 

    public DetalleTrasladoControl(DetalleTraslado detatras, DetalleTrasladoDAO detatrasDAO, Sistema vista) {
        this.detatras = detatras;
        this.detatrasDAO = detatrasDAO;
        this.vista = vista;
        this.vista.btnAgregarTrasladoDetalladoStock.addActionListener(this);
        
       
    }

    @Override
  public void actionPerformed(ActionEvent e) {
 if(e.getSource() == vista.btnAgregarTrasladoDetalladoStock){
            if(vista.txtDetalladoID.getText().equals("") 
                  || vista.txtCantidad_Detalle_trasl.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                detatras.setTraslaID(Integer.parseInt(vista.cbxTrasladoIdDetalle_Tras.getSelectedItem().toString()));
                detatras.setDetalleTrasladoID(Integer.parseInt(vista.txtDetalladoID.getText()));
                detatras.setBarrasID(vista.cbxBarrasID_Detalle_trasl.getSelectedItem().toString());
                detatras.setDetalleTraslaCantidad(Integer.parseInt(vista.txtCantidad_Detalle_trasl.getText()));

                if(detatrasDAO.Registrar(detatras)){

                    JOptionPane.showMessageDialog(null, "DetalleTraslado registrado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar el DetalleTraslado");
                }
            }
 }
      
    } 
}*/
