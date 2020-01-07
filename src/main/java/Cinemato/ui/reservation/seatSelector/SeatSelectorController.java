package main.java.Cinemato.ui.reservation.seatSelector;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.reservation.payment.PaymentController;
import main.java.Cinemato.ui.reservation.seatSelector.seat.SeatController;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatSelectorController implements Initializable {

    public ArrayList<Seat> getSeatsSelected() {
        return SeatsSelected;
    }

    public void addSeatsSelected(Seat seat) {
        this.SeatsSelected.add(seat);
    }

    public void removeSeatsSelected(Seat seat) {
        this.SeatsSelected.remove(seat);
    }

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
    private ArrayList<Seat> SeatsSelected = new ArrayList<>();
    private Screening ScreeningSelected;

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
        PaymentController.getInstance().setData(movie, ScreeningSelected, SeatsSelected);
    }

    @FXML
    private void handleGoBackAction(MouseEvent event) {
        WrapperController.getInstance().backToRepertoire(event);
    }

    @FXML
    private void selectScreening(ActionEvent event) {

        ArrayList<String> query = new ArrayList<>();
        query.add(Integer.toString(choiceData.getValue().getId()));

        Message getSeatsReserved = null;
        SeatsSelected.clear();

        for(Seat seat : this.Seats){
            seat.setAvailable(true);
        }

        try {
            getSeatsReserved = Client.sendMessage(new Message("getSeatsReserved", query));
            ArrayList<String> body = getSeatsReserved.getBody();

            for (String seat_id : body) {
                System.out.println("zarezerwowane: ");
                System.out.println(seat_id);
                System.out.println("do zarezerowania: ");
                System.out.println(this.Seats.get(Integer.parseInt(seat_id) - 1).getId());
                    this.Seats.get(Integer.parseInt(seat_id) - 1).setAvailable(false);
            }

            renderSeats();
            this.ScreeningSelected = choiceData.getValue();
            System.out.println(choiceData.getValue().getId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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


    public void renderSeats() throws IOException {

        seatsPane.clear();
        seatsRow.getChildren().clear();

        for (Seat seat : this.Seats) {
            AnchorPane x;
            x = FXMLLoader.load(getClass().getResource("/main/java/Cinemato/ui/reservation/seatSelector/seat/seat.fxml"));
            this.seatsPane.add(x);
            SeatController.getInstance().setSeat(seat);
            if(!seat.isAvailable()) SeatController.getInstance().setDisabled(true);
        }
        seatsRow.getChildren().addAll(seatsPane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Message getSeatsListResponse = null;
        try {
            getSeatsListResponse = Client.sendMessage(new Message("getSeatsList", new ArrayList<>()));
            ArrayList<String> body = getSeatsListResponse.getBody();

            for (String seat : body) {
                System.out.println(seat);

                String[] splitedSeat = seat.split("&");
                Seat importedSeat = new Seat(Integer.parseInt(splitedSeat[0]),splitedSeat[1],Integer.parseInt(splitedSeat[2]));
                importedSeat.setAvailable(false);
                this.Seats.add(importedSeat);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            renderSeats();
        } catch (IOException e) {
            e.printStackTrace();
        }

        choiceData.setOnAction(this::selectScreening);

        choiceHour.getItems().add("Mialo dzialac, braklo czasu!");
        choiceHour.getItems().add("Mialo dzialac, braklo czasu!");
        choiceHour.getItems().add("Mialo dzialac, braklo czasu!");


    }
}
