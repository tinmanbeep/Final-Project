package realFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.text.NumberFormat;

public class Order {
	private LocalDate date;
	private Customer cust;
	private ArrayList<Item> items;
	private double cost;


	
	public Order() {
		
	}
	
	public Order(Customer cust,ArrayList<Item> items, double cost) {
		this.cust = cust;
		this.items = items;
		this.cost = cost;
		
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string = null;
		double tax = cost * .1;
		double total = cost + tax;
		
		string = "Customer: " + cust.getName() + "\nDate: " + date + "\n\nItems:\n";
		
		for (int i = 0; i < items.size(); i++) {
			string = string + items.get(i).toString() + "\n";
		}
		
		return string + "\nSubtotal: " + nf.format(cost) + "\nTax (10%): " + 
		nf.format(tax) + "\nTotal: " + nf.format(total) + "\n----------------";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}


