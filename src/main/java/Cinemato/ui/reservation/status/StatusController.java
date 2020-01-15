package main.java.Cinemato.ui.reservation.status;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;

public class StatusController {

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
    private JFXButton saveButton;

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


    public void setReservationStatus(String status) {

        if (status.equals("reserved")) {
            StatusInfo.setText("REZERWACJA PRZEBIEGŁA POMYŚLNIE");
            StatusDescription.setText("Potwierdzenie rezerwacji zostranie wysłane na wskazany adres email.");
            Image statusImage = new Image("main/java/Cinemato/resources/assets/confirmed.png");
            StatusImage.setImage(statusImage);
        } else {
            StatusInfo.setText("REZERWACJA ODRZUCONA");
            StatusDescription.setText("Podane miejsca są już zajętę. Kliknij przycisk poniżej, aby zmienić miejsca.");
            Image statusImage = new Image("main/java/Cinemato/resources/assets/error.png");
            StatusImage.setImage(statusImage);
        }


    }
}



