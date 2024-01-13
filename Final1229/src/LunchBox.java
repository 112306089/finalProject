import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.List;


public class LunchBox extends Meal {
    private String type;

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

    public List<String> getRecommendedRestaurants() {
        List<String> recommendedRestaurants = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File("C:/Users/Cindy/eclipse-workspace/Final1229/餐廳.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Cell typeCell = row.getCell(5); 
                String restaurantType = typeCell.getStringCellValue();

                if ("餐盒".equals(restaurantType)) {
                    Cell nameCell = row.getCell(0); 
                    recommendedRestaurants.add(nameCell.getStringCellValue());
                }
            }

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommendedRestaurants;
    }
    
    public void costOfTheMeal() {
    	super.costOfTheMeal();
    }
}
