package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorBook
{
    private Vector<Book> vBooks; //Creates vector for class Book
    
    public VectorBook() //Constructor creates instance of vector vBook
    {
        vBooks = new Vector<Book>();
    }    
    
    public void addBook(Book book) //Add book to vector vBooks
    {
        vBooks.add(book);
    }
    
    public void removeBook(Book book) //Remove book from vector vBooks
    {
        vBooks.remove(book);
    }
    
    public void clearVector() //Remove ALL books from vector vBooks
    {
        vBooks.clear();
    }
    
    public void printVBooks() //Print all books from vector vBooks
    {
        for (Book tempBook : vBooks){
            System.out.println(tempBook.toString());
        }
    }
    
    public int getSize() //Returns the number of books stored in vector vBooks
    { 
        return vBooks.size();
    }
    
    public boolean checkBookID(String searchID) //Returns true if an book with the same BookID already exists
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Book tempBook : vBooks) //Will compare tempBook to with all instances of books saved in vector vBooks
        {
            if(tempBook.getItemID().equals(searchID)) //Uses method getBookID() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Book getBookByID(String searchID) //Returns the book whose ID matches that which is inputted
    {
        Book foundBook = new Book();
        for(Book tempBook : vBooks) //Will compare tempBook to with all instances of books saved in vector vBooks
        {
            if(tempBook.getItemID().equals(searchID)) //Uses method getBookID() to compare with parameter to search for matching ID
            {
                foundBook = tempBook; //Result saved in foundBook object
            }  
        }    
        return foundBook; //Returns the result
    }   
    
    public void readFromFile() //Reads data from file
    {
        try
        {
            File f = new File("vBooksFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vBooks = (Vector<Book>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe)
        {
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe)
        {
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe)
        {
            System.err.println("Book class cannot be found!"); //Prints error message if Book class cannot be found
        }
    }    
    
    public void saveToFile()//Saves data to a file
    {
        try
        {
            File f = new File("vBooksFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vBooks);
            oos.flush();
            oos.close();
        }catch(IOException ioe)
        {
            System.err.println("Cannot write to file!");
        }
    }
                
    public Book getBookByIndex(int i) //Returns Book by their position in the vector
    {
        Book tempBook = new Book();
        if (i < getSize())
        {
            tempBook = vBooks.get(i);
        }    
        return tempBook;
    }    
    
    public void sortBookByAuthorSurname() //Sorts the vector by the author of each book (by surname)
    {
        Book currentBook = new Book();
        Book nextBook = new Book();
        Book tempBook = new Book();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentBook = vBooks.elementAt(j);
                nextBook = vBooks.elementAt(j+1);
                if(currentBook.getAuthorSurname().equals(nextBook.getAuthorSurname()))
                {
                    tempBook = currentBook;
                    vBooks.setElementAt(nextBook, j);
                    vBooks.setElementAt(tempBook, j+1);
                }   
            }    
        }   
    }       
    
    public void sortBookByReleaseDate() //Sorts vector by the release date of each book
    {
        Book currentBook = new Book();
        Book nextBook = new Book();
        Book tempBook = new Book();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentBook = vBooks.elementAt(j);
                nextBook = vBooks.elementAt(j+1);
                if(currentBook.getReleaseDate() == (nextBook.getReleaseDate()))
                {
                    tempBook = currentBook;
                    vBooks.setElementAt(nextBook, j);
                    vBooks.setElementAt(tempBook, j+1);
                }   
            }    
        }   
    }           
} 
