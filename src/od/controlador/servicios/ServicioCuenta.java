/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicios;
import java.util.List;
import od.modelo.Cuenta;
import od.controlador.daos.CuentaDao;

/**
 *
 * @author Dennis
 */
public class ServicioCuenta {
     private CuentaDao obj = new CuentaDao();
    
    public Cuenta getCuenta(){
        return obj.getCuenta();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Cuenta>todos(){
        return obj.listar();
    }
    
    public Cuenta obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarCuenta(Cuenta cuenta){
        obj.setCuenta(cuenta);
    }
    /*
    public void crearCuentaAdmin(){
        if(todos().isEmpty()){
            ServicioCuenta servicio = new ServicioCuenta();
            servicio.getCuenta().setUsuario("Dennis");
            servicio.getCuenta().setCedula("22222222");
            servicio.getCuenta().setDireccion("Loja");
            servicio.getCuenta().setExternal_Id(UUID.randomUUID().toString());
            servicio.getCuenta().setTelefono("S/T");
            servicio.getCuenta().setRol(new ServicioRol().buscarRolNombre("Administrador"));
            Cuenta c= new Cuenta();
            c.setClave("admin");
            c.setUsuario("admin");
            c.setExternal_id(UUID.randomUUID().toString());
            c.setCreated_at(new Date());
            c.setUpdate_at(new Date());
            c.setUsuario(servicio.getCuenta(c));
            servicio.getPersona().setCuenta(c);
            servicio.guardar();
        }
    }
*/
    public Cuenta inicioSesion(String usuario,String clave){
        return obj.inicioSesion(usuario, clave);
    }
}
