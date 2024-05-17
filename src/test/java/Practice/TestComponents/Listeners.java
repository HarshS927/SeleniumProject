package Practice.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Practice.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	private static ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		//TODO 
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id ->test map
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		//TODO 
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		//TODO 
		//Screenshot
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					 .get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		//TODO 
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		//TODO 
	}
	
	public void onStart(ITestContext context)
	{
		//TODO 
	}
	
	public void onFinish(ITestContext context)
	{
		//TODO 
		extent.flush();
	}
	
	
	
}

