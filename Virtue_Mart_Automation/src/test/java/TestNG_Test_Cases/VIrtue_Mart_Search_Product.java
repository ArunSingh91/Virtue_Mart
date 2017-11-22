package TestNG_Test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import Page_Objects.Product_Information;
import parent.Base;

public class VIrtue_Mart_Search_Product extends Base
{	
	@Test(priority = 1, enabled = true)
	public void New_Product_Information()
	{
		explicitWait(driver, 20, By.cssSelector(".admin-content>form>div>div:nth-child(2)>fieldset:nth-child(1)>legend"));
		Product_Information product_info = PageFactory.initElements(driver, Product_Information.class);
		product_info.FillProductInformation();		
	}
}
