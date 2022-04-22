package edu.iu.c212.models;

public class Staff
{
    // Instance variables
    private String fullName;
    private int age;
    private String role;

    // Constructor
    public Staff(String fullName, int age, String role)
    {
        this.fullName = fullName;
        this.age = age;
        this.role = role;

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

    public void setRole(String role){
        this.role = role;
    }

}
