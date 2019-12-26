package main.java.Cinemato.ui.reservation.seatSelector.seat;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SeatController {

    private Seat seat;
    private boolean isSelected = false;


    private static SeatController instance;

    public SeatController() {
        instance = this;
    }

    public static SeatController getInstance() {
        return instance;
    }

    @FXML
    private ImageView seatImage;

    public void setSeat(Seat s) {
        this.seat = s;
    }



    public void chooseSeat(MouseEvent e) {
        System.out.println("Selectec seat: " + this.seat.getId());
    }

}
