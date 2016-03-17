//Package
package ie.gmit.clientFunctional;

//Package imports
import ie.gmit.clientserver.Customer;
import ie.gmit.clientserver.House;
import ie.gmit.clientserver.Reminder;
import ie.gmit.clientserver.RentableHouse;
import ie.gmit.clientserver.SellableHouse;
import ie.gmit.clientserver.StaffMember;
import ie.gmit.clientserver.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/*Client class contains general methods used by the client*/
public class Client 
{
	//Instance variables
	public Socket clientSocket;			//Socket belonging to client
	private ObjectOutputStream out;		//Input streams and output streams for connection with server
	private ObjectInputStream in;	
	private String serverResponseMsg;	//string to hold response from the server
	private String createdStaffID;		//Holds new staff id returned from the server
	private String signal;				//Holds signals from the server
	private StaffMember staffMember;	//Holds a staff member
	private ArrayList<StaffMember> members;	//Holds list of staff members from server
	private ArrayList<Customer> customers;	//Holds list of customers from server
	private ArrayList<House> houses;		//Holds list of houses from the server
	private ArrayList<String> files;		//Holds list of file names from the server
	private long length;					//Holds length of file from server
	private String message;					//Holds message from the server
	private House house;					//Holds single house object from the server
	private RentableHouse rHouse;			//Holds single rent house from server
	private SellableHouse sHouse;			//Holds single sell house from server
	private ArrayList<RentableHouse> rHouseList;	//Holds list of rent houses from the server
	private ArrayList<SellableHouse> sHouseList;	//Holds list of rent houses from the server
	private ArrayList<Integer> idList;				//Holds list of ids from the server
	private Customer customer;						//Holds single customer from the server
	private Reminder reminder;						//Holds single reminder from the server
	private ArrayList<Reminder> reminderList;	//Holds list of reminders from the server
	
	//Constructor for client accepts an IP address, address of server 
	public Client(String ipAddress)
	{
		try 
		{
			clientSocket = new Socket(ipAddress, 2004);						//Create a socket connection for the client
			out = new ObjectOutputStream(clientSocket.getOutputStream());	//open an output stream for client
			out.flush();													//make sure output stream is clear
			in = new ObjectInputStream(clientSocket.getInputStream());		//open an input stream for the client
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}	
	}//end Client constructor
	
