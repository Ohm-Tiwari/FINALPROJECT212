package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;
import edu.iu.c212.utils.FileUtils;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.List;
import java.awt.event.KeyListener;

import static edu.iu.c212.Store.exitCommandMenu;

public class Store implements IStore {

    // Final lists
    public static List<Item> itemList;
    public static List<Staff> staffList;

    //Command booleans
    public static boolean exitCommandMenu;

    // Constructor
    public Store() {
        // Trigger take action
        takeAction();

        // Initialize Keyboard
        Keyboard keyboard = new Keyboard();
        exitCommandMenu = false;
    }

    // Methods
    @Override
    public void takeAction()
    {
        // Create the lists
        saveItemsFromFile();
        saveStaffFromFile();
        FileUtils file = new FileUtils();
        try
        {
            //Grab command lines
            List<String> commandList = FileUtils.readCommandsFromFile();

            for (String commandLine: commandList)
            {
                if (commandLine.substring(0,3).equals("ADD")) // ADD command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.ADD(name, Double.parseDouble(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));
                }
                else if (commandLine.substring(0,3).equals("COS")) // COST command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.COST(name);
                }
                else if (commandLine.substring(0,3).equals("EXI")) // EXIT command
                {
                    file.EXIT();
                }
                else if (commandLine.substring(0,3).equals("FIN")) // FIND command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.FIND(name);
                }
                else if (commandLine.substring(0,3).equals("FIR")) // FIRE command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.FIRE(name);
                }
                else if (commandLine.substring(0,3).equals("HIR")) // HIRE command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.HIRE(name, Integer.parseInt(split[2]), split[3], split[4]);
                }
                else if(commandLine.substring(0,3).equals("PRO")) // PROMOTE command
                {

                }
                else if(commandLine.substring(0,3).equals("SAW")) // SAW command
                {
                    file.SAW();
                }
                else if(commandLine.substring(0,3).equals("SCH")) // SCHEDULE command
                {
                    file.SCHEDULE();
                }
                else if(commandLine.substring(0,3).equals("SEL")) // SELL command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.SELL(name, Integer.parseInt(split[2]));
                }
                else if(commandLine.substring(0,3).equals("QUA")) // QUANTITY command
                {
                    String[] split = commandLine.split(" ");
                    String name = split[1].substring(1, split[1].length() - 1);
                    file.QUANTITY(name);
                }
            }


        }
        catch (IOException e)
        {
            System.exit(0);
        }
    }


    @Override
    public List<Item> getItemsFromFile() {
        try {
            List<Item> inList = FileUtils.readInventoryFromFile();
            return inList;
        } catch (IOException e) {
            System.exit(0);
            return null;
        }
    }

    @Override
    public List<Staff> getStaffFromFile() {
        try {
            List<Staff> inList = FileUtils.readStaffFromFile();
            return inList;
        } catch (IOException e) {
            System.exit(0);
            return null;
        }
    }

    @Override
    public void saveItemsFromFile() {
        itemList = getItemsFromFile();
    }

    @Override
    public void saveStaffFromFile() {
        staffList = getStaffFromFile();
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