package GamerzArena.Alpha_North;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.TextUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frontend_Login_From_Excel {

    WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;

    @Test
    public void Login() throws Throwable {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://testing.gamerzarena.com/tournaments");

        long DELAY_SECONDS = 850;
        Thread.sleep(DELAY_SECONDS);

        // Click on Accept cookies permission button
        driver.findElement(By.xpath("//*[contains(text(),'Accept')]")).click();

        // get excel file
        File src = new File("C:\\Users\\Admin\\Downloads\\Login_Cred.xlsx");
        FileInputStream input = new FileInputStream(src);

        workbook = new XSSFWorkbook(input);
        sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getLastRowNum());

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            //Check if Username is Empty ot not
            cell = sheet.getRow(i).getCell(0);
            if (cell != null && !TextUtils.isEmpty(cell.getStringCellValue())) {

                // Click on Sign In button
                driver.findElement(By.cssSelector("button[class^='button-shadow btn btn-primary']")).click();

                // Enter Username
                cell = sheet.getRow(i).getCell(0);
                cell.setCellType(CellType.STRING);
                driver.findElement(By.cssSelector("input[placeholder^='User name or Email Address']")).clear();
                driver.findElement(By.cssSelector("input[placeholder^='User name or Email Address']")).sendKeys(cell.getStringCellValue());

                // Enter Password
                cell = sheet.getRow(i).getCell(1);
                cell.setCellType(CellType.STRING);
                driver.findElement(By.name("password")).clear();
                driver.findElement(By.name("password")).sendKeys(cell.getStringCellValue());

                // Click on SignIn
                driver.findElement(By.cssSelector("button[type^='submit']")).click();

                // Click on Account Button
                driver.findElement(By.cssSelector("div[class^='HeaderMenu_profileMenu__2umIR']")).click();

                // Click on Logout button
                driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();
            }
        }
        Thread.sleep(DELAY_SECONDS);
        driver.quit();
    }

}
