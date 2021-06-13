/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
Return the answers to all queries. If a single answer cannot be determined, return -1.0.
Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
    1 <= equations.length <= 20
    equations[i].length == 2
    1 <= Ai.length, Bi.length <= 5
    values.length == equations.length
    0.0 < values[i] <= 20.0
    1 <= queries.length <= 20
    queries[i].length == 2
    1 <= Cj.length, Dj.length <= 5
    Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
package in.amita.practice;

import java.util.*;

public class GraphEvaluateDivision {

	Graph g;
	Map<String, Boolean> visited;
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
		g = new Graph();

		double[] res = new double[queries.size()];

		for (int i =0; i<equations.size(); i++){
			List<String> equation = equations.get(i);
			String dividend = equation.get(0);
			String divisor = equation.get(1);
			double value = values[i];
		  g.addEdge(dividend, divisor, value);
		}


		System.out.println("graph: " + g.adjList);
		Set<String> nodes = g.adjList.keySet();
		for (int i = 0; i< queries.size(); i++){
			List<String> query = queries.get(i);
			String s = query.get(0);
			String d = query.get(1);

			if(!nodes.contains(s) || !nodes.contains(d)) {
				res[i] = -1.0;
			} else {
				if (s.equals(d)) {
					res[i] = 1.0;
				} else {
					initVisited();
					res[i] = evaluate(s, d);
				}
			}
		}
		return res;
	}

	private void initVisited(){
		visited = new HashMap<>();
		for (String node: g.adjList.keySet()) {
			visited.put(node, false);
		}
	}

	class Edge{
		String val;
		double wt;
		Edge(String val , double wt){
			this.val = val;
			this.wt = wt;
		}
	}
	private double evaluate(String s, String d){
		if(!g.adjList.containsKey(s)) return -1.0;
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(s, 1));
		while(!q.isEmpty()) {
			Edge u = q.remove();
			visited.put(u.val, true);
			Map<String, Double> children = g.adjList.get(u.val);
			for(Map.Entry<String, Double> child: children.entrySet()){
				String v = child.getKey();
				Double wt = child.getValue();
				if(v.equals(d)) return wt*u.wt;
				if(!visited.get(v)) {
					q.add(new Edge(v, wt * u.wt));
				}
			}
		}
		return -1.0;
	}

	class Graph{
		int V;
		HashMap<String, HashMap<String, Double>> adjList;

		Graph(){
			this.adjList = new HashMap<>();
		}

		public void addEdge(String u,  String v, double wt){
			if(!adjList.containsKey(u)){
				V++;
				HashMap<String, Double> weightedEdges = new HashMap<>();
				weightedEdges.put(v, wt);
				adjList.put(u, weightedEdges);
			} else {
				adjList.get(u).put(v, wt);
			}

			if(!adjList.containsKey(v)){
				V++;
				HashMap<String, Double> weightedEdges = new HashMap<>();
				weightedEdges.put(u, (1/wt));
				adjList.put(v, weightedEdges);
			} else {
				adjList.get(v).put(u, 1/wt);
			}
		}
	}
	public static void main(String[] args) {
		GraphEvaluateDivision obj = new GraphEvaluateDivision();

		List<List<String>> equations = new ArrayList<>();
		List<String> equation1 = new ArrayList<>();
		equation1.add("a"); equation1.add("b"); equations.add(equation1);
		List<String> equation2 = new ArrayList<>();
		equation2.add("b"); equation2.add("c"); equations.add(equation2);
	  double[] values = {2.0, 3.0};

		List<List<String>> queries = new ArrayList<>();
		List<String> query1 = new ArrayList<>();
		query1.add("a"); query1.add("c"); queries.add(query1);
		List<String> query2 = new ArrayList<>();
		query2.add("b"); query2.add("a"); queries.add(query2);
		List<String> query3 = new ArrayList<>();
		query3.add("a"); query3.add("e"); queries.add(query3);
		List<String> query4 = new ArrayList<>();
		query4.add("a"); query4.add("a"); queries.add(query4);
		List<String> query5 = new ArrayList<>();
		query5.add("x"); query5.add("x"); queries.add(query5);
		System.out.println(Arrays.toString(obj.calcEquation(equations, values, queries)));



		List<List<String>> equations2 = new ArrayList<>();
		List<String> equation3 = new ArrayList<>();
		equation3.add("a"); equation3.add("b"); equations2.add(equation3);
		List<String> equation4 = new ArrayList<>();
		equation4.add("b"); equation4.add("c"); equations2.add(equation4);
		List<String> equation5 = new ArrayList<>();
		equation5.add("bc"); equation5.add("cd"); equations2.add(equation5);
		double[] values2 = {1.5, 2.5, 5.0};

		List<List<String>> queries2 = new ArrayList<>();
		List<String> query6 = new ArrayList<>();
		query6.add("a"); query6.add("c"); queries2.add(query6);
		List<String> query7 = new ArrayList<>();
		query7.add("c"); query7.add("b"); queries2.add(query7);
		List<String> query8 = new ArrayList<>();
		query8.add("bc"); query8.add("cd"); queries2.add(query8);
		List<String> query9 = new ArrayList<>();
		query9.add("cd"); query9.add("bc"); queries2.add(query9);
		System.out.println(Arrays.toString(obj.calcEquation(equations2, values2, queries2)));



		List<List<String>> equations3 = new ArrayList<>();
		List<String> equation6 = new ArrayList<>();
		equation6.add("a"); equation6.add("b"); equations3.add(equation6);
		double[] values3 = {0.5};
		List<List<String>> queries3 = new ArrayList<>();
		List<String> query10 = new ArrayList<>();
		query10.add("a"); query10.add("b"); queries3.add(query10);
		List<String> query11 = new ArrayList<>();
		query11.add("b"); query11.add("a"); queries3.add(query11);
		List<String> query12 = new ArrayList<>();
		query12.add("a"); query12.add("c"); queries3.add(query12);
		List<String> query13 = new ArrayList<>();
		query13.add("x"); query13.add("y"); queries3.add(query13);
		System.out.println(Arrays.toString(obj.calcEquation(equations3, values3, queries3)));

		List<List<String>> equations4 = new ArrayList<>();
		List<String> equation7 = new ArrayList<>();
		equation7.add("x1"); equation7.add("x2"); equations4.add(equation7);
		List<String> equation8 = new ArrayList<>();
		equation8.add("x2"); equation8.add("x3"); equations4.add(equation8);
		List<String> equation9 = new ArrayList<>();
		equation9.add("x3"); equation9.add("x4"); equations4.add(equation9);
		List<String> equation10 = new ArrayList<>();
		equation10.add("x4"); equation10.add("x5"); equations4.add(equation10);
		double[] values4 = {3.0,4.0,5.0,6.0};
		List<List<String>> queries4 = new ArrayList<>();
		List<String> query14 = new ArrayList<>();
		query14.add("x2"); query14.add("x4"); queries4.add(query14);
		List<String> query15 = new ArrayList<>();
		query15.add("x5"); query15.add("x2"); queries4.add(query15);
		System.out.println(Arrays.toString(obj.calcEquation(equations4, values4, queries4)));
	}
}
