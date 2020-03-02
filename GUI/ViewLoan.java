package GUI; //Class is located in the package GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Logic.*;

public class ViewLoan extends JFrame implements ActionListener
{
    private JPanel south;
    private JTable table;
    private JButton exitButton;
    private VectorLoan vl;
    
    public ViewLoan()
    {
        super("View Loan by Surname");
        this.setLayout(new BorderLayout());
        vl = new VectorLoan();
        vl.readFromFile();
        vl.sortLoanByReturnDate();
        
        int numOfLoans = vl.getSize();
        int count = 0;
        
        Loan tempLoan = new Loan();
        String [] tableHeader = {"Loan ID", "Rental Date", "Return Date", "Librarian ID", "Member ID", "Book ID"};
        Object [][] tableContent = new Object[numOfLoans][6];
        
        for(int i = 0; i < numOfLoans; i++)
        {
            tempLoan = vl.getLoanByIndex(count);
            tableContent[i][0] = tempLoan.getLoanId();
            tableContent[i][1] = tempLoan.getRentalDate();
            tableContent[i][2] = tempLoan.getReturnDate();
            tableContent[i][3] = tempLoan.getLibrarianID();
            tableContent[i][4] = tempLoan.getMembID();
            tableContent[i][5] = tempLoan.getBookID();
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