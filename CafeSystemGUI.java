package CPIT305PROJECT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CafeSystemGUI extends JFrame {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel beverageLabel;
    private JComboBox<String> beverageComboBox;

    private JLabel BakeryLabel;
    private JComboBox<String> BakeryComboBox;
    private JButton orderButton;

    public CafeSystemGUI() {
        setTitle("Cafe System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(15);
        beverageLabel = new JLabel("         Beverage:");
        String[] beverages = {"Coffee", "Tea", "Juice"};
        beverageComboBox = new JComboBox<>(beverages);
        BakeryLabel = new JLabel("Bakery:");
        String[] Bakery = {"Donuts", "Cake", "biscuits "};
        BakeryComboBox = new JComboBox<>(Bakery);
        orderButton = new JButton("Place Order");

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                String name = nameTextField.getText();
                String beverage = (String) beverageComboBox.getSelectedItem();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String orderDateTime = dtf.format(now);

                String orderInfo = "Name: " + name + "\nBeverage: " + beverage + "\nOrder Date/Time: " + orderDateTime;
                try {

                    FileWriter writer = new FileWriter("order.txt", true);
                    writer.write(orderInfo);
                    writer.write("\n\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Thank you, " + name + "Your " + beverage + " order has been placed.");
                } catch (
                        IOException ex) {
                    ex.printStackTrace();
                }
            }
        });


        add(nameLabel);
        add(nameTextField);
        add(beverageLabel);
        add(beverageComboBox);
        add(BakeryLabel);
        add(BakeryComboBox);
        add(orderButton);

        setVisible(true);
    }
}
