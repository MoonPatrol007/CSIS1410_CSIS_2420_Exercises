/**
 * This the package for the Text Manipulation lab.
 * the program will allow the user to input their 
 * favorite national parks.
 *  
 */
package textManipulation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Hector J.
 *
 */
public class LabTextManipulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Scanner where the user inputs his/her favorite National Park.
		Scanner myParks = new Scanner(System.in);
		String favNatParks;
		
		
		// do while loop which allows the user to input multiple entries.
		do {
			System.out.println("Please enter your favorite National Park or Done to stop: ");
			favNatParks = myParks.nextLine().toLowerCase();
			System.out.println("\n\nYour favorite National Park is: " + updateSpelling(favNatParks));
									
		}while (!"done".equals(favNatParks));
		System.out.println("\nThank you, Good Bye.");
				
		// prints out the list of National Parks, using StringBuilder.
		List<String> parks = Arrays.asList("Mesa Verde", "Black Canyon of the Gunnison", "Denali", "YellowStone");
		System.out.println("\nFavorite National Parks: " + nationalParks(parks, " | "));

	}
	
	/**
	 * list of the favorite National Parks.
	 * StringBuilder was added.
	 * a separator between words and the vertical piping bar is added. 
	 * 
	 * @param list
	 * @return
	 */
	private static String nationalParks(List<String> list, String separator) {
		StringBuilder sb = new StringBuilder(32);
		boolean first = true;
		
		for(String el : list) {
			if (first)
				first = false;
			else {
				sb.append(separator);
			}
			sb.append(el);
		}
		return sb.toString();
	}

	/**
	 * private method to update the spelling.
	 * StingBuilder was added.
	 * First letter in the word will be upper case
	 * then every letter aftwards will be lower case.
	 * 
	 * @param text
	 * @return
	 */
	private static String updateSpelling(String text) {
		StringBuilder upSpell = new StringBuilder(32);
		char ch = ' ';
		
		for (int i = 0; i<text.length(); i++) {
			if(ch == ' ' && text.charAt(i) != ' ') {
				upSpell.append(Character.toUpperCase(text.charAt(i)));
				
			}else if(Character.isLetter(text.charAt(i))) {
				upSpell.append(Character.toLowerCase(text.charAt(i)));
				
			}
			// if anything other type of input is added besides letters.
			else {
				upSpell.append(text.charAt(i));
			}
			//This will keep track of previous characters inputed.
			ch = text.charAt(i);			
			
		}
		return upSpell.toString(); 
		
	}
	
}
