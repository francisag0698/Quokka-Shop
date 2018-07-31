/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import od.controlador.Conexion;

/**
 *
 * @author PotatoPower
 */
public class AdaptadorDao<T> implements InterfazDao<T> {
    private Conexion conexion;
    private Class clazz;
    private String path_file;
    
    public AdaptadorDao(Conexion conexion, Class clazz) {
        this.conexion = conexion;
        this.clazz = clazz;
        this.path_file = conexion.obtenerPath() + File.separatorChar + clazz.getSimpleName() + ".json";
        crearArchivo();
    }
    
    private void crearArchivo() {
        File archivo = new File(path_file);
        try {
            FileWriter archivoClase = new FileWriter(archivo, true);
            archivoClase.close();
        } catch (IOException e) {
            System.out.println("Error, no se pudo crear el archivo " + e);
        }
    }

    @Override
    public List<T> listar() {
        List<T> lista = new ArrayList<>();
        try {
            this.conexion.getConexion().alias(clazz.getSimpleName(), clazz);
            Object obj = this.conexion.getConexion().fromXML(new FileReader(path_file));
            //List aux = (List) obj;
            //lista = (List<T>) aux.get(0)
            lista = (List<T>) obj;
        } catch (Exception e) {
            System.out.println("Error al listar los datos " + e);
        }
        return lista;
    }

    @Override
    public void guardar(T obj) throws IOException {
        List<T> lista = listar();
        lista.add(obj);
        this.conexion.getConexion().alias(clazz.getSimpleName(), clazz);
        this.conexion.getConexion().toXML(lista, new FileOutputStream(path_file));
    }
    
    public void guardar(T obj, int index) throws IOException {
        List<T> lista = listar();
        lista.set(index, obj);
        this.conexion.getConexion().alias(clazz.getSimpleName(), clazz);
        this.conexion.getConexion().toXML(lista, new FileOutputStream(path_file));
    }

    @Override
    public Long generarID() {
        Long id = new Long(listar().size() + 1);
        return id;
    }

    @Override
    public T obtener(Long id) {
        T obj = null;
        try {
            Field identificador = obtenerIdField();
            for (T t : listar()) {
                Object value = identificador.get(t);
                Long idValor = (Long)value;
                if (idValor.intValue() == id.intValue()) {
                    obj = t;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se encuentra el identificador id "+e);
        }
        return obj;
    }    
    
    private Field obtenerIdField() {
        Field aux = null;
        Field[] flieds = clazz.getDeclaredFields();
        try {
            for (Field f : flieds) {
                f.setAccessible(true);
                if (f.getType().getSimpleName().equalsIgnoreCase("Long") && f.getName().contains("id")) {
                    aux = f;
                    break;
                }
            }
            if (aux == null) {
                flieds = clazz.getSuperclass().getDeclaredFields();
                for (Field f : flieds) {
                    f.setAccessible(true);
                    if (f.getType().getSimpleName().equalsIgnoreCase("Long") && f.getName().contains("id")) {
                        aux = f;
                        break;
                    }
                }
            }
        } catch (Exception e) { 
            System.out.println("No se encuentra el campo: "+e);
        }
        return aux;
    }
    
    public void eliminar(T obj) throws IOException{
        List<T> lista = listar();
        lista.remove(obj);
        this.conexion.getConexion().alias(clazz.getSimpleName(), clazz);
        this.conexion.getConexion().toXML(lista, new FileOutputStream(path_file));
    }
}
