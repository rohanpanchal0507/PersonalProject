package GamerzArena.Alpha_North;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Streaming_Error_Script {

    public static void main(String[] args) throws IOException, InterruptedException {

        Properties prop = new Properties();     //Read Property file data
        FileInputStream ip = new FileInputStream("/Users/drashti/Downloads/Automation/Alpha_North/src/main/java/GamerzArena/Alpha_North/config.properties");
        prop.load(ip);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().window().maximize();
        driver.get(prop.getProperty("FrontendUrl"));        //Open Frontend in Chrome

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        sleep(500);
        driver.findElement(By.xpath(prop.getProperty("OKAY"))).click();       //Click on Okay button
        driver.findElement(By.xpath(prop.getProperty("AcceptBtn"))).click();       //Click on Accept button

        driver.findElement(By.xpath(prop.getProperty("SignInBtn1"))).click();       //Click on Sign in button
        driver.findElement(By.name(prop.getProperty("UserNameField"))).sendKeys(prop.getProperty("username"));     //Enter Username
        driver.findElement(By.name(prop.getProperty("PasswordField"))).sendKeys(prop.getProperty("password"));       //Enter Password
        driver.findElement(By.cssSelector(prop.getProperty("LoginInBtn"))).click();       //Click on SignIn

        sleep(Long.parseLong(prop.getProperty("DELAY_SECONDS")));

        driver.findElement(By.cssSelector(prop.getProperty("FilterBtn"))).click();     //Click on filter button
        sleep(500);
        driver.findElement(By.id(prop.getProperty("Tracked-Filter_Option"))).click();       //Checked on Tracked option
        sleep(500);
        driver.findElement(By.name(prop.getProperty("Ongoing-Filter_Option"))).click();        //Checked on ongoing option
        sleep(500);
        driver.findElement(By.id(prop.getProperty("Leaderboard-Filter_Option"))).click();       //Checked on Leaderboard option

        driver.findElement(By.cssSelector(prop.getProperty("SearchBox"))).sendKeys(prop.getProperty("ContestName"));     //Search Contest name
        sleep(Long.parseLong(prop.getProperty("DELAY_SECONDS")));
        driver.findElement(By.cssSelector(prop.getProperty("ContestLink"))).click();     //Click on contest
        //driver.navigate().refresh();        //Refresh the page

        driver.findElement(By.cssSelector(prop.getProperty("EnterBtnOverview"))).click();      //Click on Enter button of Tournament from overview page

        WebElement r = driver.findElement(By.cssSelector(prop.getProperty("StreamURLTextBox1")));       //Remove pre-filled value form stream URL
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.SHIFT, Keys.ALT, Keys.ARROW_LEFT));
        r.sendKeys(Keys.chord(Keys.BACK_SPACE));

        sleep(1000);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");        //Open incognito
        WebDriver driver1 = new ChromeDriver(options);
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
        driver1.get(prop.getProperty("TwitchUrl"));       //Open Twitch
       // sleep(3500);

        driver1.findElement(By.cssSelector(prop.getProperty("FirstLiveStream"))).click();       // Enter First live stream of RECOMMENDED CHANNELS
        String strUrl = driver1.getCurrentUrl();        // Get URL & Copy
        System.out.println("Current Url is:" + strUrl);

        driver1.close();        //Close incognito mode

        JavascriptExecutor je = (JavascriptExecutor) driver;        // Scroll till Stream URL
        WebElement element = driver.findElement(By.name(prop.getProperty("StreamURLTextBox")));
        je.executeScript("arguments[0].scrollIntoView();", element);
        System.out.println(element.getText());

        driver.findElement(By.name(prop.getProperty("NameInGameTextBox"))).sendKeys(prop.getProperty("NameInGameEnterField"));      //Enter Name in Game in join page
        sleep(500);

        driver.findElement(By.name(prop.getProperty("StreamURLTextBox"))).sendKeys(strUrl);

        Select platform = new Select(driver.findElement(By.cssSelector(prop.getProperty("PlatformDropdown"))));        //Select the platform
        platform.selectByIndex(1);

        driver.findElement(By.xpath(prop.getProperty("EnterBtnJoinPage"))).click();        // Click on Enter button of Tournament join page

        sleep(3000);

        String heading = driver.findElement(By.cssSelector(prop.getProperty("S1"))).getText();      //Get message when stream us connected
        sleep(1500);
        System.out.println("Stream is connected & message is:" + heading);

        String Exp = "You have entered to this contest.";       //Set expected condition

        if (Exp.equalsIgnoreCase(heading))
        {
            ChromeOptions options2 = new ChromeOptions();
            WebDriver driver2 = new ChromeDriver(options2);
            driver2.manage().window().maximize();
            driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");

            driver2.get("https://www.zedge.net/ringtone/4bf1c380-fabd-4b3e-b462-82912ec8ed94");     //Play success tune
            sleep(500);
            WebElement toneS = driver2.findElement(By.id("Play-triangle"));
            toneS.click();
            sleep(5000);
            driver2.close();
            sleep(Long.parseLong(prop.getProperty("FinishStreamTiming")));       //10 seconds of delay
            driver.findElement(By.cssSelector(prop.getProperty("FinishBtnStreamConnectedPage"))).click();      //Click on Finish button from stream connect page
            driver.findElement(By.linkText(prop.getProperty("BackToContestBtnFeedbackPage"))).click();        //Click on Back to Contest button from feedback page

        }
        else
        {
            ChromeOptions options3 = new ChromeOptions();
            WebDriver driver3 = new ChromeDriver(options3);
            driver3.manage().window().maximize();
            driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
            driver3.get("https://www.zedge.net/ringtone/0cfbe017-d696-3db9-b087-b9401f0f1c26");     //Play error tune
            sleep(500);
            WebElement toneF = driver3.findElement(By.id("Play-triangle"));
            toneF.click();
            sleep(5000);
            driver3.close();
        }
        driver.close();
    }
}

