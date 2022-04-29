package edu.iu.c212;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static edu.iu.c212.Store.inputFile;
import static edu.iu.c212.utils.FileUtils.inputFile;

public class StoreMain {
    public static void main(String[] args) throws FileNotFoundException {
        Store store = new Store();

        //Instantiate and manage store object here
    }

    public static void readFileFunctions() throws FileNotFoundException {
        Scanner in = new Scanner(inputFile);
        while (in.hasNextLine()) ;
        if (in.next().equals("ADD")) {

        } else if (in.next().equals("COST")) {
        } else if (in.next().equals("EXIT")) {

        } else if (in.next().equals("FIND")) {

        } else if (in.next().equals("FIRE")) {

        } else if (in.next().equals("HIRE")) {

        }
        else if(in.next().equals("PROMOTE")){

        }
        else if(in.next().equals("SAW")){

        }
        else if(in.next().equals("SCHEDULE")){

        }
        else if(in.next().equals("SELL")){

        }
        else if(in.next().equals("QUANTITY")){

        }
    }
}
