/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import od.controlador.servicio.ServicioHabitacion;
import od.modelo.Habitacion;
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
    private TableView<Habitacion> tablaHabitacion;
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
                "Filtrar por:",
                "Tipo",
                "Estado"
        );
        comboFiltro.setValue("Filtrar por:");

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
        sh.getHabitacion().setCodigo(campoCodigo.getText());
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
            cargarTabla();
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
    private void handleBorrar() {
        limpiar();
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
                cellData -> new SimpleStringProperty(cellData.getValue().getNro_camas().toString())
        );
        precioColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPrecio().toString())
        );
        estadoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString())
        );
    }

    @FXML
    private void filtrarSeleccion() {

        if (comboFiltro.getValue().toString().equals("Filtrar por:")) {
            cargarTabla();
            comboSelecion.setDisable(true);
            comboSelecion.getItems().removeAll("Individual", "Normal", "Doble", "Familiar", "Suite Normal", "Suite Empresarial","Ocupado","Libre");
        } else if (comboFiltro.getValue().toString().equals("Tipo")) {
            comboSelecion.getItems().removeAll("Ocupado", "libre");
            comboSelecion.setDisable(false);
            comboSelecion.getItems().addAll(
                    "Individual",
                    "Normal",
                    "Doble",
                    "Familiar",
                    "Suite Normal",
                    "Suite Empresarial"
            );
            seleccionar();
        } else if (comboFiltro.getValue().toString().equals("Estado")) {
            comboSelecion.getItems().removeAll("Individual", "Normal", "Doble", "Familiar", "Suite Normal", "Suite Empresarial");
            comboSelecion.setDisable(false);
            comboSelecion.getItems().addAll(
                    "Ocupado",
                    "libre"
            );
            seleccionar();
        }
    }

    @FXML
    private void seleccionar() {
        if (comboSelecion.getValue() !=null) {
            if (comboFiltro.getValue().toString().equals("Tipo")) {
                String tipo = comboSelecion.getValue().toString();
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarTipo(tipo)));
                tablaHabitacion.refresh();
            } else if (comboFiltro.getValue().toString().equals("Estado")) {
                Boolean estado = comboSelecion.getValue().equals("libre");
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarEstado(estado)));
                tablaHabitacion.refresh();
            }
        }
    }

    @FXML
    private void buscarTextoSeleccion() {
        if (campoBuscar.getText().trim().length() >= 3) {
            if (comboFiltro.getValue().toString().equals("Filtrar por:")) {
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusqueda(campoBuscar.getText())));
            } else if (comboFiltro.getValue().toString().equals("Tipo")) {
                String tipo = comboSelecion.getValue().toString();
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusquedaTipo(tipo, campoBuscar.getText())));
                
            } else if (comboFiltro.getValue().toString().equals("Estado")) {
                Boolean estado = comboSelecion.getValue().equals("libre");
                tablaHabitacion.setItems(FXCollections.observableList(sh.listarBusquedaEstado(estado, campoBuscar.getText())));               
            }
            tablaHabitacion.refresh();
        } else {
            filtrarSeleccion();
        }
    }
}
