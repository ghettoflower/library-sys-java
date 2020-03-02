    package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewMembers extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorMember vm;
    
    public ViewMembers()
    {
        super("View Members by Surname");
        this.setLayout(new BorderLayout());
        vm = new VectorMember();
        vm.readFromFile();
        vm.sortMemberBySurname();
        
        int numOfMembers = vm.getSize();
        int count = 0;
        
        Member tempMember = new Member();
        String [] tableHeader = {"Person ID", "Member Name", "Member Surname", "Date of Birth", "Member Type", "Address", "Date Membership Started", "Date Membership Ends"};
        Object [][] tableContent = new Object[numOfMembers][8];
        
        for(int i = 0; i < numOfMembers; i++)
        {
            tempMember = vm.getMemberByIndex(count);
            tableContent[i][0] = tempMember.getPersonId();
            tableContent[i][1] = tempMember.getName();
            tableContent[i][2] = tempMember.getSurname();
            tableContent[i][3] = tempMember.getDateOfBirth();
            tableContent[i][4] = tempMember.getMemberType();
            tableContent[i][5] = tempMember.getAddress();
            tableContent[i][6] = tempMember.getDateMemberStart();
            tableContent[i][7] = tempMember.getDateMemberEnd();
            count++;
        }   
        
        table = new JTable(tableContent, tableHeader);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        this.add(table.getTableHeader(), BorderLayout.NORTH);
        this.add(table, BorderLayout.CENTER);
        
        south = new JPanel();
        south.setLayout(new FlowLayout());
        this.add(south, BorderLayout.SOUTH);
        exitButton = new JButton("Exit");
        south.add(exitButton);
        exitButton.addActionListener(this);
        
        this.setSize(800, 600);
        this.setLocation(100, 50);
        this.setVisible(true);
    }    
    
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() instanceof JButton)
        {
            if(event.getSource().equals(exitButton))
            {
                MainWindow mw = new MainWindow();
                this.dispose();
            }    
        }   
    }   
}
