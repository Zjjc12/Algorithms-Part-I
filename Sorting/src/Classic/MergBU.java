package Classic;

public class MergBU
{
	private static Comparable[] aux;

	private static void merge(Comparable[] a, int lo, int mid, int hi)
	{
		// Make sure both halves are sorted
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);

		// Copy a to aux
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		// Start i at the start of first subarray
		// Start j at the start of second subarray
		int i = lo, j = mid + 1;

		// Loop through a, merge subarrays in aux into a
		for (int k = lo; k <= hi; k++)
		{
			// If i is used up, only add from j
			if (i > mid)
				a[k] = aux[j++];
			// If j is used up, only add from i
			else if (j > hi)
				a[k] = aux[i++];
			// If j is less than i then add j
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			// Else add i
			else
				a[k] = aux[i++];
		}

		// Check if array is sorted
		assert isSorted(a, lo, hi);
	}

	private static void sort(Comparable[] a)
	{
		int N = a.length;
		aux = new Comparable[N];

		// Loop through subarray size from 1.. 2.. 4... to N
		for (int sz = 1; sz < N; sz = sz + sz)
			// Loop through and merge all subarray
			for (int lo = 0; lo < N - sz; lo += sz + sz)
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));

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
