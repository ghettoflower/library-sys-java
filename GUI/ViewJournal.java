package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewJournal extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorJournal vj;
    
    public ViewJournal()
    {
        super("View Journal by Surname");
        this.setLayout(new BorderLayout());
        vj = new VectorJournal();
        vj.readFromFile();
        vj.sortJournalByAuthorSurname();
        
        int numOfJournals = vj.getSize();
        int count = 0;
        
        Journal tempJournal = new Journal();
        String [] tableHeader = {"Title", "Journal ID", "Author Name", "Author Surname", "Release Date", "Publisher", "Area of Study"};
        Object [][] tableContent = new Object[numOfJournals][7];
        
        for(int i = 0; i < numOfJournals; i++)
        {
            tempJournal = vj.getJournalByIndex(count);
            tableContent[i][0] = tempJournal.getTitle();
            tableContent[i][1] = tempJournal.getItemID();
            tableContent[i][2] = tempJournal.getAuthorName();
            tableContent[i][3] = tempJournal.getAuthorSurname();
            tableContent[i][4] = tempJournal.getReleaseDate();
            tableContent[i][5] = tempJournal.getPublisher();
            tableContent[i][6] = tempJournal.getAreaOfStudy();
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