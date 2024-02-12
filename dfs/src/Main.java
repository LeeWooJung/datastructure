import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    // Graph 선언 - node를 담을 배열로 선언
    static node[] Graph = new node[9];
    // Path 선언
    static List<edge> path = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        
        // Graph 내 node 선언
        for(int node = 1; node <= 8; node++) {
            Graph[node] = new node(node);
        }

        // Edge 선언
        List<edge> edges = new ArrayList<>();
        edges.add(new edge(1, 2)); edges.add(new edge(2, 1));
        edges.add(new edge(1, 3)); edges.add(new edge(3, 1));
        edges.add(new edge(3, 4)); edges.add(new edge(4, 3));
        edges.add(new edge(2, 5)); edges.add(new edge(5, 2));
        edges.add(new edge(4, 6)); edges.add(new edge(6, 4));
        edges.add(new edge(5, 7)); edges.add(new edge(7, 5));
        edges.add(new edge(7, 8)); edges.add(new edge(8, 7));
        edges.add(new edge(6, 7)); edges.add(new edge(7, 6));

        

        // Graph 내 node와 edge 연결
        for(int node = 1; node <= 8; node++) {
            for(edge E : edges) {
                if(E.start == node) {
                    Graph[node].adjacent.add(E.end);
                }
            }
        }

        // DFS - 재귀함수
        //DFS_recursion(1);
        // DFS - Stack
        DFS_stack(1);

        for(edge p: path) {
            if(p.start == -1) continue;
            System.out.println(p.start + " ------ " + p.end);
        }

    }

    public static void DFS_recursion(int current) {
        if(Graph[current].visited) return;
        path.add(new edge(Graph[current].parent, current));

        Graph[current].visited = true;
        for(int child: Graph[current].adjacent) {
            Graph[child].parent = current;
            DFS_recursion(child);
        }
    }

    public static void DFS_stack(int current) {
        Stack<Integer> stack = new Stack<>();
        stack.push(current);

        while(!stack.isEmpty()) {
            current = stack.pop();
            if(Graph[current].visited) continue;

            Graph[current].visited = true;
            path.add(new edge(Graph[current].parent, current));

            for(int child: Graph[current].adjacent) {
                if(Graph[child].visited) continue;
                Graph[child].parent = current;
                stack.push(child);
            }
        }
    }
}

/*
 * @class           node
 * @instance value  value: node의 값
 * @instance value  parent: DFS를 진행할 때 해당 노드를 거치기 이전의 부모
 * @instance value  visited: node의 탐색 여부
 * @instance value  adjacent: 인접한 노드 list
 */

class node {
    int value;
    int parent;
    boolean visited;
    List<Integer> adjacent;

    node(int value) {
        this.value = value;
        this.parent = -1;
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