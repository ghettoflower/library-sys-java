package Logic;
import java.util.*;
import java.io.*;

public class VectorPerson
{
    private Vector<Person> vPerson; //Creates vector for Person class
    
    public void addPerson(Person person){ //Add person to vector vPerson
        vPerson.add(person);
    }
    
    public void removePerson(Person person){ //Remove person from vector vPerson
        vPerson.remove(person);
    }
    
    public void clearVector(){ //Remove ALL persons from vector vPerson
        vPerson.clear();
    }
    
    public void printVPerson(){ //Print all persons from vector vPerson
        for (Person tempPerson : vPerson){
            System.out.println(tempPerson.toString());
        }
    }
    
    public int getSize(){ //Returns the number of persons stored in vector vPerson
        return vPerson.size();
    }    
        
    public boolean checkPersonID(String searchID) //Returns true if a peron with the same personId already exists in vector vPersons
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Person tempPerson : vPerson) //Will compare tempPerson to with all instances of person saved in vector vPerson
        {
            if(tempPerson.getPersonId().equals(searchID)) //Uses method getPersonId() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Person getPersonByID(String searchID) //Returns the person whose ID matches that which is inputted here
    {
        Person foundPerson = new Person();
        for(Person tempPerson : vPerson) //Will compare tempPerson to with all instances of person saved in vector vPerson
        {
            if(tempPerson.getPersonId().equals(searchID)) //Uses method getPersonId() to compare with parameter to search for matching ID
            {
                foundPerson = tempPerson; //Result saved in foundPerson object
            }  
        }    
        return foundPerson; //Returns the result
    }       
    
    public void readFromFile() //Reads data from file
    {
        try{
            File f = new File("vPersonFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vPerson = (Vector<Person>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe){
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe){
            System.err.println("Person class cannot be found!"); //Prints error message if Person class cannot be found
        }
    }    
    
    public void saveToFile() //Saves data to a file
    {
        try{
            File f = new File("vPersonFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vPerson);
            oos.flush();
            oos.close();
        }catch(IOException ioe){
            System.err.println("Cannot write to file!");
        }
    }
                
    public Person getPersonByIndex(int i) //Returns person by their position in the vector
    {
        Person tempPerson = new Person();
        if (i < getSize())
        {
            tempPerson = vPerson.get(i);
        }    
        return tempPerson;
    }    
    
    public void sortPersonBySurname() //Sorts vector by person's surname
    {
        Person currentPerson = new Person();
        Person nextPerson = new Person();
        Person tempPerson = new Person();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentPerson = vPerson.elementAt(j);
                nextPerson = vPerson.elementAt(j+1);
                if(currentPerson.getSurname().equals(nextPerson.getSurname()))
                {
                    tempPerson = currentPerson;
                    vPerson.setElementAt(nextPerson, j);
                    vPerson.setElementAt(tempPerson, j+1);
                }   
            }    
        }       
    }
}