/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.IOException;
import od.controlador.Conexion;
import od.modelo.Cuenta;

/**
 *
 * @author Francis
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {
    private Cuenta cuenta;

    public CuentaDao() {
        super(new Conexion (), Cuenta.class);
    }
    
    public Cuenta getCuenta() {
        if (cuenta == null)
            cuenta = new Cuenta();
        return cuenta;
    }
    
    public void fijarInstancia(Cuenta cuenta){
        this.cuenta = cuenta;
    }
    
    public boolean guardar(){
        boolean band = false;
        try {
            if (cuenta.getId() != null) {
                
            }else{
                this.cuenta.setId(generarID());
                this.guardar(cuenta);
            }
            band = true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar "+e);
        }
        return band;
    }
    
    public Cuenta inicioSesion(String usuario, String clave){
        Cuenta admin = null;
        for (Cuenta aux : listar()) {
            if (aux.getUsuario().equals(usuario) && aux.getClave().equals(clave)) {
                admin = aux;
            }
        }
        return admin;
    }
}
