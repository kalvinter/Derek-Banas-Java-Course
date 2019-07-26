package lessons.threads.lesson_18_materials;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/* Implements Runnable:
 * 		=> Could extend a sub-class
 * 
 * */ 
public class CheckSystemTime implements Runnable{

	public void run() {
		
		Date rightNow;
		Locale currentLocale;
		DateFormat timeFormatter;
		String timeOutput;
		
			
		rightNow = new Date();
		currentLocale = new Locale("en");
		timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
		timeOutput = timeFormatter.format(rightNow);
		
		System.out.println(timeOutput);
			
	}
	
	
}
