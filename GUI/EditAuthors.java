package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import Logic.*;

public class EditAuthors extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField authNameTxtFld, authSurnameTxtFld, authNationalityTxtFld, authYearBirthTxtFld, authYearDeathTxtFld, authGenreTxtFld, authIDTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewAuthorsButton, selectAuthorsButton;
    private VectorAuthor va;    
    
    public EditAuthors()
    {
        super("Editing an Author");
        this.setLayout(new BorderLayout());
        va = new VectorAuthor();
        va.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing an Author");
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
        viewAuthorsButton = new JButton("View");
        viewAuthorsButton.addActionListener(this);
        selectAuthorsButton = new JButton("Select");
        selectAuthorsButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewAuthorsButton);
        searchPanel.add(selectAuthorsButton);
        authNameTxtFld = new JTextField();
        authSurnameTxtFld = new JTextField();
        authNationalityTxtFld = new JTextField();
        authYearBirthTxtFld = new JTextField();
        authYearDeathTxtFld = new JTextField();
        authGenreTxtFld = new JTextField();
        authIDTxtFld = new JTextField();
        
        center.add(createLabel("Author ID"));
        center.add(authIDTxtFld);
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);        
        center.add(createLabel("Author Name"));
        center.add(authNameTxtFld);
        center.add(createLabel("Author Surname"));
        center.add(authSurnameTxtFld);
        center.add(createLabel("Nationality"));
        center.add(authNationalityTxtFld);
        center.add(createLabel("Year of Birth"));
        center.add(authYearBirthTxtFld);
        center.add(createLabel("Year of Death"));
        center.add(authYearDeathTxtFld);
        center.add(createLabel("Genre"));
        center.add(authGenreTxtFld);
        
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
    
    private JLabel createLabel(String title) //Method creates new labels as String
    {
        return new JLabel(title);
    }
    
    private void clearFields()
    {
       authNameTxtFld.setText("");
       authSurnameTxtFld.setText("");
       authNationalityTxtFld.setText("");
       authYearBirthTxtFld.setText("");
       authYearDeathTxtFld.setText("");
       authGenreTxtFld.setText("");
       authIDTxtFld.setText("");
    }
    
    private void clearFields2()
    {
        authNameTxtFld.setText("");
        authSurnameTxtFld.setText("");
        authNationalityTxtFld.setText("");
        authYearBirthTxtFld.setText("");
        authYearDeathTxtFld.setText("");
        authGenreTxtFld.setText("");
        authIDTxtFld.setText("");
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
        if (event.getSource() == viewAuthorsButton)
        {
            ViewAuthors viewAuthors = new ViewAuthors();
        }
        
        if(event.getSource() == selectAuthorsButton)
        {
            if(va.checkAuthorID(authIDTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Author ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Author tempAuthor = va.getAuthorByID(authIDTxtFld.getText());
                authNameTxtFld.setText(tempAuthor.getName()); 
                authSurnameTxtFld.setText(tempAuthor.getSurname());
                authNationalityTxtFld.setText(tempAuthor.getNationality());
                authYearBirthTxtFld.setText(Integer.toString(tempAuthor.getYearOfBirth()));
                authYearDeathTxtFld.setText(Integer.toString(tempAuthor.getYearOfDeath()));                
                authGenreTxtFld.setText(tempAuthor.getGenre());  
                authIDTxtFld.setEnabled(false); //ID cannot be changed
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
            Author tempAuthor = va.getAuthorByID(authIDTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete "+ tempAuthor.getName() + " " + tempAuthor.getSurname() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                va.removeAuthor(tempAuthor);
                va.saveToFile();
                clearFields();
                authIDTxtFld.setEnabled(true);
            }
        }
        
        if (event.getSource()==saveButton)
        {
            if(validateEmptyFields()== true)
            {
                JOptionPane.showMessageDialog(null,"You have empty fields!","Empty fields", JOptionPane.ERROR_MESSAGE);
            } else
            {
                Author tempAuthor = va.getAuthorByID(authIDTxtFld.getText());
                tempAuthor.setName(authNameTxtFld.getText());
                tempAuthor.setSurname(authSurnameTxtFld.getText());
                tempAuthor.setNationality(authNationalityTxtFld.getText());
                tempAuthor.setYearOfBirth(Integer.parseInt(authYearBirthTxtFld.getText()));
                tempAuthor.setYearOfDeath(Integer.parseInt(authYearDeathTxtFld.getText()));
                tempAuthor.setGenre(authGenreTxtFld.getText());
                va.saveToFile();
                JOptionPane.showMessageDialog(null,"Author updated successfully!","Edit an author",JOptionPane.INFORMATION_MESSAGE);
                authIDTxtFld.setEnabled(true);
                clearFields();
                disableButtons();
            }          
        }
    }
}    
