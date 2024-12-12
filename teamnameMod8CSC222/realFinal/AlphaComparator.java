package realFinal;

// Elijah

import java.util.Comparator;

public class AlphaComparator implements Comparator<Customer>{
	


public AlphaComparator() {
	
}


public int compare(Customer info1,Customer info2) {
	
	// sets value of name1 and name2
	String name1 = info1.getName();
	String name2 = info2.getName();
	
	// determines order alphabetically
	if(name2.compareTo(name1)<0)
        return 1;
    else if(name2.compareTo(name1)>0)
        return -1;
    else
        return 0;
}
}