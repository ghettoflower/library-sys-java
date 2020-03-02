package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorAuthor
{
    private Vector<Author> vAuthor; //Creates vector for Author class
    
    public VectorAuthor() //Constructor creates instance of vector vAuthor
    {
        vAuthor = new Vector<Author>();
    }   
    
    public void addAuthor(Author author){ //Add author to vector vAuthor
        vAuthor.add(author);
    }
    
    public void removeAuthor(Author author){ //Remove author from vector vAuthor
        vAuthor.remove(author);
    }
    
    public void clearVector(){ //Remove ALL authors from vector vAuthor
        vAuthor.clear();
    }
    
    public void printVAuthor(){ //Print all authors from vector vAuthor
        for (Author tempAuthor : vAuthor){
            System.out.println(tempAuthor.toString());
        }
    }
    
    public int getSize(){ //Returns the number of authors stored in vector vAuthor
        return vAuthor.size();
    }    
        
    public boolean checkAuthorID(String searchID) //Returns true if an author with the same authorID already exists in vector vAuthors
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Author tempAuthor : vAuthor) //Will compare tempAuthor to with all instances of author saved in vector vAuthor
        {
            if(tempAuthor.getAuthorID().equals(searchID)) //Uses method getAuthorID() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Author getAuthorByID(String searchID) //Returns the author whose ID matches that which is inputted here
    {
        Author foundAuthor = new Author();
        for(Author tempAuthor : vAuthor) //Will compare tempAuthor to with all instances of author saved in vector vAuthor
        {
            if(tempAuthor.getAuthorID().equals(searchID)) //Uses method getAuthorID() to compare with parameter to search for matching ID
            {
                foundAuthor = tempAuthor; //Result saved in foundAuthor object
            }  
        }    
        return foundAuthor; //Returns the result
    }       
    
    public void readFromFile() //Reads data from file
    {
        try{
            File f = new File("vAuthorFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vAuthor = (Vector<Author>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe){
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe){
            System.err.println("Author class cannot be found!"); //Prints error message if Author class cannot be found
        }
    }    
    
    public void saveToFile() //Saves data to a file
    {
        try{
            File f = new File("vAuthorFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vAuthor);
            oos.flush();
            oos.close();
        }catch(IOException ioe){
            System.err.println("Cannot write to file!");
        }
    }
                
    public Author getAuthorByIndex(int i) //Returns author by their position in the vector
    {
        Author tempAuthor = new Author();
        if (i < getSize())
        {
            tempAuthor = vAuthor.get(i);
        }    
        return tempAuthor;
    }    
    
    public void sortAuthorBySurname() //Sorts vector by author's surname
    {
        Author currentAuthor = new Author();
        Author nextAuthor = new Author();
        Author tempAuthor = new Author();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentAuthor = vAuthor.elementAt(j);
                nextAuthor = vAuthor.elementAt(j+1);
                if(currentAuthor.getSurname().equals(nextAuthor.getSurname()))
                {
                    tempAuthor = currentAuthor;
                    vAuthor.setElementAt(nextAuthor, j);
                    vAuthor.setElementAt(tempAuthor, j+1);
                }   
            }    
        }       
    }
}