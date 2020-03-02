package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class EditBook extends JFrame implements ActionListener
{   
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField bkTitleTxtFld, bkItemIDTxtFld, bkAuthorNameTxtFld, bkAuthorSurnameTxtFld, bkReleaseDateTxtFld, bkPublisherTxtFld, bkGenreTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewBooksButton, selectBooksButton;
    private VectorBook vb;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public EditBook()
    {
        super("Editing a Book");
        this.setLayout(new BorderLayout());
        vb = new VectorBook();
        vb.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing a Book");
        titleLabel.setFont(new Font("Verdana",Font.BOLD,20));
        titleLabel.setForeground(Color.blue);
        north.add(titleLabel);
        
        //west and east panels
        west = new JPanel();
        east = new JPanel();
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        westLabel = new JLabel("          ");
        eastLabel = new JLabel("          ");
        west.add(westLabel);
        east.add(eastLabel);
        
        //center panel
        center = new JPanel();
        this.add(center, BorderLayout.CENTER);
        center.setLayout(new GridLayout(9,2,0,17));
        searchPanel = new JPanel();
        viewBooksButton = new JButton("View");
        viewBooksButton.addActionListener(this);
        selectBooksButton = new JButton("Select");
        selectBooksButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewBooksButton);
        searchPanel.add(selectBooksButton);
        bkTitleTxtFld = new JTextField();
        bkItemIDTxtFld = new JTextField();
        bkAuthorNameTxtFld = new JTextField();
        bkAuthorSurnameTxtFld = new JTextField();
        bkReleaseDateTxtFld = new JTextField();
        bkPublisherTxtFld = new JTextField();
        bkGenreTxtFld = new JTextField();
        
        center.add(createLabel("Book ID"));
        center.add(bkItemIDTxtFld);
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);        
        center.add(createLabel("Title"));
        center.add(bkTitleTxtFld);
        center.add(createLabel("Author Name"));
        center.add(bkAuthorNameTxtFld);
        center.add(createLabel("Author Surname"));
        center.add(bkAuthorSurnameTxtFld);
        center.add(createLabel("Release Date"));
        center.add(bkReleaseDateTxtFld);
        center.add(createLabel("Publisher"));
        center.add(bkPublisherTxtFld);
        center.add(createLabel("Genre"));
        center.add(bkGenreTxtFld);
        
        //south panel
        south = new JPanel();
        south.setLayout(new FlowLayout());
        this.add(south, BorderLayout.SOUTH);     
        deleteButton = new JButton("Delete"); 
        south.add(deleteButton);
        deleteButton.addActionListener(this);
        clearButton = new JButton("Clear"); 
        south.add(clearButton);
        clearButton.addActionListener(this);
        saveButton = new JButton("Save");
        south.add(saveButton);
        saveButton.addActionListener(this);
        exitButton = new JButton("Exit");
        south.add(exitButton);
        exitButton.addActionListener(this);
        
        
        
        disableButtons();
        this.setSize(500,530);
        this.setLocation(50,50);
        this.setVisible(true);
    }
    
    private JLabel createLabel(String title)
    {
        return new JLabel(title);
    }
    
    private void clearFields()
    {
        bkTitleTxtFld.setText("");
        bkItemIDTxtFld.setText("");
        bkAuthorNameTxtFld.setText(""); 
        bkAuthorSurnameTxtFld.setText("");
        bkReleaseDateTxtFld.setText("");
        bkPublisherTxtFld.setText(""); 
        bkGenreTxtFld.setText("");
    }
    
    private void clearFields2()
    {
        bkTitleTxtFld.setText("");
        bkItemIDTxtFld.setText("");
        bkAuthorNameTxtFld.setText(""); 
        bkAuthorSurnameTxtFld.setText("");
        bkReleaseDateTxtFld.setText("");
        bkPublisherTxtFld.setText(""); 
        bkGenreTxtFld.setText("");
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
        
    private void disableButtons() //Disables (greys out) the save, clear and delete buttons - Exit button will always stay enabled
    {
        deleteButton.setEnabled(false);
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);
    }
    
    private void enableButtons() //Enables the save and clear buttons
    {
       deleteButton.setEnabled(true);
       saveButton.setEnabled(true);
       clearButton.setEnabled(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == viewBooksButton)
        {
            ViewBooks viewBooks = new ViewBooks();
        }
        
        if(event.getSource() == selectBooksButton)
        {
            if(vb.checkBookID(bkItemIDTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Book ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Book tempBook = vb.getBookByID(bkItemIDTxtFld.getText());
                bkTitleTxtFld.setText(tempBook.getTitle());
                bkAuthorNameTxtFld.setText(tempBook.getAuthorName()); 
                bkAuthorSurnameTxtFld.setText(tempBook.getAuthorSurname());
                bkReleaseDateTxtFld.setText(sdf.format(tempBook.getReleaseDate()));
                bkPublisherTxtFld.setText(tempBook.getPublisher()); 
                bkGenreTxtFld.setText(tempBook.getGenre());  
                bkItemIDTxtFld.setEnabled(false); //ID cannot be changed
                enableButtons();
            }
        }
        
         if (event.getSource() == exitButton)
         {
            if(JOptionPane.showConfirmDialog(null,"Are you sure you want to exit ?", "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                MainWindow mw = new MainWindow();
                this.dispose();
            }
        }
        
        if (event.getSource() == clearButton)
        {
            clearFields2();
        }
        
        
        if(event.getSource() == deleteButton)
        {
            Book tempBook = vb.getBookByID(bkItemIDTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete "+tempBook.getTitle() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                vb.removeBook(tempBook);
                vb.saveToFile();
                clearFields();
                bkItemIDTxtFld.setEnabled(true);
            }
        }
        
        if (event.getSource()==saveButton)
        {
            if(validateFields()== true)
            {
                JOptionPane.showMessageDialog(null,"You have empty fields!","Empty fields", JOptionPane.ERROR_MESSAGE);
            }else
            {
                try
                {
                    Book tempBook = vb.getBookByID(bkItemIDTxtFld.getText());
                    tempBook.setTitle(bkTitleTxtFld.getText());
                    tempBook.setAuthorName(bkAuthorNameTxtFld.getText());
                    tempBook.setAuthorSurname(bkAuthorSurnameTxtFld.getText());
                    tempBook.setReleaseDate(sdf.parse(bkReleaseDateTxtFld.getText()));
                    tempBook.setPublisher(bkPublisherTxtFld.getText());
                    tempBook.setGenre(bkGenreTxtFld.getText());
                    vb.saveToFile();
                    JOptionPane.showMessageDialog(null,"Book updated successfully!","Edit a Book",JOptionPane.INFORMATION_MESSAGE);
                    bkItemIDTxtFld.setEnabled(true);
                    clearFields();
                    disableButtons();
                } catch (ParseException e)
                {
                    JOptionPane.showMessageDialog(null,"Error parsing date!", "Date Parse Error", JOptionPane.ERROR_MESSAGE); //Shows error message if date cannot be parsed
                }  
            }
        }
    }
}