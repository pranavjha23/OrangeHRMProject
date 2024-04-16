package Tests;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class OrangeHRMTest {
	 WebDriver driver;
	 ExtentReports extent;
	 ExtentHtmlReporter htmlReporter;
	 

		@BeforeSuite
		public void Setup() {
			
			htmlReporter = new ExtentHtmlReporter("extent.html");
		
			extent=new ExtentReports();
			extent.attachReporter(htmlReporter);
	}
			
	 @BeforeTest 
	    public void SetupTest() {
	    	System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
	    	driver = new ChromeDriver();
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }
	 
	   @Test(priority = 1)
	    public void LoginTest() {
	    	
		    ExtentTest Test1 = extent.createTest("Login Page");
		    Test1.log(Status.INFO, "Starting Test");
		    
	    	driver.get("https://opensource-demo.orangehrmlive.com/");
	    	Reporter.log("pass");
	    	Test1.log(Status.PASS, "Successfully Entet UserName");
	    	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	    	Test1.log(Status.PASS, "Successfully Entet Password");
	    	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	    	Test1.log(Status.PASS, "Successfully Click on login button");
	    	driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	        Test1.log(Status.INFO, "Logged-in successfull");
	    	
	   }
	   @Test(priority = 2)
	    public void AdminTest() throws InterruptedException {
		  
		  ExtentTest Test2 = extent.createTest("ADMIN menu");
          Thread.sleep(2000);
          Test2.log(Status.INFO, "Navigated to admin page");
          Test2.log(Status.PASS, "Successfully Click on admin page");
	      WebElement ele = driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span"));
	      ele.click();
	      Thread.sleep(2000);
	      Test2.log(Status.PASS, "Successfully verified admin text");
	      WebElement text = driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span"));
	      String ActualTitle=text.getText();
	      System.out.println(ActualTitle);
	      String ExpectedTitle = "Admin";
	      Assert.assertEquals(ActualTitle, ExpectedTitle);
	      System.out.println("Assert passed");
	      
	    }
	   @Test(priority = 3)
	    public void Myinfo() throws InterruptedException, IOException {
		   
		   ExtentTest Test3 = extent.createTest("MY info menu");
		  Thread.sleep(2000);
		  Test3.log(Status.INFO, "Navigated to Myinfo page");
		  driver.findElement(By.xpath("//span[normalize-space()='My Info']")).click();
		  Test3.log(Status.PASS, "Successfully Entet UserName");
		  driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("pranav");
		  Test3.log(Status.PASS, "Successfully Entet empoyee id");
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("E2358");
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  Thread.sleep(2000);
	
		  Test3.log(Status.PASS, "Successfully scrolldown");
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		  
		  Thread.sleep(2000);
		  Test3.log(Status.PASS, "Successfully scrollup");
		  js.executeScript("window.scrollBy(0,-350)");
		  Test3.log(Status.PASS, "Successfully clicked save button");
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button")).click();
		  js.executeScript("window.scrollBy(0,-450)");
		  Test3.log(Status.PASS, "Successfully taken the screenshot of Myinfo page");
		  File f= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  Files.copy(f, new File("E:\\Selenium SS\\Myinfodetails.png"));
		 
}
	   @Test(priority = 4)
	    public void PIM() throws InterruptedException, IOException {
		   
		  Thread.sleep(2000);
		  ExtentTest Test4 = extent.createTest("PIM Page");
		  driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")).click();
		  
		  driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
		  Test4.log(Status.PASS, "Successfully added employee first name");
		  driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("pranav");
		  Test4.log(Status.PASS, "Successfully added employee Last name");
		  driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Kumar");
		  Test4.log(Status.PASS, "Successfully added employee ID");
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")).sendKeys("E2358");
		  //click on save button
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		  Test4.log(Status.INFO, "Successfully added employee");
		  File f= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  Files.copy(f, new File("E:\\Selenium SS\\Myinfodetails.png"));
}
	   
	   @Test(priority = 5)
	   public void Directory() throws InterruptedException, IOException {
	      
		  ExtentTest Test5 = extent.createTest("Directory menu");
		  Thread.sleep(2000);
		  
		  Test5.log(Status.INFO, "Navigated to Directory page");
	      driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a/span")).click();
	      Test5.log(Status.PASS, "Successfully selected job title");
	      Select objSelect = new Select( driver.findElement(By.xpath(" /html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i")));
	      objSelect.selectByVisibleText("Chief Financial Officer");
	      Thread.sleep(2000);
	      Test5.log(Status.PASS, "Successfully clicked on search button");
	      driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	      
}
	   @Test(priority = 6)
	   public void Searcharea() throws InterruptedException, IOException {
		   ExtentTest Test6 = extent.createTest("Search area");
		   
		   Test6.log(Status.INFO, "Navigated to Search area");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Buzz");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")).click();
		   Test6.log(Status.PASS, "Successfully search for buzz menu and page is displayed");
		   WebElement text = driver.findElement(By.xpath("//a[normalize-space()='Buzz Newsfeed']"));
		   String ActualTitle=text.getText();
		   System.out.println(ActualTitle);
		   String ExpectedTitle = "Buzz Newsfeed";
		   Assert.assertEquals(ActualTitle, ExpectedTitle);
		   System.out.println("Assert passed");
	   }
	   

		@AfterClass
		public void TeardownTest() {
			ExtentTest Test7 = extent.createTest("Close application");
			Test7.log(Status.PASS, "Successfully close the application");
			
			driver.close();
		}
		
		@AfterSuite
		public void Teardown() {
			extent.flush();
	}
}