package Practice.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Practice.TestComponents.BaseTest;
import Practice.pageobjects.CartPage;
import Practice.pageobjects.ProductCatalogue;
public class ErrorValidationsTest extends BaseTest{

	@Test()
	public void LoginErrorValidation() throws IOException
	{
		
		lpage.loginApplication("compcreator@gmail.com", "Pra@123456");
		Assert.assertEquals("Incorrect email or password", lpage.getErrorMessage());		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException
	{
		String product = "ZARA COAT 3";
		ProductCatalogue prodcat =lpage.loginApplication("compcreator484@gmail.com", "Prac@123456");
		List<WebElement> products = prodcat.getProductList();
		prodcat.addProductToCart(product);
		
		CartPage cartpage = prodcat.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		
		
	}
	
}
