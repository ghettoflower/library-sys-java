package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewReservations extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorReservations vr;
    
    public ViewReservations()
    {
        super("View Reservations by Surname");
        this.setLayout(new BorderLayout());
        vr = new VectorReservations();
        vr.readFromFile();
        vr.sortReservationByDate();
        
        int numOfReservations = vr.getSize();
        int count = 0;
        
        Reservations tempReservation = new Reservations();
        String [] tableHeader = {"Reservation ID", "Member ID", "Reservation Date", "Journal ID"};
        Object [][] tableContent = new Object[numOfReservations][4];
        
        for(int i = 0; i < numOfReservations; i++)
        {
            tempReservation = vr.getReservationByIndex(count);
            tableContent[i][0] = tempReservation.getResID();
            tableContent[i][1] = tempReservation.getMemID();
            tableContent[i][2] = tempReservation.getResDate();
            tableContent[i][3] = tempReservation.getJournalID();
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