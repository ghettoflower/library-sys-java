package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Loan implements Serializable
{
    //Initialisation of variables
    private String loanId;
    private Date rentalDate;
    private Date returnDate;
    private String librarianID;
    private String membID;
    private String bookID;
    
    public Loan() //Parameterless Constructor
    {
    }   
    public Loan(String loanId, Date rentalDate, Date returnDate, String librarianID, String membID, String bookID) //Constructor with parameters
    {
        this.loanId = loanId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.librarianID = librarianID;
        this.membID = membID;
        this.bookID = bookID;
    }   
    
    public void setLoanId(String loanId) //Sets value of loanId as String
    {
        this.loanId = loanId;
    }
    public String getLoanId() //Returns value of String loanId
    {
        return loanId;
    }  
    
    public void setRentalDate(Date rentalDate) //Sets value of rentalDate as Date object
    {
        this.rentalDate = rentalDate;
    }   
    public Date getRentalDate() //Returns value of Date rentalDate
    {
        return rentalDate;
    }   
    
    public void setReturnDate(Date returnDate) //Sets value of returnDate as Date  object
    {
        this.returnDate = returnDate;
    }    
    public Date getReturnDate() //Returns value of Date returnDate
    {
        return returnDate;
    }   
    
    public void setLibrarianID(String librarianID) //Sets librarian as String
    {
        this.librarianID = librarianID;
    }
    public String getLibrarianID() //Returns String librarian
    {
        return librarianID;
    }    
    
    public void setMembID(String membID) //Sets member ID as String
    {
        this.membID = membID;
    }
    public String getMembID() //Returns String memID
    {
        return membID;
    }  
    
    public void setBookID(String bookID) //Sets bookID as String
    {
        this.bookID = bookID;
    }    
    public String getBookID() //Returns String bookID
    {
        return bookID;
    }   
    
    public String toString() //Returns all values as String
    {
        return "\nLoanID: " + loanId + "\nRental Date: " + rentalDate + "\nReturn Date: " + returnDate + "\nBook ID: " + bookID + "\nLibrarian ID: " + librarianID + "\nMember ID: " + membID;
    }    
}
