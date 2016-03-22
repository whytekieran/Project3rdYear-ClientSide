package ie.gmit.clientEvents;

//Package imports
import ie.gmit.clientGUI.ApplicationMainWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

/*ListHandler class implements Item Listener , this class is responsible for determining the selected index
 of specific combo boxes and returning the selected index to be used by the client event handler*/
public class ListHandler implements ItemListener 
{
	//class instance variables
	private static int currentLoginIndex;			//selected index for login combo box
	private static int currentCreateUserTypeIndex;  //selected index for create user combo box
	private static int updateUserOldTypeIndex;		//selected index for update user old combo box
	private static int updateUserNewTypeIndex;      //selected index for update user new combo box
	private static int deleteUserTypeIndex;			//selected index for delete user combo box
	private static int createNewStaffIndex;			//selected index for create staff combo box
	private static int updateStaffIndex;			//selected index for update staff combo box
	private static int sendFileOptionIndex;			//selected index for send file combo box
	private static int createHouseOptionsIndex;		//selected index for create house combo box
	private static int updateHouseOptionsIndex;		//selected index for update house combo box
	private static int newTransactionOptionIndex;	//selected index for create transaction combo box
	private static int deleteTransactionOptionIndex;	//selected index for delete transaction combo box
	private static int updateTransactionOptionIndex;	//selected index for update transaction combo box
	private static int viewTransactionOptionIndex;		//selected index for view transaction combo box
	private static int dynamicSearchEntityIndex;		//Type of entity we are searching
	private static int staffSearchTypeIndex;			//Index selection of how we are searching for staff
	private static int houseSearchTypeIndex;			//Index selection of how we are searching for a house
	private static int custSearchTypeIndex; 			//Index selection of how we are searching for a customer
	private static int empTypeSearchTypeIndex; 			//Index selection of which emp type we are searching staff by
	private static int transSearchTypeIndex;			//Hold index of transaction b or r search type combo
	private static int transBuySearchTypeIndex;			//Hold index of transaction buy search type
	private static int transRentSearchTypeIndex;		//Hold index of transaction rent search type
	private int count = 0;
	
