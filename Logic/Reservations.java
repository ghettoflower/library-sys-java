package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Reservations implements Serializable
{
    //Initialisation of variables
    private Date resDate; //Date item was reserved, stored as Date object
    private String journalID;
    private String resID;
    private String memID;
    
    public Reservations() //Parameterless constructor
    {
    }
    public Reservations(Date resDate, String journalID, String resID, String memID)  //Constuctor with parameters
    {
        this.resDate = resDate;
        this.journalID = journalID;
        this.resID = resID;
        this.memID = memID;
    }  
    
    public void setJournalID(String journalID) //Sets value of journalID as String
    {
        this.journalID = journalID;
    }    
    public String getJournalID() //Returns value of String journalID
    {
        return journalID;
    }           
    
    public void setResDate(Date resDate) //Sets value of resDate as Date object
    {
        this.resDate = resDate;
    }    
    public Date getResDate() //Returns value of Date resDate
    {
        return resDate;
    }   
    
    public void setResID(String resID) //Sets value of resID as String
    {
        this.resID = resID;
    }    
    public String getResID() //Returns value of String resID
    {
        return resID;
    }       
    
    public void setMemID(String memID) //Sets value of memID as String
    {
        this.memID = memID;
    }    
    public String getMemID() //Returns value of String memID
    {
        return memID;
    }
    
    public String toString() //Returns all values as String
    {
        return "\nReservation Date: " + resDate + "\nJournal ID Reserved: " + journalID + "\nReservation ID: " + resID;
    }   
}
