package lessons.files;

/* TOPIC: Reading/Writing to Files using CharacterStreams
 * 
 * */

import java.io.*;

public class Lesson32 {

	private static File fileDir = new File("src/Lesson32");
	
	public static void main(String[] args) 
	{
		// Create resource directory for this lesson
		fileDir.mkdir();
		
		if (!fileDir.exists()) {
			System.out.println("Could not create necessary file-directory in resources");
			System.exit(0);
		}
		
		// Start Derekt Banas Lesson part
		Customer[] customers = getCustomers();
		
		PrintWriter custOutput = createFile("src/resources/Lesson32_customer.txt");
		
		for (Customer person : customers)
		{
			createCustomers(person, custOutput);
		}
		
		custOutput.close();  // Close the file once you are finished!
		
		getFileInfo();
		
	}  // END OF main METHOD
	
	private static class Customer
	{
		public String firstName, lastName;
		public int custAge;
		
		public Customer(String firstName, String lastName, int custAge)
		{
			this.firstName = firstName;
			this.lastName = lastName;
			this.custAge = custAge;
		}
	}
	
	private static Customer[] getCustomers()
	{
		Customer[] customers = new Customer[5];
		
		customers[0] = new Customer("John", "Smith", 21);
		customers[1] = new Customer("Julie", "Smith", 26);
		customers[2] = new Customer("Alex", "Smith", 29);
		customers[3] = new Customer("Harald", "Smith", 34);
		customers[4] = new Customer("Marie", "Smith", 55);
		
		return customers;
	}
	
	private static PrintWriter createFile(String filePath)
	{
		try {
			File listOfNames = new File(filePath);
			
			/* PrintWriter uses a BufferedWriter
			 * In other cases it could also be used as a different writer to 
			 * write to console. But we redirect it to the BufferedWriter.
			 * 
			 * BufferedWriter -> gather a bunch of characters and write them at once
			 * Otherwise, the writer would write one char at a time which
			 * is not very efficient
			 * (only writes to memory)
			 * 
			 * Inside of the BUfferedWriter -> FileWriter
			 * FileWriter gets chars to write from the BufferedWriter to the File
			 * (actual object that is interacting with disk)
			 * */
			PrintWriter infoToWrite = new PrintWriter(
					new BufferedWriter(
							new FileWriter(listOfNames)));
			return infoToWrite;
					
		} catch (IOException e) {
			System.out.println("An IO Erro occurred");
			System.exit(0);
		}
		return null;
	}
	
	private static void createCustomers(Customer customer, PrintWriter custOutput)
	{
		String custInfo = customer.firstName + " " + customer.lastName + " ";
		
		custInfo += Integer.toString(customer.custAge);
		
		custOutput.println(custInfo);
	}
	
	private static void getFileInfo()
	{
		System.out.println("\nInfo written to File:\n");
		
		File listOfNames = new File("src/resources/Lesson32_customer.txt");
		
		try {
			BufferedReader getInfo = new BufferedReader(
					new FileReader(listOfNames));
			
			/* .readline()
			 * Reads one line at a time
			 * If it reaches the end of the file, the last sign will be null
			 * 
			 * During instantiation custInfo draws the first line for initial checking
			 * In each cycle the old line (that was just checked) is printed
			 * and a new line is fed in
			 * */ 
			String custInfo = getInfo.readLine();
			
			while (custInfo != null)
			{
				
				// Split string at whitespaces -> for csv split it at "," or ";"
				String[] indivCustData = custInfo.split(" ");
				
				// Draw out custAge and parse it as int 
				//(if you would want to do anything with it - 
				// here its just printed making this step obsolete)
				int custAge = Integer.parseInt(indivCustData[2]);
				
				System.out.println("Customer " + indivCustData[0] + " is " + custAge);
				custInfo = getInfo.readLine();
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("An IO Error occurred!");
			System.exit(0);
		}
	}
	
}
