/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.daos;
import od.modelo.Rol;

/**
 *
 * @author Dennis
 */
public class RolDao extends AdaptadorDao<Rol>{
    private Rol rol;
    
    public RolDao() {
        super(Rol.class);
    }

    public Rol getRol() {
        if(rol==null)
           rol= new Rol();
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(rol.getId_rol()!=null){
                modificar(rol);
            }else{
                guardar(rol);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
}
