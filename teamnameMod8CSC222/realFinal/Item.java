package realFinal;

import java.text.NumberFormat;

public abstract class Item {
	protected String description;
	protected double costEach;
	


	public Item() {
	
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return description + " , " + nf.format(costEach);
	}
	
	public Item(String description, double costEach) {
		this.description = description;
		this.costEach = costEach;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCostEach() {
		return costEach;
	}

	public void setCostEach(double costEach) {
		this.costEach = costEach;
	}
} 