package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CounterService {


	private List<Integer> usedNumbers = new ArrayList<>();
	private List<Integer> notUsedNumbers = new ArrayList<>();

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
	}

}
