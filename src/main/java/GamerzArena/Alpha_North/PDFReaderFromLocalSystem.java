package GamerzArena.Alpha_North;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

public class PDFReaderFromLocalSystem {

    @Test

    public void ReadPDFFile() throws Exception {
        File file = new File("C:\\Users\\Admin\\Downloads\\file-sample_150kB.pdf");
        FileInputStream fis = new FileInputStream(file);

        PDDocument pdfDocument = PDDocument.load(fis);
        System.out.println(pdfDocument.getPages().getCount());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        pdfTextStripper.setStartPage(2);
        pdfTextStripper.setEndPage(3);

        String docText = pdfTextStripper.getText(pdfDocument);
        System.out.println(docText);

        Assert.assertTrue(docText.contains("Vivamus"));

        pdfDocument.close();
        fis.close();
    }
}
