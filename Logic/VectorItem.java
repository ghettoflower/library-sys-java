package Logic; //Class is located in the package Logic
import java.util.*;
import java.io.*; //Used to import serializable

public class VectorItem
{
    private Vector<Item> vItems; //Creates vector for class Item
        
    public VectorItem()
    {
        vItems = new Vector<Item>();
    }       
    
    public void addItem(Item item){ //Add item to vector vItems
        vItems.add(item);
    }
    
    public void removeItem(Item item){ //Remove item from vector vItems
        vItems.remove(item);
    }
    
    public void clearVector(){ //Remove ALL items from vector vItems
        vItems.clear();
    }
    
    public void printVItems(){ //Print all items from vector vItems
        for (Item tempItem : vItems){
            System.out.println(tempItem.toString());
        }
    }
    
    public int getSize(){ //Returns the number of items stored in vector vItems
        return vItems.size();
    }
    
    public boolean checkItemID(String searchID) //Returns true if an item with the same ItemID already exists
    {
        boolean flag = false; //Sets initial value of flag to be false
        for(Item tempItem : vItems) //Will compare tempItem to with all instances of items saved in vector vItems
        {
            if(tempItem.getItemID().equals(searchID)) //Uses method getItemID() to compare with parameter to search for matching ID
            {
                flag = true;
            }   
        }
        return flag; //Returns value of flag (either true or false)
    }   
    
    public Item getItemByID(String searchID) //Returns the item whose ID matches that which is inputted
    {
        Item foundItem = new Item();
        for(Item tempItem : vItems) //Will compare tempItem to with all instances of items saved in vector vItems
        {
            if(tempItem.getItemID().equals(searchID)) //Uses method getItemID() to compare with parameter to search for matching ID
            {
                foundItem = tempItem; //Result saved in foundItem object
            }  
        }    
        return foundItem; //Returns the result
    }   
    
    public void readFromFile() //Reads data from file
    {
        try{
            File f = new File("vItemsFile.obj");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            vItems = (Vector<Item>) ois.readObject();
            ois.close();
        } catch(FileNotFoundException fnfe){
            System.err.println("File not found!"); //Prints error message if no file is found
        } catch (IOException ioe){
            System.err.println("Cannot read from file"); //Prints error message if file cannot be read
        } catch (ClassNotFoundException cnfe){
            System.err.println("Item class cannot be found!"); //Prints error message if Item class cannot be found
        }
    }    
    
    public void saveToFile()//Saves data to a file
    {
        try{
            File f = new File("vItemsFile.obj");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(vItems);
            oos.flush();
            oos.close();
        }catch(IOException ioe){
            System.err.println("Cannot write to file!");
        }
    }
                
    public Item getItemByIndex(int i) //Returns Item by their position in the vector
    {
        Item tempItem = new Item();
        if (i < getSize())
        {
            tempItem = vItems.get(i);
        }    
        return tempItem;
    }    
    
    public void sortItemByAuthorSurname() //Sorts the vector by the author of each item (by surname)
    {
        Item currentItem = new Item();
        Item nextItem = new Item();
        Item tempItem = new Item();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentItem = vItems.elementAt(j);
                nextItem = vItems.elementAt(j+1);
                if(currentItem.getAuthorSurname().equals(nextItem.getAuthorSurname()))
                {
                    tempItem = currentItem;
                    vItems.setElementAt(nextItem, j);
                    vItems.setElementAt(tempItem, j+1);
                }   
            }    
        }   
    }       
    
    public void sortItemByReleaseDate() //Sorts vector by the release date of each item
    {
        Item currentItem = new Item();
        Item nextItem = new Item();
        Item tempItem = new Item();
        for(int i = 0; i < getSize(); i++)
        {
            for(int j = 0; j < getSize()-i-1; j++)
            {
                currentItem = vItems.elementAt(j);
                nextItem = vItems.elementAt(j+1);
                if(currentItem.getReleaseDate() == (nextItem.getReleaseDate()))
                {
                    tempItem = currentItem;
                    vItems.setElementAt(nextItem, j);
                    vItems.setElementAt(tempItem, j+1);
                }   
            }    
        }   
    }           
} 
