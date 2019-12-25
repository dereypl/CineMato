package main.java.Cinemato.ui.reservation.seatSelector;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.ui.MainController;
import main.java.Cinemato.ui.wrapper.WrapperController;

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

    private Movie movie;

    @FXML
    private JFXButton goToPaymentButton;

    @FXML
    private Text movieTitle;


    @FXML
    private ChoiceBox<String> choiceData;

    @FXML
    private ChoiceBox<String> choiceHour;

    @FXML
    private void handleGoToPaymentAction(ActionEvent event) {
        WrapperController.getInstance().changeContentToPayment(event);
    }

    @FXML
    private void handleGoBackAction(MouseEvent event) {
        WrapperController.getInstance().backToRepertoire(event);
    }

    public void setMovie(Movie m) {
        this.movie = m;
        movieTitle.setText(movie.getTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceData.getItems().add("Choice 1");
        choiceData.getItems().add("Choice 2");
        choiceData.getItems().add("Choice 3");

        choiceHour.getItems().add("Hour 1");
        choiceHour.getItems().add("Hour 2");
        choiceHour.getItems().add("Hour 3");
    }

}
