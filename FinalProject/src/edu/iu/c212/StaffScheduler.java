package edu.iu.c212;

import edu.iu.c212.models.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffScheduler
{


    static String fName;
    static String lName;
    static int age;
    static String role;
    static String availability;

    public static void main(String[] args) throws FileNotFoundException {
        createStaffList();
    }
    // Methods
    public void ScheduleStaff()
    {


    }
    public static ArrayList<Staff> createStaffList() throws FileNotFoundException {

        ArrayList<Staff> inList = new ArrayList<>();
        File inputFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
        Scanner in = new Scanner(inputFile);


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
                inList.add(new Staff((fName + " " + lName), age, role, availability));
                index = 0;
            }
        }
        return inList;
    }


}
