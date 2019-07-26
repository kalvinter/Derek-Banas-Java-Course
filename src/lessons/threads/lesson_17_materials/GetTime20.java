package lessons.threads.lesson_17_materials;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/*  Extends Thread -> inherits all necessary methods for Threading
 *  This is the sub-class method 
 * 
 *  PROBLEM: You can only sub-class one class!
 * 
 *  No other super-class can be added to GetTime20!
 * */ 
public class GetTime20 extends Thread {

	public void run() {
		
		Date rightNow;
		Locale currentLocale;
		DateFormat timeFormatter;
		DateFormat dateFormatter;
		String timeOutput;
		String dateOutput;
		
		for (int i = 1; i <= 20; i++) {
			
			rightNow = new Date();
			currentLocale = new Locale("en");
			timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
			dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
			timeOutput = timeFormatter.format(rightNow);
			dateOutput = dateFormatter.format(rightNow);
			
			System.out.println(timeOutput);
			System.out.println(dateOutput);
			System.out.println();
			
			/* You have to enclose the sleep function with a 
			 * try/catch block.
			 * 
			 * Why?
			 * 		Everytime thread.sleep is called it will throw
			 * 		a InterruptedException!
			 * */ 
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	
}
