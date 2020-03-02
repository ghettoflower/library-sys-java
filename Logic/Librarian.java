package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Librarian extends Person implements Serializable
{
    private String address; //Address of Librarian saved in String
    
    public Librarian() //Parameterless constructor
    {
    }      
    public Librarian(String personId, String name, String surname, Date dateOfBirth, String address) //Constructor with parameters from mother class and String address
    {
        super(personId, name, surname, dateOfBirth); //Sets variables from mother class
        this.address = address;
    }
    
    public void setAddress(String address) //Sets value of String address
    {
        this.address = address;
    }
    public String getAddress() //Returns value of String address
    {
        return address;
    }
    
    public String toString() //Returns all variable values
    {
        return super.toString() + "\nAddress: " + address;
    }     
}
