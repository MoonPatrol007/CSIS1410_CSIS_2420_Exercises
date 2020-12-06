package docComments.Create;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the list for the Sports Cars.
 * wit 6 choices to choose from.
 * 
 * @author Hector Juarez.
 *
 */
public class SportsCarApp {
	
	static ArrayList<MySportsCars> SportsCar = new ArrayList<>();

	/**
	 * Initial main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		SportsCar.add(new MySportsCars(1966, "Lamborghini", "Miura", "Red"));
		SportsCar.add(new MySportsCars(1962, "Ferrari", "250_GTO", "Red"));
		SportsCar.add(new MySportsCars(1954, "Mercedes-Benz", "SL", "Silver"));
		SportsCar.add(new MySportsCars(1974, "Lamborghini", "Countach", "Fire Red"));
		SportsCar.add(new MySportsCars(1993, "Porsche", "964 Turbo", "Black"));
		
		while(true) {
			menu();
		}

	}

	private static void menu() {
		// private method which has the scanner input and list of choices.
		
		Scanner input = new Scanner(System.in);
		int selection;
		System.out.printf("%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
				"1. Show all Sports Cars",
				"2. Add a Sports Car",
				"3. Find a Sports Car",
				"4. Delete a Sports Car",
				"5. Number of Sports Car",
				"6. Exit\n",
				"Enter your seletion: ");
		// user enters input
		selection = input.nextInt(); input.nextLine();
		
		System.out.println();
		output(selection, input);
				
	}

	private static void output(int selection, Scanner input) {
		int carId;
		String carMake;
		String carModel;
		boolean check;
		switch(selection) {
		
		// Show all Sports Cars
		case 1:
			for(MySportsCars i: SportsCar) {
				System.out.println(i);
			}
			System.out.println();
			break;
		
		// Add a Sports Car.
		case 2:
			System.out.print("Enter Year: ");
			int year = input.nextInt();
			
			System.out.print("Enter Make: ");
			String make = input.next();
			
			System.out.print("Enter Model: ");
			String model = input.next();
			
			System.out.print("Enter Color: ");
			String color = input.next();
			
			SportsCar.add(new MySportsCars(year, make, model, color));
			System.out.println();
			break;
		
		// Find a Sports Car.
		case 3:
			
			System.out.print("Enter Car Id: ");
			carId = input.nextInt();
			check = false;
			
			for(MySportsCars i: SportsCar) {
				if(carId == i.getCarId()) {
					System.out.println(i);
					check = true;
				}
			}
			if(!check)
				System.out.println("Id: " + carId + "Sports Car Not Found. \n");
		break;
		
		case 5:
			System.out.print("Enter Sport Car Id to delete: ");
			carId = input.nextInt();
			check = false;
			
			for(MySportsCars i: SportsCar) {
				if(carId == i.getCarId()) {
					System.out.println(i + "This Sports Car has been deleted. ");
					SportsCar.remove(i);
					check = true;
					break;					
				}
			}
			if(!check)
				System.out.println("Id: " + carId + "Sports Car Not Found. \n");
		break;
		
		// Number of Sports Car listed.
		case 6:
			System.out.println("\nNumber of cars is: " + SportsCar.size() + "\n");
		break;
		
		// Exit out choices
		case 7:
			System.out.println("\nThanks for playing; Good Bye. ");
			System.exit(0);
		break;
		
		// when invalid input has been entered.
		default: System.out.println("\nPlease enter a number fromm 1 to 6 only! ");
		
		}
	}

}
