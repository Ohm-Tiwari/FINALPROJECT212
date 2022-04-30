package edu.iu.c212.programs;

import edu.iu.c212.models.Item;

import javax.swing.*;
import java.awt.*;

public class StoreMapDisplay
{
    // Instance
    protected static int WIDTH;
    protected static int HEIGHT;

    //If they want the store map to be fully static, may need to add some sort of arraylist to keep track of them during runtime, and iteratively set and display them - Josh

    // Methods
    public static void display(Item product)
    {
        JFrame mainFrame = new JFrame("High's Hardware Product Lookup: " + product);

        mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Add content pane
        StoreMap storeMapDisplayComponent = new StoreMap(product.getAisle());
        mainFrame.setContentPane(storeMapDisplayComponent);

        // Set it's size
        storeMapDisplayComponent.setPreferredSize(new Dimension(WIDTH/2, HEIGHT/2));

        // Compile and show
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


}
