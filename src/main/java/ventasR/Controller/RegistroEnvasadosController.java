package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import ventasR.model.Empresa;
import ventasR.model.Envasado;
import ventasR.model.Pais;
import ventasR.model.Perecedero;

public class RegistroEnvasadosController {

    Envasado envasadoSeleccionado;

    ObservableList<Envasado> listaProductoEnvasado = FXCollections.observableArrayList(Empresa.getInstance().getListaEnvasados());


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarEnvasado;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminarEnvasado;

    @FXML
    private Button btnRegistrarEnvasado;

    @FXML
    private ComboBox<Pais> cbPais;


    @FXML
    private TableColumn<Envasado, Integer> columCantidad;

    @FXML
    private TableColumn<Envasado, String> columCodigo;

    @FXML
    private TableColumn<Envasado, Pais> columPais;

    @FXML
    private TableColumn<Envasado, Double> columValor;

    @FXML
    private DatePicker dateFechaEnvasado;

    @FXML
    private TableView<Envasado> tabEnvasados;

    @FXML
    private TextField txtCantExistente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtValorUnitario;

    @FXML
    private Button btnDevolver;


    @FXML
    private AnchorPane ventana;
    private final Empresa empresa = Empresa.getInstance();

    @FXML
    void devolverEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");
    }

    @FXML
    void atrasEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/registroVentas.fxml");

    }
    @FXML
    void registrarEnvasadoEvent(ActionEvent event) {
        registrarEnvasadoAction();

    }

    private void registrarEnvasadoAction() {
        try {
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int cantidadExistente = Integer.parseInt(txtCantExistente.getText());
            Pais pais = cbPais.getValue();




            empresa.registrarProductoEnvasado(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    valorUnitario,
                    cantidadExistente,
                    dateFechaEnvasado.getValue(),
                    peso,pais
 );

            listaProductoEnvasado = FXCollections.observableArrayList(empresa.getListaEnvasados());

            // Asignar la nueva lista a la tabla
            tabEnvasados.setItems(listaProductoEnvasado);

            this.tabEnvasados.setItems(listaProductoEnvasado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el producto con el codigo  " + txtCodigo.getText());
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
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCantExistente.setText("");
        txtValorUnitario.setText("");
        txtPeso.setText("");
        dateFechaEnvasado.setValue(null);
        cbPais.setValue(null);
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



    @FXML
    void actualizarEnvasadoEvent(ActionEvent event) {
        actualizarEnvasadoAction();

    }
    private void actualizarEnvasadoAction() {
        try {
            String nombre = txtNombre.getText();
            String codigo = txtCodigo.getText();
            String descripcion = txtDescripcion.getText();
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            int cantidadExistente = Integer.parseInt(txtCantExistente.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            LocalDate fechaEnvasado= dateFechaEnvasado.getValue();
            Pais pais = cbPais.getValue();

            empresa.actualizarProductoEnvasado(codigo,nombre,descripcion,valorUnitario,cantidadExistente,fechaEnvasado,peso,pais);

            // Limpia los campos después del registro
            txtNombre.clear();
            txtCodigo.clear();
            txtDescripcion.clear();
            txtValorUnitario.clear();
            txtCantExistente.clear();
            txtPeso.clear();
            dateFechaEnvasado.setValue(null);
            cbPais.setValue(null);

            this.tabEnvasados.setItems(listaProductoEnvasado);
            actualizarTablaEnvasados();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha actualizado correctamente el producto con el codigo  " +codigo);
            alert.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }


    }
    private void actualizarTablaEnvasados()throws RutaInvalidaException {
        listaProductoEnvasado = FXCollections.observableArrayList(empresa.getListaEnvasados());
        tabEnvasados.getItems().clear();
        tabEnvasados.setItems(listaProductoEnvasado);
        tablaProductosEnvasados();
        tabEnvasados.refresh();
    }
    void tablaProductosEnvasados() throws RutaInvalidaException {
        tabEnvasados.setItems(listaProductoEnvasado);

        columCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCantExistencia()).asObject());
        columValor.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorUnitario()).asObject());
        columPais.setCellValueFactory(cellData -> {
            Pais pais = cellData.getValue().getPais();
            return new SimpleObjectProperty<>(pais);
        });

        // Actualizar la lista de productos envasados antes de asignarla a la tabla
        listaProductoEnvasado = FXCollections.observableArrayList(empresa.getListaEnvasados());
        tabEnvasados.setItems(listaProductoEnvasado);

        // Restablecer el listener de selección de la tabla
        tabEnvasados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            envasadoSeleccionado = newSelection;
            mostrarInformacion();
        });

        tabEnvasados.refresh();
    }

    private void mostrarInformacion() {
        if(envasadoSeleccionado!=null){
            txtCodigo.setText(envasadoSeleccionado.getCodigo());
            txtNombre.setText(envasadoSeleccionado.getNombre());
            txtDescripcion.setText(envasadoSeleccionado.getDescripcion());
            txtCantExistente.setText(String.valueOf(envasadoSeleccionado.getCantExistencia()));
            txtValorUnitario.setText(String.valueOf(envasadoSeleccionado.getValorUnitario()));
            txtPeso.setText(String.valueOf(envasadoSeleccionado.getPeso()));
            dateFechaEnvasado.setValue(envasadoSeleccionado.getFechaEnvasado());
        }
    }

    @FXML
    void eliminarEnvasadoEvent(ActionEvent event) throws AtributoVacioException {
        eliminarEnvasadoAction();

    }

    private void eliminarEnvasadoAction() throws AtributoVacioException {
        Envasado envasadoSeleccionado = tabEnvasados.getSelectionModel().getSelectedItem();

        if (envasadoSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                Empresa.getInstance().eliminarEnvasado(envasadoSeleccionado.getCodigo());
                txtNombre.clear();
                txtCodigo.clear();
                txtCantExistente.clear();
                txtDescripcion.clear();
                txtValorUnitario.clear();
                txtPeso.clear();
                dateFechaEnvasado.setValue(null);
                cbPais.setValue(null);

                actualizarTablaEnvasados();
            } catch (ElementoNoEncontradoException e) {
                // Manejar la excepción si el guía no se encuentra
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            } catch (RutaInvalidaException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Si no se selecciona ningún guía, muestra un mensaje de error
            throw new AtributoVacioException("Selecciona un producto a eliminar.");
        }
    }
    @FXML
    void initialize() throws RutaInvalidaException {
        tablaProductosEnvasados();
        actualizarTablaEnvasados();
        cbPais.setValue(null);
        cbPais.setItems(FXCollections.observableArrayList(Pais.values()));
        cbPais.setValue(Pais.COLOMBIA);
        cbPais.setValue(Pais.CHILE);
        cbPais.setValue(Pais.ARGENTINA);
        cbPais.setValue(Pais.ECUADOR);
        cbPais.setValue(Pais.PERU);
        cbPais.setValue(null);


    }

}
