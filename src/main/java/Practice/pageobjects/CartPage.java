package Practice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(css = ".cartSection h3")
	private List<WebElement> prodTitles;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	public boolean VerifyProductDisplay(String productName) {
		Boolean match = prodTitles.stream().anyMatch(a->a.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout()
	{
		checkout.click();
		return new CheckOutPage(driver);
	}

	
}
