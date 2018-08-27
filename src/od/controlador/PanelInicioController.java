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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import od.controlador.servicio.ServicioHistorial;
import od.controlador.servicio.ServicioReservacion;
import od.modelo.Historial;
import od.modelo.Reservacion;
import od.utilidades.Utilidades;


/**
 * FXML Controller class
 *
 * @author Gemelos
 */
public class PanelInicioController {
    
    @FXML
    private VBox vbNotificaciones;
    @FXML
    private TableView<Reservacion> tablaReservas;
    @FXML
    private TableColumn<Reservacion, String> nyap;
    @FXML
    private TableColumn<Reservacion, String> habitacion;
    
    private ServicioReservacion sr = new ServicioReservacion();
    private ServicioHistorial sh = new ServicioHistorial();
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarHistorial();
        cargarTabla();
    }
    
    private void cargarHistorial(){
        sh.todos().stream().map((obj) -> {
            VBox p = new VBox();
            p.getStyleClass().add("v-box");
//            Label a = new Label(obj.getIdentificador());
//            a.getStyleClass().add("identificador");
            Label b = new Label(obj.getAccion());
            b.getStyleClass().add("accion");
            Label c = new Label(obj.getPersona().getNombres().toUpperCase() + " • " + Utilidades.formatearFechaDos(obj.getFecha()));
            c.getStyleClass().add("usuario");
//            p.getChildren().add(a);
            p.getChildren().add(b);
            p.getChildren().add(c);
            return p;
        }).forEachOrdered((p) -> {
            vbNotificaciones.getChildren().add(p);
        });
    }
    
    private void cargarTabla(){
        tablaReservas.setItems(FXCollections.observableList(sr.todos()));
        nyap.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getNombres() + " " + cellData.getValue().getPersona().getApellidos())
        );
        habitacion.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHabitacion().getNombre())
        );
        tablaReservas.refresh();
    }
    
}
