package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomersPage extends MasterPage{

	By customersPageTitle = By.xpath("//h2[normalize-space(text())='Customers']");
	By viewCustomerButtonForID(String id) {
		return By.xpath("//tr/td[text()='"+id+"']/following-sibling::td/button[normalize-space(text())='View Customer']");
	}
	By nameOfID(String id) {
		return By.xpath("//table/tbody//td[@id='customerName"+id+"']");
	}
	
	public CustomersPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if Customers page is displayed");
		actions.waitForVisibility(customersPageTitle);
		return actions.isDisplayed(customersPageTitle);
	}

	public void clickViewCustomerButtonForID(String id) {
		System.out.println("Clicking View customer button for ID: "+id);
		actions.click(viewCustomerButtonForID(id) );
	}
	
	public String getNameOfID(String id) {
		String text = actions.getText(nameOfID(id));
		System.out.println("Fetched name of '"+id+"' as: "+text);
		return text;
	}
}
