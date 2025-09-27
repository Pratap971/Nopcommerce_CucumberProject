package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
	
	public WebDriver ldriver;
	
	public AddcustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this) ;
	}

	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	
	By btnAddnew = By.xpath("//a[@class='btn btn-primary']");
	
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	
	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtlastName = By.xpath("//input[@id='LastName']");
	
	By rdMaleGender = By.id("Gender_Male");
	By rdFeMaleGender = By.id("Gender_Female");
	
	By txtCompanyName = By.xpath("//input[@id='Company']");
	
	By txtcustomerRoles = By.xpath("//span[@aria-expanded='true']//input[@role='searchbox']");
	
	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	By lstitemRegistered      = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests          = By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors         = By.xpath("//li[contains(text(),'Vendors')]");

	
	By drpmgrofVendor = By.xpath("//select[@id='VendorId']");
	
	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	
	// Action Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddnew() {
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException {
		 try {
		        WebElement deleteBtn = ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@title='delete']"));
		        deleteBtn.click();
		    } catch (Exception e) {
		        
		    }

		    WebElement rolesField = ldriver.findElement(By.xpath("//div[@class='input-group-append input-group-required']//following::input[1]"));
		    rolesField.click();

		    WebElement listitem = null;

		    Thread.sleep(2000);
		
		if(role.equals("Administrators"))
		{
			listitem = ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Forum Moderators"))
		{
			listitem = ldriver.findElement(lstitemForumModerators);
		}
		else if(role.equals("Registered"))
		{
			listitem = ldriver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors"))
		{
			listitem = ldriver.findElement(lstitemVendors);
		}
		else 
		{
			listitem = ldriver.findElement(lstitemGuests);
		}
		
		//listitem.click();
		
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
	    js.executeScript("arguments[0].click();", listitem);
		
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp = new Select(ldriver.findElement(drpmgrofVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFeMaleGender).click();
		}
		else 
		{
			ldriver.findElement(rdMaleGender).click(); // Default
		}
	}
	
	 public void setFirstName(String fname)
	 {
		 ldriver.findElement(txtFirstName).sendKeys(fname);
	 }
	
	 public void setLastName(String lname)
	 {
		 ldriver.findElement(txtlastName).sendKeys(lname);
	 }
	
	 public void setCompanyName(String comname)
	 {
		 ldriver.findElement(txtCompanyName).sendKeys(comname);
	 }
	 
	 public void setAdminContent(String content)
	 {
		 ldriver.findElement(txtAdminContent).sendKeys(content);
	 }
	 
	 public void clickOnSave()
	 {
		 ldriver.findElement(btnSave).click();;
	 }
}
