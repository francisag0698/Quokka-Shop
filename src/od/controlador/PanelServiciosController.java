/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioServicio;
import od.modelo.Servicio;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;
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
    private TableView<Servicio> tablaServicios;
    @FXML
    private Pane panelFormServicio;
    @FXML
    private TableColumn<Servicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Servicio, String> columnaCosto;
    
    @FXML
    private Pane panelDescr;
    @FXML
    private Label etiquetaCosto;
    @FXML
    private Label etiquetaDescripcion;
    
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
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos vacíos");
            alert.setHeaderText("");
            alert.setContentText("Rellene todos los campos del formulario.");
            alert.showAndWait();
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
        tablaServicios.refresh();
    }
    private void limpiar(){
        areaDesServicio.setText("");
        campoCostoS.setText("");
        ss.fijarServicio(null);
    }
    
    private void guardar(){ 
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);        
        conf.setTitle("Confirmación");
        conf.setHeaderText("¿Está seguro/a de realizar esta acción?");
        conf.setContentText("Presione Aceptar para confirmarlo.");
        conf.showAndWait();
        
        if (conf.getResult().getText().equals("Aceptar")) {
           cargarObjeto();
            boolean band = true;
            if(ss.getServicio().getId_servicio() != null){
                band = false;
            }
            if(ss.guardar()){
                if (band) {
                    Utilidades.guardarHistorial("Nuevo Registro", "Nuevo Servicio añadido", Sesiones.getCuenta().getPersona());
                }else{
                    Utilidades.guardarHistorial("Registro Modificado", "Se ha modificado un Servicio", Sesiones.getCuenta().getPersona());
                }
                limpiar();
                panelFormServicio.setDisable(true);
                cargarTabla();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guardado");
                alert.setHeaderText("");
                alert.setContentText("Se ha guardado el registro correctamente.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Ha ocurrido un error al guardar.");
                alert.showAndWait();
            } 
        }
    }
    
    @FXML
    private void buscar(){        
        if(campoBusqueda.getText().trim().length() >= 3){
            tablaServicios.setItems(FXCollections.observableList(ss.listarBusqueda(campoBusqueda.getText())));
            tablaServicios.refresh();
        }else if(campoBusqueda.getText().trim().length() == 0){
            cargarTabla();
        }
        
    }
    
    @FXML
    private void handleNuevo(){
        panelFormServicio.setDisable(false);
        limpiar();
    }
    
    @FXML
    private void handleGuardar(){
        if(validar()){
            guardar();
            System.out.println("guardado");
        }
    }
    @FXML
    private void handleCancelar(){
        panelFormServicio.setDisable(true);
        limpiar();
    }
    @FXML
    private void handleModificar(){
        if (tablaServicios.getSelectionModel().getSelectedItem() != null) {
            //System.out.println(tablaServicios.getSelectionModel().getSelectedItem());
            ss.fijarServicio(tablaServicios.getSelectionModel().getSelectedItem());
            areaDesServicio.setText(ss.getServicio().getNombre_servicio());
            campoCostoS.setText(ss.getServicio().getPrecio().toString());
            panelFormServicio.setDisable(false);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }            
    }
    
    @FXML
    private void handleDetalles(){
        if (tablaServicios.getSelectionModel().getSelectedItem() != null){
            etiquetaCosto.setText(tablaServicios.getSelectionModel().getSelectedItem().getPrecio().toString());
            etiquetaDescripcion.setText(tablaServicios.getSelectionModel().getSelectedItem().getNombre_servicio());
            panelDescr.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleRegresar(){
        panelDescr.setVisible(false);
        etiquetaCosto.setText("");
        etiquetaDescripcion.setText("");        
    }
    
    @FXML
    private void validarEnterosCosto() {
        if (!Validadores.validarValor(campoCostoS, 'd')) {
            campoCostoS.setText("");
        }
    }
}
