package Page_Objects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.Helper;
import parent.Base;

public class Product_Information extends Base {
	WebDriver driver;
	Logger logger;
	String features[] = {"published", "product_special"};

	public Product_Information(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "#product_name")
	private WebElement Product_Name;

	@FindBy(how = How.CSS, using = "#product_sku")
	private WebElement Product_SKU;

	@FindBy(how = How.CSS, using = "#virtuemart_manufacturer_id_chzn")
	private WebElement Manufacturer_Locator;

	@FindBy(how = How.CSS, using = "#virtuemart_manufacturer_id_chzn>div>ul>li")
	private List<WebElement> Manufacturer_List;

	@FindBy(how = How.CSS, using = "#categories_chzn")
	private WebElement categories_Locator;

	@FindBy(how = How.CSS, using = "#categories_chzn>div>ul>li")
	private List<WebElement> product_Categories_List;
	
	@FindBy(how = How.CSS, using = "tbody>tr:nth-child(1)>td:nth-child(3)>label>input[type='checkbox']")
	private List<WebElement> label_list;
	
	@FindBy(how = How.CSS, using = "input[id='mprices.product_price_publish_up._text']")
	private WebElement SetDate;
	
	@FindBy(how = How.CSS, using = ".ui-datepicker-calendar>tbody>tr>td>a") 
	private List<WebElement> Calendar_All_Dates;
	
	@FindBy(how = How.CSS, using= ".btn.btn-small.button-apply.btn-success")
	private WebElement save_Button;
	
	@FindBy(how = How.CSS, using= ".admin-ui-menu>div:nth-child(2)>ul>li:nth-child(2)>a")
	private WebElement products;

	private void selectManufacturer(String Manufacturer_Name) {
		logger.info("inside selectManufacturer method");
		Manufacturer_Locator.click();
		try {
			Thread.sleep(3000);
			System.out.println("List is " + Manufacturer_List.size());
			for (WebElement Manufacturer : Manufacturer_List) {
				System.out.println("Manufacturer is " + Manufacturer.getText());
				if (Manufacturer.getText().equals(Manufacturer_Name)) {
					Manufacturer.click();
					Thread.sleep(3000);
					break;
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void select_Product_Categories(String Category_Name) {
		try {
			categories_Locator.click();
			explicitWait(driver, 10, By.cssSelector("#categories_chzn>div>ul"));
			for (WebElement product_Category : product_Categories_List) {
				System.out.println("Product category is " + product_Category.getText());
				if (product_Category.getText().equals(Category_Name)) {
					product_Category.click();
					Thread.sleep(3000);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void publish_And_OnFeatured(String[] features2)
	{
		for (int i = 0; i < features2.length; i++) 
		{
			for (WebElement feature : label_list)
			{
				System.out.println("feature is " + feature.getAttribute("name"));
				if (features2[i].equalsIgnoreCase(feature.getAttribute("name").trim())) 
				{
					System.out.println("Array value is "+ features2[i]);
					if (feature.isSelected()) 
					{
						System.out.println(feature.getAttribute("name") + " checkbox is already selected");
						break;
					}
					else
					{
						//feature.click();
						JavascriptExecutor executor = (JavascriptExecutor)driver;
						executor.executeScript("arguments[0].click();", feature);
						explicitWait(driver, 10, By.cssSelector("input[id='mprices.product_price_publish_up._text']"));
						System.out.println(feature.getAttribute("name")+ " Checkbox selected");
						break;
					}
				}
			}
		}
	}
	
	public void fillProductPricing() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", SetDate);
		SetDate.click();
		Thread.sleep(1000);
		for (WebElement date : Calendar_All_Dates) 
		{
			if(date.getText().equals("25"))
			{
				date.click();
				Thread.sleep(3000);
				break;
			}
		}
	}

	public void FillProductInformation(Logger logger) {
		try {
			//System.out.println("Inside FillProductInformation method");
			this.logger = logger;
			logger.info("Inside FillProductInformation method");
			Product_Name.sendKeys("Test_Product");
			Product_SKU.sendKeys("TP_101");
			selectManufacturer("Producer");
			select_Product_Categories("Product attributes");
			Thread.sleep(3000);
			publish_And_OnFeatured(features);
			Thread.sleep(3000);
			fillProductPricing();
			Helper.takeScreenshot(driver, "FillProductInformation");
			save_Button.click();
			explicitWait(driver, 20, By.cssSelector(".admin-ui-menu>div:nth-child(2)>ul>li:nth-child(2)>a"));
			products.click();
			explicitWait(driver, 20, By.cssSelector("tbody>tr:nth-child(1)>td:nth-child(2)>a"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
