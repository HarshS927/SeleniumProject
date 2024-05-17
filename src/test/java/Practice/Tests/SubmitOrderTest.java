package Practice.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.TestComponents.BaseTest;
import Practice.pageobjects.CartPage;
import Practice.pageobjects.CheckOutPage;
import Practice.pageobjects.ConfirmationPage;
import Practice.pageobjects.LandingPage;
import Practice.pageobjects.OrderPage;
import Practice.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
public class SubmitOrderTest extends BaseTest{

	String item = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		
	 	ProductCatalogue prodcat =lpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = prodcat.getProductList();
		prodcat.addProductToCart(input.get("item"));
		
		CartPage cartpage = prodcat.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("item"));
		Assert.assertTrue(match);
		
		CheckOutPage checkout = cartpage.goToCheckout();
		checkout.selectCountry("india");
		
		ConfirmationPage confirmPage = checkout.submitOrder();
		
		String text = confirmPage.getConfirmationMessage();
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue prodcat =lpage.loginApplication("compcreator484@gmail.com", "Prac@123456");
		OrderPage orderpage = prodcat.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(item));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("item","ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "compcreator484@gmail.com");
		map1.put("password", "Prac@123456");
		map1.put("item","ADIDAS ORIGINAL");
	*/	
		List<HashMap<String,String>> data = getJsonData("F:\\SeleniumFrameworkDesign\\src\\test\\java\\Practice\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	
	}

	
}
