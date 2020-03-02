package GUI; //Class is located in the package GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat; //
import java.util.Date; //Imports Date class
import java.text.ParseException;
import Logic.*;

public class EditMembers extends JFrame implements ActionListener
{
    private JPanel north, south, east, west, center, searchPanel;
    private JLabel titleLabel, westLabel, eastLabel;
    private JTextField idTxtFld, nameTxtFld, surnameTxtFld, dobTxtFld, memberTypeTxtFld, addressTxtFld, dateMemberStartTxtFld, dateMemberEndTxtFld;
    private JButton deleteButton, clearButton, saveButton, exitButton, viewMembersButton, selectMembersButton;
    private VectorMember vm;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Simple Date Format will parse the date
    
    public EditMembers()
    {
        super("Editing a Member");
        this.setLayout(new BorderLayout());
        vm = new VectorMember();
        vm.readFromFile();
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel= new JLabel("Editing a Member");
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
        viewMembersButton = new JButton("View");
        viewMembersButton.addActionListener(this);
        selectMembersButton = new JButton("Select");
        selectMembersButton.addActionListener(this);
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(viewMembersButton);
        searchPanel.add(selectMembersButton);
        
        idTxtFld = new JTextField();
        nameTxtFld = new JTextField();
        surnameTxtFld = new JTextField();
        dobTxtFld = new JTextField();
        memberTypeTxtFld = new JTextField();
        addressTxtFld = new JTextField();
        dateMemberStartTxtFld = new JTextField();
        dateMemberEndTxtFld = new JTextField();  
        
        center.add(createLabel("ID"));
        center.add(idTxtFld);       
        center.add(createLabel("  ")); //Blank Space
        center.add(searchPanel);        
        center.add(createLabel("Name"));
        center.add(nameTxtFld);       
        center.add(createLabel("Surname"));
        center.add(surnameTxtFld);
        center.add(createLabel("Date of Birth"));
        center.add(dobTxtFld);
        center.add(createLabel("Member Type"));
        center.add(memberTypeTxtFld);
        center.add(createLabel("Address"));
        center.add(addressTxtFld);
        center.add(createLabel("Date Membership Started"));
        center.add(dateMemberStartTxtFld);
        center.add(createLabel("Date Membership Ends"));
        center.add(dateMemberEndTxtFld);  
        
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
    
    private void clearFields() //Method to clear all text fields
    {
        idTxtFld.setText("");
        nameTxtFld.setText("");
        surnameTxtFld.setText("");
        dobTxtFld.setText("");
        memberTypeTxtFld.setText("");
        addressTxtFld.setText("");
        dateMemberStartTxtFld.setText("");       
        dateMemberEndTxtFld.setText("");    
    }
        
    private boolean validateFields() //Method to ensure that all fields are not empty
    {
        boolean flag=false;
        if(idTxtFld.getText().equals("") 
            || nameTxtFld.getText().equals("")
            || surnameTxtFld.getText().equals("")
            || dobTxtFld.getText().equals("")
            || memberTypeTxtFld.getText().equals("")
            || addressTxtFld.getText().equals("")
            || dateMemberStartTxtFld.getText().equals("")
            || dateMemberEndTxtFld.getText().equals(""))
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
        if (event.getSource() == viewMembersButton)
        {
            ViewMembers viewMembers = new ViewMembers();
        }
        
        if(event.getSource() == selectMembersButton)
        {
            if(vm.checkMemberID(idTxtFld.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"ID does not exist!","Member ID check", JOptionPane.ERROR_MESSAGE);
            }else
            {
                Member tempMember = vm.getMemberByID(idTxtFld.getText());
                nameTxtFld.setText(tempMember.getName()); 
                surnameTxtFld.setText(tempMember.getSurname());
                dobTxtFld.setText(sdf.format(tempMember.getDateOfBirth()));
                memberTypeTxtFld.setText(tempMember.getMemberType()); 
                addressTxtFld.setText(tempMember.getAddress());  
                dateMemberStartTxtFld.setText(sdf.format(tempMember.getDateMemberStart()));
                dateMemberEndTxtFld.setText(sdf.format(tempMember.getDateMemberEnd()));                
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
            Member tempMember = vm.getMemberByID(idTxtFld.getText());
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete "+tempMember.getName() + " " + tempMember.getSurname() + "?","WARNING",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                //yes option
                vm.removeMember(tempMember);
                vm.saveToFile();
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
                    Member tempMember = vm.getMemberByID(idTxtFld.getText());
                    tempMember.setName(nameTxtFld.getText());
                    tempMember.setSurname(surnameTxtFld.getText());
                    tempMember.setDateOfBirth(sdf.parse(dobTxtFld.getText()));
                    tempMember.setMemberType(memberTypeTxtFld.getText());
                    tempMember.setAddress(addressTxtFld.getText());
                    tempMember.setDateMemberStart(sdf.parse(dateMemberStartTxtFld.getText()));
                    tempMember.setDateMemberEnd(sdf.parse(dateMemberEndTxtFld.getText()));
                    vm.saveToFile();
                    JOptionPane.showMessageDialog(null,"Member updated successfully!","Edit a Member",JOptionPane.INFORMATION_MESSAGE);
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
