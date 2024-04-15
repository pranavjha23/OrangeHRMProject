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
import org.testng.annotations.*;

import com.google.common.io.Files;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class OrangeHRMTest {
	 WebDriver driver;
	 
	 
	 @BeforeTest 
	    public void setup() {
	    	System.setProperty("webdriver.chrome.driver", "E:\\driver\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
	    	driver = new ChromeDriver();
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }
	 
	   @Test(priority = 1)
	    public void LoginTest() {
	    			
	    	driver.get("https://opensource-demo.orangehrmlive.com/");
	    	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	    	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	    	driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();	
	   }
	   @Test(priority = 2)
	    public void AdminTest() throws InterruptedException {
		 
          Thread.sleep(2000);
		  //click on admin
	      WebElement ele = driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span"));
	      ele.click();
	      Thread.sleep(2000);
	      WebElement text = driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span"));
	      String ActualTitle=text.getText();
	      System.out.println(ActualTitle);
	      String ExpectedTitle = "Admin";
	      Assert.assertEquals(ActualTitle, ExpectedTitle);
	      System.out.println("Assert passed");
	     
	    }
	   @Test(priority = 3)
	    public void Myinfo() throws InterruptedException, IOException {
		   
		  Thread.sleep(2000);
		  //click on the myinfo
		  driver.findElement(By.xpath("//span[normalize-space()='My Info']")).click();
		  //enter name
		  driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("pranav");
		  //enter employee id
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("E2358");
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  Thread.sleep(2000);
	
		  //scroll down
		  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		  
		  Thread.sleep(2000);
		  //scroll up
		  js.executeScript("window.scrollBy(0,-350)");
		  //click on save button
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button")).click();
		  js.executeScript("window.scrollBy(0,-450)");
		  
		  File f= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  Files.copy(f, new File("E:\\Selenium SS\\Myinfodetails.png"));
		 
}
	   @Test(priority = 4)
	    public void PIM() throws InterruptedException, IOException {
		   
		  Thread.sleep(2000);
		  //click on PIM GNB
		  driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")).click();
		  //Select add Employee 
		  driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
		  //Enter employee first name
		  driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("pranav");
		  //Enter employee Last name
		  driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Kumar");
		  //Enter employee id
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input")).sendKeys("E2358");
		  //click on save button
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
		  
		  File f= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  Files.copy(f, new File("E:\\Selenium SS\\Myinfodetails.png"));
}
	   
	   @Test(priority = 5)
	   public void Directory() throws InterruptedException, IOException {
	       
		  Thread.sleep(2000);
		  //click on directory menu
	      driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[9]/a/span")).click();
	      //select job title
	      Select objSelect = new Select( driver.findElement(By.xpath(" /html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i")));
	      objSelect.selectByVisibleText("Chief Financial Officer");
	      Thread.sleep(2000);
	      //click on search button
	      driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	      
}
	   @Test(priority = 6)
	   public void Searcharea() throws InterruptedException, IOException {
		   
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Buzz");
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")).click();
		   WebElement text = driver.findElement(By.xpath("//a[normalize-space()='Buzz Newsfeed']"));
		   String ActualTitle=text.getText();
		   System.out.println(ActualTitle);
		   String ExpectedTitle = "Buzz Newsfeed";
		   Assert.assertEquals(ActualTitle, ExpectedTitle);
		   System.out.println("Assert passed");
	   }
	   

		@AfterClass
		public void TeardownTest() {
			
			
			driver.close();
		}
		
		@AfterSuite
		public void Teardown() {
		//	extent.flush();
	}
}