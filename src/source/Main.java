package source;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	List<Machine> machines = new ArrayList<Machine>();
	
	public static void main(String[] args) {
		MachinePart m = new MachinePart();
		m = new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10);
		System.out.println(m.toString());
		
		Main main = new Main();
		main.machines.add(new Machine(0, "acb"));
		main.machines.get(0).addNewMachinePart(new MachinePart(0, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		main.machines.add(new Machine(1, "abcd"));
		main.machines.get(1).addNewMachinePart(new MachinePart(1, "abc", "abc", "abc", "2019-11-20", 20, 20, 0, 0, 0, 5, 0, 10));
		
		System.out.println(main.processLine("0;0;10"));
		System.out.println(main.processLine("0;1;10"));
	}
	
	public List<Machine> getMachines() {
		return machines;
	}
	
	public boolean processLine(String line) {
		String[] str = line.split(";");
		if(str.length == 3)
			for(int i = 0; i < machines.size(); i++) {
				if(machines.get(i).getId() == Integer.parseInt(str[0]))
					for(int j = 0; j < machines.get(i).getMachineParts().size(); j++) {
						if(machines.get(i).getMachineParts().get(j).getId() == Integer.parseInt(str[1])) {
							machines.get(i).getMachineParts().get(j).setValue(Double.parseDouble(str[2]));
							return true;
						}
					}
			}
					
		return false;
	}

}
