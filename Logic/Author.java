package Logic; //Class is located in the package Logic
import java.io.*;

public class Author implements Serializable
{
    //Initialisation of variables
    private String name;
    private String surname;
    private String nationality;
    private int yearOfBirth;
    private int yearOfDeath;
    private String genre;
    private String authorID;
    
    public Author() //Parameterless constructor
    {
    }
    public Author(String name, String surname, String nationality, int yearOfBirth, int yearOfDeath, String genre, String authorID) //Constructors with parameters
    {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.genre = genre;
        this.authorID = authorID;
    }
    
    public void setName(String name) //Sets value of author's name as String
    {
        this.name = name;
    }
    public String getName() //Returns value of String name
    {
        return name;
    }
    
    public void setSurname(String surname) //Sets value of author's surname as String
    {
        this.surname = surname;
    }
    public String getSurname() //Returns value of String surname
    {
        return surname;
    }
    
    public void setNationality(String nationality)//Sets value of nationality as String
    {
        this.nationality = nationality;
    }
    public String getNationality() //Returns value of String nationality
    {
        return nationality;
    }
    
    public void setYearOfBirth(int yearOfBirth) //Sets value of birth year as int
    {
        this.yearOfBirth = yearOfBirth;
    }    
    public int getYearOfBirth() //Returns value of int yearOfBirth
    {
        return yearOfBirth;
    }    
    
    public void setYearOfDeath(int yearOfDeath) //Sets value of death year as int
    {
        this.yearOfDeath = yearOfDeath;
    }    
    public int getYearOfDeath() //Returns value of int yearOfDeath
    {
        return yearOfDeath;
    }        
    
    public void setGenre(String genre) //Sets value of genre as String
    {
        this.genre = genre;
    }
    public String getGenre() //Returns value of String genre
    {
        return genre;
    }
    
    public void setAuthorID(String authorID) //Sets value of authorID as String
    {
        this.authorID = authorID;
    }
    public String getAuthorID() //Returns value of String authorID
    {
        return authorID;
    }
    
    public String toString() //Returns all variable values as String
    {
        return "Name: " + name + "Surname: " + surname + "Nationality: " + nationality + "Birth Year: " + yearOfBirth + "Death Year: " + yearOfDeath + "Genre: " + genre + "AuthorID: " + authorID; 
    }    
}
