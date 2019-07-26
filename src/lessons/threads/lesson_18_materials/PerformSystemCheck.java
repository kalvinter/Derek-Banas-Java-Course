package lessons.threads.lesson_18_materials;

// Locks -> locking a method / variable. 
// Avoid two threads accessing it at the same time
import java.util.concurrent.locks.*;


public class PerformSystemCheck implements Runnable {

	private String checkWhat;
	
	ReentrantLock lock = new ReentrantLock();
	
	public PerformSystemCheck(String checkWhat) {
		this.checkWhat = checkWhat;
		
	}
	
	/* synchronized -> method is locked down. 
	 * Only one thread at a time can use
	 * Advantage: Very clear, very easy. Avoids lock-release bugs
	 * Disadvantage: Inflexible. Entire function is synchronized
	 * 
			synchronized public void run() {
				
			}
	* */
	
	/* manual use of lock()
	 * Advantages: More flexible, suitable for complicated scenarios
	 * Disadvantage: lock-unlock-bugs can occur (forget unlock, forget finally unlock statements etc.)
	 * 
	 * */
	public void run() {
		lock.lock();
		
		try {
			System.out.println("Checking " + this.checkWhat);
		
		} finally {
			// Always ensure to unlock using finally!
			lock.unlock();	
		}
	}
	
	
}
