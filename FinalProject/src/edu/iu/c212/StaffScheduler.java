package edu.iu.c212;

import edu.iu.c212.models.Staff;

import java.io.*;
import java.util.*;

import static edu.iu.c212.Store.staffList;

public class StaffScheduler
{

    // Methods
    public void ScheduleStaff() throws FileNotFoundException, IOException
    {
        List<Staff> schedules = getSchedules();

        File availabilityFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
        Scanner availabilityIn = new Scanner(availabilityFile);

        File scheduleInFile = new File("src/edu/iu/c212/resources/staff_schedules_IN.txt");
        Scanner scheduleInFileIn = new Scanner(scheduleInFile);

        File scheduleOutFile = new File("src/edu/iu/c212/resources/staff_availability_OUT.txt");
        Writer scheduleWriter = new FileWriter(scheduleOutFile);

    }

    public List<Staff> getSchedules() {
        List<Staff> inList = staffList;
        List<Staff> schedules = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            schedules.add(new Staff(inList.get(i).getName(), inList.get(i).getAge(), inList.get(i).getRole(),
                    inList.get(i).getAvailability(), false));
        }
        return schedules;
    }

    public static Comparator<Staff> scheduleSorter = new Comparator<Staff>() {
        @Override
        public int compare(Staff o1, Staff o2) {
            return getNumOfDaysWorkable(o2.getAvailability()) - getNumOfDaysWorkable(o1.getAvailability());
        }
    };

    public void testComparator(){

        List<Staff> inList = staffList;
        inList.sort(scheduleSorter);
        for (int i = 0; i < inList.size(); i++) {
            System.out.println(inList.get(i).getName());
            System.out.println(inList.get(i).getAge());
            System.out.println(inList.get(i).getRole());
            System.out.println(inList.get(i).getAvailability());

        }
    }


    public static int getNumOfDaysWorkable(String input) {
        String test = input;
        int count = 0;
        String[] tokens = test.split("\\.");
        for (int i = 0; i < tokens.length; i++) {
            count++;
        }
        return count;
    }
}
