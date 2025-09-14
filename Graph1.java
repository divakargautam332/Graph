import java.util.*;

public class Graph1 {
    static class Edge {
        int src;
        int dest;
        int wt;

        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // graph[0].add(new Edge(0, 1, 1));
        // graph[0].add(new Edge(0, 2, 2));
        // graph[1].add(new Edge(1, 0, 0));
        // graph[1].add(new Edge(1, 3, 3));
        // graph[2].add(new Edge(2, 0, 0));
        // graph[2].add(new Edge(2, 4, 4));
        // graph[3].add(new Edge(3, 1, 1));
        // graph[3].add(new Edge(3, 4, 4));
        // graph[3].add(new Edge(3, 5, 5));
        // graph[4].add(new Edge(4, 2, 2));
        // graph[4].add(new Edge(4, 3, 3));
        // graph[4].add(new Edge(4, 5, 5));
        // graph[5].add(new Edge(5, 3, 3));
        // graph[5].add(new Edge(5, 4, 4));
        // graph[5].add(new Edge(5, 6, 6));
        // graph[6].add(new Edge(6, 5, 5));

        //
        // graph[0].add(new Edge(0, 3, 5));
        // graph[1].add(new Edge(1, 0, 3));
        // graph[2].add(new Edge(2, 4, 4));
        // graph[3].add(new Edge(3, 2, 6));

        // dijkstra
        // graph[2].add(new Edge(2, 3, 2));
        // graph[3].add(new Edge(3, 1, 3));
        // graph[4].add(new Edge(4, 0, 5));
        // graph[4].add(new Edge(4, 1, 3));
        // graph[5].add(new Edge(5, 0, 4));
        // graph[5].add(new Edge(5, 2, 6));

        // graph[0].add(new Edge(0, 3, 2));
        // graph[2].add(new Edge(2, 3, 2));
        // graph[3].add(new Edge(3, 1, 3));
        // graph[4].add(new Edge(4, 0, 5));
        // graph[4].add(new Edge(4, 1, 3));
        // graph[5].add(new Edge(5, 0, 4));
        // graph[5].add(new Edge(5, 2, 6));

        // graph[0].add(new Edge(0, 1, 2));
        // graph[0].add(new Edge(0, 2, 4));
        // graph[1].add(new Edge(1, 3, 7));
        // graph[1].add(new Edge(1, 2, 1));
        // graph[2].add(new Edge(2, 4, 3));
        // graph[3].add(new Edge(3, 5, 1));
        // graph[4].add(new Edge(4, 3, 2));
        // graph[4].add(new Edge(4, 5, 5));

