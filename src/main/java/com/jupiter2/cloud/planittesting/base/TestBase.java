package com.jupiter2.cloud.planittesting.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 * 
 * @author Prapeja
 *
 */
public class TestBase implements IBrowserSetup{
	
	//Utility program to initialize browser
	public static WebDriver driver;
	

	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * 
	 * @return this method returns webdriver instance
	 */
	public WebDriver initializeDriver() throws IOException {

		Properties pro=new Properties(); //load the DataFile to the Properties class
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\DataFile");
		pro.load(fis);
		String browserName=pro.getProperty("browser"); //browser property from DataFile
		//String browserName=System.getProperty("browser"); //browser came from maven properties (run using "mvn test -Dbrowser chrome")	
		if(browserName.contains("chrome")) {
			System.setProperty(CHROME_KEY,CHROME_PATH);
			ChromeOptions options=new ChromeOptions();//object used to get multiple options for chrome
			if(browserName.contains("headless")) {
			options.addArguments("headless");	
		}
		driver=new ChromeDriver(options);
		}else if(browserName.equals("firefox")) {
		
		}else if(browserName.equals("IE")) {
				
		}			
		return driver;
	}
	
	/**
	 * This method is used to capture screenshot 
	 * @return 
	 * @return 
	 * 
	 * @return this method returns destinationFile instance where the screenshot will be saved
	 */
	
	public static String getScreenshotPath(String testcaseName) throws IOException{
		TakesScreenshot ts=((TakesScreenshot)driver);
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;  
	}
		
	public void enterURL(String URL) {
		driver.get(APPLICATION_URL);
	}
		
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
		
	public void waitForPageLoad() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * This method will initialize driver, maximize browser window, wait till page loads and provide URL
	 * by executing before every method 
	 */
	
	@BeforeMethod
	public void startBrowser() throws IOException {
		initializeDriver();
		maximizeBrowser();
		waitForPageLoad();
		enterURL(APPLICATION_URL);	
	}
	
	/**
	 * This method will close the entire browser session at the end by executing after every method 
	 * @throws Exception 
	 */
	@AfterMethod
	public void quitBrowser() throws Exception {
		try {
			driver.quit();
		} catch (Exception e) {
			throw new Exception("some exception occurred while quitting the browser");
		}
	}
}
