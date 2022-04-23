package edu.iu.c212.models;

public class Item
{
    // Instance variables
    private String name;
    private double price;
    private int quantity;
    private int aisleNum;

    private String tName;
    private String tPrice;
    private String tQuantity;
    private String tAisleNum;

    // Constructor
    public Item(String name, double price, int quantity, int aisleNum)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisleNum = aisleNum;
    }
    public Item(String name, String price, String quantity, String aisleNum)
    {
        this.tName = name;
        this.tPrice = price;
        this.tQuantity = quantity;
        this.tAisleNum = aisleNum;
    }

    // Methods
    public String getName()
    {
        return name;
    }

    public String gettPRice() { return tPrice; }
    public String gettQuantity() { return tQuantity; }
    public String gettAisleNum() { return tAisleNum; }
    public String gettName() { return tName; }

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
