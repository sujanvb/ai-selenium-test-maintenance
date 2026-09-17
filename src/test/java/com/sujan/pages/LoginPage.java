package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends MasterPage{

	By usernameInputField = By.xpath("//input[@id='username']");
	By passwordInputField = By.xpath("//input[@id='password']");
	By loginButton = By.xpath("//button[@id='loginButton']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if login page is loaded");
		actions.waitForVisibility(usernameInputField);
		return actions.isDisplayed(passwordInputField);
	}

	public void enterUserName(String username) {
		System.out.println("Populating username field as: "+username);
		actions.setText(usernameInputField, username);
	}
	
	public void enterPassword(String password) {
		System.out.println("Populating password field");
		actions.setText(passwordInputField, password);
	}
	
	public void clickLoginButton() {
		System.out.println("Clicking on login button");
		actions.click(loginButton);
	}
}
