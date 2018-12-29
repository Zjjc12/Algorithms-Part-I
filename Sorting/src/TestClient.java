
import java.util.Random;

import Classic.Merge;
import Elementary.Shell;
import Elementary.StdRandom;


public class TestClient
{
	Random rand = new Random();
	
	public static void main(String[] args) {
		
		TestClient c = new TestClient();
		
		Date[] dates = c.generateDates(100);
		
			
		Merge.sort(dates);
		
		c.printDate(dates);
	}
	
	public Date[] generateDates(int times) {
		
		Date[] newDates = new Date[times];
		
		for (int i = 0; i < times; i++) {
			int day = rand.nextInt(31) + 1;
			int month = rand.nextInt(12) + 1;
			int year = rand.nextInt(30) + 2000;
			
			Date newDay = new Date(month, day, year);
			
			newDates[i] = newDay;
		}
		
		return newDates;
	}
	
	public void printDate(Date[] date) {
		for (int i = 0; i < date.length; i ++) {
			System.out.println(date[i].getMonth()+"/"+date[i].getDay()+"/"+date[i].getYear());
		}
	}
	
}
