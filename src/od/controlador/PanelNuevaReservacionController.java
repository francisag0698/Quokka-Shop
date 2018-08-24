/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioPersona;
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
    private TextField campoNroHuespedes;
    @FXML
    private Label lblError;

    private ServicioPersona sp = new ServicioPersona();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }

    private void limpiar() {
        campoNroHabitaciones.setText("");
        campoNroHuespedes.setText("");
    }

    @FXML
    private boolean validarID() {
        if (Validadores.validarTF(campoDNI)) {
            if (UtilidadesComponentes.validadorDeCedula(campoDNI.getText())) {
                if (sp.ObtenerPersonaCedula(campoDNI.getText()) != null) {
                    campoDNI.setDisable(true);
                    lblError.setVisible(false);
                    panelReserva.setDisable(false);
                    return true;
                } else {
                    lblError.setText("La cedula no se encuentra registrada.");
                    lblError.setVisible(true);
                    return false;
                }
            } else {
                lblError.setText("El número de cédula no existe.");
                lblError.setVisible(true);
                return false;
            }
        } else {
            lblError.setText("Llene el campo DNI.");
            lblError.setVisible(true);
            return false;
        }
    }

    @FXML
    private boolean validarPanel1() {
        if (Validadores.validarDP(campoFechaEntrada)
                && Validadores.validarDP(campoFechaSalida)
                && Validadores.validarTF(campoNroAdultos)
                && Validadores.validarTF(campoNroNinios)) {
            lblError.setVisible(false);
            panelHabitaciones.setDisable(false);
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
    private boolean validarPanel2() {

        if (Validadores.validarTF(campoNroHabitaciones)
                && Validadores.validarTF(campoNroHuespedes)) {
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

}
