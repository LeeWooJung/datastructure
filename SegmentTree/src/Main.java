

public class Main {
    public static void main(String[] args) throws Exception {
    

        /*
         * array : int[]
         * - start at index 1
         * - must have value that equal or lager than 0
         */
        
        int[] array = {0, 1, 2, 3, 4, 5, 6}; // nElements = 6
        int nElements = 6;

        IntervalSumSegmentTree seg = new IntervalSumSegmentTree(array, nElements);
        MinMaxSegmentTree seg2 = new MinMaxSegmentTree(array, nElements);

        System.out.println("Interval 1 ~ 6 sum: " + seg.getIntervalSum(1, 6));
        System.out.println("Interval 2 ~ 4 sum: " + seg.getIntervalSum(2, 4));

        System.out.println("Min in 1 ~ 6: " + seg2.getMin(1, 6));
        System.out.println("Max in 1 ~ 6: " + seg2.getMax(1, 6));

        // update
        int loc = 3;
        int updateValue = 11;
        int diff = updateValue - array[loc];
        array[loc] += diff; // array: 0, 1, 2, 11, 4, 5, 6
        System.out.println("Array element at loc " + loc + " is updated to " + updateValue);

        seg.update(loc, diff);
        seg2.update(loc, updateValue);

        System.out.println("Interval 1 ~ 6 sum: " + seg.getIntervalSum(1, 6));
        System.out.println("Interval 2 ~ 4 sum: " + seg.getIntervalSum(2, 4));

        System.out.println("Min in 1 ~ 6: " + seg2.getMin(1, 6));
        System.out.println("Max in 1 ~ 6: " + seg2.getMax(1, 6));
        
    }
}