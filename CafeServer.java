package CPIT305PROJECT;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class CafeServer extends Thread{
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8189); // Server socket listening on port 8189

            while (true) {
                System.out.println("Waiting for client to connect...");
                Socket clientSocket = serverSocket.accept(); //Wait for client to connect
                System.out.println("Client connected!");
                System.out.println("Thank you for ordering! Please rate our service and write any suggestions/complaints you have");

                // Set up streams for object communication
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                try {
                    while (true) {
                        //Read rating from client
                        int rating = in.readInt();
                        System.out.println("Received rating: " + rating);
                        //Send response back to the client
                        out.writeObject("Rating received successfully!");
                        out.flush();
                        //Read the suggestion from the client
                        String suggestion = (String) in.readObject();
                        System.out.println("Received suggestion: " + suggestion);
                        //Send response back to the client
                        out.writeObject("Suggestion received successfully!");
                        out.flush();
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    //Close the connection
                    in.close();
                    out.close();
                    clientSocket.close();
                    System.out.println("Client disconnected!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
