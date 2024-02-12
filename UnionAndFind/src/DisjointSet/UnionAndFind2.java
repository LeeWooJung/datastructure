package DisjointSet;

import java.util.*;

/*
 * Faster Version of Union & Find algorithm
 * - Union : rank union
 * - Find : Path compression find
 */
public class UnionAndFind2 {

    public int[] parent;
    private int[] rank;

    public UnionAndFind2(int size) {

        this.parent = new int[size+1];
        this.rank = new int[size+1];

        for(int value = 1; value < size + 1; value++) {
            parent[value] = value;
            rank[value] = 0;
        }
    }

    public int find(int value) {

        if(parent[value] != value) {
            parent[value] = find(parent[value]);
        }

        return parent[value];
    }

    
    /*
     * Union by rank algorithm
     */
    public boolean union(int x, int y) {

        int xRepresentative = find(x);
        int yRepresentative = find(y);

        if(xRepresentative == yRepresentative) return false;

        int xRepRank = rank[xRepresentative];
        int yRepRank = rank[yRepresentative];

        if(xRepRank < yRepRank) parent[xRepresentative] = yRepresentative;
        else if(xRepRank > yRepRank) parent[yRepresentative] = xRepresentative;
        else {

            boolean largeX = xRepresentative > yRepresentative ? true : false;

            if(largeX) {
                parent[xRepresentative] = yRepresentative;
                rank[yRepresentative]++;
            } else {
                parent[yRepresentative] = xRepresentative;
                rank[xRepresentative]++;
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
            sb.append(" [rank: " + rank[rep] + "]: ");
            for(int value: sets.get(rep)) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }

}
