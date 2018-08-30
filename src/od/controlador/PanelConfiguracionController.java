/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioPersona;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;
import od.utilidades.Validadores;

/**
 * FXML Controller class
 *
 * @author Gemelos
 */
public class PanelConfiguracionController {

    @FXML
    private TextField campoNombres;
    @FXML
    private TextField campoApellidos;
    @FXML
    private TextField campoDNI;
    @FXML
    private DatePicker campoFechaNacimiento;
    @FXML
    private TextField campoTelefono;
    @FXML
    private TextField campoPais;
    @FXML
    private TextField campoCiudad;
    @FXML
    private TextField campoDireccion;
    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoClave;
    @FXML
    private PasswordField campoRepetirClave;
    @FXML
    private TextField campoCorreo;
    @FXML
    private ComboBox comboGenero;
    

    private ServicioPersona sp = new ServicioPersona();
    private ServicioCuenta sc = new ServicioCuenta();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        comboGenero.getItems().addAll(
                "Masculino",
                "Femenino",
                "Otros"
        );
        comboGenero.setValue("Masculino");
        cargarVista();
    }

    @FXML
    private void cargarVista() {
        sp.fijarPersona(Sesiones.getCuenta().getPersona());
        campoDNI.setText(sp.getPersona().getDni());
        campoNombres.setText(sp.getPersona().getNombres());
        campoApellidos.setText(sp.getPersona().getApellidos());
        campoFechaNacimiento.setValue(LocalDate.parse(Utilidades.formatearFecha(sp.getPersona().getFecha_nacimiento())));
        comboGenero.setValue(sp.getPersona().getSexo());
        campoTelefono.setText(sp.getPersona().getTelefono());
        campoPais.setText(sp.getPersona().getPais());
        campoCiudad.setText(sp.getPersona().getCiudad());
        campoDireccion.setText(sp.getPersona().getDireccion());
        campoUsuario.setText(sp.getPersona().getCuenta().getUsuario());
        campoClave.setText(sp.getPersona().getCuenta().getClave());
        campoRepetirClave.setText(sp.getPersona().getCuenta().getClave());
        campoCorreo.setText(sp.getPersona().getCuenta().getCorreo());
    }

    private void cargarObjetoPersona() {
        sp.getPersona().setNombres(campoNombres.getText());
        sp.getPersona().setApellidos(campoApellidos.getText());
        sp.getPersona().setDni(campoDNI.getText());
        try {
            sp.getPersona().setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(campoFechaNacimiento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } catch (ParseException e) {
            sp.getPersona().setFecha_nacimiento(new Date());
        }
        sp.getPersona().setTelefono(campoTelefono.getText());
        sp.getPersona().setSexo(comboGenero.getValue().toString());
        sp.getPersona().setPais(campoPais.getText());
        sp.getPersona().setCiudad(campoCiudad.getText());
        sp.getPersona().setDireccion(campoDireccion.getText());
    }

    private void cargarObjetoCuenta() {
        sc.fijarCuenta(Sesiones.getCuenta());
        sc.getCuenta().setClave(campoClave.getText());
        sc.getCuenta().setCorreo(campoCorreo.getText());
        sc.getCuenta().setPersona(sp.getPersona());
        sp.getPersona().setCuenta(sc.getCuenta());
    }

    private boolean validarPersona() {
        if (Validadores.validarTF(campoNombres)
                & Validadores.validarTF(campoApellidos)
                & Validadores.validarTF(campoDNI)
                & Validadores.validarDP(campoFechaNacimiento)
                & Validadores.validarTF(campoTelefono)
                & Validadores.validarTF(campoPais)
                & Validadores.validarTF(campoCiudad)
                & Validadores.validarTF(campoDireccion)) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacios");
            alert.setHeaderText("");
            alert.setContentText("Rellene todos los campos vacíos.");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validarCuenta() {
        if (Validadores.validarP(campoClave)
                & Validadores.validarP(campoRepetirClave)
                & Validadores.validarTF(campoCorreo)) {
            if (Validadores.comprobarClave(campoClave, campoRepetirClave)) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Las contraseñas ingresadas no coiciden.");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacios");
            alert.setHeaderText("");
            alert.setContentText("Rellene todos los campos vacíos.");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void guardarModificarPersona() {
        if (validarPersona()) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);        
            conf.setTitle("Confirmación");
            conf.setHeaderText("¿Está seguro/a de realizar esta acción?");
            conf.setContentText("Presione Aceptar para confirmarlo.");
            conf.showAndWait();
            
            if (conf.getResult().getText().equals("Aceptar")) {
                cargarObjetoPersona();
                if (sp.guardar()) {
                    sp.fijarPersona(null);
                    Utilidades.guardarHistorial("Modificación de Cuenta", "Datos personales modificados", Sesiones.getCuenta().getPersona());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Guardado");
                    alert.setHeaderText("");
                    alert.setContentText("Se ha guardado el registro correctamente.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("Ha ocurrido un error al guardar.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void guardarModificarCuenta() {
        if (validarCuenta()) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);        
            conf.setTitle("Confirmación");
            conf.setHeaderText("¿Está seguro/a de realizar esta acción?");
            conf.setContentText("Presione Aceptar para confirmarlo.");
            conf.showAndWait();
            
            if (conf.getResult().getText().equals("Aceptar")) {
                cargarObjetoCuenta();
                if (sp.guardar()) {
                    sc.fijarCuenta(null);
                    Utilidades.guardarHistorial("Modificación de Cuenta", "Datos de cuenta modificados", Sesiones.getCuenta().getPersona());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Guardado");
                    alert.setHeaderText("");
                    alert.setContentText("Se ha guardado el registro correctamente.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText("Ha ocurrido un error al guardar.");
                    alert.showAndWait();
                }
            }
        }
    }
}
