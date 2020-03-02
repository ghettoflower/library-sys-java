package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Book extends Item implements Serializable
{  
    private String genre; //Holds genre of book
    
    public Book() //Parameterless constructor
    {
    }   
    public Book(String title, String itemID,  String authorName, String authorSurname, Date releaseDate, String publisher, String genre) //Constructor with parameters from mother class and String genre
    {
        super(title, itemID, authorName, authorSurname, releaseDate, publisher);
        this.genre = genre;
    }
    
    public void setGenre(String genre) //Sets value of String genre
    {
        this.genre = genre;
    }
    public String getGenre() //Returns value of String genre
    {
        return genre;
    }   
    
    public String toString() //Returns all values as string
    {
        return super.toString() + "\nGenre: " + genre;
    }    
}
