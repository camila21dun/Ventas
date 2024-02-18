package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import ventasR.model.ClienteNatural;
import ventasR.model.Empresa;

public class RegistroClienteNaturalController {

    ClienteNatural clienteNaturalSeleccionado;

    ObservableList<ClienteNatural> listaClienteNatural = FXCollections.observableArrayList(Empresa.getInstance().getClientesNaturales());

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
    private TableColumn<ClienteNatural, String> columEmail;

    @FXML
    private TableColumn<ClienteNatural, String> columId;

    @FXML
    private TableColumn<ClienteNatural, String> columNombre;

    @FXML
    private DatePicker dateFechaN;

    @FXML
    private TableView<ClienteNatural> tabClientesNaturales;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentificacion;

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

    private void actualizarClienteAction() throws RutaInvalidaException {

        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String identificacion = txtIdentificacion.getText();
            String telefono = txtTelefono.getText();
            String direccion = txtDireccion.getText();
            String email = txtEmail.getText();
            LocalDate fechaNacimiento= dateFechaN.getValue();

            empresa.actualizarClienteNatural(nombre,apellido,identificacion,direccion,telefono,email,fechaNacimiento);

            // Limpia los campos después del registro
            txtNombre.clear();
            txtIdentificacion.clear();
            txtApellido.clear();
            txtEmail.clear();
            txtDireccion.clear();
            txtTelefono.clear();
            dateFechaN.setValue(null);


            this.tabClientesNaturales.setItems(listaClienteNatural);
            actualizarTablaNaturales();
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
        new ViewController(ventana, "/views/registroCliente.fxml");

    }
    @FXML
    void eliminarClienteEvent(ActionEvent event) throws AtributoVacioException, RutaInvalidaException {
        ClienteNatural clienteSeleccionado = tabClientesNaturales.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                Empresa.getInstance().eliminarClienteNatural(clienteNaturalSeleccionado.getIdentificacion());
                txtNombre.clear();
                txtIdentificacion.clear();
                txtApellido.clear();
                txtEmail.clear();
                txtDireccion.clear();
                txtTelefono.clear();
                dateFechaN.setValue(null);
                actualizarTablaNaturales();
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
        if (validarCampos()) {
            registrarClienteAction();
        }


    }

    private boolean validarCampos() {
        if (!validarNumero(txtIdentificacion.getText())) {
            mostrarAlerta("Identificacion inválida. Ingrese solo números.");
            return false;
        }

        if (!validarNumero(txtTelefono.getText())) {
            mostrarAlerta("Teléfono inválido. Ingrese solo números.");
            return false;
        }

        if (txtTelefono.getText().length() != 10) {
            mostrarAlerta("El teléfono debe tener 10 dígitos.");
            return false;
        }
        if (contieneNumeros(txtNombre.getText())) {
            mostrarAlerta("El nombre no debe contener números.");
            return false;
        }

        return true;
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


    private void registrarClienteAction() {


        try {
            empresa.registrarClienteNatural(

                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtIdentificacion.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText(),
                    txtEmail.getText(),
                    dateFechaN.getValue()
            );
            listaClienteNatural = FXCollections.observableArrayList(empresa.getClientesNaturales());

            // Asignar la nueva lista a la tabla
            tabClientesNaturales.setItems(listaClienteNatural);

            this.tabClientesNaturales.setItems(listaClienteNatural);

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
        txtEmail.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        dateFechaN.setValue(null);

    }

    public void actualizarTablaNaturales() throws RutaInvalidaException {
        listaClienteNatural = FXCollections.observableArrayList(empresa.getClientesNaturales());
        tabClientesNaturales.getItems().clear();
        tabClientesNaturales.setItems(listaClienteNatural);
        tablaClientesN();
        tabClientesNaturales.refresh();
    }
    void tablaClientesN() throws RutaInvalidaException {
        tabClientesNaturales.setItems(listaClienteNatural);

        // Personalizar las celdas de las columnas
        columNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        columEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        // Asignar el manejador de selección
        tabClientesNaturales.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            clienteNaturalSeleccionado = newSelection;
            mostrarInformacion();
        });

        // Actualizar la tabla
        tabClientesNaturales.refresh();
    }

    private void mostrarInformacion() {
        if(clienteNaturalSeleccionado!=null){
            txtNombre.setText(clienteNaturalSeleccionado.getNombre());
            txtApellido.setText(clienteNaturalSeleccionado.getApellido());
            txtIdentificacion.setText(clienteNaturalSeleccionado.getIdentificacion());
            txtTelefono.setText(clienteNaturalSeleccionado.getTelefono());
            txtDireccion.setText(clienteNaturalSeleccionado.getDireccion());
            txtEmail.setText(clienteNaturalSeleccionado.getEmail());
            dateFechaN.setValue(clienteNaturalSeleccionado.getFechaNacimiento());
        }
    }


    @FXML
    void ventasEvent(ActionEvent event) throws IOException{
        new ViewController(ventana, "/views/registroVentas.fxml");

    }

    @FXML
    void initialize() throws RutaInvalidaException {
        tablaClientesN();
        actualizarTablaNaturales();

    }

}
