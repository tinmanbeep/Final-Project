package realFinal;

// Cole

import java.util.Comparator;
import java.time.LocalDate;

public class DateComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
    	// Check if either of the Order objects is 0 and also makes sure it doesn't operate on 0 which could cause errors.
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Orders to compare cannot be null");
        }
        //This is for retrieving the actual data from Order
        LocalDate date1 = o1.getDate();
        LocalDate date2 = o2.getDate();
        //This checks to see if either dates are 0 since that would also cause errors
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Order dates cannot be null");
        }

        //Returns the information
        return date1.compareTo(date2);
    }
}
