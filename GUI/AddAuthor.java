package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class AddAuthor extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center;
    private JLabel titleLabel,westLabel,eastLabel;
    private JTextField authNameTxtFld, authSurnameTxtFld, authNationalityTxtFld, authYearBirthTxtFld, authYearDeathTxtFld, authGenreTxtFld, authIDTxtFld;
    private JButton clearButton, saveButton, exitButton;
    private VectorAuthor va;
    
    public AddAuthor()
    {
       super("Adding an author");
       this.setLayout(new BorderLayout());
       va = new VectorAuthor();
       va.readFromFile();
       
       //the north panel
       north = new JPanel();
       this.add(north,BorderLayout.NORTH);
       titleLabel = new JLabel("Adding a new author");
       titleLabel.setFont(new Font("Verdana",Font.BOLD,20));
       titleLabel.setForeground(Color.red);
       north.add(titleLabel);
       
       //West and East panels
       west = new JPanel();
       east = new JPanel();
       this.add(west, BorderLayout.WEST);
       this.add(east, BorderLayout.EAST);
       westLabel = new JLabel("          ");
       eastLabel = new JLabel("          ");
       west.add(westLabel);
       east.add(eastLabel);
       
       //Center panel
       center = new JPanel();
       this.add(center, BorderLayout.CENTER);
       center.setLayout(new GridLayout(9,2,0,20));
       authNameTxtFld = new JTextField();
       authSurnameTxtFld = new JTextField();
       authNationalityTxtFld = new JTextField();
       authYearBirthTxtFld = new JTextField();
       authYearDeathTxtFld = new JTextField();
       authGenreTxtFld = new JTextField();
       authIDTxtFld = new JTextField();
       
       center.add(createLabel("Name"));
       center.add(authNameTxtFld);
       center.add(createLabel("Surname"));
       center.add(authSurnameTxtFld);
       center.add(createLabel("Nationality"));
       center.add(authNationalityTxtFld);
       center.add(createLabel("Year of Birth"));
       center.add(authYearBirthTxtFld);
       center.add(createLabel("Year of Death"));
       center.add(authYearDeathTxtFld);
       center.add(createLabel("Genre"));
       center.add(authGenreTxtFld);
       center.add(createLabel("ID"));
       center.add(authIDTxtFld);
       
       //South panel
       south = new JPanel();
       south.setLayout(new FlowLayout());
       this.add(south, BorderLayout.SOUTH);
       clearButton = new JButton("Clear");
       saveButton = new JButton("Save");
       exitButton = new JButton("Exit");
       south.add(clearButton);
       clearButton.addActionListener(this);
       south.add(saveButton);
       saveButton.addActionListener(this);
       south.add(exitButton);
       exitButton.addActionListener(this);
       
       this.setSize(600,600);
       this.setLocation(50,50);
       this.setVisible(true);     
    }
    
    private JLabel createLabel(String title) //Method creates new labels as String
    {
        return new JLabel(title);
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
    
    private boolean validateEmptyFields() //Method to ensure that all fields are not empty
    {
        boolean flag = false;
        if(authIDTxtFld.getText().equals("")
            || authNameTxtFld.getText().equals("") 
            || authSurnameTxtFld.getText().equals("")
            || authNationalityTxtFld.getText().equals("") 
            || authYearBirthTxtFld.getText().equals("")
            || authYearDeathTxtFld.getText().equals("")
            || authGenreTxtFld.getText().equals(""))
        {
            flag = true;
        }
        return flag;       
    }
    
    private void clearFields() //Empties the contents of the fields
    {
       authNameTxtFld.setText("");
       authSurnameTxtFld.setText("");
       authNationalityTxtFld.setText("");
       authYearBirthTxtFld.setText("");
       authYearDeathTxtFld.setText("");
       authGenreTxtFld.setText("");
       authIDTxtFld.setText("");       
    }
    
    
    public void actionPerformed(ActionEvent event)//Method invoked when an action occurs. Parameter declares instance of ActionEvent class
    {
        if (event.getSource() == exitButton) //If exit button is selected, system will ask user for confirmation
        {
            if(JOptionPane.showConfirmDialog(null,"Are you sure you wish to exit?","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //Displays a yes or no option panel
            {
                MainWindow mw = new MainWindow();
                this.dispose(); //Closes the JFrame window
            }
        }
        
        if (event.getSource() == clearButton)
        {
           clearFields();
        }        
        
        if (event.getSource() == saveButton)
        {
            if (validateEmptyFields() == true)
            {
                JOptionPane.showMessageDialog(null,"You have empty fields!", "Empty fields", JOptionPane.ERROR_MESSAGE);
            } else if(va.checkAuthorID(authIDTxtFld.getText()) == true)
            {
                JOptionPane.showMessageDialog(null,"Author ID is already in use!", "Author ID check", JOptionPane.ERROR_MESSAGE);
            } else
            {
                //String name, String surname, String nationality, int yearOfBirth, int yearOfDeath, String genre, String authorID
                Author tempAuthor = new Author(authNameTxtFld.getText(), authSurnameTxtFld.getText(), authNationalityTxtFld.getText(), Integer.parseInt(authYearBirthTxtFld.getText()), Integer.parseInt(authYearDeathTxtFld.getText()), authGenreTxtFld.getText(), authIDTxtFld.getText());
                va.addAuthor(tempAuthor);
                va.saveToFile();  //Saves data to a file
                JOptionPane.showMessageDialog(null,"Author successfully added!", "Add an author", JOptionPane.INFORMATION_MESSAGE);                
                clearFields(); //Clears fields after data is saved
            }
        }
    }
}