	//Sends user information to the server, accepts a user object
	public int sendUserDetails(User user)
 	{
		int answer = -1; //answer that will be returned from this method will be either 0 or 1
 		
		try
		{
			out.writeObject(user);							//Write/Send user to the server
			out.flush();									//Clear connection, make sure everything goes through
			serverResponseMsg = (String)in.readObject();	//Wait/Get response from server 0 or 1
			answer = Integer.parseInt(serverResponseMsg);	//Convert to an int
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
 		catch(ClassNotFoundException e)
 		{
 			e.printStackTrace();
 		}
 		
 		return answer; 						//Return the answer. 0 = unsuccessful and 1 = successful
 	}//end sendUserDetails()
	
	//Method used to update a user, sends both the old user and the new user information to replace it
	public int sendUpdateUserDetails(User oldUser, User updatedUser)
 	{
		int answer = -1;									//answer that will be returned from this method will be either 0 or 1
 		
		try
		{
			out.writeObject(oldUser);					    //Write/Send old user to the server
			out.flush();									//Clear connection, make sure everything goes through
			out.writeObject(updatedUser);				    //Write/Send new to the server
			out.flush();	
			serverResponseMsg = (String)in.readObject();	//Wait/Get response from server 0 or 1
			answer = Integer.parseInt(serverResponseMsg);	//Convert to an int
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
 		catch(ClassNotFoundException e)
 		{
 			e.printStackTrace();
 		}
 		
 		return answer; 						//Return the answer. 0 = unsuccessful and 1 = successful
 	}//end endUpdateUserDetails()
	
	//getServerStaffMessage()
	public String[] getServerStaffMessage()
	{
		String[] createStaffServerData = new String[2];
		try 
		{
			createdStaffID = (String)in.readObject(); 		//Retrieve the generated user id from server
			serverResponseMsg = (String)in.readObject();	//Wait/Get response from server 0 or 1
			createStaffServerData[0] = createdStaffID;
			createStaffServerData[1] = serverResponseMsg;
		} 
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return createStaffServerData; //send information about successful update and the new staff id
	}//end getServerStaffMessage()
	
	//getServerStaffMessage()
	public int getServerSignal()
	{
		int serverSignal = -1;
		
		try 
		{
			signal = (String)in.readObject();	//Wait/Get response from server 0 or 1
			serverSignal = Integer.parseInt(signal);
		} 
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return serverSignal; //send information about successful update and the new staff id
	}//end getServerSignal
	
	//Gets an array list of staff members from the server
	@SuppressWarnings("unchecked")
	public ArrayList<StaffMember> getStaffMemberList()
	{
		try 
 		{
			 members = (ArrayList<StaffMember>)in.readObject();	//Wait/Get response from server pass into Array list of staff members
		} 
 		catch(ClassNotFoundException e) 
 		{
			e.printStackTrace();
		}
 		catch(IOException e) 
 		{
			e.printStackTrace();
		}
 		
 		return members; //Return the staff member array list
	}
	
	//Gets an array list of customers from the server
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getCustomerList()
	{
		try 
	 	{
			customers = (ArrayList<Customer>)in.readObject();	//Wait/Get response from server pass into Array list of customers
		} 
	 	catch(ClassNotFoundException e) 
	 	{
			e.printStackTrace();
		}
	 	catch(IOException e) 
	 	{
			e.printStackTrace();
		}
	 		
	 		return customers; //Return the customer array list
	}//end getCustomerList
	
	//Sends staff information to the server, accepts a user object
	public void sendStaffDetails(StaffMember staff)
	{
	 	try
		{
			out.writeObject(staff);							//Write/Send staff member to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendStaffDetails()
	
	//Sends an id used to search records to the server, accepts an int
	public void sendSearchID(int id)
	{
		 try
		 {
			out.writeObject(id);							//Write/Send id to the server
			out.flush();									//Clear connection, make sure everything goes through
		 }
		 catch(IOException e)
		 {
			e.printStackTrace();
		 }
    }//end sendStaffDetails()
	
	//Get staff member object from the server
	public StaffMember getStaffMember()
	{
		try 
		{
			staffMember = (StaffMember)in.readObject();	//Wait/Get response from server 0 or 1
		} 
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return staffMember; //send information about successful update and the new staff id
	}//end getStaffMember()
	
	//Closes all connections with this client and the server
	public void closeConnections()
 	{
 		try 
 		{
			in.close();				//close input and output streams
			out.close();
			clientSocket.close();	//close the socket
		} 
 		catch(IOException e)
 		{
			e.printStackTrace();
		}
 	}//end closeConnections()
	
	//Used to send general messages to the server from the client. (signals to the server)
	public void sendMessage(Object message)
	{
		try 
	 	{
			out.writeObject(message); //Send the command we want to do to the server
			out.flush();			  //Clear output after we have sent everything, make sure output is clear.
		} 
	 	catch(IOException e)
	 	{
			e.printStackTrace();
		}		
	}//end sendMessage()
	 
	@SuppressWarnings("unchecked")
	//Gets a list of file names from the server
	public ArrayList<String> getFilesFromServer()
	{
	 	try 
	 	{
			files = (ArrayList<String>)in.readObject();	//Wait/Get files from server pass into Array list of strings
		} 
	 	catch(ClassNotFoundException e) 
	 	{
			e.printStackTrace();
		}
	 	catch(IOException e) 
	 	{
			e.printStackTrace();
		}
	 		
	 	return files; //Return the String array list
	}//end getFilesFromServer()
	
	//Retrieves the length of a file thats coming from the server
	 public long getFileLengthFromServer()
	 {
	 	try 
	 	{
	 		length = (Long)in.readObject();//Wait, then receive the parent directory from the server, cast to string
		} 
	 	catch(ClassNotFoundException e) 
	 	{
			e.printStackTrace();
		} 
	 	catch (IOException e) 
		{
			e.printStackTrace();
		}
	 		
	 	return length;	//return the name of the parent directory
	}//end getFileLengthFromServer()
	
	 //Retrieves a message (string from the server)
	public String getServerMessage()
	{
		try 
	 	{
	 		message = (String)in.readObject();//Wait, then receive the message string
		} 
	 	catch(ClassNotFoundException e) 
	 	{
			e.printStackTrace();
		} 
	 	catch (IOException e) 
	 	{
			e.printStackTrace();
		}
	 		
	 	return message;	//return the message
    }//end getServerMessage()
	
	//Retrieves a message (int from the server)
	public int getIntFromServer()
	{
		int iMessage = -1;
		
		try 
		{
		 	iMessage = (int)in.readObject();//Wait, then receive the message string
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		 		
	 	return iMessage;	//return the message
    }//end getServerMessage()
	
	//Sends house information to the server, accepts a house object
	public void sendHouseDetails(House house)
	{
		try
		{
			out.writeObject(house);							//Write/Send house object to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendHouseDetails()
	
	//Get house information from the server
	public House getHouseDetails()
	{
		try 
		{
			house = (House)in.readObject(); //Retrieve the house object
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		return house;					//return it
	}//end getHouseDetails()
	
	//Get customer information from the server
	public Customer getCustomerDetails()
	{
		try 
		{
			customer = (Customer)in.readObject(); //Retrieve the customer object
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
			
		return customer;					//return it
	}//end getCustomerDetails()
	
	//Gets an array list of houses from the server
	@SuppressWarnings("unchecked")
	public ArrayList<House> getHouseList()
	{
		try 
	 	{
			 houses = (ArrayList<House>)in.readObject();	//Wait/Get response from server pass into Array list of houses
		} 
	 	catch(ClassNotFoundException e) 
	 	{
			e.printStackTrace();
		}
	 	catch(IOException e) 
	 	{
			e.printStackTrace();
		}
	 		
	 	return houses; //Return the house array list
	}//end getHouseList()
	
	//Send rentable house object to the server
	public void sendRentableHouseDetails(RentableHouse rHouse)
	{
		try
		{
			out.writeObject(rHouse);							//Write/Send rentable house object to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendRentableHouseDetails()
	
	//send sellable house object to the server
	public void sendSellableHouseDetails(SellableHouse sHouse)
	{
		try
		{
			out.writeObject(sHouse);							//Write/Send sellable house object to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendSellableHouseDetails()
	
	//send customer object to the server
	public void sendCustomerDetails(Customer customer)
	{
		try
		{
			out.writeObject(customer);							//Write/Send sellable house object to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendCustomerDetails()
	
	//Get rentable house information from the server
	public RentableHouse getRentableHouseDetails()
	{
		try 
		{
			rHouse = (RentableHouse)in.readObject(); //Retrieve the rentable house object
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
			
		return rHouse;					//return it
	}//end getRentableHouseDetails()
	
	//Get sellable house information from the server
	public SellableHouse getSellableHouseDetails()
	{
		try 
		{
			sHouse = (SellableHouse)in.readObject(); //Retrieve the sellable house object
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
				
		return sHouse;		//return it
	}//end getSellableHouseDetails()
	
	//Gets an array list of rentable houses from the server
	@SuppressWarnings("unchecked")
	public ArrayList<RentableHouse> getRentableHouseList()
	{
		try 
		{
			rHouseList = (ArrayList<RentableHouse>)in.readObject();	//Wait/Get response from server pass into Array list of rentable houses
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		 		
		return rHouseList; //Return the rentable house array list
	}//end getRentableHouseList()
	
	//Gets an array list of sellable houses from the server
	@SuppressWarnings("unchecked")
	public ArrayList<SellableHouse> getSellableHouseList()
	{
		try 
		{
			sHouseList = (ArrayList<SellableHouse>)in.readObject();	//Wait/Get response from server pass into Array list of sellable houses
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
			 		
		return sHouseList; //Return the Sellable house array list
	}//end getSellableHouseList()
	
	//Gets an array list of id's from the server
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getIDList()
	{
		try 
		{
			idList = (ArrayList<Integer>)in.readObject();	//Wait/Get response from server pass into Array list of ints
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
			 		
		return idList; //Return the int array list
	}//end getIDList()
	
	//Sends staff information to the server, accepts a user object
	public void sendReminderDetails(Reminder reminder)
	{
		try
		{
			out.writeObject(reminder);							//Write/Send staff member to the server
			out.flush();									//Clear connection, make sure everything goes through
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}//end sendReminderDetails()
	
	//Get customer information from the server
	public Reminder getReminderDetails()
	{
		try 
		{
			reminder = (Reminder)in.readObject(); //Retrieve the reminder object
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
			
		return reminder;					//return it
	}//end getReminderDetails()
	
	//Gets an array list of reminder from the server
	@SuppressWarnings("unchecked")
	public ArrayList<Reminder> getReminderList()
	{
		try 
		{
			reminderList = (ArrayList<Reminder>)in.readObject();	//Wait/Get response from server pass into Array list of reminders
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		 		
		return reminderList; //Return the reminder array list
	}
}//end Client class
