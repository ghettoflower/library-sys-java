package Logic; //Class is located in the package Logic
import java.util.Date; //Imports Date API
import java.io.*; //Used to import serializable

public class Member extends Person implements Serializable
{
    //Initilisation of variables
    private String memberType; //memberType can be Bronze (1 month), Silver (6 months), Gold (1 year) or Platinum (lifetime)
    private String address; //Holds member's address
    private Date dateMemberStart; //Holds the date of when the membership was purchased
    private Date dateMemberEnd; //Holds the date of when the membership will end
    
    public Member() //Parameterless constructor
    {  
    }
    public Member(String personId, String name, String surname, Date dateOfBirth, String memberType, String address, Date dateMemberStart, Date dateMemberEnd) //Constructor of variables from mother class + memberType, address, dateMemberStart, dateMmberEnd
    {
        super(personId, name, surname, dateOfBirth); //Sets variables from mother class
        this.memberType = memberType;
        this.address = address;
        this.dateMemberStart = dateMemberStart;
        this.dateMemberEnd = dateMemberEnd;
    }    
    
    public void setMemberType(String memberType) //Sets value of memberType as String
    {
        this.memberType = memberType;
    }
    public String getMemberType() //Returns value of String memberType
    {
        return memberType;
    }
    
    public void setAddress(String address) //Sets value of address as String
    {
        this.address = address;
    }
    public String getAddress() //Returns value of String address
    {
        return address;
    }
    
    public void setDateMemberStart(Date dateMemberStart) //Sets date of start of membership as Date object
    {
        this.dateMemberStart = dateMemberStart;
    }
    public Date getDateMemberStart() //Returns value of Date dateMemberStart
    {
        return dateMemberStart;
    }   
    
    public void setDateMemberEnd(Date dateMemberEnd) //Sets date of end of membership as Date object
    {
        this.dateMemberEnd = dateMemberEnd;
    }
    public Date getDateMemberEnd() //Returns value of Date dateMemberEnd
    {
        return dateMemberEnd;
    }   
    
    public String toString() //Returns all variable values as String
    {
        return super.toString() + "\nMembership Type: " + memberType + "\nAddress: " + address + "\nMembership Start Date: " + dateMemberStart + "\nMembership End Date: " + dateMemberEnd;
    }       
}
