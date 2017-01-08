package com.aeps.selenium.framework;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aeps.pages.DashboardPage;
import com.aeps.pages.HomePage;
import com.aeps.pages.LogOutPage;
import com.aeps.pages.LoginPage;

public abstract class BaseTest {

	//private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final Logger logger = Logger.getLogger(BaseTest.class);
	
	
	private static final String BREAK_LINE = "</br>";
	
	//pages object
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected DashboardPage dashboardPage;
	protected LogOutPage logOutPage;
		
	enum DriverType {
		Firefox, IE, Chrome
	}

	// Define objects
	private WebDriver driver;

	// Define variables
	protected String applicationUrl;
	
	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(@Optional String browserType) throws Exception {
		logger.setLevel(Level.INFO);
		logger.setLevel(Level.WARN);
		
		if (browserType == null) {
			browserType = Configuration.readConfig("Browser");
		}

		this.applicationUrl = Configuration.readConfig("URL");

		if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
			driver = new FirefoxDriver();
		} else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.ie.driver",
					Utilities.getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
		}

		else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.chrome.driver",
					Utilities.getPath() + "/src//test//resources//webdriver/chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			throw new Exception("Please pass valiedbrowser type value");
		}

		// Maximize window
		driver.manage().window().maximize();

		// Delete cookies
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// open application URL
		getWebDriver().navigate().to(applicationUrl);		
				
	}

	@AfterClass
	public void afterMainMethod() {
		driver.quit();
	}

	
	public WebDriver getWebDriver() {
		return driver;
	}

	// Handle child windows
	public String switchChildWindow() {
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	

	// capturing screenshot
	public void captureScreenshot(String fileName) {
		try {
			String screenshotName = Utilities.getFileName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = Utilities.getPath();
			String screen = "file://" + path + "/screenshots/" + screenshotName + ".jpg";
			Reporter.log("<a href= '" + screen + "'target='_blank' >" + screenshotName + "</a>");
		} catch (Exception e) {

		}
	}

	public void reportLog(String message) {
		message = BREAK_LINE + message;
		logger.info("Message: " + message);		
		Reporter.log(message);
	}

	public void closeBrowser(){
		driver.close();
	}
	

}
