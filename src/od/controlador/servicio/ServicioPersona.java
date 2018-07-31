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
 * @author acer
 */
public class ServicioPersona {

    private PersonaDao obj;

    public ServicioPersona() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Persona obtenerPersona() {
        return obj.getPersona();
    }

    public Persona obtenerPersona(Long id) {
        return obj.obtener(id);
    }

    public List<Persona> listado() {
        return obj.listar();
    }
}
