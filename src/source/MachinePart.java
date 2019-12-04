package source;

public class MachinePart {

	public MachinePart() {

	}

	private int id;
	private String name;
	private String type;
	private String category;
	private String startDate;
	private int lifeTime;
	private int TTL;
	private int serviceTime = 0;
	private int fault = 0;
	private int faultTime = 0;
	private int initFault = 0;
	private double value = 0;
	private double minValue = 0;
	private double maxValue = 0;

	public MachinePart(int id, String name, String type, String category, String startDate, int lifeTime) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
		this.startDate = startDate;
		this.lifeTime = lifeTime;
		this.TTL = lifeTime;
	}

	public MachinePart(int id, String name, String type, String category, String startDate, int lifeTime, int minValue, int maxValue) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
		this.startDate = startDate;
		this.lifeTime = lifeTime;
		this.TTL = lifeTime;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public MachinePart(int id, String name, String type, String category, String startDate, int lifeTime, int TTL, int serviceTime, int fault, int faultTime, int value, int minValue, int maxValue) {
		setId(id);
		setName(name);
		setType(type);
		setCategory(category);
		setStartDate(startDate);
		setLifeTime(lifeTime);
		setTTL(TTL);
		setFaultTime(faultTime);
		setFault(fault);
		setFaultTime(faultTime);
		setMinValue(minValue);
		setMaxValue(maxValue);
		setValue(value);
	}

	public void setId(int id) {
		if(id >= 0)
			this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setStartDate(String startDate) {
		//this.startDate = startDate;
		String[] str = startDate.split("-");
		if(str.length == 3) {
			for(int i = 0; i < str.length; i++)
				for(int j = 0; j < str[i].length(); j++)
					if(str[i].charAt(j) > '9' || str[i].charAt(j) < '0')
						return;
			this.startDate = startDate;
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setLifeTime(int lifeTime) {
		if(lifeTime >= 0)
			this.lifeTime = lifeTime;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public void setTTL(int TTL) {
		if(TTL >= 0)
			this.TTL = TTL;
	}

	public int getTTL() {
		return TTL;
	}

	public void setServiceTime(int serviceTime) {
		if(serviceTime >= 0)
			this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}


	public void setFaultTime(int faultTime) {
		if(faultTime >= 0)
			this.faultTime = faultTime;
	}

	public int getFaultTime() {
		return faultTime;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public void getServiced() {
		incServiceTime();
		if(serviceTime == 1)
			TTL = (int)(lifeTime * 0.8);
		else if(serviceTime == 2)
			TTL = (int)(lifeTime * 0.6);
		else if(serviceTime == 3)
			TTL = (int)(lifeTime * 0.4);
		else if(serviceTime == 4)
			TTL = (int)(lifeTime * 0.2);
		else if(serviceTime <= 5)
			TTL = 0;
	}

	public void incAge() {
		if(TTL > 0)
			TTL--;

	}

	public void incServiceTime() {
		serviceTime++;
	}

	public void setValue(double val) {
		value = val;
		if (initFault == 0) {
			if (value <= maxValue && value >= minValue) {
				initFault = 1;
			}
		}
		if (0 < initFault && fault != 1 && (value > maxValue || value < minValue))
			fault = 2;
		if(TTL <= 0)
			fault = 1;
	}

	public String toString() {
		return
			/*"id: " + id + "\n" +
			*/"name: " + name + "\n" +/*
			"type: " + type + "\n" +
			"category: " + category + "\n" +
			"startDate: " + startDate + "\n" +
			"lifeTime: " + lifeTime + "\n" +
			"TTL: " + TTL + "\n" +
			"serviceTime: " + serviceTime + "\n" +
			*/"fault: " + fault + "\n" +
			//"faultTime: " + faultTime + "\n" +
			"value: " + value + "\n" +
			"minValue: " + minValue + "\n" +
			"maxValue: " + maxValue + "\n";
	}

	public void setFault(int value) {
		fault = value;
	}

	public void setInitFault(int value) {
		initFault = value;
	}

	public int getFault() {
		return fault;
	}

	public String getType() {
		return type;
	}

	public String getCategory() {
		return category;
	}

	public double getValue() {
		return value;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public String getName() {
		return name;
	}

}
