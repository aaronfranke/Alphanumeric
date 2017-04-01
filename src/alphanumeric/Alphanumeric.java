
package alphanumeric;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Alphanumeric {
	
	private static Scanner input = null;
	private static File file = null;
	
	private static boolean numbers = true;
	private static boolean spaces = true;
	private static boolean uppercase = false;
	private static boolean lowercase = false;
	private static boolean stdin = false;
	
	private static String filename = "file.txt";
	private static String text = "";
	private static String nextLine = "";
	
	public static void main(String[] args) {
		
		parseArguments(args); // Sets the above variables based on the arguments. 
		
		if (uppercase && lowercase) {
			System.out.println(" ");
			System.out.println("Error 2: Both upper and lower arguments found. MAKE UP YOUR DAMN MIND! ");
			System.out.println(" ");
			System.exit(2);
		}
		 
		file = new File(filename);
		
		if (!file.exists()) {
			System.out.println(" ");
			System.out.println("Error 3: Invalid file name specified! See the help page for more info. ");
			System.out.println(" ");
			System.exit(3);
		}
		
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(" ");
			System.out.println("Error 4: File not found exception. THIS SHOULD NEVER HAPPEN! VERY BAD! ");
			System.out.println(" ");
			System.exit(4);
			//e.printStackTrace();
		}
		
		if (stdin) {
			input = new Scanner(System.in);
		}
		
		
		
		if (numbers && spaces) {
			
			while (input.hasNextLine()) {
				
				nextLine = input.nextLine();
				
				nextLine = nextLine.replaceAll("[^A-Za-z0-9 ]", "");
				
				text = text + nextLine + "\n";
			}
			
		} else if (numbers) {
			
			while (input.hasNextLine()) {
				
				nextLine = input.nextLine();
				
				nextLine = nextLine.replaceAll("[^A-Za-z0-9]", "");
				
				text = text + nextLine + "\n";
			}
			
		} else if (spaces) {
			
			while (input.hasNextLine()) {
				
				nextLine = input.nextLine();
				
				nextLine = nextLine.replaceAll("[^A-Za-z ]", "");
				
				text = text + nextLine + "\n";
			}
			
		} else {
			
			while (input.hasNextLine()) {
				
				nextLine = input.nextLine();
				
				nextLine = nextLine.replaceAll("[^A-Za-z]", "");
				
				text = text + nextLine + "\n";
			}
			
		}
		
		
		if (uppercase) {
			text = text.toUpperCase();
		}
		
		if (lowercase) {
			text = text.toLowerCase();
		}
		
		// Print the output and end the program. 
		System.out.println(text);
		
		
		System.out.close();
		input.close();
		
		System.exit(0);
	}
	
	
	private static void parseArguments(String[] args) {
		
		for (String argument : args) {
			String arg = argument.toLowerCase();
			
			if (arg.equals("--help") || arg.equals("-?")) {
				System.out.println(" ");
				System.out.println("Alphanumeric - A program for removing special characters. By Aaron Franke. ");
				System.out.println("Usage: java -jar alphanumeric.jar [filename] [arguments] ");
				System.out.println("If [filename] is unspecified, look for \"file.txt\" by default. ");
				System.out.println(" ");
				System.out.println("List of arguments for Alphanumeric: ");
				System.out.println(" ");
				System.out.println(" -? --help         Displays this page. ");
				System.out.println(" ");
				System.out.println(" -u --upper        Sets the text to UPPERCASE. ");
				System.out.println(" -l --lower        Sets the text to lowercase. ");
				System.out.println(" ");
				System.out.println(" -n --no-numbers   Doesn't keep numbers. Keeps letters and possibly spaces. ");
				System.out.println(" -s --no-spaces    Doesn't keep spaces. Keeps letters and possibly numbers. ");
				System.out.println(" -i --stdin        Reads from STDIN rather than [filename]. ");
				System.out.println(" ");
				System.out.println("The last three can be used together and/or with upper or lower. ");
				System.out.println(" ");
				System.exit(0);
			} else if (arg.equals("--no-numbers")) {
				numbers = false;
			} else if (arg.equals("--no-spaces")) {
				spaces = false;
			} else if (arg.equals("--upper")) {
				uppercase = true;
			} else if (arg.equals("--lower")) {
				lowercase = true;
			} else if (arg.equals("--stdin")) {
				stdin = true;
			} else if (arg.startsWith("-")) {
				
				if (arg.startsWith("--")) {
					System.out.println(" ");
					System.out.println("Error 1: Unknown argument \"" + argument + "\". See the help page for more info. ");
					System.out.println(" ");
					System.exit(1);
				} else {
					if (arg.contains("n")) {
						numbers = false;
					} 
					if (arg.contains("s")) {
						spaces = false;
					}
					if (arg.contains("u")) {
						uppercase = true;
					}
					if (arg.contains("l")) {
						lowercase = true;
					}
					if (arg.contains("i")) {
						stdin = true;
					}
				}
				
				
			} else if (!(new File(argument).exists())){
				filename = argument + ".txt";
			} else {
				filename = argument;
			}
			
		}
		
	}
	
}




