package dataStructure.lesson.chap6;

public class AOETestDriver {
	public static void main(String[] args) {
		WeightedListGraph G = new WeightedListGraph(9, true);
		
		G.addEdge(0, 1, 6);
		G.addEdge(0, 2, 4);
		G.addEdge(0, 3, 5);
		G.addEdge(1, 4, 1);
		G.addEdge(2, 4, 1);
		G.addEdge(3, 5, 2);
		G.addEdge(4, 6, 8);
		G.addEdge(4, 7, 6);
		G.addEdge(5, 7, 4);
		G.addEdge(6, 8, 2);
		G.addEdge(7, 8, 4);
		
		G.criticalActivity(0, 8);
	}
}
