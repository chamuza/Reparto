/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.FrmReparto;
import Modelo.ModContro;
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

/**
 *
 * @author Sala319
 */
public class Controlador_Reparto implements ActionListener,MouseListener{
     FrmReparto VtnReparto ;
    ModContro modelo = new ModContro();//validad el boton ingresar
    
 public String cambiarFormatoFecha(Date fecha){
           SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String formattedTime = output.format(fecha);
           return formattedTime;
       
       }
    public enum AccionMVC{            
        
        __AGREGAR_REPARTO,
        __Nuevo_Reparto,
        __AÑADIR,
        __REENVIAR_CORRESPONDENCIA, 
        __Radicado
     
    }
      public Controlador_Reparto(FrmReparto vista){
          this.VtnReparto = vista;
          
      }   
      public Calendar FormatoHora(){
    Calendar calendario = Calendar.getInstance();
        int  hora, minutos, segundos;
                                                                                                                                                                
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        return calendario;
        
        
    }
    public void iniciar(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows."
                    + "WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(VtnReparto);
            VtnReparto.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(VtnReparto, "Error de driver de "
                      + "video: "+ex.getMessage());
          }
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
        this.VtnReparto.jBtnReenviar.setActionCommand("__REENVIAR_CORRESPONDENCIA");
        this.VtnReparto.jBtnReenviar.addActionListener(this);
        
        this.VtnReparto.jTextRadicado.setActionCommand("__Radicado");
        this.VtnReparto.jTextRadicado.addActionListener(this);
        
        this.VtnReparto.JBCAgregar.setActionCommand("__AGREGAR_REPARTO");
        this.VtnReparto.JBCAgregar.addActionListener(this);
        
        this.VtnReparto.jBtnAñadir.setActionCommand("__AÑADIR");
        this.VtnReparto.jBtnAñadir.addActionListener(this);
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
                case __Radicado:{
                
                  this.VtnReparto.jTextRadicado.setText(this.modelo.numRadicado());
//                  this.VtnReparto.jTextObservacion.setEnabled(true);
                  
             }
                case __AGREGAR_REPARTO:{                    
                    if (this.modelo.NuevoReparto(cambiarFormatoFecha(this.VtnReparto.jDateChooser1.getDate()),
                            (this.VtnReparto.jTextRadicado.getText()),
                            this.VtnReparto.jTxtDocumento.getText(),
                            (String) this.VtnReparto.jEst_Repar.getSelectedItem(),
                            (String)this.VtnReparto.jComboBoxResp.getSelectedItem(),
                            this.VtnReparto.jTextObservacion.getText(),
                            this.VtnReparto.jTxtDocuReen.getText())){
                            this.VtnReparto.TablaDatos.setModel(this.modelo.
                                    getTablaDatos());
                }

                      
                        
                           
             JOptionPane.showMessageDialog(VtnReparto, this.VtnReparto.jDateChooser1.getDate());
           
                       JOptionPane.showMessageDialog(VtnReparto,"Reparto creado!.");
                        this.VtnReparto.jTextRadicado.setText("");
                         this.VtnReparto.jDateChooser1.setDate(null);
                          this.VtnReparto.jTxtDocumento.setText("");
                            this.VtnReparto.jEst_Repar.setSelectedItem("");
                           this.VtnReparto.jComboBoxResp.setSelectedItem("");
                         this.VtnReparto.jTextObservacion.setText("");
                        this.VtnReparto.jTxtDocuReen.getText();
                          this.VtnReparto.TablaDatos.setModel(this.modelo.
                               getTablaDatos());
                           
                }
                
                break;
                case __Nuevo_Reparto:{
                
                  this.VtnReparto.jTextRadicado.setText(this.modelo.numRadicado());
//                   
                 break;
                 }
                
                 case __AÑADIR:{
                 
                     this.VtnReparto.jBtnAñadir.setText(this.VtnReparto.toString());
                }
             }
        }
    }    

                         