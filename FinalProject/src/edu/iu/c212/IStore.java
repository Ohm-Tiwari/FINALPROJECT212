package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import edu.iu.c212.utils.FileUtils;

public interface IStore
{
    // Abstract Methods
    List<Item> getItemsFromFile();
    List<Staff> getStaffFromFile();
    void saveItemsFromFile();
    void saveStaffFromFile();
    void takeAction();
}
