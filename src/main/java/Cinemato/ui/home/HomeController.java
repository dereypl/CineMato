package main.java.Cinemato.ui.home;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import main.java.Cinemato.ui.MainController;
import javafx.stage.Stage;
import main.java.Cinemato.ui.wrapper.WrapperController;


import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    @FXML
    private JFXButton goToRepertoireButton;

    private static HomeController instance;

    public HomeController() {
        instance = this;
    }

    public static HomeController getInstance() {
        return instance;
    }


    @FXML
    private void handleGoToRepertoireAction(ActionEvent event) {
        WrapperController.getInstance().changeContentToRepertoire();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
