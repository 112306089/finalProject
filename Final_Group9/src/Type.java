import java.util.ArrayList;


import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.List;


public class Type extends Meal{
	
	private String foodType;
	
	ArrayList<String> typeList = new ArrayList<String>();
	ArrayList<String> typeList1 = new ArrayList<String>();
	ArrayList<String> typeList2 = new ArrayList<String>();
	ArrayList<String> typeList3 = new ArrayList<String>();
	ArrayList<String> typeList4 = new ArrayList<String>();
	ArrayList<String> typeList5 = new ArrayList<String>();
	ArrayList<String> typeList6 = new ArrayList<String>();
	ArrayList<String> typeList7 = new ArrayList<String>();
	ArrayList<String> typeList8 = new ArrayList<String>();
	ArrayList<String> typeList9 = new ArrayList<String>();
	ArrayList<String> typeList10 = new ArrayList<String>();
	ArrayList<String> typeList11 = new ArrayList<String>();
	
	private ArrayList<ArrayList<String>> restaurant6 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant7 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant8 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant9 = new ArrayList<>();
	
	public Type(int budget,String location,String foodType) {
		super(budget,location);
		this.foodType=foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType=foodType;
	}
	
	public String getFoodType() {
		return foodType;
	}
	public void costOfTheMeal() {

		try {
			FileInputStream fis = new FileInputStream(new File("餐廳.xlsx"));

			// Creating workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// Creating a Sheet object to retrieve the sheet
			XSSFSheet sheet = wb.getSheetAt(0);

			Row secondRow = sheet.getRow(1);

			for (int i = 0; i < secondRow.getLastCellNum(); i++) {
				Cell cell = secondRow.getCell(i);

				if (cell.getNumericCellValue() <= 70 && cell.getNumericCellValue() >= 50) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fifthRow = sheet.getRow(4);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fifthRowCell = fifthRow.getCell(i);

					typeList.add(firstRowCell.getStringCellValue());
					typeList1.add(thirdRowCell.getStringCellValue());
					if(fifthRowCell != null) {
						typeList2.add(fifthRowCell.getStringCellValue());
					}
					else {
						typeList2.add(" ");
					}
					

				} else if (cell.getNumericCellValue() <= 100 && cell.getNumericCellValue() > 70) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fifthRow = sheet.getRow(4);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fifthRowCell = fifthRow.getCell(i);

					typeList3.add(firstRowCell.getStringCellValue());
					typeList4.add(thirdRowCell.getStringCellValue());
					if(fifthRowCell != null) {
						typeList5.add(fifthRowCell.getStringCellValue());
					}
					else {
						typeList5.add(" ");
					}
					
				} else if (cell.getNumericCellValue() <= 200 && cell.getNumericCellValue() > 100) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fifthRow = sheet.getRow(4);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fifthRowCell = fifthRow.getCell(i);


					typeList6.add(firstRowCell.getStringCellValue());
					typeList7.add(thirdRowCell.getStringCellValue());
					if(fifthRowCell != null) {
						typeList8.add(fifthRowCell.getStringCellValue());
					}
					else {
						typeList8.add(" ");
					}
				} else if (cell.getNumericCellValue() > 200) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);
					Row fifthRow = sheet.getRow(4);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);
					Cell fifthRowCell = fifthRow.getCell(i);

					typeList9.add(firstRowCell.getStringCellValue());
					typeList10.add(thirdRowCell.getStringCellValue());
					if(fifthRowCell != null) {
						typeList11.add(fifthRowCell.getStringCellValue());
					}
					else {
						typeList11.add(" ");
					}
				}

			}

			fis.close();

			restaurant6.add(typeList);
			restaurant6.add(typeList1);
			restaurant6.add(typeList2);
			restaurant7.add(typeList3);
			restaurant7.add(typeList4);
			restaurant7.add(typeList5);
			restaurant8.add(typeList6);
			restaurant8.add(typeList7);
			restaurant8.add(typeList8);
			restaurant9.add(typeList9);
			restaurant9.add(typeList10);
			restaurant9.add(typeList11);

			

		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	public void whereToHaveMeal() {
		if (!restaurant6.isEmpty() && restaurant6.size() > 2 && !restaurant6.get(1).isEmpty() && !restaurant6.get(2).isEmpty()) {
			if (super.getBudget() >= 50 && super.getBudget() < 70) { 
				boolean found = false;
				for (int j = 0; j < restaurant6.get(1).size(); j++) {
					if (restaurant6.get(1).get(j).equals(super.getLocation()) && restaurant6.get(2).get(j).equals(foodType)) {
						System.out.println(restaurant6.get(0).get(j));
						found = true;
					}
				}
				if(!found) {
					System.out.println("Out of the area");
				}
			} else if (super.getBudget() < 50){
				System.out.println("There is nothing you can eat");
			}
		} 
		if (!restaurant7.isEmpty() && restaurant7.size() > 2 && !restaurant7.get(1).isEmpty() && !restaurant7.get(2).isEmpty()) {
			if (super.getBudget() >= 70 && super.getBudget() < 100) {	
				boolean found = false;
				for (int j = 0; j < restaurant7.get(1).size(); j++) {
					if (restaurant7.get(1).get(j).equals(super.getLocation()) && restaurant7.get(2).get(j).equals(foodType)) {
						System.out.println(restaurant7.get(0).get(j));
						found = true;
					}
				}
				if(!found) {
					System.out.println("Out of the area");
				}
			}
		}
		if (!restaurant8.isEmpty() && restaurant8.size() > 2 && !restaurant8.get(1).isEmpty() && !restaurant8.get(2).isEmpty()) {
			if (super.getBudget() >= 100 && super.getBudget() < 200) {	
				boolean found = false;
				for (int j = 0; j < restaurant8.get(1).size(); j++) {
					if (restaurant8.get(1).get(j).equals(super.getLocation()) && restaurant8.get(2).get(j).equals(foodType)) {
						System.out.println(restaurant8.get(0).get(j));
						found = true;
							}
						}
						if(!found) {
							System.out.println("Out of the area");
						}
			}
		}
		if (!restaurant9.isEmpty() && restaurant9.size() > 2 && !restaurant9.get(1).isEmpty() && !restaurant9.get(2).isEmpty()) {
			if (super.getBudget() > 200 ) {	
				boolean found = false;
				for (int j = 0; j < restaurant9.get(1).size(); j++) {
					if (restaurant9.get(1).get(j).equals(super.getLocation()) && restaurant9.get(2).get(j).equals(foodType)) {
						System.out.println(restaurant9.get(0).get(j));
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
			
	            
	            
	            

	            