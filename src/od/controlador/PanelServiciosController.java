/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import od.controlador.servicio.ServicioServicio;
import od.modelo.Servicio;
import od.utilidades.Validadores;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelServiciosController {

    @FXML
    private TextArea areaDesServicio;
    @FXML
    private TextField campoCostoS;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private TableView tablaServicios;
    @FXML
    private Label lblError;
    @FXML
    private TableColumn<Servicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Servicio, String> columnaCosto;
    
    private ServicioServicio ss = new ServicioServicio();
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarTabla();
    }    
    
    private boolean validar (){
        if(Validadores.validarTA(areaDesServicio)
                & Validadores.validarTF(campoCostoS)){
            lblError.setText("Guardado ");
            return true;
        }else{
            lblError.setText("No guardado");
            return false;
        }
    }
    private void cargarObjeto(){
        ss.getServicio().setNombre_servicio(areaDesServicio.getText().trim());
        ss.getServicio().setPrecio(Double.parseDouble(campoCostoS.getText()));
        
        
    }
    private void cargarTabla(){
        tablaServicios.setItems(FXCollections.observableList(ss.todos()));
        columnaDescripcion.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombre_servicio()));
        columnaCosto.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getPrecio().toString()));
        
    }
    private void limpiar(){
        areaDesServicio.setText("");
         campoCostoS.setText("");
         ss.fijarServicio(null);
    }
    public void guardar(){
        cargarObjeto();
        if(ss.guardar()){
            limpiar();
            lblError.setText("Guardado");
        }else{
            lblError.setText("No Guardado");
        }
    }
    @FXML
    private void buscar(){
        
        if(campoBusqueda.getText().trim().length() >= 3){
            tablaServicios.setItems(FXCollections.observableList(ss.listarBusqueda(campoBusqueda.getText())));
        }
        tablaServicios.refresh();
    }
    @FXML
    private void handleGuardar(){
        if(validar()){
            guardar();
            cargarTabla();
            System.out.println("guardado");
        }
    }
    @FXML
    private void handleCancelar(){
        limpiar();
    }
    @FXML
    private void handleModificar(){
        
    }
    @FXML
    private void handleEliminar(){
        
    }
}
