package edu.iu.c212.models;


public class Staff
{
    // Instance variables
    private String fullName;
    private int age;
    private String role;
    private String availability;
    private double hoursWorked;

    // Constructor
    public Staff(String fullName, int age, String role, String availability)
    {
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;
    }

    public Staff(String fullName, int age, String role, String availability, double hoursWorked){
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;
        this.hoursWorked = hoursWorked;
    }

    // Methods
    public String getName()
    {
       return fullName;
    }

    public int getAge()
    {
        return age;
    }

    public String getRole()
    {
        return role;
    }

    public String getAvailability() { return availability; }

    public double getHoursWorked() { return hoursWorked; }

    public void addHours(double h) { hoursWorked =+ h; }

    public void setRole(String role){
        this.role = role;
    }



}
