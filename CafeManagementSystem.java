package CPIT305PROJECT;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import javax.swing.*;

public class CafeManagementSystem {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CafeSystemGUI cafe = new CafeSystemGUI();
        });
        
        try
        {  String urlName = "https://www.kyancafe.com";
            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            //print header functions
            System.out.println("----------");
            System.out.println("Get content type: " + connection.getContentType());
            System.out.println("Get content length: " + connection.getContentLength());
            System.out.println("Get content encoding: " + connection.getContentEncoding());
            System.out.println("Get last modifed: " + new Date(connection.getLastModified()));
            System.out.println("----------");
            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";
            try (Scanner in = new Scanner(connection.getInputStream(), encoding))
            {
                while (in.hasNext())
                    System.out.println(in.nextLine());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
}
}
