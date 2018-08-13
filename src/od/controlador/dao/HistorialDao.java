/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;
import od.modelo.Historial;
/**
 *
 * @author Dennis
 */
public class HistorialDao extends AdaptadorDao<Historial>{
    private Historial historial;

    public HistorialDao() {
        super(Historial.class);
    }
    public Historial getHistorial() {
        if(historial==null)
           historial= new Historial();
        return historial;
    }
   public void setHistorial(Historial historial) {
        this.historial = historial;
    }
    public void Historial(Historial historial) {
        this.historial = historial;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(historial.getId_historial()!=null){
                modificar(historial);
            }else{
                guardar(historial);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
}
