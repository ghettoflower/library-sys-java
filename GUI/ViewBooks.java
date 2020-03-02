package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewBooks extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorBook vb;
    
    public ViewBooks()
    {
        super("View Books by Author");
        this.setLayout(new BorderLayout());
        vb = new VectorBook();
        vb.readFromFile();
        vb.sortBookByAuthorSurname();
        
        int numOfBooks = vb.getSize();
        int count = 0;
        
        Book tempBook = new Book();
        String [] tableHeader = {"Title", "ID", "Author Name", "Author Surname", "Release Date", "Publisher", "Genre"};
        Object [][] tableContent = new Object[numOfBooks][7];
        
        for(int i = 0; i < numOfBooks; i++)
        {
            tempBook = vb.getBookByIndex(count);
            tableContent[i][0] = tempBook.getTitle();
            tableContent[i][1] = tempBook.getItemID();
            tableContent[i][2] = tempBook.getAuthorName();
            tableContent[i][3] = tempBook.getAuthorSurname();
            tableContent[i][4] = tempBook.getReleaseDate();
            tableContent[i][5] = tempBook.getPublisher();
            tableContent[i][6] = tempBook.getGenre();
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
