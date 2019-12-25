package main.java.Cinemato.ui.reservation.payment;

import javafx.fxml.Initializable;

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


