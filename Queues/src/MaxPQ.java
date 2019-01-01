
public class MaxPQ<Key extends Comparable<Key>>
{

	private Key[] pq;
	private int N;

	
	// Complete Max Binary Heap Tree Implementation
	// 		Binary tree with bigger key always higher
	
	
	public MaxPQ(int capacity)
	{
		pq = (Key[]) new Comparable[capacity+1];
	}
	
	// Move a key up to its appropriate position
	private void swim(int k)
	{
		// While key is greater than its parent and not at top
		while (k > 1 && less(k / 2, k))
		{
			// Exchange key with its parent
			exch(k, k / 2);
			// Make new check index it's parent's index
			k = k / 2;
		}
	}

	// Add new key to its correct position
	public void insert(Key x)
	{
		// And new key at the end
		pq[++N] = x;
		// Swim it up to its position
		swim(N);
	}

	private void sink(int k)
	{
		// While key is not at the bottom
		while (2 * k <= N)
		{
			int j = 2 * k;
			// If children is not at the end, and choose the bigger child
			if (j < N && less(j, j + 1))
				j++;
			// If the bigger child is less than parent, then exit
			if (!less(k, j))
				break;
			// If the bigger child is less than parent, then swap with its parent
			exch(k, j);

			// Set new check index to be the choosen child
			k = j;
		}
	}

	public Key delMax()
	{
		// Store max key
		Key max = pq[1];

		// Switch it with bottom most key
		exch(1, N--);

		// Sink now the top key to appropriate position
		sink(1);

		// Avoid loitering (removing the reference to that object by setting it to
		// null), to allow for garbage collection from java
		pq[N + 1] = null;
		return max;
	}

	private boolean less(int i, int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j)
	{
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
}
