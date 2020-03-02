package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Person implements Serializable
{
    //Initilisation of variables
    private String personId;
    private String name;
    private String surname;
    private Date dateOfBirth;
   
    public Person() //Parameterless constructor
    {
    }   
    public Person(String personId, String name, String surname, Date dateOfBirth) //Creates object of type Person with parameters
    {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }    
    
    public void setPersonId(String personId) //Sets value of String personId
    {
        this.personId = personId;
    }   
    public String getPersonId() //Returns String personId
    {
        return personId;
    }   
    
    public void setName(String name) //Sets value of String Name
    {
        this.name = name;
    }
    public String getName() //Returns value of String name
    {
        return name;
    }   
    
    public void setSurname(String surname) //Sets value of String Surname
    {
        this.surname = surname;
    }   
    public String getSurname() //Returns value of String surname
    {
        return surname;
    }   
    
    public void setDateOfBirth(Date dateOfBirth) //Sets value of Date date of birth
    {
        this.dateOfBirth = dateOfBirth;
    }
    public Date getDateOfBirth() //Returns value of date of birth as Date object
    { 
        return dateOfBirth;
    }
    
    public String toString() //Returns all variable values
    {
        return "PersonID: " + personId + "\nName: " + name + "\nSurname: " + surname + "\nDate of Birth: " + dateOfBirth;
    }   
}
