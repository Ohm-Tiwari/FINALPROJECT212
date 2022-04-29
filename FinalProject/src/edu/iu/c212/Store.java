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
    public void takeAction() {
        // Create the lists
        saveItemsFromFile();
        saveStaffFromFile();
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