        // graph[0].add(new Edge(0, 1, 2));
        // graph[0].add(new Edge(0, 2, 4));
        // graph[1].add(new Edge(1, 2, -4));
        // graph[2].add(new Edge(2, 3, 2));
        // graph[3].add(new Edge(3, 4, 4));
        // graph[4].add(new Edge(4, 1, -1));

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 0, 30));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    public static void bfs(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                bfsUtil(graph, i, vis);
            }
        }
    }

    public static void bfsUtil(ArrayList<Edge>[] graph, int src, boolean vis[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!vis[curr]) {
                vis[curr] = true;
                System.out.print(curr + " ");
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                dfsUtil(graph, i, vis);
            }
        }
    }

    public static void dfsUtil(ArrayList<Edge>[] graph, int curr, boolean vis[]) {
        vis[curr] = true;
        System.out.print(curr + " ");
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfsUtil(graph, e.dest, vis);
            }
        }
    }

    public static boolean hashPath(ArrayList<Edge>[] graph, int src, int dest, boolean vis[]) {
        vis[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if (e.dest == dest) {
                return true;
            }
            if (!vis[e.dest]) {
                if (hashPath(graph, e.dest, dest, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (detectCycleUtil(graph, i, vis, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, int curr, boolean vis[], int par) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                if (detectCycleUtil(graph, e.dest, vis, curr)) {
                    return true;
                }
            }
            if (vis[e.dest] && e.dest == par) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBipertite(ArrayList<Edge>[] graph) {
        int col[] = new int[graph.length];
        for (int i = 0; i < col.length; i++) {
            col[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (col[i] == -1) {
                col[i] = 1;
                q.add(i);
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    if (!vis[curr]) {
                        vis[curr] = true;
                        for (int j = 0; j < graph[curr].size(); j++) {
                            Edge e = graph[curr].get(j);
                            if (!vis[e.dest]) {
                                int nextCol = col[curr] == 0 ? 1 : 0;
                                col[e.dest] = nextCol;
                                q.add(e.dest);
                            }
                            if (col[e.dest] == col[curr]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCycle(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (isCycleUtil(graph, i, vis, stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtil(ArrayList<Edge>[] graph, int curr, boolean vis[], boolean stack[]) {
        vis[curr] = true;
        stack[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.dest]) {
                return true;
            }
            if (!vis[e.dest]) {
                if (isCycleUtil(graph, e.dest, vis, stack)) {
                    return true;
                }
            }
        }
        stack[curr] = false;
        return false;
    }

    public static void topSort(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                topSortUtil(graph, i, vis, s);
            }
        }
        System.out.println();
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }

    public static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean vis[], Stack<Integer> s) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }

    public static void calIndeg(ArrayList<Edge>[] graph, int indeg[]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }

    public static void kahnAlgo(ArrayList<Edge>[] graph) {
        int indeg[] = new int[graph.length];
        calIndeg(graph, indeg);
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");
            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;
                if (indeg[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }
    }

    public static void allPath(ArrayList<Edge>[] graph, ArrayList<Integer> list, int src, int dest) {
        if (src == dest) {
            list.add(dest);
            System.out.print(list);
            list.remove(list.size() - 1);
            return;
        }
        list.add(src);
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            allPath(graph, list, e.dest, dest);
        }
        list.remove(list.size() - 1);
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src) {
        int dist[] = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.n]) {
                vis[curr.n] = true;
                for (int i = 0; i < graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(e.dest, dist[v]));
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    public static void bellmanFord(ArrayList<Edge>[] graph, int V, int src) {
        int dist[] = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (src != i) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i <= V - 1; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                int u = e.src;
                int v = e.dest;
                int wt = e.wt;
                if (dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < graph.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    static class Pair1 implements Comparable<Pair1> {
        int v;
        int cost;

        public Pair1(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair1 p2) {
            return this.cost - p2.cost;
        }
    }

    public static void prims(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair1> pq = new PriorityQueue<>();
        pq.add(new Pair1(0, 0));
        int finalCost = 0;
        while (!pq.isEmpty()) {
            Pair1 curr = pq.remove();
            if (!vis[curr.v]) {
                vis[curr.v] = true;
                finalCost += curr.cost;
                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair1(e.dest, e.wt));
                }
            }
        }
        System.out.println(finalCost);
    }

    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        System.out.println();
        // for (int i = 0; i < graph[3].size(); i++) {
        // Edge e = graph[3].get(i);
        // System.out.println(e.src + " " + e.dest + " " + e.wt);
        // }
        // System.out.println();
        // bfs(graph);
        // System.out.println();
        // dfs(graph);
        // System.out.println();
        // System.out.println(hashPath(graph, 0, 5, new boolean[v]));
        // System.out.println();
        // System.out.println(detectCycle(graph));
        // System.out.println();
        // System.out.println(isBipertite(graph));
        // System.out.println();
        // System.out.println(isCycle(graph));
        // topSort(graph);
        // kahnAlgo(graph);
        // ArrayList<Integer> list = new ArrayList<>();
        // allPath(graph, list, 5, 1);
        // dijkstra(graph, 0);
        // bellmanFord(graph, v, 0);
        prims(graph);
    }
}