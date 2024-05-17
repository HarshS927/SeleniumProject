package Practice.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Practice.TestComponents.BaseTest;
import Practice.pageobjects.CartPage;
import Practice.pageobjects.CheckOutPage;
import Practice.pageobjects.ConfirmationPage;
import Practice.pageobjects.LandingPage;
import Practice.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{

	public LandingPage lpage;
	public ProductCatalogue prodcat;
	public ConfirmationPage confirmPage;
	List<WebElement> products;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_ecommerce_page() throws IOException {
		
		lpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")//regular expression- "^.......$"
	public void logged_in_with_name_and_pwd(String username, String password)
	{
		prodcat =lpage.loginApplication(username, password);	
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_Add_product_to_cart(String productName)
	{
		products = prodcat.getProductList();
		prodcat.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_product_submit_order(String productName)
	{
		CartPage cartpage = prodcat.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckOutPage checkout = cartpage.goToCheckout();
		checkout.selectCountry("india");
		
		confirmPage = checkout.submitOrder();
	}
	
	@Then("{string} message is displayed on the ConfiramtionPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmmssg = confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmmssg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is dispalyed")
	public void error_message_displayed(String string)
	{
		Assert.assertEquals(string, lpage.getErrorMessage());
		driver.close();
	}
}
