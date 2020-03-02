package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class EditJournal extends JFrame implements ActionListener
{   
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField titleTxtFld,idTxtFld, authorNameTxtFld, authorSurnameTxtFld, releaseDateTxtFld, publisherTxtFld, areaStudyTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewJournalsButton, selectJournalsButton;
    private VectorJournal vj;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public EditJournal()
    {
        super("Editing a Journal");
        this.setLayout(new BorderLayout());
        vj = new VectorJournal();
        vj.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing a Journal");
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
        viewJournalsButton = new JButton("View");
        viewJournalsButton.addActionListener(this);
        selectJournalsButton = new JButton("Select");
        selectJournalsButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewJournalsButton);
        searchPanel.add(selectJournalsButton);
        idTxtFld = new JTextField();
        titleTxtFld = new JTextField();
        authorNameTxtFld = new JTextField();
        authorSurnameTxtFld = new JTextField();
        releaseDateTxtFld = new JTextField();
        publisherTxtFld = new JTextField();
        areaStudyTxtFld = new JTextField();
        
        center.add(createLabel("Journal ID"));
        center.add(idTxtFld);
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);        
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
    
    private void clearFields(){
        idTxtFld.setText("");
        titleTxtFld.setText("");
        authorNameTxtFld.setText("");
        authorSurnameTxtFld.setText("");
        releaseDateTxtFld.setText("");
        publisherTxtFld.setText("");
        areaStudyTxtFld.setText("");
    }
        
    private boolean validateFields() //Method to ensure that all fields are not empty
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
        if (event.getSource() == viewJournalsButton)
        {
            ViewJournal viewJournal = new ViewJournal();
        }
        
        if(event.getSource() == selectJournalsButton)
        {
            if(vj.checkJournalID(idTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Journal ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Journal tempJournal = vj.getJournalByID(idTxtFld.getText());
                titleTxtFld.setText(tempJournal.getTitle());
                authorNameTxtFld.setText(tempJournal.getAuthorName()); 
                authorSurnameTxtFld.setText(tempJournal.getAuthorSurname());
                releaseDateTxtFld .setText(sdf.format(tempJournal.getReleaseDate()));
                publisherTxtFld .setText(tempJournal.getPublisher()); 
                areaStudyTxtFld.setText(tempJournal.getAreaOfStudy());  
                idTxtFld.setEnabled(false); //ID cannot be changed
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
            clearFields();
        }
        
        
        if(event.getSource() == deleteButton)
        {
            Journal tempJournal = vj.getJournalByID(idTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete "+tempJournal.getTitle() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                vj.removeJournal(tempJournal);
                vj.saveToFile();
                clearFields();
                idTxtFld.setEnabled(true);
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
                    Journal tempJournal = vj.getJournalByID(idTxtFld.getText());
                    tempJournal.setTitle(titleTxtFld.getText());
                    tempJournal.setAuthorName(authorNameTxtFld.getText());
                    tempJournal.setAuthorSurname(authorSurnameTxtFld.getText());
                    tempJournal.setReleaseDate(sdf.parse(releaseDateTxtFld.getText()));
                    tempJournal.setPublisher(publisherTxtFld.getText());
                    tempJournal.setAreaOfStudy(areaStudyTxtFld.getText());
                    vj.saveToFile();
                    JOptionPane.showMessageDialog(null,"Book updated successfully!","Edit a Book",JOptionPane.INFORMATION_MESSAGE);
                    idTxtFld.setEnabled(true);
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