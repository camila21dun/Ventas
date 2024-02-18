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
import ventasR.model.ClienteNatural;
import ventasR.model.Empresa;
import ventasR.model.Perecedero;

public class RegistroPerecedeosController {

    Perecedero perecederoSeleccionado;

    ObservableList<Perecedero> listaProductoPerecedero = FXCollections.observableArrayList(Empresa.getInstance().getListaPerecederos());


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarPerecederos;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminarPerecederos;

    @FXML
    private Button btnRegistrarPerecederos;

    @FXML
    private TableColumn<Perecedero, Integer> columCantidad;

    @FXML
    private TableColumn<Perecedero, String> columCodigo;

    @FXML
    private TableColumn<Perecedero, LocalDate> columFechaVenciminento;

    @FXML
    private TableColumn<Perecedero, Double> columValor;

    @FXML
    private DatePicker dateFechaVencimiento;

    @FXML
    private TableView<Perecedero> tabPerecederos;

    @FXML
    private TextField txtCantExistente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtValorUnitario;

    @FXML
    private AnchorPane ventana;

    private final Empresa empresa = Empresa.getInstance();

    @FXML
    void atrasEvent(ActionEvent event)throws IOException {
        new ViewController(ventana, "/views/registroCliente.fxml");
    }

    @FXML
    void registrarPerecederosEvent(ActionEvent event) {
        registrarPerecederosAction();

    }

    private void registrarPerecederosAction() {

        try {
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            int cantidadExistente = Integer.parseInt(txtCantExistente.getText());


            empresa.registrarProductoPerecedero(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    valorUnitario,
                    cantidadExistente,
                    dateFechaVencimiento.getValue()
            );
            listaProductoPerecedero = FXCollections.observableArrayList(empresa.getListaPerecederos());

            // Asignar la nueva lista a la tabla
            tabPerecederos.setItems(listaProductoPerecedero);

            this.tabPerecederos.setItems(listaProductoPerecedero);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Se ha registrado correctamente el producto con el codigo  " + txtCodigo.getText());
            alert.show();
            limpiarTexto();


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
        dateFechaVencimiento.setValue(null);
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
    void actualizarPerecederosEvent(ActionEvent event) throws RutaInvalidaException {
        actualizarPerecederosAction();

    }

    private void actualizarPerecederosAction()throws RutaInvalidaException {

            try {
                String nombre = txtNombre.getText();
                String codigo = txtCodigo.getText();
                String descripcion = txtDescripcion.getText();
                double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
                int cantidadExistente = Integer.parseInt(txtCantExistente.getText());
                LocalDate fechaVencimiento= dateFechaVencimiento.getValue();

                empresa.actualizarProductoPerecedero(codigo,nombre,descripcion,valorUnitario,cantidadExistente,fechaVencimiento);

                // Limpia los campos después del registro
                txtNombre.clear();
                txtCodigo.clear();
                txtDescripcion.clear();
                txtValorUnitario.clear();
                txtCantExistente.clear();
                dateFechaVencimiento.setValue(null);

                this.tabPerecederos.setItems(listaProductoPerecedero);
                actualizarTablaPerecederos();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Se ha actualizado correctamente el producto con el codigo  " + txtCodigo.getText());
                alert.show();
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText(null);
                alert.show();
            }
    }

    private void actualizarTablaPerecederos()throws RutaInvalidaException {
            listaProductoPerecedero = FXCollections.observableArrayList(empresa.getListaPerecederos());
            tabPerecederos.getItems().clear();
            tabPerecederos.setItems(listaProductoPerecedero);
        tablaProductosPerecederos();
            tabPerecederos.refresh();
        }
        void tablaProductosPerecederos() throws RutaInvalidaException {
            tabPerecederos.setItems(listaProductoPerecedero);

            // Personalizar las celdas de las columnas
            columCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
            columCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCantExistencia()).asObject());
            columValor.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorUnitario()).asObject());
            columFechaVenciminento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaVencimiento()));

            // Asignar el manejador de selección
            tabPerecederos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                perecederoSeleccionado = newSelection;
                mostrarInformacion();
            });

            // Actualizar la tabla
            tabPerecederos.refresh();
        }

    private void mostrarInformacion() {
        if(perecederoSeleccionado!=null){
            txtCodigo.setText(perecederoSeleccionado.getCodigo());
            txtNombre.setText(perecederoSeleccionado.getNombre());
            txtDescripcion.setText(perecederoSeleccionado.getDescripcion());
            txtCantExistente.setText(String.valueOf(perecederoSeleccionado.getCantExistencia()));
            txtValorUnitario.setText(String.valueOf(perecederoSeleccionado.getValorUnitario()));
            dateFechaVencimiento.setValue(perecederoSeleccionado.getFechaVencimiento());
        }
    }



    @FXML
    void eliminarPerecederosEvent(ActionEvent event) throws AtributoVacioException {
        eliminarPerecederosAction();

    }

    private void eliminarPerecederosAction() throws AtributoVacioException {
        Perecedero perecederoSeleccionado = tabPerecederos.getSelectionModel().getSelectedItem();

        if (perecederoSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                Empresa.getInstance().eliminarPerecedero(perecederoSeleccionado.getCodigo());
                txtNombre.clear();
                txtCodigo.clear();
                txtCantExistente.clear();
                txtDescripcion.clear();
                txtValorUnitario.clear();
                dateFechaVencimiento.setValue(null);
                actualizarTablaPerecederos();
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
        tablaProductosPerecederos();
        actualizarTablaPerecederos();

    }

}
