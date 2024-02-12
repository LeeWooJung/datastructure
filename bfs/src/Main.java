
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {

        // Graph 선언 - node를 담을 배열로 선언
        node[] Graph = new node[9];

        // Graph 내 node 선언
        for(int node = 1; node <= 8; node++) {
            Graph[node] = new node(node);
        }

        // Edge 선언
        List<edge> edges = new ArrayList<>();
        edges.add(new edge(1, 3)); edges.add(new edge(3, 1));
        edges.add(new edge(1, 2)); edges.add(new edge(2, 1));
        edges.add(new edge(3, 4)); edges.add(new edge(4, 3));
        edges.add(new edge(2, 5)); edges.add(new edge(5, 2));
        edges.add(new edge(4, 6)); edges.add(new edge(6, 4));
        edges.add(new edge(5, 7)); edges.add(new edge(7, 5));
        edges.add(new edge(6, 7)); edges.add(new edge(7, 6));
        edges.add(new edge(7, 8)); edges.add(new edge(8, 7));

        // Path 선언
        List<edge> path = new ArrayList<>();

        // Graph 내 node와 edge 연결
        for(int node = 1; node <= 8; node++) {
            for(edge E : edges) {
                if(E.start == node) {
                    Graph[node].adjacent.add(E.end);
                }
            }
        }

        // BFS
        int startNode = 1;
        int current;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        while(!queue.isEmpty()) {
            current = queue.poll();

            if(Graph[current].visited) continue;

            for(int child : Graph[current].adjacent){
                if(!Graph[child].visited) {
                    queue.offer(child);
                    path.add(new edge(current, child));
                }
            }

            Graph[current].visited = true;
        }

        for(edge p: path) {
            System.out.println(p.start + " ---- " + p.end);
        }

    }
}

/*
 * @class               node
 * @instance variable   value : node의 값
 * @instance variable   visited : node의 탐색 여부
 * @instance variable   adjacent : 인접한 노드 List
 */

class node {
    int value;
    boolean visited;
    List<Integer> adjacent;

    node(int value) {
        this.value = value;
        this.visited = false;
        this.adjacent = new LinkedList<>();
    }
}

class edge {
    int start;
    int end;

    edge(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
