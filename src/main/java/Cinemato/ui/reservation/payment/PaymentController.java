package main.java.Cinemato.ui.reservation.payment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;

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
    private Text movieTitle;

    @FXML
    private Text date;

    @FXML
    private Text hour;

    @FXML
    private Text places;


    public void setData(Movie m, Screening s, ArrayList<Seat> seats) {
        this.movie = m;
        this.screening = s;
        this.seatsSelected = seats;
        movieTitle.setText(movie.getTitle());
        date.setText(screening.getDate());
        hour.setText(screening.getStartTime());

        String selectedPlaces="";
        for (Seat place : seatsSelected) {
            selectedPlaces += place.getRow() + place.getNumber() + " | ";
        }
        places.setText(selectedPlaces);

    }
}



