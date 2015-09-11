package Controlador;



import Modelo.ModContro;
import Modelo.ModReenviar;
import Vista.FrmReenviar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Controlador_Reenviar implements ActionListener,MouseListener{
    FrmReenviar VtnRegistro ;
    ModReenviar modelo = new ModReenviar();//validad el boton ingresar                 
       
   public String cambiarFormatoFecha(Date fecha){
       SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String formattedTime = output.format(fecha);
       return formattedTime;
   }
    
    public enum AccionMVC{        
        __Enviar,
        __Nuevo_Registro
        
    }
    
    

   public Controlador_Reenviar(FrmReenviar vista){
        this.VtnRegistro = vista;
        for (int i = 0; i < modelo.LlenarCombo().length; i++) {            
            vista..addItem(modelo.LlenarCombo()[i]);            
        }
    }
    
   
        
    
        
    public void iniciar(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows."
                    + "WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(VtnRegistro);
            VtnRegistro.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(VtnRegistro, "Error de driver de "
                      + "video: "+ex.getMessage());
          }
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
       
        this.VtnRegistro.jBTNhh.setActionCommand("__Nuevo_Registro");
        this.VtnRegistro.jBTNhh.addActionListener(this);
        
        
    }

    public void mouseClicked(MouseEvent e) {
       //boton izquierdo
                                
    }
    

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())){
             
                 
          
             
                case __Enviar:{                     
                    if (this.modelo.NuevoReparto(this.VtnRegistro.JtexaRadicado.getText(),
                            cambiarFormatoFecha(this.VtnRegistro.jDateChooser1.getDate()),
                            cambiarFormatoFecha(this.VtnRegistro.jDateChooser2.getDate()),
                            this.VtnRegistro.jTextObservacion.getText(),
                            this.VtnRegistro.Asunto.getText(),  
                             this.VtnRegistro.jTextConsteado.getText(),
                            modelo.retornarCodigo((String)this.VtnRegistro.jComboBox1.getSelectedItem()),
                            this.VtnRegistro.jTexRemitente.getText(),
                             (String)this.VtnRegistro.jComboEstado.getSelectedItem(),
                            (String)this.VtnRegistro.jComboRespuesta.getSelectedItem(),
                            (String)this.VtnRegistro.jTextAreaRecibido.getText())){
                    
                            JOptionPane.showMessageDialog(VtnRegistro,"Registro creado!.");
                        
                    }else{
//                        JOptionPane.showMessageDialog(VtnRegistro,"Registro NOOOOO creado!.");
                        break;
                    }
                            
                        
                            this.VtnRegistro.JtexaRadicado.setText("");
//                            this.VtnRegistro.jDateChooser1.setDate(null);
                            this.VtnRegistro.jDateChooser2.setDate(null);
                            this.VtnRegistro.jTextObservacion.setText("");
                             this.VtnRegistro.Asunto.setText("");
                              this.VtnRegistro.jTextConsteado.setText("");
                           
                    this.VtnRegistro.jComboBox1.setSelectedItem("");
                         this.VtnRegistro.jTexRemitente.setText("");
                                    this.VtnRegistro.jComboEstado.setSelectedItem("");
                    this.VtnRegistro.jComboRespuesta.setSelectedItem(null);
                    this.VtnRegistro.jTextAreaRecibido.setText("");
//                            
                         
                }
                       
                    
//                        JOptionPane.showMessageDialog(VtnRegistro,"Datos incorrectos!.");
                    break;
                
                   
                }
            }
                 
        }


