package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ventasR.model.Refigerado;

public class RegistroRefigeradosController {

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
    @FXML
    void atrasEvent(ActionEvent event)throws IOException {
        new ViewController(ventana, "/views/registroCliente.fxml");
    }


    @FXML
    void actualizarRefigeradoEvent(ActionEvent event) {

    }



    @FXML
    void eliminarRefigeradoEvent(ActionEvent event) {

    }

    @FXML
    void registrarRefigeradoEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
