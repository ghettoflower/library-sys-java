package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class AddJournal extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField titleTxtFld, idTxtFld, authorNameTxtFld, authorSurnameTxtFld, releaseDateTxtFld, publisherTxtFld, areaStudyTxtFld;
    private JButton clearButton, saveButton, exitButton;
    private VectorJournal vj;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public AddJournal()
    {
       super("Adding a journal");
       this.setLayout(new BorderLayout());
       vj = new VectorJournal();
       vj.readFromFile();
       
       //the north panel
       north = new JPanel();
       this.add(north,BorderLayout.NORTH);
       titleLabel = new JLabel("Adding a new journal");
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
       idTxtFld = new JTextField();
       titleTxtFld = new JTextField();
       authorNameTxtFld = new JTextField();
       authorSurnameTxtFld = new JTextField();
       releaseDateTxtFld = new JTextField();
       publisherTxtFld = new JTextField();
       areaStudyTxtFld = new JTextField();
       
       center.add(createLabel("Journal ID"));
       center.add(idTxtFld);       
       center.add(createLabel("Journal Title"));
       center.add(titleTxtFld);
       center.add(createLabel("Author Name"));
       center.add(authorNameTxtFld);
       center.add(createLabel("Author Surname"));
       center.add(authorSurnameTxtFld);
       center.add(createLabel("Release Date"));
       center.add(releaseDateTxtFld);
       center.add(createLabel("Publisher"));
       center.add(publisherTxtFld);       
       center.add(createLabel("Area of Study"));
       center.add(areaStudyTxtFld);            
       
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
        if(idTxtFld.getText().equals("") 
            || titleTxtFld.getText().equals("")
            || authorNameTxtFld.getText().equals("")
            || authorSurnameTxtFld.getText().equals("")
            || releaseDateTxtFld.getText().equals("")
            || publisherTxtFld.getText().equals("")
            || areaStudyTxtFld.getText().equals(""))
        {
            flag = true;
        }
        return flag;       
    }
    
    private void clearFields() //Empties the contents of the fields
    {
        idTxtFld.setText("");
        titleTxtFld.setText("");
        authorNameTxtFld.setText("");
        authorSurnameTxtFld.setText("");
        releaseDateTxtFld.setText("");
        publisherTxtFld.setText("");
        areaStudyTxtFld.setText("");
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
            } else if(vj.checkJournalID(idTxtFld.getText()) == true)
            {
                JOptionPane.showMessageDialog(null,"Journal ID is already in use!", "Journal ID check", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {
                    Journal tempJournal = new Journal(titleTxtFld.getText(), idTxtFld.getText(), authorNameTxtFld.getText(), authorSurnameTxtFld.getText(), sdf.parse(releaseDateTxtFld.getText()), publisherTxtFld.getText(), areaStudyTxtFld.getText());
                    vj.addJournal(tempJournal);
                    vj.saveToFile();  //Saves data to a file
                    JOptionPane.showMessageDialog(null,"Journal successfully added!", "Add a journal", JOptionPane.INFORMATION_MESSAGE);                
                    clearFields(); //Clears fields after data is saved
                } catch (ParseException e)
                {
                    JOptionPane.showMessageDialog(null,"Error parsing date!", "Date Parse Error", JOptionPane.ERROR_MESSAGE); //Shows error message if date cannot be parsed
                }                
            }
        }
    }
}