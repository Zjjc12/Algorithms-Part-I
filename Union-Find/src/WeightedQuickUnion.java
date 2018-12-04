
public class WeightedQuickUnion
{

    // Improvement 1:
    // weighting
    // balance by linking root of smaller tree to root of larger tree

    private int[] id;
    private int[] size;

    public WeightedQuickUnion(int N)
    {
	id = new int[N];
	size = new int[N];

	for (int i = 0; i < N; i++)
	{
	    id[i] = i;
	    size[i] = 1;
	}
    }

    public int root(int i)
    { // finds the root of the element
	while (i != id[i])
	{
	    // Make every other node in path to point to its grandparents
	    id[i] = id[id[i]];
	    //

	    i = id[i];
	}
	return i;
    }

    public boolean connected(int p, int q)
    {
	return root(p) == root(q);
    }

    public void weightedUnion(int p, int q)
    {
	int i = root(p);
	int j = root(q);

	if (i == j)
	{
	    return;
	}
	if (size[i] < size[j])
	{
	    id[i] = j;
	    size[j] += size[i];
	}

	if (size[i] > size[j])
	{
	    id[j] = i;
	    size[i] += size[j];
	}
    }

    // depth of any node x is at most lg N

    // Union and Connected are both lg N

}
