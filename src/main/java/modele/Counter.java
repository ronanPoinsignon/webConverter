package modele;

import java.util.ArrayList;
import java.util.List;

public class Counter {

	private static Counter counter = null;

	private List<Integer> usedNumbers = new ArrayList<>();
	private List<Integer> notUsedNumbers = new ArrayList<>();

	private Counter() {

	}

	public static Counter getInstance() {
		if(Counter.counter == null) {
			Counter.counter = new Counter();
		}
		return Counter.counter;
	}

	public int getActualNumber() {
		return usedNumbers.stream().mapToInt(number -> number).max().orElse(0);
	}

	public synchronized int getNextNumber() {
		if(!notUsedNumbers.isEmpty()) {
			int number = notUsedNumbers.remove(0);
			usedNumbers.add(number);
			return number;
		}

		int number = getActualNumber() + 1;
		usedNumbers.add(number);
		return number;
	}

	public void removeNumber(int number) {
		usedNumbers.remove(new Integer(number));
		notUsedNumbers.add(number);
		System.out.println("used : " + usedNumbers.size() + " / " + usedNumbers);
		System.out.println("not used : " + notUsedNumbers.size() + " / " + notUsedNumbers);
	}

}
