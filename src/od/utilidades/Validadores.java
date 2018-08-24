/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.utilidades;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Francis
 */
public class Validadores {
    public static boolean validarTF(TextField obj){
        if(obj.getText().isEmpty()){
            if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error");                        
            return false;  
        }else{
            if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
            return true; 
        }
    }
    public static boolean validarTA(TextArea obj){
        if(obj.getText().isEmpty()){
            if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error");                        
            return false;  
        }else{
            if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
            return true; 
        }
    }
    public static boolean validarDP(DatePicker obj){
        if(obj.getValue() == null){
            if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error"); 
            return false;  
        }else{
            if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
            return true; 
        }
    }
    public static boolean validarP(PasswordField obj){
        if(obj.getText().isEmpty()){
            if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error");
            return false;  
        }else{
            if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
            return true; 
        }
    }
    public static boolean comprobarClave(PasswordField obj,PasswordField obj1){
        if(obj.getText().equals(obj1.getText())){
           if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
           if (obj1.getStyleClass().contains("error")) 
                obj1.getStyleClass().remove("error");
           return true;
        }else{
           if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error");
           if (!obj1.getStyleClass().contains("error")) 
                obj1.getStyleClass().add("error");
           return false;
        }
    }
   
}
