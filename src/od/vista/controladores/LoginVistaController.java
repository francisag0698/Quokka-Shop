/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.vista.controladores;

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
    /**
     * Initializes the controller class.
     */
    public void initialize() { }
    
    @FXML
    private void handleIngreso(){
        if (validar()) {
            etiquetaError.setText("");  
            Sesiones.setCuenta(new ServicioCuenta().inicioSesion(campoUsuario.getText(), campoClave.getText()));
            if (Sesiones.getCuenta() != null) {
                if (Sesiones.getCuenta().getEstado()) {
                    ventana.close();
                    principal.iniciarRaiz();
                }else{
                    etiquetaError.setText("Cuenta deshabilitada.");
                    Sesiones.setCuenta(null);
                }                
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
    
    /*
     * Objetos del LOGIN
    */
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
    
    private Stage ventana;
    private Principal principal;
}
