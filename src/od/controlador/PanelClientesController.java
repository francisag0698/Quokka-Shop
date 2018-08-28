/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package od.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import od.controlador.servicio.ServicioPersona;
import od.controlador.servicio.ServicioRol;
import od.modelo.Persona;
import od.utilidades.Sesiones;
import od.utilidades.Utilidades;
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
    private TabPane tbpClientes;
    @FXML
    private Tab tabListado;
    @FXML
    private Tab tabFormulario;
    
    @FXML
    private Pane panelDescr;
    @FXML
    private Label lblDNI;
    @FXML
    private Label lblNombres;
    @FXML
    private Label lblApellidos;
    @FXML
    private Label lblNacimiento;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblPais;
    @FXML
    private Label lblCiudad;
    @FXML
    private Label lblDireccion;    
    
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
        tblCliente.refresh();
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
                if (sp.ObtenerPersonaCedula(txtNroDNI.getText()) == null || sp.getPersona().getId_persona() != null) {
                    return true;
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Registo Duplicado");
                    alert.setHeaderText("");
                    alert.setContentText("La cédula ingresada ya existe.");
                    alert.showAndWait();
                    return false;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dato Incorrecto");
                alert.setHeaderText("");
                alert.setContentText("La cédula ingresada es incorrecta.");
                alert.showAndWait();
                return false; 
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText("");
            alert.setContentText("Rellene todos los campos vacíos.");
            alert.showAndWait();
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
        txtNroDNI.setDisable(false);
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
        if (txtBuscar.getText().trim().length() >= 3 || (cbxFiltrarPor.getValue() != null && cbxFiltrarPor.getValue().equals("Genero"))) {
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
            boolean band = true;
            if (sp.getPersona().getId_persona() != null) {
                band = false;
            }
            cargarObjeto();
            if (sp.guardar()) {
                if (band) {
                    Utilidades.guardarHistorial("Nuevo Registro", "Nuevo Usuario Registrado", Sesiones.getCuenta().getPersona());
                }else{
                    Utilidades.guardarHistorial("Registro Modificado", "Se ha modificado un Usuario.", Sesiones.getCuenta().getPersona());
                }
                limpiar();
                cargarTabla();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guardado");
                alert.setHeaderText("");
                alert.setContentText("Se ha guardado el registro correctamente.");
                alert.showAndWait();
                tbpClientes.getSelectionModel().select(tabListado);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Ha ocurrido un error al guardar.");
                alert.showAndWait();
            }          
        }
    }
    
    @FXML
    private void handleModificar(){
        if (tblCliente.getSelectionModel().getSelectedItem() != null){
            sp.fijarPersona(tblCliente.getSelectionModel().getSelectedItem());
            txtNroDNI.setText(sp.getPersona().getDni());
            txtNroDNI.setDisable(true);
            txtNombres.setText(sp.getPersona().getNombres());
            txtApellidos.setText((sp.getPersona().getApellidos()));
            dpFecha.setValue(LocalDate.parse(sp.getPersona().getFecha_nacimiento().toString()));
            cbxGenero.setValue(sp.getPersona().getSexo());
            txtTelefono.setText(sp.getPersona().getTelefono());
            txtPais.setText(sp.getPersona().getPais());
            txtCiudad.setText(sp.getPersona().getCiudad());
            txtDireccion.setText(sp.getPersona().getDireccion());
            
            tbpClientes.getSelectionModel().select(tabFormulario);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }        
    }
    
    @FXML
    private void borrarCampos(){
        limpiar();
    }
    
    @FXML
    private void controlCombo(){
        if (cbxFiltrarPor.getValue() != null && cbxFiltrarPor.getValue().equals("Genero")) {
            txtBuscar.setText("");
            cbxComboSeleccion.setDisable(false);
        }else{
            cbxComboSeleccion.setValue(null);
            cbxComboSeleccion.setDisable(true);
            buscarTexto();
        }        
    }
    
    @FXML
    private void handleDetalles(){
        if (tblCliente.getSelectionModel().getSelectedItem() != null){
            lblDNI.setText(tblCliente.getSelectionModel().getSelectedItem().getDni());
            lblNombres.setText(tblCliente.getSelectionModel().getSelectedItem().getNombres());
            lblApellidos.setText(tblCliente.getSelectionModel().getSelectedItem().getApellidos());
            lblNacimiento.setText(tblCliente.getSelectionModel().getSelectedItem().getFecha_nacimiento().toString());
            lblGenero.setText(tblCliente.getSelectionModel().getSelectedItem().getSexo());
            lblTelefono.setText(tblCliente.getSelectionModel().getSelectedItem().getTelefono());
            lblPais.setText(tblCliente.getSelectionModel().getSelectedItem().getPais());
            lblCiudad.setText(tblCliente.getSelectionModel().getSelectedItem().getCiudad());
            lblDireccion.setText(tblCliente.getSelectionModel().getSelectedItem().getDireccion());
            panelDescr.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText("");
            alert.setContentText("Por favor, seleccione un elemento de la tabla.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleRegresar(){
        panelDescr.setVisible(false);
        lblDNI.setText("");
        lblNombres.setText("");
        lblApellidos.setText("");
        lblNacimiento.setText("");
        lblGenero.setText("");
        lblTelefono.setText("");
        lblPais.setText("");
        lblCiudad.setText("");
        lblDireccion.setText("");
    }
    
    @FXML
    private void handleBarrer(){
        txtBuscar.setText("");
        cbxFiltrarPor.setValue(null);
        cbxComboSeleccion.setValue(null);
        cbxComboSeleccion.setDisable(true);
        cargarTabla();
    }
}
