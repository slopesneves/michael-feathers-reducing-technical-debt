
package scheduler;


public class SchedulerDisplay implements Display {
	public void showEvent(Event event) {
		for(int n = 0; n < 1000; n++) {
			System.out.println("[" + event.getDate() + "]");	
		}
	}
}
