package steps;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import base.BaseDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseDriver {
	@Given("Open Browser and Enter Login URL {string}")
		public void openBrowser(String URL) {
		driver=new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		
		}
	@Given("Enter userid {string}")
	public void enterEmail(String em) {
		WebElement email=driver.findElement(By.id("user-name"));
		email.sendKeys(em);
	}
		
		@Given("Enter Password {string}")
		public void EnterPassword(String ps) {
			WebElement password = driver.findElement(By.id("password"));
			password.sendKeys(ps);	
	
	}
		@When("Click on Login")
		public void Clickbtn() {
			WebElement submitbtn = driver.findElement(By.id("login-button"));
			submitbtn.click();
		
		}}
	

	