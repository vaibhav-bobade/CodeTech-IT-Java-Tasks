package task3_multithreaded_chat_application;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            // Thread to listen for messages from server
            new Thread(() -> {
                try (Scanner in = new Scanner(socket.getInputStream())) {
                    while (in.hasNextLine()) {
                        System.out.println("Server: " + in.nextLine());
                    }
                } catch (IOException e) { e.printStackTrace(); }
            }).start();

            // Main thread to send messages
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNextLine()) {
                out.println(userInput.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}