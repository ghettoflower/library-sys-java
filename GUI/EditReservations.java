package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class EditReservations extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField idTxtFld, journalIDTxtFld, reservationDateTxtFld, memberIDTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewReservationsButton, selectReservationsButton;
    private VectorReservations vr;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public EditReservations()
    {
        super("Editing a Reservation");
        this.setLayout(new BorderLayout());
        vr = new VectorReservations();
        vr.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing a Reservation");
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
        viewReservationsButton = new JButton("View");
        viewReservationsButton.addActionListener(this);
        selectReservationsButton = new JButton("Select");
        selectReservationsButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewReservationsButton);
        searchPanel.add(selectReservationsButton);
        
        idTxtFld = new JTextField();
        journalIDTxtFld = new JTextField();
        reservationDateTxtFld = new JTextField();
        memberIDTxtFld = new JTextField();
        
        center.add(createLabel("Reservation ID"));
        center.add(idTxtFld);       
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);       
        center.add(createLabel("Journal ID"));
        center.add(journalIDTxtFld);
        center.add(createLabel("Reservation Date"));
        center.add(reservationDateTxtFld);
        center.add(createLabel("Member ID"));
        center.add(memberIDTxtFld);
        
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
        idTxtFld.setText("");
        journalIDTxtFld.setText("");
        reservationDateTxtFld.setText("");
        memberIDTxtFld.setText("");   
    }
        
    private boolean validateFields() //Method to ensure that all fields are not empty
    {
        boolean flag=false;
        if(idTxtFld.getText().equals("") 
            || journalIDTxtFld.getText().equals("")
            || reservationDateTxtFld.getText().equals("")
            || memberIDTxtFld.getText().equals(""))
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
        if (event.getSource() == viewReservationsButton)
        {
            ViewReservations viewReservations = new ViewReservations();
        }
        
        if(event.getSource() == selectReservationsButton)
        {
            if(vr.checkReservationID(idTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Reservation ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Reservations tempReservation = vr.getReservationByID(idTxtFld.getText());
                journalIDTxtFld.setText(tempReservation.getJournalID()); 
                reservationDateTxtFld.setText(sdf.format(tempReservation.getResDate()));
                memberIDTxtFld.setText(tempReservation.getMemID());
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
            Reservations tempReservation = vr.getReservationByID(idTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Reservation "+tempReservation.getResID() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                vr.removeReservation(tempReservation);
                vr.saveToFile();
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
                    Reservations tempReservation = vr.getReservationByID(idTxtFld.getText());
                    tempReservation.setJournalID(journalIDTxtFld.getText());
                    tempReservation.setResDate(sdf.parse(reservationDateTxtFld.getText()));
                    tempReservation.setMemID(memberIDTxtFld.getText());
                    vr.saveToFile();
                    JOptionPane.showMessageDialog(null,"Reservation updated successfully!","Edit a Reservation",JOptionPane.INFORMATION_MESSAGE);
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
