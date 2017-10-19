package core;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Chrome {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		String driverPath = "";

		String url = "http://facebook.com/";
		String email_address = "gnvl61@gmail.com";
		String password = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/chromedriver";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/chromedriver.exe";
		else
			throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		option.addArguments("--disable-notifications");
		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			option.addArguments("-start-fullscreen");
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			option.addArguments("--start-maximized");
		else
			throw new IllegalArgumentException("Unknown OS");
		driver = new ChromeDriver(option);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get(url);

		String title = driver.getTitle();
		String copyright = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")))
				.getText();
		
		System.out.println("Size of Copytight: " + (Dimension) driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getSize());
		System.out.println("Location of Copytight: " + (Point) driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getLocation()+"\n");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_address);

		System.out.println("Size of Email: " + (Dimension) driver.findElement(By.id("email")).getSize());
		System.out.println("Location of Email: " + (Point) driver.findElement(By.id("email")).getLocation()+"\n");
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).clear();
		
		System.out.println("Size of Password field: " + (Dimension) driver.findElement(By.id("pass")).getSize());
		System.out.println("Location of Password field: " + (Point) driver.findElement(By.id("pass")).getLocation()+"\n");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);

		System.out.println("Size of \"Login\" button: " + (Dimension) driver.findElement(By.id("u_0_2")).getSize());
		System.out.println("Location of \"Login\" button: " + (Point) driver.findElement(By.id("u_0_2")).getLocation()+"\n");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("u_0_2"))).click();
		
		System.out.println("Size of \"Timeline\" button: " + (Dimension) driver.findElement(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a")).getSize());
		System.out.println("Location of \"Timeline\" button: " + (Point) driver.findElement(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a")).getLocation()+"\n");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a")))
				.click();
		
		System.out.println("Size of \"Friends\" button: " + (Dimension) driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getSize());
		System.out.println("Location of \"Friends\" button: " + (Point) driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getLocation()+"\n");
		

		String friends = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText();

		System.out.println("Size of Account settings: " + (Dimension) driver.findElement(By.id("userNavigationLabel")).getSize());
		System.out.println("Location of Account settings: " + (Point) driver.findElement(By.id("userNavigationLabel")).getLocation()+"\n");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel"))).click();
		
		System.out.println("Size of \"Log out\": " + (Dimension) driver.findElement(By.xpath("//ul/li[18]/a/span/span")).getSize());
		System.out.println("Location of \"Log out\": " + (Point) driver.findElement(By.xpath("//ul/li[18]/a/span/span")).getLocation()+"\n");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul/li[14]/a/span/span"))).click();

		driver.quit();

		System.out.println("Browser is: Chrome");
		System.out.println("Title of the page: " + title);
		System.out.println("Copyright: " + copyright);
		System.out.println("You have " + friends + " friends");
	}
}
