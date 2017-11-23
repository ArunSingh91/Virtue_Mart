package common;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper 
{
	public static void javaScriptClick(WebDriver driver, WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void takeScreenshot(WebDriver driver, String screenshotName)
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "\\Screenshots\\"+screenshotName+System.currentTimeMillis()+".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
