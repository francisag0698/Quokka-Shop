/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import od.modelo.Reservacion;
import od.modelo.Servicio;

/**
 *
 * @author Dennis
 */
public class ServicioDao extends  AdaptadorDao<Servicio>{
    private Servicio servicio;
    
    public ServicioDao() {
        super(Servicio.class);
    }

    public Servicio getServicio() {
        if(servicio==null)
           servicio= new Servicio();
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(servicio.getId_servicio()!=null){
                modificar(servicio);
            }else{
                guardar(servicio);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    
    public List<Servicio>listarBusqueda(String texto){
        List<Servicio> lista= new ArrayList<>();
        try {
            Query q = getManager()
                    .createQuery("SELECT s FROM Servicio s WHERE (LOWER(s.nombre_servicio) LIKE CONCAT('%', :texto, '%'))");
            q.setParameter("texto", texto);
            lista = q.getResultList();
        } catch (Exception e) {
        }
        return lista;
    }
}
