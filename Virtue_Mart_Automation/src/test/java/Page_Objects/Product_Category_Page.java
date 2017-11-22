package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
	
	public void add_New_Product_Category_Details()
	{
		product_categories.click();
		explicitWait(driver, 20, By.cssSelector("#toolbar>div:nth-child(2)>button"));
		new_button_For_Product_Category.click();
		explicitWait(driver, 20, By.cssSelector("#category_name"));
		new_Category_Name.sendKeys("Dummy_Category");
	}
}
