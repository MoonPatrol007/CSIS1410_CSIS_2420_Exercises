package docComments;

/**
 * Car description and Miles Per Gallon during travel.
 * 
 * @author Hector Juarez
 *
 */
public class Car {
	
	private String make;
	private String model;
	private int mpg;
	
	/**
	 * Constructor initializes the fields
	 * 
	 * @param make		make of the car
	 * @param model		model of the car
	 * @param mpg		Miles Per Gallon
	 */
	
	public Car(String make, String model, int mpg) {
		this.make = make;
		this.model = model;
		this.mpg = mpg;
	}

	/**
	 * Returns the make of the car.
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Returns the model of the car.
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Returns the Miles Per Gallon.
	 * @return the mpg
	 */
	public int getMpg() {
		return mpg;
	}

	/**
	 * toString to generate make, model, and mpg
	 */
	@Override
	public String toString() {
		return make + " " + model + " " + mpg + " mph";
		
	}
	
	

}
