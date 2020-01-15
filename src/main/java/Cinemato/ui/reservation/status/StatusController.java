package main.java.Cinemato.ui.reservation.status;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.reservation.seatSelector.SeatSelectorController;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.File;
import java.util.ArrayList;

public class StatusController {

    private Movie movie;
    private Screening screening;
    private ArrayList<Seat> seatsSelected;

    @FXML
    private Text StatusInfo;

    @FXML
    private ImageView Poster;

    @FXML
    private Text movieTitle;

    @FXML
    private Text date;

    @FXML
    private Text hour;

    @FXML
    private Text places;

    @FXML
    private JFXButton actionButton;

    @FXML
    private Text StatusDescription;

    @FXML
    private ImageView StatusImage;


    private static StatusController instance;

    public StatusController() {
        instance = this;
    }

    public static StatusController getInstance() {
        return instance;
    }

    public void setData(Movie m, Screening s, ArrayList<Seat> seats) {
        this.movie = m;
        this.screening = s;
        movieTitle.setText(movie.getTitle());
        date.setText(screening.getDate());
        hour.setText(screening.getStartTime());
        Image image = new Image(new File(m.getPosterLink()).toURI().toString());
        Poster.setImage(image);

        String selectedPlaces = "";
        for (Seat place : seats) {
            selectedPlaces += " | " + place.getRow() + place.getNumber();
        }
        places.setText(selectedPlaces);
    }


    public void setReservationStatus(String status) {

        if (status.equals("reserved")) {
            StatusInfo.setText("REZERWACJA PRZEBIEGŁA POMYŚLNIE");
            StatusDescription.setText("Potwierdzenie rezerwacji zostranie wysłane na wskazany adres email.");
            Image statusImage = new Image("main/java/Cinemato/resources/assets/confirmed.png");
            actionButton.setText("Wróć do repertuaru");
            StatusImage.setImage(statusImage);
        } else {
            StatusInfo.setText("REZERWACJA ODRZUCONA");
            StatusDescription.setText("Podane miejsca są już zajętę. Kliknij przycisk poniżej, aby zmienić miejsca.");
            Image statusImage = new Image("main/java/Cinemato/resources/assets/error.png");
            actionButton.setText("Zmień miejsca");
            StatusImage.setImage(statusImage);
        }
    }


    @FXML
    private void handleGoBackAction() {
        if(actionButton.getText().equals("Zmień miejsca")){
            WrapperController.getInstance().changeContentToSeatSelector();
            SeatSelectorController.getInstance().setMovie(movie);
        }
        else
        {
            WrapperController.getInstance().changeContentToRepertoire();
        }

    }
}



