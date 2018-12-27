import java.util.Random;

public class StdRandom
{
	static Random r = new Random();
	
	public static void shuffle(Object[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int randomR = r.nextInt(i + 1);
			exch(a, i, randomR);
		}
	}

	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
