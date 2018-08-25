/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField campoBuscar;
    @FXML
    private ComboBox comboEstado;
    @FXML
    private ComboBox comboOrdenar;

    ServicioReservacion sr = new ServicioReservacion();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarTabla();
        // tipo de estado
        comboEstado.getItems().addAll(
                "Filtrar por:",
                "Pagado",
                "Pendiente",
                "Cancelado"
        );
        comboEstado.setValue("Filtrar por:");
        //ordenar
        comboOrdenar.getItems().addAll(
                "Ordenar por:",
                "Fecha",
                "Apellidos"
        );
        comboOrdenar.setValue("Ordenar por:");
        
    }
    
    public void cargarTabla() {
        reservasTabla.setItems(FXCollections.observableList(sr.todos()));

        nombresColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getNombres() + " "
                        + cellData.getValue().getPersona().getApellidos())
        );
        desdeColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFecha_inicio().toString())
        );
        hastaColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFecha_fin().toString())
        );
        telefonoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getTelefono())
        );
        estadoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString())
        );
    }
    @FXML
    public void limpiar(){
        campoBuscar.setText(" ");//no se donde ponerlo para se limpie cada vez q se filtre por estado :,V
        
    }

    @FXML
    private void filtrarTipo() {
        if (comboEstado.getValue().toString().equals("Filtrar por:")) {
            cargarTabla();
        } else{
            String tipo = comboEstado.getValue().toString();
            
            reservasTabla.setItems(FXCollections.observableList(sr.listarTipo(tipo)));
            reservasTabla.refresh();
            
        }

    }

    @FXML
    private void buscarTexto() {
        
        if (campoBuscar.getText().trim().length() >= 3) {
            if (comboEstado.getValue().toString().equals("Filtrar por:")) {
                reservasTabla.setItems(FXCollections.observableList(sr.listarBusqueda(campoBuscar.getText())));

            } else {
                String tipo = comboEstado.getValue().toString();
                reservasTabla.setItems(FXCollections.observableList(sr.listarBusquedaTipo(tipo, campoBuscar.getText())));

            }

            reservasTabla.refresh();
        } else {
            filtrarTipo();
        }
    }

    @FXML
    private void ordenarAscendente() {
        if (comboOrdenar.getValue().toString().equals("Ordenar por:")) {
            reservasTabla.setItems(FXCollections.observableList(sr.todos()));
            reservasTabla.refresh();
        } else {
            String orden = comboOrdenar.getValue().toString();
            reservasTabla.setItems(FXCollections.observableList(sr.ordenAscendente(orden)));
            reservasTabla.refresh();
        }
    }

}
