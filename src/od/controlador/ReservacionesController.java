/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import od.controlador.servicio.ServicioReservacion;
import od.modelo.Reservacion;


/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class ReservacionesController {
  
    @FXML
    private TableView<Reservacion> reservasTabla;
    @FXML
    private TableColumn<Reservacion, String> nombresColumna;
    @FXML
    private TableColumn<Reservacion, String> desdeColumna;
    @FXML
    private TableColumn<Reservacion, String> hastaColumna;
    @FXML
    private TableColumn<Reservacion, String> telefonoColumna;
    @FXML
    private TableColumn<Reservacion, String> estadoColumna;
    
    
    ServicioReservacion sr = new ServicioReservacion();
    /**
     * Initializes the controller class.
     */
    public void initialize(){
        
    }
    
    public void cargarTabla(){
        reservasTabla.setItems((ObservableList)sr.todos());
        
        nombresColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getNombres() + " " 
                        + cellData.getValue().getPersona().getApellidos())
        );
    }
}
