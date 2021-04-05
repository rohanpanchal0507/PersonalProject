package GamerzArena.Alpha_North;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frontend_Enter_Contest {

	private static long DELAY_SECONDS = 2000;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://testing.gamerzarena.com/tournaments/10me/overview");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//*[text()='Accept']")).click();
		
		//Click on Sign in button
		driver.findElement(By.cssSelector("button[class^='button-shadow btn btn-primary']")).click();
		
		//Enter Username
		driver.findElement(By.cssSelector("input[placeholder^='User name or Email Address']")).sendKeys("qatesting01");
		
		//Enter Password
		driver.findElement(By.name("password")).sendKeys("Gamer@23");
		
		//Click on SignIn
		driver.findElement(By.cssSelector("button[type^='submit']")).click();
		
		Thread.sleep(2000);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver1 = new ChromeDriver(options);
		driver1.manage().window().maximize();
		
		driver1.get("https://twitch.tv");

		Thread.sleep(DELAY_SECONDS);

		// Enter First live stream of RECOMMENDED CHANNELS
		driver1.findElement(By.cssSelector("a[data-a-id^='recommended-channel-0']")).click();

		// Get URL & Copy
		String strUrl = driver1.getCurrentUrl();
		System.out.println("Current Url is:" + strUrl);
		
	    driver1.close();
		
		//Click on Enter button of Toruanemnt
		driver.findElement(By.cssSelector("a[class^='EnterTournamentButton_cp__23WEo']")).click();
		
		// Scroll till Stream URL
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.name("stream_url"));
		je.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println(element.getText());
		
		//Enter Name in Game
		driver.findElement(By.name("gamer_name")).sendKeys("ABC");
		
		//Enter Live stream URL
		WebElement body = driver.findElement(By.name("stream_url"));
		body.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		
		driver.findElement(By.name("stream_url")).sendKeys(strUrl);
		
		Select platform = new Select(driver.findElement(By.cssSelector("select[name^='platform_id']")));
		platform.selectByIndex(1);
		
		// Click on Enter button of Toruanemnt join page
		driver.findElement(By.cssSelector("div[class^='JoinIndividual_buttonWrapper__20AO1']")).click();
		
		Thread.sleep(30000);
		
		//Click on Finish button
		driver.findElement(By.cssSelector("button[value^='finish']")).click();
		
		
	}

}
