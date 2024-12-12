package realFinal;

public class Customer {
	private String name;
	private String contactPhone;
	private int id;
	
	
	public Customer() {
		
	}
	
	public Customer(String name, String contactPhone, int id) {
		this.name = name;
		this.contactPhone = contactPhone;
		this.id = id;
		
	}
	
	public String toString() {
		return name + " , " + contactPhone + " , " + id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

