
package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelo.Conexion;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    public static void reporte() {

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("ACTA DE ENTREGA DE ELEMENTOS Y EQUIPOS");

// Componentes para la imagene que pondremos en el archivo de excel.
        try {
           InputStream is = new FileInputStream("src/Img/channels4_profile.png"); //Ubicacion de donde se encuentra la imagen que usaremos en
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
           
            //En esta parte expecificamos tanto la posicion con el alcho y alto que encontraremos de la imagen en el archivo de excel
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 5);

            
            // En esta parte especificamos los atributos que tendra la palabras que colocquemos en una celda y linea especifica
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            tituloEstilo.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            tituloEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setColor(IndexedColors.WHITE.getIndex());
            fuenteTitulo.setFontHeightInPoints((short) 4);
            tituloEstilo.setFont(fuenteTitulo);

            
            
           
            CellStyle tituloEstilo2 = book.createCellStyle();
            tituloEstilo2.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo2.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo2 = book.createFont();
            fuenteTitulo2.setFontName("Arial");
            fuenteTitulo2.setBold(true);
            fuenteTitulo2.setFontHeightInPoints((short)4);
            tituloEstilo2.setFont(fuenteTitulo2);
            
 // --------------------------------------------------------------------------------------------------------------------------------------------- 
            
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("ACTA DE ENTREGA DE ELEMENTOS Y EQUIPOS");
            
            
            
             
            Row filaSubTitulo = sheet.createRow(3);
            Cell celdaSubTitulo = filaSubTitulo.createCell(2);
            celdaSubTitulo.setCellStyle(tituloEstilo2);
            celdaSubTitulo.setCellValue("SISTEMA INTEGRADO DE GESTION");
            
            
           // --------------------------------------------------------------------------------------------------------------------------------------------- 
            
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
             
            String[] cabecera = new String[]{"id_Articulo", "Marca_Pantalla", "Tec_Pantalla", "Tec_Torre", "Marca_Torre","Marca_Raton"};
            
            String[] cabecera2 = new String[]{"Sede", "___________", "Negocio", "____________", "#TICKET"," "};
             
            String[] cabecera3 = new String[]{"Favor marque "+"\ncon una x"+"\nsegun corresponda"," "," "," "," "," "};
            
            String[] cabecera4 = new String[]{" "," ","SISTEMA INTEGRADO DE GESTION"," "," "," "};
            
            String[] cabecera5 = new String[]{" "," "," "," ","CODIGO","VERSION"};
            
             String[] cabecera6 = new String[]{"Marca_Teclado","Marca_Diademas", "Marca_Camara", "Adaptador", "Fecha", "Acta"};
             
            String[] cabecera7 = new String[]{"Por medio "+"\ndel presente"+"\ndocumento"+"\n se hace","\nentrega de"+"\n los siguientes:","Elementos","_____________ ","Equipos", "___"};
            
            String[] cabecera8 = new String[]{"Reconozco que"+"\n recibo los"+"\nsiguientes elementos "+"\n y equipo"+"\nequipos de parte de la"+"\nempresa"+"\n PERFILES Y SOLUCIONES LOGISTICA SAS"+"con ocasion a labor "+"\nque desempeño "+"\ncomo"+" trabajador de: "," "," "," "," "," "};
            
          // --------------------------------------------------------------------------------------------------------------------------------------------- 
         
            String[] cabecera9 = new String[]{" "," "," "," "," "," "};
            String[] cabecera10 = new String[]{"PERFILES Y SOLUCIONES LOGISTICA"+"\nS.A.S identifica con"+"\n el NIT 900592 737-3 "," "," ","","",""};
            String[] cabecera11 = new String[]{"Servicios Financieros Cabana"+"\n(SERFICABANA S.A.S)"+"\n identificada con el NIT "+"\n900 792147-6"," "," "," "," "," "};
            String[] cabecera12 = new String[]{"Servicios Financieros para "+"\nEmpresa SESPEM S.A.S"+"\n identificada con el NIT "+"\n800148-8"," "," "," "," "," "};
            String[] cabecera13 = new String[]{"ACTIVOS S.A.S "+"\nidentificada con el"+"\nNIT 860090915-9"," "," "," "," "," "};
            String[] cabecera14 = new String[]{"international labor "+"\nService S.A.S"+"\n(INLASERV S.A.S)"+"\nidentificada con el NIT "+"\n900133704-2"," "," "," "};
           // --------------------------------------------------------------------------------------------------------------------------------------------- 
           
            String[] cabecera15 = new String[]{"TODOS LOS ELEMENTOS Y/O"+"\nEQUIPOS RELACIONADOS"+"\nEN ESTE DOCUMENTO SE "+"\n ENTREGAN EN BUEN"+"\nESTADO Y FUNCIONALES"," "," "," "};
            String[] cabecera16 = new String[]{"Datos de quien autoriza"+"\nla entrega"," "," "," "," ",""};
            String[] cabecera17 = new String[]{"Nombre Completo","","Cargo"," ","Firma "," "};
           
             // --------------------------------------------------------------------------------------------------------------------------------------------- 
             
               String[] cabecera18 = new String[]{"Datos de quien entrega"," "," "," "," ",""};
               String[] cabecera19 = new String[]{"Nombre Completo","Firma","Cargo","Departamento","Fecha de envio",""};
               String[] cabecera20 = new String[]{"Datos del transportista","(si Aplica)","","","",""};
               String[] cabecera21 = new String[]{"Nombre completo"+"\n transportista","Trasnportadora","No. de caja","Firma transportista","",""};
               String[] cabecera22 = new String[]{"Elija el tipo de entrega","Marca con una x","","Marca con una x","",""};
               String[] cabecera23 = new String[]{"Entrega en oficina","","Envio a la residencia","","",""};
               String[] cabecera24 = new String[]{"Datos de quien recibe"," "," "," "," ",""};
               String[] cabecera25 = new String[]{"Nombres y apellidos"," "}; 
               String[] cabecera26 = new String[]{"Numero de identificacion"," "}; 
               String[] cabecera27 = new String[]{"Cuenta"," "}; 
               String[] cabecera28 = new String[]{"Telefono de contacto"," "}; 
               String[] cabecera29 = new String[]{"Contacto alternativo"," "}; 
               String[] cabecera30 = new String[]{"Direccion y Barrio"," ","Municipio"," "}; 
               String[] cabecera31 = new String[]{"Firma de quien"+"\n "+"recibe los equpipos"," ","Huella de quien"+"\n"+"recibe los equpipos"," "}; 
             // --------------------------------------------------------------------------------------------------------------------------------------------- 
             
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            
             CellStyle headerStyle2 = book.createCellStyle();//Para los textos normales
            headerStyle2.setAlignment(HorizontalAlignment.CENTER);
            headerStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle2.setBorderBottom(BorderStyle.THIN);
            headerStyle2.setBorderLeft(BorderStyle.THIN);
            headerStyle2.setBorderRight(BorderStyle.THIN);
            headerStyle2.setBorderBottom(BorderStyle.THIN);

             CellStyle headerStyle3 = book.createCellStyle();//Para los textos normales
            headerStyle3.setBorderBottom(BorderStyle.THIN);
            headerStyle3.setBorderLeft(BorderStyle.THIN);
            headerStyle3.setBorderRight(BorderStyle.THIN);
            headerStyle3.setBorderBottom(BorderStyle.THIN);
            
            
          CellStyle headerStyle4 = book.createCellStyle();//Para los textos normales         
            headerStyle4.setBorderBottom(BorderStyle.THIN);
            
            
                CellStyle headerStyle5 = book.createCellStyle();//Para los textos normales         
            headerStyle5.setAlignment(HorizontalAlignment.CENTER);
            headerStyle5.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle5.setBorderBottom(BorderStyle.THIN);
            headerStyle5.setBorderLeft(BorderStyle.THIN);
            headerStyle5.setBorderRight(BorderStyle.THIN);
            headerStyle5.setBorderBottom(BorderStyle.THIN);
          
             // ---------------------------------------------------------------------------------------------------------------------------------------------
             
             
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 9);
            headerStyle.setFont(font);
            
            Font font2 = book.createFont();
            font2.setFontName("Arial");
            font2.setBold(true);
            font2.setFontHeightInPoints((short)4);
            headerStyle2.setFont(font2);
            
             Font font3 = book.createFont();
            font3.setFontName("Arial");
            font3.setBold(true);
            font3.setFontHeightInPoints((short) 5);
            headerStyle3.setFont(font3);
           
             Font font4 = book.createFont();
            font4.setFontName("Arial");
            font4.setBold(true);
            font4.setFontHeightInPoints((short) 5);
            headerStyle4.setFont(font4);
            
            Font font5 = book.createFont();
            font5.setFontName("Arial");
            font5.setBold(true);
            font5.setFontHeightInPoints((short) 7);
            headerStyle5.setFont(font5);

          //-------------------------------------------------------------------------
            Row filaEncabezados = sheet.createRow(15);//Para la tabla 

            Row filaEncabezados2 = sheet.createRow(5);//Para los textos normales

            Row filaEncabezados3 = sheet.createRow(6);
             
            Row filaEncabezados4 = sheet.createRow(3);
              
            Row filaEncabezados5 = sheet.createRow(2);
              
            Row filaEncabezados6 = sheet.createRow(21);
              
            Row filaEncabezados7 = sheet.createRow(7);
               
            Row filaEncabezados8 = sheet.createRow(8);
                
            Row filaEncabezados9 = sheet.createRow(4);
                 
            Row filaEncabezados10 = sheet.createRow(10);
                   
            Row filaEncabezados11 = sheet.createRow(11);
                   
            Row filaEncabezados12 = sheet.createRow(0);
                     
            Row filaEncabezados13 = sheet.createRow(12);
                     
            Row filaEncabezados14 = sheet.createRow(9);
                      
            Row filaEncabezados15 = sheet.createRow(13);
                       
            Row filaEncabezados16 = sheet.createRow(14);
                        
            Row filaEncabezados17 = sheet.createRow(14);
                        
            Row filaEncabezados171 = sheet.createRow(27);
                          
            Row filaEncabezados18 = sheet.createRow(28);
                        
            Row filaEncabezados19 = sheet.createRow(29);
    
            Row filaEncabezados20 = sheet.createRow(30);
                         
            Row filaEncabezados21 = sheet.createRow(31);
                          
            Row filaEncabezados22 = sheet.createRow(32);
                           
            Row filaEncabezados23 = sheet.createRow(33);
                           
            Row filaEncabezados24 = sheet.createRow(34);
                          
            Row filaEncabezados25 = sheet.createRow(36);
                           
            Row filaEncabezados26 = sheet.createRow(35);
                            
            Row filaEncabezados27 = sheet.createRow(37);
                              
            Row filaEncabezados28 = sheet.createRow(38);
                              
            Row filaEncabezados29 = sheet.createRow(39);
                              
            Row filaEncabezados30 = sheet.createRow(40);
                              
            Row filaEncabezados31 = sheet.createRow(41);
                               
            Row filaEncabezados32 = sheet.createRow(42);
                                
            Row filaEncabezados33 = sheet.createRow(43);
                                 
            Row filaEncabezados34 = sheet.createRow(44);
                                  

           //------------------------------------------------------------------------- 
           
           
           
           for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
            
            
             for (int i = 0; i < cabecera2.length; i++) {
                Cell celdaEnzabezado2 = filaEncabezados2.createCell(i);
                celdaEnzabezado2.setCellStyle(headerStyle3);
                celdaEnzabezado2.setCellValue(cabecera2[i]);
            }

               for (int i = 0; i < cabecera3.length; i++) {
                Cell celdaEnzabezado3 = filaEncabezados3.createCell(i);
                celdaEnzabezado3.setCellStyle(headerStyle3);
                celdaEnzabezado3.setCellValue(cabecera3[i]);
            }
               
                    for (int i = 0; i < cabecera4.length; i++) {
                Cell celdaEnzabezado4 = filaEncabezados4.createCell(i);
                celdaEnzabezado4.setCellStyle(headerStyle3);
                celdaEnzabezado4.setCellValue(cabecera4[i]);
            }
                    
                      for (int i = 0; i < cabecera5.length; i++) {
                Cell celdaEnzabezado5 = filaEncabezados5.createCell(i);
                celdaEnzabezado5.setCellStyle(headerStyle3);
                celdaEnzabezado5.setCellValue(cabecera5[i]);
            }
                      
               for (int i = 0; i < cabecera6.length; i++) {
                Cell celdaEnzabezado6 = filaEncabezados6.createCell(i);
                celdaEnzabezado6.setCellStyle(headerStyle);
                celdaEnzabezado6.setCellValue(cabecera6[i]);
            }
               
                   for (int i = 0; i < cabecera7.length; i++) {
                Cell celdaEnzabezado7 = filaEncabezados7.createCell(i);
                celdaEnzabezado7.setCellStyle(headerStyle3);
                celdaEnzabezado7.setCellValue(cabecera7[i]);
            }
                   
                 for (int i = 0; i < cabecera8.length; i++) {
                Cell celdaEnzabezado8 = filaEncabezados8.createCell(i);
                celdaEnzabezado8.setCellStyle(headerStyle3);
                celdaEnzabezado8.setCellValue(cabecera8[i]);
            }
                 
                 
                for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado9 = filaEncabezados9.createCell(i);
                celdaEnzabezado9.setCellStyle(headerStyle3);
                celdaEnzabezado9.setCellValue(cabecera9[i]);
            }
                     
                for (int i = 0; i < cabecera10.length; i++) {
                Cell celdaEnzabezado10 = filaEncabezados10.createCell(i);
                celdaEnzabezado10.setCellStyle(headerStyle3);
                celdaEnzabezado10.setCellValue(cabecera10[i]);
            }
                             
                for (int i = 0; i < cabecera11.length; i++) {
                Cell celdaEnzabezado11 = filaEncabezados11.createCell(i);
                celdaEnzabezado11.setCellStyle(headerStyle3);
                celdaEnzabezado11.setCellValue(cabecera11[i]);
            }
                
                    for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado9 = filaEncabezados12.createCell(i);
                celdaEnzabezado9.setCellStyle(headerStyle3);
                celdaEnzabezado9.setCellValue(cabecera9[i]);
            }
                   
                    
                 for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado9 = filaEncabezados14.createCell(i);
                celdaEnzabezado9.setCellStyle(headerStyle3);
                celdaEnzabezado9.setCellValue(cabecera9[i]);
            }
                    
                for (int i = 0; i < cabecera12.length; i++) {
                Cell celdaEnzabezado12 = filaEncabezados13.createCell(i);
                celdaEnzabezado12.setCellStyle(headerStyle3);
                celdaEnzabezado12.setCellValue(cabecera12[i]);
            }
                
                    for (int i = 0; i < cabecera13.length; i++) {
                Cell celdaEnzabezado13 = filaEncabezados15.createCell(i);
                celdaEnzabezado13.setCellStyle(headerStyle3);
                celdaEnzabezado13.setCellValue(cabecera13[i]);
            }
                    
                for (int i = 0; i < cabecera14.length; i++) {
                Cell celdaEnzabezado14 = filaEncabezados17.createCell(i);
                celdaEnzabezado14.setCellStyle(headerStyle3);
                celdaEnzabezado14.setCellValue(cabecera14[i]);
            }
                
                
                 for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado15 = filaEncabezados16.createCell(i);
                celdaEnzabezado15.setCellStyle(headerStyle3);
                celdaEnzabezado15.setCellValue(cabecera9[i]);
            }
                 
                 for (int i = 0; i < cabecera15.length; i++) {
                Cell celdaEnzabezado16 = filaEncabezados171.createCell(i);
                celdaEnzabezado16.setCellStyle(headerStyle5);
                celdaEnzabezado16.setCellValue(cabecera15[i]);
            }
                 
                     for (int i = 0; i < cabecera16.length; i++) {
                Cell celdaEnzabezado17 = filaEncabezados18.createCell(i);
                celdaEnzabezado17.setCellStyle(headerStyle);
                celdaEnzabezado17.setCellValue(cabecera16[i]);
            }
                     
                for (int i = 0; i < cabecera17.length; i++) {
                Cell celdaEnzabezado18 = filaEncabezados19.createCell(i);
                celdaEnzabezado18.setCellStyle(headerStyle5);
                celdaEnzabezado18.setCellValue(cabecera17[i]);
            }
                
                  for (int i = 0; i < cabecera18.length; i++) {
                Cell celdaEnzabezado20 = filaEncabezados20.createCell(i);
                celdaEnzabezado20.setCellStyle(headerStyle);
                celdaEnzabezado20.setCellValue(cabecera18[i]);
            }
                  
                       for (int i = 0; i < cabecera19.length; i++) {
                Cell celdaEnzabezado21 = filaEncabezados21.createCell(i);
                celdaEnzabezado21.setCellStyle(headerStyle5);
                celdaEnzabezado21.setCellValue(cabecera19[i]);
            }
                       
                for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado22 = filaEncabezados22.createCell(i);
                celdaEnzabezado22.setCellStyle(headerStyle3);
                celdaEnzabezado22.setCellValue(cabecera9[i]);
            }
                
                 for (int i = 0; i < cabecera20.length; i++) {
                Cell celdaEnzabezado23 = filaEncabezados23.createCell(i);
                celdaEnzabezado23.setCellStyle(headerStyle);
                celdaEnzabezado23.setCellValue(cabecera20[i]);
            }
                 
                 for (int i = 0; i < cabecera21.length; i++) {
                Cell celdaEnzabezado24 = filaEncabezados24.createCell(i);
                celdaEnzabezado24.setCellStyle(headerStyle5);
                celdaEnzabezado24.setCellValue(cabecera21[i]);
            }
                 
                       for (int i = 0; i < cabecera22.length; i++) {
                Cell celdaEnzabezado25 = filaEncabezados25.createCell(i);
                celdaEnzabezado25.setCellStyle(headerStyle);
                celdaEnzabezado25.setCellValue(cabecera22[i]);
            }
                       
               for (int i = 0; i < cabecera9.length; i++) {
                Cell celdaEnzabezado26 = filaEncabezados26.createCell(i);
                celdaEnzabezado26.setCellStyle(headerStyle3);
                celdaEnzabezado26.setCellValue(cabecera9[i]);
            }
                
                 for (int i = 0; i < cabecera23.length; i++) {
                Cell celdaEnzabezado27 = filaEncabezados27.createCell(i);
                celdaEnzabezado27.setCellStyle(headerStyle3);
                celdaEnzabezado27.setCellValue(cabecera23[i]);
            }
                      for (int i = 0; i < cabecera24.length; i++) {
                Cell celdaEnzabezado28 = filaEncabezados28.createCell(i);
                celdaEnzabezado28.setCellStyle(headerStyle);
                celdaEnzabezado28.setCellValue(cabecera24[i]);
            }
                 for (int i = 0; i < cabecera25.length; i++) {
                Cell celdaEnzabezado29 = filaEncabezados29.createCell(i);
                celdaEnzabezado29.setCellStyle(headerStyle5);
                celdaEnzabezado29.setCellValue(cabecera25[i]);
            }
             
                         
                for (int i = 0; i < cabecera26.length; i++) {
                Cell celdaEnzabezado30 = filaEncabezados30.createCell(i);
                celdaEnzabezado30.setCellStyle(headerStyle5);
                celdaEnzabezado30.setCellValue(cabecera26[i]);
            }
                
                    for (int i = 0; i < cabecera27.length; i++) {
                Cell celdaEnzabezado31 = filaEncabezados31.createCell(i);
                celdaEnzabezado31.setCellStyle(headerStyle5);
                celdaEnzabezado31.setCellValue(cabecera27[i]);
            }
                    
                              for (int i = 0; i < cabecera28.length; i++) {
                Cell celdaEnzabezado32 = filaEncabezados32.createCell(i);
                celdaEnzabezado32.setCellStyle(headerStyle5);
                celdaEnzabezado32.setCellValue(cabecera28[i]);
            }
                              
               for (int i = 0; i < cabecera29.length; i++) {
                Cell celdaEnzabezado33 = filaEncabezados33.createCell(i);
                celdaEnzabezado33.setCellStyle(headerStyle5);
                celdaEnzabezado33.setCellValue(cabecera29[i]);
            }
               
                    for (int i = 0; i < cabecera30.length; i++) {
                Cell celdaEnzabezado34 = filaEncabezados34.createCell(i);
                celdaEnzabezado34.setCellStyle(headerStyle5);
                celdaEnzabezado34.setCellValue(cabecera30[i]);
            }
                    
                for (int i = 0; i < cabecera31.length; i++) {
                Cell celdaEnzabezado34 = filaEncabezados34.createCell(i);
                celdaEnzabezado34.setCellStyle(headerStyle5);
                celdaEnzabezado34.setCellValue(cabecera31[i]);
            }
           //-------------------------------------------------------------------------
             
            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            int numFilaDatos = 16;

           
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            Font celdas1 = book.createFont();
            celdas1.setFontName("Arial");
            celdas1.setBold(true);
            celdas1.setFontHeightInPoints((short) 7);
            datosEstilo.setFont(celdas1);

            ps = conn.prepareStatement("SELECT id_Articulo, Marca_Pantalla, Tec_Pantalla, Tec_Torre, Marca_Torre, Marca_Raton FROM listaarticulo");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);

                for (int a = 0; a < numCol; a++) {

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }


                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            
            
            //-------------------------------------------------------------------------
            
           int numFilaDatos2 = 22;

           
            CellStyle datosEstilo2 = book.createCellStyle();
            datosEstilo2.setBorderBottom(BorderStyle.THIN);
            datosEstilo2.setBorderLeft(BorderStyle.THIN);
            datosEstilo2.setBorderRight(BorderStyle.THIN);
            datosEstilo2.setBorderBottom(BorderStyle.THIN);
            Font celdas12 = book.createFont();
            celdas12.setFontName("Arial");
            celdas12.setBold(true);
            celdas12.setFontHeightInPoints((short) 7);
            datosEstilo2.setFont(celdas12);

            ps = conn.prepareStatement("SELECT Marca_Teclado, Marca_Diademas, Marca_Camara, Adaptador, Fecha, Acta FROM listaarticulo");
            rs = ps.executeQuery();

            int numCol2 = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos2);

                for (int a = 0; a < numCol2; a++) {

                    Cell CeldaDatos2 = filaDatos.createCell(a);
                    CeldaDatos2.setCellStyle(datosEstilo2);
                    CeldaDatos2.setCellValue(rs.getString(a + 1));
                }


                numFilaDatos2++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            

            
            
            sheet.setZoom(150);
            String fileName = "Reporte de Articulos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
