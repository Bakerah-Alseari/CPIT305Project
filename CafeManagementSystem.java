package CPIT305PROJECT;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class CafeManagementSystem {
    public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                CafeSystemGUI cafe = new CafeSystemGUI();
                });

            CafeServer thr = new CafeServer();
            try {
                thr.start();
                Socket socket = new Socket("localhost", 8189); //Connect to the server on localhost:8189

                //Set up streams for object communication
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    String userInput = scanner.nextLine();

                    //Get user's rating
                    System.out.println("Rate our service (1-5): (enter -exit- to exit)");
                    int rating = scanner.nextInt();
                    out.writeInt(rating);
                    out.flush();
                    //Receive and print the server's response
                    String response = (String) in.readObject();
                    System.out.println("Server response: " + response);

                    //Get user's suggestions
                    scanner.nextLine();
                    System.out.println("Do you have any complaints or suggestions? (enter -no- to exit) ");
                    String suggestion = scanner.nextLine();
                    out.writeObject(suggestion);
                    out.flush();
                    //Receive and print the server's response
                    response = (String) in.readObject();
                    System.out.println("Server response: " + response);

                    if (userInput.equalsIgnoreCase("exit") || suggestion.equalsIgnoreCase("no")) {
                        break;
                    }
                    }
                //Close the connection
                out.close();
                in.close();
                socket.close();
                }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
