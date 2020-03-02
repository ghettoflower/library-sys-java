package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorReservations
{
    private Vector<Reservations> vReservations; //Creates vector for class Reservation
        
    public VectorReservations() //Constructor creates instance of vector vReservations
    {
        vReservations = new Vector<Reservations>();
    }       
    
    public void addReservation(Reservations reservation){ //Add reservation to vector vReservations
        vReservations.add(reservation);
    }
    
    public void removeReservation(Reservations reservation){ //Remove reservation from vector vReservations
        vReservations.remove(reservation);
    }
    
    public void clearVector(){ //Remove ALL reservations from vector vReservations
        vReservations.clear();
    }
    
    public void printVReservations(){ //Print all reservations from vector vReservations
        for (Reservations tempReservation : vReservations){
            System.out.println(tempReservation.toString());
        }
    }
    
    public int getSize(){ //Returns the number of reservations stored in vector vReservations
        return vReservations.size();
    }
    
    public boolean checkReservationID(String searchID) //Returns true if an reservation with the same ReservationID already exists
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Reservations tempReservation : vReservations) //Will compare tempReservation to with all instances of reservations saved in vector vReservations
        {
            if(tempReservation.getResID().equals(searchID)) //Uses method getReservationID() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Reservations getReservationByID(String searchID) //Returns the reservation whose ID matches that which is inputted
    {
        Reservations foundReservation = new Reservations();
        for(Reservations tempReservation : vReservations) //Will compare tempReservation to with all instances of reservations saved in vector vReservations
        {
            if(tempReservation.getResID().equals(searchID)) //Uses method getReservationID() to compare with parameter to search for matching ID
            {
                foundReservation = tempReservation; //Result saved in foundReservation object
            }  
        }    
        return foundReservation; //Returns the result
    }   
    
    public void readFromFile() //Reads data from file
    {
        try{
            File f = new File("vReservationsFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vReservations = (Vector<Reservations>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe){
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe){
            System.err.println("Reservation class cannot be found!"); //Prints error message if Reservation class cannot be found
        }
    }    
    
    public void saveToFile()//Saves data to a file
    {
        try{
            File f = new File("vReservationsFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vReservations);
            oos.flush();
            oos.close();
        }catch(IOException ioe){
            System.err.println("Cannot write to file!");
        }
    }
                
    public Reservations getReservationByIndex(int i) //Returns Reservation by their position in the vector
    {
        Reservations tempReservation = new Reservations();
        if (i < getSize())
        {
            tempReservation = vReservations.get(i);
        }    
        return tempReservation;
    }    
    
    public void sortReservationByDate() //Sorts the vector by the date of each reservation
    {
        Reservations currentReservation = new Reservations();
        Reservations nextReservation = new Reservations();
        Reservations tempReservation = new Reservations();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentReservation = vReservations.elementAt(j);
                nextReservation = vReservations.elementAt(j+1);
                if(currentReservation.getResDate().equals(nextReservation.getResDate()))
                {
                    tempReservation = currentReservation;
                    vReservations.setElementAt(nextReservation, j);
                    vReservations.setElementAt(tempReservation, j+1);
                }   
            }    
        }   
    }       
     
} 
