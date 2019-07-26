package lessons.threads;

/* TOPIC: Threading Basics
 * 
 * */

import lessons.threads.lesson_17_materials.*;

public class Lesson17 {

	public static void main(String[] args) {
		
		Thread getTime = new GetTime20();
		
		Runnable getMail = new GetTheMail(4);
		Runnable getMailAgain = new GetTheMail(8);
		
		getTime.start();
		
		/* Threads constructed by implementing the Runnable Interface 
		 * have to be converted to a Thread-Object separately
		 * */
		new Thread(getMail).start();
		new Thread(getMailAgain).start();
	}

	
}
