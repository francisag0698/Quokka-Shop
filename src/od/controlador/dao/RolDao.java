/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import javax.persistence.Query;
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
            e.printStackTrace();
        }
        return verificar;
    }
    
    public Rol buscarRolNombre(String nombre){
        Rol r = null;
        try {
            Query q = getManager().createQuery("SELECT r FROM Rol r where r.nombre = :data");
            q.setParameter("data", nombre);
            r = (Rol) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
