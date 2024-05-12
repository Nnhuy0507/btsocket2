package baitapsocket2_java;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static HashMap<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1578);
            System.out.println("Server is running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected.");

                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addClient(String username, ClientHandler clientHandler) {
        clients.put(username, clientHandler);
    }

    public static void removeClient(String username) {
        clients.remove(username);
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clients.values()) {
            clientHandler.sendMessage(message);
        }
    }
}
