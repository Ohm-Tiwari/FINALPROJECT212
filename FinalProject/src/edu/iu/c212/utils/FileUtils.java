package edu.iu.c212.utils;

import edu.iu.c212.programs.SawPrimePlanks;
import edu.iu.c212.programs.StaffScheduler;
import edu.iu.c212.Store;
import edu.iu.c212.models.*;
import edu.iu.c212.programs.StoreMapDisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static final File inputFile = new File("src/edu/iu/c212/resources/input.txt");
    private static final File outputFile = new File("src/edu/iu/c212/resources/output.txt");
    private static final File inventoryFile = new File("src/edu/iu/c212/resources/inventory.txt");
    private static final File staffAvailabilityFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
    private static final File shiftSchedulesFile = new File("src/edu/iu/c212/resources/shift_schedules_IN.txt");
    private static final File storeScheduleFile = new File("src/edu/iu/c212/resources/store_schedule_OUT.txt");

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
        in.useDelimiter(",|\\r");
        //in.nextLine();
        int index = 0;

        while(in.hasNext())
        {
            if (index == 0) {
                String tempName = in.next();
                name = tempName.substring(1, tempName.length() - 1);
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

    public static List<Staff> readStaffFromFile() throws IOException
    {
        System.out.println(staffAvailabilityFile/*.toURI()*/.getPath() + "\n" + staffAvailabilityFile.exists());

        //Create return list
        List<Staff> returnList2 = new ArrayList<Staff>();

        Scanner in = new Scanner(staffAvailabilityFile);
        //in.nextLine();
        //in.useDelimiter(",|\\n");
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
                returnList2.add(new Staff(fName, age, role, availability));
                index = 0;
            }
        }
        return returnList2;
    }

    public void writeInventoryToFile(List<Item> items)
    {
        System.out.println("Wrote");
        try
        {
            FileWriter inventoryInput = new FileWriter(inventoryFile, false);
            PrintWriter rewriter = new PrintWriter(inventoryInput, true);

            // Build the items back into strings
            List<String> itemStrings = new ArrayList<String>();
            for (Item item : items)
            {
                String itemFormat = item.getName() + ("'," + item.getPrice()) + ("," + item.getQuantity()) + ("," + item.getAisle());
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
        System.out.println("Wrote staff");
        try
        {
            // Create writers
            FileWriter staffFileInput = new FileWriter(staffAvailabilityFile, false);
            PrintWriter writeToStaffFile = new PrintWriter(staffFileInput, true);

            // Write list
            for (Staff employee : employees)
            {
                writeToStaffFile.println(employee.getName() + " " + employee.getAge() + " " + employee.getRole() + " " + employee.getAvailability());
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

    public static List<String> readCommandsFromFile() throws IOException
    {
        System.out.println("Read commands");
        List<String> commandList = new ArrayList<>();
        Scanner in = new Scanner(inputFile);
        while (in.hasNextLine())
        {
            commandList.add(in.nextLine());
        }
        for (String string :commandList)
        {
            System.out.println(string);
        }
        return commandList;
    }

    public static void writeStoreScheduleToFile(List<String> lines) throws IOException
    {
        System.out.println("Wrote store schedule");
        FileWriter scheduleWriter = new FileWriter(storeScheduleFile);
        PrintWriter output = new PrintWriter(scheduleWriter, true);

        for (String line : lines)
        {
            output.println(line);
        }

        output.close();
        scheduleWriter.close();
    }

    // Should be implemented properly
    public static void writeLineToOutputFile(String line)
    {
        System.out.println("WriteLineTOOutputFile");
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

    // Command Functions \\

    // ADD Command Method
    public void ADD(String itemName, double itemCost, int itemQuantity, int itemAisle)
    {
        System.out.println("add");
        // Make and add item to our list
        Item newProduct = new Item(itemName, itemCost, itemQuantity, itemAisle);
        Store.itemList.add(newProduct);

        //Write new list
        writeInventoryToFile(Store.itemList);

        // Write output line
        writeLineToOutputFile(itemName + " was added to inventory.");
    }


    // COST Command Method
    public void COST(String itemName)
    {
        System.out.println("Cost");
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

    // EXIT Command Method
    public void EXIT() throws FileNotFoundException, IOException
    {
        System.out.println("Press enter to continue...");
        Store.exitCommandMenu = true;

        FileUtils.writeLineToOutputFile("Thank you for visiting High's Hardware and Gardening!");
    }

    // FIND Command Method (uses StoreMap)
    public  void FIND(String itemName)
    {
        System.out.println("Find");
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

    // FIRE Command Method
    public void FIRE(String staffName)
    {
        System.out.println("Fire");
        // Variable for fail condition
        Staff staffToRemove = new Staff("Null", 28, "g", "");

        // Find that staff member
        for (Staff staff: Store.staffList)
        {
            if(staff.getName().equals(staffName))
            {
                staffToRemove = staff;
            }
        }

        if (staffToRemove.getName().equals("Null"))
        {
            FileUtils.writeLineToOutputFile("ERROR: " + staffName + " cannot be found.");
        }
        else
        {
            //Remove staff from list
            Store.staffList.remove(staffAvailabilityFile);

            //Write updated list to staffFile
            writeStaffToFile(Store.staffList);

            // Write output line
            writeLineToOutputFile(staffName + " was fired.");
        }
    }

    // HIRE Command Method
    public void HIRE(String staffName, int staffAge, String staffRole, String staffAvailability)
    {
        System.out.println("Hire");
        // Make staff object, add to list
        Staff newStaffMember = new Staff(staffName, staffAge, staffRole, staffAvailability);
        Store.staffList.add(newStaffMember);

        //Write updated list to staffFile
        writeStaffToFile(Store.staffList);

        // Write output line
        writeLineToOutputFile(staffName + " has been hired as a " + staffRole);
    }

    public void PROMOTE(String staffName, String staffRole)
    {
        System.out.println("Promote");
        // Grab staff member
        Staff staffToPromote = new Staff("Null", 28, "g", "");

        // Find that staff member
        for (Staff staff: Store.staffList)
        {
            if(staff.getName().equals(staffName))
            {
                staffToPromote = staff;
            }
        }

        if (staffToPromote.getName().equals("Null"))
        {
            FileUtils.writeLineToOutputFile("ERROR: " + staffName + " cannot be found.");
        }
        else
        {
            staffToPromote.setRole(staffRole);

            //Write updated list with new role to staffFile
            writeStaffToFile(Store.staffList);

            // Write output line
            writeLineToOutputFile( staffName + " has been hired as a " + staffRole);
        }
    }

    // SAW Command Method (uses SawPrimePlanks)
    public void SAW()
    {
        System.out.println("Saw");
        // Make scheduler object
        SawPrimePlanks sawPrimePlanks = new SawPrimePlanks();

        List<Item> plankList = new ArrayList<>();
        List<Integer> plankLengthList = new ArrayList<>();

        // Derive non prime planks
        for(Item item : Store.itemList)
        {
            if(item.getName().substring(0, 5).equals("Plank"))
            {
                //Derive length from string
                String itemLength = item.getName().substring(5, item.getName().length());
                Integer plankLength = Integer.parseInt(itemLength);

                if(!sawPrimePlanks.isPrime(plankLength))
                {
                    for (int i = 0; i < item.getQuantity(); i++)
                    {
                        plankList.add(item);
                        plankLengthList.add(plankLength);
                    }

                    // Remove the item
                    Store.itemList.remove(item);
                }

            }
        }

        int count = 0;

        // Now saw those non-prime planks, and add them back to item list
        for(Integer length : plankLengthList)
        {
            List<Integer> plankPrimed = sawPrimePlanks.getPlanksLengths(length);
            Item newItem = new Item("Plank-" + plankPrimed.get(0), plankList.get(count).getPrice(), plankPrimed.size(), plankList.get(count).getAisle());
            Store.itemList.add(newItem);
            count += 1;
        }

        //Rewrite inventory.txt
        writeInventoryToFile(Store.itemList);

        // Write output line
        writeLineToOutputFile("Planks Sawed.");
    }

    // SCHEDULE Command Method (uses StaffScheduler)
    public void SCHEDULE()
    {
        System.out.println("Schedule");
        try
        {
            // Make scheduler object
            StaffScheduler staffScheduler = new StaffScheduler();

            // Use Subprogram
            staffScheduler.ScheduleStaff();

            writeLineToOutputFile("Schedule Created.");
        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }

    // SELL Command Method
    public void SELL(String itemName, int itemQuantity)
    {
        System.out.println("Sell");
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
            FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be sold.");
        }
        else
        {
            if(itemFound.getQuantity() < itemQuantity)
            {
                FileUtils.writeLineToOutputFile("ERROR: " + itemName + " cannot be sold.");
            }
            else if(itemFound.getQuantity() >= itemQuantity)
            {
                // Calculate new quantity
                int itemFoundQuantity = itemFound.getQuantity();
                int finalQuantity = itemFoundQuantity - itemQuantity; // Finally, getting new quantity total
                itemFound.setQuantity(finalQuantity);

                // Print to out
                FileUtils.writeLineToOutputFile(itemQuantity + " " + itemName + " was sold.");

            }
        }
    }

    public void QUANTITY(String itemName)
    {
        System.out.println("Quantity");
        double itemQuantity = 0.0;
        //Find the cost
        for (Item item: Store.itemList)
        {
            if(item.getName().equals(itemName))
            {
                itemQuantity = item.getPrice();
            }
        }

        FileUtils.writeLineToOutputFile(itemName +": #" + itemQuantity);
    }
}


