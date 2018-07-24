/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.utilidades;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Francis
 */
public class Validadores {
    public static boolean validarTF(TextField obj){
        if(obj.getText().isEmpty()){
            obj.setStyle("-fx-border-color: #dd1d1d;");
            return false;  
        }else{
            obj.setStyle("-fx-border-color: #a0a0a0;");
            return true; 
        }
    }
    public static boolean validarDP(DatePicker obj){
        if(obj.getValue() == null){
            obj.setStyle("-fx-border-color: #dd1d1d;");
            return false;  
        }else{
            obj.setStyle("-fx-border-color: #a0a0a0;");
            return true; 
        }
    }
    public static boolean validarP(PasswordField obj){
        if(obj.getText().isEmpty()){
            obj.setStyle("-fx-border-color: #dd1d1d;");
            return false;  
        }else{
            obj.setStyle("-fx-border-color: #a0a0a0;");
            return true; 
        }
    }
    public static boolean comprobarClave(PasswordField obj,PasswordField obj1){
        if(obj.getText().equals(obj1.getText())){
           obj.setStyle("-fx-border-color: #40FF00;");
           obj1.setStyle("-fx-border-color: #40FF00;");
           return true;
        }else{
           obj.setStyle("-fx-border-color: #dd1d1d;");
           obj1.setStyle("-fx-border-color: #dd1d1d;");
           return false;
        }
    }
   
}
