/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.MiModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sala319
 */
public class ModContro extends Conexion{
    
    
    public DefaultTableModel getTablaDatos(){
        MiModelo tablemodel = new MiModelo();
      //  DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Nume_Radi_Entr","Fech_Repa","Documento","Esta_Repa","Respuesta"
        ,"Observaciones","Docu_Reen"};
        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT"
                    +"Count(*) as total FROM Reparto;");
            ResultSet resultado = pstm.executeQuery();
            resultado.next();
            registros = resultado.getInt("total");
            resultado.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT *"
                    + "FROM Reparto");
            ResultSet resultado = pstm.executeQuery();
            int i=0;
            while(resultado.next()){
                data[i][0] = resultado.getString("Nume_Radi_Entr");
                data[i][1] = resultado.getString("Fech_Repa");
                data[i][2] = resultado.getString("Documento");
                data[i][3] = resultado.getString("Esta_Repa");
                data[i][4] = resultado.getString("Respuesta");
                data[i][5] = resultado.getString("Observaciones");
                data[i][6] = resultado.getString("Docu_Reen");
                i++;
                
            }
            resultado.close();
            
            tablemodel.setDataVector(data, columNames);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }    
      //  }"+"date_format("+Fech_Elab+",'%Y/%m/%d')//
        return tablemodel;
                  
    }
 
       public boolean NuevoReparto(String Fech_Repa , String Nume_Radi_Entr,String Documento ,String Esta_Repa, String Respuesta , String Observaciones, String Docu_Reen){
          if(Valida_Datos ( Fech_Repa ,Nume_Radi_Entr, Documento, Esta_Repa, Respuesta, Observaciones, Docu_Reen))
          {
       
                      
              String SQl = "Insert into Reparto "
                      + "Values('"+Fech_Repa+"','"+Nume_Radi_Entr+"','"+Documento+"','"+Esta_Repa+"','"+Respuesta+"','"+Observaciones+"','"+Docu_Reen+"');";
                      JOptionPane.showMessageDialog(null, SQl);
              try {
                  PreparedStatement pstm = this.getConexion().prepareStatement(SQl);
                  pstm.execute();
                  pstm.close();
                  return true;
              } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
              }
              return false;
          }
          else 
              return false;
      }
       
        private boolean Valida_Datos ( String Fech_Repa ,String Nume_Radi_Entr, String Documento ,String Esta_Repa, String Respuesta , String Observaciones, String Docu_Reen){
              if (Nume_Radi_Entr.length()> 0  && Documento.length() > 0 && Esta_Repa.length() > 0 && Respuesta.length() > 0 && Observaciones.length() > 0 && Docu_Reen.length() > 0) {
                  return true;
              }
              else {
                  return false;
              }
          }
        
         
    
       public String numRadicado (){           
          String anterior_radicado = "";
          Calendar fecha = new GregorianCalendar();
          int xxx = 0;
          
          String anio = String.valueOf(fecha.get(Calendar.YEAR));
          String mes = String.valueOf(fecha.get(Calendar.MONTH)+1);              
          
          if(mes.length() == 1){
              mes="0"+mes;
          }
        
        String sql = "SELECT max(Nume_Radi_Entr) as radicado FROM correspondencia_entrada;";                        
                
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();
            anterior_radicado = String.valueOf(Resultado.getInt("radicado"));                           
            Resultado.close();                                 
               
            String mes_anterior = anterior_radicado.substring(2, 4);            
            
   
            
            if(!mes_anterior.equals(mes)){
                anterior_radicado = anio.substring(2, 4)+mes+"0001";
                
              
                
            }else{
        
                
                xxx = Integer.parseInt(anterior_radicado.substring(5, 8));            
                ++xxx;  
                String consecutivo = String.valueOf(xxx); 
                                                            
                int longitud = consecutivo.length();
               
                if (consecutivo.length() == 4){
                     consecutivo = String.valueOf(xxx);
                }
                if(longitud == 1){
                     consecutivo = "000"+String.valueOf(xxx);                                           
                }
                if(longitud == 2){
                         consecutivo = "00"+ xxx;
                }
                if(longitud == 3){
                         consecutivo = "0"+ xxx;
                }                                                  
           
                anterior_radicado = anio.substring(2)+mes+consecutivo;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
           return anterior_radicado;
      }
        
   }