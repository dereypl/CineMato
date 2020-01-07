package main.java.Cinemato.ui.reservation.payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.reservation.seatSelector.SeatSelectorController;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController {

    private static PaymentController instance;

    public PaymentController() {
        instance = this;
    }

    public static PaymentController getInstance() {
        return instance;
    }


    private Movie movie;
    private Screening screening;
    private ArrayList<Seat> seatsSelected;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField cardNumber;

    @FXML
    private JFXTextField cvv;

    @FXML
    private Text movieTitle;

    @FXML
    private Text date;

    @FXML
    private Text hour;

    @FXML
    private Text places;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXTextField month;

    @FXML
    private JFXTextField year;


    public void setData(Movie m, Screening s, ArrayList<Seat> seats) {
        this.movie = m;
        this.screening = s;
        this.seatsSelected = seats;
        movieTitle.setText(movie.getTitle());
        date.setText(screening.getDate());
        hour.setText(screening.getStartTime());

        String selectedPlaces = "";
        for (Seat place : seatsSelected) {
            selectedPlaces += " | " + place.getRow() + place.getNumber();
        }
        places.setText(selectedPlaces);

    }


    @FXML
    public void makeReservation(ActionEvent event) {

        System.out.println("RESERVATION TRIGGERED");
        ArrayList<String> query = new ArrayList<>();
        query.add(movie.getId());
        query.add(Integer.toString(screening.getId()));
        query.add(Integer.toString(seatsSelected.get(0).getId()));
        query.add(firstName.getText());
        query.add(lastName.getText());
        query.add(email.getText());
        query.add(cardNumber.getText());
        query.add(cvv.getText());
        query.add(month.getText());
        query.add(year.getText());

        Message response = null;

        try {
            response = Client.sendMessage(new Message("reservationRequest", query));
            ArrayList<String> body = response.getBody();

            for (String info : body) {
                System.out.println(info);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}



