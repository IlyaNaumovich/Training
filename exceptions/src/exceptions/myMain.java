package exceptions;

import java.util.*;

public class myMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		arrayList.add(45);
		arrayList.add(45);
		arrayList.add(45);
		arrayList.add(45);
		arrayList.add(null);
		
		/*for(Integer i : arrayList) {
			System.out.println(i);
		}*/
		Iterator<Integer> it = arrayList.iterator();
		Boolean bool = true;
		
		for( ;it.hasNext();) {
			System.out.println(it.next());
		}
		
		LinkedList<Integer> myList = new LinkedList<Integer>();
		
		
		Map<Integer, String> myMap = new HashMap<Integer, String>();
		
		
		
	}

}
