/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import od.Principal;


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
    private ComboBox comboTipoDNI;

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
    private void test(){
        System.out.println("asd");
    }
    
    @FXML
    private void handleRegistro(){
        if (validar()) {
            System.out.println("Valido");
            panelHecho.setVisible(true);
        }else{
            System.out.println("No valido");
        }
    }
    
    @FXML
    private void alTablero(){
        panelHecho.setVisible(false);
    }
    
    private boolean validar(){
        if (campoNombre.getText().length() == 0 || campoNombre.getText() == null) {
            return false;
        }else
            return true;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.ventana = dialogStage;
    } 

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }    
}
