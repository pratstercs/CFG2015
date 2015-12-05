
import java.util.ArrayList;

import matching.Users;

public class MatchingImp2 {

	public static int totalPoints(Users user) {
		ArrayList<Integer> points = new ArrayList<Integer>();
		points = user.getPoints();
		int comp = 0;
		for (int i = 0; i < points.size(); i++) {
			comp = comp + points.get(i);
		}
		return comp;
	}

	public static int demiPoints(Users user1, Users user2) {
		ArrayList<String> interests1 = new ArrayList<String>();
		ArrayList<Integer> points1 = new ArrayList<Integer>();
		interests1 = user1.getInterests();
		points1 = user1.getPoints();

		ArrayList<String> interests2 = new ArrayList<String>();
		ArrayList<Integer> points2 = new ArrayList<Integer>();
		interests2 = user2.getInterests();
		points2 = user2.getPoints();

		int comp = 0;
		for (int i = 0; i < interests1.size(); i++) {
			int k = 0;
			boolean s = false;
			while (k < interests1.size() && s == false) {
				if (interests1.get(i).equals(interests2.get(k))) {
					comp = comp + Math.min(points1.get(i), points2.get(k));
					s = true;
				} else {
					k++;
				}
			}
		}
		return comp;
	}

	public static double getPercent(Users user1, Users user2) {
		double num = demiPoints(user1, user2);
		double den = totalPoints(user1);
		return num / den * 100;
	}

	public static double getScore(Users user1, Users user2) {
		double score1 = getPercent(user1, user2);
		double score2 = getPercent(user2, user1);

		return score1 * score2;
	}

	public static ArrayList<Double> listNumberMatch(ArrayList<Users> listGens,
			Users vol) {
		ArrayList<Double> listNb = new ArrayList<Double>();
		for (int i = 0; i < listGens.size(); i++) {
			listNb.add(getScore(listGens.get(i), vol));
		}
		return listNb;
	}

	public static ArrayList<Double> quicksort(ArrayList<Double> numbers) {
		if (numbers.size() <= 1)
			return numbers;
		int pivot = numbers.size() / 2;
		ArrayList<Double> lesser = new ArrayList<Double>();
		ArrayList<Double> greater = new ArrayList<Double>();
		int sameAsPivot = 0;
		for (Double number : numbers) {
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
		ArrayList<Double> sorted = new ArrayList<Double>();
		for (Double number : lesser)
			sorted.add(number);
		for (Double number : greater)
			sorted.add(number);
		return sorted;
	}

	public static ArrayList<Users> getMatching(Users user, ArrayList<Users> list) {
		ArrayList<Users> match = new ArrayList<Users>();
		ArrayList<Users> list1 = new ArrayList<Users>();
		list1 = list;

		ArrayList<Double> intlist = quicksort(listNumberMatch(list, user));

		for (int i = 0; i < list.size(); i++) {
			int comp = 0;
			boolean test = getScore(list.get(comp), user) == intlist.get(list
					.size() - 1);
			while (!test) {
				test = getScore(list.get(comp), user) == intlist.get(list
						.size() - comp - 1);
				if (test) {
					match.add(list1.get(comp));
					list1.remove(comp);
					intlist.remove(comp);
				}
				comp++;
			}
		}
		return match;
	}

}