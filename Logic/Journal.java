package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Journal extends Item implements Serializable
{
    private String areaStudy; //Saves area of study of journal (ex: Physics, Biology) as String
    
    public Journal() //Parameterless constructor
    {
    }
    public Journal(String title, String itemID, String authorName, String authorSurname, Date releaseDate, String publisher, String areaStudy) //Constructor with parameters from mother class and String areaStudy
    {
        super(title, itemID, authorName, authorSurname, releaseDate, publisher);
        this.areaStudy = areaStudy;
    }
    
    public void setAreaOfStudy(String areaStudy) //Sets value of area of study as String
    {
        this.areaStudy = areaStudy;
    }
    public String getAreaOfStudy() //Returns value of String areaStudy
    {
        return areaStudy;
    }    
    
    public String toString() //Returns all values as String
    {
        return super.toString() + "\nArea of Study" + areaStudy;
    }   
}
