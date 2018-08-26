/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioHabitacion;
import od.controlador.servicio.ServicioPersona;
import od.controlador.servicio.ServicioServicio;
import od.modelo.Habitacion;
import od.modelo.Servicio;
import od.utilidades.Validadores;
import od.vista.utilidades.UtilidadesComponentes;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelNuevaReservacionController {

    @FXML
    Pane panelReserva;
    @FXML
    Pane panelHabitaciones;
    @FXML
    private TextField campoDNI;
    @FXML
    private DatePicker campoFechaEntrada;
    @FXML
    private DatePicker campoFechaSalida;
    @FXML
    private TextField campoNroAdultos;
    @FXML
    private TextField campoNroNinios;
    @FXML
    private TextField campoNroHabitaciones;
    @FXML
    private Label lblError;
    @FXML
    private Label lblDisponibles;
    @FXML
    private Label lblCapacidad;
    @FXML
    private Label lblCamas;
    @FXML
    private Label lblDescripcion;
    @FXML
    private ComboBox<Habitacion> cbxHabitaciones;
    @FXML
    private ListView<Servicio> listaServicios;

    private ServicioPersona sp = new ServicioPersona();
    private ServicioHabitacion sh = new ServicioHabitacion();
    private ServicioServicio ss = new ServicioServicio();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        listaServicios.setItems(FXCollections.observableList(ss.todos()));
        listaServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void limpiar() {
        campoNroHabitaciones.setText("");
    }

    @FXML
    private boolean validarID() {
        if (Validadores.validarTF(campoDNI)) {
            if (UtilidadesComponentes.validadorDeCedula(campoDNI.getText())) {
                if (sp.ObtenerPersonaCedula(campoDNI.getText()) != null) {
                    campoDNI.setDisable(true);
                    panelReserva.setDisable(false);
                    return true;
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No encontrado");
                    alert.setHeaderText("");
                    alert.setContentText("La cédula ingresada no existe.");
                    alert.showAndWait();
                    return false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrecto");
                alert.setHeaderText("");
                alert.setContentText("La cédula ingresada no es válida.");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo vacío");
            alert.setHeaderText("");
            alert.setContentText("El campo está vacío.");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private boolean validarPanel1() {
        if (Validadores.validarDP(campoFechaEntrada)
                && Validadores.validarDP(campoFechaSalida)
                && Validadores.validarTF(campoNroAdultos)
                && Validadores.validarTF(campoNroNinios)) {
            cbxHabitaciones.setItems(FXCollections.observableList(sh.todos()));
            campoNroHabitaciones.setText("1");
            panelHabitaciones.setDisable(false);
            panelReserva.setDisable(true);
            return true;
        } else {
            lblError.setText("Faltan campos por llenar.");
            lblError.setVisible(true);
            return false;
        }
    }

    @FXML
    private boolean validarPanel2() {

        if (Validadores.validarTF(campoNroHabitaciones)) {
            lblError.setVisible(false);
            System.out.println("SE HA RESERVADO CORRECTAMENTE");
            limpiar();
            campoDNI.setDisable(true);
            panelReserva.setDisable(true);
            return true;
        } else {
            lblError.setText("Faltan campos por llenar.");
            lblError.setVisible(true);
            return false;
        }
    }
    
    @FXML
    private void fijarDescripcion(){
        if (cbxHabitaciones.getValue() != null) {
            lblDisponibles.setText(cbxHabitaciones.getValue().getCantidad() + " Habitacion(es)");
            lblCapacidad.setText(cbxHabitaciones.getValue().getCapacidad() + " Persona(s)");
            lblCamas.setText(cbxHabitaciones.getValue().getNro_camas() + " Cama(s)");
            lblDescripcion.setText(cbxHabitaciones.getValue().getDescripcion());
        }
    }
}
