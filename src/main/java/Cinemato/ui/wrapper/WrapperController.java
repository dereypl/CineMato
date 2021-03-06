package main.java.Cinemato.ui.wrapper;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.Cinemato.ui.MainController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WrapperController extends MainController {


    @FXML
    private ImageView CloseButton;

    @FXML
    AnchorPane contentPane;

    @FXML
    AnchorPane headerPane;

    private static WrapperController instance;

    public WrapperController() {
        instance = this;
    }

    public static WrapperController getInstance() {
        return instance;
    }

    private void closeStage() {
        ((Stage) CloseButton.getScene().getWindow()).close();
    }

    @FXML
    void handleCloseButtonAction(MouseEvent event) {
        System.exit(0);
    }


    private void setNode(Node node){
        contentPane.getChildren().clear();
        contentPane.getChildren().add((Node) node);
    }
    public void setContentPage(AnchorPane Content, String loc )
    {
        try {
            Content = FXMLLoader.load(getClass().getResource(loc));
            setNode(Content);
        } catch(IOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadPage("/main/java/Cinemato/ui/wrapper/wrapper.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setContentPage(contentPane, "/main/java/Cinemato/ui/home/home.fxml");
    }

    @FXML
    public void changeContentToRepertoire() {
        try {
            AnchorPane temp;
            temp = FXMLLoader.load(getClass().getResource("/main/java/Cinemato/ui/header/header.fxml"));
            headerPane.getChildren().add((Node) temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentPage(contentPane, "/main/java/Cinemato/ui/repertoire/repertoire.fxml");
    }

    @FXML
    public void changeContentToSeatSelector() {
        setContentPage(contentPane, "/main/java/Cinemato/ui/reservation/seatSelector/seatSelector.fxml");
    }

    @FXML
    public void changeContentToPayment(ActionEvent event) {
        setContentPage(contentPane, "/main/java/Cinemato/ui/reservation/payment/payment.fxml");
    }

    @FXML
    public void changeContentToReservationStatus() {
        setContentPage(contentPane, "/main/java/Cinemato/ui/reservation/status/status.fxml");
    }

    @FXML
    public void backToRepertoire(MouseEvent event) {
        setContentPage(contentPane, "/main/java/Cinemato/ui/repertoire/repertoire.fxml");
    }

}
