package ie.gmit.clientGUI;

//Package Imports
import ie.gmit.clientEvents.ClientEventHandler;
import ie.gmit.clientEvents.ListHandler;
import ie.gmit.clientEvents.MenuEventHandler;
import ie.gmit.clientEvents.UIEventHandler;
import ie.gmit.clientFunctional.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
//ApplicationMainWindow class extending JFrame to represent the application main window
public class ApplicationMainWindow extends JFrame 
{
	//Client connection object (defined in package ie.gmit.clientFunctional)
	Client client = new Client("40.114.201.52");							
	
	//Menubar, Menu's and Menu option items
	public static JMenuBar appMenuBar = new JMenuBar();					
	private JMenu userOptionsMenu, staffOptionsMenu, fileOptionsMenu, houseOptionsMenu,transactionsOptionsMenu,
				  customerOptionsMenu, reminderOptionsMenu, searchOptionsMenu;										
	public static JMenuItem createUser, updateUser, deleteUser, createStaffMember, updateStaffMember, 
							deleteStaffMember, viewStaffMembers, uploadFile, downloadFile, sendFile,
							removeFile, addHouse, deleteHouse, viewHouses, addTransaction, deleteTransaction,
							updateTransaction, updateHouse, viewTransactions, viewFile, addCustomer, deleteCustomer,
							updateCustomer, viewCustomers, addReminder, viewReminder, updateReminder, deleteReminder,
							dynamicSearch;
	//Multiple Event handler objects (defined in package ie.gmit.Events)
	MenuEventHandler menuHandler = new MenuEventHandler();				
	UIEventHandler UIhandler = new UIEventHandler();
	ListHandler listHandler = new ListHandler();
	ClientEventHandler clientHandler = new ClientEventHandler(client);  //Handles connection with server
	
	//GUI Objects and variables
	public static JPasswordField passwordField;
	public static JPanel pnlAuthentication, pnlCreateNewUser, pnlUpdateUser, pnlDeleteUser, pnlCreateStaffMember,
						 pnlUpdateStaffMember, pnlDeleteStaffMember,pnlStaffMemberShowArea, pnlUploadFile, pnlDownloadFile,
						 pnlSendFile, pnlRemoveFile, pnlCreateHouse, pnlUpdateHouse, pnlDeleteHouse, pnlHouseShowArea, pnlViewFile,
						 pnlCreateTransaction, pnlDeleteTransaction, pnlUpdateTransaction, pnlViewTransaction,
						 pnlAddCustomer, pnlDeleteCustomer, pnlUpdateCustomer, pnlCustomerShowArea, pnlCreateReminder,
						 pnlUpdateReminder, pnlDeleteReminder, pnlReminderShowArea, pnlDynamicSearch;
	public static JTextField txtUsername, txtNewUsername, txtNewPassword, txtOldUsername, txtOldPassword,
							 txtDeleteUsername, txtDeletePassword, txtUpdatedUsername, txtUpdatedPassword, txtNewStaffFirstName,
							 txtNewStaffLastName, txtNewStaffSalary, txtNewStaffAddress, txtNewStaffPPS, txtStaffIDUser,
							 txtStaffUpdateID, txtStaffUpdateFirstName, txtStaffUpdateLastName, txtStaffUpdateAddress,
							 txtStaffUpdateSalary, txtStaffUpdatePPS, txtSearchStaffEdit, txtDeleteStaffMember,
							 txtSearchStaffMember, txtUploadFile, txtDownloadFile, txtDestDownloadFile, txtUsernameSendFile,
							 txtCompPathSendFile,txtUsernameSendFileO, txtOnlineNameSendFile, txtRemoveFile, txtNewHouseStreet,
							 txtNewHouseCounty, txtNewHouseTown, txtUpdatedHouseStreet, txtUpdatedHouseTown, txtUpdatedHouseCounty,
							 txtSearchHouseEdit, txtDeleteHouse, txtSearchHouse, txtTransRentHouseID, txtTransStartDate, txtTransEndDate,
							 txtTransMonthlyRate, txtEstateAgentID, txtBuyEstateAgentID, txtBuyCost, txtTransBuyHouseID,
							 txtDeleteRentTransaction, txtDeleteSellTransaction, txtTransUpdateRentID, txtTransUpdateStartDate,
							 txtTransUpdateEndDate, txtTransUpdateMonthlyRate, txtUpdateEstateAgentID, txtTransUpdateBuyID, 
							 txtUpdateBuyCost, txtUpdateBuyEstateAgentID, txtSearchViewRentTrans, txtSearchViewBuyTrans,
							 txtRentCustID, txtBuyCustID, txtUpdateRentCustID, txtUpdateBuyCustID, txtNewCustomerAddress, txtNewCustomerNameL,
							 txtNewCustomerNameF, txtDeleteCustomer, txtCustomerUpdateAddress, txtCustomerUpdateLastName, txtCustomerUpdateFirstName,
							 txtSearchCustomerEdit, txtSearchCustomer, txtTransUpdateHouseRentID, txtTransUpdateBuyHouseID, txtCreateReminderDate,
							 txtCreateReminderTime, txtCreateReminderDesc, txtCreateReminderSubject, txtUpdateReminderDate, txtUpdateReminderTime,
							 txtUpdateReminderSubject, txtUpdateReminderDesc, txtSearchReminderEdit, txtDeleteReminder, txtSearchReminder,
							 txtStaffFirstName, txtStaffLastName, txtHouseTown, txtHouseCounty, txtCustFName, txtCustLName, txtCustAddress,
							 txtTransBuyCounty, txtTransBuyCost, txtTransBuyCustomer, txtTransBuyEstateAgent, txtTransRentCounty, txtTransRentRate,
							 txtTransRentCustomer, txtTransRentEstateAgent; 				 
	private JLabel lblUsername, lblPassword, lblNewUsername, lblNewPassword, lblOldUsername, lblOldPassword, lblDeleteUsername,
				   lblDeletePassword, lblUserType, lblUserTypes, lblOldStaffType, lblNewStaffType, lblDeleteStaffType,
				   lblStaffFirstName,lblStaffLastName, lblStaffAddress, lblStaffPPS, lblStaffSalary, lblStaffEmpType,
				   lblStaffIDUser, lblStaffUpdatePPS, lblStaffUpdateAddress, lblStaffUpdateLastName,
				   lblStaffUpdateSalary, lblStaffUpdateEmpType,lblStaffUpdateFirstName, lblDeleteStaffMember, lblUploadFile,
				   lblDownloadFile, lblDestDownloadFile, lblRemoveFile, lblNewHouseStreet, lblNewHouseTown, lblNewHouseCounty,
				   lblNewRentOrSell, lblUpdatedHouseStreet, lblUpdatedHouseTown, lblUpdatedHouseCounty, lblUpdatedRentOrSell,
				   lblDeleteHouse, lblTransactionOption, lblTransactionDeleteOption, lblUpdateTransactionOption, lblViewTransactionOption,
				   lblNewCustomerNameF, lblNewCustomerNameL, lblNewCustomerAddress, lblCustomerUpdateFirstName, lblCustomerUpdateLastName,
				   lblCustomerUpdateAddress, lblCreateReminderDate, lblCreateReminderTime, lblUpdateReminderDate, lblUpdateReminderTime,
				   lblUpdateReminderSubject, lblUpdateReminderDesc;
	//The follow JTables and JScrollPanes are instantiated otherwise we would get null pointer exceptions
	public static JTable tblShowStaff = new JTable();
	public static JTable tblShowHouses = new JTable();
	public static JTable tblShowTransactions = new JTable();
	public static JTable tblShowCustomer = new JTable();
	public static JTable tblShowReminder = new JTable();
	public static JTable tblShowDynamicResult = new JTable();
	public static JScrollPane scr1Staff = new JScrollPane();
	public static JScrollPane scr1House = new JScrollPane();
	public static JScrollPane scr1Transactions = new JScrollPane();
	public static JScrollPane scr1Customer = new JScrollPane();
	public static JScrollPane scr1Reminder = new JScrollPane();
	public static JScrollPane scr1Results = new JScrollPane();
    public static JLabel lblViewStaffMember, lblEnterIDStaff, lblUsernameSendFile, lblCompPathSendFile, lblUsernameSendFileO,
    					 lblOnlineNameSendFile, lblEnterIDHouse, lblViewHouses, lblTransRentHouseID, lblTransStartDate, lblTransEndDate,
    					 lblTransMonthlyRate, lblRentEstateAgentID, lblBuyEstateAgentID, lblBuyCost,lblTransBuyHouseID,
    					 lblDeleteSellTransaction, lblDeleteRentTransaction, lblTransUpdateRentID, lblTransUpdateStartDate,
    					 lblTransUpdateEndDate, lblRentUpdateEstateAgentID, lblTransUpdateBuyID, lblUpdateBuyCost,
    					 lblUpdateBuyEstateAgentID, lblTransUpdateMonthlyRate, lblViewTransactionRent, lblViewTransactionBuy, lblRentCustID,
    					 lblBuyCustID, lblUpdateRentCustID, lblUpdateBuyCustID, lblDeleteCustomer, lblEnterIDCustomer, lblViewCustomer,
    					 lblTransUpdateHouseRentID, lblTransUpdateBuyHouseID, lblCreateReminderSubject, lblCreateReminderDesc, lblEnterIDReminder,
    					 lblDeleteReminder, lblViewReminder, lblSearchEntity, lblSearchStaffType, lblSearchHouseType, lblSearchTransactionType,
    					 lblSearchCustomerType, lblSearchStaffFName, lblSearchStaffLName, lblSearchStaffEmp, lblSearchHouseTown, lblSearchHouseCounty,
    					 lblSearchCustFName, lblSearchCustLName, lblSearchCustAddress, lblSearchBuyTransactionType, lblSearchTransBuyCounty,
    					 lblSearchTransBuyCost, lblSearchTransBuyCustomer, lblSearchTransBuyEstateAgent, lblSearchRentTransactionType,
    					 lblSearchTransRentCounty, lblSearchTransRentRate, lblSearchTransRentCustomer, lblSearchTransRentEstateAgent;
    public static JTextArea showAllStaff, showAllFiles, showSendableFiles, showAllRemoveableFiles, showAllHouses, showFiles,
    						showAllTransactions, showAllCustomers, showAllReminder, showAllResults;
	public static JComboBox<String> cmbStaffLoginOptions, cmbCreateUserOptions, cmbUpdateUserOldType, cmbUpdateUserNewType,
									cmbDeleteUserType, cmbCreateStaffOptions, cmbUpdateStaffOptions, cmbSendFileOptions,
									cmbCreateHouseOptions, cmbUpdateHouseOptions, cmbTransactionOptions, cmbDeleteTransactionOptions,
									cmbTransactionUpdateOptions, cmbTransactionViewOptions, cmbDynamicSearchEntities, cmbDynamicStaffSearchType,
									cmbDynamicHouseSearchType, cmbDynamicTransactionSearchType, cmbDynamicCustomerSearchType, cmbStaffSearchEmpType,
									cmbDynamicBuyTransactionSearchType, cmbDynamicRentTransactionSearchType;
	private JButton btnEnterButton, btnCreateUser, btnEndCreateUser, btnUpdateUser, btnEndUpdateUser,
					btnDeleteUser, btnCancelDeleteUser, btnCreateStaff, btnCancelCreateStaff, btnDeleteStaffMember,
					btnCancelDeleteStaffMember, btnExitUpdateStaff, btnUploadFile, btnCancelUploadFile, btnViewFiles,
					btnDownloadFile, btnCancelDownloadFile, btnCancelSendFiles, btnViewRemovableFiles, btnRemoveFile,
					btnCancelRemoveFile, btnCreateHouse, btnCancelCreateHouse, btnDeleteHouse, btnCancelDeleteHouse,
					btnCancelUpdateHouse, btnViewAllFiles, btnCancelViewFile, btnCancelCreateTransaction, btnCancelDeleteTransaction,
					btnCancelUpdateTransaction, btnCreateCustomer, btnCancelCreateCustomer, btnExitUpdateCustomer,
					btnCreateReminder, btnCancelCreateReminder, btnCancelUpdateReminder, btnCancelDynamicSearch;
    public static JButton btnShowAllStaff, btnSearchForStaff, btnUpdateStaffMember, btnSearchStaffEdit,  
    				      btnCancelShowAllStaff, btnViewSendableFiles, btnSendCompFile, btnSendOnlineFile,
    				      btnSearchHouseEdit, btnUpdateHouse, btnShowAllHouses, btnSearchForHouse, btnCancelShowHouses,
    				      btnCreateRentTrans, btnCreateBuyTrans, btnDeleteRentTransaction, btnDeleteSellTransaction,
    				      btnCancelViewTransaction, btnUpdateRentTrans, btnUpdateBuyTrans, btnSearchUpdateBuyTrans,
    				      btnSearchUpdateRentTrans, btnViewAllRentTrans, btnSearchRentTrans, btnViewAllBuyTrans,
    				      btnSearchBuyTrans, btnDeleteCustomer, btnCancelDeleteCustomer, btnSearchCustomerEdit,
    				      btnShowAllCustomers, btnCancelShowCustomers, btnSearchForCustomer, btnUpdateCustomer, btnRefreshStaffEdit,
    				      btnRefreshHouseEdit, btnRefreshUpdateRentTrans, btnRefreshUpdateBuyTrans, btnRefreshCustEdit, btnSearchReminderEdit,
    				      btnRefreshReminderEdit, btnDeleteReminder, btnCancelDeleteReminder, btnShowAllReminder, btnCancelShowReminder,
    				      btnSearchForReminder, btnDynamicSearchStaffFName, btnDynamicSearchStaffLName, btnDynamicSearchStaffEType, 
    				      btnDynamicSearchHouseRent, btnDynamicSearchHouseBuy, btnDynamicSearchHouseTown, btnDynamicSearchHouseCounty,
    				      btnDynamicSearchCustFName, btnDynamicSearchCustLName, btnDynamicSearchCustAddress, btnUpdateReminder,
    				      btnDynamicSearchBuyTransCounty, btnDynamicSearchBuyTransCost, btnDynamicSearchBuyTransCustomer,
    				      btnDynamicSearchBuyTransEAgent, btnDynamicSearchRentTransCounty, btnDynamicSearchRentTransRentRate,
    				      btnDynamicSearchRentTransCustomer, btnDynamicSearchRentTransEAgent;
	public static JScrollPane scrlFiles, scrlSendbleFiles, scrlRemovableFiles, scrlAllFiles;
	//String arrays for drop down list options
	private String[] userTypeChoices = {"Administrative", "Management", "Estate Agent"};
	private String[] sendFileChoices = {"Choose...","From Online Directory", "From Computer"};
	private String[] houseChoices = {"Choose...","Buy", "Rent"};
	private String[] entitySearchChoices = {"Choose...","Staff", "House", "Customer", "Transactions"};
	private String[] staffSearchChoices = {"Choose...","First Name", "Last Name", "Employment Type"};
	private String[] customerSearchChoices = {"Choose...","First Name", "Last Name", "Address"};
	private String[] houseSearchChoices = {"Choose...", "Rent Houses", "Buy Houses", "Town", "County"};
	private String[] sellTransChoices = {"Choose...", "Find by County", "Find by Cost", "Find by Customer", "Find by Estate Agent"};
	private String[] rentTransChoices = {"Choose...", "Find by County", "Find by Monthly Rate", "Find by Customer", "Find by Estate Agent"};
	//Custom made fonts for controls
	private Font customFont1 = new Font("Sans Serif", Font.BOLD, 15);
	private Font customFont2 = new Font("Verdana", Font.BOLD, 30);
	private Font customFont3 = new Font("Gotham", Font.BOLD, 20);
	private Font customFont4 = new Font("Gotham", Font.ITALIC, 18);
	private Font customFont6 = new Font("Sans Serif", Font.ITALIC, 14);
	private Font customFont7 = new Font("Sans Serif", Font.ITALIC, 12);
			  
