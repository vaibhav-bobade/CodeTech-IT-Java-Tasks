package task3_multithreaded_chat_application;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server started...");
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                // Wait for a new client and start a new thread for them
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (Scanner in = new Scanner(socket.getInputStream())) {
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                while (in.hasNextLine()) {
                    String message = in.nextLine();
                    broadcast(message);
                }
            } catch (IOException e) {
                System.out.println("Connection lost.");
            } finally {
                if (out != null) {
                    synchronized (clientWriters) { clientWriters.remove(out); }
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}