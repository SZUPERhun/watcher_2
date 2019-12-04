package source;

import java.util.List;
import java.util.ArrayList;

public class Machine {

	private int id;
	private String name;
	private List<MachinePart> machineParts = new ArrayList<MachinePart>();
	
	public Machine() {
		setId(0);
		setName("");
	}
	
	public Machine(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setId(int id) {
		if(id >= 0)
			this.id = id;
		else
			this.id = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<MachinePart> getMachineParts(){
		return machineParts;
	}
	
	public MachinePart addNewMachinePart(MachinePart machinePart) {
		machineParts.add(machinePart);
		return machinePart;
	}
	
	public void nextDay() {
		for(int i = 0; i < machineParts.size(); i++)
			machineParts.get(i).incAge();
	}
	
	public String toString() {
		String result = 
			"MACHINE: \n" +
			"id: " + id + "\n" +
			"name: " + name + "\n" +
			"============================\n" +
			"MACHINEPARTS: \n";
		
		int i = 0;
		for (MachinePart machinePart : machineParts) {
			result += machinePart;
			if (++i < machineParts.size()) {
				result += "---------------------------- \n";
			}
		
    	}
		result += "\n";
		
		return result;
	}
	
	public MachinePart getMachinePart(String name) {
		MachinePart result = machineParts.stream()
				  .filter(machinePart -> name.equals(machinePart.getName()))
				  .findAny()
				  .orElse(machineParts.get(0));
		return result;
	}
	
}
