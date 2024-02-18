package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RegistroVentasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEnvasado;

    @FXML
    private Button btnPerecedero;

    @FXML
    private Button btnRefigerado;

    @FXML
    private AnchorPane ventana;

    @FXML
    void atrasEvent(ActionEvent event)  throws IOException {
        new ViewController(ventana, "/views/inicio.fxml");

    }

    @FXML
    void envasadoEvent(ActionEvent event)throws IOException {
            new ViewController(ventana, "/views/registroEnvasados.fxml");

        }

    @FXML
    void perecederoEvent(ActionEvent event)  throws IOException {
        new ViewController(ventana, "/views/registroPerecederos.fxml");

    }

    @FXML
    void refigeradoEvent(ActionEvent event) throws IOException {
        new ViewController(ventana, "/views/registroRefigerados.fxml");

    }

    @FXML
    void initialize() {


    }

}
