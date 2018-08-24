/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import od.controlador.servicio.ServicioHabitacion;
import od.utilidades.Validadores;
/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelHabitacionesController {

    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private ComboBox comboFiltro;
    @FXML
    private ComboBox comboSelecion;
    @FXML
    private TableView tablaHabitacion;
    @FXML
    private TextField campoCodigo;
    @FXML
    private TextField campoNombre;
    @FXML
    private ComboBox comboTipo;
    @FXML
    private TextField campoCapacidad;
    @FXML
    private TextField campoCamas;
    @FXML
    private TextArea areaHabitacion;
    @FXML
    private TextArea areaCondiciones;
    @FXML
    private Label lblError;
    @FXML
    private TextField campoPrecio;

    private ServicioHabitacion sh = new ServicioHabitacion();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        comboTipo.getItems().addAll(
        " Suit",
         "Residencial",
         "pobre",
         "Moderadamete rico",
         "tacaño");
    }

    private boolean validar() {
        if (Validadores.validarTF(campoCodigo)
                & Validadores.validarTF(campoNombre)
                & Validadores.validarTF(campoCapacidad)
                & Validadores.validarTF(campoCamas)
                & Validadores.validarTA(areaCondiciones)
                & Validadores.validarTA(areaHabitacion)) {
            lblError.setVisible(false);
            return true;
        } else {
            lblError.setText("Rellene los campos obligatorios.");
            lblError.setVisible(true);
            return false;
        }
    }
    private void cargarObjeto() {
        sh.getHabitacion().setNombre(campoNombre.getText());
        sh.getHabitacion().setCapacidad(Integer.parseInt(campoCapacidad.getText()));
        sh.getHabitacion().setCondiciones(areaCondiciones.getText());
        sh.getHabitacion().setPrecio(Double.parseDouble(campoPrecio.getText()));
        sh.getHabitacion().setNro_camas(Integer.parseInt(campoCamas.getText()));
        sh.getHabitacion().setDescripcion(areaHabitacion.getText());
        sh.getHabitacion().setEstado(true);
        sh.getHabitacion().setTipo(comboTipo.getValue().toString());
        
    }

    private void limpiar() {
        campoCamas.setText("");
        campoCapacidad.setText("");
        campoCodigo.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
        areaCondiciones.setText("");
        areaHabitacion.setText("");

    }

    public void guardar() {
        cargarObjeto();
        if (sh.guardar()) {
            limpiar();
            lblError.setVisible(false);
//            panelHecho.setVisible(true);
            System.out.println("Guardado");
        } else {
            lblError.setText("Ah ocurrido un error al intentar guardar.");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void handleGuardar() {
        if (validar()) {
            guardar();
            System.out.println("Guardado");
        }
    }
    @FXML
    private void handleBorrar(){
        limpiar();
    }
}
