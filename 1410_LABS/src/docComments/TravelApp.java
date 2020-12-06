package docComments;

// imported Precision Format Specifier to fuel Consumption for trip.
import java.util.Formatter;

public class TravelApp {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();
		
		// BMW M4 car
		Car carM4 = new Car("BMW", "M4", 25);
		
		// Honda Civic car
		Car carCivic = new Car("Honda", "Civic", 42);
		
		System.out.println("Cars: \n" + carM4 + "\n" + carCivic + "\n");
		
		
		
		// California Trip
		System.out.println("California Trip: ");
		TripPlanner californiaTrip = new TripPlanner("SF", "LA", 382, carM4);
		System.out.println("TravelPlanner " + californiaTrip );
		
		// fuel consumption of California trip with precision format specifier of 1 decimal place.
		formatter = new Formatter();
		formatter.format("%.1f", californiaTrip.fuelConsumption());
		System.out.println("fuel consumption: " + formatter + " gallons\n");
		
		
		// Florida Trip
		System.out.println("Florida Trip: ");
		TripPlanner floridaTrip = new TripPlanner("Tampa", "Miami", 280, carCivic);
		System.out.println("TravelPlanner " + floridaTrip);
		
		
		// fuel consumption of Florida trip with precision format specifier of 1 decimal place.
		formatter = new Formatter();
		formatter.format("%.1f", floridaTrip.fuelConsumption());
		System.out.println("fuel consumption: " + formatter + " gallons" );
		
						
	}
			
}
