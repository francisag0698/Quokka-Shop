/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    private ComboBox comboGenero;
    @FXML
    private Label lblError;

    private Stage ventana;
    private Principal principal;
    private ServicioPersona sp = new ServicioPersona();
    private ServicioCuenta sc = new ServicioCuenta();
    private UtilidadesComponentes uc = new UtilidadesComponentes();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        //Tipo de DNI
        comboTipoDNI.getItems().addAll(
                "Cedula de Identidad",
                "Pasaporte"
        );
        comboTipoDNI.setValue("Cedula de Identidad");
        
        //Genero
        comboGenero.getItems().addAll(
                "Masculino",
                "Femenino",
                "Otros"
        );
        comboGenero.setValue("Masculino");
        
        
    }

    public void setDialogStage(Stage dialogStage) {
        this.ventana = dialogStage;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
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
            lblError.setText("Rellene los campos obligatorios.");
            lblError.setVisible(true);
            return false;
        }
    }

    private void cargarObjeto(){
        sp.getPersona().setNombres(campoNombre.getText());
        sp.getPersona().setApellidos(campoApellido.getText());
        sp.getPersona().setTipo_dni(comboTipoDNI.getValue().toString());
        sp.getPersona().setDni(campoDNI.getText());
        try {
            sp.getPersona().setFecha_nacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(campoFechaNacimiento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));            
        } catch (ParseException e) {
            sp.getPersona().setFecha_nacimiento(new Date());
        }
        sp.getPersona().setTelefono(campoCelular.getText());
        sp.getPersona().setSexo(comboGenero.getValue().toString());
        sp.getPersona().setPais(campoPais.getText());
        sp.getPersona().setCiudad(campoCiudad.getText());
        sp.getPersona().setDireccion(campoDireccion.getText());
        sp.getPersona().setRol(new ServicioRol().buscarRolNombre("Cliente"));
        sc.getCuenta().setUsuario(campoNuevoUsuario.getText());
        sc.getCuenta().setClave(campoNuevaClave.getText());
        sc.getCuenta().setCorreo(campoCorreo.getText());
        sc.getCuenta().setExternal_id(UUID.randomUUID().toString());
        sc.getCuenta().setCreated_at(new Date());
        sc.getCuenta().setUpdate_at(new Date());
        sc.getCuenta().setEstado(true);
        sc.getCuenta().setPersona(sp.getPersona());
        sp.getPersona().setCuenta(sc.getCuenta());
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
        if (UtilidadesComponentes.validadorDeCedula(campoDNI.getText())) {
            if (sp.ObtenerPersonaCedula(campoDNI.getText()) != null) {
                lblError.setText("La cedula ingresada ya existe.");               
                lblError.setVisible(true);
            }else if(sc.ObtenerCuentaUsuario(campoNuevoUsuario.getText()) != null){
                lblError.setText("El usuario ingresado ya existe.");               
                lblError.setVisible(true);
            }else if(sc.ObtenerCuentaCorreo(campoCorreo.getText()) != null){
                lblError.setText("El correo ingresado ya existe.");               
                lblError.setVisible(true);
            }else {
                cargarObjeto();                
                if (sp.guardar()) {                         
                    limpiar();
                    lblError.setVisible(false);
                    panelHecho.setVisible(true);
                    System.out.println("Guardado");
                } else {
                    lblError.setText("Ah ocurrido un error al intentar guardar.");
                    lblError.setVisible(true);
                }
            }
        } else {
            lblError.setText("El número de cédula incorrecto.");
            lblError.setVisible(true);            
        }        
    }

    @FXML
    private void handleRegresar() {
        ventana.close();
        principal.mostrarLoginVista(ventana);
    }

    @FXML
    private void handleRegistro() {        
        if (validar()) {            
            guardar();
        }
    }
}
