/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import od.controlador.servicio.ServicioHabitacion;
import od.controlador.servicio.ServicioHistorial;
import od.controlador.servicio.ServicioPersona;
import od.controlador.servicio.ServicioReservacion;
import od.modelo.Reservacion;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;


/**
 * FXML Controller class
 *
 * @author Gemelos
 */
public class PanelInicioController {
    
    @FXML
    private VBox vbNotificaciones;
    @FXML
    private TableView<Reservacion> tablaReservas;
    @FXML
    private TableColumn<Reservacion, String> nyap;
    @FXML
    private TableColumn<Reservacion, String> habitacion;
    
    @FXML
    private Label lblCantReservas;
    @FXML
    private Label lblCantClientes;
    @FXML
    private Label lblCantHabitaciones;
    @FXML
    private Label lblCliente;
    
    private ServicioPersona sp = new ServicioPersona();
    private ServicioReservacion sr = new ServicioReservacion();
    private ServicioHistorial sh = new ServicioHistorial();
    private ServicioHabitacion sha = new ServicioHabitacion();
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cargarHistorial();
        cargarTabla();
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
            lblCantReservas.setText(sr.nroReservasActivas(Sesiones.getCuenta().getPersona().getId_persona()).toString());
            lblCantClientes.setText(sr.nroReservasTotalesCliente(Sesiones.getCuenta().getPersona().getId_persona()).toString());
            lblCliente.setText("RESERVAS REALIZADAS");
        }else{
            lblCantReservas.setText(sr.nroReservasActivas().toString());
            lblCantClientes.setText(sp.nroUsuarios().toString());
        }
        
        
        Long n = sha.cantidadDisponibles(new Date(), new Date());
        if (n != null) 
            lblCantHabitaciones.setText(n.toString());
        else
            lblCantHabitaciones.setText("0");
    }
    
    private void cargarHistorial(){
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Administrador")) {
            sh.todos().stream().map((obj) -> {
                VBox p = new VBox();
                p.getStyleClass().add("v-box");
    //            Label a = new Label(obj.getIdentificador());
    //            a.getStyleClass().add("identificador");
                Label b = new Label(obj.getAccion());
                b.getStyleClass().add("accion");
                Label c = new Label(obj.getPersona().getNombres().toUpperCase() + " • " + Utilidades.formatearFechaDos(obj.getFecha()));
                c.getStyleClass().add("usuario");
    //            p.getChildren().add(a);
                p.getChildren().add(b);
                p.getChildren().add(c);
                return p;
            }).forEachOrdered((p) -> {
                vbNotificaciones.getChildren().add(p);
            });
        }else{
            sh.listarPorPersona(Sesiones.getCuenta().getPersona().getId_persona()).stream().map((obj) ->{
               VBox p = new VBox();
                p.getStyleClass().add("v-box");
                Label b = new Label(obj.getAccion());
                b.getStyleClass().add("accion");
                Label c = new Label(Utilidades.formatearFechaDos(obj.getFecha()));
                c.getStyleClass().add("usuario");
                p.getChildren().add(b);
                p.getChildren().add(c);
                return p; 
            }).forEachOrdered((p) ->{
                vbNotificaciones.getChildren().add(p);
            });
        }        
    }
    
    private void cargarTabla(){
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
            tablaReservas.setItems(FXCollections.observableList(sr.listarPorPersona(Sesiones.getCuenta().getPersona().getId_persona())));
        }else
            tablaReservas.setItems(FXCollections.observableList(sr.todos()));
                
        nyap.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPersona().getNombres() + " " + cellData.getValue().getPersona().getApellidos())
        );
        habitacion.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getHabitacion().getNombre())
        );
        tablaReservas.refresh();
    }
    
}
