package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerDetailsPage extends MasterPage{

	By customerDetailsPageTitle = By.xpath("//h2[normalize-space(text())='Customer Details']");
	By nameInputField = By.xpath("//input[@id='customerName']");
	By emailInputField = By.xpath("//input[@id='email']");
	By phoneInputField = By.xpath("//input[@id='phone']");
	By addressInputField = By.xpath("//input[@id='address']");
	By saveButton = By.xpath("//button[@id='saveCustomer']");
	
	public CustomerDetailsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if Customer details page is displayed");
		actions.waitForVisibility(customerDetailsPageTitle);
		return actions.isDisplayed(customerDetailsPageTitle);
	}

	public void enterName(String name) {
		System.out.println("Populating name field as: "+name);
		actions.clearSetText(nameInputField, name);
	}
	
	public void enterEmail(String email) {
		System.out.println("Populating email field as: "+email);
		actions.clearSetText(emailInputField, email);
	}
	
	public void enterPhone(String phone) {
		System.out.println("Populating phone field as: "+phone);
		actions.clearSetText(phoneInputField, phone);
	}
	
	public void enterAddress(String address) {
		System.out.println("Populating address field as: "+address);
		actions.clearSetText(addressInputField, address);
	}
	
	public String getName() {
		String text = actions.getAttributeValue(nameInputField, "value");
		System.out.println("Fetched name field as: "+text);
		return text;
	}
	
	public String getEmail() {
		String text = actions.getAttributeValue(emailInputField, "value");
		System.out.println("Fetched email field as: "+text);
		return text;
	}
	
	public String getPhone() {
		String text = actions.getAttributeValue(phoneInputField, "value");
		System.out.println("Fetched phone field as: "+text);
		return text;
	}
	
	public String getAddress() {
		String text = actions.getAttributeValue(addressInputField, "value");
		System.out.println("Fetched address field as: "+text);
		return text;
	}
	
	public void clickSaveButton() {
		System.out.println("Clicking save Button");
		actions.click(saveButton);
	}
}
