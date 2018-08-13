/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.util.List;

/**
 *
 * @author Dennis
 */
public interface InterfazDao <T>{
    public void guardar (T obj)throws Exception;
    public void modificar (T obj)throws Exception;
    public List<T> listar();
    public T obtener (Long id);
}
