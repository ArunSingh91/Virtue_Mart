package Page_Objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.Helper;
import parent.Base;

public class Product_Category_Page extends Base
{
	WebDriver driver;
	public Product_Category_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.CSS, using=".admin-ui-menu>div:nth-child(2)>ul>li:nth-child(1)>a")
	private WebElement product_categories;
	
	@FindBy(how=How.CSS, using="#toolbar>div:nth-child(2)>button")
	private WebElement new_button_For_Product_Category;
	
	@FindBy(how=How.CSS, using="#category_name")
	private WebElement new_Category_Name;
	
	@FindBy(how=How.CSS, using="#tabs>li:nth-child(3)>span")
	private WebElement imagesTab;
	
	@FindBy(how=How.CSS, using="#upload")
	public WebElement uploadButton;
	
	@FindBy(how=How.CSS, using="input[id='media[media_action]upload']")
	private WebElement uploadRadioButton;
	
	public void add_New_Product_Category_Details(Logger logger)
	{
		product_categories.click();
		explicitWait(driver, 20, By.cssSelector("#toolbar>div:nth-child(2)>button"));
		new_button_For_Product_Category.click();
		explicitWait(driver, 20, By.cssSelector("#category_name"));
		new_Category_Name.sendKeys("Dummy_Category");
		imagesTab.click();
		explicitWait(driver, 20, By.cssSelector("#admin-ui-tabs>div:nth-child(4)>div.col50>div>fieldset:nth-child(3)>legend"));
		Actions action = new Actions(driver);
		action.moveToElement(uploadButton).perform();
		Helper.uploadFile(uploadButton, "\\Upload_Data\\Test_Image.jpg");
		logger.info("Screenshot Captured");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(uploadRadioButton.isSelected())
		{
			//System.out.println("Radio BUtton is selected");
			logger.info("Radio BUtton is selected");
			Helper.takeScreenshot(driver, "File_Upload");
			logger.info("File Uploaded successfully!!");
		}
	}
}
