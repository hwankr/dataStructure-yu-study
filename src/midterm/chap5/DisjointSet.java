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
}
