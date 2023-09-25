package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);
        server.cleanup();
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received from client: " + message);
                    if (message.equals(".")) {
                        out.println("QUIT");
                        break;
                    }
                    out.println("OK");
                }

                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                clientSocket.close();
            }
        } finally {
            serverSocket.close();
        }
    }

    public void cleanup() {
        // Clean up resources here
    }
}
