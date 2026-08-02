
package Control;

import Modelo.Barra;
import Modelo.BarraDAO;
import Modelo.Combo;
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


public class BarrasControl implements ActionListener, MouseListener, KeyListener{
    private Barra br;
    private BarraDAO brDAO;
    private Sistema vista ;

    
  DefaultTableModel modelo = new DefaultTableModel();

    public BarrasControl(Barra br, BarraDAO brDAO, Sistema vista) {
        this.br = br;
        this.brDAO = brDAO;
        this.vista = vista;
        this.vista.btnAgregar_AgregarNuevoPro.addActionListener(this);
        this.vista.btnAgregar_ModificarArticulo1.addActionListener(this);
        this.vista.btnEliminarProd.addActionListener(this);
        this.vista.txtBuscarCodigoProd.addKeyListener(this);
        this.vista.TableProduct.addMouseListener(this);
      listarBarra();
        
      
    }
    
     public void  listarBarra(){
        Tables color = new Tables ();
        vista.TableProduct.setDefaultRenderer(vista.TableProduct.getColumnClass(0), color);
        List<Barra> lista = brDAO.ListarBarra(vista.txtBuscarCodigoProd.getText());
        modelo = (DefaultTableModel) vista.TableProduct.getModel();
        Object[] ob = new Object[15];
        for(int i = 0; i < lista.size(); i++){
            ob[0] = lista.get(i).getId_Articulo();
            ob[1] = lista.get(i).getMarca_Pantalla();
            ob[2] = lista.get(i).getTec_Pantalla();   
            ob[3] = lista.get(i).getTec_Pantalla2();
            ob[4] = lista.get(i).getTec_Pantalla3();
            ob[5] = lista.get(i).getTec_Torre();
            ob[6] = lista.get(i).getMarca_Torre();
            ob[7] = lista.get(i).getMarca_Raton();
            ob[8] = lista.get(i).getMarca_Teclado();
            ob[9] = lista.get(i).getMarca_Camara();
            ob[10] = lista.get(i).getMarca_Diademas();
            ob[11] = lista.get(i).getAdaptador();
            ob[12] = lista.get(i).getFecha();
            ob[13] = lista.get(i).getActa();
            ob[14] = lista.get(i).getTienda_id();
            
            
             modelo.addRow(ob);
        }
        vista.TableProduct.setModel(modelo);
        
        JTableHeader header = vista.TableProduct.getTableHeader();
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
        
        
       @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnAgregar_AgregarNuevoPro){
            if(vista.txtCodigoBarra_AgregarNuevoPro.getText().equals("") 
                || vista.txtTecPantalla.getText().equals("") 
                || vista.txtMarcaPantalla.getText().equals("") 
                || vista.txtMarcaTorre.getText().equals("")
                      || vista.txtTecTorre.getText().equals("") 
                         || vista.cbxCodTiendaArticulo.getSelectedItem().toString().equals("")){
                JOptionPane.showMessageDialog(null, "Todos los campos son oblogatorios");
            }else{
                 br.setId_Articulo(vista.txtCodigoBarra_AgregarNuevoPro.getText());
                br.setMarca_Pantalla(vista.txtMarcaPantalla.getText());
                br.setTec_Pantalla(vista.txtTecPantalla.getText());
                br.setTec_Pantalla2(vista.txtAgreTecPantalla2.getText());
                br.setTec_Pantalla3(vista.txtAgreTecPantalla3.getText());
                br.setTec_Torre(vista.txtTecTorre.getText());
                br.setMarca_Torre(vista.txtMarcaTorre.getText());
                br.setMarca_Raton(vista.txtMarcaRaton.getText());
                 br.setMarca_Teclado(vista.txtMarcaRaton.getText());
                br.setMarca_Diademas(vista.txtMarcaDiademas.getText());
                br.setMarca_Camara(vista.txtMarcaCamara.getText());
                br.setAdaptador(vista.txtMarcaAdaptador.getText());
                br.setFecha(vista.txtFecha.getText());
                br.setActa(vista.txtNumeroActa.getText());
                br.setTienda_id(vista.cbxCodTiendaArticulo.getSelectedItem().toString());
                
                if(brDAO.Registrar(br)){
                     listarBarra();
                     limpiarTable ();
                   limpiarRegistro();

                    JOptionPane.showMessageDialog(null, "Articulos registrados con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al registrar los Articulos");
                }
            }
        }else{
              if(e.getSource() == vista.btnAgregar_ModificarArticulo1){
            if(vista.txtCodigoBarras_ModificarArticulo1.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                  br.setId_Articulo(vista.txtCodigoBarras_ModificarArticulo1.getText());
                br.setMarca_Pantalla(vista.txtMarcaPantalla2.getText());
                br.setTec_Pantalla(vista.txtTecPantalla2.getText());
                br.setTec_Pantalla2(vista.txtTecPantalla3.getText());
                br.setTec_Pantalla3(vista.txtTecPantalla4.getText());
                br.setTec_Torre(vista.txtTecTorre2.getText());
                br.setMarca_Torre(vista.txtMarcaTorre2.getText());
                br.setMarca_Raton(vista.txtMarcaRaton2.getText());
                 br.setMarca_Teclado(vista.txtMarcaTeclado2.getText());
                br.setMarca_Diademas(vista.txtMarcaDiademas2.getText());
                br.setMarca_Camara(vista.txtMarcaCamara2.getText());
                br.setAdaptador(vista.txtMarcaAdaptador2.getText());
                br.setFecha(vista.txtFecha2.getText());
                br.setActa(vista.txtNumeroActas2.getText());
                br.setTienda_id(vista.cbxTienda_ConsultarArticulo.getSelectedItem().toString());
                
                if(brDAO.Modificar(br)){
                     listarBarra();
                      limpiarTable ();
                    limpiarConsulta();
                   
                    JOptionPane.showMessageDialog(null, "Producto Modificado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro al modificar el Articulo");
                }
            }
            
        }else{
                  if(e.getSource() == vista.btnEliminarProd){
                   if (!"".equals(vista.txtCodigoBarras_ModificarArticulo1.getText())) {
             int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar","Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (pregunta == 0) {
                String id = vista.txtCodigoBarras_ModificarArticulo1.getText();
                brDAO.EliminarProducto(id);
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
        if(e.getSource() == vista.txtBuscarCodigoProd){
            limpiarTable ();
            listarBarra();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          if(e.getSource() == vista.TableProduct){
            int fila = vista.TableProduct.rowAtPoint(e.getPoint());
            vista.txtCodigoBarras_ModificarArticulo1.setText(vista.TableProduct.getValueAt(fila, 0).toString());
            vista.txtMarcaPantalla2.setText(vista.TableProduct.getValueAt(fila, 1).toString());
            vista.txtTecPantalla2.setText(vista.TableProduct.getValueAt(fila, 2).toString());
            vista.txtTecPantalla3.setText(vista.TableProduct.getValueAt(fila, 3).toString());
            vista.txtTecPantalla4.setText(vista.TableProduct.getValueAt(fila, 4).toString());
            vista.txtTecTorre2.setText(vista.TableProduct.getValueAt(fila, 5).toString());
            vista.txtMarcaTorre2.setText(vista.TableProduct.getValueAt(fila, 6).toString());
            vista.txtMarcaRaton2.setText(vista.TableProduct.getValueAt(fila, 7).toString());
            vista.txtMarcaTeclado2.setText(vista.TableProduct.getValueAt(fila, 8).toString());
            vista.txtMarcaDiademas2.setText(vista.TableProduct.getValueAt(fila, 9).toString());
            vista.txtMarcaCamara2.setText(vista.TableProduct.getValueAt(fila, 10).toString());
            vista.txtMarcaAdaptador2.setText(vista.TableProduct.getValueAt(fila, 11).toString());
            vista.txtFecha2.setText(vista.TableProduct.getValueAt(fila, 12).toString());
            vista.txtNumeroActas2.setText(vista.TableProduct.getValueAt(fila, 13).toString());
            vista.cbxTienda_ConsultarArticulo.setSelectedItem(vista.TableProduct.getValueAt(fila, 14).toString());
            
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
        vista.txtCodigoBarras_ModificarArticulo1.setText("");
         vista.txtMarcaPantalla2.setText("");
          vista.txtTecPantalla.setText("");
          vista.txtAgreTecPantalla2.setText("");
          vista.txtAgreTecPantalla3.setText("");
           vista.txtTecTorre2.setText("");
            vista.txtMarcaTorre2.setText("");
             vista.txtMarcaTeclado2.setText("");
              vista.txtMarcaCamara2.setText("");
               vista.txtMarcaDiademas2.setText("");
                vista.txtNumeroActas2.setText("");
                 vista.txtMarcaRaton2.setText("");
                  vista.txtMarcaAdaptador2.setText("");
                   vista.txtFecha2.setText("");
                    vista.cbxTienda_ConsultarArticulo.setSelectedItem("");
       
             
              
    }
        
        private void limpiarRegistro(){
        vista.txtCodigoBarra_AgregarNuevoPro.setText("");
        vista.txtMarcaPantalla.setText("");
        vista.txtTecPantalla.setText("");
        vista.txtTecPantalla2.setText("");
        vista.txtTecPantalla3.setText("");
        vista.txtTecTorre.setText("");
        vista.txtMarcaTorre.setText("");
        vista.txtMarcaRaton.setText("");
        vista.txtMarcaTeclado.setText("");
        vista.txtMarcaDiademas.setText("");
        vista.txtMarcaCamara.setText("");
        vista.txtMarcaAdaptador.setText("");
        vista.txtFecha.setText("");
        vista.txtNumeroActa.setText("");
        vista.cbxCodTiendaArticulo.setSelectedItem(null);
      
        
    }
        
 
}
  