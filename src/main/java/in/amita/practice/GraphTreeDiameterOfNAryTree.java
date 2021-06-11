/*
1245. Tree Diameter
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

Example 1:
Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: 
A longest path of the tree is the path 1 - 0 - 2.

Example 2:
Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: 
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 */
import java.util.*;

public class GraphTreeDiameterOfNAryTree {
    int maxDiameter;
    boolean[] visited;
    public int treeDiameter(int[][] edges){
        Graph g = new Graph();
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
        }

        maxDiameter = Integer.MIN_VALUE;
        visited = new boolean[g.V];

//        for (int i = 0; i < g.V; i++) {
//            if(!visited[i]) dfs(g, i);
//        }
        int max = dfs(g, 0);
        return Math.max(max, maxDiameter) - 1;
    }

    private int dfs(Graph g, int u){

        visited[u] = true;
        int localMax = 0;
        int secondLocalMax = 0;
        ArrayList<Integer> maxs = new ArrayList<>();
        for (int v : g.adjList.get(u)){
            if(!visited[v]) {
                int max = dfs(g, v);
                maxs.add(max);
            }
        }
        Collections.sort(maxs);
        if(maxs.size()>=1) localMax = maxs.get(maxs.size()-1);
        if(maxs.size()>=2) secondLocalMax = maxs.get(maxs.size()-2);
        maxDiameter = Math.max(maxDiameter, localMax + secondLocalMax + 1);

        return localMax + 1;
    }

    public static void main(String[] args) {
        GraphTreeDiameterOfNAryTree obj = new GraphTreeDiameterOfNAryTree();
        int[][] edges = {{0,1}, {1,2}, {1,3}, {1,4}, {2,6}, {3,5}};
        System.out.println(obj.treeDiameter(edges));
    }

    class Graph {
        Map<Integer, ArrayList<Integer>> adjList;
        int V;

        Graph(){
            adjList = new HashMap<>();
        }

        public void addEdge(int u, int v) {
            if(adjList.containsKey(u)) {
                adjList.get(u).add(v);
            }else {
                V++;
                ArrayList<Integer> l = new ArrayList<>();
                l.add(v);
                adjList.put(u,l);
            }

            if(adjList.containsKey(v)) {
                adjList.get(v).add(u);
            }else {
                V++;
                ArrayList<Integer> l = new ArrayList<>();
                l.add(u);
                adjList.put(v,l);
            }
        }

    }
}
