package main.java.Cinemato.ui.reservation.payment;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {


    private static PaymentController instance;

    public PaymentController() {
        instance = this;
    }

    public static PaymentController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}


