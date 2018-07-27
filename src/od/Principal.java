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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import od.controlador.LoginVistaController;
import od.controlador.RaizVistaController;
import od.controlador.RegistroVistaController;

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
        
        mostrarLoginVista(primaryStage);
    }
    
    public void iniciarRaiz(){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/RaizVista.fxml"));
            rootLayout = (BorderPane) cargador.load();
            
            primaryStage = new Stage();
            
            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            
            RaizVistaController controlador = cargador.getController();
            
            controlador.setStage(primaryStage);
            controlador.setPrincipal(this);
            fijarCentro("PanelInicio");
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
            primaryStage.setMaxHeight(660);
            primaryStage.setMinWidth(950);
            primaryStage.setMinHeight(660);
            
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
    
    public void fijarCentro(String fxml){
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Principal.class.getResource("vista/"+fxml+".fxml"));
            Pane panel = (Pane) cargador.load();
            
            rootLayout.setCenter(panel);
            
            /*NoticiasVistaController controlador = cargador.getController();
            controlador.setPrincipal(this);*/
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
