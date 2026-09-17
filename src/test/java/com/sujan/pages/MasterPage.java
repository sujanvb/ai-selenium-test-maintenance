package com.sujan.pages;

import org.openqa.selenium.WebDriver;

import com.sujan.utilityClasses.ReusableActionClass;

public abstract class MasterPage {
	
	ReusableActionClass actions = null;
	public MasterPage(WebDriver driver) {
		actions = new ReusableActionClass(driver);
	}
	
	public abstract boolean isPageLoaded();
}
