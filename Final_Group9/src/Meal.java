import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Meal {

	private int budget;
	private String location;

	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> list1 = new ArrayList<String>();
	private ArrayList<String> list2 = new ArrayList<String>();
	private ArrayList<String> list3 = new ArrayList<String>();
	private ArrayList<String> list4 = new ArrayList<String>();
	private ArrayList<String> list5 = new ArrayList<String>();
	private ArrayList<String> list6 = new ArrayList<String>();
	private ArrayList<String> list7 = new ArrayList<String>();

	private ArrayList<ArrayList<String>> restaurant = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant1 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant2 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant3 = new ArrayList<>();
	

	public Meal(int budget, String location) {
		this.budget = budget;
		this.location = location;
	}
	
	public int getBudget() {
		return budget;
	}
	
	public String getLocation() {
		return location;
	}

	public void costOfTheMeal() {

		try {
			FileInputStream fis = new FileInputStream(new File("C:/Users/Cindy/eclipse-workspace/Final1229/餐廳.xlsx"));

			XSSFWorkbook wb = new XSSFWorkbook(fis); // Create a new Excel workbook file (.xlsx)

			XSSFSheet sheet = wb.getSheetAt(0); // Create a new worksheet

			Row secondRow = sheet.getRow(1); // Get the second row from worksheet

			for (int i = 0; i < secondRow.getLastCellNum(); i++) {
				Cell cell = secondRow.getCell(i); // Get each cell of second row

				if (cell.getNumericCellValue() <= 70 && cell.getNumericCellValue() >= 50) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);

					list.add(firstRowCell.getStringCellValue()); // put the row which conform to the condition into
																	// array
					list1.add(thirdRowCell.getStringCellValue());

				} else if (cell.getNumericCellValue() <= 100 && cell.getNumericCellValue() > 70) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);

					list2.add(firstRowCell.getStringCellValue());
					list3.add(thirdRowCell.getStringCellValue());

				} else if (cell.getNumericCellValue() <= 200 && cell.getNumericCellValue() > 100) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);

					list4.add(firstRowCell.getStringCellValue());
					list5.add(thirdRowCell.getStringCellValue());
				} else if (cell.getNumericCellValue() > 200) {
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);

					list6.add(firstRowCell.getStringCellValue());
					list7.add(thirdRowCell.getStringCellValue());
				}

			}

			fis.close();

			restaurant.add(list);
			restaurant.add(list1);

			ArrayList<String> combinedList = new ArrayList<>(list);
			combinedList.addAll(list2);

			ArrayList<String> combinedList1 = new ArrayList<>(list1);
			combinedList1.addAll(list3);

			restaurant1.add(combinedList); // construct a 2D array
			restaurant1.add(combinedList1); // construct a 2D array

			ArrayList<String> combinedList2 = new ArrayList<>(combinedList);
			combinedList2.addAll(list4);

			ArrayList<String> combinedList3 = new ArrayList<>(combinedList1);
			combinedList3.addAll(list5);

			restaurant2.add(combinedList2);
			restaurant2.add(combinedList3);

			ArrayList<String> combinedList4 = new ArrayList<>(combinedList2);
			combinedList4.addAll(list6);

			ArrayList<String> combinedList5 = new ArrayList<>(combinedList3);
			combinedList5.addAll(list7);

			restaurant3.add(combinedList4);
			restaurant3.add(combinedList5);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void whereToHaveMeal() {
		boolean locationFound = false;

		if (budget <= 70 && budget >= 50) {
			for (int j = 0; j < restaurant.get(1).size(); j++) {
				if (restaurant.get(1).get(j).equals(location)) {
					System.out.println(restaurant.get(0).get(j)); // print out the restaurants which is in that area and
																	// fit within the budget
					locationFound = true;
				}
			}
		} else if (budget > 70 && budget <= 100) {
			for (int j = 0; j < restaurant1.get(1).size(); j++) {
				if (restaurant1.get(1).get(j).equals(location)) {
					System.out.println(restaurant1.get(0).get(j));
					locationFound = true;
				}
			}
		} else if (budget > 100 && budget <= 200) {
			for (int j = 0; j < restaurant2.get(1).size(); j++) {
				if (restaurant2.get(1).get(j).equals(location)) {
					System.out.println(restaurant2.get(0).get(j));
					locationFound = true;
				}
			}
		} else if (budget > 200) {
			for (int j = 0; j < restaurant3.get(1).size(); j++) {
				if (restaurant3.get(1).get(j).equals(location)) {
					System.out.println(restaurant3.get(0).get(j));
					locationFound = true;
				}
			}
		} else {
			System.out.println("There is nothing you can eat");
		}

		if (!locationFound && budget >= 70) {
			System.out.println("Out of area");
		}
		
	}
	
	

}
