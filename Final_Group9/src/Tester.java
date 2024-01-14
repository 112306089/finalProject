import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Tester {
    public static void main(String[] args) {
    
    	Scanner sc = new Scanner(System.in);
    	System.out.println("請問想在哪裡用餐？輸入（校內/校外）");
    	String location = sc.next();
    	System.out.println("請問預算多少元？");
    	int budget = sc.nextInt();
    	System.out.println("請問想用甚麼類型的餐點呢？輸入（飲料/餐盒/正餐）");
    	String type = sc.next();
    	
    	
    	if(type.equals("飲料")) {
    		System.out.println("請用想喝哪種類型的飲料呢？輸入（手搖/咖啡廳/果汁）");
    	    String category = sc.next();
    	    Drink drink = new Drink(budget, location, category);
    	    drink.costOfTheDrink();
    	    System.out.println("以下是附近符合您需求的飲料店：");
    	    drink.whereToHaveMeal();
    		
    	}else if(type.equals("餐盒")) {
    		LunchBox meal = new LunchBox(budget, location, type);
    		meal.setType("餐盒");
    		meal.costOfTheMeal();
    		
            System.out.println("以下是附近符合您需求的餐廳：");
            meal.whereToHaveMeal();
            
    	}else if(type.equals("正餐")) {
    		System.out.println("請問想吃甚麼類型的正餐呢？輸入（台式/中式/韓式/日式/蔬食/自助餐/異國/速食");
    	    String foodType=sc.next();
    	    Type typeOfFood=new Type(budget,location,foodType);
    	    typeOfFood.costOfTheMeal();
    	    typeOfFood.whereToHaveMeal();
    	}
    	
    	
    	sc.close();
    }
}
