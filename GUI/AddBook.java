package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat; //Imports SimpleDateFormat for parsing of date
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*; //Imports logic for data from Book class

public class AddBook extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField bkTitleTxtFld, bkItemIDTxtFld, bkAuthorNameTxtFld, bkAuthorSurnameTxtFld, bkReleaseDateTxtFld, bkPublisherTxtFld, bkGenreTxtFld;
    private JButton clearButton, saveButton, exitButton;
    private VectorBook vb; //Declares vector of Book class as "vb"
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public AddBook()
    {
        super("Adding a book"); //Title displayed in window's title bar
        this.setLayout(new BorderLayout());
        vb = new VectorBook(); //Establishes vb as a vector of the VectorBook class.
        vb.readFromFile(); //Reads from object file to see if there is any existing data
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Adding a Client");
        titleLabel.setFont(new Font("Verdana",Font.BOLD,20)); //Sets the font of the name saved in titleLabel. In this case it is Verdana, bold and size 20
        titleLabel.setForeground(Color.blue); //Sets the colour of the font used as blue
        north.add(titleLabel); //Displays the name of the window in the top panel of the window
        
        //West and East Panels
        west = new JPanel();
        east = new JPanel();
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        westLabel = new JLabel("          "); //Will display blank space, text fields will be properly centered
        eastLabel = new JLabel("          ");
        west.add(westLabel); //Displays label on the West Panel
        east.add(eastLabel); //Displays label on the East Panel
        
        //Center Panel - TextFields will be added here
        center = new JPanel();
        this.add(center, BorderLayout.CENTER);
        center.setLayout(new GridLayout(8,2,0,20)); //Sets the layout of the grid - rows, columns, horizontal gap and vertical gap are the parameters
        bkTitleTxtFld = new JTextField(); //Creates a text field which will display the book's title
        bkItemIDTxtFld = new JTextField(); //Creates a text field which will display the book's ID
        bkAuthorNameTxtFld = new JTextField(); //Creates a text field which will display the book's author's first name
        bkAuthorSurnameTxtFld = new JTextField(); //Creates a text field which will display the book's author's surname
        bkReleaseDateTxtFld = new JTextField(); //Creates a text field which will display the book's release date
        bkPublisherTxtFld = new JTextField(); //Creates a text field which will display the book's publisher
        bkGenreTxtFld = new JTextField(); //Creates a text field which will display the book's genre
        
        center.add(createLabel("Title")); //Creates and displays a label which shows the word "Title" near the text field
        center.add(bkTitleTxtFld); //Displays the text field for the book's title
        center.add(createLabel("ID")); //Creates and displays a label which shows the word "ID" near the text field
        center.add(bkItemIDTxtFld); //Displays the text field for the book's ID
        center.add(createLabel("Author Name")); //Creates and displays a label which shows the word "Author Name" near the text field
        center.add(bkAuthorNameTxtFld); //Displays the text field for the book's author's first name
        center.add(createLabel("Author Surname")); //Creates and displays a label which shows the word "Author Surname" near the text field
        center.add(bkAuthorSurnameTxtFld); //Displays the text field for the book's author's surname        
        center.add(createLabel("Release Date")); //Creates and displays a label which shows the word "Release Date" near the text field
        center.add(bkReleaseDateTxtFld); //Displays the text field for the book's release date
        center.add(createLabel("Publisher")); //Creates and displays a label which shows the word "Publisher" near the text field
        center.add(bkPublisherTxtFld); //Displays the text field for the book's publisher
        center.add(createLabel("Genre")); //Creates and displays a label which shows the word "Genre" near the text field
        center.add(bkGenreTxtFld); //Displays the text field for the book's genre        
        
        //South Panel - Buttons will be displayed here
        south = new JPanel();
        south.setLayout(new FlowLayout());
        this.add(south, BorderLayout.SOUTH);
        clearButton = new JButton("Clear"); 
        south.add(clearButton);
        clearButton.addActionListener(this);
        saveButton = new JButton("Save");
        south.add(saveButton);
        saveButton.addActionListener(this);
        exitButton = new JButton("Exit");
        south.add(exitButton);
        exitButton.addActionListener(this);        
        
        
        this.setSize(500,450); //Sets size of window as 500x450
        this.setLocation(50,50); //Sets default location of the window
        this.setVisible(true); //Sets window visibility on            
    }  
    
    private JLabel createLabel(String title) //Method creates new labels as String
    {
        return new JLabel(title);
    }    
    
    private void clearFields() //Empties the contents of the fields
    {
        bkTitleTxtFld.setText("");
        bkItemIDTxtFld.setText(""); 
        bkAuthorNameTxtFld.setText("");
        bkAuthorSurnameTxtFld.setText("");
        bkReleaseDateTxtFld.setText("");
        bkPublisherTxtFld.setText("");
        bkGenreTxtFld.setText(""); 
    }    
    
    private void disableButtons() //Disables (greys out) the save and clear buttons - Exit button will always stay enabled
    {
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);
    }
    
    private void enableButtons() //Enables the save and clear buttons
    {
       saveButton.setEnabled(true);
       clearButton.setEnabled(true);
    }    
    
    private boolean validateFields() //Method to ensure that all fields are not empty
    {
        boolean flag=false;
        if(bkTitleTxtFld.getText().equals("")
        || bkItemIDTxtFld.getText().equals("")
        || bkAuthorNameTxtFld.getText().equals("")
        || bkAuthorSurnameTxtFld.getText().equals("")
        || bkReleaseDateTxtFld.getText().equals("")
        || bkPublisherTxtFld.getText().equals("")
        || bkGenreTxtFld.getText().equals(""))
        {
            flag = true; //Sets flag as true if any of the fields are empty
        }
        return flag;
    }    
        
    public void actionPerformed(ActionEvent event) //Method invoked when an action occurs. Parameter declares instance of ActionEvent class
    {
        if (event.getSource() == exitButton) //If exit button is selected, system will ask user for confirmation
        {
            if(JOptionPane.showConfirmDialog(null,"Are you sure you wish to exit?","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //Displays a yes or no option panel
            {
                MainWindow mw = new MainWindow();
                this.dispose(); //Closes the JFrame window
            }
        }
        
        if (event.getSource() == clearButton) //If clear button is clicked, system will ask user for confirmation
        {
            if(JOptionPane.showConfirmDialog(null,"Are you sure you wish to clear this book's fields?","CLEAR", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //Displays a yes or no option panel which asks the user for confirmation of if they want to clear fields
            {            
                clearFields(); //Executes method clearFields()
            }
        }

        if (event.getSource() == saveButton) //If save button is clicked
        {
            if(vb.checkBookID(bkItemIDTxtFld.getText())==true)
            {
                JOptionPane.showMessageDialog(null,"ID is already in use!","Book ID check", JOptionPane.ERROR_MESSAGE); //Shows error message if ID for book is already used           
            } else if(validateFields()== true)
            {
                JOptionPane.showMessageDialog(null,"You have empty fields!","Empty fields", JOptionPane.ERROR_MESSAGE); //Shows error message if there are any empty fields present
            }else
            {
                try
                {
                    //String title, String ID, String authorName, String authorSurname String releaseDate (to be parsed as date), String publisher, String genre
                    Book tempBook = new Book(bkTitleTxtFld.getText(), bkItemIDTxtFld.getText(), bkAuthorNameTxtFld.getText(), bkAuthorSurnameTxtFld.getText(), sdf.parse(bkReleaseDateTxtFld.getText()), bkPublisherTxtFld.getText(), bkGenreTxtFld.getText());
                    vb.addBook(tempBook);
                    vb.saveToFile(); //Saves data to a file
                    clearFields(); //Clears fields after author is added and saved
                } catch (ParseException e)
                {
                    JOptionPane.showMessageDialog(null,"Error parsing date!", "Date Parse Error", JOptionPane.ERROR_MESSAGE); //Shows error message if date cannot be parsed
                }
            }
        }
    }     
}