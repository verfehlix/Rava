package yolo;

import java.util.ArrayList;

public class Tester {
		//some examples of how to use Rava
		public static void main(String a[]) {
			Rava superRava = new Rava();
			
			ArrayList<Double> myNumbers = new ArrayList<Double>();
			myNumbers.add(4.0);
			myNumbers.add(4.0);
			myNumbers.add(4.0);
			myNumbers.add(4.0);
			
			ArrayList<Double> moreNumbers = new ArrayList<Double>();
			moreNumbers.add(5.0);
			moreNumbers.add(5.0);
			moreNumbers.add(5.0);
			moreNumbers.add(5.0);
			
			System.out.println("calculating mean of myNumbers");
			superRava.calculateMean(myNumbers);
			
			System.out.println("calculating accuracy measures of myNumbers and moreNumbers");
			superRava.calculateAccuracy(myNumbers, moreNumbers);
		}
}
