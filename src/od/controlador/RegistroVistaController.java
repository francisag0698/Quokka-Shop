/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import od.Principal;
import od.utilidades.Validadores;


/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class RegistroVistaController {
    @FXML Pane panelHecho;
    private Stage ventana;
    private Principal principal;
    
    
    
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
    private void handleRegresar(){
        ventana.close();
        principal.mostrarLoginVista(ventana);
    }
    
    @FXML
    private void handleRegistro(){
        if (validar()) {
            panelHecho.setVisible(true);
        }
    }
    
    @FXML
    private void alTablero(){
        panelHecho.setVisible(false);
    }
    
    private boolean validar(){
        if(Validadores.validarTF(campoNombre) 
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
                & Validadores.validarTF(campoCorreo)){
            if(Validadores.comprobarClave(campoNuevaClave, campoRepetirClave)){                
                lblError.setVisible(false);  
                return true;
            }else{
                lblError.setText("Las contraseñas ingresadas no coinciden.");
                lblError.setVisible(true);
                return false;
            } 
        }else{
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
  
}
