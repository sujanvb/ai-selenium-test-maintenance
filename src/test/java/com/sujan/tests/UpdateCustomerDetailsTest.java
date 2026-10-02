package com.sujan.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sujan.pages.CustomerDetailsPage;
import com.sujan.pages.CustomersPage;
import com.sujan.pages.DashboardPage;
import com.sujan.pages.LoginPage;
import com.sujan.utilityClasses.DriverScript;
import com.sujan.utilityClasses.ReportManager;

public class UpdateCustomerDetailsTest {

	WebDriver driver = null;
	
	@BeforeTest
	public void initiateDriver() {
		driver = DriverScript.getDriver();
	}
	
	@Test
	public void updateCustomerDetails() {

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(driver);
        ReportManager.setDriver(driver);
        
        String url = DriverScript.getPropertyValue("application_url");
        
        driver.get(url);
        
        boolean loginPageLoaded = loginPage.isPageLoaded();
        
        if(loginPageLoaded) {
        	ReportManager.updateTestLog("Launch url and verify if Login Page is loaded", "URL: "+url+"<br>Login Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Launch url and verify if Login Page is loaded", "URL: "+url+"<br>Login Page is not loaded", "FAIL");
        }
        Assert.assertTrue(loginPageLoaded, "Login Page is not loaded for URL: "+url);
        
        loginPage.enterUserName("admin");
        loginPage.enterPassword("admin");
        loginPage.clickLoginButton();
        
        boolean dashboardPageLoaded = dashboardPage.isPageLoaded();
        
        if(dashboardPageLoaded) {
        	ReportManager.updateTestLog("Fill credentials and click login button<br>Verify if dashboard page is loaded", "Dashboard Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Fill credentials and click login button<br>Verify if dashboard page is loaded", "Dashboard Page is not loaded", "FAIL");
        }
        Assert.assertTrue(dashboardPageLoaded, "Dashboard Page is not loaded after login");
        
        dashboardPage.clickViewCustomersButton();
        
        boolean customersPageLoaded = customersPage.isPageLoaded();
        
        if(customersPageLoaded) {
        	ReportManager.updateTestLog("Click View Customers button<br>Verify if customers page is loaded", "Customers Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Click View Customers button<br>Verify if customers page is loaded", "Customers Page is not loaded", "FAIL");
        }
        Assert.assertTrue(customersPageLoaded, "Customers Page is not loaded");
        
        String customerID = "001";
        
        customersPage.clickViewCustomerButtonForID(customerID);
        
        boolean customerDetailsPageLoaded = customerDetailsPage.isPageLoaded();
        
        if(customerDetailsPageLoaded) {
        	ReportManager.updateTestLog("Click View Customer button for ID '"+customerID+"'<br>Verify if customer details page is loaded", "Customer Details Page is loaded", "PASS");
        } else {
        	ReportManager.updateTestLog("Click View Customer button for ID '"+customerID+"'<br>Verify if customer details page is loaded", "Customer Details Page is not loaded", "FAIL");
        }
        Assert.assertTrue(customerDetailsPageLoaded, "Customer Details Page is not loaded for ID '"+customerID+"'");
        
        String updatedName = "John Smith Updated";
        
        customerDetailsPage.enterName(updatedName);
        
        boolean nameUpdatedOnDetailsPage = customerDetailsPage.getName().equals(updatedName);
        
        if(nameUpdatedOnDetailsPage) {
        	ReportManager.updateTestLog("Update name to '"+updatedName+"'", "Name is updated", "PASS");
        } else {
        	ReportManager.updateTestLog("Update name to '"+updatedName+"'", "Name is not updated", "FAIL");
        }
        Assert.assertTrue(nameUpdatedOnDetailsPage, "Name was not updated to '"+updatedName+"' on Customer Details Page");
        
        customerDetailsPage.clickSaveButton();
        
        customersPage.isPageLoaded();
        
        boolean nameUpdatedInCustomersList = customersPage.getNameOfID(customerID).equals(updatedName);
        
        if(nameUpdatedInCustomersList) {
        	ReportManager.updateTestLog("Click on save button<br>Verify if Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "PASS");
        } else {
        	ReportManager.updateTestLog("Click on save button<br>Verify if Customer ID '"+customerID+"' name is updated to '"+updatedName+"' in the customers page", "Customer ID '"+customerID+"' name is not updated to '"+updatedName+"' in the customers page", "FAIL");
        }
        Assert.assertTrue(nameUpdatedInCustomersList, "Customer ID '"+customerID+"' name is not updated to '"+updatedName+"' in the customers page");
        
        ReportManager.closeReport();
    }
	
	@AfterTest
	public void quitDriver() {
		driver.quit();
	}
}