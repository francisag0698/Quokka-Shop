/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import od.controlador.LoginVistaController;
import od.controlador.RegistroVistaController;

/**
 *
 * @author PotatoPower
 */
public class Principal extends Application {
    private Stage primaryStage;
    private ScrollPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {        
        mostrarLoginVista(primaryStage);
    }
    
    public void iniciarRaiz(){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/RaizVista.fxml"));
            rootLayout = (ScrollPane) cargador.load();
            
            primaryStage = new Stage();
            
            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
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
            primaryStage.setMaxHeight(636);
            primaryStage.setMinWidth(950);
            primaryStage.setMinHeight(636);
            
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
            primaryStage.setMaxHeight(636);
            primaryStage.setMinWidth(1005);
            primaryStage.setMinHeight(636);
            
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
