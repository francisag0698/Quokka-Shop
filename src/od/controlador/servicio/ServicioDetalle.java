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
public class ServicioDetalle {
    private ServicioDao obj;

    public ServicioDetalle() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Servicio obtenerDetalle() {
        return obj.getServicio();
    }

    public Servicio obtenerDetalle(Long id) {
        return obj.obtener(id);
    }

    public List<Servicio> listado() {
        return obj.listar();
    }
}
