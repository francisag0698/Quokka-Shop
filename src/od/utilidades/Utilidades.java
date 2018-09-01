/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import od.controlador.servicio.ServicioHistorial;
import od.modelo.Persona;

/**
 * Clase que permite utilizar metodos dentro de las vistas
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class Utilidades {
    /**
     * Metodo que permite guardar un historial de persona
     * @param identificador acepta un dato identificador de tipo String
     * @param accion  acepta un dato accion de tipo String
     * @param persona acepta un dato persona de tipo Persona
     */
    public static void guardarHistorial(String identificador,String accion, Persona persona) {
        ServicioHistorial hs = new ServicioHistorial();
        hs.getHistorial().setIdentificador(identificador);
        hs.getHistorial().setAccion(accion);
        hs.getHistorial().setFecha(new Date());
        hs.getHistorial().setPersona(persona);
        hs.guardar();
    }//Cierre del metodo guardarHistorial
    
    /**
     * Metodo que permite extraer la fecha actual
     * @param a acepta un dato extraido de la fecha actual 
     * @return devuelve la nueva fecha
     */
    public static Date extraerFecha(LocalDate a){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(a.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (ParseException e) {
            return new Date();
        }
    }//Cierre del metodo extraerFecha
    
    /**
     * Metodo que permite dar formato a la fecha
     * @param fecha acepta un dato de tipo fecha
     * @return devuelve una fecha salida formateada
     */
    public static String formatearFecha(Date fecha){
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo formatear la fecha...");
        }
        return fechaSalida;
    }//Cierre del metodo
    
    /**
     * Metodo que permite dar formato a una fecha
     * @param fecha acepta un dato de tipo fecha
     * @return devuelve una fecha salida formateada
     */
    public static String formatearFechaDos(Date fecha){
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo formatear la fecha...");
        }
        return fechaSalida;
    }//Cierre del metodo
}
