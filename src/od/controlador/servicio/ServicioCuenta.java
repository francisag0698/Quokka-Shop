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
* Clase que permite utilizar los metodos del ServicoDetalle
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class ServicioCuenta {
    //inicializacion de una variable privada
     private CuentaDao obj = new CuentaDao();
    
     /**
     * Permite extraer un metodo del getCuenta
     * @return devuelve un objeto de Cuenta
     */
    public Cuenta getCuenta(){
        return obj.getCuenta();
    }//cierre del metodo GetCuenta
    
     /**
     * Metodo que llamar el metodo guardar
     * @return devuelve un objeto guardar
     */
    public boolean guardar(){
        return obj.guardar();
    }//cierre del metodo guardar
    
    /**
     * Metodo que permite llamar el metodo listar de AdaptadorDao
     * @return devuelve un objeto tipo lista
     */
    public List<Cuenta>todos(){
        return obj.listar();
    }//cierre del metodo todos
    
    /**
     * Metodo que permite obtener el id de Cuenta
     * @param id acepta un dato tipo Long
     * @return devuelve un objeto obtener
     */
    public Cuenta obtener(Long id){
        return obj.obtener(id);
    }//cierre del metodo obtener
    
    /**
     * Permite modificar un Cuetna
     * @param cuenta acepta un dato detalle de tipo Cuenta
     */
    public void fijarCuenta(Cuenta cuenta){
        obj.setCuenta(cuenta);
    }//cierre del metodo fijarCuenta
    
    /**
     * Metodo que permite crear la cuenta administrador
     */
    public void crearCuentaAdmin(){
        if(todos().isEmpty()){
            ServicioPersona sp = new ServicioPersona();
            sp.getPersona().setApellidos("Doe White");
            sp.getPersona().setCiudad("Loja");
            sp.getPersona().setDireccion("Lorem ipsum dolor sit amet");
            sp.getPersona().setDni("1111111111");
            sp.getPersona().setFecha_nacimiento(new Date());
            sp.getPersona().setNombres("Alex Joe");
            sp.getPersona().setPais("Ecuador");
            sp.getPersona().setSexo("Masculino");
            sp.getPersona().setTelefono("123456789");
            sp.getPersona().setRol(new ServicioRol().buscarRolNombre("Administrador"));
            
            
            Cuenta c= new Cuenta();
            c.setClave("pass");
            c.setUsuario("admin");
//            c.setExternal_id(UUID.randomUUID().toString());
            c.setCreated_at(new Date());
            c.setUpdate_at(new Date());
            c.setEstado(true);
            c.setPersona(sp.getPersona());
            sp.getPersona().setCuenta(c);
            sp.guardar();
        }
    }//cierre del matodo crearCuentaAdmin

    /**
     * Metodo que permite iniciar una sesion en cuenta
     * @param usuario acepta un dato tipo String
     * @param clave acepta un dato tipo String
     * @return objeto del modelo cuenta
     */
    public Cuenta inicioSesion(String usuario,String clave){
        return obj.inicioSesion(usuario, clave);
    }//cierre de metodo inicioSesion
    
    /**
     * Metodo que permite obtener el correo de la cuenta
     * @param correo acepta un dato tipo String
     * @return objeto del modelo cuenta
     */
    public Cuenta ObtenerCuentaCorreo(String correo){
        return obj.ObtenerCuentaCorreo(correo);
    }//cierre del metodo ObtenerCuentaCorreo
    
    /**
     * Metodo que permite obtener la cuenta de un usuario
     * @param usuario acepta un dato tipo String
     * @return objeto del modelo cuenta
     */
    public Cuenta ObtenerCuentaUsuario(String usuario){
        return obj.ObtenerCuentaUsuario(usuario);
    }//cierre de metodo ObtenerCuentaUsuario    
}//Cierre de la clase ServicioCuenta
