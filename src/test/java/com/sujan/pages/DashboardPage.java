package com.sujan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends MasterPage{

	By dashboardPageTitle = By.xpath("//h2[normalize-space(text())='Dashboard']");
	By manageCustomersButton = By.xpath("//button[@id='manageCustomersButton']");
	
	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isPageLoaded() {
		System.out.println("Checking if Dashboard page is displayed");
		actions.waitForVisibility(dashboardPageTitle);
		return actions.isDisplayed(dashboardPageTitle);
	}

	public void clickManageCustomersButton() {
		System.out.println("Clicking Manage customers button");
		actions.click(manageCustomersButton);
	}
}