package edu.iu.c212.utils;

import edu.iu.c212.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static List<Item> readInventoryFromFile() throws IOException {
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

    public List<Staff> readStaffFromFile() throws IOException {
        // TODO
        return null;
    }

    public void writeInventoryToFile(List<Item> items) {
        // TODO
    }

    public void writeStaffToFile(List<Staff> employees) {
        // TODO
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

}
