
package od.controlador.dao;
import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Persona;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Francis
 */
public class PersonaDao extends AdaptadorDao<Persona> {

    private Persona persona;

    public PersonaDao() {
        super(new Conexion(), Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void fijarInstancia(Persona persona) {
        this.persona = persona;
    }

    public boolean guardar() {
        boolean band = false;
        try {
            if (persona.getId_persona() != null) {
                
            } else {
                this.persona.setId_persona(generarID());
                this.guardar(persona);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar " + e);
        }
        return band;
    }
    
   /*{
        Persona persona=null;
        for(Persona aux:listar()){
            if (aux.getId().equals(id)) {
                persona = aux;
            }    
        }
        return persona;
    } */

}
