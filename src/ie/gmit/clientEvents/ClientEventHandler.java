//Package
package ie.gmit.clientEvents;

//Package imports
import ie.gmit.clientFunctional.Client;
import ie.gmit.clientGUI.ApplicationMainWindow;
import ie.gmit.clientserver.Customer;
import ie.gmit.clientserver.House;
import ie.gmit.clientserver.Reminder;
import ie.gmit.clientserver.RentableHouse;
import ie.gmit.clientserver.SellableHouse;
import ie.gmit.clientserver.StaffMember;
import ie.gmit.clientserver.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*ClientEventHandler class implements action listener, this class is responsible for controls that send
 a request to the server for some information*/
public class ClientEventHandler implements ActionListener
{
	//Class variables
	private ListHandler listHandler = new ListHandler();	//Handles drop down lists(defined in package ie.gmit.Events)
	private Client client;									//Handles client functionality(defined in package ie.gmit.clientFunctional)
	
	//Integers to hold 0 or 1 response depending on if a request to server was done successfully or not
	private int authenticated;				//0 or 1 from server depending on successful authentication
	private int successfulUserCreation;		//0 or 1 from server depending on successful user creation
	private int successfulUserUpdate;		//0 or 1 from server depending on successful user update
	private int successfulUserDeletion;		//0 or 1 from server depending on successful user delete
	private int id;							//holds and id usually passed to server for searching
	private char staffType;					//Holds char representing staff type
	private String[] serverCreateStaffData; //Holds 0 or 1 for success and new staff id if success
	private int serverSignal;				//Holds signal from server
	private long downloadFileLength;		//Holds size of file for download
	private String downloadFileName;		//Holds name of file for download
	private StaffMember staffMember;		//Used to hold staff member data
	private House house;					//Used to hold house data
	private int istaffType;					//Int of combobox selection
	private ArrayList<StaffMember> members; 		//Holds list of staff member objects from server
	private ArrayList<Customer> customersList;      //Holds list of customers from server
	private ArrayList<House> houseList;				//Holds list of houses from server
	private ArrayList<RentableHouse> rHouseList;	//Holds list of rent transactions from server
	private ArrayList<SellableHouse> sHouseList;	//Holds list of sell transactions from server
	private ArrayList<String> files;				//Holds list of file names from server
	private String uploadFilePath;					//Holds file path of file for upload
	private String uploadName;						//Holds name of file for upload
	private long uploadSize;						//Holds size of file for upload
	private String downloadName;					//Holds name of download file
	private String downloadDestination;				//Holds path destination of download file
	private String usernameRecipient;				//Holds username of user getting sent file
	private String fileCompSend;					//Holds path of file for sending from computer
	private String fileOnlineSend;					//Holds name of file for sending from online directory
	private String fileRemove;						//Holds name of file for removing from online directory
	private char houseType;							//Holds either b or r (buy or rent house)
	private int ihouseType;							//Holds int index of house type drop down selection
	private int agentID;							//Holds estate agent id
	private int custID;								//Holds customer id
	private ArrayList<Integer> rentIDs;			//List for rent ids from the server
	private ArrayList<Integer> buyIDs;			//List for buy ids from the server
	private ArrayList<Integer> estateAgentIDs;  //List for estate agent ids from the server
	private ArrayList<Integer> custIDs;			//List for customer ids from the server
	private ArrayList<Reminder> remindersList;			//Holds list of reminders from the server
	public static char staffTypeAuthenticator;
	
	//Array holds column names for staff table
	String[] staffColumnNames = {"ID",
							"First Name",
							"Last Name",
							"Address",
							"PPS",
							"Salary",
							"Employment Type"};
	//Array holds column names for customer table
	String[] customerColumnNames = {"CustomerID",
			"FirstName",
			"LastName",
			"Address"};
	//Array holds column names for house table
	String[] houseColumnNames = {"ID",
							"Street",
							"Town",
							"County",
							"BuyOrSell"};
	//Array holds column names for rent transactions table
	String[] rentTransactionColumnNames = {"RentID",
			"HouseID",
			"StartDate",
			"EndDate",
			"MonthlyRate",
			"EstateAgentID",
			"CustomerID"};
	//Array holds column names for buy transactions table
	String[] buyTransactionColumnNames = {"BuyID",
			"HouseID",
			"Cost",
			"EstateAgentID",
			"CustomerID"};
	//Array holds column names for reminder table
	String[] reminderColumnNames = {"ReminderID",
			"Subject",
			"Description",
			"Date",
			"Time"};
	
	/*ClientEventHandler constructor accepts a client object. The custom made client object contains 
	methods necessary for a client, such as sending messages. It also contains both ObjectOutputStream
	and an ObjectInputStream through composition*/
	public ClientEventHandler(Client client)
	{
		this.client = client;
	}
	
