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
import od.utilidades.Validadores;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelClientesController {
    @FXML
    private ComboBox cbxTipoDNI;
    @FXML
    private TextField txtNroDNI;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox cbxGenero;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDireccion;
    
    
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pfClave;
    @FXML
    private TextField txtCorreo;
    
    @FXML
    private Label lblMensaje;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    } 
    
    
    private boolean validar(){
        if (Validadores.validarTF(txtNroDNI)
                & Validadores.validarTF(txtNombres)
                & Validadores.validarTF(txtApellidos)
                & Validadores.validarDP(dpFecha)
                & Validadores.validarTF(txtTelefono)
                & Validadores.validarTF(txtPais)
                & Validadores.validarTF(txtCiudad)
                & Validadores.validarTF(txtDireccion)
                & Validadores.validarTF(txtCorreo)){
            lblMensaje.setText("");
            return true;
        }else{
            lblMensaje.setText("Rellene los campos vacíos.");
            return false;
        }
    }
    
    @FXML
    private void guardar(){
        if (validar()) {
            System.out.println("Guardado");
        }
    }
}
