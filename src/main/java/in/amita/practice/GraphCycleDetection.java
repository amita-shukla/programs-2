/*
Union Find Algorithm - Union By Rank and Path Compression
https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/

The term rank is preferred instead of height because if path compression technique (we have discussed it below) is used, then rank is not always equal to height.
 */
public class GraphCycleDetection {

    /*
    The idea is to flatten the tree when find() is called.
    When find() is called for an element x, root of the tree is returned.
     */
    public int find(Subset[] subsets, int i){
        if(subsets[i].parent!=i){
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public void union(Subset[] subsets, int x, int y){
        // consider x and y in 2 different sets initially.
        // So we find the root of the trees (subsets) x and y belong to
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        // the idea is to always attach smaller depth tree under the root of the deeper tree.
        if(subsets[xroot].rank == subsets[yroot].rank){
            // if both xroot and yroot are at the same level
            // make yroot the parent of xroot (or we could do the inverse)
            subsets[xroot].parent = yroot;
            // and increase the rank of y
            subsets[yroot].rank++;
        } else if(subsets[xroot].rank < subsets[yroot].rank){
            // if rank of xroot is less than yroot
            // make yroot the parent of xroot
            subsets[xroot].parent = yroot;
            // no need to change the rank, coz we have simply added another child to the root.
        } else {
            // if rank of xroot is greater than that of yroot
            // make xroot the parent of yroot
            subsets[yroot].parent = xroot;
            // no need to change rank, for the same reason as above.
        }
    }

    public boolean hasCycle(Graph g){
        Subset[] subsets = new Subset[g.V];
        for (int i = 0; i < g.V; i++) {
            // initially each vertex is parent of itself
            // each vertex is a single tree(subset) with 0 rank (or height?)
            subsets[i] = new Subset(i, 0);
        }

        for (int i = 0; i < g.E; i++) {
            int uSet = find(subsets, g.edges[i].u);
            int vSet = find(subsets, g.edges[i].v);

            if (uSet==vSet) return true;

            union(subsets, uSet, vSet);
        }

        return false;
    }

    public static void main(String[] args) {
        /* Let us create the following graph
            0
            | \
            | \
            1-----2 */

        GraphCycleDetection obj = new GraphCycleDetection();
        int V = 3, E = 3;
        Graph graph = obj.new Graph(V, E);

        // add edge 0-1
        graph.edges[0] = obj.new Edge(0,1);

        // add edge 1-2
        graph.edges[1] = obj.new Edge(1,2);

        // add edge 0-2
        graph.edges[2] = obj.new Edge(0,2);

        if (obj.hasCycle(graph))
            System.out.println("Graph contains cycle");
        else
            System.out.println(
                    "Graph doesn't contain cycle");
    }

    class Subset {
        int parent;
        int rank;
        Subset(int p, int r){
            parent = p;
            rank = r;
        }
    }

    class Edge {
        int u;
        int v;
        Edge(int u, int v){
            this.u = u;
            this.v = v;
        }
    }

    class Graph {
        int V, E;
        Edge[] edges;

        Graph(int V, int E){
            this.V = V;
            this.E = E;
            edges = new Edge[E];
        }
    }
}
