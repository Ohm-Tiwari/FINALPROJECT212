package edu.iu.c212.utils;

import edu.iu.c212.SawPrimePlanks;
import edu.iu.c212.StaffScheduler;
import edu.iu.c212.Store;
import edu.iu.c212.models.*;
import edu.iu.c212.programs.StoreMapDisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File inputFile = new File("../resources/input.txt");
    private static File outputFile = new File("../resources/output.txt");
    private static File inventoryFile = new File("../resources/inventory.txt");
    private static File staffFile = new File("../resources/staff.txt");
    private static File staffAvailabilityFile = new File("../resources/staff_availability_IN.txt");
    private static File shiftSchedulesFile = new File("../resources/shift_schedules_IN.txt");
    private static File storeScheduleFile = new File("../resources/store_schedule_OUT.txt");

    // Private variables used for reading from File
    private static String name;
    private static double price;
    private static int quantity;
    private static int aisleNum;

    private static String fName;
    private static String lName;
    private static int age;
    private static String role;
    private static String availability;

    // Should be adapted properly
    public static List<Item> readInventoryFromFile() throws IOException
    {
        System.out.println(inventoryFile/*.toURI()*/.getPath() + "\n" + inventoryFile.exists());
        // depending on your OS, toURI() may need to be used when working with paths

        // List to return
        List<Item> returnList = new ArrayList<Item>();

        // Moved store code
        Scanner in = new Scanner(inventoryFile);
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
                returnList.add(new Item(name, price, quantity, aisleNum));
                index = 0;
            }
        }
        return returnList;
    }

    public List<Staff> readStaffFromFile() throws IOException
    {
        //Create return list
        List<Staff> returnList2 = new ArrayList<Staff>();

        Scanner in = new Scanner(staffFile);
        in.nextLine();
        in.useDelimiter(",|\\n");
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
                returnList2.add(new Staff((fName + " " + lName), age, role, availability));
                index = 0;
            }
        }
        return returnList2;
    }

    public void writeInventoryToFile(List<Item> items)
    {
        try
        {
            FileWriter inventoryInput = new FileWriter(inventoryFile, false);
            PrintWriter rewriter = new PrintWriter(inventoryInput, true);

            // Build the items back into strings
            List<String> itemStrings = new ArrayList<String>();
            for (Item item : items)
            {
                String itemFormat = item.getName() + ("" + item.getPrice()) + ("" + item.getQuantity()) + ("" + item.getAisle());
                itemStrings.add(itemFormat);
            }

            // Then rewrite the list
            for (String item: itemStrings)
            {
                rewriter.println(item);
            }

            inventoryInput.close();
            rewriter.close();
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    public void writeStaffToFile(List<Staff> employees)
    {
        try
        {
            // Create writers
            FileWriter staffFileInput = new FileWriter(staffFile, false);
            PrintWriter writeToStaffFile = new PrintWriter(staffFileInput, true);

            // Write list
            for (Staff employee : employees)
            {
                writeToStaffFile.println(employee.getName() + "," + employee.getAge() + "," + employee.getRole() + "," + employee.getAvailability());
            }

            //Close Files
            staffFileInput.close();
            writeToStaffFile.close();
        }
        catch(IOException e)
        {
            System.exit(0);
        }

    }

    public static List<String> readCommandsFromFile() throws IOException {
        // TODO
        return null;
    }

    public static void writeStoreScheduleToFile(List<String> lines) {
        // TODO
    }

    // Should be implemented properly
    public static void writeLineToOutputFile(String line)
    {
        try
        {
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            out.println(line);
        }
        catch (IOException e)
        {
            System.exit(0);
        }

    }

    // Command Functions

    // ADD Command Function
    public void ADD(String itemName, double itemCost, int itemQuantity, int itemAisle)
    {
        // Make and add item to our list
        Item newProduct = new Item(itemName, itemCost, itemQuantity, itemAisle);
        Store.itemList.add(newProduct);

        //Write new list
        writeInventoryToFile(Store.itemList);

        // Write output line
        writeLineToOutputFile(itemName + " was added to inventory.");
    }


    // COST Command Function
    public void COST(String itemName)
    {
        double itemCost = 0.0;
        //Find the cost
        for (Item item: Store.itemList)
        {
            if(item.getName().equals(itemName))
            {
                itemCost = item.getPrice();
            }
        }

        FileUtils.writeLineToOutputFile(itemName +": $" + itemCost);
    }

    // EXIT Command Function
    public void EXIT() throws FileNotFoundException, IOException
    {
        System.out.println("Press enter to continue...");
        Store.exitCommandMenu = true;

        FileUtils.writeLineToOutputFile("Thank you for visiting High's Hardware and Gardening!");
    }

    // Essentially just a trigger for the subprogram: StoreMap
    public void FIND(String itemName)
    {
        // Find the item
        Item itemFound = new Item("Null", 0.0, 1, 1);

        for (Item item: Store.itemList)
        {
            if(item.getName().equals(itemName))
            {
                itemFound = item;
            }
        }

        if (itemFound.getName().equals("Null"))
        {
            FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be found.");
        }
        else
        {
            FileUtils.writeLineToOutputFile("Performing store lookup for " + itemName);
            // Create the store map
            StoreMapDisplay.display(itemFound);
        }
    }

    // Fire command
    public void FIRE(String name) throws IOException
    {
        try
        {
            File newFile = new File("tempFile.txt");
            BufferedReader input = new BufferedReader(new FileReader(staffScheduleInputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            for (int i = 0; i < Store.staffList.size(); i++) {
                if(Store.staffList.get(i).getName().equals(name))
                {
                    // Supposed to be empty
                }
                else
                {
                    writer.write(Store.staffList.get(i).getName() + " " + Store.staffList.get(i).getAge() + " " + Store.staffList.get(i).getRole() + " " + Store.staffList.get(i).getAvailability() + System.getProperty("line.separator"));
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

    public void HIRE(String staffName, int staffAge, String staffRole, String staffAvailability)
    {
        try
        {


            // Writer for Staff Availability


            // Write output
            out.println(staffName + " has been hired as a " + staffRole);

            // Make staff object, add to list
            Staff newStaffMember = new Staff(staffName, staffAge, staffRole, staffAvailability);
            Store.staffList.add(newStaffMember);

        }
        catch(IOException e)
        {
            System.exit(0);
        }
    }

    // Usage of SawPrimePlanks
    public void SAW()
    {
        try
        {
            // Writer for Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            // Make scheduler object
            SawPrimePlanks sawPrimePlanks = new SawPrimePlanks();

            List<Item> plankList = new ArrayList<Item>();

            // Derive non prime planks
            for(Item item : Store.itemList)
            {
                if(item.getName().substring(0, 5).equals("Plank"))
                {
                    plankList.add(item);
                }
            }

            // Remove from inventory List

            // Remove from inventory.txt
            List<String> allOtherLines = new ArrayList<String>();
            Scanner in = new Scanner(inventoryFile);
            int index = 0;

            while(in.hasNextLine())
            {
                if (index == 0)
                {
                    name = in.next();
                    index++;
                }

                allOtherLines.add(in.nextLine());
            }

            // Write to Output
            out.println("Planks Sawed.");
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    // Essentially just a trigger for the subprogram: StaffScheduler
    public void SCHEDULE()
    {
        try
        {
            // Writer for Output
            FileWriter output = new FileWriter(inventoryFile, false);
            PrintWriter out = new PrintWriter(output, true);

            // Make scheduler object
            StaffScheduler staffScheduler = new StaffScheduler();

            // Use Subprogram
            staffScheduler.ScheduleStaff();

            // Write to Output
            out.println("Schedule Created.");
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    // Sells object from staff scheduler
    public void SELL(String itemName, int itemQuantity)
    {
        try
        {
            // Write to Output
            FileWriter output = new FileWriter(outputFile, true);
            PrintWriter out = new PrintWriter(output, true);

            // Remove the given quantity from the inventory line to inventory
            Item itemFound = new Item("Null", 0.0, 1, 1);

            for (Item item: Store.itemList)
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
            for (Item item: Store.itemList)
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

    // Helper ItemList Builder
    private void itemListToInventoryTxt()
    {


    }
}

}
