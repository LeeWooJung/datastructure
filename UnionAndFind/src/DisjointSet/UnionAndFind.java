package DisjointSet;

import java.util.*;

/*
 * General Version of Union & Find algorithm
 * - Union : naive union
 * - Find : naive find
 */

public class UnionAndFind {

    public int[] parent;

    public UnionAndFind(int size) {
        this.parent = new int[size+1];
        for(int value = 1; value < size + 1; value++) {
            parent[value] = value;
        }
    }
    
    /*
     * General Find algorithm O(N)
     */
    
    public int find(int value) {
        if(parent[value] == value) {
            return value;
        }

        return find(parent[value]);
    }

    /*
     * General Union algorithm
     */

    public boolean union(int x, int y) {

        int xRepresentative = find(x);
        int yRepresentative = find(y);

        if(xRepresentative == yRepresentative) return false;

        boolean xLarge = xRepresentative > yRepresentative ? true : false;

        if(xLarge) parent[xRepresentative] = yRepresentative;
        else parent[yRepresentative] = xRepresentative;

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
            sb.append(++numDisjoint + " set \n").append("Rep " + rep + ": ");
            for(int value: sets.get(rep)) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }

    
}