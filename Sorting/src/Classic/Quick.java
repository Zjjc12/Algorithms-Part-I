package Classic;

import Elementary.Insertion;
import Elementary.StdRandom;

public class Quick
{
	private static int partition(Comparable[] a, int lo, int hi)
	{
		// Set i to low end of array, set j to high end of array
		int i = lo, j = hi + 1;

		while (true)
		{
			// Loop through and find element less than partitioning item (start at second,
			// not partitioning item)
			while (less(a[++i], a[lo]))
				if (i == hi)
					break;
			// Loop through and find element less than partitioning item
			while (less(a[lo], a[--j]))
			{
				if (j == lo)
					break;
			}

			// If pointers cross, swap
			if (i >= j)
				break;
			exch(a, i, j);
		}

		// Now everything left of j is less than PI while everything right of j is
		// greater

		// Swap partitioning item to middle
		exch(a, lo, j);

		// Return index of partitioning item
		return j;
	}

	public static void sort(Comparable[] a)
	{
		// Shuffle to ensure performance
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1, 10);
	}

	public static void threeWaySort(Comparable[] a)
	{
		// Shuffle to ensure performance
		StdRandom.shuffle(a);
		threeWaySort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi)
	{
		// If only 1 left, return
		if (hi <= lo)
			return;
		// Index j as PI and get partitioned
		int j = partition(a, lo, hi);

		// Recursively partition left of PI
		sort(a, lo, j - 1);
		// Recursively partition right of PI
		sort(a, j + 1, hi);
	}

	private static void sort(Comparable[] a, int lo, int hi, int CUTOFF)
	{
		// If only CUTOFF number left, use insertion sort for faster sorting
		if (hi <= lo + CUTOFF - 1)
		{
			Insertion.sort(a, lo, hi);
			return;
		}
		// Index j as PI and get partitioned
		int j = partition(a, lo, hi);

		// Recursively partition left of PI
		sort(a, lo, j - 1);
		// Recursively partition right of PI
		sort(a, j + 1, hi);
	}

	private static void threeWaySort(Comparable[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;

		// First set lt to low and gt to high
		int lt = lo, gt = hi;

		// Get the partitioning item
		Comparable v = a[lo];

		int i = lo;

		// If there are still unchecked elements, loop
		while (i <= gt)
		{
			int cmp = a[i].compareTo(v);

			// If element at i is less than PI, then exchange lt and i, then increment both
			if (cmp < 0)
				exch(a, lt++, i++);

			// If element at i is greater than PI, then exchange i and gt, then decrement gt
			else if (cmp > 0)
				exch(a, i, gt--);
			// If they are equal, just increment i
			// Element equal to PI will be kept between lt and i
			else
				i++;
		}

		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);

	}

	public static Comparable select(Comparable[] a, int k)
	{
		StdRandom.shuffle(a);

		// Set Pointer
		int lo = 0, hi = a.length - 1;

		// While pointers aren't crossed
		while (hi > lo)
		{
			// Partition array from low to high
			int j = partition(a, lo, hi);

			// If PI is less than k, then partition the left partition
			if (j < k)
				lo = j + 1;
			// If PI is greater than k, then partition the right partition
			else if (j > k)
				hi = j - 1;
			// If j is at k, found the kth largest
			else
				return a[k];
		}
		return a[k];
	}

	private static boolean isSorted(Comparable[] a, int lo, int mid)
	{
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	private static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
}
