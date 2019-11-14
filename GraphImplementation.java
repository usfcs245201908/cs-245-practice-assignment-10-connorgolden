import java.util.*;

public class GraphImplementation implements Graph {

    private int V;   // No. of vertices
    private LinkedList<Integer>[] adj; // Adjacency List

    GraphImplementation(int verticies){
        V = verticies;
        adj = new LinkedList[verticies];
        for (int i=0; i<verticies; ++i)
            adj[i] = new LinkedList<Integer>();
    }


    @Override
    public void addEdge(int v1, int v2) throws Exception {
        if (v1 > V-1 || v2 > V-1){
            throw new IllegalAccessException("Too big of arg");
        }
        adj[v1].add(v2);
    }

    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        if (vertex > V-1){
            throw new IllegalAccessException("Too big of arg");
        }
        return adj[vertex];
    }


    @Override
    public List<Integer> topologicalSort() {

        List<Integer> sorted = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        boolean[] seen = new boolean[V];
        for (int i = 0; i < V; i++)
            seen[i] = false;

        for (int i = 0; i < V; i++)
            if (!seen[i])
                topologicalSort(stack, i, seen);

        while (!stack.empty()) {
            //Add contents to List
            sorted.add(stack.pop());
        }

        System.out.println("Possible Path from Lowest Possible starting index:");
        for (int i : sorted){
            //Print List in order
            System.out.println(i);
        }

        return sorted;
    }

    private void topologicalSort(Stack<Integer> stack, int v, boolean[] seen) {
        seen[v] = true;
        Integer i;

        for (Integer integer : adj[v]) {
            i = integer;
            if (!seen[i])
                topologicalSort(stack, i, seen);
        }
        stack.push(v);
    }

}
