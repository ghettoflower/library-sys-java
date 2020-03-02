package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewLibrarians extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorLibrarian vl;
    
    public ViewLibrarians()
    {
        super("View Librarians by Surname");
        this.setLayout(new BorderLayout());
        vl = new VectorLibrarian();
        vl.readFromFile();
        vl.sortLibrarianBySurname();
        
        int numOfLibrarians = vl.getSize();
        int count = 0;
        
        Librarian tempLibrarian = new Librarian();
        String [] tableHeader = {"Person ID", "Librarian Name", "Librarian Surname", "Date of Birth", "Address"};
        Object [][] tableContent = new Object[numOfLibrarians][5];
        
        for(int i = 0; i < numOfLibrarians; i++)
        {
            tempLibrarian = vl.getLibrarianByIndex(count);
            tableContent[i][0] = tempLibrarian.getPersonId();
            tableContent[i][1] = tempLibrarian.getName();
            tableContent[i][2] = tempLibrarian.getSurname();
            tableContent[i][3] = tempLibrarian.getDateOfBirth();
            tableContent[i][4] = tempLibrarian.getAddress();
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
