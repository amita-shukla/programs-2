package in.amita.practice;
/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
    Every adjacent pair of words differs by a single letter.
    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 */
import java.util.*;

public class GraphWordLadder {
	Map<String, Boolean> visited;
	Map<String, Integer> distanceFromSource;
	public int ladderLength(String beginWord, String endWord, List<String> wordList){

		wordList.add(beginWord);
		Graph g = new Graph(wordList.size());

		for (int i = 0; i < wordList.size(); i++) {
			for (int j = 0; j < wordList.size(); j++) {
				if(i==j) continue;
				String u = wordList.get(i);
				String v = wordList.get(j);
				g.addEdge(u,v);
			}
		}
		visited = new HashMap<>();
		distanceFromSource = new HashMap<>();
		distanceFromSource.put(beginWord,0);
		bfs(g, beginWord, endWord);
		return !distanceFromSource.containsKey(endWord) ? 0 : distanceFromSource.get(endWord)+1;
	}

	private void bfs(Graph g, String s, String d){

		Queue<String> q = new LinkedList<>();
		q.add(s);
		visited.put(s, true);
		while (!q.isEmpty()){
			String u = q.remove();
			for(String v : g.adjList.get(u)){
				if(!visited.containsKey(v)){// || !visited.get(v)) {
					visited.put(v, true);
					distanceFromSource.put(v, distanceFromSource.get(u)+1);
					q.add(v);
				}
			}
		}
//		return 0;
	}

	private boolean differBySingleLetter(String u, String v){
		if(u.length()!=v.length()) return false;
		boolean differ = false;
		for (int i = 0; i < u.length(); i++) {
			if (u.charAt(i)!=v.charAt(i)){
					if(!differ) differ = true;
					else return false;
				}
		}
		return differ;
	}

	public static void main(String[] args) {
		GraphWordLadder obj = new GraphWordLadder();
//		String b = "hit", e = "cog";
		String b = "hot", e = "dog";
		List<String> wordList = new ArrayList<>();
		wordList.add("hot");
//		wordList.add("dot");
		wordList.add("dog");
//		wordList.add("lot");
//		wordList.add("log");
//		wordList.add("cog");
		System.out.println(obj.ladderLength(b,e,wordList));
	}

	class Graph{
		Map<String, List<String>> adjList;
		int V;

		Graph(int v) {
			this.V = v;
			adjList = new HashMap<>();
		}

		public void addEdge(String u, String v){
			if(!adjList.containsKey(u)) {
//				V++;
				adjList.put(u, new ArrayList<>());
			}

			if(differBySingleLetter(u,v)) {
				adjList.get(u).add(v);
			}
			/*
			if(adjList.containsKey(v)) {
				adjList.get(v).add(u);
			}else{
				V++;
				List<String> l = new ArrayList<>();
				l.add(u);
				adjList.put(v, l);
			}
			*/
		}
	}
}
