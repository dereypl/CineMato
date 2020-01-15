package main.java.Cinemato.ui.reservation.payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.models.Screening;
import main.java.Cinemato.models.Seat;
import main.java.Cinemato.ui.reservation.seatSelector.SeatSelectorController;

import javafx.event.ActionEvent;
import main.java.Cinemato.ui.reservation.status.StatusController;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private ImageView Poster;


    public void setData(Movie m, Screening s, ArrayList<Seat> seats) {
        this.movie = m;
        this.screening = s;
        this.seatsSelected = seats;
        movieTitle.setText(movie.getTitle());
        date.setText(screening.getDate());
        hour.setText(screening.getStartTime());
        Image image = new Image(new File(m.getPosterLink()).toURI().toString());
        Poster.setImage(image);

        String selectedPlaces = "";
        for (Seat place : seatsSelected) {
            selectedPlaces += " | " + place.getRow() + place.getNumber();
        }
        places.setText(selectedPlaces);

    }

    @FXML
    private void handleGoBackAction(MouseEvent event) {
        WrapperController.getInstance().changeContentToSeatSelector();
        SeatSelectorController.getInstance().setMovie(movie);
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

        saveButton.setText("Przetwarzanie rezerwacji...");

        Task<ArrayList<String>> MakeReservationTask = new Task<ArrayList<String>>() {
            @Override
            public ArrayList<String> call() throws IOException, ClassNotFoundException {

                Message response = null;
                response = Client.sendMessage(new Message("reservationRequest", query));
                ArrayList<String> body = response.getBody();

                for (String info : body) {
                    System.out.println(info);
                }

                return body;
            }
        };

        MakeReservationTask.setOnSucceeded(e -> {
            ArrayList<String> reposeBody = MakeReservationTask.getValue();

            WrapperController.getInstance().changeContentToReservationStatus();
            StatusController.getInstance().setReservationStatus(reposeBody.get(0));
            StatusController.getInstance().setData(movie, screening, seatsSelected);

        });

        new Thread(MakeReservationTask).start();


        //TODO: Change FixedThreadPool size = seats reserved array size
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        for (int i = 0; i < 1; i++) {
//            Runnable parallelMakeReservationTask = () -> {
//                try {
//                    Message response = null;
//                    response = Client.sendMessage(new Message("reservationRequest", query));
//                    ArrayList<String> body = response.getBody();
//
//                    for (String info : body) {
//                        System.out.println(info);
//                    }
//
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            };
//            executorService.submit(parallelMakeReservationTask);
//            executorService.shutdown();
//
//        }
//    }
    }
}



