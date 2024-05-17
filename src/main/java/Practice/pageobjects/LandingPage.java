package Practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(css = "input[type='password']")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMssg;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalogue prodcat = new ProductCatalogue(driver);
		return prodcat;
	}

	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMssg);
		return errorMssg.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
