package DisjointSet;

import DisjointSet.*;

public class Main {
    public static void main(String[] args) throws Exception {
        

        UnionAndFind4 uf = new UnionAndFind4(10);
        
        uf.union(1,2);
        uf.union(1, 4);
        uf.union(3, 4);
        uf.union(5, 7);
        uf.union(5, 8);
        uf.union(6, 7);
        uf.union(7, 9);


        System.out.println(uf.toString());
    }
}
