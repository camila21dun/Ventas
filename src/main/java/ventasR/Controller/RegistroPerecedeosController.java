package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ventasR.model.Perecedero;

public class RegistroPerecedeosController {

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

    @FXML
    void atrasEvent(ActionEvent event)throws IOException {
        new ViewController(ventana, "/views/registroCliente.fxml");
    }



    @FXML
    void actualizarPerecederosEvent(ActionEvent event) {

    }



    @FXML
    void eliminarPerecederosEvent(ActionEvent event) {

    }

    @FXML
    void registrarPerecederosEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
