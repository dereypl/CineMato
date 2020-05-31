package main.java.Cinemato.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public static void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public static Message sendMessage(Message msg) throws IOException, ClassNotFoundException {
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