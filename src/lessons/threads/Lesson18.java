package lessons.threads;

/*TOPIC: Threading Detailed
 * 
 * */

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit.*;

import lessons.threads.lesson_18_materials.*;

public class Lesson18 {
	
	public static void main(String[] args) {
		
		addThreadsToPool();
		
	}
	
	public static void addThreadsToPool() {
		// Create a pool of threads 
		// -> holds, manages and coordinates all threads in the pool
		ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(5);
		
		/* Create three Threads!
		 * .scheduleAtFixedRate() -> Create fixed time schedule for execution 
		 * 1) Command
		 * 2) InitialDelay in time units
		 * 3) Repeat every X time units
		 * 4) time units
		 * */
		eventPool.scheduleAtFixedRate(new CheckSystemTime(), 0, 2, TimeUnit.SECONDS);
		eventPool.scheduleAtFixedRate(new PerformSystemCheck("Mail"), 5, 2, TimeUnit.SECONDS);
		eventPool.scheduleAtFixedRate(new PerformSystemCheck("Calender"), 7, 2, TimeUnit.SECONDS);
		
		// There are 4 Threads running -> 1 Main Threads + 3 newly added Threads
		System.out.println("Number of Threads running: " + Thread.activeCount());
			
		Thread[] listOfThreads = new Thread[Thread.activeCount()];
		
		// takes all current threads and fills it in the array
		Thread.enumerate(listOfThreads);
		
		for (Thread i : listOfThreads) {
			// Priorities: 1 (lowest) -> 10 (highest) Priority
			System.out.println(i.getName() + " (PRIO: " + i.getPriority() + ")");
		}
		
		try {
			Thread.sleep(10 * 1000);
			
		} catch ( InterruptedException e ) {
			
		}
		
		System.out.println("Shutting down thread-pool");
		// Stop Threads by calling .shutdown();
		eventPool.shutdown();
		System.out.println("Thread-pool successfully shut down");
		
	}
	
}
