package Classic;

import Elementary.Insertion;

public class Merge
{
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
	{
		//Make sure both halves are sorted
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);
	
		//Copy a to aux
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		//Start i at the start of first subarray
		//Start j at the start of second subarray
		int i = lo, j = mid +1;
		
		//Loop through a, merge subarrays in aux into a
		for (int k = lo; k <= hi; k++) {
			//If i is used up, only add from j
			if (i > mid)
				a[k] = aux[j++];
			//If j is used up, only add from i
			else if (j > hi)
				a[k] = aux[i++];
			//If j is less than i then add j
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			//Else add i
			else
				a[k] = aux[i++];
		}
		
		//Check if array is sorted
		assert isSorted(a, lo, hi);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		//Return if only 1 is left
		if (hi <= lo)
			return;
		//Set mid point
		int mid = lo + (hi - lo) /2;
		
		//Sort first subarray
		sort(a, aux, lo, mid);
		
		//Sort second subarray
		sort(a, aux, mid + 1, hi);
		
		//Merge both subarray
		merge(a, aux, lo, mid, hi);
	}
	
	//Cut off + check
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi, int CUTOFF) {
		//Return if only 1 is left
		if (hi <= lo + CUTOFF - 1) {
			Insertion.sort(a, lo, hi);
			return;
		}
		//Set mid point
		int mid = lo + (hi - lo) /2;
		
		//Sort first subarray
		sort(a, aux, lo, mid);
		
		//Sort second subarray
		sort(a, aux, mid + 1, hi);
		
		//If already sorted, just return
		if (!less(a[mid + 1], a[mid]))
			return;
		
		//Merge both subarray
		merge(a, aux, lo, mid, hi);
	}
	
	
	
	public static void sort(Comparable[] a)
	{
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1, 7);
	}
	

	private static boolean isSorted(Comparable[] a, int lo, int mid)
	{
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i-1]))
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
