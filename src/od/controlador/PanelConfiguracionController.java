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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioPersona;
import od.modelo.Persona;
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
    
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtClave;
    @FXML
    private TextField txtCorreo;
    @FXML
    private ComboBox<Persona> cbxListaUsuarios;
    @FXML
    private Pane panelNuevaCuenta;

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
        campoCorreo.setText((sp.getPersona().getCuenta().getCorreo() == null) ? "":sp.getPersona().getCuenta().getCorreo());
        cbxListaUsuarios.setItems(FXCollections.observableList(sp.listadoUsuariosSinCuenta()));
    }

    private void cargarObjetoPersona() {
        sp.getPersona().setNombres(campoNombres.getText().trim());
        sp.getPersona().setApellidos(campoApellidos.getText().trim());
        sp.getPersona().setDni(campoDNI.getText().trim());
        try {
            sp.getPersona().setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(campoFechaNacimiento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } catch (ParseException e) {
            sp.getPersona().setFecha_nacimiento(new Date());
        }
        sp.getPersona().setTelefono(campoTelefono.getText());
        sp.getPersona().setSexo(comboGenero.getValue().toString());
        sp.getPersona().setPais(campoPais.getText().trim());
        sp.getPersona().setCiudad(campoCiudad.getText().trim());
        sp.getPersona().setDireccion(campoDireccion.getText().trim());
    }

    private void cargarObjetoCuenta() {
        sc.fijarCuenta(Sesiones.getCuenta());
        sc.getCuenta().setClave(campoClave.getText().trim());
        sc.getCuenta().setCorreo(campoCorreo.getText().trim());
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
                if (Validadores.validarCorreo(campoCorreo)) {
                    return true;
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Dato Incorrecto");
                    alert.setHeaderText("");
                    alert.setContentText("El correo ingresado es incorrecto");
                    alert.showAndWait();
                    return false;
                }
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
    
    private boolean validarDatosCuenta(){
        if (Validadores.validarTF(txtUsuario) && 
                Validadores.validarP(txtClave) &&
                Validadores.validarTF(txtCorreo)) {
            if (Validadores.validarCorreo(txtCorreo)) {
                return true;
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dato Incorrecto");
                alert.setHeaderText("");
                alert.setContentText("El correo ingresado es incorrecto");
                alert.showAndWait();
                return false;
            }
        }else{
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
    
    @FXML
    private void activarPanel(){
        if (cbxListaUsuarios.getValue() != null) {
            panelNuevaCuenta.setDisable(false);
        }
    }
    
    @FXML
    private void guardarNuevaCuenta(){
        if (validarDatosCuenta()) {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION);        
            conf.setTitle("Confirmación");
            conf.setHeaderText("¿Está seguro/a de realizar esta acción?");
            conf.setContentText("Presione Aceptar para confirmarlo.");
            conf.showAndWait();
            
            if (conf.getResult().getText().equals("Aceptar")){
                ServicioCuenta nsc = new ServicioCuenta();
                nsc.getCuenta().setUsuario(txtUsuario.getText().trim());
                nsc.getCuenta().setClave(txtClave.getText().trim());
                nsc.getCuenta().setCorreo(txtCorreo.getText().trim());
                nsc.getCuenta().setCreated_at(new Date());
                nsc.getCuenta().setUpdate_at(new Date());
                nsc.getCuenta().setEstado(Boolean.TRUE);
                cbxListaUsuarios.getValue().setCuenta(nsc.getCuenta());
                nsc.getCuenta().setPersona(cbxListaUsuarios.getValue());
                
                if (nsc.guardar()) {
                    Utilidades.guardarHistorial("Nueva Cuenta", "Se ha creado una nueva cuenta", Sesiones.getCuenta().getPersona());
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
    }
    
    @FXML
    private void validarEnteros() {
        if (!Validadores.validarValor(campoTelefono , 'i')) {
            campoTelefono.setText("");
        }
    }
}
