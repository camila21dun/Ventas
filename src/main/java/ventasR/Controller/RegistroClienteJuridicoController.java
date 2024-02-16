package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ventasR.exception.AtributoVacioException;
import ventasR.exception.ElementoNoEncontradoException;
import ventasR.exception.RutaInvalidaException;
import ventasR.model.ClienteJuridico;
import ventasR.model.Empresa;

public class RegistroClienteJuridicoController {

    ClienteJuridico clienteJuridicoSeleccionado;

    ObservableList<ClienteJuridico> listaClienteJuridico = FXCollections.observableArrayList(Empresa.getInstance().getClientesJuridicos());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnRegistrarCliente;

    @FXML
    private Button btnVentas;

    @FXML
    private TableColumn<ClienteJuridico, String> columId;

    @FXML
    private TableColumn<ClienteJuridico, String> columNit;

    @FXML
    private TableColumn<ClienteJuridico, String> columNombre;

    @FXML
    private TableView<ClienteJuridico> tabClientesJuridicos;


    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNit;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private AnchorPane ventana;

    private final Empresa empresa = Empresa.getInstance();

    @FXML
    void actualizarClienteEvent(ActionEvent event) throws RutaInvalidaException {
        actualizarClienteAction();

    }

    private void actualizarClienteAction()   throws RutaInvalidaException {

        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String identificacion = txtIdentificacion.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            String nit = txtNit.getText();

            empresa.actualizarClienteJuridico(nombre,apellido,identificacion,direccion,telefono,nit);

            // Limpia los campos después del registro
            txtNombre.clear();
            txtIdentificacion.clear();
            txtApellido.clear();
            txtDireccion.clear();
            txtTelefono.clear();

            this.tabClientesJuridicos.setItems(listaClienteJuridico);
            actualizarTablaJuridico();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha actualizado correctamente el cliente con la cedula  " + txtIdentificacion.getText());
            alert.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
    @FXML
    void atrasEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");

    }

    @FXML
    void eliminarClienteEvent(ActionEvent event) throws AtributoVacioException, RutaInvalidaException {
        eliminarClienteAction();

    }

    private void eliminarClienteAction() throws AtributoVacioException, RutaInvalidaException {
        ClienteJuridico clienteSeleccionado = tabClientesJuridicos.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                Empresa.getInstance().eliminarClienteJuridico(clienteJuridicoSeleccionado.getIdentificacion());
                txtNombre.clear();
                txtIdentificacion.clear();
                txtApellido.clear();
                txtNit.clear();
                txtDireccion.clear();
                txtTelefono.clear();
                actualizarTablaJuridico();
            } catch (ElementoNoEncontradoException e) {
                // Manejar la excepción si el guía no se encuentra
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            }
        } else {
            // Si no se selecciona ningún guía, muestra un mensaje de error
            throw new AtributoVacioException("Selecciona un guía para eliminar.");
        }
    }

    @FXML
    void registrarClienteEvent(ActionEvent event) {
        registrarClienteAction();

    }

    private void registrarClienteAction() {
        try {
            empresa.registrarClienteJuridico(

                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtIdentificacion.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtNit.getText()

            );
            listaClienteJuridico = FXCollections.observableArrayList(empresa.getClientesJuridicos());

            // Asignar la nueva lista a la tabla
            tabClientesJuridicos.setItems(listaClienteJuridico);

            this.tabClientesJuridicos.setItems(listaClienteJuridico);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el cliente con la identificacion  "+txtIdentificacion.getText());
            alert.show();
            limpiarTexto();

            //   btnIrMenu.setDisable(false);

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();

        }
    }

    private void limpiarTexto() throws IOException {
        txtNombre.setText("");
        txtApellido.setText("");
        txtIdentificacion.setText("");
        txtNit.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");

    }

    @FXML
    void ventasEvent(ActionEvent event) {

    }

    @FXML
    void initialize() throws RutaInvalidaException {
        tablaClientesJ();
        actualizarTablaJuridico();

    }
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
    private boolean validarNumero(String input) {
        return input.matches("\\d+");
    }
    private boolean contieneNumeros(String input) {
        return input.matches(".*\\d+.*");
    }
    public void actualizarTablaJuridico() throws RutaInvalidaException {
        listaClienteJuridico = FXCollections.observableArrayList(empresa.getClientesJuridicos());
        tabClientesJuridicos.getItems().clear();
        tabClientesJuridicos.setItems(listaClienteJuridico);
        tablaClientesJ();
        tabClientesJuridicos.refresh();
    }
    void tablaClientesJ() throws RutaInvalidaException {
        tabClientesJuridicos.setItems(listaClienteJuridico);

        // Personalizar las celdas de las columnas
        columNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        columNit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNit()));

        // Asignar el manejador de selección
        tabClientesJuridicos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteJuridicoSeleccionado = newSelection;
            mostrarInformacion();
        });

        // Actualizar la tabla
        tabClientesJuridicos.refresh();
    }

    private void mostrarInformacion() {
        if(clienteJuridicoSeleccionado!=null){
            txtNombre.setText(clienteJuridicoSeleccionado.getNombre());
            txtIdentificacion.setText(clienteJuridicoSeleccionado.getIdentificacion());
            txtNit.setText(clienteJuridicoSeleccionado.getNit());
        }
    }


}
