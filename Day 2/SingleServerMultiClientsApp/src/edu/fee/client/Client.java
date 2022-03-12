package edu.fee.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 5051);

        try (InputStream inStream = clientSocket.getInputStream();
             OutputStream outStream = clientSocket.getOutputStream()) {

            Scanner in = new Scanner(inStream, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);

            Scanner reader = new Scanner(System.in, StandardCharsets.UTF_8);

            Thread sendingThread = new Thread(() -> {
                String str;
                while (true) {
                    str = reader.nextLine();
                    out.println(str);
                }
            });
            sendingThread.start();
            String line;
            while (true) {
                if (in.hasNextLine()) {
                    line = in.nextLine();
                    System.out.println(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
