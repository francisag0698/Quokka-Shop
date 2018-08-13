/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.servicio;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import od.modelo.Cuenta;
import od.controlador.dao.CuentaDao;

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
    
    public void crearCuentaAdmin(){
        if(todos().isEmpty()){
            ServicioPersona sp = new ServicioPersona();
            sp.getPersona().setApellidos("admin");
            sp.getPersona().setCiudad("Loja");
            sp.getPersona().setDireccion("Av. Siempre viva");
            sp.getPersona().setDni("1111111111");
            sp.getPersona().setFecha_nacimiento(new Date());
            sp.getPersona().setNombres("admin");
            sp.getPersona().setPais("Ecuador");
            sp.getPersona().setSexo("Masculino");
            sp.getPersona().setTelefono("123456789");
            sp.getPersona().setTipo_dni("Cedula de Identidad");
            sp.getPersona().setRol(new ServicioRol().buscarRolNombre("Administrador"));
            
            
            Cuenta c= new Cuenta();
            c.setClave("admin");
            c.setUsuario("pass");
            c.setExternal_id(UUID.randomUUID().toString());
            c.setCreated_at(new Date());
            c.setUpdate_at(new Date());
            c.setPersona(sp.getPersona());
            sp.getPersona().setCuenta(c);
            sp.guardar();
        }
    }

    public Cuenta inicioSesion(String usuario,String clave){
        return obj.inicioSesion(usuario, clave);
    }
}
