package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store implements IStore
{

    static List<Item> itemList;
    static List<Staff> staffList;

    static String name;
    static double price;
    static int quantity;
    static int aisleNum;

    static String fName;
    static String lName;
    static int age;
    static String role;
    static String availability;

    // Files
    static File inventoryFile;
    static File inputFile;
    static File outputFile;
    static File staffSchedulesInputFile;
    static File staffAvailabilityFile;
    static File storeScheduleOutputFile;

    //Command bools
    static boolean exitCommandMenu; 

    // Constructor
    public Store() throws FileNotFoundException
    {
        try
        {
            // Fill files
            inventoryFile = new File("src/edu/iu/c212/resources/inventory.txt");
            staffAvailabilityFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
            inputFile = new File("src/edu/iu/c212/resources/input.txt");

            // Trigger take action
            takeAction();
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
    }

    // Methods
    @Override
    public void takeAction() throws FileNotFoundException
    {
        try
        {
            // Create the lists
            saveItemsFromFile(inventoryFile);
            saveStaffFromFile(staffAvailabilityFile);
        }
        catch(FileNotFoundException e)
        {
            System.exit(0);
        }
    }


    @Override
    public List<Item> getItemsFromFile(File inputFile) throws FileNotFoundException
    {
        List<Item> inList = new ArrayList<>();
        Scanner in = new Scanner(inputFile);
        in.nextLine();
        in.useDelimiter(",|\\n");
        int index = 0;

        while(in.hasNext()){
            if (index == 0) {
                name = in.next();
                index++;
            } else if (index == 1) {
                price = Double.parseDouble(in.next());
                index++;
            } else if (index == 2) {
                quantity = Integer.parseInt(in.next());
                index++;
            } else if (index == 3) {
                aisleNum = Integer.parseInt(in.next());
                inList.add(new Item(name, price, quantity, aisleNum));
                index = 0;
            }
        }

        return inList;
    }

    @Override
    public List<Staff> getStaffFromFile(File inputFile) throws FileNotFoundException
    {
        List<Staff> inList = new ArrayList<>();
        Scanner in = new Scanner(inputFile);

        int index = 0;
        while(in.hasNext()){
            if (index == 0) {
                fName = in.next();
                index++;
            } else if (index == 1) {
                lName = in.next();
                index++;
            } else if (index == 2) {
                age = Integer.parseInt(in.next());
                index++;
            } else if (index == 3) {
                role = in.next();
                index++;
            } else if (index == 4) {
                availability = in.next();
                inList.add(new Staff((fName + " " + lName), age, role, availability));
                index = 0;
            }
        }
        return inList;
    }

    @Override
    public void saveItemsFromFile(File inputFile) throws FileNotFoundException
    {
        try
        {
            itemList = getItemsFromFile(inputFile);
        }
        catch(FileNotFoundException e)
        {
            System.exit(0);
        }

    }

    @Override
    public void saveStaffFromFile(File inputFile) throws FileNotFoundException
    {
        try
        {
            staffList = getStaffFromFile(inputFile);
        }
        catch(FileNotFoundException e)
        {
            System.exit(0);
        }
    }

    // Helper function
    public static void ADD(String itemName, double itemCost, int itemQuantity, int itemAisle)
    {

    }

    public static void COST(String itemName)
    {

    }

    public static void EXIT() throws FileNotFoundException, IOException
    {
        try
        {
            System.out.println("Press enter to continue...");
            Scanner in = new Scanner(inputFile);

            FileWriter output = new FileWriter(inputFile, true);
            PrintWriter out = new PrintWriter(output, true);
            out.println("Thank you for visiting High's Hardware and Gardening!");

            while(!)
            {

            }
        }
        catch(FileNotFoundException e1)
        {
            System.exit(0);
        }
        catch(IOException e2)
        {
            System.exit(0);
        }

    }

}