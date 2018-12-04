public class QuickUnion
{
    // Quick Union but only changing id of one
    // Make a tree of IDs
    // Find is checking if two elements have the same root
    // Only changes one entry in the table

    private int[] id;

    public QuickUnion(int N)
    {
	id = new int[N];
	for (int i = 0; i < N; i++)
	{
	    id[i] = i;
	}
    }

    public int root(int i)
    { // finds the root of the element
	while (i != id[i])
	    i = id[i];
	return i;
    }

    public boolean connected(int p, int q)
    {
	return root(p) == root(q);
    }

    public void union(int p, int q)
    {
	int i = root(p);
	int j = root(q);

	id[i] = j;
    }

    // Trees can get tall
    // Find is too expensive

}