	//Triggered when index of a combo box selected item changes
	public void itemStateChanged(ItemEvent e) 
	{
		char c = ClientEventHandler.staffTypeAuthenticator;
		
		//If the combo box for login staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbStaffLoginOptions)
		{
			//set the selected index
			currentLoginIndex = ApplicationMainWindow.cmbStaffLoginOptions.getSelectedIndex();
		}
		
		//If the combo box for create new user staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbCreateUserOptions)
		{
			//set the selected index
			currentCreateUserTypeIndex = ApplicationMainWindow.cmbCreateUserOptions.getSelectedIndex();
		}
		
		//If the combo box for update old user staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbUpdateUserOldType)
		{
			//set the selected index
			updateUserOldTypeIndex = ApplicationMainWindow.cmbUpdateUserOldType.getSelectedIndex();
		}
		
		//If the combo box for update new user staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbUpdateUserNewType)
		{
			//set the selected index
			updateUserNewTypeIndex = ApplicationMainWindow.cmbUpdateUserNewType.getSelectedIndex();
		}
		
		//If the combo box for delete user staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbDeleteUserType)
		{
			//set the selected index
			deleteUserTypeIndex = ApplicationMainWindow.cmbDeleteUserType.getSelectedIndex();
		}
		
		//If the combo box for create staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbCreateStaffOptions)
		{
			//set the selected index
			createNewStaffIndex = ApplicationMainWindow.cmbCreateStaffOptions.getSelectedIndex();
		}
		
		//If the combo box for edit staff type changes
		if(e.getSource() == ApplicationMainWindow.cmbUpdateStaffOptions)
		{
			//set the selected index
			updateStaffIndex = ApplicationMainWindow.cmbUpdateStaffOptions.getSelectedIndex();
		}
		
		//If the combo box for send file option changes
		if(e.getSource() == ApplicationMainWindow.cmbSendFileOptions)
		{
			//set the selected index
			sendFileOptionIndex = ApplicationMainWindow.cmbSendFileOptions.getSelectedIndex();
			
			if(sendFileOptionIndex == 1)//send online file was chooses
			{
				//Set the relevant controls
				ApplicationMainWindow.lblUsernameSendFile.setVisible(false);
				ApplicationMainWindow.txtUsernameSendFile.setVisible(false);
				ApplicationMainWindow.lblCompPathSendFile.setVisible(false);
				ApplicationMainWindow.txtCompPathSendFile.setVisible(false);
				ApplicationMainWindow.scrlSendbleFiles.setVisible(true);
				ApplicationMainWindow.btnSendOnlineFile.setVisible(true);
				ApplicationMainWindow.lblUsernameSendFileO.setVisible(true);
				ApplicationMainWindow.txtUsernameSendFileO.setVisible(true);
				ApplicationMainWindow.lblOnlineNameSendFile.setVisible(true);
				ApplicationMainWindow.txtOnlineNameSendFile.setVisible(true);
				ApplicationMainWindow.btnViewSendableFiles.setVisible(true);
				ApplicationMainWindow.btnSendCompFile.setVisible(false);
			}
			else if(sendFileOptionIndex == 2)//send file from computer was chooses
			{
				//Set the relevant controls
				ApplicationMainWindow.lblUsernameSendFile.setVisible(true);
				ApplicationMainWindow.txtUsernameSendFile.setVisible(true);
				ApplicationMainWindow.lblCompPathSendFile.setVisible(true);
				ApplicationMainWindow.txtCompPathSendFile.setVisible(true);
				ApplicationMainWindow.btnSendCompFile.setVisible(true);
				ApplicationMainWindow.scrlSendbleFiles.setVisible(false);
				ApplicationMainWindow.lblUsernameSendFileO.setVisible(false);
				ApplicationMainWindow.txtUsernameSendFileO.setVisible(false);
				ApplicationMainWindow.lblOnlineNameSendFile.setVisible(false);
				ApplicationMainWindow.txtOnlineNameSendFile.setVisible(false);
				ApplicationMainWindow.btnViewSendableFiles.setVisible(false);
				ApplicationMainWindow.btnSendOnlineFile.setVisible(false);
			}
		}
		
		//If the combo box for add house type option changes
		if(e.getSource() == ApplicationMainWindow.cmbCreateHouseOptions)
		{
			//set the selected index
			createHouseOptionsIndex = ApplicationMainWindow.cmbCreateHouseOptions.getSelectedIndex();
		}
		
		//If the combo box for update house type option changes
		if(e.getSource() == ApplicationMainWindow.cmbUpdateHouseOptions)
		{
			//set the selected index
			updateHouseOptionsIndex = ApplicationMainWindow.cmbUpdateHouseOptions.getSelectedIndex();
		}
		
		//If the combo box for create rent or buy transaction type option changes
		if(e.getSource() == ApplicationMainWindow.cmbTransactionOptions)
		{
			//set the selected index
			newTransactionOptionIndex = ApplicationMainWindow.cmbTransactionOptions.getSelectedIndex();
			
			if(newTransactionOptionIndex == 1)//buy
			{
				//rent options invisible
				ApplicationMainWindow.lblTransRentHouseID.setVisible(false);
				ApplicationMainWindow.txtTransRentHouseID.setVisible(false);
				ApplicationMainWindow.lblTransStartDate.setVisible(false);
				ApplicationMainWindow.txtTransStartDate.setVisible(false);
				ApplicationMainWindow.lblTransEndDate.setVisible(false);
				ApplicationMainWindow.txtTransEndDate.setVisible(false);
				ApplicationMainWindow.lblTransMonthlyRate.setVisible(false);
				ApplicationMainWindow.txtTransMonthlyRate.setVisible(false);
				ApplicationMainWindow.lblRentEstateAgentID.setVisible(false);
				ApplicationMainWindow.txtEstateAgentID.setVisible(false);
				ApplicationMainWindow.btnCreateRentTrans.setVisible(false);
				ApplicationMainWindow.lblRentCustID.setVisible(false);
				ApplicationMainWindow.txtRentCustID.setVisible(false);
				//buy options visible
				ApplicationMainWindow.lblTransBuyHouseID.setVisible(true);
				ApplicationMainWindow.txtTransBuyHouseID.setVisible(true);
				ApplicationMainWindow.lblBuyCost.setVisible(true);
				ApplicationMainWindow.txtBuyCost.setVisible(true);
				ApplicationMainWindow.lblBuyEstateAgentID.setVisible(true);
				ApplicationMainWindow.txtBuyEstateAgentID.setVisible(true);
				ApplicationMainWindow.btnCreateBuyTrans.setVisible(true);
				ApplicationMainWindow.lblBuyCustID.setVisible(true);
				ApplicationMainWindow.txtBuyCustID .setVisible(true);
			}
			else if(newTransactionOptionIndex == 2)//rent
			{
				//buy options invisible
				ApplicationMainWindow.lblTransBuyHouseID.setVisible(false);
				ApplicationMainWindow.txtTransBuyHouseID.setVisible(false);
				ApplicationMainWindow.lblBuyCost.setVisible(false);
				ApplicationMainWindow.txtBuyCost.setVisible(false);
				ApplicationMainWindow.lblBuyEstateAgentID.setVisible(false);
				ApplicationMainWindow.txtBuyEstateAgentID.setVisible(false);
				ApplicationMainWindow.btnCreateBuyTrans.setVisible(false);
				ApplicationMainWindow.lblBuyCustID.setVisible(false);
				ApplicationMainWindow.txtBuyCustID .setVisible(false);
				//rent options visible
				ApplicationMainWindow.lblTransRentHouseID.setVisible(true);
				ApplicationMainWindow.txtTransRentHouseID.setVisible(true);
				ApplicationMainWindow.lblTransStartDate.setVisible(true);
				ApplicationMainWindow.txtTransStartDate.setVisible(true);
				ApplicationMainWindow.lblTransEndDate.setVisible(true);
				ApplicationMainWindow.txtTransEndDate.setVisible(true);
				ApplicationMainWindow.lblTransMonthlyRate.setVisible(true);
				ApplicationMainWindow.txtTransMonthlyRate.setVisible(true);
				ApplicationMainWindow.lblRentEstateAgentID.setVisible(true);
				ApplicationMainWindow.txtEstateAgentID.setVisible(true);
				ApplicationMainWindow.btnCreateRentTrans.setVisible(true);
				ApplicationMainWindow.lblRentCustID.setVisible(true);
				ApplicationMainWindow.txtRentCustID.setVisible(true);
			}
		}
		
		//If the combo box for delete rent or buy transaction type option changes
		if(e.getSource() == ApplicationMainWindow.cmbDeleteTransactionOptions)
		{
			//set the selected index
			deleteTransactionOptionIndex = ApplicationMainWindow.cmbDeleteTransactionOptions.getSelectedIndex();
			
			if(deleteTransactionOptionIndex == 1)//buy 
			{
				//set delete buy controls
				ApplicationMainWindow.lblDeleteSellTransaction.setVisible(true);
				ApplicationMainWindow.btnDeleteSellTransaction.setVisible(true);
				ApplicationMainWindow.txtDeleteSellTransaction.setVisible(true);
				ApplicationMainWindow.lblDeleteRentTransaction.setVisible(false);
				ApplicationMainWindow.btnDeleteRentTransaction.setVisible(false);
				ApplicationMainWindow.txtDeleteRentTransaction.setVisible(false);
			}
			else if(deleteTransactionOptionIndex == 2)//rent
			{
				//set delete rent controls
				ApplicationMainWindow.lblDeleteSellTransaction.setVisible(false);
				ApplicationMainWindow.btnDeleteSellTransaction.setVisible(false);
				ApplicationMainWindow.txtDeleteSellTransaction.setVisible(false);
				ApplicationMainWindow.lblDeleteRentTransaction.setVisible(true);
				ApplicationMainWindow.btnDeleteRentTransaction.setVisible(true);
				ApplicationMainWindow.txtDeleteRentTransaction.setVisible(true);
			}
		}
		
		//If the combo box for update rent or buy transaction type option changes
		if(e.getSource() == ApplicationMainWindow.cmbTransactionUpdateOptions)
		{
			//set selected index
			updateTransactionOptionIndex = ApplicationMainWindow.cmbTransactionUpdateOptions.getSelectedIndex();
			
			if(updateTransactionOptionIndex == 1)//buy update
			{
				//buy update controls are visible
				ApplicationMainWindow.lblTransUpdateBuyID.setVisible(true);
				ApplicationMainWindow.txtTransUpdateBuyID.setVisible(true);
				ApplicationMainWindow.btnSearchUpdateBuyTrans.setVisible(true);
				ApplicationMainWindow.lblUpdateBuyCost.setVisible(true);
				ApplicationMainWindow.txtUpdateBuyCost.setVisible(true);
				ApplicationMainWindow.lblUpdateBuyEstateAgentID.setVisible(true);
				ApplicationMainWindow.txtUpdateBuyEstateAgentID.setVisible(true);
				ApplicationMainWindow.btnUpdateBuyTrans.setVisible(true);
				ApplicationMainWindow.lblUpdateBuyCustID.setVisible(true);
				ApplicationMainWindow.txtUpdateBuyCustID.setVisible(true);
				ApplicationMainWindow.lblTransUpdateBuyHouseID.setVisible(true);
				ApplicationMainWindow.txtTransUpdateBuyHouseID.setVisible(true);
				ApplicationMainWindow.btnRefreshUpdateBuyTrans.setVisible(true);
				//rent update controls are not visible
				ApplicationMainWindow.lblTransUpdateRentID.setVisible(false);
				ApplicationMainWindow.txtTransUpdateRentID.setVisible(false);
				ApplicationMainWindow.btnSearchUpdateRentTrans.setVisible(false);
				ApplicationMainWindow.lblTransUpdateStartDate.setVisible(false);
				ApplicationMainWindow.txtTransUpdateStartDate.setVisible(false);
				ApplicationMainWindow.lblTransUpdateEndDate.setVisible(false);
				ApplicationMainWindow.txtTransUpdateEndDate.setVisible(false);
				ApplicationMainWindow.lblTransUpdateMonthlyRate.setVisible(false);
				ApplicationMainWindow.txtTransUpdateMonthlyRate.setVisible(false);
				ApplicationMainWindow.lblRentUpdateEstateAgentID.setVisible(false);
				ApplicationMainWindow.txtUpdateEstateAgentID.setVisible(false);
				ApplicationMainWindow.btnUpdateRentTrans.setVisible(false);
				ApplicationMainWindow.lblUpdateRentCustID.setVisible(false);
				ApplicationMainWindow.txtUpdateRentCustID.setVisible(false);
				ApplicationMainWindow.lblTransUpdateHouseRentID.setVisible(false);
				ApplicationMainWindow.txtTransUpdateHouseRentID.setVisible(false);
				ApplicationMainWindow.btnRefreshUpdateRentTrans.setVisible(false);
			}
			else if(updateTransactionOptionIndex == 2)//rent update
			{
				//rent update controls are visible
				ApplicationMainWindow.lblTransUpdateRentID.setVisible(true);
				ApplicationMainWindow.txtTransUpdateRentID.setVisible(true);
				ApplicationMainWindow.btnSearchUpdateRentTrans.setVisible(true);
				ApplicationMainWindow.lblTransUpdateStartDate.setVisible(true);
				ApplicationMainWindow.txtTransUpdateStartDate.setVisible(true);
				ApplicationMainWindow.lblTransUpdateEndDate.setVisible(true);
				ApplicationMainWindow.txtTransUpdateEndDate.setVisible(true);
				ApplicationMainWindow.lblTransUpdateMonthlyRate.setVisible(true);
				ApplicationMainWindow.txtTransUpdateMonthlyRate.setVisible(true);
				ApplicationMainWindow.lblRentUpdateEstateAgentID.setVisible(true);
				ApplicationMainWindow.txtUpdateEstateAgentID.setVisible(true);
				ApplicationMainWindow.btnUpdateRentTrans.setVisible(true);
				ApplicationMainWindow.lblUpdateRentCustID.setVisible(true);
				ApplicationMainWindow.txtUpdateRentCustID.setVisible(true);
				ApplicationMainWindow.lblTransUpdateHouseRentID.setVisible(true);
				ApplicationMainWindow.txtTransUpdateHouseRentID.setVisible(true);
				ApplicationMainWindow.btnRefreshUpdateRentTrans.setVisible(true);
				//buy update controls are not visible
				ApplicationMainWindow.lblTransUpdateBuyID.setVisible(false);
				ApplicationMainWindow.txtTransUpdateBuyID.setVisible(false);
				ApplicationMainWindow.btnSearchUpdateBuyTrans.setVisible(false);
				ApplicationMainWindow.lblUpdateBuyCost.setVisible(false);
				ApplicationMainWindow.txtUpdateBuyCost.setVisible(false);
				ApplicationMainWindow.lblUpdateBuyEstateAgentID.setVisible(false);
				ApplicationMainWindow.txtUpdateBuyEstateAgentID.setVisible(false);
				ApplicationMainWindow.btnUpdateBuyTrans.setVisible(false);
				ApplicationMainWindow.lblUpdateBuyCustID.setVisible(false);
				ApplicationMainWindow.txtUpdateBuyCustID.setVisible(false);
				ApplicationMainWindow.lblTransUpdateBuyHouseID.setVisible(false);
				ApplicationMainWindow.txtTransUpdateBuyHouseID.setVisible(false);
				ApplicationMainWindow.btnRefreshUpdateBuyTrans.setVisible(false);
			}
		}
		
		//If the combo box for view rent or buy transaction type option changes
		if(e.getSource() == ApplicationMainWindow.cmbTransactionViewOptions)
		{
			//set selected index
			viewTransactionOptionIndex = ApplicationMainWindow.cmbTransactionViewOptions.getSelectedIndex();
			
			if(viewTransactionOptionIndex == 1)//buy update
			{
				//show view buy transaction controls
				ApplicationMainWindow.btnViewAllBuyTrans.setVisible(true);
				ApplicationMainWindow.lblViewTransactionBuy.setVisible(true);
				ApplicationMainWindow.txtSearchViewBuyTrans.setVisible(true);
				ApplicationMainWindow.btnSearchBuyTrans.setVisible(true); 
				//don't show view rent transaction controls
				ApplicationMainWindow.btnViewAllRentTrans.setVisible(false);
				ApplicationMainWindow.lblViewTransactionRent.setVisible(false);
				ApplicationMainWindow.txtSearchViewRentTrans.setVisible(false);
				ApplicationMainWindow.btnSearchRentTrans.setVisible(false); 
				ApplicationMainWindow.tblShowTransactions.setVisible(false);
			}
			else if(viewTransactionOptionIndex == 2)//rent update
			{
				//show view rent transaction controls
				ApplicationMainWindow.btnViewAllRentTrans.setVisible(true);
				ApplicationMainWindow.lblViewTransactionRent.setVisible(true);
				ApplicationMainWindow.txtSearchViewRentTrans.setVisible(true);
				ApplicationMainWindow.btnSearchRentTrans.setVisible(true); 
				//dont show view buy transaction controls
				ApplicationMainWindow.btnViewAllBuyTrans.setVisible(false);
				ApplicationMainWindow.lblViewTransactionBuy.setVisible(false);
				ApplicationMainWindow.txtSearchViewBuyTrans.setVisible(false);
				ApplicationMainWindow.btnSearchBuyTrans.setVisible(false); 
				ApplicationMainWindow.tblShowTransactions.setVisible(false);
			}
		}
		
		//This is the main combo box for the dynamic search panel allowing a user to choose which type
		//of entity they wish to search.
		if(e.getSource() == ApplicationMainWindow.cmbDynamicSearchEntities)
		{
			//set selected index
			dynamicSearchEntityIndex = ApplicationMainWindow.cmbDynamicSearchEntities.getSelectedIndex();
			
			if(dynamicSearchEntityIndex == 0)//nothing selected
			{
				//nothing visible
				ApplicationMainWindow.lblSearchHouseType.setVisible(false);
				ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchCustomerType.setVisible(false);
				ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.cmbDynamicStaffSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				ApplicationMainWindow.lblSearchTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(dynamicSearchEntityIndex == 1)//staff search
			{
				//The item state changed event is actually fired twice in the background, hence the JOptionPane
				//below would be shown twice to the user. To combat this we can increment a counter and use % 2
				//on it. Then only print out the pane when we get a 1. (Half the time, Half of 2 is 1)
				//So the pane is only shown once, which is what we want.
				int printOrNot = count % 2;
				//If user is not a manager they should not be able to create users
				if(c != 'm')
				{	
					if(printOrNot == 1)
					{
					//Dialog box saying unsuccessful because your not a manager
					JOptionPane.showMessageDialog(null, "You are not a manager, you must be a manager to use dynamic staff search",
							"Invalid User", JOptionPane.ERROR_MESSAGE);
					}
					
					ApplicationMainWindow.lblSearchHouseType.setVisible(false);
					ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchCustomerType.setVisible(false);
					ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
					ApplicationMainWindow.txtStaffFirstName.setVisible(false);
					ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
					ApplicationMainWindow.txtStaffLastName.setVisible(false);
					ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
					ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
					ApplicationMainWindow.txtHouseTown.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
					ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
					ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
					ApplicationMainWindow.txtHouseCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
					ApplicationMainWindow.lblSearchCustFName.setVisible(false);
					ApplicationMainWindow.txtCustFName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
					ApplicationMainWindow.lblSearchCustLName.setVisible(false);
					ApplicationMainWindow.txtCustLName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
					ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
					ApplicationMainWindow.txtCustAddress.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
					ApplicationMainWindow.lblSearchTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
					ApplicationMainWindow.txtTransBuyCost.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
					ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
					ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
					ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
					ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
					ApplicationMainWindow.txtTransRentCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
					ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
					ApplicationMainWindow.txtTransRentRate.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
					ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
					ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				}
				else
				{
					ApplicationMainWindow.lblSearchHouseType.setVisible(false);
					ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchCustomerType.setVisible(false);
					ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
					ApplicationMainWindow.txtStaffFirstName.setVisible(false);
					ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
					ApplicationMainWindow.txtStaffLastName.setVisible(false);
					ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
					ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
					ApplicationMainWindow.txtHouseTown.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
					ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
					ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
					ApplicationMainWindow.txtHouseCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
					ApplicationMainWindow.lblSearchCustFName.setVisible(false);
					ApplicationMainWindow.txtCustFName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
					ApplicationMainWindow.lblSearchCustLName.setVisible(false);
					ApplicationMainWindow.txtCustLName.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
					ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
					ApplicationMainWindow.txtCustAddress.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
					ApplicationMainWindow.lblSearchTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
					ApplicationMainWindow.txtTransBuyCost.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
					ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
					ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
					ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
					ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
					ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
					ApplicationMainWindow.txtTransRentCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
					ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
					ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
					ApplicationMainWindow.txtTransRentRate.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
					ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
					ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
					ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
					ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
					//Make staff controls visible, user can use these controls to decide how he/she wishes
					//to search for staff
					ApplicationMainWindow.cmbDynamicStaffSearchType.setVisible(true);
					ApplicationMainWindow.cmbDynamicStaffSearchType.setSelectedIndex(0);
					ApplicationMainWindow.lblSearchStaffType.setVisible(true);
					ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
				}
			}
			else if(dynamicSearchEntityIndex == 2)//house search
			{
				//Make all search controls except house invisible
				ApplicationMainWindow.cmbDynamicStaffSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffType.setVisible(false);
				ApplicationMainWindow.lblSearchCustomerType.setVisible(false);
				ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				ApplicationMainWindow.lblSearchTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//Make house controls visible, user can use these controls to decide how he/she wishes
				//to search for house
				ApplicationMainWindow.lblSearchHouseType.setVisible(true);
				ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(true);
				ApplicationMainWindow.cmbDynamicHouseSearchType.setSelectedIndex(0);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(dynamicSearchEntityIndex == 3)//customer search
			{
				//make all controls except cutomer invisible
				ApplicationMainWindow.lblSearchHouseType.setVisible(false);
				ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(false);
				ApplicationMainWindow.cmbDynamicStaffSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				ApplicationMainWindow.lblSearchTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//Make customer controls visible, user can use these controls to decide how he/she wishes
				//to search for customer
				ApplicationMainWindow.lblSearchCustomerType.setVisible(true);
				ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(true);
				ApplicationMainWindow.cmbDynamicCustomerSearchType.setSelectedIndex(0);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(dynamicSearchEntityIndex == 4)//transaction search
			{
				//make all controls except transaction invisible
				ApplicationMainWindow.lblSearchHouseType.setVisible(false);
				ApplicationMainWindow.cmbDynamicHouseSearchType.setVisible(false);
				ApplicationMainWindow.cmbDynamicStaffSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffType.setVisible(false);
				ApplicationMainWindow.lblSearchCustomerType.setVisible(false);
				ApplicationMainWindow.cmbDynamicCustomerSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//Make transaction controls visible, user can use these controls to decide how he/she wishes
				//to search for transaction
				ApplicationMainWindow.lblSearchTransactionType.setVisible(true);
				ApplicationMainWindow.cmbDynamicTransactionSearchType.setVisible(true);
				ApplicationMainWindow.cmbDynamicTransactionSearchType.setSelectedIndex(0);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			
			++count;
		}
		
		//combo box to decide which type of staff search we are doing
		//Becomes visible when user decides to search by staff entity in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicStaffSearchType)
		{
			staffSearchTypeIndex = ApplicationMainWindow.cmbDynamicStaffSearchType.getSelectedIndex();
			
			if(staffSearchTypeIndex == 0)//nothing selected
			{
				//all controls invisible
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(staffSearchTypeIndex == 1)//first name
			{
				//all controls invisible except first name controls
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				//first name controls visible
				ApplicationMainWindow.lblSearchStaffFName.setVisible(true);
				ApplicationMainWindow.txtStaffFirstName.setVisible(true);
				ApplicationMainWindow.txtStaffFirstName.setText("");
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(staffSearchTypeIndex == 2)//last name
			{
				//all controls invisible except last name controls
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(false);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(false);
				//last name controls visible
				ApplicationMainWindow.lblSearchStaffLName.setVisible(true);
				ApplicationMainWindow.txtStaffLastName.setVisible(true);
				ApplicationMainWindow.txtStaffLastName.setText("");
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(staffSearchTypeIndex == 3)//emp type
			{
				//all controls invisible except employment type controls
				ApplicationMainWindow.lblSearchStaffLName.setVisible(false);
				ApplicationMainWindow.txtStaffLastName.setVisible(false);
				ApplicationMainWindow.lblSearchStaffFName.setVisible(false);
				ApplicationMainWindow.txtStaffFirstName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchStaffLName.setVisible(false);
				//employment type controls are visible
				ApplicationMainWindow.lblSearchStaffEmp.setVisible(true);
				ApplicationMainWindow.cmbStaffSearchEmpType.setVisible(true);
				ApplicationMainWindow.cmbStaffSearchEmpType.setSelectedIndex(0);
				ApplicationMainWindow.btnDynamicSearchStaffEType.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
		
		//sets which employment type we are searching for a staff member under in dynamic search staff area
		if(e.getSource() == ApplicationMainWindow.cmbStaffSearchEmpType)
		{
			empTypeSearchTypeIndex = ApplicationMainWindow.cmbStaffSearchEmpType.getSelectedIndex();
		}
		
		//Combo box to decide which type of house search we are doing
		//Becomes visible when user decides to search by house entity in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicHouseSearchType)
		{
			houseSearchTypeIndex = ApplicationMainWindow.cmbDynamicHouseSearchType.getSelectedIndex();
			
			if(houseSearchTypeIndex == 0)//no selection
			{
				//all controls invisible
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(houseSearchTypeIndex == 1)//rentable houses
			{
				//all controls except rent house search controls are invisible
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				//all controls for rent house search visible 
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(houseSearchTypeIndex == 2)//buyable houses
			{
				//all controls except buy house invisible
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				//all buyable house search contols are visible
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(houseSearchTypeIndex == 3)//town of house
			{
				//all controls except house town search invisble
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(false);
				ApplicationMainWindow.txtHouseCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(false);
				//set house controls so they are true
				ApplicationMainWindow.lblSearchHouseTown.setVisible(true);
				ApplicationMainWindow.txtHouseTown.setVisible(true);
				ApplicationMainWindow.txtHouseTown.setText("");
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(houseSearchTypeIndex == 4)//county of house
			{
				//all controls except house county search invisble
				ApplicationMainWindow.btnDynamicSearchHouseRent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseBuy.setVisible(false);
				ApplicationMainWindow.lblSearchHouseTown.setVisible(false);
				ApplicationMainWindow.txtHouseTown.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchHouseTown.setVisible(false);
				//show county search controls
				ApplicationMainWindow.lblSearchHouseCounty.setVisible(true);
				ApplicationMainWindow.txtHouseCounty.setVisible(true);
				ApplicationMainWindow.txtHouseCounty.setText("");
				ApplicationMainWindow.btnDynamicSearchHouseCounty.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
		
		//combo box to decide which type of customer search we are doing
		//Becomes visible when user decides to search by customer entity in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicCustomerSearchType)
		{
			custSearchTypeIndex = ApplicationMainWindow.cmbDynamicCustomerSearchType.getSelectedIndex();
			
			if(custSearchTypeIndex == 0)//no selection
			{
				//all controls invisible
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(custSearchTypeIndex == 1)//first name
			{
				//all controls except first name search controls are invisible
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				//first name search controls are visible
				ApplicationMainWindow.lblSearchCustFName.setVisible(true);
				ApplicationMainWindow.txtCustFName.setVisible(true);
				ApplicationMainWindow.txtCustFName.setText("");
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(custSearchTypeIndex == 2)//last name
			{
				//all controls invisible except search by last name controls
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustAddress.setVisible(false);
				ApplicationMainWindow.txtCustAddress.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(false);
				//last name search controls are visible
				ApplicationMainWindow.lblSearchCustLName.setVisible(true);
				ApplicationMainWindow.txtCustLName.setVisible(true);
				ApplicationMainWindow.txtCustLName.setText("");
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(custSearchTypeIndex == 3)//address
			{
				//all controls invisible except search by address controls
				ApplicationMainWindow.lblSearchCustFName.setVisible(false);
				ApplicationMainWindow.txtCustFName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustFName.setVisible(false);
				ApplicationMainWindow.lblSearchCustLName.setVisible(false);
				ApplicationMainWindow.txtCustLName.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchCustLName.setVisible(false);
				//address search controls are visible
				ApplicationMainWindow.lblSearchCustAddress.setVisible(true);
				ApplicationMainWindow.txtCustAddress.setVisible(true);
				ApplicationMainWindow.txtCustAddress.setText("");
				ApplicationMainWindow.btnDynamicSearchCustAddress.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
		
		//combo box to decide which type of transaction search we are doing buy(sell) or rent transaction
		//Becomes visible when user decides to search by transation entity in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicTransactionSearchType)
		{
			transSearchTypeIndex = ApplicationMainWindow.cmbDynamicTransactionSearchType.getSelectedIndex();
					
			if(transSearchTypeIndex == 0)//no selection
			{
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transSearchTypeIndex == 1)//buy search
			{
				//rent combo box invisible and input cotrols (rent) invisible
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(false);
				//buy combo box visible
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(true);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(true);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setSelectedIndex(0);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transSearchTypeIndex == 2)//rent search
			{
				//buy combo box invisible and input cotrols (buy) invisible
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.lblSearchBuyTransactionType.setVisible(false);
				ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.setVisible(false);
				//rent combo box visible
				ApplicationMainWindow.lblSearchRentTransactionType.setVisible(true);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setVisible(true);
				ApplicationMainWindow.cmbDynamicRentTransactionSearchType.setSelectedIndex(0);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
		
		//combo box used to search rent transactions by certain attributes
		//Becomes visible when user decides to search by rent transation in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicRentTransactionSearchType)
		{
			transRentSearchTypeIndex = ApplicationMainWindow.cmbDynamicRentTransactionSearchType.getSelectedIndex();
			
			if(transRentSearchTypeIndex == 0)//nothing selected
			{
				//all controls invisible
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transRentSearchTypeIndex == 1)//county search
			{
				//all controls invisible except county search controls
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//county search controls are visible
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(true);
				ApplicationMainWindow.txtTransRentCounty.setVisible(true);
				ApplicationMainWindow.txtTransRentCounty.setText("");
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transRentSearchTypeIndex == 2)//rate search
			{
				//all controls invisible except rate search
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//rate search controls are visible
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(true);
				ApplicationMainWindow.txtTransRentRate.setVisible(true);
				ApplicationMainWindow.txtTransRentRate.setText("");
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transRentSearchTypeIndex == 3)//customer search
			{
				//all controls invisible except customer search
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(false);
				//customer search controls are visible
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(true);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(true);
				ApplicationMainWindow.txtTransRentCustomer.setText("");
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transRentSearchTypeIndex == 4)//estate agent search
			{
				//all controls invisible except agent search
				ApplicationMainWindow.lblSearchTransRentCounty.setVisible(false);
				ApplicationMainWindow.txtTransRentCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentRate.setVisible(false);
				ApplicationMainWindow.txtTransRentRate.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransRentRate.setVisible(false);
				ApplicationMainWindow.lblSearchTransRentCustomer.setVisible(false);
				ApplicationMainWindow.txtTransRentCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchRentTransCustomer.setVisible(false);
				//estate agent search controls are visible
				ApplicationMainWindow.lblSearchTransRentEstateAgent.setVisible(true);
				ApplicationMainWindow.txtTransRentEstateAgent.setVisible(true);
				ApplicationMainWindow.txtTransRentEstateAgent.setText("");
				ApplicationMainWindow.btnDynamicSearchRentTransEAgent.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
		
		//combo box used to search buy transactions by certain attributes
		//Becomes visible when user decides to search by sell transaction in dynamic search panel
		if(e.getSource() == ApplicationMainWindow.cmbDynamicBuyTransactionSearchType)
		{
			transBuySearchTypeIndex = ApplicationMainWindow.cmbDynamicBuyTransactionSearchType.getSelectedIndex();
			
			if(transBuySearchTypeIndex == 0)//nothing selected
			{
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transBuySearchTypeIndex == 1)//county search
			{
				//all controls except find by county are invisible
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				//find by county are visible
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(true);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(true);
				ApplicationMainWindow.txtTransBuyCounty.setText("");
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transBuySearchTypeIndex == 2)//cost search
			{
				//all controls except find by cost are invisible
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				//find by cost are visible
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(true);
				ApplicationMainWindow.txtTransBuyCost.setVisible(true);
				ApplicationMainWindow.txtTransBuyCost.setText("");
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transBuySearchTypeIndex == 3)//customer search
			{
				//all controls except find by customer are invisible
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(false);
				//find by customer controls are visible
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(true);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(true);
				ApplicationMainWindow.txtTransBuyCustomer.setText("");
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
			else if(transBuySearchTypeIndex == 4)//estate agent search
			{
				//all controls except find by estate agent are invisible
				ApplicationMainWindow.lblSearchTransBuyCounty.setVisible(false);
				ApplicationMainWindow.txtTransBuyCounty.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCost.setVisible(false);
				ApplicationMainWindow.txtTransBuyCost.setVisible(false);
				ApplicationMainWindow.lblSearchTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.txtTransBuyCustomer.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCounty.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCost.setVisible(false);
				ApplicationMainWindow.btnDynamicSearchBuyTransCustomer.setVisible(false);
				//make estate agent controls visible
				ApplicationMainWindow.lblSearchTransBuyEstateAgent.setVisible(true);
				ApplicationMainWindow.txtTransBuyEstateAgent.setVisible(true);
				ApplicationMainWindow.txtTransBuyEstateAgent.setText("");
				ApplicationMainWindow.btnDynamicSearchBuyTransEAgent.setVisible(true);
				ApplicationMainWindow.scr1Results.setVisible(false);//invisible until show button is pressed
			}
		}
	}//end itemStateChanged()
	
	//Returns the selected index of login staff type combo box
	public int getLoginType()
	{
		return currentLoginIndex;
	}
	
	//Returns the selected index of create new user staff type combo box
	public int getCreateUserType()
	{
		return currentCreateUserTypeIndex;
	}
	
	//Returns the selected index of update old user staff type combo box
	public int getUpdateUserOldType()
	{
		return updateUserOldTypeIndex;
	}
	
	//Returns the selected index of update new user staff type combo box
	public int getUpdateUserNewType()
	{
		return updateUserNewTypeIndex;
	}
	
	//Returns the selected index of delete user staff type combo box
	public int getDeleteUserType()
	{
		return deleteUserTypeIndex;
	}
	
	//Returns the selected index of create new staff member type combo box
	public int getCreateStaffMemberType()
	{
		return createNewStaffIndex;
	}
	
	//Returns the selected index of create new staff member type combo box
	public int getUpdateStaffMemberType()
	{
		return updateStaffIndex;
	}
	
	//Updates/Returns selected index of update staff member type combo box
	public void setUpdateStaffMemberType(int updateIndex)
	{
		updateStaffIndex = updateIndex;
		ApplicationMainWindow.cmbUpdateStaffOptions.setSelectedIndex(updateStaffIndex);
		
	}
	
	//Updates/Returns selected index of select send file to user combo box
	public int getSendFileUserType()
	{
		return sendFileOptionIndex;
	}
	
	//Updates/Returns selected index of select new house type to user combo box
	public int getNewHouseType()
	{
		return createHouseOptionsIndex;
	}
	
	//Updates/Returns selected index of select update house type to user combo box
	public int getUpdateHouseType()
	{
		return updateHouseOptionsIndex;
	}
	
	//Updates/Returns selected index of update house type combo box
	public void setUpdateHouseType(int updateIndex)
	{
		updateHouseOptionsIndex = updateIndex;
		ApplicationMainWindow.cmbUpdateHouseOptions.setSelectedIndex(updateHouseOptionsIndex);
	}
	
	//Updates/Returns selected index of select emp type combo box
	public int getEmpSearchType()
	{
		return empTypeSearchTypeIndex;
	}	
	
	//Returns the selected index of the search by house type combo box
	public int getHouseSearchType()
	{
		return houseSearchTypeIndex;
	}
}//end class
