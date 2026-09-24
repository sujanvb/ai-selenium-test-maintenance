package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends MasterPage{

	By emailAddressInputField = By.xpath("//input[@id='userEmail']");
	By passwordInputField = By.xpath("//input[@id='userPassword']");
	By signInButton = By.xpath("//button[@id='signInButton']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if login page is loaded");
		actions.waitForVisibility(emailAddressInputField);
		return actions.isDisplayed(passwordInputField);
	}

	public void enterEmailAddress(String emailAddress) {
		System.out.println("Populating email address field as: "+emailAddress);
		actions.setText(emailAddressInputField, emailAddress);
	}
	
	public void enterPassword(String password) {
		System.out.println("Populating password field");
		actions.setText(passwordInputField, password);
	}
	
	public void clickSignInButton() {
		System.out.println("Clicking on sign in button");
		actions.click(signInButton);
	}
}