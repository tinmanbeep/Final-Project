package realFinal;

public class MarketItem extends Item{
	private double weight;

	public MarketItem() {
		
	}
	
	public MarketItem(String description, double costEach, double weight) {
		super(description,costEach);
		this.weight = weight;
		
	}
	
	public String toString() {
		String string = super.toString();
		return string + " , " + weight + " lbs";
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}