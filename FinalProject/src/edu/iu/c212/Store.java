package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.KeyListener;

import static edu.iu.c212.Store.exitCommandMenu;

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
    static File staffScheduleInputFile;
    static File staffAvailabilityFile;
    static File storeScheduleOutputFile;

    //Command booleans
    protected static boolean exitCommandMenu;

    // Constructor
    public Store() throws FileNotFoundException
    {
        try {
            // Fill files
            inventoryFile = new File("src/edu/iu/c212/resources/inventory.txt");
            staffAvailabilityFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
            outputFile = new File("src/edu/iu/c212/resources/output.txt");
            staffScheduleInputFile = new File("src/edu/iu/c212/resources/shift_schedules_IN.txt");
            storeScheduleOutputFile = new File("src/edu/iu/c212/resources/store_schedule_OUT.txt");
            inputFile = new File("src/edu/iu/c212/resources/input.txt");

            // Trigger take action
            takeAction();

            // Initialize Keyboard
            Keyboard keyboard = new Keyboard();
            exitCommandMenu = false;

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

    // Command functions for Input File Command Parsing

    // Add an item inventory.txt, record that in the output file
    // Whatever parses is meant to know those variables separately messed up
    public void ADD(String itemName, double itemCost, int itemQuantity, int itemAisle)
    {
        try
        {
            // Write to Inventory
            FileWriter inventory = new FileWriter(inventoryFile, true);
            PrintWriter inventoryIn = new PrintWriter(inventory, true);

            // Add the line to inventory
            inventoryIn.println("'" + itemName + "'" + "," + itemCost + "," + itemQuantity + "," + itemAisle);

            inventoryIn.close();

            // Write to Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            out.println(itemName + " was added to inventory.");

            out.close();
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    // Command parser derives name and then calls COST after stripping '' from 'itemname'
    // - Josh
    public void COST(String itemName)
    {
        try
        {
            double itemCost = 0.0;
            //Find the cost
            for (Item item: itemList)
            {
                if(item.getName().equals(itemName))
                {
                    itemCost = item.getPrice();
                }
            }

            // Write to Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            out.println(itemName +": $" + itemCost);

            out.close();
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    // Meant to parse
    public void EXIT() throws FileNotFoundException, IOException
    {
        try
        {
            System.out.println("Press enter to continue...");
            exitCommandMenu = true;

            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);
            out.println("Thank you for visiting High's Hardware and Gardening!");

            out.close();
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
    public void FIND(String itemName)
    {

    }

    // Fire command
    public void FIRE(String name) throws IOException
    {
        try
        {
            File newFile = new File("tempFile.txt");
            BufferedReader input = new BufferedReader(new FileReader(staffScheduleInputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            for (int i = 0; i < staffList.size(); i++) {
                if(staffList.get(i).getName().equals(name))
                {
                    // Supposed to be empty
                }
                else
                {
                    writer.write(staffList.get(i).getName() + " " + staffList.get(i).getAge() + " " + staffList.get(i).getRole() + " " + staffList.get(i).getAvailability() + System.getProperty("line.separator"));
                }

            }
            boolean result = newFile.renameTo(inputFile);
            input.close();
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("File could not be found!");
            System.exit(0);
        }
    }

    public void HIRE(String staffName, int staffAge, String staffRole, String Availability)
    {

    }

    public void SAW()
    {

    }

    public void SCHEDULE()
    {

    }

    public void SELL(String itemName, int itemQuantity)
    {
        try
        {
            // Write to Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            // Remove the given quantity from the inventory line to inventory
            Item itemFound = new Item("Null", 0.0, 1, 1);

            for (Item item: itemList)
            {
                if(item.getName().equals(itemName))
                {
                    itemFound = item;
                }
            }

            if (itemFound.getName().equals("Null"))
            {
                out.println("ERROR: " + itemName + " could not be sold.");
            }
            else
            {
                if(itemFound.getQuantity() < itemQuantity)
                {
                    out.println("ERROR: " + itemName + " could not be sold.");
                }
                else if(itemFound.getQuantity() >= itemQuantity)
                {
                    // Calculate new quantity
                    int itemFoundQuantity = itemFound.getQuantity();
                    int finalQuantity = itemFoundQuantity - itemQuantity; // Finally, getting new quantity total
                    itemFound.setQuantity(finalQuantity);

                    // Print to out
                    out.println(itemQuantity + itemName + " was sold.");
                }
            }

            out.close();
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    public void QUANTITY(String itemName)
    {
        try
        {
            // Write to Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            double itemQuantity = 0.0;
            //Find the cost
            for (Item item: itemList)
            {
                if(item.getName().equals(itemName))
                {
                    itemQuantity = item.getPrice();
                }
            }

            out.println(itemName +": #" + itemQuantity);

            out.close();
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }
}

class Keyboard implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent event)
    {
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        //Input key for Exit command
        if(event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(exitCommandMenu)
            {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
    }
}