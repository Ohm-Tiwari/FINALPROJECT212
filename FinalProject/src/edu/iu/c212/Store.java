package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;
import edu.iu.c212.programs.StoreMap;
import edu.iu.c212.programs.StoreMapDisplay;
import edu.iu.c212.utils.FileUtils;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

        try
        {
            //Grab command lines
            List<String> commandList = FileUtils.readCommandsFromFile();

            for (String commandLine: commandList)
            {
                if (commandLine.substring(0,3).equals("ADD")) // ADD command
                {
                    String[] split = commandLine.split(" ");
                }
                else if (commandLine.substring(0,3).equals("COS")) // COST command
                {

                }
                else if (commandLine.substring(0,3).equals("EXI")) // EXIT command
                {

                }
                else if (commandLine.substring(0,3).equals("FIN")) // FIND command
                {

                }
                else if (commandLine.substring(0,3).equals("FIR")) // FIRE command
                {

                }
                else if (commandLine.substring(0,3).equals("HIR")) // HIRE command
                {

                }
                else if(commandLine.substring(0,3).equals("PRO")) // PROMOTE command
                {

                }
                else if(commandLine.substring(0,3).equals("SAW")) // SAW command
                {

                }
                else if(commandLine.substring(0,3).equals("SCH")) // SCHEDULE command
                {

                }
                else if(commandLine.substring(0,3).equals("SEL")) // SELL command
                {

                }
                else if(commandLine.substring(0,3).equals("QUA")) // QUANTITY command
                {

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