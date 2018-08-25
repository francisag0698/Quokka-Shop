/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import od.modelo.Persona;

/**
 *
 * @author Dennis
 */
public class PersonaDao extends AdaptadorDao<Persona>{
    private Persona persona;

    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if(persona==null)
           persona= new Persona();
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(persona.getId_persona()!=null){
                modificar(persona);
            }else{
                guardar(persona);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    
    
    public Persona obtenerPersonaCedula(String cedula){
        Persona p = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.dni = :cedula");
            q.setParameter("cedula", cedula);
            p = (Persona)q.getSingleResult();
        } catch (Exception e) {
        }
        return p;
    }
    
    public List<Persona> listarSinAdministrador(){
        List<Persona> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :admin AND p.rol.nombre != :res");
            q.setParameter("admin", "Administrador");
            q.setParameter("res", "Recepcionista");
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("Meth: listarSinAdministrador()");
        }
        return lista;
    }
    
    public List<Persona> listarSinAdministradorBusqueda(String texto){
        List<Persona> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :admin AND p.rol.nombre != :res "
                            + "AND (LOWER(p.nombres) LIKE CONCAT('%', :texto, '%') "
                            + "OR LOWER(p.apellidos) LIKE CONCAT('%', :texto, '%'))");
            q.setParameter("admin", "Administrador");
            q.setParameter("res", "Recepcionista");
            q.setParameter("texto", texto);
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("Meth: listarSinAdministrador()");
        }
        return lista;
    }
    
    public List<Persona> listarSinAdministradorDNIBusqueda(String texto){
        List<Persona> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :admin AND p.rol.nombre != :res "
                            + "AND  p.dni LIKE CONCAT('%', :texto, '%')");
            q.setParameter("admin", "Administrador");
            q.setParameter("res", "Recepcionista");
            q.setParameter("texto", texto);
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("Meth: listarSinAdministrador()");
        }
        return lista;
    }
    
    public List<Persona> listarSinAdministradorGeneroBusqueda(String texto, String genero){
        List<Persona> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT p FROM Persona p WHERE p.rol.nombre != :admin AND p.rol.nombre != :res "
                            + "AND  p.sexo = :genero "
                            + "AND (LOWER(p.nombres) LIKE CONCAT('%', :texto, '%') "
                            + "OR LOWER(p.apellidos) LIKE CONCAT('%', :texto, '%'))");
            q.setParameter("admin", "Administrador");
            q.setParameter("res", "Recepcionista");
            q.setParameter("texto", texto);
            q.setParameter("genero", genero);
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("Meth: listarSinAdministrador()");
        }
        return lista;
    }
}
