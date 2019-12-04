package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Machine;
import source.MachinePart;
import source.Main;
import source.Regulator;

public class RegulatorTest {

	Main m = new Main();
	Regulator regulator = new Regulator();

	@Before
	public void onSetUp() {
		m = new Main();
		regulator = new Regulator();
	}

	@Test
	public void regulateTest() {
		Main main = new Main();

		String toRegulate[] = {"abc"};

		main.getMachines().add(new Machine(0, "acb"));
		main.getMachines().get(0).addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		main.getMachines().add(new Machine(1, "abcd"));
		main.getMachines().get(1).addNewMachinePart(new MachinePart(1, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));

		regulator.regulate(main.getMachines().get(0), toRegulate);
		regulator.regulate(main.getMachines().get(1), toRegulate);

		assertTrue(0 < main.getMachines().get(0).getMachinePart("abc").getValue());
		assertTrue(0 < main.getMachines().get(1).getMachinePart("abc").getValue());
	}
}
