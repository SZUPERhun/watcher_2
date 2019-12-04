package source;

import java.util.concurrent.ThreadLocalRandom;

public class Regulator {

	public void regulate(Machine machine, String[] toRegulate) {
		for (String machinePartType : toRegulate) {
			machine.getMachinePart(machinePartType).setValue(calculateValue(
					machine.getMachinePart(machinePartType).getFault(),
					machine.getMachinePart(machinePartType).getValue(),
					machine.getMachinePart(machinePartType).getMinValue(),
					machine.getMachinePart(machinePartType).getMaxValue()
					));
		}
	}

	private double calculateValue(int fault, double val, double min, double max) {
		if (fault == 0) {
			if (min <= val && val <= max) {
				val = random(min, max);
				return val;
			}

			val += random(min, max) / 10;

			return val;
		}

		if (fault != 0) {
			if (val == 0) {
				return 0;
			}

			val -= random(min, max) / 15;
			if (val < 0) {
				return 0;
			}

			return val;
		}

		return 0;
	}

	private double random(double min, double max) {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}