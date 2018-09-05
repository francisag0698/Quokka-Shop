/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.vista.controladores;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
        
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) btnDarDeAlta.setVisible(false);        
    }

    public void cargarTabla() {

        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
            reservasTabla.setItems(FXCollections.observableList(sr.listarPorPersona(Sesiones.getCuenta().getPersona().getId_persona())));
        } else {
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
                cellData -> new SimpleStringProperty((LocalDate.parse(Utilidades.formatearFecha(cellData.getValue().getFecha_fin())).isBefore(LocalDate.now()) && cellData.getValue().getEstado().equals(Boolean.TRUE)) ? "Caducada": ((cellData.getValue().getEstado()) ? "Activo" : "Inactivo"))
        );

        reservasTabla.refresh();
    }

    @FXML
    public void limpiar() {
        campoBuscar.setText("");
        comboEstado.setValue(null);
        //comboOrdenar.setValue(null);
        cargarTabla();
    }

    @FXML
    private void filtrarTipo() {
        if (comboEstado.getValue() == null && campoBuscar.getText().trim().length() == 0) {
            cargarTabla();
        } else if (comboEstado.getValue() != null) {
            Boolean tipo = comboEstado.getValue().equals("Activo");
            if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
                reservasTabla.setItems(FXCollections.observableList(sr.listarTipo(tipo, Sesiones.getCuenta().getPersona().getId_persona())));
            } else {
                reservasTabla.setItems(FXCollections.observableList(sr.listarTipo(tipo)));
            }
            reservasTabla.refresh();
        }
    }

    @FXML
    private void buscarTexto() {

        if (campoBuscar.getText().trim().length() >= 3) {
            if (comboEstado.getValue() == null) {
                if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusqueda(campoBuscar.getText(), Sesiones.getCuenta().getPersona().getId_persona())));
                } else {
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusqueda(campoBuscar.getText())));
                }
            } else {
                Boolean tipo = comboEstado.getValue().equals("Activo");
                if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusquedaTipo(tipo, campoBuscar.getText(), Sesiones.getCuenta().getPersona().getId_persona())));
                } else {
                    reservasTabla.setItems(FXCollections.observableList(sr.listarBusquedaTipo(tipo, campoBuscar.getText())));
                }
            }

            reservasTabla.refresh();
        } else {
            filtrarTipo();
        }
    }

//    @FXML
//    private void ordenarAscendente() {
//        if (comboOrdenar.getValue() != null && comboOrdenar.getValue().toString().equals("Ordenar por:")) {
//            if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente"))
//                reservasTabla.setItems(FXCollections.observableList(sr.listarPorPersona(Sesiones.getCuenta().getPersona().getId_persona())));
//            else
//                reservasTabla.setItems(FXCollections.observableList(sr.todos()));
//            reservasTabla.refresh();
//        } else {
//            if (comboOrdenar.getValue() != null) {
//                String orden = comboOrdenar.getValue().toString();
//                if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente"))
//                    reservasTabla.setItems(FXCollections.observableList(sr.ordenAscendente(orden, Sesiones.getCuenta().getPersona().getId_persona())));
//                else
//                    reservasTabla.setItems(FXCollections.observableList(sr.ordenAscendente(orden)));
//                reservasTabla.refresh();
//            }
//        }
//    }

    @FXML
    private void handleDetalles() {
        if (reservasTabla.getSelectionModel().getSelectedItem() != null) {
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
                } else {
                    servicios += reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getServicios().get(i).getNombre_servicio();
                }
            }
            lblServicios.setText(servicios);
            lblSubtotal.setText("$" + reservasTabla.getSelectionModel().getSelectedItem().getDetalle().getPago_subtotal().toString());
            lblTotal.setText("$" + reservasTabla.getSelectionModel().getSelectedItem().getPago_total().toString());
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
    private void handleAlta(){
        if (reservasTabla.getSelectionModel().getSelectedItem() != null) {
            sr.fijarReservacion(reservasTabla.getSelectionModel().getSelectedItem());
            NumberFormat df = new DecimalFormat("#0.00");
            
            double hab = sr.getReservacion().getHabitacion().getPrecio() * sr.getReservacion().getDetalle().getCant_habitaciones();
            lblValorHabitaciones.setText("$" + df.format(hab));
            
            long nochesT = ChronoUnit.DAYS.between(LocalDate.parse(Utilidades.formatearFecha(sr.getReservacion().getFecha_inicio())), LocalDate.parse(Utilidades.formatearFecha(sr.getReservacion().getFecha_fin())));
            long noches = ChronoUnit.DAYS.between(LocalDate.parse(Utilidades.formatearFecha(sr.getReservacion().getFecha_inicio())), LocalDate.now());
            
            if (noches < 0) 
                noches = 0;
            else if (noches == 0)
                noches++;
            else if (noches > nochesT)
                noches = nochesT;
            
            lblNroNoches.setText(""+noches);
            
            lblValorServicios.setText("$"+ df.format(sr.getReservacion().getDetalle().getPago_subtotal()));
            
            double b = (hab + sr.getReservacion().getDetalle().getPago_subtotal()) * noches;
            lblSubtotalCheck.setText("$" + df.format(b));
            
            double iva = b * 0.14;
            lblIVACheck.setText("$" + df.format(iva));
            lblTotalCheck.setText("$" + df.format(b + iva));
            
            panelAlta.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDarAlta(){
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);        
        conf.setTitle("Confirmación");
        conf.setHeaderText("¿Está seguro/a de realizar esta acción?");
        conf.setContentText("Presione Aceptar para confirmarlo.");
        conf.showAndWait();
        
        if (conf.getResult().getText().equals("Aceptar")){
            sr.getReservacion().setEstado(Boolean.FALSE);
            if (sr.guardar()) {                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto");
                alert.setHeaderText("");
                alert.setContentText("El Check Out se ha realizado correctamente.");
                alert.showAndWait();
                
                cerrarCheckOut();
                cargarTabla();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Ha ocurrido un error al guardar.");
                alert.showAndWait();
            }
        }            
    }

    @FXML
    private void handleRegresar() {
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
    
    @FXML
    private void cerrarCheckOut(){
        lblValorHabitaciones.setText("00.00");
        lblValorServicios.setText("00.00");
        lblSubtotalCheck.setText("00.00");
        lblIVACheck.setText("00.00");
        lblTotalCheck.setText("00.00");
        sr.fijarReservacion(null);
        panelAlta.setVisible(false);
    }
    
    
    
    /*
     * Objetos de RESERVACIONES
    */
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
//    @FXML
//    private ComboBox comboOrdenar;

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
    
    @FXML
    private Pane panelAlta;
    @FXML
    private Label lblValorHabitaciones;
    @FXML
    private Label lblValorServicios;
    @FXML
    private Label lblSubtotalCheck;
    @FXML
    private Label lblIVACheck;
    @FXML
    private Label lblTotalCheck;
    @FXML
    private Label lblNroNoches;
    @FXML
    private Button btnDarDeAlta;
    
    private Double totalReserva;
    private ServicioReservacion sr = new ServicioReservacion();

}
