package parent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Page_Objects.Dashboard_Page;
import factory.BrowserFactory;

public class Base {
	public WebDriver driver;
	public Logger logger;
	@Parameters({ "browsername" })
	@BeforeTest
	public void startUP(String browser) {
		logger = Logger.getLogger(Logger.class.getName());
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Data_Provider\\Config_Data_Provider\\Log4j.properties");
		//System.out.println("Inside startUP method");
		logger.info("Inside startUP method");
		this.driver = BrowserFactory.driverInitialization(browser);
		BrowserFactory.openApplication();
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement notification_popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mod-login-username")));
		notification_popup.clear();
		notification_popup.sendKeys("demo");
    	driver.findElement(By.cssSelector("#mod-login-password")).clear();
        driver.findElement(By.cssSelector("#mod-login-password")).sendKeys("demo");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-large.login-button")).click();
        explicitWait(driver, 20, By.cssSelector("#vm-menu>li>a"));
		Dashboard_Page dashboard = new Dashboard_Page(driver);
		dashboard.addNewProduct();
	}

	@AfterTest
	public void selectAndAddProductIntoCart() {
		System.out.println("Inside selectAndAddProductIntoCart method");
		driver.quit();
	}

	public void explicitWait(WebDriver driver, int timeouts, By locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, timeouts);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
