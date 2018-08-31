
package od.utilidades;

import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Clase que permite realizar validacion de los campos de una vista
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class Validadores {
    /**
     * Metodo para viladar un campo TextField
     * @param obj acepta un dato de un textField
     * @return devuelve un boolean
     */
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
    }//Cierre del metodo validarTF
    
    /**
     * Metodo para viladar un campo TextArea
     * @param obj acepta un dato de un textArea
     * @return devuelve un boolean
     */
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
    }//Cierre del metodo validarTA
    
    /**
     * Metodo para viladar un campo DatePicker
     * @param obj acepta un dato de un DatePicker
     * @return devuelve un boolean
     */
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
    }//Cierre del metodo validarDP
    
    /**
     * Metodo para viladar un campo passwordField
     * @param obj acepta un dato de un passwordFiel
     * @return devuelve un boolean
     */
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
    }//Cierre del metodo validarP
    
    /**
     * Metodo para comprobar una clave correcta
     * @param obj acepta un dato de passwordField
     * @param obj1 acepta un dato de passwordField
     * @return devuelve un boolean
     */
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
    }//Cierre del metodo comprobarClave
    
    /**
     * Metodo para comprobar un valor
     * @param obj acepta un dato de TextField
     * @param opcion acepta un dato de tipo char
     * @return devuelve un boolean
     */
    public static boolean validarValor(TextField obj, char opcion){
        switch(opcion){
            case 'i':
                try {
                    Integer.parseInt(obj.getText());
                    if (obj.getStyleClass().contains("error")) 
                        obj.getStyleClass().remove("error");
                    return true;
                } catch (NumberFormatException e) {
                    obj.getStyleClass().add("error"); 
                    obj.setText("");
                    return false;
                }
            case 'd':
                try {
                    Double.parseDouble(obj.getText());
                    if (obj.getStyleClass().contains("error")) 
                        obj.getStyleClass().remove("error");
                    return true;
                } catch (NumberFormatException e) {
                    obj.getStyleClass().add("error"); 
                    obj.setText("");
                    return false;
                }
            default: return false;
        }
    }//Cierre del metodo validarValor
    
    public static boolean validarCorreo(TextField obj){
        if (obj.getText().contains("@")) {
            if (obj.getStyleClass().contains("error")) 
                obj.getStyleClass().remove("error");
            return true; 
        }else{
            if (!obj.getStyleClass().contains("error")) 
                obj.getStyleClass().add("error");                        
            return false;
        }
    }
}//Cierre de la clase validadores
