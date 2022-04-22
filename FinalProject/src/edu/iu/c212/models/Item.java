package edu.iu.c212.models;

public class Item
{
    // Instance variables
    private String name;
    private double price;
    private int quantity;
    private int aisleNum;

    // Constructor
    public Item(String name, double price, int quantity, int aisleNum)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisleNum = aisleNum;
    }

    // Methods
    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getAisle()
    {
        return aisleNum;
    }

    public void setAisle(int aisleNum){
        this.aisleNum = aisleNum;
    }
}
