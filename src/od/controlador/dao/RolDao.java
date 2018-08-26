
package od.controlador.dao;
import javax.persistence.Query;
import od.modelo.Rol;

/**
 * Clase que permite utilizar los metodos del adaptador
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class RolDao extends AdaptadorDao<Rol>{
    
    //inicializacion de una variable privada
    private Rol rol;
    
    /**
     * Constructor para obtener las variables de modelo Rol
     */
    public RolDao() {
        super(Rol.class);
    }//Cierre del constructor

    /**
     * Permite obtener un nuevo rol
     * @return devueve un rol
     */
    public Rol getRol() {
        if(rol==null)
           rol= new Rol();
        return rol;
    }//Cierre del getRol

    /**
     * permite modificar un rol
     * @param rol acepta un dato rol de tipo Rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }//Cierre del setRol
    
    /**
     * Metodo que permite guardar y modificar el rol
     * @return devuelve un valor de tipo booleano
     */
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
    }//Cierre del metodo guardar
    
    /**
     * Metodo para buscar por nombre dentro de Rol
     * @param nombre acepta un dato nombre de tipo String
     * @return devuelve nombre del rol seleccionado
     */
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
    }//Cierre del metodo buscarRolNombre
}//Cierre de la clase RolDao
