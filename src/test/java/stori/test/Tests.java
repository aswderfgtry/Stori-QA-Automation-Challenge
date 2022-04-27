package stori.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tests {
	
	WebDriver driver;
	JavascriptExecutor js;
	String mainWindow;
	String second;	
	
	@BeforeTest
	public void setUpTest(ITestContext testContext) {
		String browser = System.getProperty("browser");
		if(browser!=null&&browser.contains("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
	    }else if(browser!=null&&browser.contains("firefox")){
	    	WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	    }else if(browser!=null&&browser.contains("opera")){
	    	WebDriverManager.operadriver().setup();
	        driver = new OperaDriver();
	    }else {
	    	WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
	    }
		js=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
	}
	
		@Test(priority=0)
		public void testOne(ITestContext testContext) {
			WebElement inputElement =driver.findElement(By.id("autocomplete"));
			inputElement.sendKeys("me");
			WebElement option =driver.findElement(By.xpath("//li[6]/div"));
			option.click();
		}
		
		@Test(priority=2)
		public void testTwo(ITestContext testContext) {
			Select select = new Select(driver.findElement(By.id("dropdown-class-example")));
			select.selectByVisibleText("Option2");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			select.selectByVisibleText("Option3");
		}
		
		@Test(priority=3)
		public void testTree(ITestContext testContext) {
			WebElement button = driver.findElement(By.id("openwindow"));
			mainWindow = driver.getWindowHandle();
			button.click();
			Set<String> handles=driver.getWindowHandles();
			handles.remove(mainWindow);
			String second = (String) handles.toArray()[0];
			driver.switchTo().window(second);
			WebElement garantee =  driver.findElement(By.xpath("//div[5]/div/div[2]/div/div[2]/h3"));
			js.executeScript("arguments[0].scrollIntoView();",garantee );
			Assert.assertNotNull(garantee);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
		
		@Test(priority=4)
		public void testFour(ITestContext testContext)  {
			WebElement button = driver.findElement(By.id("opentab"));
			button.click();
			Set<String> handles=driver.getWindowHandles();
			handles.remove(mainWindow);
			second = (String) handles.toArray()[0];
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {}
			driver.switchTo().window(second);
			WebElement link = driver.findElement(By.xpath("//section[2]/div[2]/a"));
			js.executeScript("arguments[0].scrollIntoView();",link );
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(".//testfour.png"));
			} catch (IOException e) {}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			driver.switchTo().window(mainWindow);
			driver.switchTo().activeElement();
		}
		
		@Test(priority=5)
		public void testFive(ITestContext testContext)  {
			WebElement textbox = driver.findElement(By.id("name"));
			textbox.sendKeys("Stori Card");
			WebElement alert = driver.findElement(By.id("alertbtn"));
			WebElement confirm = driver.findElement(By.id("confirmbtn"));
			alert.click();
			Reporter.log(driver.switchTo().alert().getText());
			System.out.println(driver.switchTo().alert().getText());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			driver.switchTo().alert().accept();
			textbox = driver.findElement(By.id("name"));
			textbox.sendKeys("Stori Card");
			confirm.click();
			Reporter.log(driver.switchTo().alert().getText());
			System.out.println(driver.switchTo().alert().getText());
			String text =driver.switchTo().alert().getText();
			Assert.assertEquals(text, "Hello Stori Card, Are you sure you want to confirm?");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			driver.switchTo().alert().accept();
		}
		
		@Test(priority=6)
		public void testSix(ITestContext testContext)  {
			WebElement table = driver.findElement(By.id("product"));
			List<WebElement> elements =table.findElements(By.xpath("//td[contains(.,'25')]"));
			for (WebElement element:elements) {  
				WebElement temporal =driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(element));
				Reporter.log(temporal.getText());
				System.out.println(temporal.getText());
			}
		}
		
		@Test(priority=7)
		public void testSeven(ITestContext testContext)  {
			WebElement table = driver.findElement(By.className("tableFixHead"));
			List<WebElement> elements =table.findElements(By.xpath("//td[contains(.,'Engineer')]"));
			for (WebElement element:elements) {  
				WebElement temporal =driver.findElement(RelativeLocator.with(By.tagName("td")).toLeftOf(element));
				Reporter.log(temporal.getText());
				System.out.println(temporal.getText());
			}
		}
		
		@Test(priority=8)
		public void testEight(ITestContext testContext)  {
			WebElement iFrame = driver.findElement(By.id("courses-iframe"));
			js.executeScript("arguments[0].scrollIntoView();", iFrame);
			driver.switchTo().frame(iFrame);
			WebElement text = driver.findElement(By.xpath("//section[4]/div/div/div/div[2]/ul/li[2]"));
			js.executeScript("arguments[0].scrollIntoView();", text);
			int xOffset = text.getRect().width;
			int yOffset = text.getRect().height;
			Actions actionProvider = new Actions(driver);
			actionProvider.moveToElement(text,-xOffset/2,1-yOffset/2).perform();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			actionProvider.clickAndHold().moveByOffset(xOffset,yOffset).release().perform();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
	
	@AfterTest
	public void tearDown(ITestContext testContext) {
		driver.quit();
	}

}
