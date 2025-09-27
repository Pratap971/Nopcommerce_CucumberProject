package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchcustomerPage;


public class Stepdef extends Baseclass {
	
   @Before	
   public void Setup() throws IOException
   {
	   logger = Logger.getLogger("Nopcommerce"); 
	   PropertyConfigurator.configure("log4j.properties");
	   
	   configProp = new Properties();
	   FileInputStream configPropFile = new FileInputStream("config.properties");
	   configProp.load(configPropFile);
   		
	   String br = configProp.getProperty("browser");
	   
	   if(br.equals("chrome"))
	   {
   		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
   		driver = new ChromeDriver();
	   }
	   else if(br.equals("firefox"))
	   {
		   System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
	   		driver = new FirefoxDriver();
	   }
	   else if(br.equals("edge"))
	   {
		   System.setProperty("webdriver.edge.driver", configProp.getProperty("edgepath"));
	   		driver = new EdgeDriver();
	   }
	   
        driver.manage().window().maximize();
        
   		
   		logger.info(" ****** Launching Browser *****");
   }
	
    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
         
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
    	logger.info("***** Opening URL *****");
        driver.get(url);
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
    	
    	logger.info("***** Providing login details *****");
    	
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
    	logger.info("***** Started Login *****");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            
            logger.info("***** Login Passed *****");
            Assert.assertTrue(false);
        } else {
        	
        	logger.info("***** Login Failed *****");
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
    	
    	logger.info("***** Click on logout link *****");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {
    	logger.info("***** Closing Browser *****");
        driver.quit();
    }
    
    // Customers Feature stepdefination ----------------------------
    
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
    	
    	addCust = new AddcustomerPage(driver);
    	Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
        
    }
    
    
    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
    	addCust.clickOnCustomersMenu();
    }
    
    
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
    	addCust.clickOnCustomersMenuItem(); 
    }
    
    
    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
    	addCust.clickOnAddnew();
    	Thread.sleep(2000);
    }
    
    
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
      Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());  
    }
    
    
    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
    	
       logger.info("***** Adding New Customer *****");
       logger.info("***** Providing Customer details  *****");
       String email = randomstring()+"@gmail.com"; 
       addCust.setEmail(email);
       addCust.setPassword("test123");
       
       addCust.setFirstName("Pratap");
       addCust.setLastName("Yadav");
       addCust.setGender("Male");
       
       addCust.setCompanyName("QAExpert");
       
       // Registered - Default
       // The customer cannot be in both 'Guests' and 'Registered' customer roles
       // Add the customer to 'Guests' or 'Registered' customer role
       
       addCust.setCustomerRoles("Guest");
       Thread.sleep(3000);
       
       addCust.setManagerOfVendor("Vendor 2");
       
       
       addCust.setAdminContent("This is for Automation testing ---------------");
    }
    
    
    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
    	logger.info("***** Saving Customer data *****");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }
    
    
    @Then("User can confirmation message {string}")
    public void user_can_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
        		.contains("The new customer has been added successfully."));
    }
    
    
    // steps for Searching a customer using Email ID -------------
    
    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
    	logger.info("***** Searching customer y Email Id *****");
    	searchCust = new SearchcustomerPage(driver);
    	searchCust.setEmail("victoria_victoria@nopCommerce.com");
       }
    
    
    @When("Click on Search button")
    public void click_on_search_button() throws InterruptedException {
    	searchCust.clickSearch();
    	Thread.sleep(3000);
        
    }
    
    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
     boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        
    	Assert.assertEquals(true, status);
    }

    
    // Steps for Searching a customer using First Name & Last Name 
    
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
    	logger.info("***** Searching customer by Name *****");
    	searchCust = new SearchcustomerPage(driver);
    	searchCust.setFirstName("Victoria");
    }
    
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
       searchCust.setLastName("Terces"); 
    }
    
    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean status = searchCust.searchCustomerByName("Victoria Terces");
        
        Assert.assertEquals(true, status);
    }

    
}
