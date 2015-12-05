package ageuk;

import java.util.ArrayList;

public class MatchingImp {

	String name1 = null;
	String name2 = null;

	public static int commonInterests(ArrayList<String> description,
			ArrayList<String> interests) {
		int found = 0;
		for (int i = 0; i < interests.size(); i++) {
			if (description.contains(interests.get(i))) {
				found++;
			}
		}
		return found;
	}

	public static ArrayList<String> commonInterestsList(
			ArrayList<String> description, ArrayList<String> interests) {
		ArrayList<String> found = new ArrayList<String>();
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
			listNb.add(commonInterests(listGens.get(i), vol));
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
				if (commonInterests(listPeople.get(j), you) == intlist
						.get(listPeople.size() - 1 - i)) {
					list.add(commonInterestsList(listPeople.get(j), you));
				}
			}
		}
		return list;
	}

	public static ArrayList<Users> matchingListUsers(ArrayList<Users> list,
			Users user) {
		ArrayList<String> you = new ArrayList<String>();
		you = user.getInterests();
		ArrayList<Users> userList = new ArrayList<Users>();
		ArrayList<ArrayList<String>> superlist = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < list.size(); i++) {
			superlist.add(list.get(i).getInterests());
		}

		ArrayList<Integer> intlist = quicksort(listNumberMatch(superlist, you));

		for (int i = 0; i < superlist.size(); i++) {
			int comp = 0;
			boolean test = commonInterests(superlist.get(comp), you) == intlist
					.get(superlist.size() - 1);
			while (!test) {
				test = commonInterests(superlist.get(comp), you) == intlist
						.get(superlist.size() - comp - 1);
				if (test) {
					userList.add(userList.get(comp));
					userList.remove(comp);
					superlist.remove(comp);
				}
				comp++;
			}
		}
		return list;
	}

	public static ArrayList<ArrayList<String>> matchingListInterests(
			ArrayList<Users> listPeople, Users user) {
		ArrayList<String> you = new ArrayList<String>();
		you = user.getInterests();
		ArrayList<Users> userList = matchingListUsers(listPeople, user);
		ArrayList<ArrayList<String>> interestsList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> superlist = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < userList.size(); i++) {
			superlist.add(userList.get(i).getInterests());
		}
		for (int i = 0; i < userList.size(); i++) {
			interestsList.add(commonInterestsList(superlist.get(i), you));
		}
		return superlist;

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

		System.out.println(commonInterestsList(me1, you));
		System.out.println(commonInterestsList(me2, you));
		System.out.println(commonInterestsList(me3, you));
		System.out.println(commonInterests(me1, you));
		System.out.println(commonInterests(me2, you));
		System.out.println(commonInterests(me3, you));
		System.out.println(matchingList(test, you));

	}
}