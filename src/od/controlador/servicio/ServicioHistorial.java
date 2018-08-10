/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.HistorialDao;
import od.modelo.Historial;

/**
 *
 * @author Dennis
 */
public class ServicioHistorial {
    private HistorialDao obj;

    public ServicioHistorial() {

    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Historial obtenerHistorial() {
        return obj.getHistorial();
    }

    public Historial obtenerHistorial(Long id) {
        return obj.obtener(id);
    }

    public List<Historial> listado() {
        return obj.listar();
    }
}
