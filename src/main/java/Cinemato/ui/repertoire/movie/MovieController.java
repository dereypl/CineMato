package main.java.Cinemato.ui.repertoire.movie;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.ui.MainController;
import main.java.Cinemato.ui.reservation.seatSelector.SeatSelectorController;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieController {

    private Movie movie;

    @FXML
    private Text movieName;

    @FXML
    private Text movieDescription;

    @FXML
    private Text movieRating;

    @FXML
    private Text movieYear;

    @FXML
    private Text movieDuration;

    @FXML
    private Text movieDirector;

    @FXML
    private Text movieGenere;

    @FXML
    private ImageView Poster;


    private static MovieController instance;
    public MovieController() {
        instance = this;
    }
    public static MovieController getInstance() {
        return instance;
    }

    @FXML
    void chooseMovie(ActionEvent event) {
        WrapperController.getInstance().changeContentToSeatSelector(event);
        SeatSelectorController.getInstance().setMovie(movie);
    }

    public void setMovie(Movie m) {
        this.movie = m;
        movieName.setText(movie.getTitle());
        movieDescription.setText(movie.getDescription());
        movieDirector.setText(movie.getDirector());
        movieDuration.setText(movie.getDuration_min());
        movieGenere.setText(movie.getGenre());
        movieRating.setText(movie.getRating());
        movieYear.setText(movie.getYear());
        Image image = new Image(new File(m.getPosterLink()).toURI().toString());
        Poster.setImage(image);
    }
}
