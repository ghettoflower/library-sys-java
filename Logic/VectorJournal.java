package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorJournal
{
    private Vector<Journal> vJournal; //Creates vector for class Journal
        
    public VectorJournal()
    {
        vJournal = new Vector<Journal>();
    }       
    
    public void addJournal(Journal journal) //Add journal to vector vJournal
    {
        vJournal.add(journal);
    }
    
    public void removeJournal(Journal journal) //Remove journal from vector vJournal
    {
        vJournal.remove(journal);
    }
    
    public void clearVector() //Remove ALL journal from vector vJournal
    {
        vJournal.clear();
    }
    
    public void printVJournal() //Print all journal from vector vJournal
    {
        for (Journal tempJournal : vJournal){
            System.out.println(tempJournal.toString());
        }
    }
    
    public int getSize() //Returns the number of journal stored in vector vJournal
    {
        return vJournal.size();
    }
    
    public boolean checkJournalID(String searchID) //Returns true if an journal with the same JournalID already exists
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Journal tempJournal : vJournal) //Will compare tempJournal to with all instances of journal saved in vector vJournal
        {
            if(tempJournal.getItemID().equals(searchID)) //Uses method getJournalID() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Journal getJournalByID(String searchID) //Returns the journal whose ID matches that which is inputted
    {
        Journal foundJournal = new Journal();
        for(Journal tempJournal : vJournal) //Will compare tempJournal to with all instances of journal saved in vector vJournal
        {
            if(tempJournal.getItemID().equals(searchID)) //Uses method getJournalID() to compare with parameter to search for matching ID
            {
                foundJournal = tempJournal; //Result saved in foundJournal object
            }  
        }    
        return foundJournal; //Returns the result
    }   
    
    public void readFromFile() //Reads data from file
    {
        try
        {
            File f = new File("vJournalFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vJournal = (Vector<Journal>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe)
        {
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe)
        {
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Journal class cannot be found!"); //Prints error message if Journal class cannot be found
        }
    }    
    
    public void saveToFile()//Saves data to a file
    {
        try
        {
            File f = new File("vJournalFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vJournal);
            oos.flush();
            oos.close();
        }catch(IOException ioe)
        {
            System.err.println("Cannot write to file!");
        }
    }
                
    public Journal getJournalByIndex(int i) //Returns Journal by their position in the vector
    {
        Journal tempJournal = new Journal();
        if (i < getSize())
        {
            tempJournal = vJournal.get(i);
        }    
        return tempJournal;
    }    
    
    public void sortJournalByAuthorSurname() //Sorts the vector by the date of each journal
    {
        Journal currentJournal = new Journal();
        Journal nextJournal = new Journal();
        Journal tempJournal = new Journal();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentJournal = vJournal.elementAt(j);
                nextJournal = vJournal.elementAt(j+1);
                if(currentJournal.getAuthorSurname().equals(nextJournal.getAuthorSurname()))
                {
                    tempJournal = currentJournal;
                    vJournal.setElementAt(nextJournal, j);
                    vJournal.setElementAt(tempJournal, j+1);
                }   
            }    
        }   
    }       
     
} 