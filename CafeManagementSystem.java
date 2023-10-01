package CPIT205PROJECT;

public class CafeManagementSystem {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();

        // Adding menu items
        cafe.addItem(new MenuItem("Coffee", 2.50));
        cafe.addItem(new MenuItem("Tea", 1.75));
        // Add more menu items as needed

        // Creating an order
        Order order = cafe.createOrder();

        // Adding items to the order
        order.addItem(new OrderItem(cafe.getMenu().get(0), 2)); // 2 coffees
        order.addItem(new OrderItem(cafe.getMenu().get(1), 1)); // 1 tea

        // Displaying the order
        System.out.println("Order Items:");
        for (OrderItem item : order.getOrderItems()) {
            System.out.println(item.getMenuItem().getName() + " x" + item.getQuantity() + " - $" + item.getTotalPrice());
        }
        System.out.println("Total Price: $" + order.getTotalPrice());
    }
}
