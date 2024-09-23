import java.util.InputMismatchException;

/*
 * assumes that array elements are equal or larget than 0
 */

public class MinMaxSegmentTree {

    public int arrSize;
    private int segTreeSize;
    private minmax[] segTree;

    public MinMaxSegmentTree(int[] array, int nElements) {

        this.arrSize = array.length - 1;

        if(arrSize != nElements) {
            throw new InputMismatchException("Array Must start at index 1");
        }

        this.segTreeSize = getSegmentTreeSize(arrSize);
        this.segTree = new minmax[this.segTreeSize];

        // initialize segment tree
        init(array, 1, this.arrSize, 1);

    }

    private int getSegmentTreeSize(int size) {
        
        int height = (int) Math.ceil(Math.log(size) / Math.log(2));
        
        return (int)Math.pow(2, height + 1);
    }

    private minmax init(int[] array, int start, int end, int node) {

        if(start == end) {
            return segTree[node] = new minmax(array[start], array[start]);
        }

        int mid = (start + end) / 2;
        minmax leftChild = init(array, start, mid, node * 2);
        minmax rightChild = init(array, mid + 1, end, node * 2 + 1);

        int min = Math.min(leftChild.min, rightChild.min);
        int max = Math.max(leftChild.max, rightChild.max);

        return segTree[node] = new minmax(min, max);
    }

    public void update(int loc, int newValue) {
        update(loc, newValue, 1, 1, this.arrSize);
    }

    private void update(int loc, int newValue, int node, int start, int end) {

        if(loc < start || end < loc) return;

        if(start == end) {
            segTree[node].min = newValue;
            segTree[node].max = newValue;
            return;
        }

        segTree[node].min = Math.min(segTree[node].min, newValue);
        segTree[node].max = Math.max(segTree[node].max, newValue);

        int mid = (start + end) / 2;

        update(loc, newValue, node * 2, start, mid);
        update(loc, newValue, node * 2 + 1, mid + 1, end);
    }

    public int getMax(int left, int right) {
        return getMax(1, this.arrSize, left, right, 1);
    }

    private int getMax(int start, int end, int left, int right, int node) {

        if(right < start || end < left) return - 1;

        if(left <= start && end <= right) return segTree[node].max;

        int mid = (start + end) / 2;

        int leftMax = getMax(start, mid, left, right, node * 2);
        int rightMax = getMax(mid + 1, end, left, right, node * 2 + 1);

        return (int)Math.max(leftMax, rightMax);
    }

    public int getMin(int left, int right) {
        return getMin(1, this.arrSize, left, right, 1);
    }

    private int getMin(int start, int end, int left, int right, int node) {

        if(right < start || end < left) return - 1;

        if(left <= start && end <= right) return segTree[node].min;

        int mid = (start + end) / 2;

        int leftMin = getMin(start, mid, left, right, node * 2);
        int rightMin = getMin(mid + 1, end, left, right, node * 2 + 1);

        return (int)Math.min(leftMin, rightMin);
    }


    private class minmax {
        int min;
        int max;

        minmax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

}
