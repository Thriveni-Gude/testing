package demowebpack;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Addproduct {

	public static void main(String[] args)     {
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@qa='categoryDD']")).click();
	    WebDriverWait wait=new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Beverages' and @qa='catL1']")));
		driver.findElement(By.xpath("//a[text()='Beverages' and @qa='catL1']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='ng-binding' and text()='Tea'])[1]")));
		driver.findElement(By.xpath("(//span[@class='ng-binding' and text()='Tea'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Green Tea'])[1]")));
		driver.findElement(By.xpath("(//span[text()='Green Tea'])[1]")).click();
		driver.findElement(By.xpath("(//input[@value='Search by Brand' and @type='text' ])[1]")).sendKeys("tea");
		List<WebElement> checkbox=driver.findElements(By.xpath("//section[@style='min-height: 30px; max-height: 150px; overflow-y:scroll; margin-top:5px']//div[@class='col-xs-12 checkbox ng-scope']//span[@class='cr']//i[@class='cr-icon fa fa-check']"));
		int checkcount=checkbox.size();
		System.out.println(checkcount);
		checkbox.get(0).click();
		checkbox.get(checkcount-1).click();
		List<WebElement> products = driver.findElements(By.xpath("//div[@qa='price']//span[@class='discnt-price']//span[contains(@ng-bind,'selectedProduct')]"));
		double expected_price = Double.parseDouble(products.get(0).getText().toString());
		expected_price = expected_price*2;
		System.out.println(expected_price);
		int count=products.size();
		System.out.println(count);
		driver.findElement(By.xpath("(//input[@ng-model='vm.startQuantity'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@ng-model='vm.startQuantity'])[1]")).sendKeys("2");
		driver.findElement(By.xpath("(//button[@qa='add' and @class='btn btn-add col-xs-9'])[1]")).click();
		driver.findElement(By.id("city-drop-overlay")).click();
		WebElement mybasket=driver.findElement(By.id("totalNumberOfCartItems"));
		Actions act=new Actions(driver);
		act.moveToElement(mybasket).perform();
		act.click(mybasket).perform();
		WebElement twoproducts = driver.findElement(By.xpath("//span[@ng-bind='vm.cart.cart_total']"));
		double actual_price = Double.parseDouble(twoproducts.getText().toString());
		System.out.println(actual_price);
		Assert.assertEquals(actual_price,expected_price);
		driver.quit();
		
		
	}
}
		
        
		
		