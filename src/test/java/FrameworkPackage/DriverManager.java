package FrameworkPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	private  WebDriver driver;
	
	public DriverManager() {
		//getDriver();
	}
	
	public WebDriver getDriver() {
		if(driver == null) {
			setFirefoxDriver();
		}
		driver.get("https://todomvc.com/examples/vanillajs/");

		return driver;
	}
	
	public void setChromeDriver() {
		if(driver != null)
			killDriver();
		
		driver = new ChromeDriver();
	}
	public void setFirefoxDriver() {
		if(driver != null)
			killDriver();
		driver = new FirefoxDriver();
	}
	
	public void killDriver() {
		if(driver !=  null) {
			driver.quit();
			driver = null;
		}
	}
	
}
