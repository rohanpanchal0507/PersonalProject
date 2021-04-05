package GamerzArena.Alpha_North;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Duplicate_Contest_Frontend_Enter {

	private static long DELAY_SECONDS = 2000;
	private static String Leaderboard_Tournament_ID = "10me";
	private static String AdminUrl = "https://testingconsole.gamerzarena.com/login";
	private static String TwitchUrl = "https://twitch.tv";

	public static void main(String[] args) throws Throwable {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(AdminUrl);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Enter Email
		driver.findElement(By.id("email")).sendKeys("mytest.qatesting+01@gmail.com");

		// Enter Password
		driver.findElement(By.id("password")).sendKeys("Gamer@23");

		Thread.sleep(850);

		// Click on Login button
		driver.findElement(By.cssSelector("button[class^='btn btn-primary btn-md btn-block waves-effect text-center m-b-20']")).click();

		// Click on Gameplay dropdown
		driver.findElement(By.linkText("Gameplay")).click();

		Thread.sleep(500);

		// Click on Contests button
		driver.findElement(By.linkText("Contests")).click();

		// Click on Searchbar
		driver.findElement(By.name("search")).sendKeys(Leaderboard_Tournament_ID + Keys.ENTER);

		// Click on Tournament
		driver.findElement(By.className("rowlink")).click();

		// Duplicate Tournament
		driver.findElement(By.cssSelector("button[class^='btn btn-secondary pull-right mr-2 duplicateContest']")).click();

		// Click on OK button
		driver.findElement(By.cssSelector("button[class^='swal-button swal-button--confirm swal-button--danger']")).click();

		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.close();
		driver.switchTo().window(tabs1.get(1));

		// Click on View Contest in Frontend
		driver.findElement(By.cssSelector("[title=\"View Contest In Frontend\"]")).click();

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
		driver.close();
		driver.switchTo().window(tabs2.get(1));

		// Click on Accept cookies permission button
		// Sikuli
		// Screen search = new Screen();
		driver.findElement(By.xpath("//*[contains(text(),'Accept')]")).click();
		// search.click("C:\\Users\\Admin\\eclipse-workspace\\GamerzArena_Admin_Frontend\\Upload_Files\\Accept_Btn.png");

		Thread.sleep(DELAY_SECONDS);

		// Click on Sign In button
		driver.findElement(By.cssSelector("button[class^='button-shadow btn btn-primary']")).click();

		// Enter Username
		driver.findElement(By.cssSelector("input[placeholder^='User name or Email Address']")).sendKeys("alpha_01");

		// Enter Password
		driver.findElement(By.name("password")).sendKeys("Gamer@23");

		// Click on SignIn
		driver.findElement(By.cssSelector("button[type^='submit']")).click();

		Thread.sleep(2000);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver1 = new ChromeDriver(options);
		driver1.manage().window().maximize();

		driver1.get(TwitchUrl);

		Thread.sleep(DELAY_SECONDS);

		// Enter First live stream of RECOMMENDED CHANNELS
		driver1.findElement(By.cssSelector("a[data-a-id^='recommended-channel-0']")).click();

		// Get URL & Copy
		String strUrl = driver1.getCurrentUrl();
		System.out.println("Current Url is:" + strUrl);

		driver1.close();

		// Click on Enter button of Toruanemnt
		driver.findElement(By.cssSelector("a[class^='EnterTournamentButton_cp__23WEo']")).click();

		// Scroll till Stream URL
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.name("stream_url"));
		je.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println(element.getText());

		// Enter Name in Game
		driver.findElement(By.name("gamer_name")).sendKeys("ABC");

		// Enter Live stream URL
		WebElement body = driver.findElement(By.name("stream_url"));
		body.sendKeys(Keys.chord(Keys.CONTROL, "a"));

		driver.findElement(By.name("stream_url")).sendKeys(strUrl);

		Select platform = new Select(driver.findElement(By.cssSelector("select[name^='platform_id']")));
		platform.selectByIndex(1);

		// Click on Enter button of Toruanemnt join page
		driver.findElement(By.cssSelector("div[class^='JoinIndividual_buttonWrapper__20AO1']")).click();

		Thread.sleep(20000);

		// Click on Finish button
		driver.findElement(By.cssSelector("button[value^='finish']")).click();

		// Close Browser
		driver.quit();

	}

}
