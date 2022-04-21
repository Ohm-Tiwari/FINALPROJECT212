package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;

import java.util.List;

public class Store implements IStore
{
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
