package docComments;

/**
 * This is a TripPlanner that includes departure, arrival, and distance traveled.
 * 
 * @author Hector Juarez
 *
 */

public class TripPlanner {
	private String departure;
	private String arrival;
	private int distance;
	private Car car;
	
	
	/**
	 * Constructor of class TripPlanner.
	 * 
	 * @param departure		location of departure
	 * @param arrival		location of arrival
	 * @param distance		distance traveled from departure to arrival
	 * @param car			model of car
	 */
	public TripPlanner(String departure, String arrival, int distance, Car car) {
		this.departure = departure;
		this.arrival = arrival;
		this.distance = distance;
		this.car = car;
	
	}
	
	/**
	 * method to calculate the amount of fuel that is required to travel 
	 * from the departure to the destination and return the result in 
	 * full precision(not rounded)
	 * @return		fuel Consumption in gallons
	 * 
	 */
	public double fuelConsumption() {
		double fuelConsumption = distance / (double)car.getMpg();
		
		return fuelConsumption;
		
		
	}
	
	/**
	 * toString with added change of car to represent model, not the toString method of
	 * class Car.
	 *   
	 */
	@Override
	public String toString() {
		return " [departure=" + departure + ", arrival=" + arrival + ", distance=" + distance + ", Car=" + car.getModel() + "]";
		
		
	}
	
	

}
