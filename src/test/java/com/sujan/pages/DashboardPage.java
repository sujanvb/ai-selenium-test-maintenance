package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends MasterPage{

	By dashboardPageTitle = By.xpath("//h2[normalize-space(text())='Dashboard']");
	By viewCustomersButton = By.xpath("//button[@id='viewCustomersButton']");
	
	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if Dashboard page is displayed");
		actions.waitForVisibility(dashboardPageTitle);
		return actions.isDisplayed(dashboardPageTitle);
	}

	public void clickViewCustomersButton() {
		System.out.println("Clicking View customers button");
		actions.click(viewCustomersButton);
	}
}
