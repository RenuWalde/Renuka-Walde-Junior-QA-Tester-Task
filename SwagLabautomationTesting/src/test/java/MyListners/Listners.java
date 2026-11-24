package MyListners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseDriver;

public class Listners implements ITestListener {
	ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		
		System.out.println("New Test Started" +result.getName());
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("Test Successfully Finished" +result.getName());
		extentTest=extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, result.getName());
	}

	public void onTestFailure(ITestResult result) {
		 //ITestContext context = result.getTestContext();
		WebDriver driver = BaseDriver.driver;
	 
		System.out.println("Test Failed=>" +result.getName());
	  extentTest=extentReports.createTest(result.getName());
		// extentTest.log(Status.FAIL,result.getThrowable());
//		 Object testClass = result.getInstance();
		 if (result.getStatus() == ITestResult.FAILURE) {
		        String screenshotPath = getScreenshot(driver, result.getName());
		        System.out.println(screenshotPath);
		        // Attach to the report log
		        	extentTest.fail("Test Case Failed: " + result.getThrowable(), 
		            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		    }
		    extentReports.flush(); 
		    
	}

	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Test Skipped" +result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		System.out.println("Test Failed but within success percentage" +result.getName());
		 extentTest=extentReports.createTest(result.getName());
		 extentTest.log(Status.FAIL,result.getThrowable());
		 
	}

	public void onStart(ITestContext context) {
		
		
		System.out.println("This is onStart method" +context.getOutputDirectory());
		  System.out.println("Before Test");
		   Random r=new Random();
		   int n=r.nextInt(100,1000000);
	       extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport_"+n+".html");
//	       extentSparkReporter.setAppendExisting(true)
	       extentReports = new ExtentReports();
	       extentReports.attachReporter(extentSparkReporter);
	       //configuration items to change the look and feel
	       //add content, manage tests etc
	       extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
	       extentSparkReporter.config().setReportName("Test Report");
	       extentSparkReporter.config().setTheme(Theme.STANDARD);
	       extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	      
	}

	public void onFinish(ITestContext context) {
			
		System.out.println("This is onFinish method" +context.getPassedTests());
		System.out.println("This is onFinish method" +context.getFailedTests());
		  extentReports.flush();
	}
	public static String getScreenshot(WebDriver driver, String screenshotName)  {
	  
	     
	    try {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    
	    // Path where screenshot will be saved
	    String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
	    File finalDestination = new File(destination);
	    FileUtils.copyFile(source, finalDestination);
	    return destination;
	    }
	    catch(Exception ex) {
	    	return "Exception=>"+ex.getMessage();
	    }
	}


}
