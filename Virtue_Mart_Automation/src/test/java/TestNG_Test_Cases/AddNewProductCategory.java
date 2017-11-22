package TestNG_Test_Cases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Page_Objects.Product_Category_Page;
import parent.Base;

public class AddNewProductCategory extends Base
{
	@Test
	public void add_New_Product_Category()
	{
		explicitWait(driver, 20, By.cssSelector(".admin-ui-menu>div:nth-child(2)>ul>li:nth-child(1)>a"));
		Product_Category_Page product_Category = new Product_Category_Page(driver);
		product_Category.add_New_Product_Category_Details();
	}
}
