import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Drink extends Meal {
	
	private String category;
	
	public Drink(int budget, String location, String category) {
		super(budget, location);
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	private ArrayList<String> drinkList1 = new ArrayList<>();
	private ArrayList<String> drinkList2 = new ArrayList<>();
	private ArrayList<String> drinkList3 = new ArrayList<>();
	private ArrayList<String> drinkList4 = new ArrayList<>();
	private ArrayList<String> drinkList5 = new ArrayList<>();
	private ArrayList<String> drinkList6 = new ArrayList<>();
	
	private ArrayList<ArrayList<String>> restaurant4 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant5 = new ArrayList<>();
	
	public void costOfTheDrink() {
		try {
			FileInputStream fis = new FileInputStream(new File("餐廳.xlsx"));

			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet = wb.getSheetAt(0);
			
			Row secondRow = sheet.getRow(1);

			for (int i = 0; i < secondRow.getLastCellNum(); i++) {
				Cell cell = secondRow.getCell(i);

				if (cell.getNumericCellValue() < 120 && cell.getNumericCellValue() >= 25) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fourthRow = sheet.getRow(3);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fourthRowCell = fourthRow.getCell(i);

					drinkList1.add(firstRowCell.getStringCellValue());
                    drinkList2.add(thirdRowCell.getStringCellValue());
                    if (fourthRowCell != null) {
                        drinkList3.add(fourthRowCell.getStringCellValue());
                    } else {
                        drinkList3.add("");
                    }

				}
				
				if(cell.getNumericCellValue() <= 120 && cell.getNumericCellValue() > 0){
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fourthRow = sheet.getRow(3);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fourthRowCell = fourthRow.getCell(i);

					drinkList4.add(firstRowCell.getStringCellValue());
                    drinkList5.add(thirdRowCell.getStringCellValue());
                    if (fourthRowCell != null) {
                        drinkList6.add(fourthRowCell.getStringCellValue());
                    } else {
                        drinkList6.add("");
                    }
				}
			}
			
			fis.close();
			
			restaurant4.add(drinkList1);
			restaurant4.add(drinkList2);
			restaurant4.add(drinkList3);
			restaurant5.add(drinkList4);
			restaurant5.add(drinkList5);
			restaurant5.add(drinkList6);
			
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

	public void whereToHaveMeal() {
		if (!restaurant4.isEmpty() && restaurant4.size() > 2 && !restaurant4.get(1).isEmpty() && !restaurant4.get(2).isEmpty()) {
			if (super.getBudget() >= 25 && super.getBudget() < 120) { 
				boolean found = false;
				for (int j = 0; j < restaurant4.get(1).size(); j++) {
					if (restaurant4.get(1).get(j).equals(super.getLocation()) && restaurant4.get(2).get(j).equals(category)) {
						System.out.println(restaurant4.get(0).get(j));
						found = true;
					}
				}
				if(!found) {
					System.out.println("Out of the area");
				}
			} else if (super.getBudget() < 25){
				System.out.println("There is nothing you can eat");
			}
		} 
		if (!restaurant5.isEmpty() && restaurant5.size() > 2 && !restaurant5.get(1).isEmpty() && !restaurant5.get(2).isEmpty()) {
			if (super.getBudget() >= 120) {	
				boolean found = false;
				for (int j = 0; j < restaurant5.get(1).size(); j++) {
					if (restaurant5.get(1).get(j).equals(super.getLocation()) && restaurant5.get(2).get(j).equals(category)) {
						System.out.println(restaurant5.get(0).get(j));
						found = true;
					}
				}
				if(!found) {
					System.out.println("Out of the area");
				}
			}
		}
	}
}


