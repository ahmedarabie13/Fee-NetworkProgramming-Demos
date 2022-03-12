package edu.fee.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5051)){
            System.out.println("Server Started at Port 5051");
            int clientsNum = 1;
            List<Socket> clientsSockets = new ArrayList<>();
            while (true){
                Socket clientSocket = serverSocket.accept();
                clientsSockets.add(clientSocket);
                System.out.println("Serving "+clientsNum+" Client(s)");
                Runnable clientHandler = new ClientHandler(clientSocket,clientsSockets);
                Thread clientHandlerThread = new Thread(clientHandler);
                clientHandlerThread.start();
                clientsNum++;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
