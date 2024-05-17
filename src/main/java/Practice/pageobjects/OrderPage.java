package Practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> prodnames;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	public boolean VerifyOrderDisplay(String productName) {
		Boolean match = prodnames.stream().anyMatch(a->a.getText().equalsIgnoreCase(productName));
		return match;
	}
	

	
}
