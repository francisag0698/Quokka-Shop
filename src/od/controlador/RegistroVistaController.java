/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.util.Date;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import od.Principal;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioPersona;
import od.controlador.servicio.ServicioRol;
import od.utilidades.Validadores;
import od.vista.utilidades.UtilidadesComponentes;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class RegistroVistaController {

    @FXML
    Pane panelHecho;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoApellido;
    @FXML
    private TextField campoDNI;
    @FXML
    private DatePicker campoFechaNacimiento;
    @FXML
    private TextField campoCelular;
    @FXML
    private TextField campoPais;
    @FXML
    private TextField campoCiudad;
    @FXML
    private TextField campoDireccion;
    @FXML
    private TextField campoNuevoUsuario;
    @FXML
    private PasswordField campoNuevaClave;
    @FXML
    private PasswordField campoRepetirClave;
    @FXML
    private TextField campoCorreo;
    @FXML
    private ComboBox comboTipoDNI;
    @FXML
    private Label lblError;

    private Stage ventana;
    private Principal principal;
    private ServicioPersona sp = new ServicioPersona(); // >:v fuen pancho
    private UtilidadesComponentes uc = new UtilidadesComponentes();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        comboTipoDNI.getItems().addAll(
                "Cedula de Identidad",
                "Pasaporte"
        );
        comboTipoDNI.setValue("Cedula de Identidad");
    }

    @FXML
    private void handleRegresar() {
        ventana.close();
        principal.mostrarLoginVista(ventana);
    }

    @FXML
    private void handleRegistro() {
        if (validar()) {
            panelHecho.setVisible(true);
            guardar();
        }

    }

    @FXML
    private void alTablero() {
        panelHecho.setVisible(false);
    }

    private boolean validar() {
        if (Validadores.validarTF(campoNombre)
                & Validadores.validarTF(campoApellido)
                & Validadores.validarTF(campoDNI)
                & Validadores.validarDP(campoFechaNacimiento)
                & Validadores.validarTF(campoCelular)
                & Validadores.validarTF(campoPais)
                & Validadores.validarTF(campoCiudad)
                & Validadores.validarTF(campoDireccion)
                & Validadores.validarTF(campoNuevoUsuario)
                & Validadores.validarP(campoNuevaClave)
                & Validadores.validarP(campoRepetirClave)
                & Validadores.validarTF(campoCorreo)) {
            if (Validadores.comprobarClave(campoNuevaClave, campoRepetirClave)) {
                lblError.setVisible(false);
                return true;
            } else {
                lblError.setText("Las contraseñas ingresadas no coinciden.");
                lblError.setVisible(true);
                return false;
            }
        } else {
            lblError.setText("Rellene todos los campos vacíos.");
            lblError.setVisible(true);
            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.ventana = dialogStage;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    private void cargarObjeto() {
        sp.getPersona().setNombres(campoNombre.getText());
        sp.getPersona().setApellidos(campoApellido.getText());
        sp.getPersona().setDni(campoDNI.getText());
        sp.getPersona().setFecha_nacimiento(new Date());
        sp.getPersona().setTelefono(campoCelular.getText());
        sp.getPersona().setPais(campoPais.getText());
        sp.getPersona().setCiudad(campoCiudad.getText());
        sp.getPersona().setDireccion(campoDireccion.getText());
        sp.getPersona().setRol(new ServicioRol().buscarRolNombre("Cliente")); // >:v

        ServicioCuenta c = new ServicioCuenta();
        c.getCuenta().setUsuario(campoNuevoUsuario.getText());
        c.getCuenta().setClave(campoNuevaClave.getText());
        c.getCuenta().setExternal_id(UUID.randomUUID().toString());
        c.getCuenta().setCreated_at(new Date());
        c.getCuenta().setUpdate_at(new Date());
        c.getCuenta().setEstado(true);
        c.getCuenta().setPersona(sp.getPersona());
        sp.getPersona().setCuenta(c.getCuenta());
        sp.guardar();

    }
    private void limpiar(){
        campoApellido.setText("");
        campoCelular.setText("");
        campoCiudad.setText("");
        campoCorreo.setText("");
        campoDNI.setText("");
        campoDireccion.setText("");
        campoNombre.setText("");
        campoNuevaClave.setText("");
        campoNuevoUsuario.setText("");
        campoPais.setText("");
        campoRepetirClave.setText("");
        
       }
    public void guardar() {

        cargarObjeto();
        if (sp.getPersona().getId_persona() != null) {
            //modificalo perra
        } else {
            //valida cedula perra

            if (UtilidadesComponentes.validadorDeCedula(campoDNI.getText())) {
                if (sp.ObtenerPersonaCedula(campoDNI.getText()) != null) {
                        System.out.println("La cedula ya existe perra");
                } else {
                    if (sp.guardar()) {
                         
                        System.out.println("Ok guardado perra");
                        limpiar();
                    } else {

                        System.out.println("Haslo bien perra");
                    }
                }
            } else {
                System.out.println(" No vale la cedula perra");
            }

        }
    }
}
