package Classic;

public class Heap
{
	// Sort using heap binary tree structure and removing the max every time
	public static void sort(Comparable[] pq)
	{
		int N = pq.length;
		// For every key starting at N/2 (N/2 is the left tree not include 1 key branches)
		for (int k = N/2; k >= 1; k--)
			// Sink that key
			sink(pq, k, N);
		// Now its in heap order
		
		// While there are still keys left
		while(N > 1)
		{
			// Exchange the first with the last
			exch(pq, 1, N);
			// And sink last to appropriate position
			// --N moving the key to the end and removing it from tree
			// But is still kept in the array making it in increasing order
			sink(pq, 1, --N);
		}
	}
	
	
	private static void sink(Comparable[] pq, int k, int N)
	{
		// While key is not at the bottom
		while (2 * k <= N)
		{
			int j = 2 * k;
			// If children is not at the end, and choose the bigger child
			if (j < N && less(pq, j, j + 1))
				j++;
			// If the bigger child is less than parent, then exit
			if (!less(pq, k, j))
				break;
			// If the bigger child is less than parent, then swap with its parent
			exch(pq, k, j);

			// Set new check index to be the choosen child
			k = j;
		}
	}

	private static boolean less(Comparable[] pq, int i, int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}

	private static void exch(Comparable[] pq, int i, int j)
	{
		Comparable t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
}
