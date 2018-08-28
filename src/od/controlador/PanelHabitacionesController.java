/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioHabitacion;
import od.modelo.Habitacion;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;
import od.utilidades.Validadores;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelHabitacionesController {

    @FXML
    private TextField campoBuscar;
    @FXML
    private ComboBox comboFiltro;
    @FXML
    private ComboBox comboSelecion;
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
    private TextField campoPrecio;
    @FXML
    private TextField campoCantidad;

    @FXML
    private TableView<Habitacion> tablaHabitacion;
    @FXML
    private TableColumn<Habitacion, String> nombresColumna;
    @FXML
    private TableColumn<Habitacion, String> tipoColumna;
    @FXML
    private TableColumn<Habitacion, String> capacidadColumna;
    @FXML
    private TableColumn<Habitacion, String> camasColumna;
    @FXML
    private TableColumn<Habitacion, String> precioColumna;
    @FXML
    private TableColumn<Habitacion, String> estadoColumna;

    @FXML
    private TabPane tbpHabitaciones;
    @FXML
    private Tab tabListado;
    @FXML
    private Tab tabFormulario;

    @FXML
    private Pane panelDescr;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTipo;
    @FXML
    private Label lblCamas;
    @FXML
    private Label lblCapacidad;
    @FXML
    private Label lblCantidad;
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblCondiciones;

    private ServicioHabitacion sh = new ServicioHabitacion();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarTabla();
        // TODO
        comboTipo.getItems().addAll(
                "Individual",
                "Normal",
                "Doble",
                "Familiar",
                "Suite Normal",
                "Suite Empresarial"
        );
        //tipo de filtro
        comboFiltro.getItems().addAll(
                "Codigo",
                "Tipo"
        );
        comboSelecion.getItems().addAll(
                "Individual",
                "Normal",
                "Doble",
                "Familiar",
                "Suite Normal",
                "Suite Empresarial"
        );
    }
    
    

    public void cargarTabla() {
        tablaHabitacion.setItems(FXCollections.observableList(sh.todos()));

        nombresColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombre())
        );
        tipoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getTipo())
        );
        capacidadColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCapacidad().toString())
        );
        camasColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCantidad().toString())
        );
        precioColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPrecio().toString())
        );
        estadoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCodigo())
        );
        tablaHabitacion.refresh();
    }

    private boolean validar() {
        if (Validadores.validarTF(campoCodigo)
                & Validadores.validarTF(campoNombre)
                & Validadores.validarTF(campoCapacidad)
                & Validadores.validarTF(campoCamas)
                & Validadores.validarTF(campoPrecio)
                & Validadores.validarTF(campoCantidad)
                & Validadores.validarTA(areaCondiciones)
                & Validadores.validarTA(areaHabitacion)
                & comboTipo.getValue() != null) {
            if (Validadores.validarValor(campoCapacidad, 'i')
                    & Validadores.validarValor(campoCamas, 'i')
                    & Validadores.validarValor(campoPrecio, 'd')
                    & Validadores.validarValor(campoCantidad, 'i')) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error en los datos");
                alert.setHeaderText("");
                alert.setContentText("Los datos ingresados no son válidos.");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText("");
            alert.setContentText("Rellene todos los campos vacíos.");
            alert.showAndWait();
            return false;
        }
    }

    private void cargarObjeto() {
        sh.getHabitacion().setNombre(campoNombre.getText());
        sh.getHabitacion().setCapacidad(Integer.parseInt(campoCapacidad.getText()));
        sh.getHabitacion().setCondiciones(areaCondiciones.getText());
        sh.getHabitacion().setPrecio(Double.parseDouble(campoPrecio.getText()));
        sh.getHabitacion().setNro_camas(Integer.parseInt(campoCamas.getText()));
        sh.getHabitacion().setCantidad(Integer.parseInt(campoCantidad.getText()));
        sh.getHabitacion().setDescripcion(areaHabitacion.getText());
        sh.getHabitacion().setEstado(true);
        sh.getHabitacion().setTipo(comboTipo.getValue().toString());
        sh.getHabitacion().setCodigo(campoCodigo.getText());
    }

    private void limpiar() {
        campoCamas.setText("");
        campoCapacidad.setText("");
        campoCodigo.setText("");
        campoCodigo.setDisable(false);
        campoNombre.setText("");
        campoPrecio.setText("");
        areaCondiciones.setText("");
        areaHabitacion.setText("");
        campoCantidad.setText("");
        comboTipo.setValue(null);

        sh.fijarHabitacion(null);
    }

    public void guardar() {
        cargarObjeto();
        boolean band = true; // True si Guarda, False si Modifica
        if (sh.getHabitacion().getId_habitacion() != null) {
            band = false;
        }
        if (sh.guardar()) {
            cargarTabla();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardado");
            alert.setHeaderText("");
            alert.setContentText("Se ha guardado el registro correctamente.");
            alert.showAndWait();
            System.out.println(alert.getResult().getText());
            tbpHabitaciones.getSelectionModel().select(tabListado);
            if (band) {
                Utilidades.guardarHistorial("Nuevo Registro", "Nueva Habitación añadida", Sesiones.getCuenta().getPersona());
            } else {
                Utilidades.guardarHistorial("Registro Modificado", "Se ha modificado una Habitación", Sesiones.getCuenta().getPersona());
            }
            limpiar();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Ha ocurrido un error al guardar.");
            alert.showAndWait();
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
    private void handleModificar() {
        if (tablaHabitacion.getSelectionModel().getSelectedItem() != null) {
            sh.fijarHabitacion(tablaHabitacion.getSelectionModel().getSelectedItem());
            campoCodigo.setText(sh.getHabitacion().getCodigo());
            campoCodigo.setDisable(true);
            campoNombre.setText(sh.getHabitacion().getNombre());
            comboTipo.setValue(sh.getHabitacion().getTipo());
            campoCapacidad.setText(sh.getHabitacion().getCapacidad().toString());
            campoCamas.setText(sh.getHabitacion().getNro_camas().toString());
            campoPrecio.setText(sh.getHabitacion().getPrecio().toString());
            campoCantidad.setText(sh.getHabitacion().getCantidad().toString());
            areaHabitacion.setText(sh.getHabitacion().getDescripcion());
            areaCondiciones.setText(sh.getHabitacion().getCondiciones());

            tbpHabitaciones.getSelectionModel().select(tabFormulario);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBorrar() {
        limpiar();
    }

    @FXML
    private void buscarTexto() {
        if (campoBuscar.getText().trim().length() >= 3 || (comboFiltro.getValue() != null && comboFiltro.getValue().equals("Tipo"))) {
            if (comboFiltro.getValue() == null) {
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusqueda(campoBuscar.getText())));
                tablaHabitacion.refresh();
            } else if (comboFiltro.getValue().equals("Codigo")) {
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusquedaCodigo(campoBuscar.getText())));
                tablaHabitacion.refresh();
            } else {
                if (comboSelecion.getValue() != null) {
                    tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusquedaTipo(comboSelecion.getValue().toString(), campoBuscar.getText())));
                    tablaHabitacion.refresh();
                } else {
                    tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusqueda(campoBuscar.getText())));
                }
            }
        } else if (campoBuscar.getText().trim().length() == 0) {
            cargarTabla();
        }
    }

    @FXML
    private void handleDetalles() {
        if (tablaHabitacion.getSelectionModel().getSelectedItem() != null) {
            lblCodigo.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getCodigo());
            lblNombre.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getNombre());
            lblTipo.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getTipo());
            lblCamas.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getNro_camas().toString());
            lblCapacidad.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getCapacidad().toString());
            lblCantidad.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getCantidad().toString());
            lblPrecio.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getPrecio().toString());
            lblDescripcion.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getDescripcion());
            lblCondiciones.setText(tablaHabitacion.getSelectionModel().getSelectedItem().getCondiciones());
            panelDescr.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRegresar() {
        panelDescr.setVisible(false);
        lblCodigo.setText("");
        lblNombre.setText("");
        lblTipo.setText("");
        lblCamas.setText("");
        lblCapacidad.setText("");
        lblCantidad.setText("");
        lblPrecio.setText("");
        lblDescripcion.setText("");
        lblCondiciones.setText("");
    }
    
    @FXML
    private void controlCombo(){
        if(comboFiltro.getValue() != null && comboFiltro.getValue().equals("Tipo") ){
            campoBuscar.setText("");
            comboSelecion.setDisable(false);
        }else{
            campoBuscar.setText("");
            comboSelecion.setValue(null);
            comboSelecion.setDisable(true);
            buscarTexto();
        }
    }
    @FXML
    private void barrer(){
        comboSelecion.setValue(null);
        comboSelecion.setDisable(true);
        comboFiltro.setValue(null);
        campoBuscar.setText("");
        cargarTabla();
    }
}
