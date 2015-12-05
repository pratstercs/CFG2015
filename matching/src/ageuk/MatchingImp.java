package ageuk;

import java.util.ArrayList;

public class MatchingImp {

	String name1 = null;
	String name2 = null;

	
	public static int commonInterests(ArrayList<String> description,
			ArrayList<String> interests, int found) {
		for (int i = 0; i < interests.size(); i++) {
			if (description.contains(interests.get(i))) {
				found++;
			}
		}
		return found;
	}

	
	public static ArrayList<String> commonInterestsList(
			ArrayList<String> description, ArrayList<String> interests,
			ArrayList<String> found) {
		for (int i = 0; i < interests.size(); i++) {
			if (description.contains(interests.get(i))) {
				found.add(interests.get(i));
			}

		}
		return found;

	}

	public static ArrayList<Integer> listNumberMatch(
			ArrayList<ArrayList<String>> listGens, ArrayList<String> vol) {
		ArrayList<Integer> listNb = new ArrayList<Integer>();
		for (int i = 0; i < listGens.size(); i++) {
			listNb.add(commonInterests(listGens.get(i), vol, 0));
		}
		return listNb;
	}

	public static ArrayList<Integer> quicksort(ArrayList<Integer> numbers) {
		if (numbers.size() <= 1)
			return numbers;
		int pivot = numbers.size() / 2;
		ArrayList<Integer> lesser = new ArrayList<Integer>();
		ArrayList<Integer> greater = new ArrayList<Integer>();
		int sameAsPivot = 0;
		for (int number : numbers) {
			if (number > numbers.get(pivot))
				greater.add(number);
			else if (number < numbers.get(pivot))
				lesser.add(number);
			else
				sameAsPivot++;
		}
		lesser = quicksort(lesser);
		for (int i = 0; i < sameAsPivot; i++)
			lesser.add(numbers.get(pivot));
		greater = quicksort(greater);
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		for (int number : lesser)
			sorted.add(number);
		for (int number : greater)
			sorted.add(number);
		return sorted;
	}

	public static ArrayList<ArrayList<String>> matchingList(
			ArrayList<ArrayList<String>> listPeople, ArrayList<String> you) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> intlist = quicksort(listNumberMatch(listPeople, you));
		for (int i = 0; i < listPeople.size(); i++) {
			for (int j = 0; j < listPeople.size(); j++) {
				if (commonInterests(listPeople.get(j), you, 0) == intlist
						.get(listPeople.size() - 1 - i)) {
					list.add(commonInterestsList(listPeople.get(j), you,
							new ArrayList<String>()));
				}
			}
		}
		return list;
	}
	

	public static void main(String[] args) {
		ArrayList<String> you = new ArrayList<String>();
		ArrayList<String> me1 = new ArrayList<String>();
		ArrayList<String> me2 = new ArrayList<String>();
		ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		you.add("1");
		you.add("2");
		you.add("3");
		me1.add("1");
		me2.add("2");
		me2.add("3");
		me2.add("1");
		ArrayList<String> me3 = new ArrayList<String>();
		me3.add("1");
		me3.add("3");
		test.add(me1);
		test.add(me2);
		test.add(me3);

		System.out.println(commonInterestsList(me1, you,
				new ArrayList<String>()));
		System.out.println(commonInterestsList(me2, you,
				new ArrayList<String>()));
		System.out.println(commonInterestsList(me3, you,
				new ArrayList<String>()));
		System.out.println(commonInterests(me1, you, 0));
		System.out.println(commonInterests(me2, you, 0));
		System.out.println(commonInterests(me3, you, 0));
		System.out.println(matchingList(test, you));

	}
}
