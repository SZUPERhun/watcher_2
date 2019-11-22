package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Machine;
import source.MachinePart;

public class MachineTest {

	Machine m;
	
	@Before
	public void onSetUp() {
		m = new Machine();
	}
	
	@Test
	public void setIdTest() {
		m.setId(-1);
		assertEquals(0, m.getId());
		
		m.setId(0);
		assertEquals(0, m.getId());
		
		m.setId(1);
		assertEquals(1, m.getId());
	}
	
	@Test
	public void setNameTest() {
		m.setName("abc");
		assertEquals("abc", m.getName());
	}

	@Test
	public void AddNewMachinePartTest() {
		assertEquals("id: " + 0 + "\n" +
				"name: " + "abc" + "\n" +
				"type: " + "abc" + "\n" +
				"category: " + "abc" + "\n" +
				"startDate: " + "2019-11-20" + "\n" +
				"lifeTime: " + 20 + "\n" +
				"TTL: " + 20 + "\n" +
				"serviceTime: " + 0 + "\n" +
				"fault: " + 0 + "\n" +
				"faultTime: " + 0 + "\n" +
				"value: " + 5.0 + "\n" +
				"minValue: " + 0.0 + "\n" +
				"maxValue: " + 10.0 + "\n", m.addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10)).toString());
	}
	
	@Test
	public void getMachinePartTest() {
		m.addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 10, 10, 0, 0, 0, 5, 0, 10));
		m.addNewMachinePart(new MachinePart(1, "abc1", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		assertEquals(0, m.getMachinePart("abc").getId());
		assertEquals(1, m.getMachinePart("abc1").getId());
	}
	
	@Test
	public void NewDayTest() {
		m.addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 10, 10, 0, 0, 0, 5, 0, 10));
		m.addNewMachinePart(new MachinePart(1, "abc1", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		m.nextDay();
		assertEquals(9, m.getMachinePart("abc").getTTL());
		assertEquals(19, m.getMachinePart("abc1").getTTL());
	}


}
