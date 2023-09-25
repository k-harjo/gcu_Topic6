package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start("localhost", 6666);
        client.cleanup();
    }

    public void start(String ipAddress, int port) throws IOException {
        Socket socket = new Socket(ipAddress, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        try {
            for (int i = 1; i <= 9; i++) {
                String message = "Message " + i;
                String response = sendMessage(out, in, message);
                System.out.println("Received from server: " + response);
            }

            // Send the quit message
            String quitMessage = ".";
            String response = sendMessage(out, in, quitMessage);
            System.out.println("Received from server: " + response);
        } finally {
            socket.close();
        }
    }

    public String sendMessage(PrintWriter out, BufferedReader in, String message) throws IOException {
        out.println(message);
        return in.readLine();
    }

    public void cleanup() {
        // Clean up resources here
    }
}
