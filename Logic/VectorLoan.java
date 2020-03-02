package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorLoan
{
    private Vector<Loan> vLoans; //Creates vector for Loan class
    
    public VectorLoan() //Constructor creates instance of vector vLoans
    {
        vLoans = new Vector<Loan>();
    }       
    
    public void addLoan(Loan loan) //Method will add a loan
    {
        vLoans.add(loan);
    }    
    
    public void removeLoan(Loan loan) //Method to remove loan from vLoans vector
    {
        vLoans.remove(loan);
    }   
    
    public void clearVector() //Removes all from vector
    {
        vLoans.clear();
    }    
    
    public void printAllLoans() //Prints all in the vector vLoans
    {
        for (Loan tempLoan : vLoans)
        {
            System.out.println(tempLoan.toString());
        }
    }     
    
    public int getSize() //Returns size of vector
    {
        return vLoans.size();
    }        
    
    public boolean checkLoanById(String id) //Returns true if loan with specific ID is found
    {
        boolean flag = false;
        for (Loan tempLoan : vLoans)
        {
            if (tempLoan.getLoanId().equals(id))
            {
                flag = true;
            }
        }    
        return flag;
    }     
    
    public Loan returnLoanById(String id) //Returns a loan object by given ID
    {
        Loan foundLoan = new Loan();
        for (Loan tempLoan : vLoans)
        {
            if (tempLoan.getLoanId().equals(id))
            {
                foundLoan = tempLoan;
            }   
        }     
        return foundLoan; //Returns loan with matching id
    }    
    
    public void readFromFile() //Reads data from file
    {
        try
        {
            File f = new File("vLoansFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vLoans = (Vector<Loan>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe)
        {
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe)
        {
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Loan class cannot be found!"); //Prints error message if Loan class cannot be found
        }
    }    
    
    public void saveToFile() //Saves data to a file
    {
        try
        {
            File f = new File("vLoansFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vLoans);
            oos.flush();
            oos.close();
        }catch(IOException ioe)
        {
            System.err.println("Cannot write to file!"); //Prints error message if I/O exception occurs and data can't be written to file
        }
    }
                
    public Loan getLoanByIndex(int i) //Returns loan by its position in the vector
    {
        Loan tempLoan = new Loan();
        if (i < getSize())
        {
            tempLoan = vLoans.get(i);
        }    
        return tempLoan;
    }     
    
    public void sortLoanByReturnDate()
    {
        Loan currentLoan = new Loan();
        Loan nextLoan = new Loan();
        Loan tempLoan = new Loan();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentLoan = vLoans.elementAt(j);
                nextLoan = vLoans.elementAt(j+1);
                if(currentLoan.getReturnDate() == (nextLoan.getReturnDate()))
                {
                    tempLoan = currentLoan;
                    vLoans.setElementAt(nextLoan, j);
                    vLoans.setElementAt(tempLoan, j+1);
                }   
            }    
        }           
    } 
}
