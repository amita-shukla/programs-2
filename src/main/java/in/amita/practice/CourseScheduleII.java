import java.util.*;
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
 */
public class CourseScheduleII {
    Graph g;
    state[] visited;
    boolean hasCycle;
    enum state {NOT_VISITED, BEING_VISITED, DONE_VISITED};
    public int[] findOrder(int numCourse, int[][] prerequisites){
        int[] res = new int[numCourse];
        if(prerequisites.length==0) {
            for (int i = 0; i < numCourse; i++) {
                res[i] = i;
            }
            return res;
        }

        g = new Graph(numCourse);

        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][1], v = prerequisites[i][0];
            g.addEdge(u, v);
        }

        Stack<Integer> stack = new Stack<Integer>();

        visited = new state[g.V];
        for (int i = 0; i < g.V; i++)
            if (visited[i] ==null || visited[i].equals(state.NOT_VISITED))
                topologicalSort(i,stack);

        if(hasCycle) return new int[]{};

        int i =0;
        while (!stack.empty()) {
            res[i] = stack.pop();
            i++;
        }
        return res;
    }

    private void topologicalSort(int u, Stack<Integer> stack){
        if(hasCycle) return;

        visited[u] = state.BEING_VISITED;
        ArrayList<Integer> adjacent = g.adjList.get(u);
        for (int v: adjacent) {
            if(visited[v] == null || visited[v].equals(state.NOT_VISITED)){
                topologicalSort(v, stack);
            }else if(visited[v].equals(state.BEING_VISITED)) {
                hasCycle = true;
            }
        }
        stack.push(u);
        visited[u] = state.DONE_VISITED;
    }

    public static void main(String[] args) {
        CourseScheduleII obj = new CourseScheduleII();
        System.out.println(Arrays.toString(obj.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
        System.out.println(Arrays.toString(obj.findOrder(2, new int[][]{{1,0}})));
        System.out.println(Arrays.toString(obj.findOrder(1, new int[][]{})));
        System.out.println(Arrays.toString(obj.findOrder(3, new int[][]{{1,0},{0,2},{2,1}})));
    }

    class Graph{
        int V;
        ArrayList<ArrayList<Integer>> adjList;
        Graph(int V){
            this.V = V;
            adjList = new ArrayList<>();
            for(int i=0; i<V; i++){
                adjList.add(new ArrayList<>());
            }
        }
        public void addEdge(int u, int v){
            adjList.get(u).add(v);
        }
    }
}
