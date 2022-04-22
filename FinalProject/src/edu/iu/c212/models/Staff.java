package edu.iu.c212.models;

public class Staff
{
    // Instance variables
    private String fullName;
    private int age;
    private String role;
    private String availability;

    // Constructor
    public Staff(String fullName, int age, String role, String availability)
    {
        this.fullName = fullName;
        this.age = age;
        this.role = role;
        this.availability = availability;

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

    public void setRole(String role){
        this.role = role;
    }

}
