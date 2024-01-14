import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LunchBox extends Meal {
    private String type;
    private List<List<String>> restaurantList = new ArrayList<>();

    public LunchBox(int budget, String location, String type) {
        super(budget, location);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void costOfTheMeal() {
        try {
            FileInputStream fis = new FileInputStream(new File("餐廳.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            Row secondRow = sheet.getRow(1);

            for (int i = 0; i < secondRow.getLastCellNum(); i++) {
                Cell cell = secondRow.getCell(i);
                handleCell(sheet, i, restaurantList);
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCell(XSSFSheet sheet, int columnIndex, List<List<String>> restaurantList) {
        Row firstRow = sheet.getRow(0);
        Row thirdRow = sheet.getRow(2);
        Row sixthRow = sheet.getRow(5);

        Cell firstRowCell = firstRow.getCell(columnIndex);
        Cell thirdRowCell = thirdRow.getCell(columnIndex);
        Cell sixthRowCell = sixthRow.getCell(columnIndex);

        List<String> restaurantInfo = new ArrayList<>();
        restaurantInfo.add(firstRowCell.getStringCellValue());
        restaurantInfo.add(thirdRowCell.getStringCellValue());
        if (sixthRowCell != null) {
            restaurantInfo.add(sixthRowCell.getStringCellValue());
        } else {
            restaurantInfo.add(" ");
        }
        
        // 添加预算信息
        Cell budgetCell = sheet.getRow(1).getCell(columnIndex);
        restaurantInfo.add(String.valueOf((int) budgetCell.getNumericCellValue()));

        restaurantList.add(restaurantInfo);
    }

    public void whereToHaveMeal() {
        boolean found = false;

        for (List<String> restaurantInfo : restaurantList) {
            String restaurantName = restaurantInfo.get(0);
            String location = restaurantInfo.get(1);
            String restaurantType = restaurantInfo.get(2);
            int restaurantBudget = Integer.parseInt(restaurantInfo.get(3));

            if (super.getLocation().equals(location) && getType().equals(restaurantType) && super.getBudget() >= restaurantBudget) {
                System.out.println(restaurantName);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Out of the area");
        }
    }
}
