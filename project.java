package CPIT305PROJECT;

import java.util.*;



class MenuItem {
    private String name;
    private double priceM;
    private double priceL;
    private double price;

    public MenuItem(String name, double priceM, double priceL) {
        this.name = name;
        this.priceM = priceM;
        this.priceL = priceL;
    }

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPriceM() {
        return priceM;
    }

    public double getPriceL() {
        return priceL;
    }

    public double getSizePrice(String size){
        if(size=="M")
            return priceM;
        else if (size=="L")
            return priceL;
        
        return price;
    }
}

class OrderItem {
    private MenuItem menuItem;
    private String Size;
    private int quantity;

    public OrderItem(MenuItem menuItem, String Size, int quantity) {
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
        return menuItem.getSizePrice(Size) * quantity;
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



