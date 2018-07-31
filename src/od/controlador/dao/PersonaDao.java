
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
            if (persona.getId() != null) {
                
            } else {
                this.persona.setId(generarID());
                this.guardar(persona);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar " + e);
        }
        return band;
    }

}
