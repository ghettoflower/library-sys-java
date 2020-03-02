package GUI; //Class is located in the package GUI
import java.awt.*; //Imports entire Abstract Window Toolkit API for developing GUI
import java.awt.event.*; //Imports entire API for event
import javax.swing.*; //Imports GUI toolkit API

public class MainWindow extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;    
    private JPanel north, south, west, east, center; //Declares 5 different panels; window split in 5 in this case
    private JLabel titleLabel, westLabel, eastLabel, centerLabel; //Declares labels which will later disploy information in the different panels
    private ImageIcon libraryImage; //Declares an icon which will be used as an image to on the main window
    private JButton exitButton; //Declares a button which will later be used to properly exit the system when clicked
    
    public MainWindow()
    {
        super("Alexandria Library"); //Displays name in the window's title bar
        this.setLayout(new BorderLayout());
        
        //Building the Menu bar
        menuBar = new JMenuBar();
        
        //Building the File menu
        menu = new JMenu("System");
        menuBar.add(menu);
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        
        //Building the Add menu
        menu = new JMenu("Add");
        menuBar.add(menu);
        menuItem = new JMenuItem("Member");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Librarian");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Author");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Book");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Journal");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Reservation");
        menuItem.addActionListener(this);
        menu.add(menuItem);                
        menuItem = new JMenuItem("Loan");
        menuItem.addActionListener(this);
        menu.add(menuItem);           
        
        //Building the View Menu
        menu = new JMenu("View");
        menuBar.add(menu);
        menuItem = new JMenuItem("Member");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Librarian");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Author");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Book");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Journal");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Reservation");
        menuItem.addActionListener(this);
        menu.add(menuItem);                
        menuItem = new JMenuItem("Loan");
        menuItem.addActionListener(this);
        menu.add(menuItem);               
        
        //Building the Edit Menu
        menu = new JMenu("Edit");
        menuBar.add(menu);
        menuItem = new JMenuItem("Member");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Librarian");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Author");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem = new JMenuItem("Book");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Journal");
        menuItem.addActionListener(this);
        menu.add(menuItem);        
        menuItem = new JMenuItem("Reservation");
        menuItem.addActionListener(this);
        menu.add(menuItem);                
        menuItem = new JMenuItem("Loan");
        menuItem.addActionListener(this);
        menu.add(menuItem);               
        
        //North Panel
        north = new JPanel();
        this.add(north,BorderLayout.NORTH);
        titleLabel = new JLabel("Alexandria Library System"); //Assigns name to titleLabel
        titleLabel.setFont(new Font("Arial",Font.BOLD,22)); //Sets the font of the name saved in titleLabel. In this case it is Arial, bold and size 22
        titleLabel.setForeground(Color.black); //Sets the colour of the font used as black
        north.add(titleLabel); //Displays the name of the library in the top of the window itself
        
        //West and East Panels
        west = new JPanel();
        east = new JPanel();
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        westLabel = new JLabel("          "); //Will display blank space so that the image is centred properly
        eastLabel = new JLabel("          ");
        west.add(westLabel); //Displays label on the west panel
        east.add(eastLabel); //Displayes label on the east panel
        
        //Center Panel
        center = new JPanel();
        this.add(center, BorderLayout.CENTER);
        libraryImage = new ImageIcon(getClass().getResource("librarysystem.jpg")); //Creates an instance of the image.
        centerLabel = new JLabel(libraryImage); //Assigns library image as centreLabel. Not displayed here yet, just saved
        center.add(centerLabel); //Displays image in the centre of the window
        
        //South Panel
        south = new JPanel();
        south.setLayout(new FlowLayout());
        this.add(south, BorderLayout.SOUTH);
        exitButton = new JButton("Exit"); //Exit button labelled as "Exit"
        exitButton.addActionListener(this); //Adds action to the button - when clicked it will close the window
        south.add(exitButton); //Displays the exit button
        
        this.setJMenuBar(menuBar);        
        this.setSize(600,500); //Sets size of window as 600x500
        this.setLocation(50,50); //Sets default location of the window
        this.setVisible(true); //Sets window visibility on
    }   
    
    public void actionPerformed(ActionEvent event) //Method invoked when an action occurs. Parameter declares instance of ActionEvent class
    {
        if (event.getSource() == exitButton)
        {
            if(JOptionPane.showConfirmDialog(null,"Are you sure you wish to exit?","EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //Displays a yes or no option panel
            {
                this.dispose(); //Closes the JFrame window
            }            
        }
        if (event.getSource() instanceof JMenuItem)
        {    
            JMenuItem jmi = (JMenuItem) event.getSource();
            JPopupMenu jpm = (JPopupMenu) jmi.getParent();
            JMenu menuChoice = (JMenu) jpm.getInvoker();
            if(menuChoice.getText().equals("Add"))
            {
                if (jmi.getText().equals("Member"))
                {
                    AddMember am = new AddMember();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Librarian"))
                {
                    AddLibrarian al = new AddLibrarian();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Author"))
                {
                    AddAuthor aa = new AddAuthor();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Book"))
                {
                    AddBook ab = new AddBook();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Journal"))
                {
                    AddJournal aj = new AddJournal();
                    this.dispose(); //Closes the JFrame window                    
                }      
                if (jmi.getText().equals("Reservations"))
                {
                    AddReservations ar = new AddReservations();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Loan"))
                {
                    AddLoan alo = new AddLoan();
                    this.dispose(); //Closes the JFrame window                    
                }                 
            }
            if (menuChoice.getText().equals("Edit"))
            {
                if (jmi.getText().equals("Member"))
                {
                    EditMembers em = new EditMembers();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Librarian"))
                {
                    EditLibrarians el = new EditLibrarians();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Author"))
                {
                    EditAuthors ea = new EditAuthors();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Book"))
                {
                    EditBook eb = new EditBook();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Journal"))
                {
                    EditJournal ej = new EditJournal();
                    this.dispose(); //Closes the JFrame window                    
                }      
                if (jmi.getText().equals("Reservation"))
                {
                    EditReservations er = new EditReservations();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Loan"))
                {
                    EditLoan elo = new EditLoan();
                    this.dispose(); //Closes the JFrame window                    
                }
            }       
            if(menuChoice.getText().equals("View"))
            {
                if (jmi.getText().equals("Member"))
                {
                    ViewMembers vm = new ViewMembers();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Librarian"))
                {
                    ViewLibrarians vl = new ViewLibrarians();
                    this.dispose(); //Closes the JFrame window                    
                }
                if (jmi.getText().equals("Author"))
                {
                    ViewAuthors va = new ViewAuthors();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Book"))
                {
                    ViewBooks vb = new ViewBooks();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Journal"))
                {
                    ViewJournal vj = new ViewJournal();
                    this.dispose(); //Closes the JFrame window                    
                }      
                if (jmi.getText().equals("Reservation"))
                {
                    ViewReservations vr = new ViewReservations();
                    this.dispose(); //Closes the JFrame window                    
                } 
                if (jmi.getText().equals("Loan"))
                {
                    ViewLoan vlo = new ViewLoan();
                    this.dispose(); //Closes the JFrame window                    
                }                 
            }           
            if(menuChoice.getText().equals("System"))
            {
                if (jmi.getText().equals("Exit"))
                {
                    this.dispose(); //Closes the JFrame window
                }
            }
        }
    }
}
