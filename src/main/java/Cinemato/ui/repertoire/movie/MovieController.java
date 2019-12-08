package main.java.Cinemato.ui.repertoire.movie;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Cinemato.ui.MainController;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieController {

    private static MovieController instance;
    public MovieController() {
        instance = this;
    }
    public static MovieController getInstance() {
        return instance;
    }

    @FXML
    void chooseMovie(ActionEvent event) {
        WrapperController.getInstance().changeContentToSeatSelector(event);
    }

}
