package ventasR.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class InicioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnProductos;

    @FXML
    private AnchorPane ventana;

    @FXML
    void clientesEvent(ActionEvent event) throws IOException{
            new ViewController(ventana, "/views/registroCliente.fxml");

    }

    @FXML
    void productosEfvent(ActionEvent event) throws IOException{
            new ViewController(ventana, "/views/registroVentas.fxml");

    }

    @FXML
    void initialize() {

    }

}