	/*ApplicationMainWindow Constructor Builds the Windows attributes and creates various 
	  panels for different tasks*/
	public ApplicationMainWindow()
	{
		//Creating JFrame objects attributes
		this.setTitle("Management System");						//Set window title
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 		    //Set window size to full screen
		this.setContentPane(new JLabel(new ImageIcon("Images/ApplicationMainBackground.jpg")));//Background image
	    this.setLayout(null);									//No specific layout											
	    this.setResizable(true);								//The window can not be resized
	    
	    //Methods that create various panels for performing tasks and a menu bar to select the panels
	    createAuthenticationPanel();			//Panel for logging in
	    createMenuBar();						//Creating menu bar
	    createUserOptionsMenu();				//Creating User menu and options for the menu bar
	    pnlCreateUser();						//Create panel for adding a new user
	    pnlUpdateUser();						//Create panel for updating a user
	    pnlDeleteUser();						//Create panel for deleting a user
	    staffOptionsMenu();						//Create Staff menu options for the menu bar
	    pnlCreateStaffMember();					//Create panel for adding a staff member
	    pnlUpdateStaffMember();					//Create panel for updating a staff member
	    deleteStaffMemberPanel();				//Panel to delete staff member
	    createStaffShowArea();					//Create Panel to view staff members
	    fileOptionsMenu();						//Create menu for file options
	    uploadFilePanel();						//Create panel for uploading files
	    downloadFilePanel();					//Create panel for downloading files
	    sendFilePanel();						//Create panel for sending files
	    viewFilePanel();						//Create panel for viewing files
	    removeFilePanel();						//Create panel for removing files
	    houseOptionsMenu();						//Create house options menu
	    addHousePanel();						//Create panel for adding houses
	    updateHousePanel();						//Create panel for updating houses
	    deleteHousePanel();						//Create panel for deleting houses
	    createHouseShowArea();					//Create panel for viewing houses
	    transactionOptionsMenu();				//Create transaction options menu
	    createTransactionPanel();				//Create panel for sending files
	    deleteTransactionPanel();				//Create panel for deleting transactions
	    updateTransactionPanel();				//Create panel for updating transactions
	    viewTransactionsPanel();				//Create panel for viewing transactions
	    customerOptionsMenu();					//Create customer options menu
	    createCustomerPanel();					//Create panel for adding customers
	    deleteCustomerPanel();					//Create panel for deleting customers
	    updateCustomer();						//Create panel for updating customers
	    viewCustomersPanel();					//Create panel for viewing customer
	    reminderOptionsMenu();					//Create reminder options menu
	    createReminderPanel();					//Create panel for new reminders
	    updateReminderPanel(); 					//Create panel for updating reminders
	    deleteReminderPanel();					//Create panel for deleting reminder 
	    viewRemindersPanel();					//Create panel to view reminders
	    searchOptionsMenu();					//Create options menu for searching
	    dynamicSearchPanel();					//Create panel to perform more dynamic searches
	    tblShowTransactions = new JTable();	//Table for viewing buy and rent transactions
	    
	    //Use anonymous class and add window closing event which calls the exit() method
	    addWindowListener(new WindowAdapter() 
	    {
	    	public void windowClosing(WindowEvent event) 
	        {
	    	    onExit();		//Closes all socket connections when the window is closing
	    	}
	    });
	    
	    //Make this window visible to the user and set the default close operation
	    this.setVisible(true);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); //Set exit on close operation
	}//end ApplicationMainWindow constructor
	
	//Create panel and controls for authentication
	private void createAuthenticationPanel()
	{
		//Creating a title and line for titled lined border thickness of 5
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 5);
		
		pnlAuthentication = new JPanel();						//create a panel
		pnlAuthentication.setLayout(null);						//no layout specified
		pnlAuthentication.setSize(500, 275);					//set size
		pnlAuthentication.setLocation(435, 150);				//location in the window
		pnlAuthentication.setBackground(Color.black);			//background colour
		
		lblUsername = new JLabel("Enter Username Here: ");
		lblUsername.setLocation(60, 40);						//Set the labels location
	    lblUsername.setSize(170, 30);							//set its size
		lblUsername.setFont(customFont1);						//set font
	    lblUsername.setForeground(Color.decode("#BE81F7"));		//set foreground colour
		pnlAuthentication.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setLocation(260, 40);						//set the textfields location
		txtUsername.setSize(165, 30);							//set the textfields size
		pnlAuthentication.add(txtUsername);				        //add the textfield to the panel
		
		lblPassword = new JLabel("Enter Password Here: ");
		lblPassword.setLocation(60, 90);						//set the label location
	    lblPassword.setSize(170, 30);							//set the label size
	    lblPassword.setFont(customFont1);						//set the labels font
	    lblPassword.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlAuthentication.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setLocation(260, 90);									//set location of textfield
		passwordField.setSize(165, 30);										//set textfields size
		pnlAuthentication.add(passwordField);					    		//add textfield to the panel
		
		lblUserType = new JLabel("Select a user type: ");
		lblUserType.setLocation(60, 140);						//set the label location
		lblUserType.setSize(170, 30);							//set the label size
		lblUserType.setFont(customFont1);						//set the labels font
		lblUserType.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlAuthentication.add(lblUserType);
		
		cmbStaffLoginOptions = new JComboBox<String>(userTypeChoices);	//Create a combo box for staff types
		cmbStaffLoginOptions.setSize(300, 25);							//set its size
		cmbStaffLoginOptions.setLocation(100, 175);						//set its location
		cmbStaffLoginOptions.setMaximumRowCount(3);						//Set max rows	
		cmbStaffLoginOptions.addItemListener(listHandler);				//add an event handler for the combo box
		cmbStaffLoginOptions.setVisible(true);							//set combo box visible to true
		pnlAuthentication.add(cmbStaffLoginOptions);					//add combo box to the panel
		
		//Create a button for user to submit his/her password and username 
		btnEnterButton = new JButton("Login");
	    btnEnterButton.setSize(170, 40);							//set size of the button
	    btnEnterButton.setLocation(165, 210);						//set buttons location was 65
	    btnEnterButton.setFont(customFont2);						//set custom font for button
	    btnEnterButton.setBackground(Color.decode("#BE81F7"));		//set button background colour
	    btnEnterButton.setForeground(Color.decode("#4C0B5F"));		//set button foreground colour
	    btnEnterButton.setActionCommand("enterAuthenticationInfo");	//action command to reference in event handler
	    btnEnterButton.addActionListener(clientHandler);
	    pnlAuthentication.add(btnEnterButton);				    	//add the button to the window

	    //Creating a titled, lined border
	    title = BorderFactory.createTitledBorder(borderLine, "Authentication Window");
	    title.setTitleJustification(TitledBorder.CENTER);
	    title.setTitleColor(Color.decode("#BE81F7"));
	    pnlAuthentication.setBorder(title);
	    
	    pnlAuthentication.setVisible(true);					//set authentication panel visibility to true
		
	    this.add(pnlAuthentication);
	}//end of createAuthenticationPanel() method
	
	//Exit method called when window is closing. (User pressing x in top right corner for example)
	private void onExit()
	{
	      client = clientHandler.getClient();	//Return current client object from the client event handler
		  client.sendMessage(2);				//send message to server to say client is logging off
		  client.closeConnections();			//client close connections
	}//end of onExit() method
	
	//Creates the Menu bar
	private void createMenuBar()
	{
		appMenuBar.setLocation(0, 0);			//Location starts in top left corner
		appMenuBar.setVisible(false);			//visible is false (until login is successful)
		this.setJMenuBar(appMenuBar);			//add the menu bar to the window
	}//end createMenuBar()
	
	//Creates a menu called User, gives it some options like add, update, delete a user. The adds the 
	//menu and its options to the menubar for the application
	private void createUserOptionsMenu()
	{
		userOptionsMenu = new JMenu("User");					//Create user options menu 		
		userOptionsMenu.setFont(customFont3);					//Setting some styling for it
		userOptionsMenu.setForeground(Color.decode("#4C0B5F"));
	    appMenuBar.add(userOptionsMenu);						//Adding it to the application menu bar
	    
	    createUser = new JMenuItem("Create User");				//Create menu item (add new user)
	    createUser.setFont(customFont4);						//Setting some styling for it
	    createUser.setForeground(Color.decode("#BE81F7"));
	    userOptionsMenu.add(createUser);						//Adding it to user options menu
	    createUser.addActionListener(menuHandler);				//Giving it an event listener
	    
	    updateUser = new JMenuItem("Update User");				//Create menu item (update a user)
	    updateUser.setFont(customFont4);						//Setting some styling for it
	    updateUser.setForeground(Color.decode("#BE81F7"));
	    userOptionsMenu.add(updateUser);						//Adding it to user options menu
	    updateUser.addActionListener(menuHandler);				//Giving it an event listener
	    
	    deleteUser = new JMenuItem("Delete User");				//Create menu item (delete a user)
	    deleteUser.setFont(customFont4);						//Setting some styling for it
	    deleteUser.setForeground(Color.decode("#BE81F7"));
	    userOptionsMenu.add(deleteUser);						//Adding it to user options menu
	    deleteUser.addActionListener(menuHandler);				//Giving it an event listener
	}//end createUserOptionsMenu()
	
	//Creates a panel and adds the necessary controls to it allowing a new user to be created
	private void pnlCreateUser()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlCreateNewUser = new JPanel();							//create a panel
		pnlCreateNewUser.setLayout(new GridLayout(5,2));			//layout is in grid format 5rows 2columns
		pnlCreateNewUser.setSize(350, 200);							//set the panels size
		pnlCreateNewUser.setLocation(525, 200);						//set the panels location
		pnlCreateNewUser.setBackground(Color.black);				//background colour is black
		
		lblStaffIDUser = new JLabel("    Enter Staff ID: ");		//create a label
		lblStaffIDUser.setFont(customFont6);						//set the labels font
		lblStaffIDUser.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewUsername = new JLabel("    Enter New Username: ");	//create a label
		lblNewUsername.setFont(customFont6);						//set the labels font
		lblNewUsername.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewPassword = new JLabel("    New User Password: ");		//create a label
		lblNewPassword.setFont(customFont6);						//set the labels font
		lblNewPassword.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		txtStaffIDUser  = new JTextField();
		txtNewUsername  = new JTextField();							//textfield for username
		txtNewPassword = new JTextField();							//textfield for password
		lblUserTypes = new JLabel("    Select a User Type: ");		//label
		lblUserTypes.setFont(customFont6);						    //set the labels font
		lblUserTypes.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		cmbCreateUserOptions = new JComboBox<String>(userTypeChoices);		//combo box for staff type
		cmbCreateUserOptions.addItemListener(listHandler);				//event handler for combo box
		btnCreateUser = new JButton("Create New User");				//button to create user
		btnCreateUser.setActionCommand("CreateNewUser");			//action command that links to button
		btnCreateUser.addActionListener(clientHandler);				//event handler for button
		btnEndCreateUser = new JButton("Cancel");					//button to cancel create of user
		btnEndCreateUser.setActionCommand("CancelNewUser");			//action command that links to button
		btnEndCreateUser.addActionListener(UIhandler);				//event handler for button
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 5rows 2columns*/
		pnlCreateNewUser.add(lblStaffIDUser);			//1st row 1st column
		pnlCreateNewUser.add(txtStaffIDUser);			//1st row 2nd column
		pnlCreateNewUser.add(lblNewUsername);			//2nd row 1st column
		pnlCreateNewUser.add(txtNewUsername);			//2nd row 2nd column
		pnlCreateNewUser.add(lblNewPassword);			//3rd row 1st column
		pnlCreateNewUser.add(txtNewPassword);			//3rd row 2nd column etc
		pnlCreateNewUser.add(lblUserTypes);
		pnlCreateNewUser.add(cmbCreateUserOptions);
		pnlCreateNewUser.add(btnCreateUser);
		pnlCreateNewUser.add(btnEndCreateUser);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create User Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCreateNewUser.setBorder(title);
		
		pnlCreateNewUser.setVisible(false);		//Panel to create user is not visible when application starts
		
		this.add(pnlCreateNewUser);				//add the panel to the application window
	}//pnlCreateUser()
	
	//Creates a panel and adds the necessary controls to it allowing a user to be updated
	private void pnlUpdateUser()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlUpdateUser = new JPanel();								//create a panel
		pnlUpdateUser.setLayout(new GridLayout(7,2));				//grid layout 7rows 2columns
		pnlUpdateUser.setSize(500, 300);							//set size of panel
		pnlUpdateUser.setLocation(450, 175);						//set panel location
		pnlUpdateUser.setBackground(Color.black);					//set background colour
		lblOldUsername = new JLabel("    Enter Old Username: ");	//create label
		lblOldUsername.setFont(customFont6);						//set labels styling
		lblOldUsername.setForeground(Color.decode("#BE81F7"));	
		lblNewUsername = new JLabel("    Enter New Username: ");	//create label
		lblNewUsername.setFont(customFont6);						//set the labels font
		lblNewUsername.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblOldPassword = new JLabel("    Enter Old Password: ");	//create label
		lblOldPassword.setFont(customFont6);						//set the labels font
		lblOldPassword.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewPassword = new JLabel("    Enter New Password: ");	//create label
		lblNewPassword.setFont(customFont6);						//set the labels font
		lblNewPassword.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblOldStaffType = new JLabel("    Select Staff Type: ");	//create label
		lblOldStaffType.setFont(customFont6);						//set the labels font
		lblOldStaffType.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewStaffType = new JLabel("    Select New Staff Type: ");	//create label
		lblNewStaffType.setFont(customFont6);						//set the labels font
		lblNewStaffType.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		txtOldUsername  = new JTextField();			//Textfield to hold old/new username and password for update
		txtUpdatedUsername = new JTextField();
		txtOldPassword  = new JTextField();
		txtUpdatedPassword = new JTextField();
		cmbUpdateUserOldType = new JComboBox<String>(userTypeChoices);	//Combo box to select old staff type
		cmbUpdateUserOldType.addItemListener(listHandler);				//add event handler for combo box
		cmbUpdateUserNewType = new JComboBox<String>(userTypeChoices);  //Combo box to select new staff type
		cmbUpdateUserNewType.addItemListener(listHandler);				//add event handler for combo box
		btnUpdateUser = new JButton(" Update User");			//button to update a user
		btnUpdateUser.setActionCommand("UpdateUser");			//action command that links a button to event
		btnUpdateUser.addActionListener(clientHandler);			//add event handler to button
		btnEndUpdateUser = new JButton("Cancel Update");		//button to cancel an update
		btnEndUpdateUser.setActionCommand("CancelUpdateUser");	//action command that links button to an event
		btnEndUpdateUser.addActionListener(UIhandler);			//add event listener to the button
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 7rows 2columns*/
		pnlUpdateUser.add(lblOldUsername);					//1st row 1st column
		pnlUpdateUser.add(txtOldUsername);					//1st row 2nd column
		pnlUpdateUser.add(lblNewUsername);					//2nd row 1st column
		pnlUpdateUser.add(txtUpdatedUsername);				//2nd row 2nd column
		pnlUpdateUser.add(lblOldPassword);					//3rd row 1st column
		pnlUpdateUser.add(txtOldPassword);					//3rd row 2nd column etc
		pnlUpdateUser.add(lblNewPassword);
		pnlUpdateUser.add(txtUpdatedPassword);
		pnlUpdateUser.add(lblOldStaffType);
		pnlUpdateUser.add(cmbUpdateUserOldType);
		pnlUpdateUser.add(lblNewStaffType);
		pnlUpdateUser.add(cmbUpdateUserNewType);
		pnlUpdateUser.add(btnUpdateUser);
		pnlUpdateUser.add(btnEndUpdateUser);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update User Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateUser.setBorder(title);
		
		pnlUpdateUser.setVisible(false);	//Panel to update user is not visible when application starts		
		
		this.add(pnlUpdateUser);			//add the panel to the application window
	}//end pnlUpdateUser()
	
	//Creates a panel and adds the necessary controls to it allowing a user to be deleted
	private void pnlDeleteUser()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlDeleteUser = new JPanel();					    //create a panel
		pnlDeleteUser.setLayout(new GridLayout(4,2));	    //grid layout 4rows 2columns
		pnlDeleteUser.setSize(500, 200);					//set size of panel
		pnlDeleteUser.setLocation(450, 200);				//set location of panel
		pnlDeleteUser.setBackground(Color.black);			//set background of panel
		
		lblDeleteUsername = new JLabel("    Enter Username For Deletion: ");
		lblDeleteUsername.setFont(customFont6);						    //set the labels font
		lblDeleteUsername.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblDeletePassword = new JLabel("    Enter Usernames Password: ");
		lblDeletePassword.setFont(customFont6);						    //set the labels font
		lblDeletePassword.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblDeleteStaffType = new JLabel("    Select Users Staff Type: ");
		lblDeleteStaffType.setFont(customFont6);						//set the labels font
		lblDeleteStaffType.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		cmbDeleteUserType = new JComboBox<String>(userTypeChoices);		//combo box for user choices
		cmbDeleteUserType.addItemListener(listHandler);			   		//add event handler for combo box
		txtDeleteUsername  = new JTextField();							//textfield for username
		txtDeletePassword = new JTextField();					//textfield for password
		btnDeleteUser = new JButton("Delete User");				//button for deleting user
		btnDeleteUser.setActionCommand("DeleteUser");			//action command that links button to an event
		btnDeleteUser.addActionListener(clientHandler);			//add event handler for delete button
		btnCancelDeleteUser = new JButton("Cancel");			//button for cancel delete user
		btnCancelDeleteUser.setActionCommand("CancelDeleteUser");//action command that links button to an event
		btnCancelDeleteUser.addActionListener(UIhandler);		//add event handler for cancel delete button
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 4rows 2columns*/
		pnlDeleteUser.add(lblDeleteUsername);			//1st row 1st column
		pnlDeleteUser.add(txtDeleteUsername);			//1st row 2nd column
		pnlDeleteUser.add(lblDeletePassword);			//2nd row 1st column
		pnlDeleteUser.add(txtDeletePassword);			//2nd row 2nd column etc
		pnlDeleteUser.add(lblDeleteStaffType);
		pnlDeleteUser.add(cmbDeleteUserType);
		pnlDeleteUser.add(btnDeleteUser);
		pnlDeleteUser.add(btnCancelDeleteUser);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete User Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteUser.setBorder(title);
		
		pnlDeleteUser.setVisible(false);	//Panel to delete user is not visible when application starts
		
		this.add(pnlDeleteUser);			//add the panel to the application window
	}//end pnlDeleteUser()
	
	//Creates a menu called Staff, gives it some options like add, update, delete, view a staff member(s). 
	//Them adds the menu and its options to the menu bar for the application
	private void staffOptionsMenu()
	{
		staffOptionsMenu = new JMenu("Staff");						//Create staff options menu 		
		staffOptionsMenu.setFont(customFont3);						//Setting some styling for it
		staffOptionsMenu.setForeground(Color.decode("#4C0B5F"));
	    appMenuBar.add(staffOptionsMenu);							//Adding it to the application menu bar
	    
	    createStaffMember = new JMenuItem("Create Staff Member");	//Create menu item (add new staff member)
	    createStaffMember.setFont(customFont4);						//Setting some styling for it
	    createStaffMember.setForeground(Color.decode("#BE81F7"));
	    staffOptionsMenu.add(createStaffMember);					//Adding it to staff options menu
	    createStaffMember.addActionListener(menuHandler);			//Giving it an event listener
	    
	    updateStaffMember = new JMenuItem("Update Staff Member");	//Create menu item (update a staff member)
	    updateStaffMember.setFont(customFont4);						//Setting some styling for it
	    updateStaffMember.setForeground(Color.decode("#BE81F7"));
	    staffOptionsMenu.add(updateStaffMember);					//Adding it to staff options menu
	    updateStaffMember.addActionListener(menuHandler);			//Giving it an event listener
	    
	    deleteStaffMember = new JMenuItem("Delete Staff Member");	//Create menu item (delete a staff member)
	    deleteStaffMember.setFont(customFont4);						//Setting some styling for it
	    deleteStaffMember.setForeground(Color.decode("#BE81F7"));
	    staffOptionsMenu.add(deleteStaffMember);					//Adding it to staff options menu
	    deleteStaffMember.addActionListener(menuHandler);			//Giving it an event listener
	    
	    viewStaffMembers = new JMenuItem("View Staff Members");		//Create menu item (view staff members)
	    viewStaffMembers.setFont(customFont4);						//Setting some styling for it
	    viewStaffMembers.setForeground(Color.decode("#BE81F7"));
	    staffOptionsMenu.add(viewStaffMembers);						//Adding it to staff options menu
	    viewStaffMembers.addActionListener(menuHandler);			//Giving it an event listener
	}//end createStaffOptionsMenu()
	
	//Creates a panel and adds the necessary controls to it allowing a new staff member to be created
	private void pnlCreateStaffMember()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
			
		pnlCreateStaffMember = new JPanel();							//create a panel
		pnlCreateStaffMember.setLayout(new GridLayout(7,2));			//layout is in grid format 7rows 2columns
		pnlCreateStaffMember.setSize(450, 300);							//set the panels size
		pnlCreateStaffMember.setLocation(475, 150);						//set the panels location
		pnlCreateStaffMember.setBackground(Color.black);				//background colour is black
		
		lblStaffFirstName = new JLabel("    Enter First Name: ");		//create a label
		lblStaffFirstName.setFont(customFont6);							//set the labels font
		lblStaffFirstName.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblStaffLastName = new JLabel("    Enter Last Name: ");			//create a label
		lblStaffLastName.setFont(customFont6);							//set the labels font
		lblStaffLastName.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblStaffAddress = new JLabel("    Enter Address: ");			//create a label
		lblStaffAddress.setFont(customFont6);							//set the labels font
		lblStaffAddress.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblStaffPPS = new JLabel("    Enter PPS Number: ");				//create a label
		lblStaffPPS.setFont(customFont6);								//set the labels font
		lblStaffPPS.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		lblStaffSalary = new JLabel("    Enter Weekly Salary(decimal): ");			//create a label
		lblStaffSalary.setFont(customFont6);							//set the labels font
		lblStaffSalary.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblStaffEmpType = new JLabel("    Select Employment Type: ");	//create a label
		lblStaffEmpType.setFont(customFont6);							//set the labels font
		lblStaffEmpType.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtNewStaffFirstName  = new JTextField();						//textfield for username
		txtNewStaffLastName = new JTextField();							//textfield for password
		txtNewStaffAddress = new JTextField();	
		txtNewStaffPPS = new JTextField();	
		txtNewStaffSalary = new JTextField();	
		cmbCreateStaffOptions = new JComboBox<String>(userTypeChoices);		//combo box for staff type
		cmbCreateStaffOptions.addItemListener(listHandler);				    //event handler for combo box
		btnCreateStaff = new JButton("Create New Staff Member");			//button to create staff member
		btnCreateStaff.setActionCommand("CreateNewStaffMember");			//action command that links to button
		btnCreateStaff.addActionListener(clientHandler);					//event handler for button
		btnCancelCreateStaff = new JButton("Cancel");						//button to cancel create of staff member
		btnCancelCreateStaff.setActionCommand("CancelNewStaffMember");		//action command that links to button
		btnCancelCreateStaff.addActionListener(UIhandler);				//event handler for button
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 7rows 2columns*/
		pnlCreateStaffMember.add(lblStaffFirstName);			//1st row 1st column
		pnlCreateStaffMember.add(txtNewStaffFirstName);			//1st row 2nd column
		pnlCreateStaffMember.add(lblStaffLastName);				//2nd row 1st column
		pnlCreateStaffMember.add(txtNewStaffLastName);			//2nd row 2nd column etc
		pnlCreateStaffMember.add(lblStaffAddress);
		pnlCreateStaffMember.add(txtNewStaffAddress);
		pnlCreateStaffMember.add(lblStaffPPS);
		pnlCreateStaffMember.add(txtNewStaffPPS);
		pnlCreateStaffMember.add(lblStaffSalary);
		pnlCreateStaffMember.add(txtNewStaffSalary);
		pnlCreateStaffMember.add(lblStaffEmpType);
		pnlCreateStaffMember.add(cmbCreateStaffOptions);
		pnlCreateStaffMember.add(btnCreateStaff);
		pnlCreateStaffMember.add(btnCancelCreateStaff);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create Staff Member Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCreateStaffMember.setBorder(title);
			
		pnlCreateStaffMember.setVisible(false);		//Panel to create staff member is not visible when application starts
			
		this.add(pnlCreateStaffMember);				//add the panel to the application window
	}//pnlCreateStaffMember()
	
	//Creates a panel and adds the necessary controls to it allowing a staff member to be updated
	private void pnlUpdateStaffMember()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
				
		pnlUpdateStaffMember = new JPanel();							//create a panel
		pnlUpdateStaffMember.setLayout(new GridLayout(7, 2));			//layout is in grid format 7rows 2columns
		pnlUpdateStaffMember.setSize(450, 300);							//set the panels size
		pnlUpdateStaffMember.setLocation(475, 150);						//set the panels location
		pnlUpdateStaffMember.setBackground(Color.black);				//background colour is black
		
		lblStaffUpdateFirstName = new JLabel("Update First Name:");
		lblStaffUpdateFirstName.setFont(customFont6);							//set the labels font
		lblStaffUpdateFirstName.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblStaffUpdateLastName = new JLabel("Update Last Name:");
		lblStaffUpdateLastName.setFont(customFont6);							//set the labels font
		lblStaffUpdateLastName.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblStaffUpdateAddress = new JLabel("Update Address :");
		lblStaffUpdateAddress.setFont(customFont6);								//set the labels font
		lblStaffUpdateAddress.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblStaffUpdatePPS = new JLabel("Update Enter PPS :");
		lblStaffUpdatePPS.setFont(customFont6);									//set the labels font
		lblStaffUpdatePPS.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		lblStaffUpdateSalary = new JLabel("Update Salary :");
		lblStaffUpdateSalary.setFont(customFont6);								//set the labels font
		lblStaffUpdateSalary.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblStaffUpdateEmpType = new JLabel("Update Staff Type :");
		lblStaffUpdateEmpType.setFont(customFont6);								//set the labels font
		lblStaffUpdateEmpType.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtStaffUpdateFirstName = new JTextField();							//text fields to hold staff details
		txtStaffUpdateLastName = new JTextField();
		txtStaffUpdateAddress = new JTextField();
		txtStaffUpdatePPS = new JTextField();
		txtStaffUpdateSalary = new JTextField();
		cmbUpdateStaffOptions = new JComboBox<String>(userTypeChoices);		    //combo box for staff type
		cmbUpdateStaffOptions.addItemListener(listHandler);				    	//event handler for combo box
		btnUpdateStaffMember = new JButton("Update Staff Member");	//Button to update staff member
		btnUpdateStaffMember.setEnabled(false);						//not currently enabled
		btnUpdateStaffMember.setActionCommand("UpdateStaffMember"); //action command for update button
		btnUpdateStaffMember.addActionListener(clientHandler);		//event listener for update button
		btnExitUpdateStaff = new JButton("Cancel Update");			//cancel update button
		btnExitUpdateStaff.setActionCommand("CancelUpdateStaffMember"); //action command for cancel update
		btnExitUpdateStaff.addActionListener(UIhandler);			//event listener for cancel update
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 7rows 2columns*/
		pnlUpdateStaffMember.add(lblStaffUpdateFirstName);	//1st row 1st column
		pnlUpdateStaffMember.add(txtStaffUpdateFirstName);	//1st row 2nd column
		pnlUpdateStaffMember.add(lblStaffUpdateLastName);	//2nd row 1st column etc
		pnlUpdateStaffMember.add(txtStaffUpdateLastName);
		pnlUpdateStaffMember.add(lblStaffUpdateAddress);
		pnlUpdateStaffMember.add(txtStaffUpdateAddress );
		pnlUpdateStaffMember.add(lblStaffUpdatePPS);
		pnlUpdateStaffMember.add(txtStaffUpdatePPS);
		pnlUpdateStaffMember.add(lblStaffUpdateSalary);
		pnlUpdateStaffMember.add(txtStaffUpdateSalary);
		pnlUpdateStaffMember.add(lblStaffUpdateEmpType);
		pnlUpdateStaffMember.add(cmbUpdateStaffOptions);
		pnlUpdateStaffMember.add(btnUpdateStaffMember);
		pnlUpdateStaffMember.add(btnExitUpdateStaff);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update Staff Member Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateStaffMember.setBorder(title);
						
		pnlUpdateStaffMember.setVisible(false);		//Panel to create staff member is not visible when application starts
						
		this.add(pnlUpdateStaffMember);				//Add the panel to the window	
		
		/*Underneath the panel there are some labels, buttons and text fields used to retrieve, a 
		 particular staff member and add him/her into the grid above for updating*/
		
		lblEnterIDStaff = new JLabel("Enter ID of Staff Member you want to edit"); //label
		lblEnterIDStaff.setLocation(500, 480);									   //attributes for label
		lblEnterIDStaff.setSize(250, 30);
		lblEnterIDStaff.setForeground(Color.white);								   
		lblEnterIDStaff.setVisible(false);					//not visible on application launch
		add(lblEnterIDStaff);								//add label to the window
				 
		btnSearchStaffEdit = new JButton("Find Staff Member");		//Button for searching a staff member
		btnSearchStaffEdit.setActionCommand("FindStaffMember");		//action command for the button
		btnSearchStaffEdit.addActionListener(clientHandler);		//event listener for the button
		btnSearchStaffEdit.setLocation(580, 520);					//location, size and visibility
		btnSearchStaffEdit.setSize(145, 31);
		btnSearchStaffEdit.setVisible(false);
		add(btnSearchStaffEdit);									//add button to the window
		
		btnRefreshStaffEdit = new JButton("Refresh");							//Button for searching a staff member
		btnRefreshStaffEdit.setActionCommand("RefreshUpdateStaffMember");		//action command for the button
		btnRefreshStaffEdit.addActionListener(UIhandler);						//event listener for the button
		btnRefreshStaffEdit.setLocation(750, 520);								//location, size and visibility
		btnRefreshStaffEdit.setSize(145, 31);
		btnRefreshStaffEdit.setVisible(false);
		add(btnRefreshStaffEdit);									//add button to the window
				    
		txtSearchStaffEdit = new JTextField();						//text field to accept id for searching
		txtSearchStaffEdit.setLocation(505, 520);					//location, size and visibility attributes
		txtSearchStaffEdit.setSize(50, 30);
		txtSearchStaffEdit.setVisible(false);
		add(txtSearchStaffEdit);									//add text field to the window
	}//pnlUpdateStaffMember()
	
	//Panel that allows a staff member to be deleted
	private void deleteStaffMemberPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlDeleteStaffMember = new JPanel();							//create a panel
		pnlDeleteStaffMember.setLayout(null);							//layout is null
		pnlDeleteStaffMember.setSize(400, 180);							//set the panels size
		pnlDeleteStaffMember.setLocation(490, 90);						//set the panels location
		pnlDeleteStaffMember.setBackground(Color.black);				//background colour is black
		
		lblDeleteStaffMember = new JLabel("Enter ID of Staff Member you want to delete");//label
		lblDeleteStaffMember.setLocation(70, 20);							//location and size
		lblDeleteStaffMember.setSize(280, 30);
		lblDeleteStaffMember.setFont(customFont6);							//set the labels font
		lblDeleteStaffMember.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlDeleteStaffMember.add(lblDeleteStaffMember);						//and label to panel
					 
		btnDeleteStaffMember = new JButton("Delete Staff Member");			//button to delete staff member
		btnDeleteStaffMember.setActionCommand("DeleteStaffMember");			//buttons action command
		btnDeleteStaffMember.addActionListener(clientHandler);				//event listener
		btnDeleteStaffMember.setLocation(135, 75);							//size, location, visibility
		btnDeleteStaffMember.setSize(200, 31);
		btnDeleteStaffMember.setVisible(true);
		pnlDeleteStaffMember.add(btnDeleteStaffMember);						//add button to panel
		
		btnCancelDeleteStaffMember = new JButton("Cancel Delete");					//button to cancel delete
		btnCancelDeleteStaffMember.setActionCommand("CancelDeleteStaffMember");		//buttons action command
		btnCancelDeleteStaffMember.addActionListener(UIhandler);					//event listener
		btnCancelDeleteStaffMember.setLocation(135, 115);					//size, location, visibility
		btnCancelDeleteStaffMember.setSize(200, 31);
		btnCancelDeleteStaffMember.setVisible(true);
		pnlDeleteStaffMember.add(btnCancelDeleteStaffMember);			//and button to the panel
					    
		txtDeleteStaffMember = new JTextField();				//text field for id of staff member to delete
		txtDeleteStaffMember.setLocation(75, 75);				//location, size, visibility attributes
		txtDeleteStaffMember.setSize(50, 30);
		txtDeleteStaffMember.setVisible(true);
		pnlDeleteStaffMember.add(txtDeleteStaffMember);			//add text field to panel
			
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete Staff Member Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteStaffMember.setBorder(title);
				
		pnlDeleteStaffMember.setVisible(false);		//Panel to delete staff member is not visible when application starts
			
		this.add(pnlDeleteStaffMember);				//add the panel to the application window
	}//end deleteStaffMemberPanel()
		
	//creates show staff area
	private void createStaffShowArea()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
			
		pnlStaffMemberShowArea = new JPanel();					//create a panel
		pnlStaffMemberShowArea.setLayout(null);					//layout is in grid format null
		pnlStaffMemberShowArea.setSize(1200, 600);				//set the panels size
		pnlStaffMemberShowArea.setLocation(90, 50);				//set the panels location
		pnlStaffMemberShowArea.setBackground(Color.black);		//background colour is black
			
		btnShowAllStaff = new JButton("View All Staff");			//button to show all the staff
		btnShowAllStaff.setActionCommand("ShowAllStaff");			//action command for button
		btnShowAllStaff.addActionListener(clientHandler);			//event listener
		btnShowAllStaff.setLocation(150, 480);						//location, size, visibility
		btnShowAllStaff.setSize(200, 31);
		btnShowAllStaff.setVisible(false);
		pnlStaffMemberShowArea.add(btnShowAllStaff);				//add button to panel
		
		btnCancelShowAllStaff = new JButton("Exit");					//button to exit show staff panel
		btnCancelShowAllStaff.setActionCommand("CancelShowStaff");		//action command for button
		btnCancelShowAllStaff.addActionListener(UIhandler);				//event listener
		btnCancelShowAllStaff.setLocation(420, 480);					//location, size, visibility 
		btnCancelShowAllStaff.setSize(200, 31);
		btnCancelShowAllStaff.setVisible(false);
		pnlStaffMemberShowArea.add(btnCancelShowAllStaff);				//add button to panel
			
		lblViewStaffMember = new JLabel("Enter Staff ID Number: ");		//label
		lblViewStaffMember.setLocation(690, 480);						//size, location, visibility
		lblViewStaffMember.setSize(200, 31);
		lblViewStaffMember.setFont(customFont6);						//set the labels font
		lblViewStaffMember.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblViewStaffMember.setVisible(false);
		pnlStaffMemberShowArea.add(lblViewStaffMember);					//add label to the panel
			
		txtSearchStaffMember = new JTextField();						//text field to accept search id
		txtSearchStaffMember.setLocation(850, 480);						//size, location and visibility
		txtSearchStaffMember.setSize(200, 31);
		txtSearchStaffMember.setVisible(false);
		pnlStaffMemberShowArea.add(txtSearchStaffMember);				//add text field to panel
			
		btnSearchForStaff = new JButton("Search Staff Member");			//button search for a staff member
		btnSearchForStaff.setActionCommand("ViewSearchStaffMember");	//action command for button
		btnSearchForStaff.addActionListener(clientHandler);				//event listener
		btnSearchForStaff.setLocation(850, 520);						//location, size, visibility
		btnSearchForStaff.setSize(200, 31);
		btnSearchForStaff.setVisible(false);
		pnlStaffMemberShowArea.add(btnSearchForStaff);					//add button to the panel
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "View Staff Members Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlStaffMemberShowArea.setBorder(title);
				
		pnlStaffMemberShowArea.setVisible(false);		//Panel to view staff member is not visible when application starts
			
		this.add(pnlStaffMemberShowArea);				//add the panel to the application window
	}//end createStaffShowArea()
	
	//Creates a menu called File, gives it some options like upload, download, send, remove a file. 
	//Them adds the menu and its options to the menu bar for the application
	private void fileOptionsMenu()
	{
		fileOptionsMenu = new JMenu("File");						//Create file options menu 		
		fileOptionsMenu.setFont(customFont3);						//Setting some styling for it
		fileOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(fileOptionsMenu);							//Adding it to the application menu bar
		    
		uploadFile = new JMenuItem("Upload File");					//Create menu item (upload file)
		uploadFile.setFont(customFont4);							//Setting some styling for it
		uploadFile.setForeground(Color.decode("#BE81F7"));
		fileOptionsMenu.add(uploadFile);							//Adding it to file options menu
		uploadFile.addActionListener(menuHandler);					//Giving it an event listener
		    
		downloadFile = new JMenuItem("Download File");				//Create menu item (upload file)
		downloadFile.setFont(customFont4);							//Setting some styling for it
		downloadFile.setForeground(Color.decode("#BE81F7"));
		fileOptionsMenu.add(downloadFile);							//Adding it to file options menu
		downloadFile.addActionListener(menuHandler);				//Giving it an event listener
		    
		sendFile = new JMenuItem("Send File");						//Create menu item (upload file)
		sendFile.setFont(customFont4);								//Setting some styling for it
		sendFile.setForeground(Color.decode("#BE81F7"));
		fileOptionsMenu.add(sendFile);								//Adding it to file options menu
		sendFile.addActionListener(menuHandler);					//Giving it an event listener
		
		viewFile = new JMenuItem("View Files");						//Create menu item (upload file)
		viewFile.setFont(customFont4);								//Setting some styling for it
		viewFile.setForeground(Color.decode("#BE81F7"));
		fileOptionsMenu.add(viewFile);							//Adding it to file options menu
		viewFile.addActionListener(menuHandler);				//Giving it an event listener
		
		removeFile = new JMenuItem("Remove File");				//Create menu item (upload file)
		removeFile.setFont(customFont4);						//Setting some styling for it
		removeFile.setForeground(Color.decode("#BE81F7"));
		fileOptionsMenu.add(removeFile);						//Adding it to file options menu
		removeFile.addActionListener(menuHandler);				//Giving it an event listener
	}//end fileOptionsMenu()
	
	//panel used to upload files
	private void uploadFilePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlUploadFile = new JPanel();								//create a panel
		pnlUploadFile.setLayout(null);								//layout is null
		pnlUploadFile.setSize(400, 180);							//set the panels size
		pnlUploadFile.setLocation(490, 90);							//set the panels location
		pnlUploadFile.setBackground(Color.black);					//background color is black
		
		lblUploadFile = new JLabel("Enter Path of the file you wish to upload (Include File Extension)");//label
		lblUploadFile.setLocation(25, 20);							//location and size
		lblUploadFile.setSize(360, 30);
		lblUploadFile.setFont(customFont7);							//set the labels font
		lblUploadFile.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		pnlUploadFile.add(lblUploadFile);							//and label to panel
					 
		btnUploadFile = new JButton("Upload File");					//button to upload a file
		btnUploadFile.setActionCommand("UploadFile");				//buttons action command
		btnUploadFile.addActionListener(clientHandler);				//event listener
		btnUploadFile.setLocation(15, 130);							//size, location, visibility
		btnUploadFile.setSize(175, 31);
		btnUploadFile.setVisible(true);
		pnlUploadFile.add(btnUploadFile);							//add button to panel
		
		btnCancelUploadFile = new JButton("Cancel Upload");					//button to cancel upload
		btnCancelUploadFile.setActionCommand("CancelUploadFile");			//buttons action command
		btnCancelUploadFile.addActionListener(UIhandler);					//event listener
		btnCancelUploadFile.setLocation(210, 130);							//size, location, visibility
		btnCancelUploadFile.setSize(175, 31);
		btnCancelUploadFile.setVisible(true);
		pnlUploadFile.add(btnCancelUploadFile);			//and button to the panel
					    
		txtUploadFile = new JTextField();				//text field for path of file for upload
		txtUploadFile.setLocation(100, 75);				//location, size, visibility attributes
		txtUploadFile.setSize(220, 30);
		txtUploadFile.setVisible(true);
		pnlUploadFile.add(txtUploadFile);				//add text field to panel
			
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Upload File Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUploadFile.setBorder(title);
				
		pnlUploadFile.setVisible(false);		//Panel to upload file is not visible when application starts
			
		this.add(pnlUploadFile);				//add the panel to the application window
	}//uploadFilePanel()
	
	//panel used to download files
	private void downloadFilePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
				
		pnlDownloadFile = new JPanel();								//create a panel
		pnlDownloadFile.setLayout(null);							//layout is null 
		pnlDownloadFile.setSize(1000, 500);							//set the panels size
		pnlDownloadFile.setLocation(180, 75);						//set the panels location
		pnlDownloadFile.setBackground(Color.black);					//background color is black
		
		showAllFiles = new JTextArea(1000, 30);			//text area shown when this panel is picked from menu
		scrlFiles = new JScrollPane(showAllFiles);		//scroller for text area, add text area to scroll pane
		scrlFiles.setLocation(295, 100);				//location, size visibility
		scrlFiles.setSize(400, 250);
		showAllFiles.setEditable(false);
		scrlFiles.setVisible(true);
		pnlDownloadFile.add(scrlFiles);	
		
		btnViewFiles = new JButton("View Files");				//button to view online files
		btnViewFiles.setActionCommand("ViewFiles");				//buttons action command
		btnViewFiles.addActionListener(clientHandler);			//event listener
		btnViewFiles.setLocation(15, 450);						//size, location, visibility
		btnViewFiles.setSize(175, 31);
		btnViewFiles.setVisible(true);
		pnlDownloadFile.add(btnViewFiles);
		
		lblDownloadFile = new JLabel("Enter Name of the file you wish to download including extension (eg Test.txt)");//label
		lblDownloadFile.setLocation(290, 360);							//location and size
		lblDownloadFile.setSize(420, 30);
		lblDownloadFile.setFont(customFont7);							//set the labels font
		lblDownloadFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlDownloadFile.add(lblDownloadFile);							//and label to panel
		
		btnDownloadFile = new JButton("Download File");				//button to download a file
		btnDownloadFile.setActionCommand("DownloadFile");			//buttons action command
		btnDownloadFile.addActionListener(clientHandler);			//event listener
		btnDownloadFile.setLocation(290, 390);						//size, location, visibility
		btnDownloadFile.setSize(175, 31);
		btnDownloadFile.setVisible(true);
		pnlDownloadFile.add(btnDownloadFile);
		
		txtDownloadFile = new JTextField();					//text field for path of file for download
		txtDownloadFile.setLocation(480, 390);				//location, size, visibility attributes
		txtDownloadFile.setSize(220, 30);
		txtDownloadFile.setVisible(true);
		pnlDownloadFile.add(txtDownloadFile);				//add text field to panel
		
		lblDestDownloadFile = new JLabel("Enter path where you want this file saved to (eg B:\\Software\\Folder)");//label
		lblDestDownloadFile.setLocation(290, 420);							//location and size
		lblDestDownloadFile.setSize(420, 30);
		lblDestDownloadFile.setFont(customFont7);							//set the labels font
		lblDestDownloadFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlDownloadFile.add(lblDestDownloadFile);							//and label to panel
		
		txtDestDownloadFile = new JTextField();					//text field for path of file for upload
		txtDestDownloadFile.setLocation(380, 450);				//location, size, visibility attributes
		txtDestDownloadFile.setSize(220, 30);
		txtDestDownloadFile.setVisible(true);
		pnlDownloadFile.add(txtDestDownloadFile);				//add text field to panel
		
		btnCancelDownloadFile = new JButton("Cancel");							//button to cancel window
		btnCancelDownloadFile.setActionCommand("CancelFileDownload");			//buttons action command
		btnCancelDownloadFile.addActionListener(UIhandler);					//event listener
		btnCancelDownloadFile.setLocation(800, 450);						//size, location, visibility
		btnCancelDownloadFile.setSize(175, 31);
		btnCancelDownloadFile.setVisible(true);
		pnlDownloadFile.add(btnCancelDownloadFile);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Online File Directory & Download File Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDownloadFile.setBorder(title);
						
		pnlDownloadFile.setVisible(false);		//Panel to download file is not visible when application starts
					
		this.add(pnlDownloadFile);				//add the panel to the application window
	}//end downloadFilePanel()
	
	//panel used to send files
	private void sendFilePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
		
		pnlSendFile = new JPanel();								//create a panel
		pnlSendFile.setLayout(null);							//layout is null
		pnlSendFile.setSize(1000, 500);							//set the panels size
		pnlSendFile.setLocation(180, 75);						//set the panels location
		pnlSendFile.setBackground(Color.black);					//background color is black
		
		//COMBOBOX, CANCEL BUTTON AND ASSOCIATED LABEL
		lblDestDownloadFile = new JLabel("Where are you sending the file from?");//label
		lblDestDownloadFile.setLocation(380, 35);							//location and size
		lblDestDownloadFile.setSize(420, 30);
		lblDestDownloadFile.setFont(customFont6);							//set the labels font
		lblDestDownloadFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlSendFile.add(lblDestDownloadFile);								//and label to panel
		
		btnCancelSendFiles = new JButton("Cancel");							//button to view files
		btnCancelSendFiles.setActionCommand("CancelSendFiles");				//buttons action command
		btnCancelSendFiles.addActionListener(UIhandler);					//event listener
		btnCancelSendFiles.setLocation(20, 20);								//size, location, visibility
		btnCancelSendFiles.setSize(175, 31);
		btnCancelSendFiles.setVisible(true);
		pnlSendFile.add(btnCancelSendFiles);
		
		cmbSendFileOptions = new JComboBox<String>(sendFileChoices);	//Create a combo box for send file type
		cmbSendFileOptions.setSize(300, 25);							//set its size
		cmbSendFileOptions.setLocation(350, 75);						//set its location
		cmbSendFileOptions.setMaximumRowCount(3);						//Set max rows	
		cmbSendFileOptions.addItemListener(listHandler);				//add an event handler for the combo box
		cmbSendFileOptions.setVisible(true);							//set combo box visible to true
		pnlSendFile.add(cmbSendFileOptions);							//add combo box to the panel
		
		//PATH ON COMP
		lblUsernameSendFile = new JLabel("Enter username of the recipient of the file");//label
		lblUsernameSendFile.setLocation(365, 127);							//location and size
		lblUsernameSendFile.setSize(420, 30);
		lblUsernameSendFile.setFont(customFont6);							//set the labels font
		lblUsernameSendFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblUsernameSendFile.setVisible(false);
		pnlSendFile.add(lblUsernameSendFile);								//and label to panel
		
		txtUsernameSendFile = new JTextField();				//text field for path of file for upload
		txtUsernameSendFile.setLocation(380, 163);				//location, size, visibility attributes
		txtUsernameSendFile.setSize(220, 30);
		txtUsernameSendFile.setVisible(false);
		pnlSendFile.add(txtUsernameSendFile);				//add text field to panel
		
		lblCompPathSendFile = new JLabel("Enter path the file for sending (eg B:\\Folder\\File.txt");//label
		lblCompPathSendFile.setLocation(335, 205);							//location and size
		lblCompPathSendFile.setSize(420, 30);
		lblCompPathSendFile.setFont(customFont6);							//set the labels font
		lblCompPathSendFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblCompPathSendFile.setVisible(false);
		pnlSendFile.add(lblCompPathSendFile);							//and label to panel
		
		txtCompPathSendFile = new JTextField();				//text field for path of file send file
		txtCompPathSendFile.setLocation(380, 243);				//location, size, visibility attributes
		txtCompPathSendFile.setSize(220, 30);
		txtCompPathSendFile.setVisible(false);
		pnlSendFile.add(txtCompPathSendFile);				//add text field to panel
		
		btnSendCompFile = new JButton("Send File");							//button to view files
		btnSendCompFile.setActionCommand("SendUserCompFile");			//buttons action command
		btnSendCompFile.addActionListener(clientHandler);						//event listener
		btnSendCompFile.setLocation(403, 300);							//size, location, visibility
		btnSendCompFile.setSize(175, 31);
		btnSendCompFile.setVisible(false);
		pnlSendFile.add(btnSendCompFile);
		
		//FROM ONLINE DIRECTORY
		showSendableFiles = new JTextArea(1000, 30);			//text area shown when this panel is picked from send file type combox
		scrlSendbleFiles = new JScrollPane(showSendableFiles);			//scroller for text area, add text area to scroll pane
		scrlSendbleFiles.setLocation(500, 125);						//location, size visibility
		scrlSendbleFiles.setSize(250, 250);
		showSendableFiles.setEditable(false);
		scrlSendbleFiles.setVisible(false);
		pnlSendFile.add(scrlSendbleFiles);	
		
		lblUsernameSendFileO = new JLabel("Enter username of the recipient of the file");//label
		lblUsernameSendFileO.setLocation(165, 127);							//location and size
		lblUsernameSendFileO.setSize(420, 30);
		lblUsernameSendFileO.setFont(customFont6);							//set the labels font
		lblUsernameSendFileO.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblUsernameSendFileO.setVisible(false);
		pnlSendFile.add(lblUsernameSendFileO);							//and label to panel
		
		txtUsernameSendFileO = new JTextField();				//text field for path of file for upload
		txtUsernameSendFileO.setLocation(180, 163);				//location, size, visibility attributes
		txtUsernameSendFileO.setSize(220, 30);
		txtUsernameSendFileO.setVisible(false);
		pnlSendFile.add(txtUsernameSendFileO);	
		
		lblOnlineNameSendFile = new JLabel("Enter name of the file your sending (eg File.txt)");//label
		lblOnlineNameSendFile.setLocation(150, 205);							//location and size
		lblOnlineNameSendFile.setSize(420, 30);
		lblOnlineNameSendFile.setFont(customFont6);							//set the labels font
		lblOnlineNameSendFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblOnlineNameSendFile.setVisible(false);
		pnlSendFile.add(lblOnlineNameSendFile);							//and label to panel
		
		txtOnlineNameSendFile = new JTextField();				//text field for path of file send file
		txtOnlineNameSendFile.setLocation(180, 243);				//location, size, visibility attributes
		txtOnlineNameSendFile.setSize(220, 30);
		txtOnlineNameSendFile.setVisible(false);
		pnlSendFile.add(txtOnlineNameSendFile);				//add text field to panel
		
		btnViewSendableFiles = new JButton("View Files");							//button to view files
		btnViewSendableFiles.setActionCommand("ViewSendableFiles");			//buttons action command
		btnViewSendableFiles.addActionListener(clientHandler);						//event listener
		btnViewSendableFiles.setLocation(538, 390);							//size, location, visibility
		btnViewSendableFiles.setSize(175, 31);
		btnViewSendableFiles.setVisible(false);
		pnlSendFile.add(btnViewSendableFiles);								//adding button to the panel
		
		btnSendOnlineFile = new JButton("Send File");							//button to view files
		btnSendOnlineFile.setActionCommand("SendUserOnlineFile");			//buttons action command
		btnSendOnlineFile.addActionListener(clientHandler);						//event listener
		btnSendOnlineFile.setLocation(225, 300);							//size, location, visibility
		btnSendOnlineFile.setSize(175, 31);
		btnSendOnlineFile.setVisible(false);
		pnlSendFile.add(btnSendOnlineFile);									//adding button to the panel
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Send File Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlSendFile.setBorder(title);
								
		pnlSendFile.setVisible(false);		//Panel to send file is not visible when application starts
							
		this.add(pnlSendFile);				//add the panel to the application window
	}//end sendFilePanel()
	
	//panel used to view a particular file
	private void viewFilePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
					
		pnlViewFile = new JPanel();								//create a panel
		pnlViewFile.setLayout(null);							//layout is null
		pnlViewFile.setSize(700, 440);							//set the panels size
		pnlViewFile.setLocation(340, 75);						//set the panels location
		pnlViewFile.setBackground(Color.black);					//background color is black
			
		showFiles = new JTextArea(1000, 30);			//text area shown when this panel is picked from menu
		scrlAllFiles = new JScrollPane(showFiles);		//scroller for text area, add text area to scroll pane
		scrlAllFiles.setLocation(150, 100);				//location, size visibility
		scrlAllFiles.setSize(400, 250);
		showFiles.setEditable(false);
		scrlAllFiles.setVisible(true);
		pnlViewFile.add(scrlAllFiles);	
			
		btnViewAllFiles = new JButton("View Files");				//button to view online files
		btnViewAllFiles.setActionCommand("ViewAllTheFiles");				//buttons action command
		btnViewAllFiles.addActionListener(clientHandler);			//event listener
		btnViewAllFiles.setLocation(160, 390);						//size, location, visibility
		btnViewAllFiles.setSize(150, 31);
		btnViewAllFiles.setVisible(true);
		pnlViewFile.add(btnViewAllFiles);							//adding button to the panel
			
		btnCancelViewFile = new JButton("Cancel");				//button to cancel window
		btnCancelViewFile.setActionCommand("CancelFileView");			//buttons action command
		btnCancelViewFile.addActionListener(UIhandler);			//event listener
		btnCancelViewFile.setLocation(385, 390);						//size, location, visibility
		btnCancelViewFile.setSize(150, 31);
		btnCancelViewFile.setVisible(true);
		pnlViewFile.add(btnCancelViewFile);						//adding button to the panel
			
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Online File Directory");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlViewFile.setBorder(title);
							
		pnlViewFile.setVisible(false);		//Panel to view file is not visible when application starts
						
		this.add(pnlViewFile);				//add the panel to the application window
	}//end viewFilePanel()
	
	//panel used to delete a particular file
	private void removeFilePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
				
		pnlRemoveFile = new JPanel();								//create a panel
		pnlRemoveFile.setLayout(null);							//layout is null
		pnlRemoveFile.setSize(1000, 440);							//set the panels size
		pnlRemoveFile.setLocation(180, 75);						//set the panels location
		pnlRemoveFile.setBackground(Color.black);					//background color is black
		
		showAllRemoveableFiles = new JTextArea(1000, 30);			//text area shown when this panel is picked from menu
		scrlRemovableFiles = new JScrollPane(showAllRemoveableFiles);		//scroller for text area, add text area to scroll pane
		scrlRemovableFiles.setLocation(295, 100);				//location, size visibility
		scrlRemovableFiles.setSize(400, 250);
		showAllRemoveableFiles.setEditable(false);
		scrlRemovableFiles.setVisible(true);
		pnlRemoveFile.add(scrlRemovableFiles);	
		
		btnViewRemovableFiles = new JButton("View Files");				//button to view online files
		btnViewRemovableFiles.setActionCommand("ViewRemovableFiles");				//buttons action command
		btnViewRemovableFiles.addActionListener(clientHandler);			//event listener
		btnViewRemovableFiles.setLocation(15, 390);						//size, location, visibility
		btnViewRemovableFiles.setSize(175, 31);
		btnViewRemovableFiles.setVisible(true);
		pnlRemoveFile.add(btnViewRemovableFiles);
		
		lblRemoveFile = new JLabel("Enter Name of the file you wish to remove including extension (eg Test.txt)");//label
		lblRemoveFile.setLocation(290, 360);							//location and size
		lblRemoveFile.setSize(420, 30);
		lblRemoveFile.setFont(customFont7);							//set the labels font
		lblRemoveFile.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlRemoveFile.add(lblRemoveFile);							//and label to panel
		
		btnRemoveFile = new JButton("Remove File");				//button to remove a file
		btnRemoveFile.setActionCommand("RemoveFile");			//buttons action command
		btnRemoveFile.addActionListener(clientHandler);			//event listener
		btnRemoveFile.setLocation(290, 390);						//size, location, visibility
		btnRemoveFile.setSize(175, 31);
		btnRemoveFile.setVisible(true);
		pnlRemoveFile.add(btnRemoveFile);
		
		txtRemoveFile = new JTextField();				//text field for name of file to delete
		txtRemoveFile.setLocation(480, 390);				//location, size, visibility attributes
		txtRemoveFile.setSize(220, 30);
		txtRemoveFile.setVisible(true);
		pnlRemoveFile.add(txtRemoveFile);				//add text field to panel
		
		btnCancelRemoveFile = new JButton("Cancel");				//button to cancel window
		btnCancelRemoveFile.setActionCommand("CancelFileRemove");			//buttons action command
		btnCancelRemoveFile.addActionListener(UIhandler);			//event listener
		btnCancelRemoveFile.setLocation(800, 390);						//size, location, visibility
		btnCancelRemoveFile.setSize(175, 31);
		btnCancelRemoveFile.setVisible(true);
		pnlRemoveFile.add(btnCancelRemoveFile);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Online File Directory & Remove File Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlRemoveFile.setBorder(title);
						
		pnlRemoveFile.setVisible(false);		//Panel to remove file is not visible when application starts
					
		this.add(pnlRemoveFile);				//add the panel to the application window
	}//end removeFilePanel()
	
	//Creates a menu called House, gives it some options like add, delete, view house(s). 
	//Them adds the menu and its options to the menu bar for the application
	private void houseOptionsMenu()
	{
		houseOptionsMenu = new JMenu("House");						//Create house options menu 		
		houseOptionsMenu.setFont(customFont3);						//Setting some styling for it
		houseOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(houseOptionsMenu);							//Adding it to the application menu bar
			    
		addHouse = new JMenuItem("New House");				//Create menu item (Create House)
		addHouse.setFont(customFont4);						//Setting some styling for it
		addHouse.setForeground(Color.decode("#BE81F7"));
		houseOptionsMenu.add(addHouse);						//Adding it to house options menu
		addHouse.addActionListener(menuHandler);				//Giving it an event listener
		
		updateHouse = new JMenuItem("Update House");				//Create menu item (Update House)
		updateHouse.setFont(customFont4);						//Setting some styling for it
		updateHouse.setForeground(Color.decode("#BE81F7"));
		houseOptionsMenu.add(updateHouse);						//Adding it to house options menu
		updateHouse.addActionListener(menuHandler);				//Giving it an event listener
			    
		deleteHouse = new JMenuItem("Delete House");				//Create menu item (Delete House)
		deleteHouse.setFont(customFont4);						//Setting some styling for it
		deleteHouse.setForeground(Color.decode("#BE81F7"));
		houseOptionsMenu.add(deleteHouse);						//Adding it to house options menu
		deleteHouse.addActionListener(menuHandler);				//Giving it an event listener
		    
		viewHouses = new JMenuItem("View Houses");				//Create menu item (View Houses)
		viewHouses.setFont(customFont4);						//Setting some styling for it
		viewHouses.setForeground(Color.decode("#BE81F7"));
		houseOptionsMenu.add(viewHouses);						//Adding it to house options menu
		viewHouses.addActionListener(menuHandler);				//Giving it an event listener
	}//end houseOptionsMenu()
	
	//Panel used to create a new house and add it to currently available houses for sale/rent and 
	//already being sold/rented
	private void addHousePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
					
		pnlCreateHouse = new JPanel();							//create a panel for house creation
		pnlCreateHouse.setLayout(new GridLayout(5,2));			//layout is in grid format 5rows 2columns
		pnlCreateHouse.setSize(450, 300);							//set the panels size
		pnlCreateHouse.setLocation(475, 150);						//set the panels location
		pnlCreateHouse.setBackground(Color.black);				//background colour is black
				
		lblNewHouseStreet = new JLabel("    House Street Name: ");		//create a label
		lblNewHouseStreet.setFont(customFont6);							//set the labels font
		lblNewHouseStreet.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewHouseTown = new JLabel("    House Town Name: ");			//create a label
		lblNewHouseTown.setFont(customFont6);							//set the labels font
		lblNewHouseTown.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblNewHouseCounty = new JLabel("    House County Name: ");			//create a label
		lblNewHouseCounty.setFont(customFont6);							//set the labels font
		lblNewHouseCounty.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblNewRentOrSell = new JLabel("    House Sell or Rent: ");				//create a label
		lblNewRentOrSell.setFont(customFont6);								//set the labels font
		lblNewRentOrSell.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		txtNewHouseStreet  = new JTextField();						//text field for house street
		txtNewHouseTown = new JTextField();							//text field for house town
		txtNewHouseCounty = new JTextField();						//text field for house county
		cmbCreateHouseOptions = new JComboBox<String>(houseChoices);		//combo box for house transaction type
		cmbCreateHouseOptions.addItemListener(listHandler);				    //event handler for combo box
		btnCreateHouse = new JButton("Create New House");			//button to create house
		btnCreateHouse.setActionCommand("CreateNewHouse");			//action command that links to button
		btnCreateHouse.addActionListener(clientHandler);					//event handler for button
		btnCancelCreateHouse = new JButton("Cancel");						//button to cancel create of house
		btnCancelCreateHouse.setActionCommand("CancelNewHouse");		//action command that links to button
		btnCancelCreateHouse.addActionListener(UIhandler);				//event handler for button
				
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 7rows 2columns*/
		pnlCreateHouse.add(lblNewHouseStreet);				//1st row 1st column
		pnlCreateHouse.add(txtNewHouseStreet);			//1st row 2nd column
		pnlCreateHouse.add(lblNewHouseTown);				//2nd row 1st column
		pnlCreateHouse.add(txtNewHouseTown);			//2nd row 2nd column etc
		pnlCreateHouse.add(lblNewHouseCounty);
		pnlCreateHouse.add(txtNewHouseCounty);
		pnlCreateHouse.add(lblNewRentOrSell);
		pnlCreateHouse.add(cmbCreateHouseOptions);
		pnlCreateHouse.add(btnCreateHouse);
		pnlCreateHouse.add(btnCancelCreateHouse);
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create House Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCreateHouse.setBorder(title);
					
		pnlCreateHouse.setVisible(false);		//Panel to create house is not visible when application starts
					
		this.add(pnlCreateHouse);				//add the panel to the application window
	}//end addHousePanel()

	//Panel used to edit the attributes of a house
	private void updateHousePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
					
		pnlUpdateHouse = new JPanel();							//create a panel for house edit
		pnlUpdateHouse.setLayout(new GridLayout(5,2));			//layout is in grid format 5rows 2columns
		pnlUpdateHouse.setSize(450, 300);							//set the panels size
		pnlUpdateHouse.setLocation(475, 150);						//set the panels location
		pnlUpdateHouse.setBackground(Color.black);				//background colour is black
				
		lblUpdatedHouseStreet = new JLabel("  Update House Street Name: ");		//create a label
		lblUpdatedHouseStreet.setFont(customFont6);							//set the labels font
		lblUpdatedHouseStreet.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblUpdatedHouseTown = new JLabel("   Update House Town Name: ");			//create a label
		lblUpdatedHouseTown.setFont(customFont6);							//set the labels font
		lblUpdatedHouseTown.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblUpdatedHouseCounty = new JLabel("   Update House County Name: ");			//create a label
		lblUpdatedHouseCounty.setFont(customFont6);							//set the labels font
		lblUpdatedHouseCounty.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblUpdatedRentOrSell = new JLabel("   Update House Sell or Rent: ");				//create a label
		lblUpdatedRentOrSell.setFont(customFont6);								//set the labels font
		lblUpdatedRentOrSell.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		txtUpdatedHouseStreet  = new JTextField();						//text field for update house street
		txtUpdatedHouseTown = new JTextField();							//text field for update house town
		txtUpdatedHouseCounty = new JTextField();						//text field update house county
		cmbUpdateHouseOptions = new JComboBox<String>(houseChoices);		//combo box for house transaction type
		cmbUpdateHouseOptions.addItemListener(listHandler);				    //event handler for combo box
		btnUpdateHouse = new JButton("Update House");			//button to update a house
		btnUpdateHouse.setEnabled(false);						//not currently enabled
		btnUpdateHouse.setActionCommand("UpdateHouse");			//action command that links to button
		btnUpdateHouse.addActionListener(clientHandler);					//event handler for button
		btnCancelUpdateHouse = new JButton("Cancel Update");						//button to cancel update of house
		btnCancelUpdateHouse.setActionCommand("CancelUpdateHouse");		//action command that links to button
		btnCancelUpdateHouse.addActionListener(UIhandler);				//event handler for button
				
		//Adding all the controls that were created above to the panel, because layout for this panel
		//is a grid, controls are added left to right and top to bottom 7rows 2columns
		pnlUpdateHouse.add(lblUpdatedHouseStreet);				//1st row 1st column
		pnlUpdateHouse.add(txtUpdatedHouseStreet);			//1st row 2nd column
		pnlUpdateHouse.add(lblUpdatedHouseTown);				//2nd row 1st column
		pnlUpdateHouse.add(txtUpdatedHouseTown);			//2nd row 2nd column etc
		pnlUpdateHouse.add(lblUpdatedHouseCounty);
		pnlUpdateHouse.add(txtUpdatedHouseCounty);
		pnlUpdateHouse.add(lblUpdatedRentOrSell);
		pnlUpdateHouse.add(cmbUpdateHouseOptions);
		pnlUpdateHouse.add(btnUpdateHouse);
		pnlUpdateHouse.add(btnCancelUpdateHouse);
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update House Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateHouse.setBorder(title);
					
		pnlUpdateHouse.setVisible(false);		//Panel to update house is not visible when application starts
					
		this.add(pnlUpdateHouse);				//add the panel to the application window
		
		/*Underneath the panel there are some labels, buttons and text fields used to retrieve, a 
		 particular house and add its data into the grid above for updating*/
		lblEnterIDHouse = new JLabel("Enter ID of the House you want to update"); //label
		lblEnterIDHouse.setLocation(510, 480);									   //attributes for label
		lblEnterIDHouse.setSize(250, 30);
		lblEnterIDHouse.setForeground(Color.white);								   
		lblEnterIDHouse.setVisible(false);					//not visible on application launch
		add(lblEnterIDHouse);								//add label to the window
				 
		btnSearchHouseEdit = new JButton("Find House");		//Button for searching a house
		btnSearchHouseEdit.setActionCommand("FindHouse");		//action command for the button
		btnSearchHouseEdit.addActionListener(clientHandler);		//event listener for the button
		btnSearchHouseEdit.setLocation(580, 520);					//location, size and visibility
		btnSearchHouseEdit.setSize(115, 31);
		btnSearchHouseEdit.setVisible(false);
		add(btnSearchHouseEdit);									//add button to the window
			
		btnRefreshHouseEdit = new JButton("Refresh");		//Button for searching a house
		btnRefreshHouseEdit.setActionCommand("RefreshUpdateHouse");		//action command for the button
		btnRefreshHouseEdit.addActionListener(UIhandler);		//event listener for the button
		btnRefreshHouseEdit.setLocation(750, 520);					//location, size and visibility
		btnRefreshHouseEdit.setSize(145, 31);
		btnRefreshHouseEdit.setVisible(false);
		add(btnRefreshHouseEdit);				
		
		txtSearchHouseEdit = new JTextField();						//text field to accept id for searching
		txtSearchHouseEdit.setLocation(505, 520);					//location, size and visibility attributes
		txtSearchHouseEdit.setSize(50, 30);
		txtSearchHouseEdit.setVisible(false);
		add(txtSearchHouseEdit);									//add text field to the window
	}//end updateHousePanel()
	
	//panel used to delete a particular house
	private void deleteHousePanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlDeleteHouse = new JPanel();							//create a panel to delete house
		pnlDeleteHouse.setLayout(null);							//layout is null
		pnlDeleteHouse.setSize(400, 180);						//set the panels size
		pnlDeleteHouse.setLocation(490, 90);					//set the panels location
		pnlDeleteHouse.setBackground(Color.black);				//background colour is black
			
		lblDeleteHouse = new JLabel("Enter ID of House you want to delete");//label
		lblDeleteHouse.setLocation(70, 20);							//location and size
		lblDeleteHouse.setSize(280, 30);
		lblDeleteHouse.setFont(customFont6);					//set the labels font
		lblDeleteHouse.setForeground(Color.decode("#BE81F7"));	//set labels foreground colour
		pnlDeleteHouse.add(lblDeleteHouse);						//and label to panel
							 
		btnDeleteHouse = new JButton("Delete House");			//button to delete house
		btnDeleteHouse.setActionCommand("DeleteHouse");			//buttons action command
		btnDeleteHouse.addActionListener(clientHandler);		//event listener
		btnDeleteHouse.setLocation(135, 75);					//size, location, visibility
		btnDeleteHouse.setSize(200, 31);
		btnDeleteHouse.setVisible(true);
		pnlDeleteHouse.add(btnDeleteHouse);						//add button to panel
				
		btnCancelDeleteHouse = new JButton("Cancel Delete");					//button to cancel delete
		btnCancelDeleteHouse.setActionCommand("CancelDeleteHouse");				//buttons action command
		btnCancelDeleteHouse.addActionListener(UIhandler);						//event listener
		btnCancelDeleteHouse.setLocation(135, 115);								//size, location, visibility
		btnCancelDeleteHouse.setSize(200, 31);
		btnCancelDeleteHouse.setVisible(true);
		pnlDeleteHouse.add(btnCancelDeleteHouse);			//and button to the panel
							    
		txtDeleteHouse = new JTextField();					//text field for id of house to delete
		txtDeleteHouse.setLocation(75, 75);					//location, size, visibility attributes
		txtDeleteHouse.setSize(50, 30);
		txtDeleteHouse.setVisible(true);
		pnlDeleteHouse.add(txtDeleteHouse);					//add text field to panel
					
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete House Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteHouse.setBorder(title);
						
		pnlDeleteHouse.setVisible(false);		//Panel to delete house is not visible when application starts
					
		this.add(pnlDeleteHouse);				//add the panel to the application window
	}//end deleteHouse()
	
	//show house panel used to show all the houses
	private void createHouseShowArea()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
					
		pnlHouseShowArea = new JPanel();					//create a panel to show houses
		pnlHouseShowArea.setLayout(null);					//layout is null
		pnlHouseShowArea.setSize(1200, 600);				//set the panels size
		pnlHouseShowArea.setLocation(90, 50);				//set the panels location
		pnlHouseShowArea.setBackground(Color.black);		//background colour is black

		btnShowAllHouses = new JButton("View All Houses");			//button to show all the houses
		btnShowAllHouses.setActionCommand("ShowAllHouses");			//action command for button
		btnShowAllHouses.addActionListener(clientHandler);			//event listener
		btnShowAllHouses.setLocation(150, 480);						//location, size, visibility
		btnShowAllHouses.setSize(200, 31);
		btnShowAllHouses.setVisible(false);
		pnlHouseShowArea.add(btnShowAllHouses);				//add button to panel
				
		btnCancelShowHouses = new JButton("Exit");					//button to exit show house panel
		btnCancelShowHouses.setActionCommand("CancelShowHouses");	//action command for button
		btnCancelShowHouses.addActionListener(UIhandler);			//event listener
		btnCancelShowHouses.setLocation(420, 480);					//location, size, visibility 
		btnCancelShowHouses.setSize(200, 31);
		btnCancelShowHouses.setVisible(false);
		pnlHouseShowArea.add(btnCancelShowHouses);				//add button to panel
					
		lblViewHouses = new JLabel("Enter House ID Number: ");		//label
		lblViewHouses.setLocation(690, 480);						//size, location, visibility
		lblViewHouses.setSize(200, 31);
		lblViewHouses.setFont(customFont6);							//set the labels font
		lblViewHouses.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblViewHouses.setVisible(false);
		pnlHouseShowArea.add(lblViewHouses);					//add label to the panel
					
		txtSearchHouse = new JTextField();						//text field to accept search id
		txtSearchHouse.setLocation(850, 480);					//size, location and visibility
		txtSearchHouse.setSize(200, 31);
		txtSearchHouse.setVisible(false);
		pnlHouseShowArea.add(txtSearchHouse);				//add text field to panel
					
		btnSearchForHouse = new JButton("Search House");				//button search for a house
		btnSearchForHouse.setActionCommand("ViewSearchHouse");			//action command for button
		btnSearchForHouse.addActionListener(clientHandler);				//event listener
		btnSearchForHouse.setLocation(850, 520);						//location, size, visibility
		btnSearchForHouse.setSize(200, 31);
		btnSearchForHouse.setVisible(false);
		pnlHouseShowArea.add(btnSearchForHouse);					//add button to the panel
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "View Houses Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlHouseShowArea.setBorder(title);
					
		pnlHouseShowArea.setVisible(false);		//Panel to view house is not visible when application starts
					
		this.add(pnlHouseShowArea);				//add the panel to the application window
	}//end showHousePanel()
	
	//Creates a menu called Transaction, gives it some options like add, delete, view transaction(s). 
	//Them adds the menu and its options to the menu bar for the application
	private void transactionOptionsMenu()
	{
		transactionsOptionsMenu = new JMenu("Transaction");					//Create transaction options menu 		
		transactionsOptionsMenu.setFont(customFont3);						//Setting some styling for it
		transactionsOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(transactionsOptionsMenu);							//Adding it to the application menu bar
				    
		addTransaction = new JMenuItem("New Transaction");			//Create menu item (Create Trans)
		addTransaction.setFont(customFont4);						//Setting some styling for it
		addTransaction.setForeground(Color.decode("#BE81F7"));
		transactionsOptionsMenu.add(addTransaction);				//Adding it to transaction options menu
		addTransaction.addActionListener(menuHandler);				//Giving it an event listener
				    
		deleteTransaction = new JMenuItem("Delete Transaction");	//Create menu item (Delete Trans)
		deleteTransaction.setFont(customFont4);						//Setting some styling for it
		deleteTransaction.setForeground(Color.decode("#BE81F7"));
		transactionsOptionsMenu.add(deleteTransaction);					//Adding it to transaction options menu
		deleteTransaction.addActionListener(menuHandler);				//Giving it an event listener
			    
		updateTransaction = new JMenuItem("Update Transaction");			//Create menu item (Update Trans)
		updateTransaction.setFont(customFont4);								//Setting some styling for it
		updateTransaction.setForeground(Color.decode("#BE81F7"));
		transactionsOptionsMenu.add(updateTransaction);					//Adding it to transaction options menu
		updateTransaction.addActionListener(menuHandler);				//Giving it an event listener
		
		viewTransactions = new JMenuItem("View Transactions");			//Create menu item (View Trans)
		viewTransactions.setFont(customFont4);							//Setting some styling for it
		viewTransactions.setForeground(Color.decode("#BE81F7"));
		transactionsOptionsMenu.add(viewTransactions);					//Adding it to transaction options menu
		viewTransactions.addActionListener(menuHandler);				//Giving it an event listener
	}//end transactionOptionsMenu()
	
	//panel which allows a new transaction, rent or sell to be created
	private void createTransactionPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
				
		pnlCreateTransaction = new JPanel();							//create a panel for a new transaction
		pnlCreateTransaction.setLayout(null);							//layout is null
		pnlCreateTransaction.setSize(1000, 500);						//set the panels size
		pnlCreateTransaction.setLocation(180, 75);						//set the panels location
		pnlCreateTransaction.setBackground(Color.black);				//background color is black
		
		//COMBOBOX, CANCEL BUTTON AND ASSOCIATED LABEL
		lblTransactionOption = new JLabel("What kind of transaction are you creating?");//label
		lblTransactionOption.setLocation(370, 35);							//location and size
		lblTransactionOption.setSize(420, 30);
		lblTransactionOption.setFont(customFont6);							//set the labels font
		lblTransactionOption.setVisible(true);
		lblTransactionOption.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		pnlCreateTransaction.add(lblTransactionOption);						//and label to panel
						
		btnCancelCreateTransaction = new JButton("Cancel");							//button to exit transaction window
		btnCancelCreateTransaction.setActionCommand("CancelCreateTransaction");		//buttons action command
		btnCancelCreateTransaction.addActionListener(UIhandler);					//event listener
		btnCancelCreateTransaction.setLocation(20, 20);					//size, location, visibility
		btnCancelCreateTransaction.setSize(175, 31);
		btnCancelCreateTransaction.setVisible(true);
		pnlCreateTransaction.add(btnCancelCreateTransaction);			//add cancel button to panel
		
		cmbTransactionOptions = new JComboBox<String>(houseChoices);	//Create a combo box for transaction type
		cmbTransactionOptions.setSize(300, 25);							//set its size
		cmbTransactionOptions.setLocation(350, 75);						//set its location
		cmbTransactionOptions.setMaximumRowCount(3);					//Set max rows	
		cmbTransactionOptions.addItemListener(listHandler);				//add an event handler for the combo box
		cmbTransactionOptions.setVisible(true);							//set combo box visible to true
		pnlCreateTransaction.add(cmbTransactionOptions);				//add combo box to the panel
		
		//RENT A HOUSE OPTIONS
		lblTransRentHouseID = new JLabel("Enter house id number: ");		//label
		lblTransRentHouseID.setLocation(250, 130);							//location and size
		lblTransRentHouseID.setSize(420, 30);
		lblTransRentHouseID.setFont(customFont6);							//set the labels font
		lblTransRentHouseID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransRentHouseID.setVisible(false);
		pnlCreateTransaction.add(lblTransRentHouseID);						//and label to panel
		
		txtTransRentHouseID = new JTextField();				//text field for rent transaction house id
		txtTransRentHouseID.setLocation(520, 130);			//location, size, visibility attributes
		txtTransRentHouseID.setSize(220, 30);
		txtTransRentHouseID.setVisible(false);
		pnlCreateTransaction.add(txtTransRentHouseID);		//add text field to panel
		
		lblTransStartDate = new JLabel("Enter Start Rent Date eg (yyyy-mm-dd): ");//label
		lblTransStartDate.setLocation(250, 170);								//location and size
		lblTransStartDate.setSize(420, 30);
		lblTransStartDate.setFont(customFont6);									//set the labels font
		lblTransStartDate.setForeground(Color.decode("#BE81F7"));				//set labels foreground color
		lblTransStartDate.setVisible(false);
		pnlCreateTransaction.add(lblTransStartDate);							//and label to panel
		
		txtTransStartDate = new JTextField();					//text field for rent house start date
		txtTransStartDate.setLocation(520, 170);				//location, size, visibility attributes
		txtTransStartDate.setSize(220, 30);
		txtTransStartDate.setVisible(false);
		pnlCreateTransaction.add(txtTransStartDate);			//add text field to panel
		
		lblTransEndDate = new JLabel("Enter End Rent Date eg (yyyy-mm-dd): ");//label
		lblTransEndDate.setLocation(250, 210);							//location and size
		lblTransEndDate.setSize(420, 30);
		lblTransEndDate.setFont(customFont6);							//set the labels font
		lblTransEndDate.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransEndDate.setVisible(false);
		pnlCreateTransaction.add(lblTransEndDate);						//and label to panel
		
		txtTransEndDate = new JTextField();						//text field for rent house end date
		txtTransEndDate.setLocation(520, 210);					//location, size, visibility attributes
		txtTransEndDate.setSize(220, 30);
		txtTransEndDate.setVisible(false);
		pnlCreateTransaction.add(txtTransEndDate);				//add text field to panel
		
		lblTransMonthlyRate = new JLabel("Enter Monthly Rate: ");//label
		lblTransMonthlyRate.setLocation(250, 250);							//location and size
		lblTransMonthlyRate.setSize(420, 30);
		lblTransMonthlyRate.setFont(customFont6);							//set the labels font
		lblTransMonthlyRate.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransMonthlyRate.setVisible(false);
		pnlCreateTransaction.add(lblTransMonthlyRate);						//and label to panel
		
		txtTransMonthlyRate = new JTextField();							//text field for rent house monthly rate
		txtTransMonthlyRate.setLocation(520, 250);						//location, size, visibility attributes
		txtTransMonthlyRate.setSize(220, 30);
		txtTransMonthlyRate.setVisible(false);
		pnlCreateTransaction.add(txtTransMonthlyRate);							//add text field to panel
		
		lblRentEstateAgentID = new JLabel("Enter Estate Agent ID: ");			//label
		lblRentEstateAgentID.setLocation(250, 290);								//location and size
		lblRentEstateAgentID.setSize(420, 30);
		lblRentEstateAgentID.setFont(customFont6);								//set the labels font
		lblRentEstateAgentID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblRentEstateAgentID.setVisible(false);
		pnlCreateTransaction.add(lblRentEstateAgentID);							//and label to panel
		
		txtEstateAgentID = new JTextField();						//text field for estate agent id
		txtEstateAgentID.setLocation(520, 290);						//location, size, visibility attributes
		txtEstateAgentID.setSize(220, 30);
		txtEstateAgentID.setVisible(false);
		pnlCreateTransaction.add(txtEstateAgentID);					//add text field to panel
		
		lblRentCustID = new JLabel("Enter Customer ID: ");//label
		lblRentCustID.setLocation(250, 330);							//location and size
		lblRentCustID.setSize(420, 30);
		lblRentCustID.setFont(customFont6);							//set the labels font
		lblRentCustID.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblRentCustID.setVisible(false);
		pnlCreateTransaction.add(lblRentCustID);					//and label to panel
		
		txtRentCustID = new JTextField();						//text field for customer id
		txtRentCustID.setLocation(520, 330);				//location, size, visibility attributes
		txtRentCustID.setSize(220, 30);
		txtRentCustID.setVisible(false);
		pnlCreateTransaction.add(txtRentCustID);	
		
		btnCreateRentTrans = new JButton("Add Rent Transaction");			//button to exit transaction window
		btnCreateRentTrans.setActionCommand("CreateRentTransaction");			//buttons action command
		btnCreateRentTrans.addActionListener(clientHandler);						//event listener
		btnCreateRentTrans.setLocation(410, 375);							//size, location, visibility
		btnCreateRentTrans.setSize(175, 31);
		btnCreateRentTrans.setVisible(false);
		pnlCreateTransaction.add(btnCreateRentTrans);
		
		//BUY A HOUSE OPTIONS
		lblTransBuyHouseID = new JLabel("Enter house id number: ");			//label
		lblTransBuyHouseID.setLocation(250, 130);							//location and size
		lblTransBuyHouseID.setSize(420, 30);
		lblTransBuyHouseID.setFont(customFont6);							//set the labels font
		lblTransBuyHouseID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransBuyHouseID.setVisible(false);
		pnlCreateTransaction.add(lblTransBuyHouseID);							//and label to panel
		
		txtTransBuyHouseID = new JTextField();				//text field for buy house id
		txtTransBuyHouseID.setLocation(520, 130);			//location, size, visibility attributes
		txtTransBuyHouseID.setSize(220, 30);
		txtTransBuyHouseID.setVisible(false);
		pnlCreateTransaction.add(txtTransBuyHouseID);				//add text field to panel
		
		lblBuyCost = new JLabel("Enter House Cost: ");//label
		lblBuyCost.setLocation(250, 170);							//location and size
		lblBuyCost.setSize(420, 30);
		lblBuyCost.setFont(customFont6);							//set the labels font
		lblBuyCost.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblBuyCost.setVisible(false);
		pnlCreateTransaction.add(lblBuyCost);						//and label to panel
		
		txtBuyCost = new JTextField();						//text field for buy house cost
		txtBuyCost.setLocation(520, 170);					//location, size, visibility attributes
		txtBuyCost.setSize(220, 30);
		txtBuyCost.setVisible(false);
		pnlCreateTransaction.add(txtBuyCost);				//add text field to panel
		
		lblBuyEstateAgentID = new JLabel("Enter Estate Agent ID: ");		//label
		lblBuyEstateAgentID.setLocation(250, 210);							//location and size
		lblBuyEstateAgentID.setSize(420, 30);
		lblBuyEstateAgentID.setFont(customFont6);							//set the labels font
		lblBuyEstateAgentID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblBuyEstateAgentID.setVisible(false);
		pnlCreateTransaction.add(lblBuyEstateAgentID);						//and label to panel
		
		txtBuyEstateAgentID = new JTextField();					//text field for estate agent id
		txtBuyEstateAgentID.setLocation(520, 210);				//location, size, visibility attributes
		txtBuyEstateAgentID.setSize(220, 30);
		txtBuyEstateAgentID.setVisible(false);
		pnlCreateTransaction.add(txtBuyEstateAgentID);				//add text field to panel
		
		lblBuyCustID = new JLabel("Enter Customer ID: ");			//label
		lblBuyCustID.setLocation(250, 250);							//location and size
		lblBuyCustID.setSize(420, 30);
		lblBuyCustID.setFont(customFont6);							//set the labels font
		lblBuyCustID.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblBuyCustID.setVisible(false);
		pnlCreateTransaction.add(lblBuyCustID);						//and label to panel
		
		txtBuyCustID = new JTextField();				//text field for customer id
		txtBuyCustID.setLocation(520, 250);				//location, size, visibility attributes
		txtBuyCustID.setSize(220, 30);
		txtBuyCustID.setVisible(false);
		pnlCreateTransaction.add(txtBuyCustID);	
		
		btnCreateBuyTrans = new JButton("Add Buy Transaction");				//button to exit transaction window
		btnCreateBuyTrans.setActionCommand("CreateBuyTransaction");			//buttons action command
		btnCreateBuyTrans.addActionListener(clientHandler);					//event listener
		btnCreateBuyTrans.setLocation(410, 300);							//size, location, visibility
		btnCreateBuyTrans.setSize(175, 31);
		btnCreateBuyTrans.setVisible(false);
		pnlCreateTransaction.add(btnCreateBuyTrans);
		
		//BORDER SETTINGS
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create Transaction Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCreateTransaction.setBorder(title);
										
		pnlCreateTransaction.setVisible(false);		//Panel to create transaction is not visible when application starts
									
		this.add(pnlCreateTransaction);				//add the panel to the application window
	}//end createTransactionPanel()
	
	//panel used to delete buy and sell transactions
	private void deleteTransactionPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
						
		pnlDeleteTransaction = new JPanel();								//create a panel
		pnlDeleteTransaction.setLayout(null);								//layout is null
		pnlDeleteTransaction.setSize(1000, 300);							//set the panels size
		pnlDeleteTransaction.setLocation(180, 75);							//set the panels location
		pnlDeleteTransaction.setBackground(Color.black);					//background color is black
				
		//COMBOBOX, CANCEL BUTTON AND ASSOCIATED LABEL
		lblTransactionDeleteOption = new JLabel("What kind of transaction are you deleting?");//label
		lblTransactionDeleteOption.setLocation(365, 35);							//location and size
		lblTransactionDeleteOption.setSize(420, 30);
		lblTransactionDeleteOption.setFont(customFont6);							//set the labels font
		lblTransactionDeleteOption.setVisible(true);
		lblTransactionDeleteOption.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlDeleteTransaction.add(lblTransactionDeleteOption);							//and label to panel
								
		btnCancelDeleteTransaction = new JButton("Cancel");							//button to exit transaction window
		btnCancelDeleteTransaction.setActionCommand("CancelDeleteTransaction");		//buttons action command
		btnCancelDeleteTransaction.addActionListener(UIhandler);					//event listener
		btnCancelDeleteTransaction.setLocation(20, 20);							//size, location, visibility
		btnCancelDeleteTransaction.setSize(175, 31);
		btnCancelDeleteTransaction.setVisible(true);
		pnlDeleteTransaction.add(btnCancelDeleteTransaction);
				
		cmbDeleteTransactionOptions = new JComboBox<String>(houseChoices);		//Create a combo box for transaction type
		cmbDeleteTransactionOptions.setSize(300, 25);							//set its size
		cmbDeleteTransactionOptions.setLocation(350, 75);						//set its location
		cmbDeleteTransactionOptions.setMaximumRowCount(3);						//Set max rows	
		cmbDeleteTransactionOptions.addItemListener(listHandler);				//add an event handler for the combo box
		cmbDeleteTransactionOptions.setVisible(true);							//set combo box visible to true
		pnlDeleteTransaction.add(cmbDeleteTransactionOptions);					//add combo box to the panel
			
		//DELETE RENTED HOUSE
		lblDeleteRentTransaction = new JLabel("Enter ID of Rent Transaction you want to delete");//label
		lblDeleteRentTransaction.setLocation(350, 120);							//location and size
		lblDeleteRentTransaction.setSize(300, 30);
		lblDeleteRentTransaction.setFont(customFont6);							//set the labels font
		lblDeleteRentTransaction.setVisible(false);
		lblDeleteRentTransaction.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlDeleteTransaction.add(lblDeleteRentTransaction);						//and label to panel
					 
		btnDeleteRentTransaction = new JButton("Delete Rent Transaction");		//button to delete rent transaction
		btnDeleteRentTransaction.setActionCommand("DeleteRentTransaction");		//buttons action command
		btnDeleteRentTransaction.addActionListener(clientHandler);				//event listener
		btnDeleteRentTransaction.setLocation(360, 150);							//size, location, visibility
		btnDeleteRentTransaction.setSize(200, 31);
		btnDeleteRentTransaction.setVisible(false);
		pnlDeleteTransaction.add(btnDeleteRentTransaction);						//add button to panel
	
		txtDeleteRentTransaction = new JTextField();				//text field for id of rent transaction to delete
		txtDeleteRentTransaction.setLocation(590, 150);				//location, size, visibility attributes
		txtDeleteRentTransaction.setSize(50, 30);
		txtDeleteRentTransaction.setVisible(false);
		pnlDeleteTransaction.add(txtDeleteRentTransaction);			//add text field to panel
		
		//DELETE SELL HOUSE
		lblDeleteSellTransaction = new JLabel("Enter ID of Sell Transaction you want to delete");//label
		lblDeleteSellTransaction.setLocation(350, 120);							//location and size
		lblDeleteSellTransaction.setSize(300, 30);
		lblDeleteSellTransaction.setFont(customFont6);							//set the labels font
		lblDeleteSellTransaction.setVisible(false);
		lblDeleteSellTransaction.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlDeleteTransaction.add(lblDeleteSellTransaction);						//and label to panel
							 
		btnDeleteSellTransaction = new JButton("Delete Sell Transaction");		//button to delete sell transaction
		btnDeleteSellTransaction.setActionCommand("DeleteSellTransaction");		//buttons action command
		btnDeleteSellTransaction.addActionListener(clientHandler);				//event listener
		btnDeleteSellTransaction.setLocation(360, 150);							//size, location, visibility
		btnDeleteSellTransaction.setSize(200, 31);
		btnDeleteSellTransaction.setVisible(false);
		pnlDeleteTransaction.add(btnDeleteSellTransaction);						//add button to panel
			
		txtDeleteSellTransaction = new JTextField();				//text field for id of sell transaction to delete
		txtDeleteSellTransaction.setLocation(590, 150);				//location, size, visibility attributes
		txtDeleteSellTransaction.setSize(50, 30);
		txtDeleteSellTransaction.setVisible(false);
		pnlDeleteTransaction.add(txtDeleteSellTransaction);			//add text field to panel
		
		//BORDER SETTINGS
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete Transaction Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteTransaction.setBorder(title);
												
		pnlDeleteTransaction.setVisible(false);		//Panel to delete transactions is not visible when application starts
											
		this.add(pnlDeleteTransaction);				//add the panel to the application window
	}//end deleteTransactionPanel()
	
	//panel used for updating a buy/sell transaction
	private void updateTransactionPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
						
		pnlUpdateTransaction = new JPanel();								//create a panel
		pnlUpdateTransaction.setLayout(null);								//layout is null
		pnlUpdateTransaction.setSize(1000, 550);							//set the panels size
		pnlUpdateTransaction.setLocation(180, 35);							//set the panels location
		pnlUpdateTransaction.setBackground(Color.black);					//background color is black
				
		//COMBOBOX, CANCEL BUTTON AND ASSOCIATED LABEL
		lblUpdateTransactionOption = new JLabel("What kind of transaction are you updating?");//label
		lblUpdateTransactionOption.setLocation(365, 35);							//location and size
		lblUpdateTransactionOption.setSize(420, 30);
		lblUpdateTransactionOption.setFont(customFont6);							//set the labels font
		lblUpdateTransactionOption.setVisible(true);
		lblUpdateTransactionOption.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlUpdateTransaction.add(lblUpdateTransactionOption);						//and label to panel
								
		btnCancelUpdateTransaction = new JButton("Cancel");							//button to exit transaction window
		btnCancelUpdateTransaction.setActionCommand("CancelUpdateTransaction");		//buttons action command
		btnCancelUpdateTransaction.addActionListener(UIhandler);					//event listener
		btnCancelUpdateTransaction.setLocation(20, 20);								//size, location, visibility
		btnCancelUpdateTransaction.setSize(175, 31);
		btnCancelUpdateTransaction.setVisible(true);
		pnlUpdateTransaction.add(btnCancelUpdateTransaction);						//add button to the panel
				
		cmbTransactionUpdateOptions = new JComboBox<String>(houseChoices);	//Create a combo box for transaction type
		cmbTransactionUpdateOptions.setSize(300, 25);						//set its size
		cmbTransactionUpdateOptions.setLocation(350, 75);					//set its location
		cmbTransactionUpdateOptions.setMaximumRowCount(3);					//Set max rows	
		cmbTransactionUpdateOptions.addItemListener(listHandler);			//add an event handler for the combo box
		cmbTransactionUpdateOptions.setVisible(true);						//set combo box visible to true
		pnlUpdateTransaction.add(cmbTransactionUpdateOptions);				//add combo box to the panel
		
		//UPDATE A RENTED HOUSE(TRANSACTION) OPTIONS
		lblTransUpdateRentID = new JLabel("Enter ID number of the transaction you wish to update: ");//label
		lblTransUpdateRentID.setLocation(305, 120);							//location and size
		lblTransUpdateRentID.setSize(420, 30);
		lblTransUpdateRentID.setFont(customFont6);							//set the labels font
		lblTransUpdateRentID.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblTransUpdateRentID.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateRentID);						//add label to panel
				
		txtTransUpdateRentID = new JTextField();							//text field 
		txtTransUpdateRentID.setLocation(292, 150);							//location, size, visibility attributes
		txtTransUpdateRentID.setSize(220, 30);
		txtTransUpdateRentID.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateRentID);						//add text field to panel
		
		btnSearchUpdateRentTrans = new JButton("Search Rent Transaction");					//button to find rent transaction
		btnSearchUpdateRentTrans.setActionCommand("SearchUpdateRentTransaction");			//buttons action command
		btnSearchUpdateRentTrans.addActionListener(clientHandler);							//event listener
		btnSearchUpdateRentTrans.setLocation(532, 150);										//size, location, visibility
		btnSearchUpdateRentTrans.setSize(175, 31);
		btnSearchUpdateRentTrans.setVisible(false);
		pnlUpdateTransaction.add(btnSearchUpdateRentTrans);
		
		lblTransUpdateHouseRentID = new JLabel("Update House ID: ");//label
		lblTransUpdateHouseRentID.setLocation(230, 220);							//location and size
		lblTransUpdateHouseRentID.setSize(420, 30);
		lblTransUpdateHouseRentID.setFont(customFont6);								//set the labels font
		lblTransUpdateHouseRentID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransUpdateHouseRentID.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateHouseRentID);						//add label to panel
				
		txtTransUpdateHouseRentID = new JTextField();							//text field 
		txtTransUpdateHouseRentID.setLocation(550, 220);						//location, size, visibility attributes
		txtTransUpdateHouseRentID.setSize(220, 30);
		txtTransUpdateHouseRentID.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateHouseRentID);					//add text field to panel
		
		lblTransUpdateStartDate = new JLabel("Updated Start Rent Date eg (yyyy-mm-dd): ");//label
		lblTransUpdateStartDate.setLocation(230, 260);							//location and size
		lblTransUpdateStartDate.setSize(420, 30);
		lblTransUpdateStartDate.setFont(customFont6);							//set the labels font
		lblTransUpdateStartDate.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransUpdateStartDate.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateStartDate);						//add label to panel
				
		txtTransUpdateStartDate = new JTextField();						//text field
		txtTransUpdateStartDate.setLocation(550, 260);					//location, size, visibility attributes
		txtTransUpdateStartDate.setSize(220, 30);
		txtTransUpdateStartDate.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateStartDate);				//add text field to panel
				
		lblTransUpdateEndDate = new JLabel("Update End Rent Date eg (yyyy-mm-dd): ");//label
		lblTransUpdateEndDate.setLocation(230, 300);								//location and size
		lblTransUpdateEndDate.setSize(420, 30);
		lblTransUpdateEndDate.setFont(customFont6);									//set the labels font
		lblTransUpdateEndDate.setForeground(Color.decode("#BE81F7"));				//set labels foreground color
		lblTransUpdateEndDate.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateEndDate);							//add label to panel
				
		txtTransUpdateEndDate = new JTextField();					//text field  
		txtTransUpdateEndDate.setLocation(550, 300);				//location, size, visibility attributes
		txtTransUpdateEndDate.setSize(220, 30);
		txtTransUpdateEndDate.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateEndDate);			//add text field to panel
				
		lblTransUpdateMonthlyRate = new JLabel("Update Monthly Rate: ");//label
		lblTransUpdateMonthlyRate.setLocation(230, 340);							//location and size
		lblTransUpdateMonthlyRate.setSize(420, 30);
		lblTransUpdateMonthlyRate.setFont(customFont6);								//set the labels font
		lblTransUpdateMonthlyRate.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransUpdateMonthlyRate.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateMonthlyRate);						//add label to panel
				
		txtTransUpdateMonthlyRate = new JTextField();					//text field
		txtTransUpdateMonthlyRate.setLocation(550, 340);				//location, size, visibility attributes
		txtTransUpdateMonthlyRate.setSize(220, 30);
		txtTransUpdateMonthlyRate.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateMonthlyRate);			//add text field to panel
				
		lblRentUpdateEstateAgentID = new JLabel("Update Estate Agent ID: ");//label
		lblRentUpdateEstateAgentID.setLocation(230, 380);							//location and size
		lblRentUpdateEstateAgentID.setSize(420, 30);
		lblRentUpdateEstateAgentID.setFont(customFont6);							//set the labels font
		lblRentUpdateEstateAgentID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblRentUpdateEstateAgentID.setVisible(false);
		pnlUpdateTransaction.add(lblRentUpdateEstateAgentID);						//add label to panel
				
		txtUpdateEstateAgentID = new JTextField();						//text field 
		txtUpdateEstateAgentID.setLocation(550, 380);					//location, size, visibility attributes
		txtUpdateEstateAgentID.setSize(220, 30);
		txtUpdateEstateAgentID.setVisible(false);
		pnlUpdateTransaction.add(txtUpdateEstateAgentID);				//add text field to panel
		
		lblUpdateRentCustID = new JLabel("Update Customer ID: ");//label
		lblUpdateRentCustID.setLocation(230, 420);							//location and size
		lblUpdateRentCustID.setSize(420, 30);
		lblUpdateRentCustID.setFont(customFont6);							//set the labels font
		lblUpdateRentCustID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblUpdateRentCustID.setVisible(false);
		pnlUpdateTransaction.add(lblUpdateRentCustID);						//add label to panel
		
		txtUpdateRentCustID = new JTextField();					//text field for path of file for upload
		txtUpdateRentCustID.setLocation(550, 420);				//location, size, visibility attributes
		txtUpdateRentCustID.setSize(220, 30);
		txtUpdateRentCustID.setVisible(false);
		pnlUpdateTransaction.add(txtUpdateRentCustID);	
				
		btnUpdateRentTrans = new JButton("Update Rent Transaction");			//button to update rent transaction
		btnUpdateRentTrans.setActionCommand("UpdateRentTransaction");			//buttons action command
		btnUpdateRentTrans.addActionListener(clientHandler);					//event listener
		btnUpdateRentTrans.setEnabled(false);
		btnUpdateRentTrans.setLocation(410, 460);								//size, location, visibility
		btnUpdateRentTrans.setSize(175, 31);
		btnUpdateRentTrans.setVisible(false);
		pnlUpdateTransaction.add(btnUpdateRentTrans);
		
		btnRefreshUpdateRentTrans = new JButton("Refresh");							//button to set controls to default
		btnRefreshUpdateRentTrans.setActionCommand("RefreshRentTransaction");		//buttons action command
		btnRefreshUpdateRentTrans.addActionListener(UIhandler);						//event listener
		btnRefreshUpdateRentTrans.setLocation(410, 500);							//size, location, visibility
		btnRefreshUpdateRentTrans.setSize(175, 31);
		btnRefreshUpdateRentTrans.setVisible(false);
		pnlUpdateTransaction.add(btnRefreshUpdateRentTrans);
		
		//UPDATE A BOUGHT HOUSE OPTIONS
		lblTransUpdateBuyID = new JLabel("Enter ID number of the transaction you wish to update: ");//label
		lblTransUpdateBuyID.setLocation(310, 120);							//location and size
		lblTransUpdateBuyID.setSize(420, 30);
		lblTransUpdateBuyID.setFont(customFont6);							//set the labels font
		lblTransUpdateBuyID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblTransUpdateBuyID.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateBuyID);						//add label to panel
				
		txtTransUpdateBuyID = new JTextField();					//text field for path of file for upload
		txtTransUpdateBuyID.setLocation(295, 150);				//location, size, visibility attributes
		txtTransUpdateBuyID.setSize(220, 30);
		txtTransUpdateBuyID.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateBuyID);			//add text field to panel
		
		btnSearchUpdateBuyTrans = new JButton("Search Buy Transaction");				//button to search for a buy transaction
		btnSearchUpdateBuyTrans.setActionCommand("SearchUpdateBuyTransaction");			//buttons action command
		btnSearchUpdateBuyTrans.addActionListener(clientHandler);						//event listener
		btnSearchUpdateBuyTrans.setLocation(535, 150);									//size, location, visibility
		btnSearchUpdateBuyTrans.setSize(175, 31);
		btnSearchUpdateBuyTrans.setVisible(false);
		pnlUpdateTransaction.add(btnSearchUpdateBuyTrans);
		
		lblTransUpdateBuyHouseID = new JLabel("Update House ID: ");//label
		lblTransUpdateBuyHouseID.setLocation(270, 220);							//location and size
		lblTransUpdateBuyHouseID.setSize(420, 30);
		lblTransUpdateBuyHouseID.setFont(customFont6);							//set the labels font
		lblTransUpdateBuyHouseID.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblTransUpdateBuyHouseID.setVisible(false);
		pnlUpdateTransaction.add(lblTransUpdateBuyHouseID);						//add label to panel
				
		txtTransUpdateBuyHouseID = new JTextField();				//text field for path of file for upload
		txtTransUpdateBuyHouseID.setLocation(510, 220);				//location, size, visibility attributes
		txtTransUpdateBuyHouseID.setSize(220, 30);
		txtTransUpdateBuyHouseID.setVisible(false);
		pnlUpdateTransaction.add(txtTransUpdateBuyHouseID);				//add text field to panel
				
		lblUpdateBuyCost = new JLabel("Update House Cost: ");//label
		lblUpdateBuyCost.setLocation(270, 260);							//location and size
		lblUpdateBuyCost.setSize(420, 30);
		lblUpdateBuyCost.setFont(customFont6);							//set the labels font
		lblUpdateBuyCost.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblUpdateBuyCost.setVisible(false);
		pnlUpdateTransaction.add(lblUpdateBuyCost);						//add label to panel
				
		txtUpdateBuyCost = new JTextField();				//text field for path of file for upload
		txtUpdateBuyCost.setLocation(510, 260);				//location, size, visibility attributes
		txtUpdateBuyCost.setSize(220, 30);
		txtUpdateBuyCost.setVisible(false);
		pnlUpdateTransaction.add(txtUpdateBuyCost);			//add text field to panel
				
		lblUpdateBuyEstateAgentID = new JLabel("Update Estate Agent ID: ");//label
		lblUpdateBuyEstateAgentID.setLocation(270, 300);						//location and size
		lblUpdateBuyEstateAgentID.setSize(420, 30);
		lblUpdateBuyEstateAgentID.setFont(customFont6);							//set the labels font
		lblUpdateBuyEstateAgentID.setForeground(Color.decode("#BE81F7"));		//set labels foreground color
		lblUpdateBuyEstateAgentID.setVisible(false);
		pnlUpdateTransaction.add(lblUpdateBuyEstateAgentID);					//add label to panel
				
		txtUpdateBuyEstateAgentID = new JTextField();					//text field 
		txtUpdateBuyEstateAgentID.setLocation(510, 300);				//location, size, visibility attributes
		txtUpdateBuyEstateAgentID.setSize(220, 30);
		txtUpdateBuyEstateAgentID.setVisible(false);
		pnlUpdateTransaction.add(txtUpdateBuyEstateAgentID);				//add text field to panel
		
		lblUpdateBuyCustID = new JLabel("Update Customer ID: ");			//label
		lblUpdateBuyCustID.setLocation(270, 340);							//location and size
		lblUpdateBuyCustID.setSize(420, 30);
		lblUpdateBuyCustID.setFont(customFont6);							//set the labels font
		lblUpdateBuyCustID.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		lblUpdateBuyCustID.setVisible(false);
		pnlUpdateTransaction.add(lblUpdateBuyCustID);						//and label to panel
		
		txtUpdateBuyCustID = new JTextField();				//text field for path of file for upload
		txtUpdateBuyCustID.setLocation(510, 340);			//location, size, visibility attributes
		txtUpdateBuyCustID.setSize(220, 30);
		txtUpdateBuyCustID.setVisible(false);
		pnlUpdateTransaction.add(txtUpdateBuyCustID);	
		
		btnUpdateBuyTrans = new JButton("Update Buy Transaction");			//button to update a buy transaction
		btnUpdateBuyTrans.setActionCommand("UpdateBuyTransaction");			//buttons action command
		btnUpdateBuyTrans.addActionListener(clientHandler);					//event listener
		btnUpdateBuyTrans.setEnabled(false);								//not currently enabled
		btnUpdateBuyTrans.setLocation(410, 390);							//size, location, visibility
		btnUpdateBuyTrans.setSize(175, 31);
		btnUpdateBuyTrans.setVisible(false);
		pnlUpdateTransaction.add(btnUpdateBuyTrans);
		
		btnRefreshUpdateBuyTrans = new JButton("Refresh");							//button to refresh update controls to default
		btnRefreshUpdateBuyTrans.setActionCommand("RefreshBuyTransaction");			//buttons action command
		btnRefreshUpdateBuyTrans.addActionListener(UIhandler);						//event listener
		btnRefreshUpdateBuyTrans.setLocation(410, 430);								//size, location, visibility
		btnRefreshUpdateBuyTrans.setSize(175, 31);
		btnRefreshUpdateBuyTrans.setVisible(false);
		pnlUpdateTransaction.add(btnRefreshUpdateBuyTrans);
		
		//BORDER SETTINGS
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update Transaction Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateTransaction.setBorder(title);
												
		pnlUpdateTransaction.setVisible(false);		//Panel to update transactions is not visible when application starts
											
		this.add(pnlUpdateTransaction);				//add the panel to the application window
	}//end updateTransactionPanel()
	
	private void viewTransactionsPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
								
		pnlViewTransaction = new JPanel();								//create a panel to view transactions
		pnlViewTransaction.setLayout(null);								//layout is null
		pnlViewTransaction.setSize(1000, 610);							//set the panels size
		pnlViewTransaction.setLocation(180, 35);						//set the panels location
		pnlViewTransaction.setBackground(Color.black);					//background color is black
						
		//COMBOBOX, CANCEL BUTTON AND ASSOCIATED LABEL
		lblViewTransactionOption = new JLabel("Which type of transactions do you wish to view?");//label
		lblViewTransactionOption.setLocation(355, 35);							//location and size
		lblViewTransactionOption.setSize(420, 30);
		lblViewTransactionOption.setFont(customFont6);							//set the labels font
		lblViewTransactionOption.setVisible(true);
		lblViewTransactionOption.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlViewTransaction.add(lblViewTransactionOption);							//and label to panel
										
		btnCancelViewTransaction = new JButton("Cancel");							//button to exit transaction window
		btnCancelViewTransaction.setActionCommand("CancelViewTransaction");			//buttons action command
		btnCancelViewTransaction.addActionListener(UIhandler);						//event listener
		btnCancelViewTransaction.setLocation(20, 20);								//size, location, visibility
		btnCancelViewTransaction.setSize(175, 31);
		btnCancelViewTransaction.setVisible(true);
		pnlViewTransaction.add(btnCancelViewTransaction);
						
		cmbTransactionViewOptions = new JComboBox<String>(houseChoices);	//Create a combo box for transaction type
		cmbTransactionViewOptions.setSize(300, 25);							//set its size
		cmbTransactionViewOptions.setLocation(350, 75);						//set its location
		cmbTransactionViewOptions.setMaximumRowCount(3);					//Set max rows	
		cmbTransactionViewOptions.addItemListener(listHandler);				//add an event handler for the combo box
		cmbTransactionViewOptions.setVisible(true);							//set combo box visible to true
		pnlViewTransaction.add(cmbTransactionViewOptions);					//add combo box to the panel
		
		//SEARCH RENT TRANSACTION CONTROLS
		btnViewAllRentTrans = new JButton("View All Rent Transactions");		//button to view all rent transaction
		btnViewAllRentTrans.setActionCommand("ViewAllRentTransaction");			//buttons action command
		btnViewAllRentTrans.addActionListener(clientHandler);					//event listener
		btnViewAllRentTrans.setLocation(110, 550);								//size, location, visibility
		btnViewAllRentTrans.setSize(190, 31);
		btnViewAllRentTrans.setVisible(false);
		pnlViewTransaction.add(btnViewAllRentTrans);
		
		lblViewTransactionRent = new JLabel("Enter the ID of the transaction/house your looking for");						//label
		lblViewTransactionRent.setLocation(505, 515);							//location and size
		lblViewTransactionRent.setSize(420, 30);
		lblViewTransactionRent.setFont(customFont6);							//set the labels font
		lblViewTransactionRent.setVisible(false);
		lblViewTransactionRent.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlViewTransaction.add(lblViewTransactionRent);							//and label to panel
		
		txtSearchViewRentTrans = new JTextField();				//text field 
		txtSearchViewRentTrans.setLocation(460, 550);				//location, size, visibility attributes
		txtSearchViewRentTrans.setSize(220, 30);
		txtSearchViewRentTrans.setVisible(false);
		pnlViewTransaction.add(txtSearchViewRentTrans);				//add text field to panel
		
		btnSearchRentTrans = new JButton("Search Rent Transactions");			//button to search rent transaction
		btnSearchRentTrans.setActionCommand("SearchRentTransaction");			//buttons action command
		btnSearchRentTrans.addActionListener(clientHandler);					//event listener
		btnSearchRentTrans.setLocation(695, 550);								//size, location, visibility
		btnSearchRentTrans.setSize(190, 31);
		btnSearchRentTrans.setVisible(false);
		pnlViewTransaction.add(btnSearchRentTrans);
	
		//SEARCH BUY TRANSACTION CONTROLS
		btnViewAllBuyTrans = new JButton("View All Buy Transactions");		//button to view all buy transactions
		btnViewAllBuyTrans.setActionCommand("ViewAllBuyTransaction");		//buttons action command
		btnViewAllBuyTrans.addActionListener(clientHandler);				//event listener
		btnViewAllBuyTrans.setLocation(110, 550);							//size, location, visibility
		btnViewAllBuyTrans.setSize(190, 31);
		btnViewAllBuyTrans.setVisible(false);
		pnlViewTransaction.add(btnViewAllBuyTrans);
		
		lblViewTransactionBuy = new JLabel("Enter the ID of the transaction/house your looking for");						//label
		lblViewTransactionBuy.setLocation(505, 515);							//location and size
		lblViewTransactionBuy.setSize(420, 30);
		lblViewTransactionBuy.setFont(customFont6);								//set the labels font
		lblViewTransactionBuy.setVisible(false);
		lblViewTransactionBuy.setForeground(Color.decode("#BE81F7"));			//set labels foreground color
		pnlViewTransaction.add(lblViewTransactionBuy);							//and label to panel
		
		txtSearchViewBuyTrans = new JTextField();					//text field 
		txtSearchViewBuyTrans.setLocation(460, 550);				//location, size, visibility attributes
		txtSearchViewBuyTrans.setSize(220, 30);
		txtSearchViewBuyTrans.setVisible(false);
		pnlViewTransaction.add(txtSearchViewBuyTrans);				//add text field to panel
		
		btnSearchBuyTrans = new JButton("Search Buy Transactions");			//button to search a buy transaction
		btnSearchBuyTrans.setActionCommand("SearchBuyTransaction");			//buttons action command
		btnSearchBuyTrans.addActionListener(clientHandler);					//event listener
		btnSearchBuyTrans.setLocation(695, 550);							//size, location, visibility
		btnSearchBuyTrans.setSize(190, 31);
		btnSearchBuyTrans.setVisible(false);
		pnlViewTransaction.add(btnSearchBuyTrans);
		
		//BORDER SETTINGS
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "View Transactions Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlViewTransaction.setBorder(title);
														
		pnlViewTransaction.setVisible(false);		//Panel to view transactions is not visible when application starts
													
		this.add(pnlViewTransaction);				//add the panel to the application window	
	}//end viewTransactions()
	
	//Creates a menu called Customer, gives it some options like add, delete, view customer(s). 
	//Them adds the menu and its options to the menu bar for the application
	private void customerOptionsMenu()
	{
		customerOptionsMenu = new JMenu("Customer");					//Create customer options menu 		
		customerOptionsMenu.setFont(customFont3);						//Setting some styling for it
		customerOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(customerOptionsMenu);							//Adding it to the application menu bar
					    
		addCustomer = new JMenuItem("New Customer");			//Create menu item (Create Customer)
		addCustomer.setFont(customFont4);						//Setting some styling for it
		addCustomer.setForeground(Color.decode("#BE81F7"));
		customerOptionsMenu.add(addCustomer);					//Adding it to customer options menu
		addCustomer.addActionListener(menuHandler);				//Giving it an event listener
					    
		deleteCustomer = new JMenuItem("Delete Customer");			//Create menu item (Delete Customer)
		deleteCustomer.setFont(customFont4);						//Setting some styling for it
		deleteCustomer.setForeground(Color.decode("#BE81F7"));
		customerOptionsMenu.add(deleteCustomer);					//Adding it to customer options menu
		deleteCustomer.addActionListener(menuHandler);				//Giving it an event listener
				    
		updateCustomer = new JMenuItem("Update Customer");			//Create menu item (Update Customer)
		updateCustomer.setFont(customFont4);						//Setting some styling for it
		updateCustomer.setForeground(Color.decode("#BE81F7"));
		customerOptionsMenu.add(updateCustomer);					//Adding it to customer options menu
		updateCustomer.addActionListener(menuHandler);				//Giving it an event listener
			
		viewCustomers = new JMenuItem("View Customers");			//Create menu item (View Customers)
		viewCustomers.setFont(customFont4);							//Setting some styling for it
		viewCustomers.setForeground(Color.decode("#BE81F7"));
		customerOptionsMenu.add(viewCustomers);						//Adding it to customer options menu
		viewCustomers.addActionListener(menuHandler);				//Giving it an event listener
	}//end customerOptionsMenu()
	
	//panel and controls used for adding a new customer record
	private void createCustomerPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 4);
										
		pnlAddCustomer = new JPanel();								//create a panel to create a customer
		pnlAddCustomer.setLayout(new GridLayout(4,2));				//layout is grid layout 4,2
		pnlAddCustomer.setSize(500, 250);							//set the panels size
		pnlAddCustomer.setLocation(425, 175);						//set the panels location
		pnlAddCustomer.setBackground(Color.black);					//background color is black
		
		lblNewCustomerNameF = new JLabel("    Enter Customer First Name: ");	//create a label
		lblNewCustomerNameF.setFont(customFont6);								//set the labels font
		lblNewCustomerNameF.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		lblNewCustomerNameL = new JLabel("    Enter Customer Last Name: ");		//create a label
		lblNewCustomerNameL.setFont(customFont6);							//set the labels font
		lblNewCustomerNameL.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblNewCustomerAddress = new JLabel("    Enter Customer Address: ");		//create a label
		lblNewCustomerAddress.setFont(customFont6);								//set the labels font
		lblNewCustomerAddress.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtNewCustomerNameF  = new JTextField();						//text field for customer first name
		txtNewCustomerNameL = new JTextField();							//text field for customer last name
		txtNewCustomerAddress = new JTextField();						//text field for customer address
		btnCreateCustomer = new JButton("Create New Customer");				//button to create customer
		btnCreateCustomer.setActionCommand("CreateNewCustomer");			//action command that links to button
		btnCreateCustomer.addActionListener(clientHandler);					//event handler for button
		btnCancelCreateCustomer = new JButton("Cancel");					//button to cancel create of customer
		btnCancelCreateCustomer.setActionCommand("CancelNewCustomer");		//action command that links to button
		btnCancelCreateCustomer.addActionListener(UIhandler);				//event handler for button
		
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 7rows 2columns*/
		pnlAddCustomer.add(lblNewCustomerNameF);				//1st row 1st column
		pnlAddCustomer.add(txtNewCustomerNameF);			//1st row 2nd column
		pnlAddCustomer.add(lblNewCustomerNameL);				//2nd row 1st column
		pnlAddCustomer.add(txtNewCustomerNameL);			//2nd row 2nd column etc
		pnlAddCustomer.add(lblNewCustomerAddress);
		pnlAddCustomer.add(txtNewCustomerAddress);
		pnlAddCustomer.add(btnCreateCustomer);
		pnlAddCustomer.add(btnCancelCreateCustomer);
		
		//BORDER SETTINGS
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create Customer Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlAddCustomer.setBorder(title);
																
		pnlAddCustomer.setVisible(false);		//Panel to create customer is not visible when application starts
															
		this.add(pnlAddCustomer);				//add the panel to the application window	
	}//end createCustomerPanel
	
	//panel and controls used for deleting a customer record
	private void deleteCustomerPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlDeleteCustomer = new JPanel();							//create a panel to delete customer
		pnlDeleteCustomer.setLayout(null);							//layout is null
		pnlDeleteCustomer.setSize(400, 180);						//set the panels size
		pnlDeleteCustomer.setLocation(490, 90);						//set the panels location
		pnlDeleteCustomer.setBackground(Color.black);				//background colour is black
		
		lblDeleteCustomer = new JLabel("Enter ID of Customer you want to delete");//label
		lblDeleteCustomer.setLocation(70, 20);							//location and size
		lblDeleteCustomer.setSize(280, 30);
		lblDeleteCustomer.setFont(customFont6);							//set the labels font
		lblDeleteCustomer.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlDeleteCustomer.add(lblDeleteCustomer);						//and label to panel
					 
		btnDeleteCustomer = new JButton("Delete Customer");				//button to delete customer
		btnDeleteCustomer.setActionCommand("DeleteCustomer");			//buttons action command
		btnDeleteCustomer.addActionListener(clientHandler);				//event listener
		btnDeleteCustomer.setLocation(135, 75);							//size, location, visibility
		btnDeleteCustomer.setSize(200, 31);
		btnDeleteCustomer.setVisible(true);
		pnlDeleteCustomer.add(btnDeleteCustomer);								//add button to panel
		
		btnCancelDeleteCustomer = new JButton("Cancel Delete");					//button to cancel delete
		btnCancelDeleteCustomer.setActionCommand("CancelDeleteCustomer");		//buttons action command
		btnCancelDeleteCustomer.addActionListener(UIhandler);					//event listener
		btnCancelDeleteCustomer.setLocation(135, 115);					//size, location, visibility
		btnCancelDeleteCustomer.setSize(200, 31);
		btnCancelDeleteCustomer.setVisible(true);
		pnlDeleteCustomer.add(btnCancelDeleteCustomer);			//and button to the panel
					    
		txtDeleteCustomer = new JTextField();				//text field for id of customer to delete
		txtDeleteCustomer.setLocation(75, 75);				//location, size, visibility attributes
		txtDeleteCustomer.setSize(50, 30);
		txtDeleteCustomer.setVisible(true);
		pnlDeleteCustomer.add(txtDeleteCustomer);			//add text field to panel
			
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete Customer Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteCustomer.setBorder(title);
				
		pnlDeleteCustomer.setVisible(false);		//Panel to delete customer is not visible when application starts
			
		this.add(pnlDeleteCustomer);				//add the panel to the application window
	}//end deleteCustomerPanel()
	
	//panel and controls used for updating a new customer record 
	private void updateCustomer()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
					
		pnlUpdateCustomer = new JPanel();							//create a panel
		pnlUpdateCustomer.setLayout(new GridLayout(4, 2));			//layout is in grid format 4rows 2columns
		pnlUpdateCustomer.setSize(450, 250);							//set the panels size
		pnlUpdateCustomer.setLocation(475, 150);						//set the panels location
		pnlUpdateCustomer.setBackground(Color.black);				//background colour is black
				
		lblCustomerUpdateFirstName = new JLabel("Update First Name:");
		lblCustomerUpdateFirstName.setFont(customFont6);							//set the labels font
		lblCustomerUpdateFirstName.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblCustomerUpdateLastName = new JLabel("Update Last Name:");
		lblCustomerUpdateLastName.setFont(customFont6);							//set the labels font
		lblCustomerUpdateLastName.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		lblCustomerUpdateAddress = new JLabel("Update Address :");
		lblCustomerUpdateAddress.setFont(customFont6);								//set the labels font
		lblCustomerUpdateAddress.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtCustomerUpdateFirstName = new JTextField();							//text fields to hold customer details
		txtCustomerUpdateLastName = new JTextField();
		txtCustomerUpdateAddress = new JTextField();
		btnUpdateCustomer = new JButton("Update Customer");	//Button to update customer
		btnUpdateCustomer.setEnabled(false);						//not currently enabled
		btnUpdateCustomer.setActionCommand("UpdateCustomer"); //action command for update button
		btnUpdateCustomer.addActionListener(clientHandler);		//event listener for update button
		btnExitUpdateCustomer = new JButton("Cancel Update");			//cancel update button
		btnExitUpdateCustomer.setActionCommand("CancelUpdateCustomer"); //action command for cancel update
		btnExitUpdateCustomer.addActionListener(UIhandler);			//event listener for cancel update
				
		/*Adding all the controls that were created above to the panel, because layout for this panel
		is a grid, controls are added left to right and top to bottom 4rows 2columns*/
		pnlUpdateCustomer.add(lblCustomerUpdateFirstName);	//1st row 1st column
		pnlUpdateCustomer.add(txtCustomerUpdateFirstName);	//1st row 2nd column
		pnlUpdateCustomer.add(lblCustomerUpdateLastName);	//2nd row 1st column etc
		pnlUpdateCustomer.add(txtCustomerUpdateLastName);
		pnlUpdateCustomer.add(lblCustomerUpdateAddress);
		pnlUpdateCustomer.add(txtCustomerUpdateAddress);
		pnlUpdateCustomer.add(btnUpdateCustomer);
		pnlUpdateCustomer.add(btnExitUpdateCustomer);
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update Customer Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateCustomer.setBorder(title);
								
		pnlUpdateCustomer.setVisible(false);		//Panel to update customer is not visible when application starts
								
		this.add(pnlUpdateCustomer);				//Add the panel to the window	
				
		/*Underneath the panel there are some labels, buttons and text fields used to retrieve, a 
		particular customer and add him/her into the grid above for updating*/
		lblEnterIDCustomer = new JLabel("Enter ID of Customer you want to edit"); //label
		lblEnterIDCustomer.setLocation(500, 430);									   //attributes for label
		lblEnterIDCustomer.setSize(250, 30);
		lblEnterIDCustomer.setForeground(Color.white);								   
		lblEnterIDCustomer.setVisible(false);					//not visible on application launch
		add(lblEnterIDCustomer);								//add label to the window
						 
		btnSearchCustomerEdit = new JButton("Find Customer");		//Button for searching a customer
		btnSearchCustomerEdit.setActionCommand("FindCustomer");		//action command for the button
		btnSearchCustomerEdit.addActionListener(clientHandler);		//event listener for the button
		btnSearchCustomerEdit.setLocation(570, 470);					//location, size and visibility
		btnSearchCustomerEdit.setSize(145, 31);
		btnSearchCustomerEdit.setVisible(false);
		add(btnSearchCustomerEdit);									//add button to the window
		
		btnRefreshCustEdit = new JButton("Refresh");		//Button for searching a customer
		btnRefreshCustEdit.setActionCommand("RefreshUpdateCustomer");		//action command for the button
		btnRefreshCustEdit.addActionListener(UIhandler);		//event listener for the button
		btnRefreshCustEdit.setLocation(755, 470);					//location, size and visibility
		btnRefreshCustEdit.setSize(145, 31);
		btnRefreshCustEdit.setVisible(false);
		add(btnRefreshCustEdit);									//add button to the window
				    
		txtSearchCustomerEdit = new JTextField();						//text field to accept id for searching
		txtSearchCustomerEdit.setLocation(495, 470);					//location, size and visibility attributes
		txtSearchCustomerEdit.setSize(50, 30);
		txtSearchCustomerEdit.setVisible(false);
		add(txtSearchCustomerEdit);									//add text field to the window
	}//end updateCustomer()
	
	private void viewCustomersPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
							
		pnlCustomerShowArea = new JPanel();					//create a panel
		pnlCustomerShowArea.setLayout(null);					//layout is null
		pnlCustomerShowArea.setSize(1200, 600);				//set the panels size
		pnlCustomerShowArea.setLocation(90, 50);				//set the panels location
		pnlCustomerShowArea.setBackground(Color.black);		//background colour is black
							
		btnShowAllCustomers = new JButton("View All Customers");			//button to show all customers
		btnShowAllCustomers.setActionCommand("ShowAllCustomers");			//action command for button
		btnShowAllCustomers.addActionListener(clientHandler);			//event listener
		btnShowAllCustomers.setLocation(150, 480);						//location, size, visibility
		btnShowAllCustomers.setSize(200, 31);
		btnShowAllCustomers.setVisible(false);
		pnlCustomerShowArea.add(btnShowAllCustomers);				//add button to panel
						
		btnCancelShowCustomers = new JButton("Exit");					//button to exit show customer
		btnCancelShowCustomers.setActionCommand("CancelShowCustomers");		//action command for button
		btnCancelShowCustomers.addActionListener(UIhandler);				//event listener
		btnCancelShowCustomers.setLocation(420, 480);					//location, size, visibility 
		btnCancelShowCustomers.setSize(200, 31);
		btnCancelShowCustomers.setVisible(false);
		pnlCustomerShowArea.add(btnCancelShowCustomers);				//add button to panel
							
		lblViewCustomer = new JLabel("Enter Customer ID Number: ");		//label
		lblViewCustomer.setLocation(665, 480);						//size, location, visibility
		lblViewCustomer.setSize(200, 31);
		lblViewCustomer.setFont(customFont6);						//set the labels font
		lblViewCustomer.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblViewCustomer.setVisible(false);
		pnlCustomerShowArea.add(lblViewCustomer);					//add label to the panel
							
		txtSearchCustomer = new JTextField();						//text field to accept search id
		txtSearchCustomer.setLocation(850, 480);						//size, location and visibility
		txtSearchCustomer.setSize(200, 31);
		txtSearchCustomer.setVisible(false);
		pnlCustomerShowArea.add(txtSearchCustomer);				//add text field to panel
							
		btnSearchForCustomer = new JButton("Search Customer");			//button search for a customer
		btnSearchForCustomer.setActionCommand("ViewSearchCustomer");	//action command for button
		btnSearchForCustomer.addActionListener(clientHandler);				//event listener
		btnSearchForCustomer.setLocation(850, 520);						//location, size, visibility
		btnSearchForCustomer.setSize(200, 31);
		btnSearchForCustomer.setVisible(false);
		pnlCustomerShowArea.add(btnSearchForCustomer);					//add button to the panel
						
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "View Customer Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCustomerShowArea.setBorder(title);
							
		pnlCustomerShowArea.setVisible(false);		//Panel to view customer is not visible when application starts
							
		this.add(pnlCustomerShowArea);				//add the panel to the application window
	}//end viewCustomersPanel()
	
	//Creates a menu called Reminders, gives it some options like add, delete, view reminder(s). 
	//Them adds the menu and its options to the menu bar for the application
	private void reminderOptionsMenu()
	{
		reminderOptionsMenu = new JMenu("Reminders");					//Create Reminders options menu 		
		reminderOptionsMenu.setFont(customFont3);						//Setting some styling for it
		reminderOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(reminderOptionsMenu);							//Adding it to the application menu bar
						    
		addReminder = new JMenuItem("New Reminder");			//Create menu item (Create Reminder)
		addReminder.setFont(customFont4);						//Setting some styling for it
		addReminder.setForeground(Color.decode("#BE81F7"));
		reminderOptionsMenu.add(addReminder);					//Adding it to reminder options menu
		addReminder.addActionListener(menuHandler);				//Giving it an event listener
		
		updateReminder = new JMenuItem("Update Reminder");			//Create menu item (Update Reminder)
		updateReminder.setFont(customFont4);						//Setting some styling for it
		updateReminder.setForeground(Color.decode("#BE81F7"));
		reminderOptionsMenu.add(updateReminder);					//Adding it to reminder options menu
		updateReminder.addActionListener(menuHandler);				//Giving it an event listener
			
		deleteReminder = new JMenuItem("Delete Reminder");			//Create menu item (Delete Reminder)
		deleteReminder.setFont(customFont4);						//Setting some styling for it
		deleteReminder.setForeground(Color.decode("#BE81F7"));
		reminderOptionsMenu.add(deleteReminder);					//Adding it to reminder options menu
		deleteReminder.addActionListener(menuHandler);				//Giving it an event listener
		
		viewReminder = new JMenuItem("View Reminders");			//Create menu item (View Reminders)
		viewReminder.setFont(customFont4);						//Setting some styling for it
		viewReminder.setForeground(Color.decode("#BE81F7"));
		reminderOptionsMenu.add(viewReminder);					//Adding it to reminder options menu
		viewReminder.addActionListener(menuHandler);				//Giving it an event listener
	}//end reminderOptionsMenu()
	
	private void createReminderPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		pnlCreateReminder = new JPanel();					//create a panel
		pnlCreateReminder.setLayout(new GridLayout(5, 2));	//layout is Grid 5,2
		pnlCreateReminder.setSize(400, 300);				//set the panels size
		pnlCreateReminder.setLocation(480, 75);				//set the panels location
		pnlCreateReminder.setBackground(Color.black);		//background colour is black
		
		lblCreateReminderDate = new JLabel("Enter Date eg (yyyy-mm-dd):");
		lblCreateReminderDate.setFont(customFont6);							//set the labels font
		lblCreateReminderDate.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblCreateReminderTime = new JLabel("Enter Time (24hr Format):");
		lblCreateReminderTime.setFont(customFont6);							//set the labels font
		lblCreateReminderTime.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblCreateReminderSubject = new JLabel("Enter Subject:");
		lblCreateReminderSubject.setFont(customFont6);							//set the labels font
		lblCreateReminderSubject.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblCreateReminderDesc = new JLabel("Enter Description:");
		lblCreateReminderDesc.setFont(customFont6);							//set the labels font
		lblCreateReminderDesc.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtCreateReminderDate = new JTextField();	
		txtCreateReminderTime = new JTextField();
		txtCreateReminderSubject = new JTextField();
		txtCreateReminderDesc = new JTextField();
		btnCreateReminder = new JButton("Create New Reminder");				//button to create reminder
		btnCreateReminder.setActionCommand("CreateNewReminder");			//action command that links to button
		btnCreateReminder.addActionListener(clientHandler);					//event handler for button
		btnCancelCreateReminder = new JButton("Cancel");					//button to cancel create of reminder
		btnCancelCreateReminder.setActionCommand("CancelNewReminder");		//action command that links to button
		btnCancelCreateReminder.addActionListener(UIhandler);				//event handler for button
		
		//Adding controls to the grid from left to right, top to bottom
		pnlCreateReminder.add(lblCreateReminderDate);
		pnlCreateReminder.add(txtCreateReminderDate);
		pnlCreateReminder.add(lblCreateReminderTime);
		pnlCreateReminder.add(txtCreateReminderTime);
		pnlCreateReminder.add(lblCreateReminderSubject);
		pnlCreateReminder.add(txtCreateReminderSubject);
		pnlCreateReminder.add(lblCreateReminderDesc);
		pnlCreateReminder.add(txtCreateReminderDesc);
		pnlCreateReminder.add(btnCreateReminder);
		pnlCreateReminder.add(btnCancelCreateReminder);
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Create Reminder Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlCreateReminder.setBorder(title);
									
		pnlCreateReminder.setVisible(false);		//Panel to create reminder is not visible when application starts
		this.add(pnlCreateReminder);				//add the panel to the application window
	}//end createReminderPanel()
	
	//Panel and controls for updating a reminder
	private void updateReminderPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
				
		pnlUpdateReminder = new JPanel();					//create a panel
		pnlUpdateReminder.setLayout(new GridLayout(5, 2));	//layout is Grid 5,2
		pnlUpdateReminder.setSize(400, 300);				//set the panels size
		pnlUpdateReminder.setLocation(480, 75);				//set the panels location
		pnlUpdateReminder.setBackground(Color.black);		//background colour is black
				
		lblUpdateReminderDate = new JLabel("Update Date:");
		lblUpdateReminderDate.setFont(customFont6);							//set the labels font
		lblUpdateReminderDate.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblUpdateReminderTime = new JLabel("Update Time:");
		lblUpdateReminderTime.setFont(customFont6);							//set the labels font
		lblUpdateReminderTime.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblUpdateReminderSubject = new JLabel("Update Subject:");
		lblUpdateReminderSubject.setFont(customFont6);							//set the labels font
		lblUpdateReminderSubject.setForeground(Color.decode("#BE81F7"));		    //set labels foreground colour
		lblUpdateReminderDesc = new JLabel("Update Description:");
		lblUpdateReminderDesc.setFont(customFont6);							//set the labels font
		lblUpdateReminderDesc.setForeground(Color.decode("#BE81F7"));			//set labels foreground colour
		txtUpdateReminderDate = new JTextField();	
		txtUpdateReminderTime = new JTextField();
		txtUpdateReminderSubject = new JTextField();
		txtUpdateReminderDesc = new JTextField();
		btnUpdateReminder = new JButton("Update Reminder");				//button to update reminder
		btnUpdateReminder.setEnabled(false);
		btnUpdateReminder.setActionCommand("UpdateReminder");			//action command that links to button
		btnUpdateReminder.addActionListener(clientHandler);					//event handler for button
		btnCancelUpdateReminder = new JButton("Cancel");					//button to cancel update of reminder
		btnCancelUpdateReminder.setActionCommand("CancelUpdateReminder");		//action command that links to button
		btnCancelUpdateReminder.addActionListener(UIhandler);				//event handler for button
				
		pnlUpdateReminder.add(lblUpdateReminderDate);
		pnlUpdateReminder.add(txtUpdateReminderDate);
		pnlUpdateReminder.add(lblUpdateReminderTime);
		pnlUpdateReminder.add(txtUpdateReminderTime);
		pnlUpdateReminder.add(lblUpdateReminderSubject);
		pnlUpdateReminder.add(txtUpdateReminderSubject);
		pnlUpdateReminder.add(lblUpdateReminderDesc);
		pnlUpdateReminder.add(txtUpdateReminderDesc);
		pnlUpdateReminder.add(btnUpdateReminder);
		pnlUpdateReminder.add(btnCancelUpdateReminder);
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Update Reminder Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlUpdateReminder.setBorder(title);
										
		pnlUpdateReminder.setVisible(false);		//Panel to update reminder is not visible when application starts
										
		this.add(pnlUpdateReminder);				//Add the panel to the window	
						
		/*Underneath the panel there are some labels, buttons and text fields used to retrieve, a 
		particular reminder and add it into the grid above for updating*/
		lblEnterIDReminder = new JLabel("Enter ID of Reminder you want to edit"); //label
		lblEnterIDReminder.setLocation(480, 400);									   //attributes for label
		lblEnterIDReminder.setSize(250, 30);
		lblEnterIDReminder.setForeground(Color.white);								   
		lblEnterIDReminder.setVisible(false);					//not visible on application launch
		add(lblEnterIDReminder);								//add label to the window
								 
		btnSearchReminderEdit = new JButton("Find Reminder");		//Button for searching a reminder
		btnSearchReminderEdit.setActionCommand("FindReminder");		//action command for the button
		btnSearchReminderEdit.addActionListener(clientHandler);		//event listener for the button
		btnSearchReminderEdit.setLocation(550, 440);					//location, size and visibility
		btnSearchReminderEdit.setSize(145, 31);
		btnSearchReminderEdit.setVisible(false);
		add(btnSearchReminderEdit);									//add button to the window
				
		btnRefreshReminderEdit = new JButton("Refresh");		//Button for searching a reminder
		btnRefreshReminderEdit.setActionCommand("RefreshUpdateReminder");		//action command for the button
		btnRefreshReminderEdit.addActionListener(UIhandler);		//event listener for the button
		btnRefreshReminderEdit.setLocation(735, 440);					//location, size and visibility
		btnRefreshReminderEdit.setSize(145, 31);
		btnRefreshReminderEdit.setVisible(false);
		add(btnRefreshReminderEdit);									//add button to the window
						    
		txtSearchReminderEdit = new JTextField();						//text field to accept id for searching
		txtSearchReminderEdit.setLocation(475, 440);					//location, size and visibility attributes
		txtSearchReminderEdit.setSize(50, 30);
		txtSearchReminderEdit.setVisible(false);
		add(txtSearchReminderEdit);									//add text field to the window
	}//end updateReminderPanel()
	
	//panel and controls used for deleting a customer record
	private void deleteReminderPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
			
		pnlDeleteReminder = new JPanel();							//create a panel to delete reminder
		pnlDeleteReminder.setLayout(null);							//layout is null
		pnlDeleteReminder.setSize(400, 180);						//set the panels size
		pnlDeleteReminder.setLocation(490, 90);						//set the panels location
		pnlDeleteReminder.setBackground(Color.black);				//background colour is black
			
		lblDeleteReminder = new JLabel("Enter ID of Reminder you want to delete");//label
		lblDeleteReminder.setLocation(70, 20);							//location and size
		lblDeleteReminder.setSize(280, 30);
		lblDeleteReminder.setFont(customFont6);							//set the labels font
		lblDeleteReminder.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		pnlDeleteReminder.add(lblDeleteReminder);						//and label to panel
						 
		btnDeleteReminder = new JButton("Delete Reminder");				//button to delete reminder
		btnDeleteReminder.setActionCommand("DeleteReminder");			//buttons action command
		btnDeleteReminder.addActionListener(clientHandler);				//event listener
		btnDeleteReminder.setLocation(135, 75);							//size, location, visibility
		btnDeleteReminder.setSize(200, 31);
		btnDeleteReminder.setVisible(true);
		pnlDeleteReminder.add(btnDeleteReminder);								//add button to panel
			
		btnCancelDeleteReminder = new JButton("Cancel Delete");					//button to cancel delete
		btnCancelDeleteReminder.setActionCommand("CancelDeleteReminder");		//buttons action command
		btnCancelDeleteReminder.addActionListener(UIhandler);					//event listener
		btnCancelDeleteReminder.setLocation(135, 115);					//size, location, visibility
		btnCancelDeleteReminder.setSize(200, 31);
		btnCancelDeleteReminder.setVisible(true);
		pnlDeleteReminder.add(btnCancelDeleteReminder);			//and button to the panel
						    
		txtDeleteReminder = new JTextField();				//text field for id of customer to delete
		txtDeleteReminder.setLocation(75, 75);				//location, size, visibility attributes
		txtDeleteReminder.setSize(50, 30);
		txtDeleteReminder.setVisible(true);
		pnlDeleteReminder.add(txtDeleteReminder);			//add text field to panel
				
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Delete Reminder Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDeleteReminder.setBorder(title);
					
		pnlDeleteReminder.setVisible(false);		//Panel to delete reminder is not visible when application starts
				
		this.add(pnlDeleteReminder);				//add the panel to the application window
	}//end deleteReminderPanel()
	
	private void viewRemindersPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
							
		pnlReminderShowArea = new JPanel();					//create a panel
		pnlReminderShowArea.setLayout(null);					//layout is null
		pnlReminderShowArea.setSize(1200, 600);				//set the panels size
		pnlReminderShowArea.setLocation(90, 50);				//set the panels location
		pnlReminderShowArea.setBackground(Color.black);		//background colour is black
							
		btnShowAllReminder = new JButton("View Todays Reminders");			//button to show all Reminder
		btnShowAllReminder.setActionCommand("ShowTodaysReminder");			//action command for button
		btnShowAllReminder.addActionListener(clientHandler);			//event listener
		btnShowAllReminder.setLocation(110, 480);						//location, size, visibility
		btnShowAllReminder.setSize(200, 31);
		btnShowAllReminder.setVisible(false);
		pnlReminderShowArea.add(btnShowAllReminder);				//add button to panel
						
		btnCancelShowReminder = new JButton("Exit");					//button to exit show Reminder
		btnCancelShowReminder.setActionCommand("CancelShowReminder");		//action command for button
		btnCancelShowReminder.addActionListener(UIhandler);				//event listener
		btnCancelShowReminder.setLocation(380, 480);					//location, size, visibility 
		btnCancelShowReminder.setSize(200, 31);
		btnCancelShowReminder.setVisible(false);
		pnlReminderShowArea.add(btnCancelShowReminder);				//add button to panel
							
		lblViewReminder = new JLabel("Enter Search Date eg (yyyy-mm-dd): ");		//label
		lblViewReminder.setLocation(610, 480);						//size, location, visibility
		lblViewReminder.setSize(240, 31);
		lblViewReminder.setFont(customFont6);						//set the labels font
		lblViewReminder.setForeground(Color.decode("#BE81F7"));		//set labels foreground colour
		lblViewReminder.setVisible(false);
		pnlReminderShowArea.add(lblViewReminder);					//add label to the panel
							
		txtSearchReminder = new JTextField();						//text field to accept search id
		txtSearchReminder.setLocation(850, 480);						//size, location and visibility
		txtSearchReminder.setSize(200, 31);
		txtSearchReminder.setVisible(false);
		pnlReminderShowArea.add(txtSearchReminder);				//add text field to panel
							
		btnSearchForReminder = new JButton("Search Reminder");			//button search for a Reminder
		btnSearchForReminder.setActionCommand("ViewSearchReminder");	//action command for button
		btnSearchForReminder.addActionListener(clientHandler);				//event listener
		btnSearchForReminder.setLocation(850, 520);						//location, size, visibility
		btnSearchForReminder.setSize(200, 31);
		btnSearchForReminder.setVisible(false);
		pnlReminderShowArea.add(btnSearchForReminder);					//add button to the panel
						
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "View Reminder Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlReminderShowArea.setBorder(title);
							
		pnlReminderShowArea.setVisible(false);		//Panel to view Reminder is not visible when application starts
							
		this.add(pnlReminderShowArea);				//add the panel to the application window
	}//end viewCustomersPanel()
	
	//Creating an options menu for searching
	private void searchOptionsMenu()
	{
		searchOptionsMenu = new JMenu("Search");					//Create Search options menu 		
		searchOptionsMenu.setFont(customFont3);						//Setting some styling for it
		searchOptionsMenu.setForeground(Color.decode("#4C0B5F"));
		appMenuBar.add(searchOptionsMenu);							//Adding it to the application menu bar
						    
		dynamicSearch = new JMenuItem("Dynamic Search");			//Create menu item (Dynamic Search)
		dynamicSearch.setFont(customFont4);						//Setting some styling for it
		dynamicSearch.setForeground(Color.decode("#BE81F7"));
		searchOptionsMenu.add(dynamicSearch);					//Adding it to search options menu
		dynamicSearch.addActionListener(menuHandler);				//Giving it an event listener
	}//end searchOptionsMenu()
	
	//Panel and controls used for dynamic searches
	private void dynamicSearchPanel()
	{
		//Creating a title and line for titled lined border thickness of 2
		TitledBorder title;
		Border borderLine = BorderFactory.createLineBorder(Color.decode("#BE81F7"), 2);
		
		//The search panel
		pnlDynamicSearch = new JPanel();					//create a panel
		pnlDynamicSearch.setLayout(null);					//layout is null
		pnlDynamicSearch.setSize(1200, 600);				//set the panels size
		pnlDynamicSearch.setLocation(85, 50);				//set the panels location
		pnlDynamicSearch.setBackground(Color.black);		//background colour is black
		
		//Exit Button
		btnCancelDynamicSearch = new JButton("Exit");					//button to exit dynamic search
		btnCancelDynamicSearch.setActionCommand("CancelDynamicSearch");		//action command for button
		btnCancelDynamicSearch.addActionListener(UIhandler);				//event listener
		btnCancelDynamicSearch.setLocation(950, 30);					//location, size, visibility 
		btnCancelDynamicSearch.setSize(200, 31);
		btnCancelDynamicSearch.setVisible(true);
		pnlDynamicSearch.add(btnCancelDynamicSearch);				//add button to panel
		
		//Search label for combobox below
		lblSearchEntity = new JLabel("Choose an entity to search: ");		//label
		lblSearchEntity.setLocation(90, 35);								//size, location, visibility
		lblSearchEntity.setSize(240, 31);
		lblSearchEntity.setFont(customFont6);								//set the labels font
		lblSearchEntity.setForeground(Color.decode("#BE81F7"));				//set labels foreground colour
		lblSearchEntity.setVisible(true);
		pnlDynamicSearch.add(lblSearchEntity);							//add label to the panel
		
		//Combobox to pick entity your searching for
		cmbDynamicSearchEntities = new JComboBox<String>(entitySearchChoices);	//Create a combo box for entity your searching
		cmbDynamicSearchEntities.setSize(300, 30);						//set its size
		cmbDynamicSearchEntities.setLocation(40, 70);					//set its location
		cmbDynamicSearchEntities.setMaximumRowCount(5);					//Set max rows	
		cmbDynamicSearchEntities.addItemListener(listHandler);			//add an event handler for the combo box
		cmbDynamicSearchEntities.setVisible(true);						//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicSearchEntities);					//add combo box to the panel
		
		//Staff Search options
		//search options here after entity is choosen
		//Search label for combobox below
		lblSearchStaffType = new JLabel("Search for staff on what attribute: ");		//label
		lblSearchStaffType.setLocation(85, 105);											//size, location, visibility
		lblSearchStaffType.setSize(240, 31);
		lblSearchStaffType.setFont(customFont6);										//set the labels font
		lblSearchStaffType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchStaffType.setVisible(false);
		pnlDynamicSearch.add(lblSearchStaffType);										//add label to the panel
		
		//Combobox to pick type of staff search wanted
		cmbDynamicStaffSearchType = new JComboBox<String>(staffSearchChoices);	//Create a combo box for staff search type
		cmbDynamicStaffSearchType.setSize(300, 30);						//set its size
		cmbDynamicStaffSearchType.setLocation(40, 140);					//set its location
		cmbDynamicStaffSearchType.setMaximumRowCount(5);				//Set max rows	
		cmbDynamicStaffSearchType.addItemListener(listHandler);			//add an event handler for the combo box
		cmbDynamicStaffSearchType.setVisible(false);						//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicStaffSearchType);				//add combo box to the panel
		
		//first name staff search
		lblSearchStaffFName = new JLabel("Enter First Name: ");		//label
		lblSearchStaffFName.setLocation(130, 180);											//size, location, visibility
		lblSearchStaffFName.setSize(240, 31);
		lblSearchStaffFName.setFont(customFont6);										//set the labels font
		lblSearchStaffFName.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchStaffFName.setVisible(false);
		pnlDynamicSearch.add(lblSearchStaffFName);										//add label to the panel
		
		txtStaffFirstName = new JTextField();						//text field to accept search by staff name
		txtStaffFirstName.setLocation(85, 210);						//size, location and visibility
		txtStaffFirstName.setSize(200, 31);
		txtStaffFirstName.setVisible(false);
		pnlDynamicSearch.add(txtStaffFirstName);				//add text field to panel
		
		//last name staff search
		lblSearchStaffLName = new JLabel("Enter Last Name: ");		//label
		lblSearchStaffLName.setLocation(130, 180);											//size, location, visibility
		lblSearchStaffLName.setSize(240, 31);
		lblSearchStaffLName.setFont(customFont6);										//set the labels font
		lblSearchStaffLName.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchStaffLName.setVisible(false);
		pnlDynamicSearch.add(lblSearchStaffLName);										//add label to the panel
				
		txtStaffLastName = new JTextField();						//text field to accept search by staff name
		txtStaffLastName.setLocation(85, 210);						//size, location and visibility
		txtStaffLastName.setSize(200, 31);
		txtStaffLastName.setVisible(false);
		pnlDynamicSearch.add(txtStaffLastName);				//add text field to panel
		
		//emp type staff search
		lblSearchStaffEmp = new JLabel("Select Staff Employment Type: ");		//label
		lblSearchStaffEmp.setLocation(85, 180);											//size, location, visibility
		lblSearchStaffEmp.setSize(240, 31);
		lblSearchStaffEmp.setFont(customFont6);										//set the labels font
		lblSearchStaffEmp.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchStaffEmp.setVisible(false);
		pnlDynamicSearch.add(lblSearchStaffEmp);										//add label to the panel
						
		cmbStaffSearchEmpType = new JComboBox<String>(userTypeChoices);	//Create a combo box for staff search employment type
		cmbStaffSearchEmpType.setSize(300, 30);						//set its size
		cmbStaffSearchEmpType.setLocation(40, 210);					//set its location
		cmbStaffSearchEmpType.setMaximumRowCount(3);				//Set max rows	
		cmbStaffSearchEmpType.addItemListener(listHandler);			//add an event handler for the combo box
		cmbStaffSearchEmpType.setVisible(false);						//set combo box visible to true
		pnlDynamicSearch.add(cmbStaffSearchEmpType);				//add combo box to the panel
		
		//staff search buttons
		btnDynamicSearchStaffFName = new JButton("Search By First Name");				//button to search by staff first name
		btnDynamicSearchStaffFName.setActionCommand("DynamicSearchStaffFName");		//action command for button
		btnDynamicSearchStaffFName.addActionListener(clientHandler);					//event listener
		btnDynamicSearchStaffFName.setLocation(84, 250);									//location, size, visibility 
		btnDynamicSearchStaffFName.setSize(200, 31);
		btnDynamicSearchStaffFName.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchStaffFName);								//add button to panel
		
		btnDynamicSearchStaffLName = new JButton("Search By Last Name");				//button to search by staff last name
		btnDynamicSearchStaffLName.setActionCommand("DynamicSearchStaffLName");		//action command for button
		btnDynamicSearchStaffLName.addActionListener(clientHandler);					//event listener
		btnDynamicSearchStaffLName.setLocation(84, 250);									//location, size, visibility 
		btnDynamicSearchStaffLName.setSize(200, 31);
		btnDynamicSearchStaffLName.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchStaffLName);								//add button to panel
		
		btnDynamicSearchStaffEType = new JButton("Search By Employee Type");			//button to search by staff employment type
		btnDynamicSearchStaffEType.setActionCommand("DynamicSearchStaffEType");		//action command for button
		btnDynamicSearchStaffEType.addActionListener(clientHandler);					//event listener
		btnDynamicSearchStaffEType.setLocation(84, 250);									//location, size, visibility 
		btnDynamicSearchStaffEType.setSize(200, 31);
		btnDynamicSearchStaffEType.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchStaffEType);								//add button to panel
		
		//House Search options
		//Search label for combobox below
		lblSearchHouseType = new JLabel("Search for House on what attribute: ");		//label
		lblSearchHouseType.setLocation(80, 105);										//size, location, visibility
		lblSearchHouseType.setSize(240, 31);
		lblSearchHouseType.setFont(customFont6);										//set the labels font
		lblSearchHouseType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchHouseType.setVisible(false);
		pnlDynamicSearch.add(lblSearchHouseType);										//add label to the panel
				
		//Combobox to pick type of house search wanted
		cmbDynamicHouseSearchType = new JComboBox<String>(houseSearchChoices);	//Create a combo box for house search type
		cmbDynamicHouseSearchType.setSize(300, 30);						//set its size
		cmbDynamicHouseSearchType.setLocation(40, 140);					//set its location
		cmbDynamicHouseSearchType.setMaximumRowCount(5);				//Set max rows	
		cmbDynamicHouseSearchType.addItemListener(listHandler);			//add an event handler for the combo box
		cmbDynamicHouseSearchType.setVisible(false);						//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicHouseSearchType);				//add combo box to the panel
		
		//house search buttons
		btnDynamicSearchHouseRent = new JButton("Search Rentable Houses");		//button to search by houses for rent
		btnDynamicSearchHouseRent.setActionCommand("DynamicSearchRentHouse");		//action command for button
		btnDynamicSearchHouseRent.addActionListener(clientHandler);				//event listener
		btnDynamicSearchHouseRent.setLocation(84, 190);								//location, size, visibility 
		btnDynamicSearchHouseRent.setSize(200, 31);
		btnDynamicSearchHouseRent.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchHouseRent);							//add button to panel
		
		btnDynamicSearchHouseBuy = new JButton("Search Buyable Houses");		    //button to search by houses for sell
		btnDynamicSearchHouseBuy.setActionCommand("DynamicSearchBuyHouse");		//action command for button
		btnDynamicSearchHouseBuy.addActionListener(clientHandler);				//event listener
		btnDynamicSearchHouseBuy.setLocation(84, 190);								//location, size, visibility 
		btnDynamicSearchHouseBuy.setSize(200, 31);
		btnDynamicSearchHouseBuy.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchHouseBuy);							    //add button to panel
		
		//House search by town
		lblSearchHouseTown = new JLabel("Enter House Town Name: ");		//label
		lblSearchHouseTown.setLocation(105, 180);											//size, location, visibility
		lblSearchHouseTown.setSize(240, 31);
		lblSearchHouseTown.setFont(customFont6);										//set the labels font
		lblSearchHouseTown.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchHouseTown.setVisible(false);
		pnlDynamicSearch.add(lblSearchHouseTown);										//add label to the panel
						
		txtHouseTown = new JTextField();						//text field to accept search by staff name
		txtHouseTown.setLocation(85, 210);						//size, location and visibility
		txtHouseTown.setSize(200, 31);
		txtHouseTown.setVisible(false);
		pnlDynamicSearch.add(txtHouseTown);						//add text field to panel
		
		btnDynamicSearchHouseTown = new JButton("Search House By Town");		        //button to search by houses by town
		btnDynamicSearchHouseTown.setActionCommand("DynamicSearchHouseTown");		//action command for button
		btnDynamicSearchHouseTown.addActionListener(clientHandler);				//event listener
		btnDynamicSearchHouseTown.setLocation(84, 250);								//location, size, visibility 
		btnDynamicSearchHouseTown.setSize(200, 31);
		btnDynamicSearchHouseTown.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchHouseTown);							    //add button to panel
		
		//house search by county
		lblSearchHouseCounty = new JLabel("Enter House County Name: ");						//label
		lblSearchHouseCounty.setLocation(105, 180);											//size, location, visibility
		lblSearchHouseCounty.setSize(240, 31);
		lblSearchHouseCounty.setFont(customFont6);											//set the labels font
		lblSearchHouseCounty.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchHouseCounty.setVisible(false);
		pnlDynamicSearch.add(lblSearchHouseCounty);											//add label to the panel
						
		txtHouseCounty = new JTextField();							//text field to accept search by house county name
		txtHouseCounty.setLocation(85, 210);						//size, location and visibility
		txtHouseCounty.setSize(200, 31);
		txtHouseCounty.setVisible(false);
		pnlDynamicSearch.add(txtHouseCounty);						//add text field to panel
		
		btnDynamicSearchHouseCounty = new JButton("Search House By County");		    //button to search by houses by county
		btnDynamicSearchHouseCounty.setActionCommand("DynamicSearchHouseCounty");		//action command for button
		btnDynamicSearchHouseCounty.addActionListener(clientHandler);					//event listener
		btnDynamicSearchHouseCounty.setLocation(84, 250);								//location, size, visibility 
		btnDynamicSearchHouseCounty.setSize(200, 31);
		btnDynamicSearchHouseCounty.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchHouseCounty);							    //add button to panel
		
		//Customer options
		//Search label for combobox below
		lblSearchCustomerType = new JLabel("Search for Customer on what attribute");		//label
		lblSearchCustomerType.setLocation(70, 105);											//size, location, visibility
		lblSearchCustomerType.setSize(240, 31);
		lblSearchCustomerType.setFont(customFont6);										//set the labels font
		lblSearchCustomerType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchCustomerType.setVisible(false);
		pnlDynamicSearch.add(lblSearchCustomerType);										//add label to the panel
								
		//Combobox to pick type of customer search wanted
		cmbDynamicCustomerSearchType = new JComboBox<String>(customerSearchChoices);	//Create a combo box for customer search type
		cmbDynamicCustomerSearchType.setSize(300, 30);						//set its size
		cmbDynamicCustomerSearchType.setLocation(40, 140);					//set its location
		cmbDynamicCustomerSearchType.setMaximumRowCount(5);				//Set max rows	
		cmbDynamicCustomerSearchType.addItemListener(listHandler);			//add an event handler for the combo box
		cmbDynamicCustomerSearchType.setVisible(false);						//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicCustomerSearchType);				//add combo box to the panel
		
		//customer search by first name
		lblSearchCustFName = new JLabel("Enter Customer First Name: ");						//label
		lblSearchCustFName.setLocation(105, 180);											//size, location, visibility
		lblSearchCustFName.setSize(240, 31);
		lblSearchCustFName.setFont(customFont6);											//set the labels font
		lblSearchCustFName.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchCustFName.setVisible(false);
		pnlDynamicSearch.add(lblSearchCustFName);											//add label to the panel
								
		txtCustFName = new JTextField();							//text field to accept search by customer first name
		txtCustFName.setLocation(85, 210);						//size, location and visibility
		txtCustFName.setSize(200, 31);
		txtCustFName.setVisible(false);
		pnlDynamicSearch.add(txtCustFName);						//add text field to panel
				
		btnDynamicSearchCustFName = new JButton("Search First Name");		    //button to search by customer first name
		btnDynamicSearchCustFName.setActionCommand("DynamicSearchCustFName");		//action command for button
		btnDynamicSearchCustFName.addActionListener(clientHandler);					//event listener
		btnDynamicSearchCustFName.setLocation(84, 250);								//location, size, visibility 
		btnDynamicSearchCustFName.setSize(200, 31);
		btnDynamicSearchCustFName.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchCustFName);					//add button to the panel	
		
		//customer search by last name
		lblSearchCustLName = new JLabel("Enter Customer Last Name: ");						//label
		lblSearchCustLName.setLocation(105, 180);											//size, location, visibility
		lblSearchCustLName.setSize(240, 31);
		lblSearchCustLName.setFont(customFont6);											//set the labels font
		lblSearchCustLName.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchCustLName.setVisible(false);
		pnlDynamicSearch.add(lblSearchCustLName);											//add label to the panel
										
		txtCustLName = new JTextField();							//text field to accept search by customer last name
		txtCustLName.setLocation(85, 210);						//size, location and visibility
		txtCustLName.setSize(200, 31);
		txtCustLName.setVisible(false);
		pnlDynamicSearch.add(txtCustLName);						//add text field to panel
						
		btnDynamicSearchCustLName = new JButton("Search Last Name");		    //button to search by customers by last name
		btnDynamicSearchCustLName.setActionCommand("DynamicSearchCustLName");		//action command for button
		btnDynamicSearchCustLName.addActionListener(clientHandler);					//event listener
		btnDynamicSearchCustLName.setLocation(84, 250);								//location, size, visibility 
		btnDynamicSearchCustLName.setSize(200, 31);
		btnDynamicSearchCustLName.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchCustLName);					//add button to the panel	
		
		//customer search by address
		lblSearchCustAddress = new JLabel("Enter Customer Address: ");						//label
		lblSearchCustAddress.setLocation(105, 180);											//size, location, visibility
		lblSearchCustAddress.setSize(240, 31);
		lblSearchCustAddress.setFont(customFont6);											//set the labels font
		lblSearchCustAddress.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchCustAddress.setVisible(false);
		pnlDynamicSearch.add(lblSearchCustAddress);											//add label to the panel
												
		txtCustAddress = new JTextField();							//text field to accept search by customer address
		txtCustAddress.setLocation(85, 210);						//size, location and visibility
		txtCustAddress.setSize(200, 31);
		txtCustAddress.setVisible(false);
		pnlDynamicSearch.add(txtCustAddress);						//add text field to panel
								
		btnDynamicSearchCustAddress = new JButton("Search Address");		    //button to search by customers by address
		btnDynamicSearchCustAddress.setActionCommand("DynamicSearchCustAddress");		//action command for button
		btnDynamicSearchCustAddress.addActionListener(clientHandler);					//event listener
		btnDynamicSearchCustAddress.setLocation(84, 250);								//location, size, visibility 
		btnDynamicSearchCustAddress.setSize(200, 31);
		btnDynamicSearchCustAddress.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchCustAddress);					//add button to the panel	
		
		//Transaction Search options
		//Search label for combobox below
		lblSearchTransactionType = new JLabel("Search on what type of transaction: ");		//label
		lblSearchTransactionType.setLocation(80, 105);											//size, location, visibility
		lblSearchTransactionType.setSize(240, 31);
		lblSearchTransactionType.setFont(customFont6);										//set the labels font
		lblSearchTransactionType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransactionType.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransactionType);										//add label to the panel
								
		//Combobox to pick type of transaction search wanted
		cmbDynamicTransactionSearchType = new JComboBox<String>(houseChoices);	//Create a combo box for transaction search type
		cmbDynamicTransactionSearchType.setSize(300, 30);						//set its size
		cmbDynamicTransactionSearchType.setLocation(40, 140);					//set its location
		cmbDynamicTransactionSearchType.setMaximumRowCount(5);				//Set max rows	
		cmbDynamicTransactionSearchType.addItemListener(listHandler);			//add an event handler for the combo box
		cmbDynamicTransactionSearchType.setVisible(false);						//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicTransactionSearchType);				//add combo box to the panel
		
		//buy transaction option for search
		lblSearchBuyTransactionType = new JLabel("Search buy transactions: ");					//label
		lblSearchBuyTransactionType.setLocation(80, 180);											//size, location, visibility
		lblSearchBuyTransactionType.setSize(240, 31);
		lblSearchBuyTransactionType.setFont(customFont6);										//set the labels font
		lblSearchBuyTransactionType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchBuyTransactionType.setVisible(false);
		pnlDynamicSearch.add(lblSearchBuyTransactionType);										//add label to the panel
		
		cmbDynamicBuyTransactionSearchType = new JComboBox<String>(sellTransChoices);	//Create a combo box for sell transaction search type
		cmbDynamicBuyTransactionSearchType.setSize(300, 30);							//set its size
		cmbDynamicBuyTransactionSearchType.setLocation(40, 215);						//set its location
		cmbDynamicBuyTransactionSearchType.setMaximumRowCount(5);						//Set max rows	
		cmbDynamicBuyTransactionSearchType.addItemListener(listHandler);				//add an event handler for the combo box
		cmbDynamicBuyTransactionSearchType.setVisible(false);							//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicBuyTransactionSearchType);						//add combo box to the panel
		
		//buy transaction search by county
		lblSearchTransBuyCounty = new JLabel("Enter County Name: ");							//label
		lblSearchTransBuyCounty.setLocation(120, 255);											//size, location, visibility
		lblSearchTransBuyCounty.setSize(240, 31);
		lblSearchTransBuyCounty.setFont(customFont6);											//set the labels font
		lblSearchTransBuyCounty.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransBuyCounty.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransBuyCounty);											//add label to the panel
														
		txtTransBuyCounty = new JTextField();							//text field to accept search by transaction county
		txtTransBuyCounty.setLocation(85, 285);							//size, location and visibility
		txtTransBuyCounty.setSize(200, 31);
		txtTransBuyCounty.setVisible(false);
		pnlDynamicSearch.add(txtTransBuyCounty);						//add text field to panel
		
		btnDynamicSearchBuyTransCounty = new JButton("Search By County");		    //button to search by buy transactions by county
		btnDynamicSearchBuyTransCounty.setActionCommand("DynamicSearchBTransCounty");		//action command for button
		btnDynamicSearchBuyTransCounty.addActionListener(clientHandler);					//event listener
		btnDynamicSearchBuyTransCounty.setLocation(85, 325);								//location, size, visibility 
		btnDynamicSearchBuyTransCounty.setSize(200, 31);
		btnDynamicSearchBuyTransCounty.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchBuyTransCounty);					//add button to the panel
		
		//buy transaction search by cost
		lblSearchTransBuyCost = new JLabel("Enter Cost (add =, <, or > before number): ");	//label
		lblSearchTransBuyCost.setLocation(50, 255);											//size, location, visibility
		lblSearchTransBuyCost.setSize(280, 31);
		lblSearchTransBuyCost.setFont(customFont6);											//set the labels font
		lblSearchTransBuyCost.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransBuyCost.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransBuyCost);											//add label to the panel
																
		txtTransBuyCost = new JTextField();							//text field to accept search by transaction cost
		txtTransBuyCost.setLocation(85, 285);							//size, location and visibility
		txtTransBuyCost.setSize(200, 31);
		txtTransBuyCost.setVisible(false);
		pnlDynamicSearch.add(txtTransBuyCost);						//add text field to panel
		
		btnDynamicSearchBuyTransCost = new JButton("Search By Cost");		    //button to search by buy transactions by cost
		btnDynamicSearchBuyTransCost.setActionCommand("DynamicSearchBTransCost");		//action command for button
		btnDynamicSearchBuyTransCost.addActionListener(clientHandler);					//event listener
		btnDynamicSearchBuyTransCost.setLocation(85, 325);								//location, size, visibility 
		btnDynamicSearchBuyTransCost.setSize(200, 31);
		btnDynamicSearchBuyTransCost.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchBuyTransCost);					//add button to the panel
		
		//buy transaction search by customer
		lblSearchTransBuyCustomer = new JLabel("Enter Customer ID: ");										//label
		lblSearchTransBuyCustomer.setLocation(125, 255);											//size, location, visibility
		lblSearchTransBuyCustomer.setSize(240, 31);
		lblSearchTransBuyCustomer.setFont(customFont6);											//set the labels font
		lblSearchTransBuyCustomer.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransBuyCustomer.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransBuyCustomer);											//add label to the panel
																		
		txtTransBuyCustomer = new JTextField();							//text field to accept search by transaction customers
		txtTransBuyCustomer.setLocation(85, 285);							//size, location and visibility
		txtTransBuyCustomer.setSize(200, 31);
		txtTransBuyCustomer.setVisible(false);
		pnlDynamicSearch.add(txtTransBuyCustomer);						//add text field to panel
		
		btnDynamicSearchBuyTransCustomer = new JButton("Search By Customer");		    		//button to search by buy transactions by customer
		btnDynamicSearchBuyTransCustomer.setActionCommand("DynamicSearchBTransCustomer");		//action command for button
		btnDynamicSearchBuyTransCustomer.addActionListener(clientHandler);					//event listener
		btnDynamicSearchBuyTransCustomer.setLocation(85, 325);									//location, size, visibility 
		btnDynamicSearchBuyTransCustomer.setSize(200, 31);
		btnDynamicSearchBuyTransCustomer.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchBuyTransCustomer);									//add button to the panel
		
		//buy transaction search by estate agent
		lblSearchTransBuyEstateAgent = new JLabel("Enter Estate Agent ID: ");										//label
		lblSearchTransBuyEstateAgent.setLocation(115, 255);											//size, location, visibility
		lblSearchTransBuyEstateAgent.setSize(240, 31);
		lblSearchTransBuyEstateAgent.setFont(customFont6);											//set the labels font
		lblSearchTransBuyEstateAgent.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransBuyEstateAgent.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransBuyEstateAgent);											//add label to the panel
																				
		txtTransBuyEstateAgent = new JTextField();							//text field to accept search by transaction estate agents
		txtTransBuyEstateAgent.setLocation(85, 285);							//size, location and visibility
		txtTransBuyEstateAgent.setSize(200, 31);
		txtTransBuyEstateAgent.setVisible(false);
		pnlDynamicSearch.add(txtTransBuyEstateAgent);						//add text field to panel
		
		btnDynamicSearchBuyTransEAgent = new JButton("Search By Estate Agent");		    		//button to search by buy transactions by estate agent
		btnDynamicSearchBuyTransEAgent.setActionCommand("DynamicSearchBTransEAgent");		//action command for button
		btnDynamicSearchBuyTransEAgent.addActionListener(clientHandler);					//event listener
		btnDynamicSearchBuyTransEAgent.setLocation(85, 325);									//location, size, visibility 
		btnDynamicSearchBuyTransEAgent.setSize(200, 31);
		btnDynamicSearchBuyTransEAgent.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchBuyTransEAgent);									//add button to the panel
		
		//rent transaction option for search
		lblSearchRentTransactionType = new JLabel("Search rent transactions: ");					//label
		lblSearchRentTransactionType.setLocation(80, 180);											//size, location, visibility
		lblSearchRentTransactionType.setSize(240, 31);
		lblSearchRentTransactionType.setFont(customFont6);										//set the labels font
		lblSearchRentTransactionType.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchRentTransactionType.setVisible(false);
		pnlDynamicSearch.add(lblSearchRentTransactionType);										//add label to the panel
				
		cmbDynamicRentTransactionSearchType = new JComboBox<String>(rentTransChoices);	//Create a combo box for rent transaction search type
		cmbDynamicRentTransactionSearchType.setSize(300, 30);							//set its size
		cmbDynamicRentTransactionSearchType.setLocation(40, 215);						//set its location
		cmbDynamicRentTransactionSearchType.setMaximumRowCount(5);						//Set max rows	
		cmbDynamicRentTransactionSearchType.addItemListener(listHandler);				//add an event handler for the combo box
		cmbDynamicRentTransactionSearchType.setVisible(false);							//set combo box visible to true
		pnlDynamicSearch.add(cmbDynamicRentTransactionSearchType);						//add combo box to the panel
		
		//rent transaction search by county
		lblSearchTransRentCounty = new JLabel("Enter County Name: ");							//label
		lblSearchTransRentCounty.setLocation(120, 255);											//size, location, visibility
		lblSearchTransRentCounty.setSize(240, 31);
		lblSearchTransRentCounty.setFont(customFont6);											//set the labels font
		lblSearchTransRentCounty.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransRentCounty.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransRentCounty);											//add label to the panel
																
		txtTransRentCounty = new JTextField();							//text field to accept search by transaction county (rent)
		txtTransRentCounty.setLocation(85, 285);							//size, location and visibility
		txtTransRentCounty.setSize(200, 31);
		txtTransRentCounty.setVisible(false);
		pnlDynamicSearch.add(txtTransRentCounty);						//add text field to panel
				
		btnDynamicSearchRentTransCounty = new JButton("Search By County");		    		//button to search by rent transactions by county
		btnDynamicSearchRentTransCounty.setActionCommand("DynamicSearchRTransCounty");		//action command for button
		btnDynamicSearchRentTransCounty.addActionListener(clientHandler);					//event listener
		btnDynamicSearchRentTransCounty.setLocation(85, 325);								//location, size, visibility 
		btnDynamicSearchRentTransCounty.setSize(200, 31);
		btnDynamicSearchRentTransCounty.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchRentTransCounty);					//add button to the panel
		
		//rent transaction search by monthly rate
		lblSearchTransRentRate = new JLabel("Enter Monthly Rate (add =, <, or > before number): ");										//label
		lblSearchTransRentRate.setLocation(20, 255);											//size, location, visibility
		lblSearchTransRentRate.setSize(330, 31);
		lblSearchTransRentRate.setFont(customFont6);											//set the labels font
		lblSearchTransRentRate.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransRentRate.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransRentRate);											//add label to the panel
																		
		txtTransRentRate = new JTextField();							//text field to accept search by transaction rent rate
		txtTransRentRate.setLocation(85, 285);							//size, location and visibility
		txtTransRentRate.setSize(200, 31);
		txtTransRentRate.setVisible(false);
		pnlDynamicSearch.add(txtTransRentRate);						//add text field to panel
				
		btnDynamicSearchRentTransRentRate = new JButton("Search By Rate");		    //button to search by rent transactions by rate
		btnDynamicSearchRentTransRentRate.setActionCommand("DynamicSearchRTransRentRate");		//action command for button
		btnDynamicSearchRentTransRentRate.addActionListener(clientHandler);					//event listener
		btnDynamicSearchRentTransRentRate.setLocation(85, 325);								//location, size, visibility 
		btnDynamicSearchRentTransRentRate.setSize(200, 31);
		btnDynamicSearchRentTransRentRate.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchRentTransRentRate);					//add button to the panel
		
		//rent transaction search by customer
		lblSearchTransRentCustomer = new JLabel("Enter Customer ID: ");										//label
		lblSearchTransRentCustomer.setLocation(125, 255);											//size, location, visibility
		lblSearchTransRentCustomer.setSize(240, 31);
		lblSearchTransRentCustomer.setFont(customFont6);											//set the labels font
		lblSearchTransRentCustomer.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransRentCustomer.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransRentCustomer);											//add label to the panel
																				
		txtTransRentCustomer = new JTextField();							//text field to accept search by transaction customer
		txtTransRentCustomer.setLocation(85, 285);							//size, location and visibility
		txtTransRentCustomer.setSize(200, 31);
		txtTransRentCustomer.setVisible(false);
		pnlDynamicSearch.add(txtTransRentCustomer);						//add text field to panel
				
		btnDynamicSearchRentTransCustomer = new JButton("Search By Customer");		    		//button to search by rent transactions by customer
		btnDynamicSearchRentTransCustomer.setActionCommand("DynamicSearchRTransCustomer");		//action command for button
		btnDynamicSearchRentTransCustomer.addActionListener(clientHandler);					//event listener
		btnDynamicSearchRentTransCustomer.setLocation(85, 325);									//location, size, visibility 
		btnDynamicSearchRentTransCustomer.setSize(200, 31);
		btnDynamicSearchRentTransCustomer.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchRentTransCustomer);									//add button to the panel
		
		//rent transaction search by estate agent
		lblSearchTransRentEstateAgent = new JLabel("Enter Estate Agent ID: ");										//label
		lblSearchTransRentEstateAgent.setLocation(115, 255);											//size, location, visibility
		lblSearchTransRentEstateAgent.setSize(240, 31);
		lblSearchTransRentEstateAgent.setFont(customFont6);											//set the labels font
		lblSearchTransRentEstateAgent.setForeground(Color.decode("#BE81F7"));						//set labels foreground colour
		lblSearchTransRentEstateAgent.setVisible(false);
		pnlDynamicSearch.add(lblSearchTransRentEstateAgent);											//add label to the panel
																						
		txtTransRentEstateAgent = new JTextField();							//text field to accept search by transaction estate agent
		txtTransRentEstateAgent.setLocation(85, 285);							//size, location and visibility
		txtTransRentEstateAgent.setSize(200, 31);
		txtTransRentEstateAgent.setVisible(false);
		pnlDynamicSearch.add(txtTransRentEstateAgent);						//add text field to panel
				
		btnDynamicSearchRentTransEAgent = new JButton("Search By Estate Agent");		    //button to search by buy transactions by estate agent
		btnDynamicSearchRentTransEAgent.setActionCommand("DynamicSearchRTransEAgent");		//action command for button
		btnDynamicSearchRentTransEAgent.addActionListener(clientHandler);					//event listener
		btnDynamicSearchRentTransEAgent.setLocation(85, 325);									//location, size, visibility 
		btnDynamicSearchRentTransEAgent.setSize(200, 31);
		btnDynamicSearchRentTransEAgent.setVisible(false);
		pnlDynamicSearch.add(btnDynamicSearchRentTransEAgent);									//add button to the panel
		
		//Creating a titled, lined border
		title = BorderFactory.createTitledBorder(borderLine, "Dynamic Search Window");
		title.setTitleJustification(TitledBorder.CENTER);
		title.setTitleColor(Color.decode("#BE81F7"));
		pnlDynamicSearch.setBorder(title);
									
		pnlDynamicSearch.setVisible(false);		//Panel to view dynamic search is not visible when application starts
									
		this.add(pnlDynamicSearch);				//add the panel to the application window
	}//end dynamicSearchPanel()
}//end ApplicationMainWindow class

