package com.sujan.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.sujan.pages.CustomerDetailsPage;
import com.sujan.pages.CustomersPage;
import com.sujan.pages.DashboardPage;
import com.sujan.pages.LoginPage;
import com.sujan.utilityClasses.DriverScript;
import com.sujan.utilityClasses.ReportManager;

public class UpdateCustomerDetailsTest {

	@Test
	public void updateCustomerDetails() {

        WebDriver driver = DriverScript.getDriver();
        
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(driver);
        ReportManager.setDriver(driver);
        
        String url = DriverScript.getPropertyValue("application_url");
        
        driver.get(url);
        
        if(loginPage.isPageLoaded()) {
        	ReportManager.updateTestLog("Launch url and verify if Login Page is loaded", "URL: "+url+"<br>Login Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Launch url and verify if Login Page is loaded", "URL: "+url+"<br>Login Page is not loaded", "FAIL");
        }
        
        String loginEmailAddress = "";
        
        loginPage.enterEmailAddress(loginEmailAddress);
        loginPage.enterPassword("admin");
        loginPage.clickSignInButton();
        
        if(dashboardPage.isPageLoaded()) {
        	ReportManager.updateTestLog("Fill credentials and click sign in button<br>Verify if dashboard page is loaded", "Dashboard Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Fill credentials and click sign in button<br>Verify if dashboard page is loaded", "Dashboard Page is not loaded", "FAIL");
        }
        
        dashboardPage.clickManageCustomersButton();
        
        if(customersPage.isPageLoaded()) {
        	ReportManager.updateTestLog("Click Manage Customers button<br>Verify if customers page is loaded", "Customers Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Click Manage Customers button<br>Verify if customers page is loaded", "Customers Page is not loaded", "FAIL");
        }
        
        String customerID = "001";
        
        customersPage.clickViewDetailsButtonForID(customerID);
        
        if(customerDetailsPage.isPageLoaded()) {
        	ReportManager.updateTestLog("Click View Details button for ID '"+customerID+"'<br>Verify if customer details page is loaded", "Customer Details Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Click View Details button for ID '"+customerID+"'<br>Verify if customer details page is loaded", "Customer Details Page is not loaded", "FAIL");
        }
        
        String updatedName = "John Smith Updated";
        
        customerDetailsPage.enterName(updatedName);
        
        if(customerDetailsPage.getName().equals(updatedName)) {
        	ReportManager.updateTestLog("Update name to '"+updatedName+"'", "Name is updated", "PASS");
        } else {
        	ReportManager.updateTestLog("Update name to '"+updatedName+"'", "Name is not updated", "FAIL");
        }
        
        customerDetailsPage.clickUpdateCustomerButton();
        
        customerDetailsPage.clickBackToCustomersButton();
        
        customersPage.isPageLoaded();
        
        if(customersPage.getNameOfID(customerID).equals(updatedName)) {
        	ReportManager.updateTestLog("Click on update customer button<br>Click on back to customers button<br>Verify if Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "PASS");
        } else {
        	ReportManager.updateTestLog("Click on update customer button<br>Click on back to customers button<br>Verify if Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "Customer ID '"+customerID+"' name is not updated to '"+updatedName+"' in the customers page", "FAIL");
        }
        
        ReportManager.closeReport();
        driver.quit();
    }
}