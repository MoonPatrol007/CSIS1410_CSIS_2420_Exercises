
package docComments.Create;

/**
 * Description of top 5 best sports cars.
 * 
 * @author Hector Juarez
 */
public class MySportsCars {
	private int year;
	private String make;
	private String model;
	private String color;
	private int carId;
	private static int count = 0;


/**
 * Constructor initializes the field of sports cars.
 * 
 * @param year
 * @param make
 * @param model
 */
public MySportsCars(int year, String make, String model, String color) {
	this.year = year;
	this.make = make;
	this.model = model;
	this.color = color;
	carId = 1234567 + count++;
	
}


/**
 * @return the year
 */
public int getYear() {
	return year;
}


/**
 * @param year the year to set
 */
public void setYear(int year) {
	this.year = year;
}


/**
 * @return the make
 */
public String getMake() {
	return make;
}


/**
 * @param make the make to set
 */
public void setMake(String make) {
	this.make = make;
}


/**
 * @return the model
 */
public String getModel() {
	return model;
}


/**
 * @param model the model to set
 */
public void setModel(String model) {
	this.model = model;
}


/**
 * @return the color
 */
public String getColor() {
	return color;
}


/**
 * @param color the color to set
 */
public void setColor(String color) {
	this.color = color;
}


/**
 * @return the carId
 */
public int getCarId() {
	return carId;
}


/**
 * @param carId the carId to set
 */
public void setCarId(int carId) {
	this.carId = carId;
}


@Override
public String toString() {
	return "MySportsCars Year: " + year + ", Make: " + make + ", Model: " + model + ", Color=" + color + "ID: " + carId;
}



}