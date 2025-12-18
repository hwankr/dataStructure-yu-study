package midterm.chap5;

public class DisjointSet {
	private int[] parent;
	
    public DisjointSet(int n) {
         parent = new int[n];
         for (int i = 0; i < n; i++) {
        	 parent[i] = -1; 
         }
    }
    
    public int find(int i) {
    	for (; parent[i] >= 0; i = parent[i]);
    	return i;
    }
    
    public void union(int i, int j) {
    	int pi = find(i);
    	int pj = find(j);
    	parent[pi] = pj;
    }

    public void weightedUnion(int i, int j) {
    	int pi = find(i);
    	int pj = find(j);
    	if (pi == pj) return;
    	
    	int temp = parent[i] + parent[j];
    	if (parent[i] > parent[j]) {
    		parent[i] = j;
    		parent[j] = temp;
    	}
    	else {
    		parent[j] = i;
    		parent[i] = temp;
    	}
    }
    
    public int collapsingFind(int i) {
    	int node, next, root;
    	
    	for (root = i; parent[root] >= 0; root = parent[root]);
    	for (node = i; node != root && parent[node] != root; node = next) {
    		next = parent[node];
    		parent[node] = root;
    	}
    	return root;
    }
}
