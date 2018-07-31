/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;

import java.util.List;
import od.controlador.dao.CuentaDao;
import od.modelo.Cuenta;

/**
 *
 * @author acer
 */
public class ServicioCuenta {

    private CuentaDao obj;

    public ServicioCuenta() {
        this.obj = new CuentaDao();
    }

    public boolean guardar() {
        return this.obj.guardar();
    }

    public Cuenta obtenerCuenta() {
        return obj.getCuenta();
    }

    public Cuenta obtenerCuenta(Long id) {
        return obj.obtener(id);
    }

    public List<Cuenta> listado() {
        return obj.listar();
    }

    public Cuenta inicioSesion(String usuario, String clave) {
        return obj.inicioSesion(usuario, clave);
    }
}
