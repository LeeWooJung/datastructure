import java.util.InputMismatchException;

public class IntervalSumSegmentTree {

    public int arrSize;
    private int segTreeSize;
    private long[] segTree;

    public IntervalSumSegmentTree(int[] array, int nElements) {

        this.arrSize = array.length - 1;

        if(arrSize != nElements) {
            throw new InputMismatchException("Array Must start at index 1");
        }

        this.segTreeSize = getSegmentTreeSize(arrSize);
        this.segTree = new long[this.segTreeSize];

        // initialize segment tree
        init(array, 1, this.arrSize, 1);

    }

    private int getSegmentTreeSize(int size) {
        
        int height = (int) Math.ceil(Math.log(size) / Math.log(2));
        
        return (int)Math.pow(2, height + 1);
    }

    private long init(int[] array, int start, int end, int node) {

        if(start == end) return segTree[node] = array[start];

        int mid = (start + end) / 2;
        long leftChild = init(array, start, mid, node * 2);
        long rightChild = init(array, mid + 1, end, node * 2 + 1);

        return segTree[node] = leftChild + rightChild;
    }

    public void update(int loc, int diff) {
        update(loc, diff, 1, 1, this.arrSize);
        System.out.println("Segment Tree is updated");
    }

    private void update(int loc, int diff, int node, int start, int end) {

        if(loc < start || end < loc) return;

        segTree[node] += (long) diff;

        if(start == end) return;
        int mid = (start + end) / 2;

        update(loc, diff, node * 2, start, mid);
        update(loc, diff, node * 2 + 1, mid + 1, end);
    }

    public long getIntervalSum(int left, int right) {
        return getIntervalSum(1, this.arrSize, left, right, 1);
    }

    private long getIntervalSum(int start, int end, int left, int right, int node) {

        if(right < start || end < left) return 0;

        if(left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;

        return getIntervalSum(start, mid, left, right, node * 2) + getIntervalSum(mid + 1, end, left, right, node * 2 + 1);
    }

}
