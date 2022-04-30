package edu.iu.c212.programs;

import edu.iu.c212.models.Staff;

import java.io.*;
import java.util.*;

import static edu.iu.c212.Store.staffList;
import static edu.iu.c212.Store.storeScheduleOutputFile;

public class StaffScheduler
{

    // Methods
    public void ScheduleStaff()
    {
        List<Staff> schedules = getSchedules();

        List<Staff> mon = new ArrayList<>();
        List<Staff> tues = new ArrayList<>();
        List<Staff> wed = new ArrayList<>();
        List<Staff> thur = new ArrayList<>();
        List<Staff> fri = new ArrayList<>();
        List<Staff> sat = new ArrayList<>();
        List<Staff> sun = new ArrayList<>();

        File storeScheduleFile = new File("../resources/store_schedule_OUT.txt");
        Writer scheduleWriter = new FileWriter(storeScheduleFile);

        List<Staff> staffList = getSchedules();

        //sorting all the staff by how few days they can work
        Collections.sort(staffList, StaffScheduler.StaffSorter);

        // creating arraylist of staff that can work on monday
        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("M")){
                mon.add(staffList.get(i));
            }
        }
        //picking the workers with the fewest amount of workable days and printing them
        scheduleWriter.write("M ");
        for (int i = 0; i < mon.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + mon.get(i) + ") ");
            }

        }

        //creating array of workers that can work on tuesdays
        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("T.")){
                tues.add(staffList.get(i));
            }
        }
        //sorting them such that people who have worked the most hours go in the back
        Collections.sort(tues, StaffScheduler.HourSorter);

        scheduleWriter.write(System.getProperty("line.separator") + "T ");
        for (int i = 0; i < tues.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + tues.get(i) + ") ");
                tues.get(i).addHours(9);
            }
        }

        // creating array of workers that can work on wednesdays
        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("W")){
                wed.add(staffList.get(i));
            }
        }
        Collections.sort(wed, StaffScheduler.HourSorter);

        scheduleWriter.write(System.getProperty("line.separator") + "W ");

        //picking the workers with the fewest amount of workable days and printing them
        for (int i = 0; i < wed.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + wed.get(i) + ") ");
                wed.get(i).addHours(9);
            }
        }


        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("TR")){
                thur.add(staffList.get(i));
            }
        }
        Collections.sort(thur, StaffScheduler.HourSorter);
        scheduleWriter.write(System.getProperty("line.separator") + "TR ");
        for (int i = 0; i < thur.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + thur.get(i) + ") ");
                thur.get(i).addHours(9);
            }
        }


        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("F")){
                fri.add(staffList.get(i));
            }
        }
        Collections.sort(fri, StaffScheduler.HourSorter);
        scheduleWriter.write(System.getProperty("line.separator") + "F ");
        for (int i = 0; i < fri.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + fri.get(i) + ") ");
                fri.get(i).addHours(9);
            }
        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("SAT")){
                sat.add(staffList.get(i));
            }
        }
        Collections.sort(sat, StaffScheduler.HourSorter);
        scheduleWriter.write(System.getProperty("line.separator") + "SAT ");
        for (int i = 0; i < sat.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + sat.get(i) + ") ");
                sat.get(i).addHours(12.5);
            }
        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getAvailability().contains("SUN")){
                sun.add(staffList.get(i));
            }
        }
        Collections.sort(sun, StaffScheduler.HourSorter);
        scheduleWriter.write(System.getProperty("line.separator") + "SUN ");
        for (int i = 0; i < sun.size(); i++) {
            if (i >= 3){
                break;
            }
            else{
                scheduleWriter.write("(" + sun.get(i) + ") ");
                sun.get(i).addHours(12.5);
            }
        }
        scheduleWriter.close();
    }

    public List<Staff> getSchedules() {
        List<Staff> inList = staffList;
        List<Staff> schedules = new ArrayList<>();
        for (int i = 0; i < inList.size(); i++) {
            schedules.add(new Staff(inList.get(i).getName(), inList.get(i).getAge(), inList.get(i).getRole(),
                    inList.get(i).getAvailability(), 0));
        }
        return schedules;
    }

    public static Comparator<Staff> StaffSorter = new Comparator<Staff>() {
        @Override
        public int compare(Staff o1, Staff o2) {
            String sSched1 = o1.getAvailability();
            String sSched2 = o2.getAvailability();
            return getNumOfDaysWorkable(sSched1) - getNumOfDaysWorkable(sSched2);
        }
    };

    public static Comparator<Staff> HourSorter = new Comparator<Staff>() {
        @Override
        public int compare(Staff o1, Staff o2) {
            double h1 = o1.getHoursWorked();
            double h2 = o2.getHoursWorked();
            int value = (int)(h1 - h2);
            return value;
        }
    };


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
