package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store implements IStore
{
    static String name;
    static String price;
    static String quantity;
    static String aisleNum;


    // Constructor
    public Store()
    {
        // Required call
        takeAction();

        // Develop this into running through the input file
    }

    // Methods
    @Override
    public void takeAction()
    {

    }



    public static ArrayList<Item> getItemList() throws FileNotFoundException {
        ArrayList<Item> inList = new ArrayList<>();
        File inputFile = new File("src/edu/iu/c212/resources/inventory.txt");
        Scanner in = new Scanner(inputFile);
        in.nextLine();
        in.useDelimiter(",|\\n");
        int index = 0;

        while(in.hasNext()){
            if (index == 0) {
                name = in.next();
                index++;
            } else if (index == 1) {
                price = in.next();
                index++;
            } else if (index == 2) {
                quantity = in.next();
                index++;
            } else if (index == 3) {
                aisleNum = in.next();
                inList.add(new Item(name, price, quantity, aisleNum));
                index = 0;
            }
        }

        return inList;
    }

    @Override
    public List<Item> getItemsFromFile() {
        return null;
    }

    @Override
    public List<Staff> getStaffFromFile() {
        return null;
    }

    @Override
    public void saveItemsFromFile() {

    }

    @Override
    public void saveStaffFromFile() {

    }
}
