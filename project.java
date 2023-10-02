package CPIT305PROJECT;

import java.util.*;




class MenuItem {
    private String name;
    private int price;
    private int Lprice;
    
    public MenuItem(String name, int price,int Lprice) {
        this.name = name;
        this.price = price;
        this.Lprice=Lprice;
    }


    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public int getLPrice() {
        return Lprice;
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



