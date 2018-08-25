/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import od.controlador.servicio.ServicioPersona;
import od.controlador.servicio.ServicioRol;
import od.modelo.Persona;
import od.utilidades.Validadores;
import od.vista.utilidades.UtilidadesComponentes;

/**
 * FXML Controller class
 *
 * @author PotatoPower
 */
public class PanelClientesController {
    @FXML
    private TableView<Persona> tblCliente;
    @FXML
    private TableColumn<Persona, String> colNyAp;
    @FXML
    private TableColumn<Persona, String> colNroDNI;
    @FXML
    private TableColumn<Persona, String> colGenero;
    @FXML
    private TableColumn<Persona, String> colTelefono;
    @FXML
    private TableColumn<Persona, String> colProcedencia;
    
    @FXML
    private ComboBox cbxTipoDNI;
    @FXML
    private TextField txtNroDNI;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox cbxGenero;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox cbxFiltrarPor;
    @FXML
    private ComboBox cbxComboSeleccion;
    
    @FXML
    private Label lblMensaje;
    
    private ServicioPersona sp = new ServicioPersona();
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cbxGenero.getItems().addAll(
                "Masculino",
                "Femenino",
                "Otros"
        );
        cbxGenero.setValue("Masculino");
        
        cbxFiltrarPor.getItems().addAll(
                "Numero de DNI",
                "Genero"
        );
        
        cbxComboSeleccion.getItems().addAll(
                "Masculino",
                "Femenino",
                "Otros"
        );
        cargarTabla();
    }
    
    public void cargarTabla(){
        tblCliente.setItems(FXCollections.observableList(sp.listarSinAdministrador()));
        colNyAp.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getNombres() + " "
                        + cellData.getValue().getApellidos())
        );
        colNroDNI.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDni())
        );
        colGenero.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getSexo())
        );
        colTelefono.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getTelefono())
        );
        colProcedencia.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCiudad())
        );
    }
    
    private boolean validar(){
        if (Validadores.validarTF(txtNroDNI)
                & Validadores.validarTF(txtNombres)
                & Validadores.validarTF(txtApellidos)
                & Validadores.validarDP(dpFecha)
                & Validadores.validarTF(txtTelefono)
                & Validadores.validarTF(txtPais)
                & Validadores.validarTF(txtCiudad)
                & Validadores.validarTF(txtDireccion)){
            if (UtilidadesComponentes.validadorDeCedula(txtNroDNI.getText())) {
                if (sp.ObtenerPersonaCedula(txtNroDNI.getText()) == null) {
                    lblMensaje.setText("");
                    return true;
                }else{
                    lblMensaje.setText("Nro de Identificacion existente.");
                    return false;
                }
            }else{
                lblMensaje.setText("El numero de identificacion es incorrecto.");
                return false; 
            }
        }else{
            lblMensaje.setText("Rellene los campos vacíos.");
            return false;
        }
    }
    
    private void cargarObjeto(){
        sp.getPersona().setDni(txtNroDNI.getText());
        sp.getPersona().setNombres(txtNombres.getText());
        sp.getPersona().setApellidos(txtApellidos.getText());
        try {
            sp.getPersona().setFecha_nacimiento(new SimpleDateFormat("dd-MM-yyyy").parse(dpFecha.getValue().toString()));            
        } catch (ParseException e) {
            sp.getPersona().setFecha_nacimiento(new Date());
        }
        sp.getPersona().setSexo(cbxGenero.getValue().toString());
        sp.getPersona().setTelefono(txtTelefono.getText());
        sp.getPersona().setPais(txtPais.getText());
        sp.getPersona().setCiudad(txtCiudad.getText());
        sp.getPersona().setDireccion(txtDireccion.getText());
        sp.getPersona().setRol(new ServicioRol().buscarRolNombre("Cliente"));
    }
    
    private void limpiar(){
        txtNroDNI.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        dpFecha.setValue(null);
        cbxGenero.setValue("Masculino");
        txtTelefono.setText("");
        txtPais.setText("");
        txtCiudad.setText("");
        txtDireccion.setText("");
        sp.fijarPersona(null);
    }
    
    @FXML
    private void buscarTexto(){
        if (txtBuscar.getText().trim().length() >= 3 || cbxFiltrarPor.getValue().equals("Genero")) {
            if (cbxFiltrarPor.getValue() == null) {
                tblCliente.setItems(FXCollections.observableList(sp.listarSinAdministradorBusqueda(txtBuscar.getText())));
                tblCliente.refresh();
            }else if(cbxFiltrarPor.getValue().equals("Numero de DNI")){
                tblCliente.setItems(FXCollections.observableList(sp.listarSinAdministradorDNIBusqueda(txtBuscar.getText())));
                tblCliente.refresh();
            }else{
                if (cbxComboSeleccion.getValue() != null) {
                    tblCliente.setItems(FXCollections.observableList(sp.listarSinAdministradorGeneroBusqueda(txtBuscar.getText(), cbxComboSeleccion.getValue().toString())));
                    tblCliente.refresh();
                }else{
                    tblCliente.setItems(FXCollections.observableList(sp.listarSinAdministradorBusqueda(txtBuscar.getText())));
                    tblCliente.refresh();
                }
            }
        }else if(txtBuscar.getText().trim().length() == 0){
            cargarTabla();
        }
    }
    
    @FXML
    private void guardar(){
        if (validar()) {
            cargarObjeto();
            if (sp.guardar()) {
                System.out.println("Guardado");
                limpiar();
                cargarTabla();
            }            
        }
    }
    
    @FXML
    private void borrarCampos(){
        limpiar();
    }
    
    @FXML
    private void controlCombo(){
        if (cbxFiltrarPor.getValue().equals("Genero")) {
            txtBuscar.setText("");
            cbxComboSeleccion.setDisable(false);
        }else{
            cbxComboSeleccion.setValue(null);
            cbxComboSeleccion.setDisable(true);
            buscarTexto();
        }        
    }
}
