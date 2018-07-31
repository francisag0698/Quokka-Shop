/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Rol;

/**
 *
 * @author acer
 */
public class RolDao extends AdaptadorDao<Rol> {

    private Rol rol;

    public RolDao() {
        super(new Conexion(), Rol.class);
    }

    public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

    public void fijarInstancia(Rol rol) {
        this.rol = rol;
    }

    public boolean guardar() {
        boolean band = false;
        try {
            if (rol.getId() != null) {
                //modificar
            } else {
                this.rol.setId(generarID());
                this.guardar(rol);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar" + e);
        }
        return band;
    }
}
