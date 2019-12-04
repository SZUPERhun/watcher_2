package source;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
	private Timer t;

	public Scheduler() {
		t = new Timer();
	}

	public void start(List<Machine> machines) {
		Regulator regulator = new Regulator();
		String toRegulate[] = {"tertmp", "szelltmp"};
		t.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	for (Machine machine : machines) {
		    		System.out.println(machine);
		    		regulator.regulate(machine, toRegulate);
		    	}

		    }
		}, 0, 500);
	}

	public void stop() {
		t.cancel();
	}
}
