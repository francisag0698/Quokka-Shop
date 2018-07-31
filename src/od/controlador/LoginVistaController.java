/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import od.Principal;
import od.controlador.servicio.ServicioCuenta;
import od.utilidades.Sesiones;
import od.utilidades.Validadores;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class LoginVistaController {
    private Stage ventana;
    private Principal principal;
    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoClave;
    @FXML
    private Button botonLogin;
    @FXML 
    private Hyperlink linkRegistro;
    @FXML
    private Label etiquetaError;
    
    ServicioCuenta sc = new ServicioCuenta();
    /**
     * Initializes the controller class.
     */
    public void initialize() { }
    
    @FXML
    private void handleIngreso(){
        if (validar()) {
            etiquetaError.setText("");  
            Sesiones.setSesion(sc.inicioSesion(campoUsuario.getText(), campoClave.getText()));
            if (Sesiones.getSesion() != null) {
                ventana.close();
                principal.iniciarRaiz();
            }else{
                etiquetaError.setText("Usuario y/o contraseña incorrectos.");
            }            
        }
    }
    
    @FXML
    private void handleRegistro(){
        ventana.close();
        principal.mostrarRegistroVista(ventana);
    }
    
    private boolean validar(){
        if (Validadores.validarTF(campoUsuario) &
                Validadores.validarP(campoClave)) {
            return true;           
        }else{
            etiquetaError.setText("¡Campos vacíos!");
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
