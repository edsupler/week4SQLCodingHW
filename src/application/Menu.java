package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.FoodDao;
import entity.Food;

public class Menu {

	private FoodDao foodDao = new FoodDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Foods",	//will display all as a list with their ID
			"Display Food",		//will display a specific one with the description
			"Create Food",		//will create a new food
			"Edit Food",		//will edit the food description
			"Delete Food");		//deletes a food by ID
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayFoods();
				} else if (selection.equals("2")) {
					displayFood();
				} else if (selection.equals("3")) {
					createFood();
				} else if (selection.equals("4")) {
					editFood();
				} else if (selection.equals("5")) {
					deleteFood();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}

	private void printMenu() {
		System.out.println("Select and Option:\n------------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayFoods() throws SQLException {
		List<Food> foods = foodDao.getFoods();
		for (Food food : foods) {
			System.out.println(food.getId() + ":" + food.getName());
		}
	}
		
	private void displayFood() throws SQLException {
		System.out.print("Enter Food ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Food food = foodDao.getFoodById(id);
		System.out.println(food.getName() + ": " + food.getDescription());
	}
	
	private void createFood() throws SQLException {
		System.out.println("Enter name of new food:");
		String name = scanner.nextLine();
		System.out.println("Enter description:");
		String description = scanner.nextLine();
		foodDao.createNewFood(name, description);
	}
	
	private void editFood() throws SQLException {
		System.out.println("Enter ID of food to modify");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter new description:");
		String description = scanner.nextLine();
		foodDao.editFoodById(id, description);
		
	}
	
	private void deleteFood() throws SQLException {
		System.out.println("Enter Food ID to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		foodDao.deleteFoodById(id);
		
		
	}
	
	
	
				
}
