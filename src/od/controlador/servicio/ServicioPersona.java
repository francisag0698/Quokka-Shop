/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.PersonaDao;
import od.modelo.Persona;

/**
 *
 * @author Dennis
 */
public class ServicioPersona {
    private PersonaDao obj= new PersonaDao();
    
    public Persona getPersona(){
        return obj.getPersona();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Persona>todos(){
        return obj.listar();
    }
    
    public Persona obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarPersona(Persona persona){
        obj.setPersona(persona);
    }
    
    public Persona ObtenerPersonaCedula(String cedula){
        return obj.obtenerPersonaCedula(cedula);
        
    }
    
    public List<Persona> listarSinAdministrador(){
        return obj.listarSinAdministrador();
    }
    
    public List<Persona> listarSinAdministradorBusqueda(String texto){
        return obj.listarSinAdministradorBusqueda(texto);
    }
    
    public List<Persona> listarSinAdministradorDNIBusqueda(String DNI){
        return obj.listarSinAdministradorDNIBusqueda(DNI);
    }
    
    public List<Persona> listarSinAdministradorGeneroBusqueda(String texto, String genero){
        return obj.listarSinAdministradorGeneroBusqueda(texto, genero);
    }
}
