package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelData {
    /**
     * reads excel file and returns data as
     * two dimensional String array
     *
     * @param fileName
     * @param sheetName
     * @return
     */

    public String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbookSelected = Workbook.getWorkbook(fileInputStream);
            Sheet sheetSelected = workbookSelected.getSheet(sheetName);

            int totalNoOfCols = sheetSelected.getColumns();
            int totalNoOfRows = sheetSelected.getRows();

            //-1 is done done to ignore the first row of the sheet
            //that contains Headers
            arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
            for (int i = 1; i < totalNoOfRows; i++) {
                for (int j = 0; j < totalNoOfCols; j++) {
                    arrayExcelData[i-1][j] = sheetSelected.getCell(j,i).getContents();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }
}
