package com.webdriver.example.parentchild;

import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Ofir Gal
 * Implements the child.htm file
 */
public class Child extends BasePage {

	@FindBy(id = "OpenPopupButton")
	private WebElement buttonOpen;
	
	@FindBy(id = "MyModalPopup")
	private WebElement buttonClose;
	
	private String parent;
	
	public Child(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public String getParent(){
		return parent;
	}
	
	public void setParent(String windowHandler){
		parent = windowHandler;
	}
	
	/**
	 * @return
	 * This JS implementation is a workaround for WebDriver bug #284 https://code.google.com/p/selenium/issues/detail?id=284
	 */
	public Child clickOpenModalPopup(){
		Child rtnChild = new Child(driver);
		rtnChild.setParent(driver.getWindowHandle());
		Set<String> beforePopUp = driver.getWindowHandles();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("var el=arguments[0]; setTimeout(function() { el.click(); }, 100);", buttonOpen);
		new WebDriverWait(driver, 10).until(newPopUp(beforePopUp.size()));
		Set<String> afterPopup = driver.getWindowHandles();
		String popUp = null;
		afterPopup.removeAll(beforePopUp);
		if(afterPopup.size() == 1) 
			popUp = (String) afterPopup.toArray()[0];
		driver.switchTo().window(popUp);
		return rtnChild;
	}
	
	/**
	 * This JS implementation is a workaround for WebDriver bug #284 https://code.google.com/p/selenium/issues/detail?id=284
	 */
	public void clickClose(){
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("var el=arguments[0]; setTimeout(function() { el.click(); }, 100);", buttonClose);	
		driver.switchTo().window(getParent());
	}
	
	
}
