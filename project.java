package CPIT305PROJECT;
import java.util.*;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}

class Order {
    private List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}

class Cafe {
    private List<MenuItem> menu;

    public Cafe() {
        this.menu = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public Order createOrder() {
        return new Order();
    }
}

