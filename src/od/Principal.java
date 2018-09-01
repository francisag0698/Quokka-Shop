/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import od.controlador.Conexion;
import od.vista.controladores.LoginVistaController;
import od.vista.controladores.PanelNuevaReservacionController;
import od.vista.controladores.RaizVistaController;
import od.vista.controladores.RegistroVistaController;

/**
 *
 * @author PotatoPower
 */
public class Principal extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/UB.ttf"), 12); //Ubuntu Bold
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/UR.ttf"), 12); //Ubuntu Regular
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/UM.ttf"), 12); //Ubuntu Medium
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/RL.ttf"), 12); //Roboto Condensed Light
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/RR.ttf"), 12); //Roboto Condensed Regular
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/Font Awesome 5 Free-Solid-900.otf"), 12); //Font Awesome
        Font.loadFont(getClass().getResourceAsStream("vista/fuentes/Font Awesome 5 Free-Regular-400.otf"), 12); //Font Awesome
        Conexion.initManager();
        closeRequest(primaryStage);
        mostrarLoginVista(primaryStage);
    }
    
    public void closeRequest(Stage primaryStage){
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            Platform.runLater(() -> {
                System.out.println("Cerrando aplicación...");
                System.exit(0);
            });
        });
    }
    
    public void iniciarRaiz(){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/RaizVista.fxml"));
            rootLayout = (BorderPane) cargador.load();
            
            primaryStage = new Stage();
            
            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            primaryStage.setMinWidth(1030);
            primaryStage.setMaxWidth(1030);
            
            RaizVistaController controlador = cargador.getController();
            
            controlador.setStage(primaryStage);
            controlador.setPrincipal(this);
            controlador.setRootLayout(rootLayout);
            fijarCentroPane("PanelInicio");
            closeRequest(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mostrarLoginVista(Stage primaryStage){
        try{
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/LoginVista.fxml"));
            Pane pagina = (Pane) cargador.load();
            
            primaryStage.setTitle("Sistema de Reservación de Habitaciones | Login");
            primaryStage.setMaxWidth(950);
            primaryStage.setMaxHeight(668);
            primaryStage.setMinWidth(950);
            primaryStage.setMinHeight(668);
            
            Scene escena = new Scene(pagina);
            primaryStage.setScene(escena);
            
            LoginVistaController controlador = cargador.getController();
            controlador.setDialogStage(primaryStage);
            controlador.setPrincipal(this);
            primaryStage.show();
        }catch (IOException e){
            System.out.println("Error en la vista 'Login': " + e);
        }
    }
    
    public void mostrarRegistroVista(Stage primaryStage){
        try{
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/RegistroVista.fxml"));
            AnchorPane pagina = (AnchorPane) cargador.load();
            
            
            primaryStage.setTitle("Sistema de Reservación de Habitaciones | Registro");
            primaryStage.setMaxWidth(1005);
            primaryStage.setMaxHeight(660);
            primaryStage.setMinWidth(1005);
            primaryStage.setMinHeight(660);
            
            Scene escena = new Scene(pagina);            
            primaryStage.setScene(escena);
            
            RegistroVistaController controlador = cargador.getController();
            controlador.setDialogStage(primaryStage);
            controlador.setPrincipal(this);
            
            primaryStage.show();
        }catch (IOException e){
            System.out.println("Error en la vista 'Registro': " + e);
        }
    }
    
    public void fijarCentroPane(String fxml){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/" + fxml + ".fxml"));
            Pane panel = (Pane) cargador.load();
            
            rootLayout.setCenter(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void fijarCentroScroll(String fxml){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/" + fxml + ".fxml"));
            ScrollPane spanel = (ScrollPane) cargador.load();
            
            rootLayout.setCenter(spanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void fijarCentroNuevaReserva(){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/PanelNuevaReservacion.fxml"));
            ScrollPane spanel = (ScrollPane) cargador.load();
            
            PanelNuevaReservacionController controlador = cargador.getController();
            controlador.setPrincipal(this);
            rootLayout.setCenter(spanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
