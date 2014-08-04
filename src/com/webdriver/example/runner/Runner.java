package com.webdriver.example.runner;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.webdriver.example.parentchild.*;

/**
 * @author Ofir Gal
 * Demo executer
 */
public class Runner {

	private static WebDriver driver;
	
	public static void main(String[] args) {
		String path = Runner.class.getClassLoader().getResource(".").getPath();
		driver = new FirefoxDriver();;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.get("file://" + path + "parent.htm");
		Parent parent =  new Parent(driver);
		Child child1 = parent.clickOpenModal();
		Child child2 = child1.clickOpenModalPopup();
		child2.clickClose();
		child1.clickClose();
		driver.quit();
	}
}
