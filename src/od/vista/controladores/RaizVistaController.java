/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.vista.controladores;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import od.Principal;
import od.utilidades.Sesiones;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class RaizVistaController {
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        listeners(); 
        lblAdmin.setText(Sesiones.getCuenta().getPersona().toString());
        menuPorRol();
    }
    
    public void menuPorRol(){
        if (Sesiones.getCuenta().getPersona().getRol().getNombre().equals("Cliente")) {
            lblRol.setText(Sesiones.getCuenta().getPersona().getCiudad().toUpperCase() + ", "
                + Sesiones.getCuenta().getPersona().getPais().toUpperCase());
            btnReservacion.setText("Mis reservaciones");
            btnHabitacion.setVisible(false);
            btnServicio.setVisible(false);
            btnCliente.setVisible(false);
        }else{            
            lblRol.setText(Sesiones.getCuenta().getPersona().getRol().getNombre().toUpperCase());
        }
    }
    
    private void listeners(){
        aux = btnInicio;
        
        btnNuevaReserva.setOnAction((event)->{            
            if (claseCSS(btnInicio) || !rootLayout.getCenter().getId().equals("RegistrarReserva")){
                principal.fijarCentroNuevaReserva();
            }                
            event.consume();
        });
        
        btnInicio.setOnAction((event)->{            
            if (claseCSS(btnInicio)) 
                principal.fijarCentroPane("PanelInicio");             
            event.consume();
        });
        
        btnReservacion.setOnAction((event)->{
            if(claseCSS(btnReservacion))
                principal.fijarCentroPane("PanelReservaciones");
            event.consume();
        });
        
        btnHabitacion.setOnAction((event)->{
            if(claseCSS(btnHabitacion))
                principal.fijarCentroPane("PanelHabitaciones");
            event.consume();
        });
        
        btnServicio.setOnAction((event)->{
            if(claseCSS(btnServicio))
                principal.fijarCentroPane("PanelServicios");
            event.consume();
        });
        
        btnCliente.setOnAction((event)->{
            if(claseCSS(btnCliente))
                principal.fijarCentroPane("PanelClientes");
            event.consume();
        });
        
        btnConfiguracion.setOnAction((event)->{
            if(claseCSS(btnConfiguracion))
                principal.fijarCentroScroll("PanelConfiguracion");
            event.consume();
        });
    }
    
    private boolean claseCSS(Button obj){
        if (obj instanceof Button) {
            Button b = (Button) obj;
            if (!b.getStyleClass().contains("active")) {
                this.aux.getStyleClass().remove("active");
                b.getStyleClass().add("active");
                this.aux = b;
                return true;
            }else
                return false;
        }else
            return false;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setStage(Stage ventana) {
        this.ventana = ventana;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }
    
    
    @FXML
    private void cerrarSesion(){
        ventana.close();
        principal.mostrarLoginVista(ventana);
    }
    
    
    
    /*
     * Objetos de la RAIZ
    */
    @FXML
    private Button btnNuevaReserva;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnReservacion;
    @FXML
    private Button btnHabitacion;
    @FXML
    private Button btnServicio;
    @FXML
    private Button btnCliente;
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblRol;
    
    private Button aux;
    private Stage ventana;
    private Principal principal;
    private BorderPane rootLayout;
}
