package edu.fee;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server started at port 5050");
        Socket socket = serverSocket.accept();
        System.out.println("Server got a client");

        OutputStream socketOutputStream = socket.getOutputStream();
        DataOutputStream socketDOS = new DataOutputStream(socketOutputStream);


        String serverMsg = "Server: Hola Client";
        socketDOS.writeUTF(serverMsg);
        System.out.println(serverMsg);


        InputStream socketInputStream = socket.getInputStream();
        DataInputStream socketDIS = new DataInputStream(socketInputStream);

        String clientResponse = socketDIS.readUTF();
        System.out.println(clientResponse);


        socketDOS.close();
        socketDIS.close();
        socketOutputStream.close();
        socketInputStream.close();
        socket.close();
        serverSocket.close();

    }
}
