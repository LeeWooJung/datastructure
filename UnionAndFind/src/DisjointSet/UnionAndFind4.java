package DisjointSet;

import java.util.*;

/*
 * Faster Version of Union & Find algorithm
 * - Union : inrank union (less memory consumption)
 * - Find : Path compression find + inrank union
 */

public class UnionAndFind4 {
    
    public int[] parent;

    public UnionAndFind4(int size) {
        this.parent = new int[size+1];
        for(int value = 1; value < size + 1; value++) {
            parent[value] = 0;
        }
    }

    /*
     * Path Compression Find algorithm 
     * & inRank Union algorithm
     */
    public int find(int value) {
        
        if(parent[value] > 0) {
            parent[value] = find(parent[value]);
            return parent[value];
        }
        return value;
    }

    /*
     * Union by inRank algorithm
     */
    public boolean union(int x, int y) {

        int xRepresentative = find(x);
        int yRepresentative = find(y);

        if(xRepresentative == yRepresentative) return false;

        int xRepRank = Math.abs(parent[xRepresentative]);
        int yRepRank = Math.abs(parent[yRepresentative]);

        if(xRepRank > yRepRank) {
            parent[yRepresentative] = xRepresentative;
        } else if(yRepRank > xRepRank) {
            parent[xRepresentative] = yRepresentative;
        } else {
            boolean xLarge = xRepresentative > yRepresentative ? true : false;
            if(xLarge) {
                parent[xRepresentative] = yRepresentative;
                parent[yRepresentative]--; // rank 증가
            } else {
                parent[yRepresentative] = xRepresentative;
                parent[xRepresentative]--; // rank 증가
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
            if(parent[value] <= 0) {
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
            sb.append(" [rank: " + -this.parent[rep] + "]: ");
            for(int value: sets.get(rep)) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }
}
