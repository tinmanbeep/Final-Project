package realFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class JavaFoodDelivery {

	public static void main(String[] args) {
		
		boolean key = true;
		
		// Initializes ArrayLists
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<Customer> cust = new ArrayList<>();
	    ArrayList<Order> orders = new ArrayList<>(); 
	    
		// Runs while key = true
		while(key == true) {
			int userChoice = menu();
			
			// Loads data from text file
			if (userChoice == 1) {
				
				loadData(items,cust);
				
				System.out.println("\nData Loaded!");
			
			// Sorts and prints customers alphabetically - Elijah
			}else if (userChoice == 2) {
				
				// Makes sure isn't empty
				if (isEmpty(cust)) {
					System.out.println("Must input data first! \n");
					
				}else
					
					// Prints info and sorts alphabetically
					System.out.println("Customers in alphabetical order:");
					Collections.sort(cust,new AlphaComparator());
					
					// Prints customers
					for (int i = 0; i < cust.size(); i++) {
						System.out.println(cust.get(i).toString());
					}

			// Prints items for sale
			}else if (userChoice == 3) {
				
				// Makes sure isn't empty
				if (isEmpty(items)) {
					System.out.println("Must input data first! \n");
					
				}else
					
					itemsForSale(items);
					
			// Creates an order - Jordan
			}else if (userChoice == 4) {
				createOrder(cust, items, orders);
				
			// Searches for item by name or partial name
			}else if (userChoice == 5) {
				
				// Makes sure isn't empty
				if (isEmpty(items)) {
					System.out.println("Must input data first!");
					
				}else
					
					searchItem(items);
	            
			// Sorts orders by date - Cole
			}else if (userChoice == 6) {
				Collections.sort(orders, new DateComparator());
				
				for (int i = 0; i < orders.size(); i++) {
					System.out.println("Order " + (i+1) + ": \n" + orders.get(i).toString());
				}
				
			// Saves info to files - Jordan
			}else if (userChoice == 7) {
			    System.out.println("Saving data to file...");
			    saveAllDataToFile(cust, items, orders);
			   
			// Reads customer, inventory, and orders back
			}else if (userChoice == 8) {
				printOut(items,cust,orders);
				
			// Total revenue
			}else if (userChoice == 9) {
				totalRevenue(orders);
			
			// Exits program
	        } else if (userChoice == -1) {
				key = false;
				System.out.println("Goodbye!");
	        }	
		}
			
	}

	// Prints menu and returns user choice number - Elijah
	public static int menu() {
		Scanner scan = new Scanner(System.in);
		
		int userChoice;
		
		// prints menu choices to user
		System.out.println("\nJava Food Delivery... at your doorstep in minutes!");
		System.out.println("Choose from the following:");
		System.out.println("1. Load fake data");
		System.out.println("2. List all customers alphabetically");
		System.out.println("3. List all items for sale");
		System.out.println("4. Create an order");
		System.out.println("5. Search for the name or partial name of an item");
		System.out.println("6. List all orders arranged by dates");
		System.out.println("7. Save the customers, inventory and orders to a file");
		System.out.println("8. Read current customers, inventory and orders back ");
		System.out.println("9. Show total revenue");
		System.out.println("10. ");
		System.out.println("-1. End program");
		
		userChoice = scan.nextInt();
		
		return userChoice;
		
	}
	
	// Menu Item 1: Loads data from text file - Elijah
	public static void loadData(ArrayList<Item> items, ArrayList<Customer> cust) {
		String fileName = FileLoc.pickLocRead();
		
		ArrayList<String> fileArray = new ArrayList<>();
		ArrayList<String> splitLines = new ArrayList<>();
		
		File file = new File(fileName);
		
	Scanner inputFile = null;
			
			// tries unless IOException
			try {
				
				inputFile = new Scanner(file);
				
				while (inputFile.hasNext()) {
					
					String line = inputFile.nextLine();
					
					fileArray.add(line);
					
			}
			
			// catches IOException
			}catch (IOException ioe) {
				System.out.println("IO Exception.  Need to exit");
				System.exit(0);
					
			}finally {
				inputFile.close();
			}
			
	
			for (int i = 0; i < fileArray.size();i++) {
				String line = fileArray.get(i);
				
				// string tokenizer that splits by " "
				StringTokenizer st = new StringTokenizer(line,",");
				
				// tokens per line
				int tpl = st.countTokens();
				
				//runs for size of tpl
				for (int j = 0; j < tpl; j++) {
					
					//adds next token to splitLines 
					splitLines.add(st.nextToken().trim());
				
				}
			}
				
			for (int e = 0; e < splitLines.size();e++) {
				
				// current string for loop is on
				String current = splitLines.get(e);

				if (current.equals("Cust:")) {
					
					String name = splitLines.get(e+1);
					String number = splitLines.get(e+2);
					int id = Integer.valueOf(splitLines.get(e+3)).intValue();
					
					cust.add(new Customer(name,number,id));
					
				}else if(current.equals("Pizza:")) {
					
					String name = splitLines.get(e+1);
					double price = (double) Double.valueOf(splitLines.get(e+2)).doubleValue();
					String crust = splitLines.get(e+3);
					String size = splitLines.get(e+4);
					
					items.add(new PizzaItem(name,price,crust,size));
					
				}else if (current.equals("Market:")) {
					
					String name = splitLines.get(e+1);
					double price = (double) Double.valueOf(splitLines.get(e+2)).doubleValue();
					int weight = Integer.valueOf(splitLines.get(e+3).trim()).intValue();
					
					items.add(new MarketItem(name,price,weight));
				}
			}
			
	}
	
	// Menu Item 3: Prints item for sale - Cole
	public static void itemsForSale(ArrayList<Item> items) {
	    System.out.println("Items for Sale:");
	    for (Item item : items) {
	        System.out.println(item.toString());
	        }
	    }	
	
	// Menu Item 4: Creates order - Jordan
	public static void createOrder(ArrayList<Customer> cust, ArrayList<Item> items, ArrayList<Order> orders) {
		
	    Scanner scanner = new Scanner(System.in);
	    
	    int count = 0;

	    // show customers and let user choose or add a new one
	    System.out.println("List of Customers:");
	    for (int i = 0; i < cust.size(); i++) {
	        System.out.println((i + 1) + ". " + cust.get(i));
	    }
	    System.out.println((cust.size() + 1) + ". Add a new customer");

	    System.out.print("Choose a customer by number: ");
	    int custChoice = scanner.nextInt();
	    scanner.nextLine();
	    Customer selectedCustomer;

	    if (custChoice == cust.size() + 1) {
	        System.out.print("Enter name: ");
	        String name = scanner.nextLine();
	        
	        String contactPhone;
	        
	        while (true) {
	            System.out.print("Enter contact phone (format: xxx-xxx-xxxx or xxxxxxxxxx): ");
	            contactPhone = scanner.nextLine();
	            
	            if (contactPhone.matches("\\d{3}-\\d{3}-\\d{4}") || contactPhone.matches("\\d{10}")) {
	                break;
	            } else {
	                System.out.println("Invalid phone number format. Please try again.");
	            }
	        }

	        int id;
	        
	        while (true) {
	            System.out.print("Enter ID (numeric only): ");
	            if (scanner.hasNextInt()) {
	            	
	                id = scanner.nextInt();
	                scanner.nextLine();
	                break;
	                
	            } else {
	            	
	                System.out.println("Invalid ID. Please enter a valid number.");
	                scanner.next();
	            }
	        }

	        selectedCustomer = new Customer(name, contactPhone, id);
	        cust.add(selectedCustomer);
	        
	    } else {
	        selectedCustomer = cust.get(custChoice - 1);
	    }

	    //date validation
	    LocalDate orderDate;
	    while (true) {
	        System.out.println("\nEnter the date for the order (YYYY-MM-DD) or press Enter to use today's date:");
	        String dateInput = scanner.nextLine().trim();
	        if (dateInput.isEmpty()) {
	            orderDate = LocalDate.now();
	            break;
	        }
	        try {
	            orderDate = LocalDate.parse(dateInput);

	            LocalDate today = LocalDate.now();
	            if (orderDate.isBefore(today)) {
	                System.out.println("Invalid date. The order date cannot be in the past.");
	            } else {
	                break;
	            }
	        } catch (DateTimeParseException e) {
	            System.out.println("Invalid date format or date. Please use a valid date in YYYY-MM-DD format.");
	        }
	    }

	    //check if items are available
	    if (items.isEmpty()) {
	        System.out.println("No items are currently available.");
	        System.out.print("Would you like to add items to the inventory? (yes/no): ");
	        String addItemsResponse = scanner.nextLine().trim().toLowerCase();
	        if (addItemsResponse.equals("yes")) {
	            addItemsToInventory(scanner, items);
	        } else {
	            System.out.println("No items added. Exiting order creation.");
	            //if no items were added
	            return;
	        }
	    }

	    //continue with adding items to the order
	    ArrayList<Item> orderItems = new ArrayList<>();
	    boolean addMoreItemsToOrder = true;

	    while (addMoreItemsToOrder) {
	        System.out.println("\nAvailable Items:");
	        for (int i = 0; i < items.size(); i++) {
	            System.out.println((i + 1) + ". " + items.get(i));
	        }
	        System.out.println((items.size() + 1) + ". Add a new item");

	        System.out.print("Choose an item by number: ");
	        int itemChoice = scanner.nextInt();
	        scanner.nextLine(); 

	        if (itemChoice == items.size() + 1) {
	            //add new item
	            addItemsToInventory(scanner, items);
	        } else if (itemChoice > 0 && itemChoice <= items.size()) {
	            orderItems.add(items.get(itemChoice - 1));

	            System.out.print("Do you want to add more items to the order? (yes/no): ");
	            addMoreItemsToOrder = scanner.nextLine().equalsIgnoreCase("yes");
	        } else {
	            System.out.println("Invalid choice. Please select a valid item number.");
	        }
	    }

	    //order summary
	    System.out.println("\nOrder Summary:");
	    double totalCost = 0;
	    for (Item item : orderItems) {
	        totalCost += item.costEach;
	    }
	    

	    //Save order information
	    Order newOrder = new Order(selectedCustomer, orderItems, totalCost); 
	    newOrder.setDate(orderDate);
	    orders.add(newOrder);
	    
	    System.out.println(orders.get(count).toString());
	    
	    
	    System.out.println("Order saved successfully!\n");
	    
	    count++;
	}

	// Method to add items to the inventory - Jordan
	public static void addItemsToInventory(Scanner scanner, ArrayList<Item> items) {
	    boolean addNewItem = true;
	    while (addNewItem) {
	        System.out.print("Enter item description: ");
	        String description = scanner.nextLine();
	        System.out.print("Enter item cost: ");
	        double cost = scanner.nextDouble();
	        System.out.print("Is this item a Pizza (yes/no)? ");
	        scanner.nextLine(); 
	        String isPizza = scanner.nextLine();

	        if (isPizza.equalsIgnoreCase("yes")) {
	            System.out.print("Enter crust type: ");
	            String crustType = scanner.nextLine();
	            System.out.print("Enter size: ");
	            String size = scanner.nextLine();
	            items.add(new PizzaItem(description, cost, crustType, size));
	        } else {
	            System.out.print("Enter weight: ");
	            double weight = scanner.nextDouble();
	            items.add(new MarketItem(description, cost, weight));
	            scanner.nextLine(); 
	        }

	        System.out.println("Item added successfully!");
	        System.out.println("Returning to available items...");
	      //return to available items
	        addNewItem = false; 
	    }
	}

	// Saves customers to a file - Jordan
	private static void saveAllDataToFile(ArrayList<Customer> customers, ArrayList<Item> items,  ArrayList<Order> orders) {
	    String filePath = FileLoc.pickLocOut(); // Use FileLoc to pick the save location
	    if (filePath == null || filePath.isEmpty()) {
	        System.out.println("No file selected. Data not saved.");
	        return;
	    }

	    // makes sure the file ends with .txt
	    if (!filePath.endsWith(".txt")) {
	        filePath += ".txt";
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
	        //write customers
	        for (Customer customer : customers) {
	            writer.write(String.format("Cust: , %s , %s , %d", 
	                customer.getName(), customer.getContactPhone(), customer.getId()));
	            writer.newLine();
	        }
	        writer.newLine();

	        // write items (pizza) 
	        for (Item item : items) {
	            if (item instanceof PizzaItem) {
	                PizzaItem pizza = (PizzaItem) item;
	                writer.write(String.format("Pizza: , %s , %.2f , %s , %s", 
	                    pizza.getDescription(), pizza.getCostEach(), pizza.getCrustType(), pizza.getSize()));
	                writer.newLine();
	            }
	        }

	        // write items (market)
	        for (Item item : items) {
	            if (item instanceof MarketItem) {
	                MarketItem market = (MarketItem) item;
	                double weight = market.getWeight();
	                // Save weight as an integer if it has no fractional part
	                String weightFormatted = (weight == (int) weight) ? String.valueOf((int) weight) : String.valueOf(weight);
	                writer.write(String.format("Market: , %s , %.2f , %s", 
	                    market.getDescription(), market.getCostEach(), weightFormatted));
	                writer.newLine();
	            }
	        }
	        // write orders
	        for (Order order : orders) {
	            writer.write("Order: , " + order.getCust().getName() + " , " + order.getDate() + " , Items: [");
	            for (Item orderItem : order.getItems()) {
	                if (orderItem instanceof PizzaItem) {
	                    PizzaItem pizza = (PizzaItem) orderItem;
	                    writer.write(String.format("(Pizza: , %s , %.2f , %s , %s), ",
	                        pizza.getDescription(), pizza.getCostEach(), pizza.getCrustType(), pizza.getSize()));
	                } else if (orderItem instanceof MarketItem) {
	                    MarketItem market = (MarketItem) orderItem;
	                    writer.write(String.format("(Market: , %s , %.2f , %.1f), ",
	                        market.getDescription(), market.getCostEach(), market.getWeight()));
	                }
	            }
	            writer.write("]");
	            writer.newLine();
	        }
	        writer.newLine();
	        
	        System.out.println("All data saved to: " + filePath);
	    } catch (IOException e) {
	        System.out.println("Error saving data to file: " + e.getMessage());
	    }
	}
	
	// Menu Item 5: Searches for item by name - Cole
	public static void searchItem(ArrayList<Item> items) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the name or partial name of the item:");
        String query = scan.nextLine().toLowerCase();
        boolean found = false;

        for (Item item : items) {
            if (item.getDescription().toLowerCase().contains(query)) {
                System.out.println(item.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items found matching the query.");
        }
        
        System.out.println("\n");
    }
	
	// Menu Item 8: Prints current items, customers, and orders - Elijah
	public static void printOut(ArrayList<Item> items, ArrayList<Customer> cust, ArrayList<Order> orders) {
		
		// Prints inventory
		System.out.println("Current Inventory:");
		for (int i = 0; i < items.size(); i ++) {
			System.out.println(items.get(i).toString());
		}
		
		// Prints customers
		System.out.println("\nCurrent Customers:");
		for (int c = 0; c < cust.size();c++) {
			System.out.println(cust.get(c).toString());
		}
		
		// Prints orders
		System.out.println("\nCurrent Orders: ");
		for (int o = 0; o < orders.size(); o++) {
			System.out.println(orders.get(o).toString());
		}
	}
	
	// Menu Item 9: Determines total revenue - Elijah
	public static void totalRevenue(ArrayList<Order> orders) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double totalRevenue = 0;
		double withTax = 0;
		
		
		for (int i = 0; i < orders.size(); i++) {
			ArrayList<Item> items = orders.get(i).getItems();
			for (int j = 0; j < items.size(); j++) {
				withTax = withTax + (((items.get(j).getCostEach()) * .1) + items.get(j).getCostEach());
				totalRevenue = totalRevenue + items.get(j).getCostEach();
			}
		}
		
		System.out.println("Total Revenue: " + nf.format(totalRevenue));
		System.out.println("Total Revenue (With Tax): " + nf.format(withTax));
	}
	
	// Method that checks if an ArrayList is empty - Elijah
	public static boolean isEmpty(ArrayList arraylist) {
		return arraylist.isEmpty();
	}
	

}

