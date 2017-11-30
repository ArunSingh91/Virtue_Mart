package Page_Objects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import parent.Base;

public class Dashboard_Page extends Base {

	WebDriver driver;

	public Dashboard_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = "#vm-menu>li>ul>li>a")
	private List<WebElement> Virtue_Mart_Categories;

	@FindBy(how = How.CSS, using = "#vm-menu>li>a")
	private WebElement VirtueMart;

	@FindBy(how = How.CSS, using = "#toolbar>div:nth-child(7)>button")
	private WebElement NewButton;

	public Product_Information addNewProduct() {
		VirtueMart.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//System.out.println(e.getMessage());
			logger.info(e.getMessage());
		}
		for (WebElement Category : Virtue_Mart_Categories) {
			if (Category.getText().equals("Products")) {
				Category.click();
				break;
			}
		}
		explicitWait(driver, 20, By.cssSelector("#toolbar>div:nth-child(7)>button"));
		NewButton.click();
		return new Product_Information(driver);
		
	}

}
