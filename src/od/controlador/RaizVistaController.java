/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import od.Principal;
import od.controlador.servicio.ServicioCuenta;
import od.controlador.servicio.ServicioPersona;
import od.utilidades.Sesiones;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class RaizVistaController {
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
    private Button btnAyuda;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblRol;
    
    private Button aux;
    private Stage ventana;
    private Principal principal;
    
    private ServicioCuenta sc= new ServicioCuenta();
    private ServicioPersona sp = new ServicioPersona();
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        listeners();
       
       // lblAdmin.setText(sp.obtenerPersona(Sesiones.getSesion().getPersona().getId()).getApellidos());
        System.out.println(sp.obtenerPersona(new Long(1))+"");
    }
    
    private void listeners(){
        aux = btnInicio;
        
        btnInicio.setOnAction((event)->{            
            if (claseCSS(btnInicio)) 
                principal.fijarCentro("PanelInicio");             
            event.consume();
        });
        
        btnReservacion.setOnAction((event)->{
            if(claseCSS(btnReservacion))
                principal.fijarCentro("PanelReservaciones");
            event.consume();
        });
        
        btnHabitacion.setOnAction((event)->{
            claseCSS(btnHabitacion);
            event.consume();
        });
        
        btnServicio.setOnAction((event)->{
            claseCSS(btnServicio);
            event.consume();
        });
        
        btnCliente.setOnAction((event)->{
            claseCSS(btnCliente);
            event.consume();
        });
        
        btnConfiguracion.setOnAction((event)->{
            claseCSS(btnConfiguracion);
            event.consume();
        });
        
        btnAyuda.setOnAction((event)->{
            claseCSS(btnAyuda);
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
    
}
