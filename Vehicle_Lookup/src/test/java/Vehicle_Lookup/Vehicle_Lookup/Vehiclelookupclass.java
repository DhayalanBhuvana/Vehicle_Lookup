package Vehicle_Lookup.Vehicle_Lookup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Vehiclelookupclass {
	
	public static WebDriver driver;
	
	@Given("^user launches the dealer portal$")
	public void user_launches_the_dealer_portal() throws Throwable {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://covercheck.vwfsinsuranceportal.co.uk/");
	}
	
	@When("^user performs Vehicle lookup for \"([^\"]*)\"$")
	public void user_performs_Vehicle_lookup_for(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@id='vehicleReg']")).click();
		driver.findElement(By.xpath("//input[@id='vehicleReg']")).sendKeys(arg1);
		driver.findElement(By.xpath("//span[text()='Find vehicle']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	@Then("^results for \"([^\"]*)\" should be displayed$")
	public void results_for_should_be_displayed(String arg1) throws Throwable {
	    String msg_result1 = driver.findElement(By.xpath("//div[@class='result']")).getText();
	    Assert.assertEquals("Result for : "+arg1, msg_result1);
	    Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Cover start:')]")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Cover end:')]")).isDisplayed());
	    driver.close();
	}
	
	@Then("^no records should be displayed$")
	public void no_records_should_be_displayed() throws Throwable {
	    String msg_result2 = driver.findElement(By.xpath("//div[@class='result']")).getText();
	    Assert.assertEquals("Sorry record not found", msg_result2);
		driver.close();
	}
	
	@Then("^error message should be displayed$")
	public void error_message_should_be_displayed() throws Throwable {
		String msg_result3 = driver.findElement(By.xpath("//div[@class='error-required']")).getText();
		Assert.assertEquals("Please enter a valid car registration", msg_result3);
		driver.close();
	}

}
