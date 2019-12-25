package main.java.Cinemato.ui.repertoire;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.models.Movie;
import main.java.Cinemato.ui.MainController;
import main.java.Cinemato.ui.repertoire.movie.MovieController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RepertoireController implements Initializable {


    private static RepertoireController instance;

    public RepertoireController() {
        instance = this;
    }

    public static RepertoireController getInstance() {
        return instance;
    }

    @FXML
    private ImageView CloseButton;

    @FXML
    private VBox movieContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Movie> movies = new ArrayList<>();
        Message response = null;


        try {
            response = Client.sendMessage(new Message("getMovieList", new ArrayList<>()));


            ArrayList<String> body = response.getBody();

            for (String movie : body) {
                System.out.println(movie);

                String[] splitedMovie = movie.split("&");

                Movie importedMovie = new Movie(Integer.parseInt(splitedMovie[0]), splitedMovie[1], splitedMovie[2], splitedMovie[3], splitedMovie[4], splitedMovie[5], splitedMovie[6], splitedMovie[7]);
                movies.add(importedMovie);
            }

            for (int i = 0; i < movies.size(); i++) {
                AnchorPane moviePane;

                moviePane = FXMLLoader.load(getClass().getResource("/main/java/Cinemato/ui/repertoire/movie/movie.fxml"));
                MovieController.getInstance().setMovie(movies.get(i));
                movieContainer.getChildren().add(moviePane);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
