package lessons.files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* TOPIC: Read/Write Binary Streams
 * 
 * */

public class Lesson33 {
	public static File baseDir;
	
	public static void main(String[] args) 
	{
		try {
			baseDir = new File("src/resources/Lesson33");
			baseDir.mkdir();	
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// START LESSON 33
		
		Customer[] customers = getCustomers();
		
		/* DataOutputStream -> writes primitive data types to an output
		 * */
		DataOutputStream custOutput = createFile(baseDir + "/customer.dat");
		
		for (Customer person : customers)
		{
			createCustomers(person, custOutput);
		}
		
		try {
			custOutput.close();
		} catch (IOException e) {
			System.out.println("IOError occurred!");
			System.exit(0);
		}
		
		System.out.println("Info Written to File!");
		
		getFileInfo(baseDir + "/customer.dat");
		
	} // END OF MAIN
	
	private static class Customer
	{
		public String custName;
		public int custAge;
		public double custDebt;
		public boolean oweMoney;
		public char custSex;
		
		public Customer(String custName, int custAge, double custDebt, 
				boolean oweMoney, char custSex)
		{
			this.custName = custName;
			this.custAge = custAge;
			this.custDebt = custDebt;
			this.oweMoney = oweMoney;
			this.custSex = custSex;
		}
	} // END OF CUSTOMER CLASS
	
	private static Customer[] getCustomers() 
	{
		Customer[] customers = new Customer[5];
		
		customers[0] = new Customer("John Smith", 22, 443.23, true, 'm');
		customers[1] = new Customer("Sue Smith", 32, 210.99, true, 'f');
		customers[2] = new Customer("Paul Smith", 35, 0, false, 'm');
		customers[3] = new Customer("Julia Smith", 28, 507.83, true, 'f');
		customers[4] = new Customer("Mark Smith", 20, 0, false, 'm');
		
		return customers;
	}
	
	private static DataOutputStream createFile(String filePath)
	{
		try {
			 
			File listOfNames = new File(filePath);
			
			/* DataOutputStream -> transforms values to stream
			 * 
			 * BufferedOutputStream -> receives DataStream and buffers. 
			 * Only forwards Chunks of DataStreams for writing to File and 
			 * not continuously one unit at a time
			 * 
			 * FileOutputStream -> writes the received Stream to a File
			 * 
			 * CAREFUL: FileOutputStream(File-Object, boolean append)
			 * 	-> append 
			 * 		-> if true retains old data and just appends new data
			 * 		-> if false overwrites all existing data in the file
			 * */
			DataOutputStream infoToWrite = new DataOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(listOfNames, false)
							)
					);
			return infoToWrite;
			
		} catch (IOException e) {
			System.out.println("IOError occurred!");
			System.exit(0);
		}
		
		return null;
	}
	
	private static void createCustomers(Customer customer, DataOutputStream custOutput)
	{
		try {
			/* This is an OutputStream aka very raw writing to File
			 * writeUTF() -> When writing text-file an encoding has to be specified
			 * */ 
			custOutput.writeUTF(customer.custName);
			custOutput.writeInt(customer.custAge);
			custOutput.writeDouble(customer.custDebt);
			custOutput.writeBoolean(customer.oweMoney);
			custOutput.writeChar(customer.custSex);
			
		} catch (IOException e) {
			System.out.println("IOError occurred!");
			System.exit(0);
		}
	}
	
	private static void getFileInfo(String filePath)
	{
		File listOfNames = new File(filePath);
		boolean eof = false;
		
		try {
			DataInputStream getInfo = new DataInputStream(
					new BufferedInputStream(
							new FileInputStream(listOfNames)
							)
					);
			
			while (!eof) 
			{
				String custName = getInfo.readUTF();
				int custAge = getInfo.readInt();
				double custDebt = getInfo.readDouble();
				boolean oweMoney = getInfo.readBoolean();
				char custSex = getInfo.readChar();
				
				System.out.println(custName + ", " + custAge + ", " + custDebt + ", " + oweMoney + ", " + custSex);
			}
			
			// This one will in this code never be printed!
			// EOF-exception occurs and ends the method
			System.out.println("Broke out of while (!eof) loop");
					
		} catch (EOFException e) {
			System.out.println("EOF Exception reached");
			eof = true;
			
		} catch (IOException e) {
			System.out.println("IOError occurred!");
			System.exit(0);
		}
		
	}
}
