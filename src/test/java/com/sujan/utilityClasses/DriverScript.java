package com.sujan.utilityClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverScript {

    private static ChromeDriver driver = null;

    private DriverScript() {
        // Prevent object creation
    }

    public static ChromeDriver getDriver() {

        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            // Start browser maximized
            options.addArguments("--start-maximized");

            // Disable cache
            options.addArguments("--disable-application-cache");
            options.addArguments("--disk-cache-size=0");

            // Start browser in incognito mode
            options.addArguments("--incognito");

            // Disable extensions and plugins
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-plugins");

            // Selenium Manager automatically handles ChromeDriver
            driver = new ChromeDriver(options);
        }

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    public static String getPropertyValue(String key) {
        Properties properties = new Properties();
        
        // Open the file and load it in a single try-with-resources block
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
						+ "java" + File.separator + "resources" + File.separator + "GlobalSettings.properties")) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
            return null; 
        }
    }
}