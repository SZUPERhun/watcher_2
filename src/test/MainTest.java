package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Machine;
import source.MachinePart;
import source.Main;

public class MainTest {
	
	Main m;
	
	@Before
	public void onSetUp() {
		m = new Main();
	}

	@Test
	public void test() {
		Main main = new Main();
		main.getMachines().add(new Machine(0, "acb"));
		main.getMachines().get(0).addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		main.getMachines().add(new Machine(1, "abcd"));
		main.getMachines().get(1).addNewMachinePart(new MachinePart(1, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		
		assertEquals(true, main.processLine("0;0;10"));
		assertEquals(false, main.processLine("0;1;10"));
		assertEquals(true, main.processLine("1;1;10"));
		
		main.getMachines().get(0).addNewMachinePart(new MachinePart(1, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		assertEquals(true, main.processLine("0;1;10"));
	}

}
