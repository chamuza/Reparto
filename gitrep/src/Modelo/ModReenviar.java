/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sala319
 */
public class ModReenviar extends Conexion{
  
    
    
    public  DefaultTableModel getTabla(){
        DefaultTableModel tableModel = new DefaultTableModel();
        int Registros = 0;
        String[] ColumNames = {"Codi_Ofic","Nomb_Ofic","Telefono","Email"};
    
    
    
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT "
                    + "count(*) as Total FROM Reparto;");
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();
            Registros = Resultado.getInt("Total");
            Resultado.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        Object[][] data = new String[Registros][4];
          try {
              PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * "
                                + "FROM reparto");
              ResultSet resultado = pstm.executeQuery();
              int i=0;
              while(resultado.next()){
                  data[i][0] = resultado.getString("Codi_Ofic");
                  data[i][1] = resultado.getString("Nomb_Ofic");
                  data[i][2] = resultado.getString("Telefono");
                  data[i][3] = resultado.getString("Email");
                  i++;
                  
              }
              resultado.close();
              
          tableModel.setDataVector(data, ColumNames);
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
          
          return tableModel;
          
      }
    public String[] llenarCombo(){
        String[] nomb_ofic = new String[33];
        
        String sql ="select documento from empleados order by documento" ;
        
        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                nomb_ofic[i] = resultado.getString("documento");
                i++;
            }
            resultado.close();
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
        return nomb_ofic;
    }
         public String[] LlenarCombo(){
       String SQL = "Select documento from empleados;";
          int i = 0;
          try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();
                while(Resultado.next()){
                  i++;
                }               
          } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
          }
          
          String[] Combo = new String[i];
            
          SQL = "Select documento From  empleados order by documento;";
            
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();
                i = 0;
                while(Resultado.next()){
                  Combo[i] = Resultado.getString("documento");                  
                  i++;
                }
                Resultado.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return Combo;  
      }
   

     

public boolean NuevoReenviar(String Fech_Repa , String Documento ,String Docu_Reen, String Respuesta , String Observaciones){
          if(Valida_Datos ( Fech_Repa , Documento, Docu_Reen, Respuesta, Observaciones))
          {
       
                      
              String SQl = "Insert into Reparto "
                      + "Values('"+Fech_Repa+"','"+Documento+"','"+Docu_Reen+"','"+Respuesta+"','"+Observaciones+"');";
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
 private boolean Valida_Datos (String Fech_Repa, String Documento, String Docu_Reen, String respuesta ,String Observaciones ){
     
         if (Fech_Repa.length() > 0 && Documento.length() > 0  && Docu_Reen.length() > 0 && respuesta.length() > 0 && Observaciones.length() > 0) {
                  return true;
              }
              else {
                  return false;
              }
          }
      }

   // public Object Llenarjtexoficina() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        


