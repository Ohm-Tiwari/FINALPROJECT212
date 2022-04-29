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

        List<String> mon = new ArrayList<>();
        List<String> tues = new ArrayList<>();
        List<String> wed = new ArrayList<>();
        List<String> thur = new ArrayList<>();
        List<String> fri = new ArrayList<>();
        List<String> sat = new ArrayList<>();
        List<String> sun = new ArrayList<>();

        File availabilityFile = new File("src/edu/iu/c212/resources/staff_availability_IN.txt");
        Scanner availabilityIn = new Scanner(availabilityFile);

        File scheduleInFile = new File("src/edu/iu/c212/resources/staff_schedules_IN.txt");
        Scanner scheduleInFileIn = new Scanner(scheduleInFile);

        File scheduleOutFile = new File("src/edu/iu/c212/resources/staff_availability_OUT.txt");
        Writer scheduleWriter = new FileWriter(scheduleOutFile);

        List<Staff> staffList = getSchedules();
        Collections.sort(staffList, StaffScheduler.StaffSorter);

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(mon.size() >= 2){
                break; //if monday already has workers
            }
            else if(staffList.get(i).getAvailability().contains("M")){
                mon.add(staffList.get(i).getName());
            }


        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(tues.size() >= 2){
                break; //if tues already has workers
            }
            else if(staffList.get(i).getAvailability().contains("T.")){
                tues.add(staffList.get(i).getName());
            }


        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(wed.size() >= 2){
                break; //if wed already has workers
            }
            else if(staffList.get(i).getAvailability().contains("W")){
                wed.add(staffList.get(i).getName());
            }

        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(thur.size() >= 2){
                break; //if thur already has workers
            }
            else if(staffList.get(i).getAvailability().contains("TR")){
                thur.add(staffList.get(i).getName());
            }

        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(fri.size() >= 2){
                break; //if thur already has workers
            }
            else if(staffList.get(i).getAvailability().contains("F")){
                fri.add(staffList.get(i).getName());
            }

        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(sat.size() >= 2){
                break; //if thur already has workers
            }
            else if(staffList.get(i).getAvailability().contains("SAT")){
                sat.add(staffList.get(i).getName());
            }

        }

        for (int i = 0; i < staffList.size(); i++) {
            if(staffList.get(i).getWorked() == true){
                //skip past this staff member
            }
            else if(sun.size() >= 2){
                break; //if thur already has workers
            }
            else if(staffList.get(i).getAvailability().contains("SUN")){
                sun.add(staffList.get(i).getName());
            }
        }

        scheduleWriter.write("M ");
        for (int i = 0; i < mon.size(); i++) {
            scheduleWriter.write("(" + mon.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "T ");
        for (int i = 0; i < tues.size(); i++) {
            scheduleWriter.write("(" + tues.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "W ");
        for (int i = 0; i < wed.size(); i++) {
            scheduleWriter.write("(" + wed.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "TR ");
        for (int i = 0; i < thur.size(); i++) {
            scheduleWriter.write("(" + thur.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "F ");
        for (int i = 0; i < fri.size(); i++) {
            scheduleWriter.write("(" + fri.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "SAT ");
        for (int i = 0; i < sat.size(); i++) {
            scheduleWriter.write("(" + sat.get(i) + ") ");

        }
        scheduleWriter.write(System.getProperty("line.separator") + "SUN ");
        for (int i = 0; i < sun.size(); i++) {
            scheduleWriter.write("(" + sun.get(i) + ") ");

        }


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

    public static Comparator<Staff> StaffSorter = new Comparator<Staff>() {
        @Override
        public int compare(Staff o1, Staff o2) {
            String sSched1 = o1.getAvailability();
            String sSched2 = o2.getAvailability();
            return getNumOfDaysWorkable(sSched1) - getNumOfDaysWorkable(sSched2);
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
