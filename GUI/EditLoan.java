package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class EditLoan extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField idTxtFld, rentalDateTxtFld, returnDateTxtFld, libIDTxtFld, membIDTxtFld, bookIDTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewLoanButton, selectLoanButton;
    private VectorLoan vl;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public EditLoan()
    {
        super("Editing a Loan");
        this.setLayout(new BorderLayout());
        vl = new VectorLoan();
        vl.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing a Loan");
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
        viewLoanButton = new JButton("View");
        viewLoanButton.addActionListener(this);
        selectLoanButton = new JButton("Select");
        selectLoanButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewLoanButton);
        searchPanel.add(selectLoanButton);
        
        idTxtFld = new JTextField();
        rentalDateTxtFld = new JTextField();
        returnDateTxtFld = new JTextField();
        libIDTxtFld = new JTextField();
        membIDTxtFld = new JTextField();
        bookIDTxtFld = new JTextField();
        
        center.add(createLabel("Loan ID"));
        center.add(idTxtFld);       
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);       
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
       rentalDateTxtFld.setText("");
       returnDateTxtFld.setText("");
       libIDTxtFld.setText("");   
       membIDTxtFld.setText(""); 
       bookIDTxtFld.setText("");
    }
        
    private boolean validateFields() //Method to ensure that all fields are not empty
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
        if (event.getSource() == viewLoanButton)
        {
            ViewLoan viewLoan = new ViewLoan();
        }
        
        if(event.getSource() == selectLoanButton)
        {
            if(vl.checkLoanById(idTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Loan ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Loan tempLoan = vl.returnLoanById(idTxtFld.getText());
                rentalDateTxtFld.setText(sdf.format(tempLoan.getRentalDate())); 
                returnDateTxtFld.setText(sdf.format(tempLoan.getReturnDate()));
                libIDTxtFld.setText(tempLoan.getLibrarianID());
                membIDTxtFld.setText(tempLoan.getMembID());
                bookIDTxtFld.setText(tempLoan.getBookID());
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
            Loan tempLoan = vl.returnLoanById(idTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete Loan "+tempLoan.getLoanId() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                vl.removeLoan(tempLoan);
                vl.saveToFile();
                clearFields();
                idTxtFld.setEnabled(true);
            }
        }
        
        if (event.getSource()==saveButton)
        {
            if(validateFields()== true){
                JOptionPane.showMessageDialog(null,"You have empty fields!","Empty fields", JOptionPane.ERROR_MESSAGE);
            }else
            {
                try
                {
                    Loan tempLoan = vl.returnLoanById(idTxtFld.getText());
                    tempLoan.setRentalDate(sdf.parse(rentalDateTxtFld.getText()));
                    tempLoan.setReturnDate(sdf.parse(returnDateTxtFld.getText()));
                    tempLoan.setLibrarianID(libIDTxtFld.getText());
                    tempLoan.setMembID(membIDTxtFld.getText());
                    vl.saveToFile();
                    JOptionPane.showMessageDialog(null,"Loan updated successfully!","Edit a Loan",JOptionPane.INFORMATION_MESSAGE);
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
