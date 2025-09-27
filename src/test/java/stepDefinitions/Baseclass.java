package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchcustomerPage;

public class Baseclass {
	
	public WebDriver driver;
    public LoginPage lp;
    public AddcustomerPage addCust;
    public SearchcustomerPage searchCust;
    public static Logger logger;
    public Properties configProp;
    
    // created for geerating random string for unique Email
    
    public static String randomstring() {
    	String generatedString1 = RandomStringUtils.randomAlphabetic(5);
    	return (generatedString1);
    }

}
