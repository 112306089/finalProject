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

	ArrayList<String> list = new ArrayList<String>(); //這裡是將excel第一列的值放進去的陣列
	ArrayList<String> list1 = new ArrayList<String>();//這裡是將excel第三列的值放進去的陣列，以此類推
	ArrayList<String> list2 = new ArrayList<String>();
	ArrayList<String> list3 = new ArrayList<String>();
	ArrayList<String> list4 = new ArrayList<String>();
	ArrayList<String> list5 = new ArrayList<String>();
	ArrayList<String> list6 = new ArrayList<String>();
	ArrayList<String> list7 = new ArrayList<String>();

	private ArrayList<ArrayList<String>> restaurant = new ArrayList<>(); //會這樣寫是因為要將上面的list丟進去排成一個2*n的陣列
	private ArrayList<ArrayList<String>> restaurant1 = new ArrayList<>(); //如果你們知道更好的寫法也可以跟我說，有時間會改一下
	private ArrayList<ArrayList<String>> restaurant2 = new ArrayList<>();
	private ArrayList<ArrayList<String>> restaurant3 = new ArrayList<>();

	public Meal(int budget, String location) {
		this.budget = budget;
		this.location = location;
	}

	public void costOfTheMeal() {

		try {
			FileInputStream fis = new FileInputStream(new File("C:/Users/Cindy/eclipse-workspace/Final1229/餐廳.xlsx")); // 讀取檔案

			XSSFWorkbook wb = new XSSFWorkbook(fis); // 新建一個.xlsx檔的工作簿

			XSSFSheet sheet = wb.getSheetAt(0); // 新建工作表

			Row secondRow = sheet.getRow(1); // 從工作表中取得第二列

			for (int i = 0; i < secondRow.getLastCellNum(); i++) { // getLastCellNum為取得最後一個單元格的編碼
				Cell cell = secondRow.getCell(i); // 取得第二列的單元格

				if (cell.getNumericCellValue() <= 70 && cell.getNumericCellValue() >= 50) { // getNumericCellValue為將單元格的值設為數字
					Row firstRow = sheet.getRow(0);
					Row thirdRow = sheet.getRow(2);

					Cell firstRowCell = firstRow.getCell(i);
					Cell thirdRowCell = thirdRow.getCell(i);

					list.add(firstRowCell.getStringCellValue()); // getStringValue為將單元格的值設為字串
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

			ArrayList<String> combinedList1 = new ArrayList<>(list1); // 這裡會需要這樣將陣列加進去是因為前面的條件只會篩選像是70-100之間的餐廳，
			combinedList1.addAll(list3);// 70以下的就不會有，我不知道有沒有其他比較好的方式能將100以下的都加進陣列中所以就這樣寫
			
			restaurant1.add(combinedList); 
			restaurant1.add(combinedList1);

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
		if (budget <= 70 && budget >= 50) {
			for (int j = 0; j < restaurant.get(1).size(); j++) {
				if (restaurant.get(1).get(j).equals(location)) {
					System.out.println(restaurant.get(0).get(j));
				} else {
					System.out.println("Out of the area");
					break;
				}
			}
		} else if (budget > 70 && budget <= 100) {
			for (int j = 0; j < restaurant1.get(1).size(); j++) {
				if (restaurant1.get(1).get(j).equals(location)) {
					System.out.println(restaurant1.get(0).get(j));
				} else {
					System.out.println("Out of the area");
					break;
				}
			}
		} else if (budget > 100 && budget <= 200) {
			for (int j = 0; j < restaurant2.get(1).size(); j++) {
				if (restaurant2.get(1).get(j).equals(location)) {
					System.out.println(restaurant2.get(0).get(j));
				} else {
					System.out.println("Out of the area");
					break;
				}
			}
		} else if (budget > 200) {
			for (int j = 0; j < restaurant3.get(1).size(); j++) {
				if (restaurant3.get(1).get(j).equals(location)) {
					System.out.println(restaurant3.get(0).get(j));
				} else {
					System.out.println("Out of the area");
					break;
				}
			}
		} else {
			System.out.println("There is nothing you can eat");
		}
	}
}
