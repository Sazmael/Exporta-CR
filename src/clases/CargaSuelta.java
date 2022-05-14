package clases;

import java.util.Date;

public class CargaSuelta extends CalculoExportacion {
    double piesCarga;
    String tipoServicio;

    public CargaSuelta() {
    }

    public CargaSuelta(double piesCarga, String tipoServicio) {
        this.piesCarga = piesCarga;
        this.tipoServicio = tipoServicio;
    }

    public CargaSuelta(double piesCarga, String tipoServicio, int id_cotizacion, Date fecha, Double kg,Double costo, String zona_exportacion, String zona_entrega, Double gramos, Double libras, Double toneladas) {
        super(id_cotizacion, fecha, kg, costo, zona_exportacion, zona_entrega, gramos, libras, toneladas);
        this.piesCarga = piesCarga;
        this.tipoServicio = tipoServicio;
    }

    public double getPiesCarga() {
        return piesCarga;
    }

    public void setPiesCarga(double piesCarga) {
        this.piesCarga = piesCarga;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
   public static double costoSuelta(double costo,String tipoServicio, double piesCarga, Double kg) { 
     
       //Formula Carga Suelta por barco
        if(tipoServicio == "Barco" && piesCarga <= 18){
           costo = 50 * kg;
            
        }
        //Formula Carga Suelta por barco con excendente
        if(tipoServicio == "Barco" && piesCarga >= 18){
           costo = 50 * kg;
           piesCarga = 15 * piesCarga;
           costo = (piesCarga - 270)+costo; 
            
        }
        
         //Formula Carga Suelta por avión
        if(tipoServicio == "Avión" && piesCarga <= 18){
            
            costo = 100 * kg;
        }
        //Formula Carga Suelta por avión con excedente
        if(tipoServicio == "Avión" && piesCarga >= 18){
           costo = 100 * kg;
           piesCarga = 15 * piesCarga;
           costo = (piesCarga - 270)+costo;
            
        }
            
            
        return costo;
    }
 
    
    
}
