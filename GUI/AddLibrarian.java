package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class AddLibrarian extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField idTxtFld, nameTxtFld, surnameTxtFld, dobTxtFld, addressTxtFld;
    private JButton clearButton, saveButton, exitButton;
    private VectorLibrarian vl;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public AddLibrarian()
    {
       super("Adding a librarian");
       this.setLayout(new BorderLayout());
       vl = new VectorLibrarian();
       vl.readFromFile();
       
       //the north panel
       north = new JPanel();
       this.add(north,BorderLayout.NORTH);
       titleLabel = new JLabel("Adding a new librarian");
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
       nameTxtFld = new JTextField();
       surnameTxtFld = new JTextField();
       dobTxtFld = new JTextField();
       addressTxtFld = new JTextField();   
       
       center.add(createLabel("ID"));
       center.add(idTxtFld);       
       center.add(createLabel("Name"));
       center.add(nameTxtFld);
       center.add(createLabel("Surname"));
       center.add(surnameTxtFld);
       center.add(createLabel("Date of Birth"));
       center.add(dobTxtFld);
       center.add(createLabel("Address"));
       center.add(addressTxtFld);   
       
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
            || nameTxtFld.getText().equals("")
            || surnameTxtFld.getText().equals("")
            || dobTxtFld.getText().equals("")
            || addressTxtFld.getText().equals(""))
        {
            flag = true;
        }
        return flag;       
    }
    
    private void clearFields() //Empties the contents of the fields
    {
       idTxtFld.setText("");
       nameTxtFld.setText("");
       surnameTxtFld.setText("");
       dobTxtFld.setText("");
       addressTxtFld.setText("");            
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
            } else if(vl.checkLibrarianID(idTxtFld.getText()) == true)
            {
                JOptionPane.showMessageDialog(null,"Librarian ID is already in use!", "Librarian ID check", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {
                    Librarian tempLibrarian = new Librarian(idTxtFld.getText(), nameTxtFld.getText(), surnameTxtFld.getText(), sdf.parse(dobTxtFld.getText()), addressTxtFld.getText());
                    vl.addLibrarian(tempLibrarian);
                    vl.saveToFile();  //Saves data to a file
                    JOptionPane.showMessageDialog(null,"Librarian successfully added!", "Add a librarian", JOptionPane.INFORMATION_MESSAGE);                
                    clearFields(); //Clears fields after data is saved
                } catch (ParseException e)
                {
                    JOptionPane.showMessageDialog(null,"Error parsing date!", "Date Parse Error", JOptionPane.ERROR_MESSAGE); //Shows error message if date cannot be parsed
                }                
            }
        }
    }
}
