
package od.vista.utilidades;

/**
 * Clase que permite rutilizar metodos dentro de la vista
 * @author Agreda Francisco
 * @author Macas Dennis
 * @author Ortega César
 * @version JDK 1.8
 */
public class UtilidadesComponentes {
    /**
     * Metodo que permite validar un cedula 
     * @param cedula acepta un dato cedula de tipo String
     * @return devuelve un boolean
     */
    public static boolean validadorDeCedula(String cedula) {
        try {
            if (cedula.length() == 10)
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        
                        return true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        
                        return true;
                    } else {                        
                        return false;
                    }
                } else {                    
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }//Cierre del metodo validarCedula
}//Cierre de la clase UtilidadesComponente
