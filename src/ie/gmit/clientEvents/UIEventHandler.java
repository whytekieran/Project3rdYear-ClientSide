package ie.gmit.clientEvents;

//Package imports
import ie.gmit.clientGUI.ApplicationMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* UIEventHandler class is responsible for any buttons that are strictly for the UI, they are not used
   to interact with the server in anyway and they are also not menu items*/
public class UIEventHandler implements ActionListener
{
	//Triggered when a button if clicked
	public void actionPerformed(ActionEvent event) 
	{
		//Perform a switch on the action command for the event
		switch(event.getActionCommand())
		{
			//If buttons action command is CancelNewUser
			case "CancelNewUser":
				ApplicationMainWindow.pnlCreateNewUser.setVisible(false);//Make the create user panel invisible
				break;
			//If buttons action command is CancelUpdateUser
			case "CancelUpdateUser":
				ApplicationMainWindow.pnlUpdateUser.setVisible(false);//Make the update user panel invisible
				break;
			//If buttons action command is CancelDeleteUser
			case "CancelDeleteUser":
				ApplicationMainWindow.pnlDeleteUser.setVisible(false);//Make the delete user panel invisible
				break;
			//If buttons action command is CancelNewStaffMember
			case "CancelNewStaffMember":
				ApplicationMainWindow.pnlCreateStaffMember.setVisible(false);//Make create staff panel invisible
				break;
			//If buttons action command is CancelUpdateStaffMember
			case "CancelUpdateStaffMember":
				ApplicationMainWindow.pnlUpdateStaffMember.setVisible(false);//Make the update staff panel invisible
				ApplicationMainWindow.lblEnterIDStaff.setVisible(false);//Make the update staff panel invisible
				ApplicationMainWindow.btnSearchStaffEdit.setVisible(false);//Make the update staff panel invisible
				ApplicationMainWindow.txtSearchStaffEdit.setVisible(false);//Make the update staff panel invisible
				ApplicationMainWindow.btnRefreshStaffEdit.setVisible(false);
				break;
			//If buttons action command is CancelDeleteStaffMember
			case "CancelDeleteStaffMember":
				ApplicationMainWindow.pnlDeleteStaffMember.setVisible(false);//make delete staff member panel invisible
				break;
			//If buttons action command is CancelShowStaff
			case "CancelShowStaff":
				ApplicationMainWindow.pnlStaffMemberShowArea.setVisible(false);//make show staff member panel invisible
				break;
			//If buttons action command is CancelUploadFile
			case "CancelUploadFile":
				ApplicationMainWindow.pnlUploadFile.setVisible(false);//make upload file panel invisible
				break;
			//If buttons action command is CancelFileDownload
			case "CancelFileDownload":
				ApplicationMainWindow.pnlDownloadFile.setVisible(false);//make panel download file invisible
				break;
			//If buttons action command is CancelSendFiles
			case "CancelSendFiles":
				//Set send file panel to invisible and default combo box selected index
				ApplicationMainWindow.pnlSendFile.setVisible(false);
				ApplicationMainWindow.cmbSendFileOptions.setSelectedIndex(0);
				break;
			//If buttons action command is CancelFileRemove
			case "CancelFileRemove":
				ApplicationMainWindow.pnlRemoveFile.setVisible(false);//make remove file panel invisible
				break;
			//If buttons action command is CancelNewHouse
			case "CancelNewHouse":
				ApplicationMainWindow.pnlCreateHouse.setVisible(false);//make create house panel invisible
				break;
			//If buttons action command is CancelUpdateHouse
			case "CancelUpdateHouse":
				//make update house panel and controls invisible
				ApplicationMainWindow.pnlUpdateHouse.setVisible(false);
				ApplicationMainWindow.lblEnterIDHouse.setVisible(false);
				ApplicationMainWindow.btnSearchHouseEdit.setVisible(false);
				ApplicationMainWindow.txtSearchHouseEdit.setVisible(false);
				ApplicationMainWindow.btnRefreshHouseEdit.setVisible(false);
				break;
			//If buttons action command is CancelDeleteHouse
			case "CancelDeleteHouse":
				ApplicationMainWindow.pnlDeleteHouse.setVisible(false);//make delete house panel invisible
				break;
			//If buttons action command is CancelShowHouses
			case "CancelShowHouses":
				ApplicationMainWindow.pnlHouseShowArea.setVisible(false);//make show houses panel invisible
				break;
			//If buttons action command is CancelFileView
			case "CancelFileView":
				ApplicationMainWindow.pnlViewFile.setVisible(false);//make view file panel invisible
				break;
			//If buttons action command is CancelCreateTransaction
			case "CancelCreateTransaction":
				ApplicationMainWindow.pnlCreateTransaction.setVisible(false);//make create transaction panel invisible
				break;
			//If buttons action command is CancelDeleteTransaction
			case "CancelDeleteTransaction":
				ApplicationMainWindow.pnlDeleteTransaction.setVisible(false);//make delete transaction panel invisible
				break;
			//If buttons action command is CancelUpdateTransaction
			case "CancelUpdateTransaction":
				ApplicationMainWindow.pnlUpdateTransaction.setVisible(false);//make update transaction panel invisible
				break;
			//If buttons action command is CancelViewTransaction
			case "CancelViewTransaction":
				ApplicationMainWindow.pnlViewTransaction.setVisible(false);//make view transaction panel invisible
				break;
			//If buttons action command is CancelNewCustomer
			case "CancelNewCustomer":
				ApplicationMainWindow.pnlAddCustomer.setVisible(false);//make add customer panel invisible
				break;
			//If buttons action command is CancelDeleteCustomer
			case "CancelDeleteCustomer":
				ApplicationMainWindow.pnlDeleteCustomer.setVisible(false);//make delete customer panel invisible
				break;
			//If buttons action command is CancelUpdateCustomer
			case "CancelUpdateCustomer":
				//make update customer panel and controls invisible
				ApplicationMainWindow.pnlUpdateCustomer.setVisible(false);
				ApplicationMainWindow.lblEnterIDCustomer.setVisible(false);
				ApplicationMainWindow.btnSearchCustomerEdit.setVisible(false);
				ApplicationMainWindow.txtSearchCustomerEdit.setVisible(false);
				ApplicationMainWindow.btnRefreshCustEdit.setVisible(false);
				break;
			//If buttons action command is CancelShowCustomers
			case "CancelShowCustomers":
				//make show customers panel and controls invisible
				ApplicationMainWindow.pnlCustomerShowArea.setVisible(false);
				ApplicationMainWindow.scr1Customer.setVisible(false);
				ApplicationMainWindow.btnShowAllCustomers.setVisible(false);
				ApplicationMainWindow.btnCancelShowCustomers.setVisible(false);
				ApplicationMainWindow.lblViewCustomer.setVisible(false);
				ApplicationMainWindow.txtSearchCustomer.setVisible(false);
				ApplicationMainWindow.btnSearchForCustomer.setVisible(false);
				break;
			//If buttons action command is RefreshUpdateStaffMember
			case "RefreshUpdateStaffMember":
				//Set all controls on the update staff member panel back to default
				ApplicationMainWindow.txtSearchStaffEdit.setText("");
				ApplicationMainWindow.txtSearchStaffEdit.setEditable(true);
				ApplicationMainWindow.txtStaffUpdateFirstName.setText("");
				ApplicationMainWindow.txtStaffUpdateLastName.setText("");
				ApplicationMainWindow.txtStaffUpdateAddress.setText("");
				ApplicationMainWindow.txtStaffUpdatePPS.setText("");
				ApplicationMainWindow.txtStaffUpdateSalary.setText("");
				ApplicationMainWindow.cmbUpdateStaffOptions.setSelectedIndex(0);
				ApplicationMainWindow.btnUpdateStaffMember.setEnabled(false);	
				break;
			//If buttons action command is RefreshUpdateHouse
			case "RefreshUpdateHouse":
				//Set all controls on the update house panel back to default
				ApplicationMainWindow.txtSearchHouseEdit.setText("");
				ApplicationMainWindow.txtSearchHouseEdit.setEditable(true);
				ApplicationMainWindow.txtUpdatedHouseStreet.setText("");
				ApplicationMainWindow.txtUpdatedHouseTown.setText("");
				ApplicationMainWindow.txtUpdatedHouseCounty.setText("");
				ApplicationMainWindow.cmbUpdateHouseOptions.setSelectedIndex(0);
				ApplicationMainWindow.btnUpdateHouse.setEnabled(false);
				break;
			//If buttons action command is RefreshRentTransaction
			case "RefreshRentTransaction":
				//Set all controls on the update rent transaction panel back to default
				ApplicationMainWindow.txtTransUpdateRentID.setText("");
				ApplicationMainWindow.txtTransUpdateRentID.setEditable(true);
				ApplicationMainWindow.txtTransUpdateHouseRentID.setText("");
				ApplicationMainWindow.txtTransUpdateStartDate.setText("");
				ApplicationMainWindow.txtTransUpdateEndDate.setText("");
				ApplicationMainWindow.txtTransUpdateMonthlyRate.setText("");
				ApplicationMainWindow.txtUpdateEstateAgentID.setText("");
				ApplicationMainWindow.txtUpdateRentCustID.setText("");
				ApplicationMainWindow.btnUpdateRentTrans.setEnabled(false);
				break;
			//If buttons action command is RefreshBuyTransaction
			case "RefreshBuyTransaction":
				//Set all controls on the update buy transaction panel back to default
				ApplicationMainWindow.txtTransUpdateBuyID.setText("");
				ApplicationMainWindow.txtTransUpdateBuyID.setEditable(true);
				ApplicationMainWindow.txtTransUpdateBuyHouseID.setText("");
				ApplicationMainWindow.txtUpdateBuyCost.setText("");
				ApplicationMainWindow.txtUpdateBuyEstateAgentID.setText("");
				ApplicationMainWindow.txtUpdateBuyCustID.setText("");
				ApplicationMainWindow.btnUpdateBuyTrans.setEnabled(false);
				break;
			//If buttons action command is RefreshUpdateCustomer
			case "RefreshUpdateCustomer":
				//Set all controls on the update customer panel back to default
				ApplicationMainWindow.txtSearchCustomerEdit.setEditable(true);
				ApplicationMainWindow.txtSearchCustomerEdit.setText("");
				ApplicationMainWindow.txtCustomerUpdateFirstName.setText("");
				ApplicationMainWindow.txtCustomerUpdateLastName.setText("");
				ApplicationMainWindow.txtCustomerUpdateAddress.setText("");
				ApplicationMainWindow.btnUpdateCustomer.setEnabled(false);
				break;
			//If buttons action command is CancelNewReminder
			case "CancelNewReminder":
				ApplicationMainWindow.pnlCreateReminder.setVisible(false);
				break;
			//If buttons action command is CancelUpdateReminder
			case "CancelUpdateReminder":
				ApplicationMainWindow.lblEnterIDReminder.setVisible(false);
				ApplicationMainWindow.btnSearchReminderEdit.setVisible(false);
				ApplicationMainWindow.btnRefreshReminderEdit.setVisible(false);
				ApplicationMainWindow.txtSearchReminderEdit.setVisible(false);
				ApplicationMainWindow.pnlUpdateReminder.setVisible(false);
				break;
			//If buttons action command is CancelDeleteReminder
			case "CancelDeleteReminder":
				ApplicationMainWindow.pnlDeleteReminder.setVisible(false);
				break;
			//If buttons action command is CancelShowReminder
			case "CancelShowReminder":
				ApplicationMainWindow.pnlReminderShowArea.setVisible(false);
				ApplicationMainWindow.scr1Reminder.setVisible(false);
				ApplicationMainWindow.btnShowAllReminder.setVisible(false);
				ApplicationMainWindow.btnCancelShowReminder.setVisible(false);
				ApplicationMainWindow.lblViewReminder.setVisible(false);
				ApplicationMainWindow.txtSearchReminder.setVisible(false);
				ApplicationMainWindow.btnSearchForReminder.setVisible(false);
				break;
			//If buttons action command is CancelDynamicSearch
			case "CancelDynamicSearch":
				ApplicationMainWindow.pnlDynamicSearch.setVisible(false);
				break;
			//If buttons action command is RefreshUpdateReminder
			case "RefreshUpdateReminder":
				//Set all controls on the update reminder panel back to default
				ApplicationMainWindow.txtUpdateReminderDate.setText("");
				ApplicationMainWindow.txtSearchReminderEdit.setEditable(true);
				ApplicationMainWindow.txtUpdateReminderTime.setText("");
				ApplicationMainWindow.txtUpdateReminderSubject.setText("");
				ApplicationMainWindow.txtUpdateReminderDesc.setText("");
				ApplicationMainWindow.txtSearchReminderEdit.setText("");
				ApplicationMainWindow.btnUpdateReminder.setEnabled(false);
				break;
		}//end switch statement
	}//end actionPerformed()
}//end UIEventHandler class
