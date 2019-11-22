package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.MachinePart;

public class MachinePartTest {

	MachinePart m;
	
	@Before
	public void onSetUp() {
		m = new MachinePart();
	}

	@Test
	public void test() {
		m.setId(0);
		assertEquals(0, m.getId());
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
	public void setTypeTest() {
		m.setType("abc");
		assertEquals("abc", m.getType());
	}
	
	@Test
	public void setCategoryTest() {
		m.setCategory("abc");
		assertEquals("abc", m.getCategory());
	}
	
	@Test
	public void setStartDateTest() {
		m.setStartDate("2019-11-20");
		assertEquals("2019-11-20", m.getStartDate());
		
		m.setStartDate("2019-11-2a");
		assertEquals("2019-11-20", m.getStartDate());
		
		m.setStartDate("abc");
		assertEquals("2019-11-20", m.getStartDate());
		
		m.setStartDate("20191120");
		assertEquals("2019-11-20", m.getStartDate());
	}
	
	@Test
	public void setLifeTimeTest() {
		m.setLifeTime(-1);
		assertEquals(0, m.getLifeTime());
		
		m.setLifeTime(0);
		assertEquals(0, m.getLifeTime());
		
		m.setLifeTime(1);
		assertEquals(1, m.getLifeTime());
	}
	
	@Test
	public void setTTLTest() {
		m.setTTL(-1);
		assertEquals(0, m.getTTL());
		
		m.setTTL(0);
		assertEquals(0, m.getTTL());
		
		m.setTTL(1);
		assertEquals(1, m.getTTL());
	}
	
	@Test
	public void setServiceTimeTest() {
		m.setServiceTime(-1);
		assertEquals(0, m.getServiceTime());
		
		m.setServiceTime(0);
		assertEquals(0, m.getServiceTime());
		
		m.setServiceTime(1);
		assertEquals(1, m.getServiceTime());
	}
	
	@Test
	public void setFaultTest() {
		m.setFault(-1);
		assertEquals(0, m.getFault());
		
		m.setFault(0);
		assertEquals(0, m.getFault());
		
		m.setFault(1);
		assertEquals(1, m.getFault());
		
		m.setFault(2);
		assertEquals(2, m.getFault());
		
		m.setFault(3);
		assertEquals(2, m.getFault());
	}
	
	@Test
	public void setFaultTimeTest() {
		m.setFaultTime(-1);
		assertEquals(0, m.getFaultTime());
		
		m.setFaultTime(0);
		assertEquals(0, m.getFaultTime());
		
		m.setFaultTime(1);
		assertEquals(1, m.getFaultTime());
	}
	
	@Test
	public void setValueTest() {
		m.setValue(-0.1);
		assertEquals(-0.1, m.getValue(), 0.0);
		
		m.setValue(0);
		assertEquals(0.0, m.getValue(), 0.0);
		
		m.setValue(0.1);
		assertEquals(0.1, m.getValue(), 0.0);
		
		m.setMinValue(2);
		m.setMaxValue(5);
		m.setValue(1);
		assertEquals(2, m.getFault());
		m.setValue(3);
		assertEquals(0, m.getFault());
		m.setValue(6);
		assertEquals(2, m.getFault());
	}
	
	@Test
	public void setMinValueTest() {
		m.setMinValue(-0.1);
		assertEquals(-0.1, m.getMinValue(), 0.0);
		
		m.setMinValue(0);
		assertEquals(0.0, m.getMinValue(), 0.0);
		
		m.setMinValue(0.1);
		assertEquals(0.1, m.getMinValue(), 0.0);
	}
	
	@Test
	public void setMaxValueTest() {
		m.setMaxValue(-0.1);
		assertEquals(-0.1, m.getMaxValue(), 0.0);
		
		m.setMaxValue(0);
		assertEquals(0.0, m.getMaxValue(), 0.0);
		
		m.setMaxValue(0.1);
		assertEquals(0.1, m.getMaxValue(), 0.0);
	}
	
	@Test
	public void getServicedTest() {
		int value = 20;
		m.setTTL(value);
		m.setServiceTime(0);
		assertEquals(value, m.getTTL());
		assertEquals(0, m.getServiceTime());
		
		m.getServiced();
		assertNotEquals(value, m.getTTL());
		assertEquals(1, m.getServiceTime());
		
		m.getServiced();
		assertNotEquals(value, m.getTTL());
		assertEquals(2, m.getServiceTime());
		
		m.getServiced();
		assertNotEquals(value, m.getTTL());
		assertEquals(3, m.getServiceTime());
		
		m.getServiced();
		assertNotEquals(value, m.getTTL());
		assertEquals(4, m.getServiceTime());
		
		m.getServiced();
		assertEquals(0, m.getTTL());
		assertEquals(5, m.getServiceTime());
	}
	
	@Test
	public void incAgeTest() {
		m.setTTL(1);
		m.incAge();
		assertEquals(0, m.getTTL());
		m.incAge();
		assertEquals(0, m.getTTL());
	}
	
	@Test
	public void incServiceTimeTest() {
		m.setServiceTime(0);
		m.incServiceTime();
		assertEquals(1, m.getServiceTime());
	}
	
	@Test
	public void toStringTest() {
		m = new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10);
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
				"maxValue: " + 10.0 + "\n", m.toString());
	}
	
}
