package realFinal;

public class PizzaItem extends Item{
	private String crustType;
	private String size;
	
	
	public PizzaItem() {
		
	}
	
	public PizzaItem(String description, double costEach, String crustType, String size) {
		super(description,costEach);
		this.crustType = crustType;
		this.size = size;
	}
	
	public String toString() {
		String string = super.toString();
		return string + " , " + crustType + " , " + size;
	}

	public String getCrustType() {
		return crustType;
	}

	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}


