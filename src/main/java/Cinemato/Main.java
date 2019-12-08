package main.java.Cinemato;

import javafx.application.Application;
import main.java.Cinemato.ui.home.HomeController;
import main.java.Cinemato.ui.wrapper.WrapperController;


//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("home/repertoire.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}



public class Main {

    public static void main(String[] args) {
        Application.launch(WrapperController.class);

    }

}

