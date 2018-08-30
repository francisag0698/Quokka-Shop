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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioReservacion;
import od.modelo.Reservacion;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;


/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class ReservacionesController {

    @FXML
    private TableView<Reservacion> reservasTabla;
    @FXML
    private TableColumn<Reservacion, String> nombresColumna;
    @FXML
    private TableColumn<Reservacion, String> desdeColumna;
    @FXML
    private TableColumn<Reservacion, String> hastaColumna;
    @FXML
    private TableColumn<Reservacion, String> telefonoColumna;
    @FXML
    private TableColumn<Reservacion, String> estadoColumna;
    @FXML
    private TextField campoBuscar;
    @FXML
    private ComboBox comboEstado;
    @FXML
    private ComboBox comboOrdenar;
    
    @FXML
    private Pane panelDescr;
    @FXML
    private Label lblNombres;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblFechaEntrada;
    @FXML
    private Label lblFechaSalida;
    @FXML
    private Label lblHabitacion;
    @FXML
    private Label lblAdultos;
    @FXML
    private Label lblMenores;
    @FXML
    private Label lblNroHabitaciones;
    @FXML
    private Label lblServicios;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblTotal;

    ServicioReservacion sr = new ServicioReservacion();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarTabla();
        // tipo de estado
        comboEstado.getItems().addAll(
                "Activo",
                "Inactivo"
        );
        //ordenar
        comboOrdenar.getItems().addAll(
                "Fecha",
                "Apellidos"
        );
        
    }
    
    public void cargarTabla() {
        
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
            reservasTabla.setItems(FXCollections.observableList(sr.listarPorPersona(Sesiones.getCuenta().getPersona().getId_persona())));
        }else{
            reservasTabla.setItems(FXCollections.observableList(sr.todos()));
        }        

        nombresColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getNombres() + " "
                        + cellData.getValue().getPersona().getApellidos())
        );
        desdeColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(Utilidades.formatearFechaDos(cellData.getValue().getFecha_inicio()))
        );
        hastaColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(Utilidades.formatearFechaDos(cellData.getValue().getFecha_fin()))
        );
        telefonoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getTelefono())
        );
        estadoColumna.setCellValueFactory(
                cellData -> new SimpleStringProperty((cellData.getValue().getEstado()) ? "Activo":"Inactivo")
        );
        
        reservasTabla.refresh();
    }
    
    @FXML
    public void limpiar(){
        campoBuscar.setText("");
        comboEstado.setValue(null);
        comboOrdenar.setValue(null);
    }

    @FXML
    private void filtrarTipo() {
        if (comboEstado.getValue() == null && campoBuscar.getText().trim().length() == 0) {
            cargarTabla();
        } else if(comboEstado.getValue() != null){
            Boolean tipo = comboEstado.getValue().equals("Activo");    
            if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) 
                reservasTabla.setItems(FXCollections.observableList(sr.listarTipo(tipo, Sesiones.getCuenta().getPersona().getId_persona())));                
            else
                reservasTabla.setItems(FXCollections.observableList(sr.listarTipo(tipo)));                        
            reservasTabla.refresh();           
        }
    }

    @FXML
    private void buscarTexto() {
        
        if (campoBuscar.getText().trim().length() >= 3) {
            if (comboEstado.getValue() == null) {
                if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente"))
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusqueda(campoBuscar.getText(), Sesiones.getCuenta().getPersona().getId_persona())));
                else                    
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusqueda(campoBuscar.getText())));
            } else {
                Boolean tipo = comboEstado.getValue().equals("Activo");
                if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente"))
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusquedaTipo(tipo, campoBuscar.getText(), Sesiones.getCuenta().getPersona().getId_persona())));
                else
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusquedaTipo(tipo, campoBuscar.getText())));
            }

            reservasTabla.refresh();
        } else {
            filtrarTipo();
        }
    }

    @FXML
    private void ordenarAscendente() {
        if (comboOrdenar.getValue() != null && comboOrdenar.getValue().toString().equals("Ordenar por:")) {
            reservasTabla.setItems(FXCollections.observableList(sr.todos()));
            reservasTabla.refresh();
        } else {
            String orden = comboOrdenar.getValue().toString();
            reservasTabla.setItems(FXCollections.observableList(sr.ordenAscendente(orden)));
            reservasTabla.refresh();
        }
    }
    
    @FXML
    private void handleDetalles(){
        if (reservasTabla.getSelectionModel().getSelectedItem() != null){
            lblNombres.setText(reservasTabla.getSelectionModel().getSelectedItem().getPersona().getNombres() + " " + reservasTabla.getSelectionModel().getSelectedItem().getPersona().getApellidos());
            lblFecha.setText(Utilidades.formatearFechaDos(reservasTabla.getSelectionModel().getSelectedItem().getFecha()));
            lblFechaEntrada.setText(Utilidades.formatearFechaDos(reservasTabla.getSelectionModel().getSelectedItem().getFecha_inicio()));
            lblFechaSalida.setText(Utilidades.formatearFechaDos(reservasTabla.getSelectionModel().getSelectedItem().getFecha_fin()));
            lblHabitacion.setText(reservasTabla.getSelectionModel().getSelectedItem().getHabitacion().getNombre());
            lblAdultos.setText(reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getAdultos().toString());
            lblMenores.setText(reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getMenores().toString());
            lblNroHabitaciones.setText(reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getCant_habitaciones().toString());
            String servicios = "";
            for (int i = 0; i < reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getServicios().size(); i++) {
                if (i != reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getServicios().size() - 1) {
                   servicios += reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getServicios().get(i).getNombre_servicio() + ", "; 
                }else{
                   servicios += reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getServicios().get(i).getNombre_servicio(); 
                }
            }
            lblServicios.setText(servicios);
            lblSubtotal.setText("$" + reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getPago_subtotal().toString());
            lblTotal.setText("$" + reservasTabla.getSelectionModel().getSelectedItem().getPago_total().toString());
            panelDescr.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleRegresar(){
        panelDescr.setVisible(false);
        lblNombres.setText("");
        lblFecha.setText("");
        lblFechaEntrada.setText("");
        lblFechaSalida.setText("");
        lblHabitacion.setText("");
        lblAdultos.setText("");
        lblMenores.setText("");
        lblNroHabitaciones.setText("");
        lblServicios.setText("");
        lblSubtotal.setText("");
        lblTotal.setText("");
    }

}
