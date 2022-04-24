package edu.iu.c212.models;

public class Staff
{
    // Instance variables
    private String fullName;
    private int age;
    private String role;
    private String availability;
    private boolean worked;

    // Constructor
    public Staff(String fullName, int age, String role, String availability)
    {
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;
    }
    public Staff(String fullName, int age, String role, String availability, boolean worked){
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;
        this.worked = worked;
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

    public boolean getWorked() { return worked; }

    public void setRole(String role){
        this.role = role;
    }

}
