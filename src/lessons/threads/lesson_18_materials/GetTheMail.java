package lessons.threads.lesson_18_materials;


/* Implements Runnable-Interface
 * 
 * Advantage: You can still sub-class a class!
 * 
 * */
public class GetTheMail implements Runnable {

	private int startTime;
	
	public GetTheMail(int startTime) {
		this.startTime = startTime;
	}
	
	public void run() {
		try {
			/* StartTime * 1000 -> startTime is in seconds
			 * => if startTime is 10 -> it converts 10 seconds to milliseconds (* 1000)
			 * */
			Thread.sleep(startTime * 1000);
			
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("Checking Mail ...");
	}
	
}
