package DisjointSet;

import java.util.*;

/*
 * Faster Version of Union & Find algorithm
 * - Union : size union
 * - Find : Path compression find
 */

public class UnionAndFind3 {

    public int[] parent;
    private int[] size;

    public UnionAndFind3(int size) {

        this.parent = new int[size+1];
        this.size = new int[size+1];

        for(int value = 1; value < size + 1; value++) {
            parent[value] = value;
            this.size[value] = 1;
        }
    }

    public int find(int value) {

        if(parent[value] != value) {
            parent[value] = find(parent[value]);
        }

        return parent[value];
    }

    
    /*
    * Union by size algorithm
    */
    public boolean union(int x, int y) {

        int xRepresentative = find(x);
        int yRepresentative = find(y);

        int xSize = this.size[xRepresentative];
        int ySize = this.size[yRepresentative];

        if(xRepresentative == yRepresentative) return false;

        if(xSize < ySize) {
            parent[xRepresentative] = yRepresentative;
            this.size[yRepresentative] += this.size[xRepresentative];
        } else if(xSize > ySize) {
            parent[yRepresentative] = xRepresentative;
            this.size[xRepresentative] += this.size[yRepresentative];
        } else {
            boolean largeX = xRepresentative > yRepresentative ? true : false;
            if(largeX) {
                parent[xRepresentative] = yRepresentative;
                this.size[yRepresentative] += this.size[xRepresentative];
            } else {
                parent[yRepresentative] = xRepresentative;
                this.size[xRepresentative] += this.size[yRepresentative];
            }
        }

        return true;
    }

    @Override
    public String toString() {
        int numDisjoint = 0;
        int key;
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, ArrayList<Integer>> sets = new HashMap<>();

        for(int value = 1; value < this.parent.length; value++) {
            ArrayList<Integer> list = new ArrayList<>();
            if(parent[value] == value) {
                key = value;
                list.add(value);
            } else {
                key = find(value);
                list = sets.get(key);
                list.add(value);
            }
            sets.put(key, list);
        }

        for(int rep: sets.keySet()) {
            sb.append(++numDisjoint + " set \n").append("Rep " + rep);
            sb.append(" [size: " + this.size[rep] + "]: ");
            for(int value: sets.get(rep)) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }
}
