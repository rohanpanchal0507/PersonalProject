package GamerzArena.Alpha_North;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.time.LocalDate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Create_Excel_File_for_LoginCred {

    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("GA_Login_Cred");

        Object[][] LogData = {
                {"Username/Email", "Password"},
                {"qatesting01", "Gamer@23"},
                {"rohan@alchemytech.ca", "Gamer@23"},
                {"alpha_01", "Gamer@23"},
                {"alpha_02", "Gamer@23"}
        };

        //Using for Loops
        /*int rows = LogData.length;
        int cols = LogData[0].length;

        System.out.println(rows);  //5
        System.out.println(cols);  //2

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.createRow(r);

            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.createCell(c);
                Object value = LogData[r][c];

                if (value instanceof String)
                    cell.setCellValue((String) value);
                if (value instanceof Integer)
                    cell.setCellValue((Integer) value);
                if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);

            }
        }*/

        //Using For...each loop
        int rowCount = 0;
        for (Object[] Login : LogData)
        {
            XSSFRow row = sheet.createRow(rowCount++);
                    int columnCount = 0;
            for (Object value:Login)
            {
                XSSFCell cell = row.createCell(columnCount++);
                if (value instanceof String)
                    cell.setCellValue((String) value);
                if (value instanceof Integer)
                    cell.setCellValue((Integer) value);
                if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);
            }
        }
        String directory = "Upload_Files";
        File fileObj = new File(directory);
        if (!fileObj.exists()) {
            fileObj.mkdir();
        }
        directory = directory + File.separator + "TestReport.xls";

        FileOutputStream outputStream = new FileOutputStream(directory);
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("LoginDetails.xls file is written successfully...");


    }

}
