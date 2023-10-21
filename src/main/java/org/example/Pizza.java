package org.example;

public class Pizza implements Product{
    private final String name;
    private final Size size;
    private final double price;
    public Pizza(String name, Size size, double price){
        this.name = name;
        this.size = size;
        this.price = price;
    }
    @Override
    public double getPrice(){
        return price;
    }
    @Override
    public String getName() {
        return name;
    }
}
