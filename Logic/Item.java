package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Item implements Serializable
{
    //Initialisation of variables
    private String title;
    private String itemID;
    private String authorName;
    private String authorSurname;
    private Date releaseDate;
    private String publisher;
    
    public Item() //Parameterless constructor
    {
    }
    public Item(String title, String itemID, String authorName, String authorSurname, Date releaseDate, String publisher) //Constructor with parameters
    {
        this.title = title;
        this.itemID = itemID;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
    }    
    
    public void setTitle(String title) //Sets value of title as String
    {
        this.title = title;
    }
    public String getTitle() //Returns value of String title
    {
        return title;
    }
    
    public void setItemID(String itemID) //Sets value of itemID as String
    {
        this.itemID = itemID;
    }
    public String getItemID() //Returns value of String itemID
    {
        return itemID;
    }   
    
    public void setAuthorName(String authorName) //Sets value of authorName as String
    {
        this.authorName = authorName;
    }
    public String getAuthorName() //Returns authorName
    {
        return authorName;
    }
    
    public void setAuthorSurname(String authorSurname) //Sets value of authorSurname as String
    {
        this.authorSurname = authorName;
    }
    public String getAuthorSurname() //Returns authorSurname
    {
        return authorSurname;
    }    
    
    public void setReleaseDate(Date releaseDate) //Sets value of releaseDate as Date
    {
        this.releaseDate = releaseDate;
    }
    public Date getReleaseDate() //Returns value of Date releaseDate
    {
        return releaseDate;
    }
    
    public void setPublisher(String publisher) //Sets value of publisher as String
    {
        this.publisher = publisher;
    }
    public String getPublisher() //Returns value of String publisher
    {
        return publisher;
    }
    
    public String toString() //Returns all values as String
    {
        return "\nTitle: " + title + "\nItem ID: " + itemID + "\nAuthor: " + authorName + " " + authorSurname + "\nRelease Date: " + releaseDate + "\nPublisher: " + publisher;
    }    
}
