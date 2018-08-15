/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
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
    //metodo que permite buscar un persona por cedula
    public Persona ObtenerPersonaCedula(String cedula){
        Persona p = null;
        //el try es para que no se caiga el programa
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p Where p.cedula = :cedula");//es creatyQuery es una consulta a nivel de objetos
            q.setParameter("cedula", cedula);
            p = (Persona)q.getSingleResult();
        } catch (Exception e) {
        }
        return p;
    }
}
