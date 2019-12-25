package main.java.Cinemato.connection;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static Client instance;
    public Client() {
        instance = this;
    }
    public static Client getInstance() {
        return instance;
    }

    private static  Socket clientSocket;
    private static  ObjectOutputStream out;
    private static  ObjectInputStream in;
//    private BufferedReader in;
//    PrintWriter

    public static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static Message sendMessage(Message msg) throws IOException, ClassNotFoundException {
//        out.println(msg);
        System.out.println("Sending messages to the ServerSocket");
        out.writeObject(msg);
        Message resp = (Message) in.readObject();
        return resp;
    }

    public static void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
//
//public class Client {
//
//    public static void main(String[] args) throws IOException {
//        // need host and port, we want to connect to the ServerSocket at port 7777
//        Socket socket = new Socket("127.0.0.1", 8808);
//        System.out.println("Connected!");
//
//        // get the output stream from the socket.
//        OutputStream outputStream = socket.getOutputStream();
//        // create an object output stream from the output stream so we can send an object through it
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//
//        // make a bunch of messages to send.
//        List<Message> messages = new ArrayList<>();
//
//        ArrayList<String> requests = new ArrayList<>();
//        ArrayList<String> test = new ArrayList<>();
//        requests.add("request1");
//        requests.add("request2");
//        requests.add("request2");
//
//
//        messages.add(new Message("getAllMovies",requests));
//        messages.add(new Message("getCostam",test));
//
//        System.out.println("Sending messages to the ServerSocket");
//        objectOutputStream.writeObject(messages);
//
//        System.out.println("Closing socket and terminating program.");
//        socket.close();
//    }
//}