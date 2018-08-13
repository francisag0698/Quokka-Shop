/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import javax.persistence.Query;
import od.modelo.Cuenta;

/**
 *
 * @author Dennis
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {
    private Cuenta cuenta;

    public CuentaDao() {
        super(Cuenta.class);
    }
    public Cuenta getCuenta() {
        if(cuenta==null)
           cuenta= new Cuenta();
        return cuenta;
    }
   public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    public void Cuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(cuenta.getId_cuenta()!=null){
                modificar(cuenta);
            }else{
                guardar(cuenta);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
            e.printStackTrace();
        }
        return verificar;
    }
    public Cuenta inicioSesion(String usuario,String clave){
        Cuenta c= null;
        try{
            Query q= getManager().createQuery("SELECT c FROM Cuenta c where c.usuario = :user");//si no funciona mira el nombre de la cuenta en la base de datos
            q.setParameter("user",usuario );
             Cuenta aux = (Cuenta)q.getSingleResult();
             if(aux != null && aux.getClave().equals(clave)){
                 c = aux;
             }
        }catch(Exception e){
           
        }
        return c;
    }
}
