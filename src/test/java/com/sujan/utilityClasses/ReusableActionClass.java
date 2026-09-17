package com.sujan.utilityClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ReusableActionClass {

    private final WebDriver driver;

    public ReusableActionClass(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForVisibility(By locator) {
        try {
            System.out.println("Waiting for visibility of element: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(DriverScript.getPropertyValue("objectSyncTimeout"))));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("[Exception in waitForVisibility] " + e.getMessage());
        }
    }

    public void waitForClickability(By locator) {
        try {
            System.out.println("Waiting for element to be clickable: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(DriverScript.getPropertyValue("objectSyncTimeout"))));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("[Exception in waitForClickability] " + e.getMessage());
        }
    }

    public void click(By locator) {
        try {
            System.out.println("Clicking element: " + locator);
            waitForVisibility(locator);
            driver.findElement(locator).click();
        } catch (Exception e) {
            System.out.println("[Exception in clickElement] " + e.getMessage());
        }
    }

    public boolean isDisplayed(By locator) {
        try {
            System.out.println("Checking if element is displayed: " + locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            System.out.println("[Exception in isDisplayed] " + e.getMessage());
            return false;
        }
    }

    public String getText(By locator) {
        try {
            System.out.println("Getting text from element: " + locator);
            waitForVisibility(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("[Exception in getText] " + e.getMessage());
            return "";
        }
    }

    public void setText(By locator, String text) {
        try {
            System.out.println("Setting text '" + text + "' into element: " + locator);
            waitForVisibility(locator);
            WebElement element = driver.findElement(locator);
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("[Exception in clearSetText] " + e.getMessage());
        }
    }
    
    public void clearSetText(By locator, String text) {
        try {
            System.out.println("Setting text '" + text + "' into element: " + locator);
            waitForVisibility(locator);
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("[Exception in clearSetText] " + e.getMessage());
        }
    }

    public String getAttributeValue(By locator, String attributeName) {
        try {
            System.out.println("Getting '" + attributeName + "' attribute from element: " + locator);
            waitForVisibility(locator);
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            System.out.println("[Exception in getAttributeValue] " + e.getMessage());
            return "";
        }
    }

    public List<String> getTextOfElements(By locator) {
        try {
            System.out.println("Getting text from all elements: " + locator);
            waitForVisibility(locator);
            List<WebElement> elements = driver.findElements(locator);
            return elements.stream().map(WebElement::getText).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("[Exception in getTextOfElements] " + e.getMessage());
            return List.of();
        }
    }
}
