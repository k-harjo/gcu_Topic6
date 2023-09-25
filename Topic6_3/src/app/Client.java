package app;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ipAddress, int port) throws IOException {
        socket = new Socket(ipAddress, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String sendMessage(String message) throws IOException {
        out.println(message);
        String response = in.readLine();
        return response;
    }

    public void cleanup() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.start("DESKTOP-GA9VKMI", 8088); // Connect to the server running on localhost:8088
            for (int i = 1; i <= 9; i++) {
                String message = "Message " + i;
                String response = client.sendMessage(message);
                System.out.println("Sent: " + message);
                System.out.println("Received: " + response);
                Thread.sleep(5000); // Sleep for 5 seconds
            }
            client.sendMessage(".");
            client.cleanup();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}