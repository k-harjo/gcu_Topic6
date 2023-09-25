package app;

public class ServerApp {
 public static void main(String[] args) {
     // Create a thread for the Server
     Thread serverThread = new Thread(() -> {
    	 ServerThread server = new ServerThread(8088); // Create a server instance listening on port 8088
         server.start(); 
     });

     serverThread.start(); // Start the server thread

     while (serverThread.isAlive()) {
         System.out.print(".");
         try {
             Thread.sleep(5000); // Sleep for 5 seconds
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
 }
}
