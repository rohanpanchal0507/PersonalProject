package GamerzArena.Alpha_North;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class PDFReaderFromInternet {

    @Test

    public void ReadPDFFile() throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://file-examples.com/");
        driver.findElement(By.xpath("//h3[text()='Documents']/following-sibling::a")).click();
        driver.findElement(By.xpath("//td[text()='PDF']/following-sibling::td/a")).click();
        driver.findElement(By.xpath("//td[text()='150 kB']/following-sibling::td/a")).click();
        String urlString = driver.getCurrentUrl();

        URL url = new URL(urlString);
        PDDocument pdfDocument = PDDocument.load(url.openStream());

        System.out.println("Number of Pages: " +pdfDocument.getPages().getCount());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setStartPage(1); // comment this line if you want to read the entire document
        pdfTextStripper.setEndPage(3); // comment this line if you want to read the entire document
        String docText = pdfTextStripper.getText(pdfDocument);

        System.out.println(docText);

        Assert.assertTrue(docText.contains("Maecenas"));

        pdfDocument.close();
        driver.quit();

    }
}

