/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import od.modelo.Detalle;

/**
 *
 * @author Dennis
 */
public class DetalleDao extends AdaptadorDao<Detalle>{
    private Detalle detalle;

    public DetalleDao() {
        super(Detalle.class);
    }
    public Detalle getDetalle() {
        if(detalle==null)
           detalle= new Detalle();
        return detalle;
    }
   public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
    public void Detalle(Detalle detalle) {
        this.detalle = detalle;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(detalle.getId_detalle()!=null){
                modificar(detalle);
            }else{
                guardar(detalle);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
}
