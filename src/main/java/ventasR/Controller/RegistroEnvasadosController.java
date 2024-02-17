package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ventasR.model.Envasado;
import ventasR.model.Pais;

public class RegistroEnvasadosController {

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
    private ComboBox<?> cbPais;

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
    private AnchorPane ventana;

    @FXML
    void atrasEvent(ActionEvent event)throws IOException {
        new ViewController(ventana, "/views/registroCliente.fxml");
    }



    @FXML
    void actualizarEnvasadoEvent(ActionEvent event) {

    }


    @FXML
    void eliminarEnvasadoEvent(ActionEvent event) {

    }

    @FXML
    void registrarEnvasadoEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
