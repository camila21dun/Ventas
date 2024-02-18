package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import ventasR.model.Perecedero;
import ventasR.model.Refigerado;

public class RegistroRefigeradosController {

    Refigerado refigeradoSeleccionado;

    ObservableList<Refigerado> listaProductoRefigerado = FXCollections.observableArrayList(Empresa.getInstance().getListaRefigerados());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarRefigerado;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminarRefigerado;

    @FXML
    private Button btnRegistrarRefigerado;

    @FXML
    private TableColumn<Refigerado, Integer> columCantidad;

    @FXML
    private TableColumn<Refigerado, String> columCodigo;

    @FXML
    private TableColumn<Refigerado, Integer> columTemperatura;

    @FXML
    private TableColumn<Refigerado, Double> columValor;

    @FXML
    private TableView<Refigerado> tabRefigerados;

    @FXML
    private TextField txtCantExistente;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCodigoAprobacion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTemperatura;

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
    void registrarRefigeradoEvent(ActionEvent event) {
        registrarRefigeradoAction();

    }

    private void registrarRefigeradoAction() {
        try {
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            int cantidadExistente = Integer.parseInt(txtCantExistente.getText());
            int temperatura = Integer.parseInt(txtTemperatura.getText());


            empresa.registrarProductoRefigerado(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    valorUnitario,
                    cantidadExistente,
                    txtCodigoAprobacion.getText(),
                    temperatura

            );
            listaProductoRefigerado = FXCollections.observableArrayList(empresa.getListaRefigerados());

            // Asignar la nueva lista a la tabla
            tabRefigerados.setItems(listaProductoRefigerado);

            this.tabRefigerados.setItems(listaProductoRefigerado);

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
        txtTemperatura.clear();
        txtCodigoAprobacion.clear();
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
    void actualizarRefigeradoEvent(ActionEvent event) {
        actualizarRefigeradoAction();
    }

    private void actualizarRefigeradoAction() {
        try {
            String nombre = txtNombre.getText();
            String codigo = txtCodigo.getText();
            String descripcion = txtDescripcion.getText();
            String codigoAprovacion = txtCodigoAprobacion.getText();
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());
            int cantidadExistente = Integer.parseInt(txtCantExistente.getText());
            int temperatura = Integer.parseInt(txtTemperatura.getText());

            empresa.actualizarProductoRefigerado(codigo,nombre,descripcion,valorUnitario,cantidadExistente,codigoAprovacion,temperatura);

            // Limpia los campos después del registro
            txtNombre.clear();
            txtCodigo.clear();
            txtDescripcion.clear();
            txtValorUnitario.clear();
            txtCantExistente.clear();
            txtTemperatura.clear();
            txtCodigoAprobacion.clear();

            this.tabRefigerados.setItems(listaProductoRefigerado);
            actualizarTablaRefigerados();
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
    private void actualizarTablaRefigerados()throws RutaInvalidaException {
        listaProductoRefigerado = FXCollections.observableArrayList(empresa.getListaRefigerados());
        tablaProductosRefigedados(); // Llama a esta función primero
        tabRefigerados.getItems().clear();
        tabRefigerados.setItems(listaProductoRefigerado);
        tabRefigerados.refresh();
    }
    void tablaProductosRefigedados() throws RutaInvalidaException {
        tabRefigerados.setItems(listaProductoRefigerado);

        // Personalizar las celdas de las columnas
        columCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCantExistencia()).asObject());
        columValor.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorUnitario()).asObject());
        columTemperatura.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTemperatura()).asObject());
        // Asignar el manejador de selección
        tabRefigerados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            refigeradoSeleccionado = newSelection;
            mostrarInformacion();
        });

        // Actualizar la tabla
        tabRefigerados.refresh();
    }

    private void mostrarInformacion() {
        if(refigeradoSeleccionado!=null){
            txtNombre.setText(refigeradoSeleccionado.getNombre());
            txtDescripcion.setText(refigeradoSeleccionado.getDescripcion());
            txtCantExistente.setText(String.valueOf(refigeradoSeleccionado.getCantExistencia()));
            txtValorUnitario.setText(String.valueOf(refigeradoSeleccionado.getValorUnitario()));
            txtTemperatura.setText(String.valueOf(refigeradoSeleccionado.getCantExistencia()));
            txtCodigoAprobacion.setText(refigeradoSeleccionado.getCodigoAprovacion());
            txtCodigo.setText(refigeradoSeleccionado.getCodigo());

        }
    }
    @FXML
    void eliminarRefigeradoEvent(ActionEvent event) throws AtributoVacioException {
        eliminarRefigeradoAction();
    }

    private void eliminarRefigeradoAction() throws AtributoVacioException {
        Refigerado refigeradoSeleccionado = tabRefigerados.getSelectionModel().getSelectedItem();

        if (refigeradoSeleccionado != null) {
            try {
                // Llamar al método de eliminación en la clase principal
                Empresa.getInstance().eliminarPerecedero(refigeradoSeleccionado.getCodigo());
                txtNombre.clear();
                txtCodigo.clear();
                txtCantExistente.clear();
                txtDescripcion.clear();
                txtValorUnitario.clear();
                txtTemperatura.clear();
                txtCodigoAprobacion.clear();
                actualizarTablaRefigerados();
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
        tablaProductosRefigedados();
        actualizarTablaRefigerados();
    }

}
