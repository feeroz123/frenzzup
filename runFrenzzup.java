import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class runFrenzzup {

	public static void main(String[] args) throws InterruptedException {

		String userId = "61312590";
		String userPwd = "nisha@123";
		int noOfClicks = 0;

		String baseURL = "http://www.frenzzup.com/";
		String linkButtonPath = "//span[contains(@onclick,'updateTask')]";

		System.setProperty("webdriver.chrome.driver", "D:\\Official\\SELENIUM\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Load the website
		driver.get(baseURL);
		System.out.println("Loaded website");

		Thread.sleep(3000);

		// Click on Agree Conditions checkbox
		clickWhenReady(driver, By.id("agreeconditions"), 5);

		// Click on Go to Website button
		clickWhenReady(driver, By.id("linkweb"), 5);

		// Click on Sign in on login page
		clickWhenReady(driver, By.linkText("Login"), 5);

		// Enter User credentials and login
		driver.findElement(By.xpath("//input[@id='txtEmailID']")).sendKeys(userId);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(userPwd);
		driver.findElement(By.id("CndSignIn")).click();
		System.out.println("Entered User credentials");

		Thread.sleep(5000);

		// Go to links page
		// clickWhenReady(driver,By.xpath("//a[@href='User/EarnePoints.aspx']"),
		// 10);
		
		driver.get("http://frenzzup.com/User/EarnePoints.aspx");
		System.out.println("Entering links page");

		do {

			// ******** Performing the Click *********
			clickWhenReady(driver, By.xpath(linkButtonPath), 10);
			System.out.println("Click performed: " + noOfClicks);
			noOfClicks++;

			// Random wait of 12+ secs
			Thread.sleep(randomWaitTime(driver));

		} while (driver.findElement(By.xpath(linkButtonPath)).isEnabled());

		System.out.println("Task Completed. Total links clicked = " + noOfClicks);

	}

	public static void clickWhenReady(WebDriver driver, By locater, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locater));
		element.click();
	}

	public static int randomWaitTime(WebDriver driver) {
		// Setting up random wait period
		int randomNumber = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		int waitPeriod = randomNumber * 1000;
		int totalWaitPeriod = 12000 + waitPeriod;
		return totalWaitPeriod;
	}

}
