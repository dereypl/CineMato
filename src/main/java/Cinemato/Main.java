package main.java.Cinemato;

import javafx.application.Application;
import main.java.Cinemato.connection.Client;
import main.java.Cinemato.connection.Message;
import main.java.Cinemato.ui.wrapper.WrapperController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        Communicate();
        Client.startConnection("127.0.0.1", 8808);

        Application.launch(WrapperController.class);

    }
}
//
//    private static void Communicate() throws IOException, ClassNotFoundException {
//        Client client1 = new Client();
//        client1.startConnection("127.0.0.1", 8808);
////        String msg1 = client1.sendMessage("hello");
////        String msg2 = client1.sendMessage("world");
////        String terminate = client1.sendMessage(".");
//
//
//        ArrayList<String> requests = new ArrayList<>();
//        requests.add("request1");
//        requests.add("request2");
//        requests.add("request2");
//
//        Message msg1 = client1.sendMessage(new Message("getMovieList", new ArrayList<>() ));
//        System.out.println(msg1.getType());
//        ArrayList<String> body = msg1.getBody();
//        for (String movie : body) {
//            System.out.println(movie);
//        }
//
//
//
//        Message terminate = client1.sendMessage(new Message("terminate",requests ));
//        System.out.println(terminate.getType());
//
//        client1.stopConnection();
//    }
//
//
//}

