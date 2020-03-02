package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorLibrarian
{
    private Vector<Librarian> vLibrarian; //Creates vector for Librarian class
    
    public VectorLibrarian() //Constructor creates instance of vector vLibrarian
    {
        vLibrarian = new Vector<Librarian>();
    }       
    
    public void addLibrarian(Librarian librarian) //Add librarian to vector vLibrarian
    {
        vLibrarian.add(librarian);
    }
    
    public void removeLibrarian(Librarian librarian) //Remove librarian from vector vLibrarian
    {
        vLibrarian.remove(librarian);
    }
    
    public void clearVector() //Remove ALL librarians from vector vLibrarian
    {
        vLibrarian.clear();
    }
    
    public void printVLibrarian() //Print all librarians from vector vLibrarian
    {
        for (Librarian tempLibrarian : vLibrarian){
            System.out.println(tempLibrarian.toString());
        }
    }
    
    public int getSize() //Returns the number of librarians stored in vector vLibrarian
    { 
        return vLibrarian.size();
    }    
        
    public boolean checkLibrarianID(String searchID) //Returns true if a peron with the same librarianId already exists in vector vLibrarians
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Librarian tempLibrarian : vLibrarian) //Will compare tempLibrarian to with all instances of librarian saved in vector vLibrarian
        {
            if(tempLibrarian.getPersonId().equals(searchID)) //Uses method getLibrarianId() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Librarian getLibrarianByID(String searchID) //Returns the librarian whose ID matches that which is inputted here
    {
        Librarian foundLibrarian = new Librarian();
        for(Librarian tempLibrarian : vLibrarian) //Will compare tempLibrarian to with all instances of librarian saved in vector vLibrarian
        {
            if(tempLibrarian.getPersonId().equals(searchID)) //Uses method getLibrarianId() to compare with parameter to search for matching ID
            {
                foundLibrarian = tempLibrarian; //Result saved in foundLibrarian object
            }  
        }    
        return foundLibrarian; //Returns the result
    }       
    
    public void readFromFile() //Reads data from file
    {
        try
        {
            File f = new File("vLibrarianFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vLibrarian = (Vector<Librarian>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe)
        {
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe)
        {
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Librarian class cannot be found!"); //Prints error message if Librarian class cannot be found
        }
    }    
    
    public void saveToFile() //Saves data to a file
    {
        try
        {
            File f = new File("vLibrarianFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vLibrarian);
            oos.flush();
            oos.close();
        }catch(IOException ioe)
        {
            System.err.println("Cannot write to file!");
        }
    }
                
    public Librarian getLibrarianByIndex(int i) //Returns librarian by their position in the vector
    {
        Librarian tempLibrarian = new Librarian();
        if (i < getSize())
        {
            tempLibrarian = vLibrarian.get(i);
        }    
        return tempLibrarian;
    }    
    
    public void sortLibrarianBySurname() //Sorts vector by librarian's surname
    {
        Librarian currentLibrarian = new Librarian();
        Librarian nextLibrarian = new Librarian();
        Librarian tempLibrarian = new Librarian();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentLibrarian = vLibrarian.elementAt(j);
                nextLibrarian = vLibrarian.elementAt(j+1);
                if(currentLibrarian.getSurname().equals(nextLibrarian.getSurname()))
                {
                    tempLibrarian = currentLibrarian;
                    vLibrarian.setElementAt(nextLibrarian, j);
                    vLibrarian.setElementAt(tempLibrarian, j+1);
                }   
            }    
        }       
    }
}