/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import od.Principal;

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
    private TextField campoClave;
    @FXML
    private Button botonLogin;
    @FXML 
    private Hyperlink linkRegistro;
    @FXML
    private Label etiquetaError;
    /**
     * Initializes the controller class.
     */
    public void initialize() { }
    
    @FXML
    private void handleIngreso(){
        if (validar()) {
            etiquetaError.setText("");
            campoUsuario.setStyle("-fx-border-color: #a0a0a0;");
            campoClave.setStyle("-fx-border-color: #a0a0a0;");
            
            ventana.close();
            principal.iniciarRaiz();
        }
    }
    
    @FXML
    private void handleRegistro(){
        ventana.close();
        principal.mostrarRegistroVista(ventana);
    }
    
    private boolean validar(){
        boolean band = true;
        if (campoUsuario.getText() == null || campoUsuario.getText().length() == 0){
            band = false;
            campoUsuario.setStyle("-fx-border-color: #dd1d1d;");
        }
            
        if (campoClave.getText() == null || campoClave.getText().length() == 0){
            band = false;
            campoClave.setStyle("-fx-border-color: #dd1d1d;");
        }            
        
        if (!band)
            etiquetaError.setText("¡Campos vacíos!");        
            
        return band;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.ventana = dialogStage;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    } 
}
