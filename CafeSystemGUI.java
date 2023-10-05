package CPIT305PROJECT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CafeSystemGUI extends JFrame {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel beverageLabel;
    private JComboBox<String> beverageComboBox;
    private JLabel numOfBevLabel;
    private JTextField numOfBevTextField;
    
    private JComboBox<String> beverageSizeComboBox;


    private JButton orderButton;

    public CafeSystemGUI() {
        setTitle("Cafe System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        nameLabel = new JLabel("   Name:");
        nameTextField = new JTextField(15); 
        beverageLabel = new JLabel("        Beverage:");

        
        //
        ArrayList<MenuItem> item = AddItem();
        String[] beverages = new String[item.size()];
        for(int i = 0; i < item.size(); i++){
            beverages[i]=item.get(i).getName();
        }

        //

        beverageComboBox = new JComboBox<>(beverages);

        numOfBevLabel = new JLabel("How many drinks would you like?");
        numOfBevTextField = new JTextField(5);

        JLabel sizeOfBevLabel = new JLabel("What size would you like?");
        String[] sizes = {"Medium", "Large"};
        beverageSizeComboBox = new JComboBox<>(sizes);
        

        orderButton = new JButton("Place Order");


        orderButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        String name = nameTextField.getText();
        String beverage = (String) beverageComboBox.getSelectedItem();
        String beverageSize = (String) beverageSizeComboBox.getSelectedItem();
        double price = 0.0;

        //
        for (MenuItem item : item) {
            if (item.getName().equalsIgnoreCase(beverage)) {
                if (beverageSize.equalsIgnoreCase("Medium")) {
                    price = item.getPriceM();
                } else if (beverageSize.equalsIgnoreCase("Large")) {
                    price = item.getPriceL();
                }
                break;
            }
        }
        //

        String numberOfBev = numOfBevTextField.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String orderDateTime = dtf.format(now);

        String orderInfo = "Name: " + name + "\nBeverage: " + beverage +
                "\nNumber of beverages ordered: " + numberOfBev + "\nOrder Size: " + beverageSize +"\nPrice :"+
                price+
                "\nOrder Date/Time: " + orderDateTime + "\nTotal price for order: $" + String.format("%.2f", price * Integer.parseInt(numberOfBev));

        try {
            FileWriter writer = new FileWriter("order.txt", true);
            writer.write(orderInfo);
            writer.write("\n\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Thank you " + name + ", your " + beverage + " order has been placed.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});



        add(nameLabel);
        add(nameTextField);
        add(beverageLabel);
        add(beverageComboBox);
        add(numOfBevLabel);
        add(numOfBevTextField);
        add(sizeOfBevLabel);
        add(beverageSizeComboBox);
        add(orderButton);

        setVisible(true);
    }


    //
    public static ArrayList<MenuItem> AddItem(){
        ArrayList<MenuItem> items = new ArrayList<>();
        //String category = "";

        try (BufferedReader menuReader = new BufferedReader(new FileReader("MENU.txt"))) {
            String line=menuReader.readLine();

            if (line.equalsIgnoreCase("Drinks")) {
                line = menuReader.readLine();
                while(!line.equalsIgnoreCase("Desserts")){
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int price = Integer.parseInt(parts[1]);
                    int lPrice = Integer.parseInt(parts[2].trim());
                    items.add(new MenuItem(name, price,lPrice));
                    line = menuReader.readLine();
                }
            }

            for(int i=2;i>0;i--){
                line = menuReader.readLine();

                String[] parts = line.split(",");
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);
                items.add(new MenuItem(name, price));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        return items;
        ///
    }

}
