package CPIT305PROJECT;

//Thread class
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class OrderProcessor implements Runnable{
    private String name;
    private String beverage;
    private String beverageSize;
    private double price;
    private String numberOfBev;
    private String orderDateTime;

    public OrderProcessor(String name, String beverage, String beverageSize, double price, String numberOfBev, String orderDateTime) {
        this.name = name;
        this.beverage = beverage;
        this.beverageSize = beverageSize;
        this.price = price;
        this.numberOfBev = numberOfBev;
        this.orderDateTime = orderDateTime;
    }

    @Override
    public void run() {
        // Process the order in the background (e.g., file writing)
        String orderInfo = "Name: " + name + "\nBeverage: " + beverage +
                "\nNumber of beverages ordered: " + numberOfBev + "\nOrder Size: " + beverageSize + "\nPrice :" +
                price +
                "\nOrder Date/Time: " + orderDateTime + "\nTotal price for order: $" + String.format("%.2f", price * Integer.parseInt(numberOfBev));

        try {
            FileWriter writer = new FileWriter("order.txt", true);
            writer.write(orderInfo);
            writer.write("\n\n");
            writer.close();
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "Thank you " + name + ", your " + beverage + " order has been placed.")
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
