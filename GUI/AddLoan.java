package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class AddLoan extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField idTxtFld, rentalDateTxtFld, returnDateTxtFld, libIDTxtFld, membIDTxtFld, bookIDTxtFld;
    private JButton clearButton, saveButton, exitButton;
    private VectorLoan vl;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public AddLoan()
    {
       super("Adding a loan");
       this.setLayout(new BorderLayout());
       vl = new VectorLoan();
       vl.readFromFile();
       
       //the north panel
       north = new JPanel();
       this.add(north,BorderLayout.NORTH);
       titleLabel = new JLabel("Adding a new loan");
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
       rentalDateTxtFld = new JTextField();
       returnDateTxtFld = new JTextField();
       libIDTxtFld = new JTextField();
       membIDTxtFld = new JTextField();
       bookIDTxtFld = new JTextField();
       
       center.add(createLabel("Loan ID"));
       center.add(idTxtFld);       
       center.add(createLabel("Rental Date"));
       center.add(rentalDateTxtFld);
       center.add(createLabel("Return Date"));
       center.add(returnDateTxtFld);
       center.add(createLabel("Librarian ID"));
       center.add(libIDTxtFld);
       center.add(createLabel("Member ID"));
       center.add(membIDTxtFld);
       center.add(createLabel("Book ID"));
       center.add(bookIDTxtFld);       
       
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
            || rentalDateTxtFld.getText().equals("")
            || returnDateTxtFld.getText().equals("")
            || libIDTxtFld.getText().equals("")
            || membIDTxtFld.getText().equals("")
            || bookIDTxtFld.getText().equals(""))
        {
            flag = true;
        }
        return flag;       
    }
    
    private void clearFields() //Empties the contents of the fields
    {
       idTxtFld.setText("");
       rentalDateTxtFld.setText("");
       returnDateTxtFld.setText("");
       libIDTxtFld.setText("");   
       membIDTxtFld.setText(""); 
       bookIDTxtFld.setText(""); 
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
            } else if(vl.checkLoanById(idTxtFld.getText()) == true)
            {
                JOptionPane.showMessageDialog(null,"Loan ID is already in use!", "Loan ID check", JOptionPane.ERROR_MESSAGE);
            } else
            {
                try
                {
                    Loan tempLoan = new Loan(idTxtFld.getText(), sdf.parse(rentalDateTxtFld.getText()), sdf.parse(returnDateTxtFld.getText()), libIDTxtFld.getText(), membIDTxtFld.getText(), bookIDTxtFld.getText());
                    vl.addLoan(tempLoan);
                    vl.saveToFile();  //Saves data to a file
                    JOptionPane.showMessageDialog(null,"Loan successfully added!", "Add a loan", JOptionPane.INFORMATION_MESSAGE);                
                    clearFields(); //Clears fields after data is saved
                } catch (ParseException e)
                {
                    JOptionPane.showMessageDialog(null,"Error parsing date!", "Date Parse Error", JOptionPane.ERROR_MESSAGE); //Shows error message if date cannot be parsed
                }                
            }
        }
    }
}
