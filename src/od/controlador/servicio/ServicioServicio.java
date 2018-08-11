/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.ServicioDao;
import od.modelo.Servicio;

/**
 *
 * @author Dennis
 */
public class ServicioServicio {
    private ServicioDao obj;

    public ServicioServicio() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Servicio obtenerServicio() {
        return obj.getServicio();
    }

    public Servicio obtenerServicio(Long id) {
        return obj.obtener(id);
    }

    public List<Servicio> listado() {
        return obj.listar();
    }
}
