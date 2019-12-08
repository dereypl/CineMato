package main.java.Cinemato.ui.reservation.seatSelector;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Cinemato.ui.MainController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeatSelectorController implements Initializable {


    private static SeatSelectorController instance;
    public SeatSelectorController() {
        instance = this;
    }
    public static SeatSelectorController getInstance() {
        return instance;
    }

    @FXML
    private ImageView CloseButton;

    @FXML
    private ChoiceBox<String> choiceData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceData.getItems().add("Choice 1");
        choiceData.getItems().add("Choice 2");
        choiceData.getItems().add("Choice 3");
    }

}
