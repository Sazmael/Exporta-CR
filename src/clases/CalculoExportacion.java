package clases;

import java.util.Date;

public class CalculoExportacion {

    int id_cotizacion;
    Date fecha;
    Double kg;
    Double gramos = 1.000;
    Double libras = 2.205;
    Double toneladas = 1.000;
    String zona_exportacion, zona_entrega;
    double costo = 0;

    //Se inicia los constructores para la manipulacion de los atributos del objeto
    public CalculoExportacion() {
    }
 
    
    public CalculoExportacion(int id_cotizacion, Date fecha, Double kg,double costo, String zona_exportacion, String zona_entrega,Double gramos, Double libras, Double toneladas) {
        this.id_cotizacion = id_cotizacion;
        this.fecha = fecha;
        this.kg = kg;
        this.costo = costo;
        this.zona_exportacion = zona_exportacion;
        this.zona_entrega = zona_entrega;
        this.gramos = gramos;
        this.libras = libras;
        this.toneladas = toneladas;
        
        
    }

    //Se inicia los Getters and Setters de los atributos del objeto
    public int getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public String getZona_exportacion() {
        return zona_exportacion;
    }

    public void setZona_exportacion(String zona_exportacion) {
        this.zona_exportacion = zona_exportacion;
    }

    public String getZona_entrega() {
        return zona_entrega;
    }

    public void setZona_entrega(String zona_entrega) {
        this.zona_entrega = zona_entrega;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Double getGramos() {
        return gramos;
    }

    public void setGramos(Double gramos) {
        this.gramos = gramos;
    }

    public Double getLibras() {
        return libras;
    }

    public void setLibras(Double libras) {
        this.libras = libras;
    }

    public Double getToneladas() {
        return toneladas;
    }

    public void setToneladas(Double toneladas) {
        this.toneladas = toneladas;
    }

    //Esta es la función utilizada para generar de forma aleatoria el identificador de cotización.  
    public static int aleatorio(int cotizacion) { 
        cotizacion = (int) (Math.random() * 1000);
        return cotizacion;
    }
    
    //Función para calcular el costo por Kilogramo
    
    
    //Función para desglosar los Kilogramos a gramos, libras y toneladas
    public static double gramos(double gramos, double kg) { 
        
        gramos = kg * 1000;
        return gramos;
    }
    
    public static double libras(double libras, double kg) { 
        
        libras = kg * libras;
        return libras;
    }
    
    public static double toneladas(double toneladas, double kg) { 
        
        toneladas = kg / 1000;
        return toneladas;
    }
    
}
