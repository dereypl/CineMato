package main.java.Cinemato.ui.reservation.seatSelector;


import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.MainController;
import main.java.Cinemato.ui.repertoire.movie.MovieController;
import main.java.Cinemato.ui.reservation.seatSelector.seat.SeatController;
import main.java.Cinemato.ui.wrapper.WrapperController;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatSelectorController implements Initializable {

    class MyClassConverter extends StringConverter<Screening> {

        public Screening fromString(String string) {
            return new Screening(99999,9999,string,string);
        }

        public String toString(Screening myClassinstance) {
            // convert a myClass instance to the text displayed in the choice box
            return myClassinstance.getDate() + ": "+myClassinstance.getStartTime();
        }
    }


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

    private ArrayList<Screening> Screenings = new ArrayList<>();
    private ArrayList<Seat> Seats = new ArrayList<>();

    @FXML
    private ChoiceBox<Screening> choiceData;

    @FXML
    private ChoiceBox<String> choiceHour;

    @FXML
    private JFXButton seat;

    @FXML
    private JFXButton seat2;

    @FXML
    private HBox seatsRow;

    ArrayList<AnchorPane> seatsPane = new ArrayList<>();


    @FXML
    private void handleGoToPaymentAction(ActionEvent event) {
        WrapperController.getInstance().changeContentToPayment(event);
    }

    @FXML
    private void handleGoBackAction(MouseEvent event) {
        WrapperController.getInstance().backToRepertoire(event);
    }

    @FXML
    private void ShowSelectedDate(ActionEvent event) {
        System.out.println(choiceData.getValue().getId());
    }

    public void setMovie(Movie m) {
        this.movie = m;
        movieTitle.setText(movie.getTitle());

        ArrayList<String> query = new ArrayList<>();
        query.add(movie.getId());

        Message response = null;

        try {
            response = Client.sendMessage(new Message("getMovieScreenings", query));
            ArrayList<String> body = response.getBody();

            for (String screening : body) {
                System.out.println(screening);

                String[] splitedScreening = screening.split("&");

                Screening importedScreening = new Screening(Integer.parseInt(splitedScreening[0]),Integer.parseInt(splitedScreening[1]),splitedScreening[2],splitedScreening[3]);
                this.Screenings.add(importedScreening);
                choiceData.setConverter(new MyClassConverter());
                choiceData.getItems().add(importedScreening);

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Message getSeatsListResponse = null;
        AnchorPane x;
        try {
            getSeatsListResponse = Client.sendMessage(new Message("getSeatsList", new ArrayList<>()));
            ArrayList<String> body = getSeatsListResponse.getBody();

            for (String seat : body) {
                System.out.println(seat);

                String[] splitedSeat = seat.split("&");
                Seat importedSeat = new Seat(Integer.parseInt(splitedSeat[0]),splitedSeat[1],Integer.parseInt(splitedSeat[2]));
                this.Seats.add(importedSeat);

                x = FXMLLoader.load(getClass().getResource("/main/java/Cinemato/ui/reservation/seatSelector/seat/seat.fxml"));
                this.seatsPane.add(x);
                SeatController.getInstance().setSeat(importedSeat);


            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        seatsRow.getChildren().addAll(seatsPane);

        choiceData.setOnAction(this::ShowSelectedDate);

        choiceHour.getItems().add("Hour 1");
        choiceHour.getItems().add("Hour 2");
        choiceHour.getItems().add("Hour 3");


    }
}
