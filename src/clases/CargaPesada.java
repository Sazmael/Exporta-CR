package clases;

import java.util.Date;
import javax.swing.JOptionPane;

public class CargaPesada extends CalculoExportacion {

    String tipoCarga;
    String piesContenedor;
    String tipoServicio;
    
    public CargaPesada() {
    }

    public CargaPesada(String tipoCarga, String piesContenedor, String tipoServicio, int id_cotizacion, Date fecha, Double kg,Double costo, String zona_exportacion, String zona_entrega, Double gramos, Double libras, Double toneladas) {
        super(id_cotizacion, fecha, kg, costo,zona_exportacion, zona_entrega, gramos, libras, toneladas);
        this.tipoCarga = tipoCarga;
        this.piesContenedor = piesContenedor;
        this.tipoServicio = tipoServicio;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public String getPiesContenedor() {
        return piesContenedor;
    }

    public void setPiesContenedor(String piesContenedor) {
        this.piesContenedor = piesContenedor;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
   
    
 public static double costoPesada(double kg,String piesContenedor,String tipoCarga,String tipoServicio,double costo) { 
     costo = 0;
    //Pies contenedor 20 pies vacio
     if(piesContenedor == "20" && kg <= 1500){
        costo = kg * 20; 
        
         
       }
     if(piesContenedor == "20" && kg >= 1501){
         /*Formula para contenedor 20 pies adicional
         1500kg x 20 dolares = 30,000
         1500kg x 85 dolares = 127,500
         por lo tanto 127,500 - 30,000 = 97,500
         costo = (kg * costo) - 90,000
         Lo cual nos sirve para rebajar el costo ya obtenido a 20 dolares hasta 1500kg*/
          costo = (kg * 85) - 97500;  
     }  
     
     //Formula para contenedor 40 pies vacio
     if(piesContenedor == "40" && kg <= 3750){

        costo = kg * 20;   
           
       }
     //Formula para contenedor 40 pies adicional
     if(piesContenedor == "40" && kg >= 3751){
         
        costo = (kg * 75) - 206250;
     }
     
     if(tipoCarga == "Carga embalada" && tipoServicio == "Avión"){
         //Carga embalada en avión 
         costo = kg * 125;
    
        } 
     if(tipoCarga == "Carga embalada" && tipoServicio == "Barco"){
         //Carga embalada en barco
            costo = (kg * 75);
 
     }

        return costo;
    } 
      
    
} 
    
