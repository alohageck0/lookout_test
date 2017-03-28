package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class MyXLSDataProviders {

   private static XSSFWorkbook workbook = null;
   private static XSSFSheet sheet = null;
   private static XSSFRow row = null;
   private static XSSFCell cell = null;

   /**
    * Method to get usernames Data from xls file.
    */
   @DataProvider
   public static Object[][] addUserData() throws IOException {
      return getData("usernames");
   }

   @DataProvider
   public static Object[][] editUserData() throws IOException {
      return getData("editedusernames");
   }

   @DataProvider
   public static Object[][] editPasswordData() throws IOException {
      return getData("editPassword");
   }

   @DataProvider
   public static Object[][] deleteUserData() throws IOException {
      return getData("deleteUsers");
   }

   @DataProvider
   public static Object[][] addMasterUserData() throws IOException {
      return getData("masterCodes");
   }

   @DataProvider
   public static Object[][] addGuestUserData() throws IOException {
      return getData("guestCodes");
   }

   @DataProvider
   public static Object[][] userAndGuestData() throws IOException {
      return getData("userAndGuest");
   }


   private static Object[][] getData(String sheetName) throws IOException {
      FileInputStream file = new FileInputStream("/Users/royalfiish/IdeaProjects/AndroidTesting/src/usernames.xlsx");
      workbook = new XSSFWorkbook(file);
      sheet = workbook.getSheet(sheetName);
      int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
      int columnCount = sheet.getRow(0).getLastCellNum();

      Object[][] data = new Object[rowCount - 1][columnCount];

      for (int i = 2; i <= rowCount; i++) {
         for (int j = 0; j < columnCount; j++) {
            data[i - 2][j] = getCellData(sheetName, i, j);
         }
      }
      return data;
   }

   private static String getCellData(String sheetName, int rowNumber, int columnNumber) {
      try {
         if (rowNumber <= 0)
            return "";
         int index = workbook.getSheetIndex(sheetName);
         if (index == -1) {
            return "";
         }
         sheet = workbook.getSheetAt(index);
         row = sheet.getRow(rowNumber - 1);
         if (row == null) {
            return "";
         }
         cell = row.getCell(columnNumber);
         if (cell == null) {
            return "";
         }
         if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
         } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
         } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getStringCellValue();
         } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
         } else {
            return String.valueOf(cell.getBooleanCellValue());
         }
      } catch (Exception e) {
         e.printStackTrace();
         return "does not exist in *.xls";

      }
   }

   @DataProvider
   public static Object[][] addUsersTests() throws IOException {
      return getData("addUsersTests");

   }
}
