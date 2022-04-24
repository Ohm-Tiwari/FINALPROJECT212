package edu.iu.c212;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.Staff;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;

public interface IStore
{
    // Abstract Methods
    List<Item> getItemsFromFile(File inputFile) throws FileNotFoundException;
    List<Staff> getStaffFromFile(File inputFile) throws FileNotFoundException;
    void saveItemsFromFile(File inputFile) throws FileNotFoundException;
    void saveStaffFromFile(File inputFile) throws  FileNotFoundException;
    void takeAction() throws FileNotFoundException;
}
