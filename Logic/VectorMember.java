package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorMember
{
    private Vector<Member> vMember; //Creates vector for Member class
    
    public VectorMember() //Constructor creates instance of vector vMember
    {
        vMember = new Vector<Member>();
    }       
    
    public void addMember(Member member) //Add member to vector vMember
    {
        vMember.add(member);
    }
    
    public void removeMember(Member member) //Remove member from vector vMember
    {
        vMember.remove(member);
    }
    
    public void clearVector() //Remove ALL members from vector vMember
    {
        vMember.clear();
    }
    
    public void printVMember() //Print all members from vector vMember
    {
        for (Member tempMember : vMember){
            System.out.println(tempMember.toString());
        }
    }
    
    public int getSize() //Returns the number of members stored in vector vMember
    {
        return vMember.size();
    }    
        
    public boolean checkMemberID(String searchID) //Returns true if a peron with the same memberId already exists in vector vMembers
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Member tempMember : vMember) //Will compare tempMember to with all instances of member saved in vector vMember
        {
            if(tempMember.getPersonId().equals(searchID)) //Uses method getMemberId() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Member getMemberByID(String searchID) //Returns the member whose ID matches that which is inputted here
    {
        Member foundMember = new Member();
        for(Member tempMember : vMember) //Will compare tempMember to with all instances of member saved in vector vMember
        {
            if(tempMember.getPersonId().equals(searchID)) //Uses method getMemberId() to compare with parameter to search for matching ID
            {
                foundMember = tempMember; //Result saved in foundMember object
            }  
        }    
        return foundMember; //Returns the result
    }       
    
    public void readFromFile() //Reads data from file
    {
        try
        {
            File f = new File("vMemberFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vMember = (Vector<Member>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe)
        {
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe)
        {
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Member class cannot be found!"); //Prints error message if Member class cannot be found
        }
    }    
    
    public void saveToFile() //Saves data to a file
    {
        try
        {
            File f = new File("vMemberFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vMember);
            oos.flush();
            oos.close();
        }catch(IOException ioe)
        {
            System.err.println("Cannot write to file!");
        }
    }
                
    public Member getMemberByIndex(int i) //Returns member by their position in the vector
    {
        Member tempMember = new Member();
        if (i < getSize())
        {
            tempMember = vMember.get(i);
        }    
        return tempMember;
    }    
    
    public void sortMemberBySurname() //Sorts vector by member's surname
    {
        Member currentMember = new Member();
        Member nextMember = new Member();
        Member tempMember = new Member();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentMember = vMember.elementAt(j);
                nextMember = vMember.elementAt(j+1);
                if(currentMember.getSurname().equals(nextMember.getSurname()))
                {
                    tempMember = currentMember;
                    vMember.setElementAt(nextMember, j);
                    vMember.setElementAt(tempMember, j+1);
                }   
            }    
        }       
    }
}