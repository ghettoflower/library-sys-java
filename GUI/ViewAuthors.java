package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewAuthors extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorAuthor va;
    
    public ViewAuthors()
    {
        super("View Authors by surname");
        this.setLayout(new BorderLayout());
        va = new VectorAuthor();
        va.readFromFile();
        va.sortAuthorBySurname();
        
        int numOfAuthors = va.getSize();
        int count = 0;
        
        Author tempAuthor = new Author();
        String [] tableHeader = {"Author ID", "Author Name", "Author Surname", "Nationality", "Year of Birth", "Year of Death", "Genre"};
        Object [][] tableContent = new Object[numOfAuthors][7];
        
        for(int i = 0; i < numOfAuthors; i++)
        {
            tempAuthor = va.getAuthorByIndex(count);
            tableContent[i][0] = tempAuthor.getAuthorID();
            tableContent[i][1] = tempAuthor.getName();
            tableContent[i][2] = tempAuthor.getSurname();
            tableContent[i][3] = tempAuthor.getNationality();
            tableContent[i][4] = tempAuthor.getYearOfBirth();
            tableContent[i][5] = tempAuthor.getYearOfDeath();
            tableContent[i][6] = tempAuthor.getGenre();
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