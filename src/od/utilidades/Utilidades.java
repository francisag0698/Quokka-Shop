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
import od.controlador.servicio.ServicioPersona;

/**
 *
 * @author USUARIO
 */
public class Utilidades {
    public static void guardarHistorial(String identificador,String accion, ServicioPersona sp) {
        ServicioHistorial hs = new ServicioHistorial();
        hs.getHistorial().setIdentificador(identificador);
        hs.getHistorial().setAccion(accion);
        hs.getHistorial().setFecha(new Date());
        hs.getHistorial().setPersona(sp.getPersona());
        hs.guardar();
    }
    
    public static Date extraerFecha(LocalDate a){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(a.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (ParseException e) {
            return new Date();
        }
    }
    
    public static String formatearFecha(Date fecha){
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo formatear la fecha...");
        }
        return fechaSalida;
    }
    
    public static String formatearFechaDos(Date fecha){
        String fechaSalida = "";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            fechaSalida = formato.format(fecha);
        } catch (Exception e) {
            System.out.println("No se pudo formatear la fecha...");
        }
        return fechaSalida;
    }
}
