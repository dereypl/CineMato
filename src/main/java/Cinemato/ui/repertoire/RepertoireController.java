package main.java.Cinemato.ui.repertoire;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Cinemato.ui.MainController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RepertoireController implements Initializable {


    private static RepertoireController instance;
    public RepertoireController() {
        instance = this;
    }
    public static RepertoireController getInstance() {
        return instance;
    }

    @FXML
    private ImageView CloseButton;

    @FXML
    private VBox movieContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 5; i++) {
            AnchorPane moviePane;
            try {
                moviePane = FXMLLoader.load(getClass().getResource("/main/java/Cinemato/ui/repertoire/movie/movie.fxml"));
                movieContainer.getChildren().add(moviePane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