	//Triggered when a button if clicked
	@SuppressWarnings("resource")
	public void actionPerformed(ActionEvent event) 
	{
		//Find the action command of the control
		switch(event.getActionCommand())
		{
			//SEND AUTHENTICATION INFO (CLIENT SIDE)
		    //If the action command is enterAuthenticationInfo
			case "enterAuthenticationInfo":
				client.sendMessage(1);				 	//Send server a signal to indicate what we are doing
				
				/*The User class is included on both client and server side programs as a packaged .jar
				 file. the class was written by myself and is packaged into a jar as its needed by both
				 programs (client and server) The source code is available as part of the project in jar classes
				 folder*/
				User authenticatingUser = new User();	//Create user object
				authenticatingUser.setUsername(ApplicationMainWindow.txtUsername.getText()); //set username
				authenticatingUser.setPassword(ApplicationMainWindow.txtPassword.getText()); //set password
				/*Listhandler gets the index of the item selected from a specific combo box, we can then use
				 the index to figure out which staff type was selected. Listhandler contains static ints
				 to hold these indexes*/
				
				/*Return selected index of login combo box with getLoginType() then pass it into getStaffType
				 which contains a switch statement to determine the staff type (defined bottom of class)*/ 
				staffType = getStaffTypeChar(listHandler.getLoginType()); 
				authenticatingUser.setStaffType(staffType);
				
				/*Once the user object has the values it needs send it to the server using the clients
				sendUserDetails() method. This method sends the user and waits for a response (0 or 1)
				stating if the login was successful or not*/
				authenticated = client.sendUserDetails(authenticatingUser);
			
				//Depending on if the login was successful or not output a relavant dialog box
				if(authenticated == 0)//if unsuccessful
				{
					//Dialog box saying unsuccessful
					JOptionPane.showMessageDialog(null, "Invalid user details - please try again",
							"Invalid User Authentication", JOptionPane.ERROR_MESSAGE);
				}
				else if(authenticated == 1)//if successful
				{
					//authentication panel is now gone and menu bar is now available
					staffTypeAuthenticator = authenticatingUser.getStaffType(); //stores the type of staff member you are
					ApplicationMainWindow.pnlAuthentication.setVisible(false);
					ApplicationMainWindow.appMenuBar.setVisible(true);
					
					//Dialog box saying successful
					JOptionPane.showMessageDialog(null, "Valid Entry - Welcome User "+authenticatingUser.getUsername()+"",
							"Valid User Authentication", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//EXIT THE PROGRAM (CLIENT SIDE)
				//If the action command is exitProgram
			case "exitProgram":
				client.sendMessage(2);			//Send server a signal to indicate what we are doing
				client.closeConnections();		//call the clients close connections method to close all connections
	         	System.exit(0);					//Terminate the program
				break;
				//CREATE NEW USER (CLIENT SIDE)
				//If the action command is CreateNewUser
			case "CreateNewUser":
				User newUser = new User();		//Create User object to hold new user data
				
				try
				{
					newUser.setStaffID(Integer.parseInt(ApplicationMainWindow.txtStaffIDUser.getText()));	//get/set id
					newUser.setUsername(ApplicationMainWindow.txtNewUsername.getText());	//get/set username
					newUser.setPassword(ApplicationMainWindow.txtNewPassword.getText());	//get/set password
					
					/*Return index of selected staff type from listHandler for specific combo box, 
					  pass it into getStaffType() to determine the staff type, the set users staff type to it*/
					staffType = getStaffTypeChar(listHandler.getCreateUserType()); 
					newUser.setStaffType(staffType);
					
					//Send new user object to server, wait for a response of 0 or 1
					client.sendMessage(3);			//Send server a signal to indicate what we are doing
					successfulUserCreation = client.sendUserDetails(newUser); //send user data and get response
					if(successfulUserCreation == 0)//if unsuccessful, means user must already exist
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Details - You have entered some invalid data, recheck it and try again",
								"Invalid User Creation", JOptionPane.ERROR_MESSAGE);
					}
					else if(successfulUserCreation == 1)//if successful means new user was created
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry - New User "+newUser.getUsername()+"",
								"Valid User Creation", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//catches incorrect entered data
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - A you must enter a number for ID",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATE AN EXISTING USER (CLIENT SIDE)
				//If the action command is UpdateUser
			case "UpdateUser":
				client.sendMessage(4);		//Send server a signal to indicate what we are doing
				User oldUser = new User();	//Create User object to hold old user data (user to be updated)
				oldUser.setUsername(ApplicationMainWindow.txtOldUsername.getText());//get from textfield/set old username
				oldUser.setPassword(ApplicationMainWindow.txtOldPassword.getText());//get from textfield/set old password
				
				/*Return index of selected staff type from listHandler for specific combo box, 
				  pass it into getStaffType() to determine the staff type, the set users staff type to it*/
				staffType = getStaffTypeChar(listHandler.getUpdateUserOldType());
				oldUser.setStaffType(staffType);
				
				User updatedUser = new User();//Create User object to hold update user data (data used to update user)
				updatedUser.setUsername(ApplicationMainWindow.txtUpdatedUsername.getText());//get from textfield/set updated username
				updatedUser.setPassword(ApplicationMainWindow.txtUpdatedPassword.getText());//get from textfield/set updated username
				
				/*Return index of selected staff type from listHandler for specific combo box, 
				  pass it into getStaffType() to determine the staff type, the set users staff type to it*/
				staffType = getStaffTypeChar(listHandler.getUpdateUserNewType());
				updatedUser.setStaffType(staffType);
				
				/*Sends both the original and the new user data to the server to update the user,
				 waits for a response of 0 or 1*/
				successfulUserUpdate = client.sendUpdateUserDetails(oldUser, updatedUser);
				
				if(successfulUserUpdate == 0)//if unsuccessful
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid details - cant update a user that doesnt exist",
							"Invalid User Update", JOptionPane.ERROR_MESSAGE);
				}
				else if(successfulUserUpdate == 1)//if successful
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Valid Entry - User "+oldUser.getUsername()+" is now User "+updatedUser.getUsername()+"",
							"Valid User Update", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//DELETING A USER (CLIENT SIDE)
				//If the action command is DeleteUser
			case "DeleteUser":
				client.sendMessage(5);//Send server a signal to indicate what we are doing
				User deletedUser = new User(); //Create User object to hold delete user data (user to be deleted)
				deletedUser.setUsername(ApplicationMainWindow.txtDeleteUsername.getText());//get from textfield/set deleted username
				deletedUser.setPassword(ApplicationMainWindow.txtDeletePassword.getText());//get from textfield/set deleted username
				
				/*Return index of selected staff type from listHandler for specific combo box, 
				  pass it into getStaffType() to determine the staff type, the set users staff type to it*/
				staffType = getStaffTypeChar(listHandler.getDeleteUserType());
				deletedUser.setStaffType(staffType);
				
				//Send delete user object to server, wait for a response of 0 or 1
				successfulUserDeletion = client.sendUserDetails(deletedUser);
				
				if(successfulUserDeletion == 0)//if unsuccessful
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid details - cant delete a user that doesnt exist",
							"Invalid User Deletion", JOptionPane.ERROR_MESSAGE);
				}
				else if(successfulUserDeletion == 1)//if successful
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Valid Entry - User "+deletedUser.getUsername()+" has been deleted",
							"Valid User Deletion", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//CREATING A NEW STAFF MEMBER (CLIENT SIDE)
				//if action command is "CreateNewStaffMember"
			case "CreateNewStaffMember":
				serverCreateStaffData = new String[2];	//holds result and id of new user
				StaffMember createStaff = new StaffMember();//object to hold new staff member data
				
				try
				{
					//Grab data from text boxes
					createStaff.setFirstName(ApplicationMainWindow.txtNewStaffFirstName.getText());//set objects data
					createStaff.setLastName(ApplicationMainWindow.txtNewStaffLastName.getText());
					createStaff.setAddress(ApplicationMainWindow.txtNewStaffAddress.getText());
					createStaff.setPps(ApplicationMainWindow.txtNewStaffPPS.getText());
					createStaff.setSalary(Double.parseDouble(ApplicationMainWindow.txtNewStaffSalary.getText()));
				
					/*Return index of selected staff type from listHandler for specific combo box, 
				  	pass it into getStaffType() to determine the staff type, then set new members staff type to it*/
					staffType = getStaffTypeChar(listHandler.getCreateStaffMemberType());
					createStaff.setStaffType(staffType);
				
					client.sendMessage(6);					//Send server a signal to indicate what we are doing
					client.sendStaffDetails(createStaff);//send staff member object to the server
					serverCreateStaffData = client.getServerStaffMessage(); //get a response 0 or 1 and 
																			//if successful the new id
					if(Integer.parseInt(serverCreateStaffData[1]) == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid details - Could not create Staff member",
							"Invalid Staff Creation", JOptionPane.ERROR_MESSAGE);
					}	
					else if(Integer.parseInt(serverCreateStaffData[1]) == 1)//if successful
					{
						//output relevant dialog box and the new staff id
						JOptionPane.showMessageDialog(null, "Valid Entry - New Staff Member ID is: "+serverCreateStaffData[0]+" Remember it. Its Yours",
								"Valid Staff Creation", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into salary text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//RETRIEVING A STAFF MEMBER (CLIENT SIDE)
				//if action command is "FindStaffMember"
			case "FindStaffMember":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchStaffEdit.getText());	//get the id
					
					client.sendMessage(7);			//Send server a signal to indicate what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that staff member
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find this member
						JOptionPane.showMessageDialog(null, "Invalid ID - Could not find this Staff member",
								"Invalid Staff Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						staffMember = client.getStaffMember();//retrieve the staff member
					
						//Here we set the text fields to the retreived members attributes
						ApplicationMainWindow.txtStaffUpdateFirstName.setText(staffMember.getFirstName());
						ApplicationMainWindow.txtStaffUpdateLastName.setText(staffMember.getLastName());
						ApplicationMainWindow.txtStaffUpdateAddress.setText(staffMember.getAddress());
						ApplicationMainWindow.txtStaffUpdatePPS.setText(staffMember.getPps());
						ApplicationMainWindow.txtStaffUpdateSalary.setText(Double.toString(staffMember.getSalary()));
						istaffType = getStaffTypeInt(staffMember.getStaffType());
						listHandler.setUpdateStaffMemberType(istaffType);
						ApplicationMainWindow.btnUpdateStaffMember.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtSearchStaffEdit.setEditable(false);//cannot edit text field holding the id
				
						//output successful dialog box
						JOptionPane.showMessageDialog(null, "Found this Staff member",
								"Valid Staff Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into search by ID text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATING A STAFF MEMBER (CLIENT SIDE)
				//if action command is "UpdateStaffMember" 
			case "UpdateStaffMember":
				StaffMember updateStaff = new StaffMember();//create an object to hold the data
				
				try
				{
					//Retrieve the data from the relevant text fields and add set the object to the values
					id = Integer.parseInt(ApplicationMainWindow.txtSearchStaffEdit.getText());
					updateStaff.setFirstName(ApplicationMainWindow.txtStaffUpdateFirstName.getText());
					updateStaff.setLastName(ApplicationMainWindow.txtStaffUpdateLastName.getText());
					updateStaff.setAddress(ApplicationMainWindow.txtStaffUpdateAddress.getText());
					updateStaff.setPps(ApplicationMainWindow.txtStaffUpdatePPS.getText());
					updateStaff.setSalary(Double.parseDouble(ApplicationMainWindow.txtStaffUpdateSalary.getText()));
				
					/*Return index of selected staff type from listHandler for specific combo box, 
				  	pass it into getStaffType() to determine the staff type, the set users staff type to it*/
					staffType = getStaffTypeChar(listHandler.getUpdateStaffMemberType());
					updateStaff.setStaffType(staffType);
				
					client.sendMessage(8);	//Send server a signal to indicate what we are doing
					client.sendSearchID(id);				//send id to search for staff member
					client.sendStaffDetails(updateStaff);	//send object with data for the update
					serverSignal = client.getServerSignal(); //get servers response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry - Staff Member has been updated",
								"Valid Staff Update", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Unable to update this particular user, SQL Issue",
								"Invalid Staff Update", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//catches incorrect entered data
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into salary text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//DELETING A STAFF MEMBER (CLIENT SIDE)
				//if action command is "DeleteStaffMember"
			case "DeleteStaffMember":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteStaffMember.getText());//get id for delete
					client.sendMessage(9);			//Send server a signal to indicate what we are doing
					client.sendSearchID(id);					//send the id
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry Staff Member at Id: "+id+" has been deleted" ,
								"Valid Staff Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Entry, this staff member does not exist" ,
								"Invalid Staff Deletion", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//catches incorrect entered data
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into delete staff id text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SHOW ALL STAFF MEMBERS (CLIENT SIDE)
				//if action command is "ShowAllStaff"
			case "ShowAllStaff":
				client.sendMessage(10);//send signal to server to indicate what we are doing
				Object[][] staff;	  //2D array to hold staff members data
				StaffMember member;   //object to hold each retrieved staff member 
				
				members = client.getStaffMemberList();//retrieve array list from server of all the staff members
				
				if(members.size() > 0)//if we have members in the array list
				{
					staff = new Object[members.size()][7];//create array to hold them
					
					//loop over entire list of staff members
					for(int row = 0; row < members.size(); ++row)//loop over each record
					{
						member = members.get(row); //get the object
						
						staff[row][0] = member.getId();				//pass it into an array
						staff[row][1] = member.getFirstName();
						staff[row][2] = member.getLastName();
						staff[row][3] = member.getAddress();
						staff[row][4] = member.getPps();
						staff[row][5] = member.getSalary();
						staff[row][6] = member.getStaffType();
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will used staff members 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(staff, staffColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowStaff = new JTable(model);
				    ApplicationMainWindow.scr1Staff = new JScrollPane(ApplicationMainWindow.tblShowStaff);
				    ApplicationMainWindow.scr1Staff.setLocation(100, 70);
				    ApplicationMainWindow.scr1Staff.setSize(1000, 400);
				    ApplicationMainWindow.scr1Staff.setVisible(true);
				    ApplicationMainWindow.pnlStaffMemberShowArea.add(ApplicationMainWindow.scr1Staff);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Members Have been found" ,
							"Valid Staff Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Staff Members Have been found" ,
							"Invalid Staff Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SEARCH FOR A STAFF MEMBER (CLIENT SIDE)
				//if action command is "ViewSearchStaffMember"
			case "ViewSearchStaffMember":
				Object[][] retreivedMember = new Object[1][7]; //2D array to hold staff members data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchStaffMember.getText());//get id entered
					client.sendMessage(11);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send it to server so it can perform a search
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "No Staff Members Have been found" ,
								"Invalid Staff Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						staffMember = client.getStaffMember();//get the staff member from the server
					
						retreivedMember[0][0] = staffMember.getId();		//Pass its data into a 2D array
						retreivedMember[0][1] = staffMember.getFirstName();
						retreivedMember[0][2] = staffMember.getLastName();
						retreivedMember[0][3] = staffMember.getAddress();
						retreivedMember[0][4] = staffMember.getPps();
						retreivedMember[0][5] = staffMember.getSalary();
						retreivedMember[0][6] = staffMember.getStaffType();
					
						//Create a table with no editing that outputs the data
						@SuppressWarnings("serial")
						DefaultTableModel model = new DefaultTableModel(retreivedMember, staffColumnNames) 
						{
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
				    
						//Set the tables attributes on the application window
						ApplicationMainWindow.tblShowStaff = new JTable(model);
						ApplicationMainWindow.scr1Staff = new JScrollPane(ApplicationMainWindow.tblShowStaff);
						ApplicationMainWindow.scr1Staff.setLocation(100, 70);
						ApplicationMainWindow.scr1Staff.setSize(1000, 400);
						ApplicationMainWindow.scr1Staff.setVisible(true);
						ApplicationMainWindow.pnlStaffMemberShowArea.add(ApplicationMainWindow.scr1Staff);
				    
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Member Has been found" ,
								"Valid Staff Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//catches incorrect entered data
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into search staff id text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPLOADING A FILE (CLIENT SIDE)
				//if action command is "UploadFile"
			case "UploadFile":
				client.sendMessage(12);//send server a signal to tell it what we are doing
				
				uploadFilePath = ApplicationMainWindow.txtUploadFile.getText();
				uploadFilePath = uploadFilePath.replace('\\', '/');
				File uploadFile = new File(uploadFilePath);	
				
				if(uploadFile.exists())								//Check if the file exists and if it does
				{
					client.sendMessage(1);							//Notify server to get ready for an upload
					uploadName = uploadFile.getName();				//Get the files name 
					uploadSize = uploadFile.length();				//Get the files size
					client.sendMessage(uploadName);					//Send name of uploading file to server
					client.sendMessage(uploadSize);					//Send size of uploading file to server
					
					try
					{
						byte[] fileByteArray = new byte[(int) uploadFile.length()];							//Create byte array of file size
						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(uploadFile));	//Create buffered input stream for the file
						bis.read(fileByteArray, 0, fileByteArray.length);									//Read file into an array of bytes
					    OutputStream os = client.clientSocket.getOutputStream();							//Get an output stream for the server
					    os.write(fileByteArray, 0, fileByteArray.length);									//Write array of bytes to the server
					    os.flush();																			//Clear the stream
					}
					catch(FileNotFoundException e)
					{
						e.printStackTrace();
					} 
					catch(IOException e) 
					{
						e.printStackTrace();
					}
					
					serverSignal = client.getServerSignal();
					
					if(serverSignal == 1)
					{
						JOptionPane.showMessageDialog(null, "The file: "+uploadName+" has been successfully uploaded to your online directory" ,
								"Valid File Upload", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)
					{
						JOptionPane.showMessageDialog(null, "There appears to have been a problem with the upload - please try again" ,
								"Invalid File Upload", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					client.sendMessage(-1);	//Send useless command to server allowing it to loop over and wait again
					
					JOptionPane.showMessageDialog(null, "This file does not exist, you may have entered the path incorrectly" ,
							"Invalid File Upload", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEWING/REQUESTING ALL FILE NAMES ON THE ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "ViewFiles"
			case "ViewFiles":
				client.sendMessage(13);//send server a signal to tell it what we are doing
				files = client.getFilesFromServer();	//Get list of file names from the server
				ApplicationMainWindow.showAllFiles.setText("");//Set text of text area to blank
				
				//Loop over the list of file names and append each one to the text area
				for(int i = 0; i < files.size(); ++i)
				{
					ApplicationMainWindow.showAllFiles.append(String.format("%-4s %-15s\n",
							 (i+1)+":", files.get(i)));
				}
				
				//output relevant dialog box
				JOptionPane.showMessageDialog(null, "Files in your online directory have been found successfully" ,
						"Valid File Search", JOptionPane.INFORMATION_MESSAGE);
				break;
				//DOWNLOADING A FILE FROM THE USERS ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "DownloadFile"
			case "DownloadFile":
				client.sendMessage(14);	//send signal to server to indicate what we are doing
				InputStream inStream;										//InputStream object
				downloadDestination = ApplicationMainWindow.txtDestDownloadFile.getText(); //get download path
				downloadDestination = downloadDestination.replace('\\', '/');
				if(!downloadDestination.endsWith("/"))
				{
					downloadDestination += "/";
				}
				
				//Create file object with download destination path
				File destFolder = new File(downloadDestination);
				
				if(destFolder.exists())					//Check if the destination exists and if it does
				{
					downloadName = ApplicationMainWindow.txtDownloadFile.getText();//get download file name
					client.sendMessage(downloadName);//send it to the server
					
					serverSignal = client.getServerSignal(); //get server response
					
					if(serverSignal == 1)//if successful
					{
						downloadFileLength = client.getFileLengthFromServer();//get size from server
						downloadFileName = client.getServerMessage();//get name from server
						byte[] fileByteArray = new byte[(int) downloadFileLength];			//Create array of bytes the same length as the file.
						
						try 
						{
							//download the file
							inStream = client.clientSocket.getInputStream(); //Instantiate the InputStream object
							FileOutputStream fileOutStream = new FileOutputStream(downloadDestination+""+downloadFileName);	//Create FileOutputStream set to filename received from server	
							BufferedOutputStream buffedOutStream = new BufferedOutputStream(fileOutStream);		//Pass that into BufferedOutputStream (bytes to characters)
							int bytesRead = inStream.read(fileByteArray, 0, fileByteArray.length);				//Read bytes from server and pass into byte array,
							buffedOutStream.write(fileByteArray, 0, bytesRead);								//Write these bytes (buffered) to file.
							buffedOutStream.close();														//Close the BufferedOutputStream
							
							//output relevant dialog box
							JOptionPane.showMessageDialog(null, "File "+downloadFileName+" has been downloaded to "+downloadDestination ,
									"Valid Download", JOptionPane.INFORMATION_MESSAGE);
						} 
						catch(IOException e) 
						{
							e.printStackTrace();
						}									
					}
					else if(serverSignal == 0)//if no success
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Problem with the download - ensure the name you enter matches the files (case sensitive)" ,
								"Invalid Download", JOptionPane.ERROR_MESSAGE);
					}
				}
				else//if download destination path doesnt exist
				{
					JOptionPane.showMessageDialog(null, "The download destination does not exist" ,
							"Invalid Download", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SENDING A FILE FROM USERS PC TO ANOTHER USER(CLIENT SIDE)
				//if action command is "SendUserCompFile"
			case "SendUserCompFile":
				client.sendMessage(15);//send signal to server to indicate what we are doing
				File file; 
				
				usernameRecipient = ApplicationMainWindow.txtUsernameSendFile.getText();//username getting file
				fileCompSend = ApplicationMainWindow.txtCompPathSendFile.getText(); //path of file for sending
				fileCompSend = fileCompSend.replace('\\', '/');
					
				file = new File(fileCompSend);//create file object of the file for sending
					
				if(file.exists())					//Check if the file exists and if it does
				{
					client.sendMessage(usernameRecipient);
					uploadName = file.getName();				//Get the files name 
					uploadSize = file.length();				//Get the files size
					client.sendMessage(uploadName);					//Send name of uploading file to server
					client.sendMessage(uploadSize);					//Send size of uploading file to server
						
					serverSignal = client.getServerSignal();		//get server response
						
					if(serverSignal == 1)//if all okay
					{
						try
						{
							//send the file
							byte[] fileByteArray = new byte[(int) file.length()];							//Create byte array of file size
							BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));	//Create buffered input stream for the file
							bis.read(fileByteArray, 0, fileByteArray.length);									//Read file into an array of bytes
							OutputStream os = client.clientSocket.getOutputStream();							//Get an output stream for the server
							os.write(fileByteArray, 0, fileByteArray.length);									//Write array of bytes to the server
							os.flush();						//Clear the stream
							    
							//output relevant dialog box
						    JOptionPane.showMessageDialog(null, "File "+uploadName+" has been succesfully sent to "+usernameRecipient ,
										"Valid Send", JOptionPane.INFORMATION_MESSAGE);
						}
						catch(FileNotFoundException e)
						{
							e.printStackTrace();
						} 
						catch(IOException e) 
						{
							e.printStackTrace();
						}
					}
					else if(serverSignal == 0)//if the user does not exist
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "This user does not exist" ,
								"Invalid Download", JOptionPane.ERROR_MESSAGE);
					}
				}
				else//file does not exist
				{
					JOptionPane.showMessageDialog(null, "The file your trying to send does not exist" ,
							"Invalid Download", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEWING/REQUESTING ALL FILE NAMES ON THE ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "ViewSendableFiles"
			case "ViewSendableFiles":
				client.sendMessage(16);//send server a signal to tell it what we are doing
				files = client.getFilesFromServer();	//get all files from the server
				ApplicationMainWindow.showSendableFiles.setText("");
				
				//loop over list and append all names to text area
				for(int i = 0; i < files.size(); ++i)
				{
					ApplicationMainWindow.showSendableFiles.append(String.format("%-4s %-15s\n",
							 (i+1)+":", files.get(i)));
				}
				
				JOptionPane.showMessageDialog(null, "Files in your online directory have been found successfully" ,
						"Valid File Search", JOptionPane.INFORMATION_MESSAGE);
				break;
				//SENDING USER A FILE THAT IS ON THE CURRENT USERS ONLINE DIRECTORY
				//if action command is "SendUserOnlineFile"
			case "SendUserOnlineFile":
				client.sendMessage(17);//send signal to tell server what we are doing
				
				//Get user recieving and the name of the file in online directory
				usernameRecipient = ApplicationMainWindow.txtUsernameSendFileO.getText();
				fileOnlineSend = ApplicationMainWindow.txtOnlineNameSendFile.getText();
				
				//send recipient name and file name to server
				client.sendMessage(usernameRecipient);
				client.sendMessage(fileOnlineSend);
				
				serverSignal = client.getServerSignal();//get server response
					
				if(serverSignal == 1)//success
				{
						JOptionPane.showMessageDialog(null, "User "+usernameRecipient+" has successfully recieved the file "+fileOnlineSend ,
								"Valid Send To User "+usernameRecipient, JOptionPane.INFORMATION_MESSAGE);
				}
				else if(serverSignal == 0)//no success
				{
						JOptionPane.showMessageDialog(null, "Something went wront the file could not be sent - please try again" ,
								"Invalid File Send", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEWING/REQUESTING ALL FILE NAMES ON THE ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "ViewRemovableFiles"
			case "ViewRemovableFiles":
				client.sendMessage(18);//send server a signal to tell it what we are doing
				files = client.getFilesFromServer();
				ApplicationMainWindow.showAllRemoveableFiles.setText("");
				
				//loop over list and append all names to text area
				for(int i = 0; i < files.size(); ++i)
				{
					ApplicationMainWindow.showAllRemoveableFiles.append(String.format("%-4s %-15s\n",
							 (i+1)+":", files.get(i)));
				}
				
				JOptionPane.showMessageDialog(null, "Files in your online directory have been found successfully" ,
						"Valid File Search", JOptionPane.INFORMATION_MESSAGE);
				break;
				//REMOVING A FILE FROM USERS ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "RemoveFile"
			case "RemoveFile":
				client.sendMessage(19);//send server a signal to tell it what we are doing
				
				//Get name of file to remove from online directory and send to the server
				fileRemove = ApplicationMainWindow.txtRemoveFile.getText();
				client.sendMessage(fileRemove);
				
				serverSignal = client.getServerSignal();//get server response
				
				if(serverSignal == 1)//success
				{
					JOptionPane.showMessageDialog(null, "File in your online directory has been deleted successfully" ,
							"Valid File Deletion", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(serverSignal == 0)//no success
				{
					JOptionPane.showMessageDialog(null, "File in your online directory hasnt been deleted - try again" ,
							"Invalid File Deletion", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//CREATING A NEW HOUSE, SENDING HOUSE DATA TO THE SERVER (CLIENT SIDE)
				//if action command is "CreateNewHouse"
			case "CreateNewHouse":
				House newHouse = new House();	//create a house object
			
				//Get house data from text boxes
				newHouse.setStreet(ApplicationMainWindow.txtNewHouseStreet.getText()); //set its data
				newHouse.setTown(ApplicationMainWindow.txtNewHouseTown.getText());
				newHouse.setCounty(ApplicationMainWindow.txtNewHouseCounty.getText());
			
				/*Return selected index of login combo box with getNewHouseType() then pass it into getHouseType
			 	which contains a switch statement to determine the house type (defined bottom of class)*/ 
				houseType = getHouseType(listHandler.getNewHouseType()); 
				newHouse.setRentOrSale(houseType);
				
				//Making sure user has selected a type of house
				if(houseType == 'r' || houseType == 'b')
				{	
					client.sendMessage(20);//send server a signal to tell it what we are doing
					client.sendHouseDetails(newHouse); //send house data
					
					serverSignal = client.getServerSignal(); //get server response
				
					if(serverSignal == 1)//success
					{
						JOptionPane.showMessageDialog(null, "New House has been successfully created" ,
							"Valid House Creation", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//no success
					{
						JOptionPane.showMessageDialog(null, "There was a problem creating this house - please try again" ,
								"Invalid House Creation", JOptionPane.ERROR_MESSAGE);
					}
				}
				else//if the user has no selected a house type
				{
					JOptionPane.showMessageDialog(null, "You must select a house type from combo box - please try again" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//RETRIEVING A HOUSE (CLIENT SIDE)
				//if action command is "FindHouse"
			case "FindHouse":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchHouseEdit.getText());	//get the id
					client.sendMessage(21);			//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that staff member
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find this member
						JOptionPane.showMessageDialog(null, "Invalid ID - Could not find thia Staff member",
								"Invalid House Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						house = client.getHouseDetails();//retrieve the staff member
					
						//Here we set the text fields to the retrieved members attributes
						ApplicationMainWindow.txtUpdatedHouseStreet.setText(house.getStreet());
						ApplicationMainWindow.txtUpdatedHouseTown.setText(house.getTown());
						ApplicationMainWindow.txtUpdatedHouseCounty.setText(house.getCounty());
						ihouseType = getHouseTypeInt(house.getRentOrSale());
						listHandler.setUpdateHouseType(ihouseType);
						ApplicationMainWindow.btnUpdateHouse.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtSearchHouseEdit.setEditable(false);//cannot edit text field holding the id
					}
				}
				catch(NumberFormatException e)//incorrect data format entered
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into search house id text box",
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATING A HOUSE (CLIENT SIDE)
				//if action command is "UpdateHouse"
			case "UpdateHouse":
				client.sendMessage(22);	//Send server a signal to indicate what we are doing
				House updateHouse = new House();//create an object to hold the data
				
				//Retrieve the data from the relevant text fields and add set the object to the values
			    id = Integer.parseInt(ApplicationMainWindow.txtSearchHouseEdit.getText());
			    updateHouse.setStreet(ApplicationMainWindow.txtUpdatedHouseStreet.getText());
			    updateHouse.setTown(ApplicationMainWindow.txtUpdatedHouseTown.getText());
			    updateHouse.setCounty(ApplicationMainWindow.txtUpdatedHouseCounty.getText());
			    
			    /*Return index of selected staff type from listHandler for specific combo box, 
				  pass it into getStaffType() to determine the house, the set house to it*/
			    houseType = getHouseType(listHandler.getUpdateHouseType());
			    updateHouse.setRentOrSale(houseType);
			 
			  //Making sure user has selected a type of house
			    if(houseType == 'r' || houseType == 'b')
				{	
			    	client.sendSearchID(id);				//send id to search for house
			    	client.sendHouseDetails(updateHouse);	//send object with data for the update
			    	serverSignal = client.getServerSignal(); //get servers response
				
			    	if(serverSignal == 1)//if successful
			    	{
			    		//output relevant dialog box
			    		JOptionPane.showMessageDialog(null, "Valid Entry - House has been updated",
							"Valid House Update", JOptionPane.INFORMATION_MESSAGE);
			    	}
			    	else if(serverSignal == 0)//otherwise
			    	{
			    		//output relevant dialog box
			    		JOptionPane.showMessageDialog(null, "Unable to update this particular house, SQL Issue",
			    				"Invalid Staff Update", JOptionPane.ERROR_MESSAGE);
			    	}
				}
			    else//if the user has no selected a house type
			    {
			    	JOptionPane.showMessageDialog(null, "You must select a house type from combo box - please try again" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
			    }
				break;
				//DELETING A HOUSE (CLIENT SIDE)
				//if action command is "DeleteHouse"
			case "DeleteHouse":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteHouse.getText());//get id for delete
					client.sendMessage(23);//Send server a signal to indicate what we are doing
					client.sendSearchID(id);					//send the id
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry house at Id: "+id+" has been deleted" ,
								"Valid House Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						JOptionPane.showMessageDialog(null, "Invalid Entry, this house does not exist" ,
								"Invalid House Deletion", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				catch(NumberFormatException e)//incorrect data format entered
				{
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into search house id text box" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
			break;
			//SHOW ALL HOUSES (CLIENT SIDE)
			//if action command is "ShowAllHouses"
			case "ShowAllHouses":
				client.sendMessage(24);//send signal to server to indicate what we are doing
				Object[][] houses;	  //2D array to hold staff members data
				House houseMember;   //object to hold each retrieved staff member 
				
				houseList = client.getHouseList();	//retrieve array list from server of all the houses
				
				if(houseList.size() > 0)//if we have members in the array list
				{
					houses = new Object[houseList.size()][5];//create array to hold them
					
					for(int row = 0; row < houseList.size(); ++row)//loop over each record
					{
						houseMember = houseList.get(row); //get the object
						
						houses[row][0] = houseMember.getId();				//pass it into an array
						houses[row][1] = houseMember.getStreet();
						houses[row][2] = houseMember.getTown();
						houses[row][3] = houseMember.getCounty();
						houses[row][4] = houseMember.getRentOrSale();
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will use house (object) 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(houses, houseColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowHouses = new JTable(model);
				    ApplicationMainWindow.scr1House = new JScrollPane(ApplicationMainWindow.tblShowHouses);
				    ApplicationMainWindow.scr1House.setLocation(100, 70);
				    ApplicationMainWindow.scr1House.setSize(1000, 400);
				    ApplicationMainWindow.scr1House.setVisible(true);
				    ApplicationMainWindow.pnlHouseShowArea.add(ApplicationMainWindow.scr1House);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Houses Have been found" ,
							"Valid House Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Houses Have been found" ,
							"Invalid House Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SEARCH FOR A PARTICULAR HOUSE (CLIENT SIDE)
				//if action command is "ViewSearchHouse"
			case "ViewSearchHouse":
				Object[][] retreivedHouse = new Object[1][5]; //2D array to hold house data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchHouse.getText());//get id entered
					client.sendMessage(25);//send signal to server to indicate what we are doing
					client.sendSearchID(id);					//send it to server so it can perform a search
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "No House Has been found" ,
								"Invalid House Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						house = client.getHouseDetails();//get the house from the server
					
						retreivedHouse[0][0] = house.getId();		//Pass its data into a 2D array
						retreivedHouse[0][1] = house.getStreet();
						retreivedHouse[0][2] = house.getTown();
						retreivedHouse[0][3] = house.getCounty();
						retreivedHouse[0][4] = house.getRentOrSale();
				
						//Create a table with no editing that outputs the data
						@SuppressWarnings("serial")
						DefaultTableModel model = new DefaultTableModel(retreivedHouse, houseColumnNames) 
						{
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
				    
						//set the tables attributes
						ApplicationMainWindow.tblShowHouses = new JTable(model);
						ApplicationMainWindow.scr1House = new JScrollPane(ApplicationMainWindow.tblShowHouses);
						ApplicationMainWindow.scr1House.setLocation(100, 70);
						ApplicationMainWindow.scr1House.setSize(1000, 400);
						ApplicationMainWindow.scr1House.setVisible(true);
						ApplicationMainWindow.pnlHouseShowArea.add(ApplicationMainWindow.scr1House);
				    
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "House Has been found" ,
								"Valid House Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if incorrect data format is entered
				{
					JOptionPane.showMessageDialog(null, "Invalid Details - Enter a number into search house id text box" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEWING/REQUESTING ALL FILE NAMES ON THE ONLINE DIRECTORY (CLIENT SIDE)
				//if action command is "ViewAllTheFiles"
			case "ViewAllTheFiles":
				client.sendMessage(26);//send server a signal to tell it what we are doing
				files = client.getFilesFromServer(); //get list of files from the server
				ApplicationMainWindow.showFiles.setText("");//set text area to blank
				
				//loop over list of files and append each name to text area
				for(int i = 0; i < files.size(); ++i)
				{
					ApplicationMainWindow.showFiles.append(String.format("%-4s %-15s\n",
							 (i+1)+":", files.get(i)));
				}
				
				//output relevant dialog box
				JOptionPane.showMessageDialog(null, "Files in your online directory have been found successfully" ,
						"Valid File Search", JOptionPane.INFORMATION_MESSAGE);
				break;
				//CREATE A RENT TRANSACTION (CLIENT SIDE)
				//if action command is "CreateRentTransaction"
			case "CreateRentTransaction":
				RentableHouse newRentHouse = new RentableHouse();
				
				try
				{
					//Grab rent transaction data
					newRentHouse.setId(Integer.parseInt(ApplicationMainWindow.txtTransRentHouseID.getText()));
					newRentHouse.setFromDate(ApplicationMainWindow.txtTransStartDate.getText());
					newRentHouse.setToDate(ApplicationMainWindow.txtTransEndDate.getText());
					newRentHouse.setRate(Double.parseDouble(ApplicationMainWindow.txtTransMonthlyRate.getText()));
					agentID = Integer.parseInt(ApplicationMainWindow.txtEstateAgentID.getText());
					custID = Integer.parseInt(ApplicationMainWindow.txtRentCustID.getText());
			
					client.sendMessage(27);//send server a signal to tell it what we are doing
					client.sendMessage(agentID);//send estate agent id
					client.sendMessage(custID);//send customer id
					client.sendRentableHouseDetails(newRentHouse);//send rentable house data
				
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 1)//success
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "New Rent Transaction Has been Added" ,
								"Valid", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//no success
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "              Incorrect Information\n"+ 
														"There may not be a house with this ID\n"+
														"A house may already may be booked on this date\n"+
														"There might not be a customer with this ID\n"+
														"There may not be an estate agent with this ID\n"+
														"              Please Try Again",
							"Invalid", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format entered
				{
					JOptionPane.showMessageDialog(null, "Invalid - Check the house, estate agent and customer id text boxes hold numbers" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
			break;
			//CREATE A BUY TRANSACTION (CLIENT SIDE)
			//if action command is "CreateBuyTransaction"
			case "CreateBuyTransaction":
				SellableHouse newBuyHouse = new SellableHouse();
				
				try
				{
					//grab sellable house data from text boxes
					newBuyHouse.setId(Integer.parseInt(ApplicationMainWindow.txtTransBuyHouseID.getText()));
					newBuyHouse.setCost(Double.parseDouble(ApplicationMainWindow.txtBuyCost.getText()));
					agentID = Integer.parseInt(ApplicationMainWindow.txtBuyEstateAgentID.getText());
					custID = Integer.parseInt(ApplicationMainWindow.txtBuyCustID.getText());
				
					client.sendMessage(28);//send server a signal to tell it what we are doing
					client.sendMessage(agentID);//send estate id
					client.sendMessage(custID);//send customer id
					client.sendSellableHouseDetails(newBuyHouse);//send sellable house data
				
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 1)//success
					{	
						JOptionPane.showMessageDialog(null, "New Buy Transaction Has been Added" ,
								"Valid", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//no success
					{
						JOptionPane.showMessageDialog(null, "              Incorrect Information\n"+ 
														"There may not be a house with this ID\n"+
														"There might not be a customer with this ID\n"+
														"There may not be an estate agent with this ID\n"+
														"              Please Try Again",
							"Invalid", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if data format is invalid
				{
					JOptionPane.showMessageDialog(null, "Invalid - All text boxes must get a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
			break;
			//SEARCH FOR A PARTICULAR RENT TRANSACTION (CLIENT SIDE)
			//if action command is "SearchUpdateRentTransaction"
			case "SearchUpdateRentTransaction":
				RentableHouse retreivedRHouse = new RentableHouse();//holds entered rentable house data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtTransUpdateRentID.getText());	//get the id
					client.sendMessage(29);//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that rent transaction
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find rent transaction
						JOptionPane.showMessageDialog(null, "Invalid ID - Could not find the Rent Transaction",
								"Invalid Rent Transaction Retreival", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						retreivedRHouse = client.getRentableHouseDetails();
						agentID = client.getIntFromServer();
						custID = client.getIntFromServer();
					
						//Here we set the text fields to the retrieved rent transaction attributes
						ApplicationMainWindow.txtTransUpdateHouseRentID.setText(Integer.toString(retreivedRHouse.getId()));
						ApplicationMainWindow.txtTransUpdateStartDate.setText(retreivedRHouse.getFromDate());
						ApplicationMainWindow.txtTransUpdateEndDate.setText(retreivedRHouse.getToDate());
						ApplicationMainWindow.txtTransUpdateMonthlyRate.setText(Double.toString(retreivedRHouse.getRate()));
						ApplicationMainWindow.txtUpdateEstateAgentID.setText(Double.toString(retreivedRHouse.getRate()));
						ApplicationMainWindow.txtUpdateEstateAgentID.setText(Integer.toString(agentID));
						ApplicationMainWindow.txtUpdateRentCustID.setText(Integer.toString(custID));
						ApplicationMainWindow.btnUpdateRentTrans.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtTransUpdateRentID.setEditable(false);//cannot edit text field holding the id
				
						//could find this rent transaction
						JOptionPane.showMessageDialog(null, "Valid ID - Found Rent Transaction",
								"Valid Rent Transaction Retrieval", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid - Search rent transaction id textbox must take a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATE A RENT TRANSACTION (CLIENT SIDE)
				//if action command is "UpdateRentTransaction"
			case "UpdateRentTransaction":
				RentableHouse updateRHouse = new RentableHouse(); //holds the entered rentable house data
				
				try
				{
					//Grab rent house data from text boxes
					id = Integer.parseInt(ApplicationMainWindow.txtTransUpdateRentID.getText());
					updateRHouse.setId(Integer.parseInt(ApplicationMainWindow.txtTransUpdateHouseRentID.getText()));
					updateRHouse.setFromDate(ApplicationMainWindow.txtTransUpdateStartDate.getText());
					updateRHouse.setToDate(ApplicationMainWindow.txtTransUpdateEndDate.getText());
					updateRHouse.setRate(Double.parseDouble(ApplicationMainWindow.txtTransUpdateMonthlyRate.getText()));
					agentID = Integer.parseInt(ApplicationMainWindow.txtUpdateEstateAgentID.getText());
					custID = Integer.parseInt(ApplicationMainWindow.txtUpdateRentCustID.getText());
				
					client.sendMessage(30);//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that rent transaction
					client.sendRentableHouseDetails(updateRHouse);
					client.sendMessage(agentID);
					client.sendMessage(custID);
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not update rent transaction
						JOptionPane.showMessageDialog(null, "Invalid - Could not update rent Transaction\n"+
														"You may have entered some improper data - please try again ",
							"Invalid Rent Transaction Update", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						//say could not update rent transaction
						JOptionPane.showMessageDialog(null, "Valid - Successful update of rent Transaction\n"+
														"This Rent transaction was updated ",
							"Invalid Rent Transaction Update", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid - House, Estate agent and Customer ID must be numbers" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SEARCH FOR A PARTICULAR BUY TRANSACTION (CLIENT SIDE)
				//if action command is "SearchUpdateBuyTransaction"
			case "SearchUpdateBuyTransaction":
				client.sendMessage(31);//send server a signal to tell it what we are doing
				SellableHouse sellRetreievedHouse = new SellableHouse(); //holds sellable house data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtTransUpdateBuyID.getText());	//get the id
					client.sendSearchID(id);		//send that id to the server to search for that buy member
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find rent transaction
						JOptionPane.showMessageDialog(null, "Invalid - Could not find the Buy Transaction",
								"Invalid Buy Transaction Retrieval", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						//get sellable house, estate agent and customer id data from server
						sellRetreievedHouse = client.getSellableHouseDetails();
						agentID = client.getIntFromServer();
						custID = client.getIntFromServer();
					
						//Here we set the text fields to the retrieved rent transaction attributes
						ApplicationMainWindow.txtTransUpdateBuyHouseID.setText(Integer.toString(sellRetreievedHouse.getId()));
						ApplicationMainWindow.txtUpdateBuyCost.setText(Double.toString(sellRetreievedHouse.getCost()));
						ApplicationMainWindow.txtUpdateBuyEstateAgentID.setText(Integer.toString(agentID));
						ApplicationMainWindow.txtUpdateBuyCustID.setText(Integer.toString(custID));
						ApplicationMainWindow.btnUpdateBuyTrans.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtTransUpdateBuyID.setEditable(false);//cannot edit text field holding the id
				
						//found this rent transaction
						JOptionPane.showMessageDialog(null, "Found Buy Transaction",
								"Valid Buy Transaction Retrieval", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Invalid - Search buy transaction id textbox must take a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATE A BUY TRANSACTION (CLIENT SIDE)
				//if action command is "UpdateBuyTransaction"
			case "UpdateBuyTransaction":
				SellableHouse updateSHouse = new SellableHouse(); //holds sellable house data
				
				try
				{
					//grab sellable house data from text boxes
					id = Integer.parseInt(ApplicationMainWindow.txtTransUpdateBuyID.getText());
					updateSHouse.setId(Integer.parseInt(ApplicationMainWindow.txtTransUpdateBuyHouseID.getText()));
					updateSHouse.setCost(Double.parseDouble(ApplicationMainWindow.txtUpdateBuyCost.getText()));
					agentID = Integer.parseInt(ApplicationMainWindow.txtUpdateBuyEstateAgentID.getText());
					custID = Integer.parseInt(ApplicationMainWindow.txtUpdateBuyCustID.getText());
				
					client.sendMessage(32);//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that buy transaction
					client.sendSellableHouseDetails(updateSHouse); //send data to server
					client.sendMessage(agentID);
					client.sendMessage(custID);
				
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not update rent transaction
						JOptionPane.showMessageDialog(null, "Invalid - Could not update buy Transaction\n"+
														"You may have entered some improper data - please try again ",
														"Invalid Buy Transaction Update", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						//say could not update rent transaction
						JOptionPane.showMessageDialog(null, "Valid - Successful update of buy Transaction\n"+
														"This Buy transaction was updated ",
														"Invalid Buy Transaction Update", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					JOptionPane.showMessageDialog(null, "Invalid - House, Estate agent and Customer ID must be numbers" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//DELETE A BUY TRANSACTION (CLIENT SIDE)
				//if action command is "DeleteSellTransaction"
			case "DeleteSellTransaction":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteSellTransaction.getText());//get id for delete
					client.sendMessage(33);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send the id
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry sell transaction at Id: "+id+" has been deleted" ,
								"Valid Sell Transaction Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Entry, this sell transaction does not exist" ,
								"Invalid Sell Transaction Deletion", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					JOptionPane.showMessageDialog(null, "Invalid Entry, sell transaction id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//DELETE A RENT TRANSACTION (CLIENT SIDE)
				//if action command is "DeleteRentTransaction"
			case "DeleteRentTransaction":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteRentTransaction.getText());//get id for delete
					client.sendMessage(34);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send the id
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry rent transaction at Id: "+id+" has been deleted" ,
								"Valid Rent Transaction Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Entry, This rent transaction does not exist" ,
								"Invalid Rent Transaction Deletion", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					JOptionPane.showMessageDialog(null, "Invalid Entry, rent transaction id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "ViewAllRentTransaction":
				//VIEWING ALL RENT HOUSE TRANSACTION(CLIENT SIDE)
				client.sendMessage(35);//send server a signal to tell it what we are doing
				Object[][] rentTransactions;	  //2D array to hold rent transactions data
				RentableHouse rHouseMember;   //object to hold each retrieved rentable house 
				
				rentIDs = client.getIDList();		//get id list of rent transactions from server
				rHouseList = client.getRentableHouseList();	//retrieve array list from server of all the houses
				estateAgentIDs = client.getIDList(); //get id list of estate agents from server
				custIDs = client.getIDList(); //get id list of customers from the server
				
				//if we have members in the array lists
				if(rHouseList.size() > 0 && rentIDs.size() > 0 && 
						estateAgentIDs.size() > 0 && custIDs.size() > 0)
				{
					rentTransactions = new Object[rHouseList.size()][7];//create array to hold them
					
					for(int row = 0; row < rHouseList.size(); ++row)//loop over each record
					{
						//add each record to the 2D array
						rentTransactions[row][0] = rentIDs.get(row);
						rHouseMember = rHouseList.get(row);
						rentTransactions[row][1] = rHouseMember.getId();
						rentTransactions[row][2] = rHouseMember.getFromDate();
						rentTransactions[row][3] = rHouseMember.getToDate();
						rentTransactions[row][4] = rHouseMember.getRate();
						rentTransactions[row][5] = estateAgentIDs.get(row);
						rentTransactions[row][6] = custIDs.get(row);
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will use house (object) 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(rentTransactions, rentTransactionColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowRentTransactions = new JTable(model);
				    ApplicationMainWindow.scr1Transactions = new JScrollPane(ApplicationMainWindow.tblShowRentTransactions);
				    ApplicationMainWindow.scr1Transactions.setLocation(50, 120);
				    ApplicationMainWindow.scr1Transactions.setSize(900, 370);
				    ApplicationMainWindow.scr1Transactions.setVisible(true);
				    ApplicationMainWindow.pnlViewTransaction.add(ApplicationMainWindow.scr1Transactions);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Rent Transactions Have been found" ,
							"Valid Rent Transaction Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Rent Transactions Have been found" ,
							"Invalid Rent Transactions Retreival", JOptionPane.ERROR_MESSAGE);
				}
			break;
			//SEARCH FOR A PARTICULAR RENT TRANSACTION (CLIENT SIDE)
			//if action command is "SearchRentTransaction"
			case "SearchRentTransaction":
				Object[][] retreivedRentTransaction = new Object[1][7]; //2D array to hold house data
				RentableHouse foundRentTrans;   //object to hold each retrieved rentable house 
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchViewRentTrans.getText());//get id entered
					client.sendMessage(36);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send it to server so it can perform a search
				
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "No Rent Transaction Was found" ,
								"Invalid Rent Transaction Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{	
						//add retrieved rent ransaction to 2D array row
						retreivedRentTransaction[0][0] = client.getIntFromServer();
						foundRentTrans = client.getRentableHouseDetails();
						retreivedRentTransaction[0][1] = foundRentTrans.getId();
						retreivedRentTransaction[0][2] = foundRentTrans.getFromDate();
						retreivedRentTransaction[0][3] = foundRentTrans.getToDate();
						retreivedRentTransaction[0][4] = foundRentTrans.getRate();
						retreivedRentTransaction[0][5] = client.getIntFromServer();
						retreivedRentTransaction[0][6] = client.getIntFromServer();
		
						//Create a table with no editing that outputs the data
						@SuppressWarnings("serial")
						DefaultTableModel model = new DefaultTableModel(retreivedRentTransaction, rentTransactionColumnNames) 
						{
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
				    
						//set the tables attributes
						ApplicationMainWindow.tblShowRentTransactions = new JTable(model);
						ApplicationMainWindow.scr1Transactions = new JScrollPane(ApplicationMainWindow.tblShowRentTransactions);
						ApplicationMainWindow.scr1Transactions.setLocation(50, 120);
						ApplicationMainWindow.scr1Transactions.setSize(900, 370);
						ApplicationMainWindow.scr1Transactions.setVisible(true);
						ApplicationMainWindow.pnlViewTransaction.add(ApplicationMainWindow.scr1Transactions);
				    
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Rent Transaction Has been found" ,
								"Valid House Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					JOptionPane.showMessageDialog(null, "Invalid Entry, rent transaction id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEWING ALL BUY HOUSE TRANSACTION(CLIENT SIDE)
				//if action command is "ViewAllBuyTransaction"
			case "ViewAllBuyTransaction":
				client.sendMessage(37);//send server a signal to tell it what we are doing
				Object[][] buyTransactions;	  //2D array to hold rent transactions data
				SellableHouse bHouseMember;   //object to hold each retrieved rentable house 
				
				buyIDs = client.getIDList();
				sHouseList = client.getSellableHouseList();	//retrieve array list from server of all the sell house transactions
				estateAgentIDs = client.getIDList();
				custIDs = client.getIDList();
				
				//if we have members in the array lists
				if(sHouseList.size() > 0 && buyIDs.size() > 0 && 
						estateAgentIDs.size() > 0 && custIDs.size() > 0)
				{
					buyTransactions = new Object[sHouseList.size()][5];//create array to hold them
					
					for(int row = 0; row < sHouseList.size(); ++row)//loop over each record
					{
						//add each recod to 2D array
						buyTransactions[row][0] = buyIDs.get(row);
						bHouseMember = sHouseList.get(row);
						buyTransactions[row][1] = bHouseMember.getId();
						buyTransactions[row][2] = bHouseMember.getCost();
						buyTransactions[row][3] = estateAgentIDs.get(row);
						buyTransactions[row][4] = custIDs.get(row);
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will use house (object) 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(buyTransactions,  buyTransactionColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowRentTransactions = new JTable(model);
				    ApplicationMainWindow.scr1Transactions = new JScrollPane(ApplicationMainWindow.tblShowRentTransactions);
				    ApplicationMainWindow.scr1Transactions.setLocation(50, 120);
				    ApplicationMainWindow.scr1Transactions.setSize(900, 370);
				    ApplicationMainWindow.scr1Transactions.setVisible(true);
				    ApplicationMainWindow.pnlViewTransaction.add(ApplicationMainWindow.scr1Transactions);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Buy Transactions Have been found" ,
							"Valid Buy Transaction Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Buy Transactions Have been found" ,
							"Invalid Buy Transactions Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SEARCH FOR A PARTICULAR BUY TRANSACTION (CLIENT SIDE)
				//if action command is "SearchBuyTransaction"
			case "SearchBuyTransaction":
				Object[][] retreivedBuyTransaction = new Object[1][5]; //2D array to hold house data
				SellableHouse foundBuyTrans;   //object to hold each retrieved sellable house 
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchViewBuyTrans.getText());//get id entered
					client.sendMessage(38);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send it to server so it can perform a search
				
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "No Buy Transaction Was found" ,
								"Invalid Rent Transaction Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						retreivedBuyTransaction[0][0] = client.getIntFromServer();
						foundBuyTrans = client.getSellableHouseDetails();
						retreivedBuyTransaction[0][1] = foundBuyTrans.getId();
						retreivedBuyTransaction[0][2] = foundBuyTrans.getCost();
						retreivedBuyTransaction[0][3] = client.getIntFromServer();
						retreivedBuyTransaction[0][4] = client.getIntFromServer();
		
						//Create a table with no editing that outputs the data
						@SuppressWarnings("serial")
						DefaultTableModel model = new DefaultTableModel(retreivedBuyTransaction, buyTransactionColumnNames) 
						{
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
				    
						//set the tables attributes
						ApplicationMainWindow.tblShowRentTransactions = new JTable(model);
						ApplicationMainWindow.scr1Transactions = new JScrollPane(ApplicationMainWindow.tblShowRentTransactions);
						ApplicationMainWindow.scr1Transactions.setLocation(50, 120);
						ApplicationMainWindow.scr1Transactions.setSize(900, 370);
						ApplicationMainWindow.scr1Transactions.setVisible(true);
						ApplicationMainWindow.pnlViewTransaction.add(ApplicationMainWindow.scr1Transactions);
				    
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Buy Transaction Has been found" ,
								"Valid Buy Transaction Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, buy transaction id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//CREATE A NEW CUSTOMER
				//if action command is "CreateNewCustomer"
			case "CreateNewCustomer":
				client.sendMessage(39);//send server a signal to tell it what we are doing
				Customer newCustomer = new Customer();//customer object to hold customer data
				
				//Grab customer data from text boxes
				newCustomer.setfName(ApplicationMainWindow.txtNewCustomerNameF.getText());
				newCustomer.setlName(ApplicationMainWindow.txtNewCustomerNameL.getText());
				newCustomer.setAddress(ApplicationMainWindow.txtNewCustomerAddress.getText());
		
				client.sendCustomerDetails(newCustomer);//send customer object with its data
				
				serverSignal = client.getServerSignal();	//get servers response
				
				if(serverSignal == 1)//success
				{
					JOptionPane.showMessageDialog(null, "New Customer Has been Added" ,
							"Valid", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(serverSignal == 0)//no success
				{
					JOptionPane.showMessageDialog(null, "              Incorrect Information\n"+ 
														"			Customer was not added\n"+
														"		Check all fields were entered\n"+
														"              Please Try Again",
							"Invalid", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//DELETING A CUSTOMER
				//if action command is "DeleteCustomer"
			case "DeleteCustomer":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteCustomer.getText());//get id for delete
					client.sendMessage(40);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send the id
				
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry customer at Id: "+id+" has been deleted" ,
								"Valid Customer Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Entry, Customer does not exist" ,
								"Invalid Customer Deletion", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, customer id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//FINDING A CUSTOMER (CLIENT SIDE)
				//action command is "FindCustomer
			case "FindCustomer":
				Customer foundCustomer = new Customer(); //customer object to hold found customer data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchCustomerEdit.getText());	//get the id
					client.sendMessage(41);//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that customer
			
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find rent transaction
						JOptionPane.showMessageDialog(null, "Invalid - Could not find the Customer",
								"Invalid Customer Retrieval", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						foundCustomer = client.getCustomerDetails();
				
						//Here we set the text fields to the retrieved customer attributes
						ApplicationMainWindow.txtCustomerUpdateFirstName.setText(foundCustomer.getfName());
						ApplicationMainWindow.txtCustomerUpdateLastName.setText(foundCustomer.getlName());
						ApplicationMainWindow.txtCustomerUpdateAddress.setText(foundCustomer.getAddress());
						ApplicationMainWindow.btnUpdateCustomer.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtSearchCustomerEdit.setEditable(false);//cannot edit text field holding the id
				
						//could find this rent transaction
						JOptionPane.showMessageDialog(null, "Found Customer",
								"Valid Customer Retrieval", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//invalid data format entered
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, customer id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATE A CUSTOMER (CLIENT SIDE)
				//if action command is "UpdateCustomer"
			case "UpdateCustomer":
				client.sendMessage(42);//send server a signal to tell it what we are doing
				
				Customer updateCustomer = new Customer(); //customer object holds update customer data
				
				//Grab update customer data from text boxes
				id = Integer.parseInt(ApplicationMainWindow.txtSearchCustomerEdit.getText());
				updateCustomer.setfName(ApplicationMainWindow.txtCustomerUpdateFirstName.getText());
				updateCustomer.setlName(ApplicationMainWindow.txtCustomerUpdateLastName.getText());
				updateCustomer.setAddress(ApplicationMainWindow.txtCustomerUpdateAddress.getText());
				
				client.sendSearchID(id);		//send that id to the server to search for that customer
				client.sendCustomerDetails(updateCustomer); //send customer object to the server
				
				serverSignal = client.getServerSignal(); //get response from the server
				
				if(serverSignal == 0)//if unsuccessful
				{
					//say could not update rent transaction
					JOptionPane.showMessageDialog(null, "Invalid - Could not update customer\n"+
														"You may have entered some improper data - please try again ",
							"Invalid Customer Update", JOptionPane.ERROR_MESSAGE);
				}
				else if(serverSignal == 1)//otherwise
				{
					//say could not update rent transaction
					JOptionPane.showMessageDialog(null, "Valid - Successful update of customer\n"+
														"This Customer was updated ",
							"Valid Customer Update", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//SHOW ALL CUSTOMERS (CLIENT SIDE)
				//if action command is "ShowAllCustomers"
			case "ShowAllCustomers":
				client.sendMessage(43);//send server a signal to tell it what we are doing
				Object[][] customers;	  //2D array to hold customers data
				Customer customer;   //object to hold each retrieved customer
				
				customersList = client.getCustomerList();//retrieve array list from server of all the customers
				
				if(customersList.size() > 0)//if we have customers in the array list
				{
					customers = new Object[customersList.size()][4];//create array to hold them
					
					for(int row = 0; row < customersList.size(); ++row)//loop over each record
					{
						customer = customersList.get(row); //get the object
						
						customers[row][0] = customer.getCustID();				//pass it into an array
						customers[row][1] = customer.getfName();
						customers[row][2] = customer.getlName();
						customers[row][3] = customer.getAddress();
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will used customer 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(customers, customerColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowCustomer = new JTable(model);
				    ApplicationMainWindow.scr1Customer = new JScrollPane(ApplicationMainWindow.tblShowCustomer);
				    ApplicationMainWindow.scr1Customer.setLocation(100, 70);
				    ApplicationMainWindow.scr1Customer.setSize(1000, 400);
				    ApplicationMainWindow.scr1Customer.setVisible(true);
				    ApplicationMainWindow.pnlCustomerShowArea.add(ApplicationMainWindow.scr1Customer);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Customers Have been found" ,
							"Valid Customer Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Customer Have been found" ,
							"Invalid Customer Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//SEARCH FOR A PARTICULAR CUSTOMER
				//if action command is "ViewSearchCustomer"
			case "ViewSearchCustomer":
				Object[][] retreivedCustomer = new Object[1][5]; //2D array to customer data
				Customer singleRetrievedCustomer;   //object to hold each retrieved customer
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchCustomer.getText());//get id entered
					client.sendMessage(44);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send it to server so it can perform a search
				
					serverSignal = client.getServerSignal();	//get servers response
				
					if(serverSignal == 0)//if unsuccessful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "No Buy Transaction Was found" ,
								"Invalid Rent Transaction Search", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						singleRetrievedCustomer = client.getCustomerDetails();//get customer from server
					
						//add to a 2D array
						retreivedCustomer[0][0] = singleRetrievedCustomer.getCustID();				//pass it into an array
						retreivedCustomer[0][1] = singleRetrievedCustomer.getfName();
						retreivedCustomer[0][2] = singleRetrievedCustomer.getlName();
						retreivedCustomer[0][3] = singleRetrievedCustomer.getAddress();
		
						//Create a table with no editing that outputs the data
						@SuppressWarnings("serial")
						DefaultTableModel model = new DefaultTableModel(retreivedCustomer, customerColumnNames) 
						{
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
				    
						//set the tables attributes
						ApplicationMainWindow.tblShowCustomer = new JTable(model);
						ApplicationMainWindow.scr1Customer = new JScrollPane(ApplicationMainWindow.tblShowCustomer);
						ApplicationMainWindow.scr1Customer.setLocation(100, 70);
						ApplicationMainWindow.scr1Customer.setSize(1000, 400);
						ApplicationMainWindow.scr1Customer.setVisible(true);
						ApplicationMainWindow.pnlCustomerShowArea.add(ApplicationMainWindow.scr1Customer);
				    
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Customer Has been found" ,
								"Valid Buy Transaction Search", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//invalid data format inputted
				{	
					JOptionPane.showMessageDialog(null, "Invalid Entry, customer id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//CREATE A NEW REMINDER (CLIENT SIDE)
				//if action command is "CreateNewReminder"
			case "CreateNewReminder":
				client.sendMessage(45);					//send server a signal to tell it what we are doing
				Reminder newReminder = new Reminder();  //create reminder object
				//set objects data from user input
				newReminder.setSubject(ApplicationMainWindow.txtCreateReminderSubject.getText());
				newReminder.setDesc(ApplicationMainWindow.txtCreateReminderDesc.getText());
				newReminder.setDate(ApplicationMainWindow.txtCreateReminderDate.getText());
				newReminder.setTime(ApplicationMainWindow.txtCreateReminderTime.getText());
				
				client.sendReminderDetails(newReminder);//send reminder object with data to the server
				
				serverSignal = client.getServerSignal();//get signal from server indicating success or not
				
				if(serverSignal == 0)//if unsuccessful
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, could not create reminder" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				else if(serverSignal == 1)//if success
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "New Reminder Has been Created" ,
							"Valid Reminder Creation", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//FINDING A REMINDER (CLIENT SIDE)
				//action command is "FindReminder
			case "FindReminder":
				Reminder foundReminder = new Reminder(); //customer object to hold found reminder data
				
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtSearchReminderEdit.getText());	//get the id
					client.sendMessage(46);			//send server a signal to tell it what we are doing
					client.sendSearchID(id);		//send that id to the server to search for that reminder
			
					serverSignal = client.getServerSignal(); //get response from the server
				
					if(serverSignal == 0)//if unsuccessful
					{
						//say could not find rent transaction
						JOptionPane.showMessageDialog(null, "Invalid - Could not find the Reminder",
								"Invalid Reminder Retrieval", JOptionPane.ERROR_MESSAGE);
					}
					else if(serverSignal == 1)//otherwise
					{
						foundReminder = client.getReminderDetails();
				
						//Here we set the text fields to the retrieved customer attributes
						ApplicationMainWindow.txtUpdateReminderDate.setText(foundReminder.getDate());
						ApplicationMainWindow.txtUpdateReminderTime.setText(foundReminder.getTime());
						ApplicationMainWindow.txtUpdateReminderSubject.setText(foundReminder.getSubject());
						ApplicationMainWindow.txtUpdateReminderDesc.setText(foundReminder.getDesc());
						ApplicationMainWindow.btnUpdateReminder.setEnabled(true);//now the update button is enabled
						ApplicationMainWindow.txtSearchReminderEdit.setEditable(false);//cannot edit text field holding the id
				
						//could find this rent transaction
						JOptionPane.showMessageDialog(null, "Found Reminder",
								"Valid Reminder Retrieval", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(NumberFormatException e)//invalid data format entered
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, reminder id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//UPDATE A REMINDER (CLIENT SIDE)
				//if action command is "UpdateReminder"
			case "UpdateReminder":
				client.sendMessage(47);//send server a signal to tell it what we are doing
				
				Reminder updateReminder = new Reminder(); //reminder object holds update reminder data
				
				//Grab update reminder data from text boxes
				id = Integer.parseInt(ApplicationMainWindow.txtSearchReminderEdit.getText());
				updateReminder.setDate(ApplicationMainWindow.txtUpdateReminderDate.getText());
				updateReminder.setTime(ApplicationMainWindow.txtUpdateReminderTime.getText());
				updateReminder.setSubject(ApplicationMainWindow.txtUpdateReminderSubject.getText());
				updateReminder.setDesc(ApplicationMainWindow.txtUpdateReminderDesc.getText());
				
				client.sendSearchID(id);		//send that id to the server to search for that reminder
				client.sendReminderDetails(updateReminder); //send reminder object to the server
				
				serverSignal = client.getServerSignal(); //get response from the server
				
				if(serverSignal == 0)//if unsuccessful
				{
					//say could not update rent transaction
					JOptionPane.showMessageDialog(null, "Invalid - Could not update Reminder\n"+
														"You may have entered some improper data - please try again ",
							"Invalid Customer Update", JOptionPane.ERROR_MESSAGE);
				}
				else if(serverSignal == 1)//otherwise
				{
					//say could not update rent transaction
					JOptionPane.showMessageDialog(null, "Valid - Successful update of Reminder\n"+
														"This Reminder was updated ",
							"Valid Customer Update", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				//DELETE A REMINDER (CLIENT SIDE)
				//if action command is "DeleteReminder"
			case "DeleteReminder":
				try
				{
					id = Integer.parseInt(ApplicationMainWindow.txtDeleteReminder.getText());//get id for delete
					client.sendMessage(48);//send server a signal to tell it what we are doing
					client.sendSearchID(id);					//send the id
				
					serverSignal = client.getServerSignal();	//get server response
				
					if(serverSignal == 1)//if successful
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Valid Entry reminder at Id: "+id+" has been deleted" ,
								"Valid Reminder Deletion", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(serverSignal == 0)//otherwise
					{
						//output relevant dialog box
						JOptionPane.showMessageDialog(null, "Invalid Entry, Reminder does not exist" ,
								"Invalid Reminder Deletion", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e)//if invalid data format
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "Invalid Entry, reminder id must be a number" ,
							"Invalid Input Data", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEW TODAYS REMINDERS (CLIENT SIDE)
				//if action command is "ShowTodaysReminder"
			case "ShowTodaysReminder":
				client.sendMessage(49);		//send server a signal to tell it what we are doing
				Object[][] reminders;	    //2D array to hold reminders data
				Reminder reminder;          //object to hold each retrieved customer
				
				//Get todays date
				DateFormat dateFormatToday = new SimpleDateFormat("yyyy-MM-dd");
				Date dateToday = new Date();
				
				client.sendMessage(dateFormatToday.format(dateToday));//send todays date to the server (string)
				remindersList = client.getReminderList();//retrieve array list from server of all the reminders
				
				if(remindersList.size() > 0)//if we have customers in the array list
				{
					reminders = new Object[remindersList.size()][5];//create array to hold them
					
					for(int row = 0; row < remindersList.size(); ++row)//loop over each record
					{
						reminder = remindersList.get(row); //get the object
						
						reminders[row][0] = reminder.getReminderID();				//pass it into an array
						reminders[row][1] = reminder.getSubject();
						reminders[row][2] = reminder.getDesc();
						reminders[row][3] = reminder.getDate();
						reminders[row][4] = reminder.getTime();
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will use reminders 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(reminders, reminderColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowReminder = new JTable(model);
				    ApplicationMainWindow.scr1Reminder = new JScrollPane(ApplicationMainWindow.tblShowReminder);
				    ApplicationMainWindow.scr1Reminder.setLocation(100, 70);
				    ApplicationMainWindow.scr1Reminder.setSize(1000, 400);
				    ApplicationMainWindow.scr1Reminder.setVisible(true);
				    ApplicationMainWindow.pnlReminderShowArea.add(ApplicationMainWindow.scr1Reminder);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Todays Reminders Have been found" ,
							"Valid Reminder Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Reminders For Today Have been found" ,
							"Invalid Reminder Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
				//VIEW REMINDERS FOR A CHOOSEN DATE (CLIENT SIDE)
				//if action command is "ViewSearchReminder"
			case "ViewSearchReminder":
				client.sendMessage(50);		//send server a signal to tell it what we are doing
				Object[][] searchReminders;	    //2D array to hold reminders data
				Reminder searchReminderMember;          //object to hold each retrieved customer
				
				String date = ApplicationMainWindow.txtSearchReminder.getText();
				
				client.sendMessage(date);//send todays date to the server (string)
				remindersList = client.getReminderList();//retrieve array list from server of all the reminders
				
				if(remindersList.size() > 0)//if we have customers in the array list
				{
					searchReminders = new Object[remindersList.size()][5];//create array to hold them
					
					for(int row = 0; row < remindersList.size(); ++row)//loop over each record
					{
						searchReminderMember = remindersList.get(row); //get the object
						
						searchReminders[row][0] = searchReminderMember.getReminderID();				//pass it into an array
						searchReminders[row][1] = searchReminderMember.getSubject();
						searchReminders[row][2] = searchReminderMember.getDesc();
						searchReminders[row][3] = searchReminderMember.getDate();
						searchReminders[row][4] = searchReminderMember.getTime();
					}
					
					@SuppressWarnings("serial")
					//Create a table model that does not allow editing, which will use reminders 2D array
					//as its data
					DefaultTableModel model = new DefaultTableModel(searchReminders, reminderColumnNames) 
				    {
				        public boolean isCellEditable(int rowIndex, int mColIndex) {
				          return false;
				        }
				    };
				    
				    //set the tables attributes
				    ApplicationMainWindow.tblShowReminder = new JTable(model);
				    ApplicationMainWindow.scr1Reminder = new JScrollPane(ApplicationMainWindow.tblShowReminder);
				    ApplicationMainWindow.scr1Reminder.setLocation(100, 70);
				    ApplicationMainWindow.scr1Reminder.setSize(1000, 400);
				    ApplicationMainWindow.scr1Reminder.setVisible(true);
				    ApplicationMainWindow.pnlReminderShowArea.add(ApplicationMainWindow.scr1Reminder);
				    
				  //output relevant dialog box
					JOptionPane.showMessageDialog(null, "Reminders For "+ date +" Have been found" ,
							"Valid Reminder Retreival", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					//output relevant dialog box
					JOptionPane.showMessageDialog(null, "No Reminders For "+ date +" Have been found" ,
							"Invalid Reminder Retreival", JOptionPane.ERROR_MESSAGE);
				}
				break;
		}//end switch
	}//end actionPerformed()
	
	//returns the current client object (used in ApplicationMainWindow exit() method)
	public Client getClient()
	{
		return client;
	}
	
	//Determines and returns the appropriate staff type based on combo box selected index (used above)
	public char getStaffTypeChar(int type)
	{
		char staffType = 0;
		
		switch(type)
		{
			case 0:
				staffType = 'a';
				break;
			case 1:
				staffType = 'm';
				break;
			case 2:
				staffType = 'e';
				break;
		}
		
		return staffType;
	}
	
	//Determines and returns the appropriate staff type based on combo box selected index (used above)
	//does the same as the method above except char to int
	public int getStaffTypeInt(char type)
	{
		int staffType = -1;
		
		switch(type)
		{
			case 'a':
				staffType = 0;
				break;
			case 'm':
				staffType = 1;
				break;
			case 'e':
				staffType = 2;
				break;
		}
		
		return staffType;
	}
	
	//Determines if a house is for sale (s) or for rent (r) depending on the index of the choosen combo box
	public char getHouseType(int type)
	{
		char houseType = 'x';
		
		switch(type)
		{
			case 1:
				houseType = 'b';
				break;
			case 2:
				houseType = 'r';
				break;
		}
		
		return houseType;
	}
	
	//Determines if a house is for sale 0 or for rent 1 depending on the type of character retrieved
	public int getHouseTypeInt(char type)
	{
		int houseType = -1;
			
		switch(type)
		{
			case 'b':
				houseType = 1;
				break;
			case 'r':
				houseType = 2;
				break;
		}
			
		return houseType;
	}
	
	public char getStaffType()
	{
		return staffTypeAuthenticator;
	}
}
