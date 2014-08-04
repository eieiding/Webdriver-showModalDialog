package com.webdriver.example.parentchild;

import java.util.Set;
import org.openqa.selenium.WebDriver;
import com.google.common.base.Predicate;

/**
 * @author Ofir Gal
 * The abstract base page for the example child/parent pages
 */
public abstract class BasePage {

	 protected final WebDriver driver;
	 	 
	 protected BasePage (WebDriver driver){
		 this.driver = driver;
	 }
	 
	 protected WebDriver getDriver(){
		 return driver;
	 }
 
	 protected Predicate<WebDriver> newPopUp(final int beforeSize) {
		    return new Predicate<WebDriver>() {
		        @Override public boolean apply(WebDriver driver) {
		        	Set<String> afterPopup = driver.getWindowHandles();
		        	if (afterPopup.size() > beforeSize)
		        		return true;
		        	else
		        		return false;
		        }
		    };
	 }
}
