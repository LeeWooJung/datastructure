package heapsort;

public class HeapSort<E> {
    public E[] data;
    public int size;
    public boolean asc;

    private int heapSize;

    public HeapSort(E[] data) {
        this(data, true);
    }

    public HeapSort(E[] data, boolean asc) {
        this.data = data;
        this.size = data.length;
        this.heapSize = this.size - 1;
        this.asc = asc;
    }

    public void sort() {
        if(size < 2) return;

        int startidx = getParentIndex(size-1);

        // make maxheap
        while(startidx >= 0) {
            heapify(startidx);
            startidx--;
        }

        // sorting progress
        while(heapSize > 0) {
            swap(0, heapSize--);
            heapify(0);
        }

        return;
    }

    private void heapify(int index) {
        int pIdx = index;
        int lcIdx; // left child index
        int rcIdx; // right child index
        int pickedIdx;

        while((lcIdx = getLeftChildIndex(pIdx)) != -1) {
            
            pickedIdx = compare(data[pIdx], data[lcIdx]) >= 0 ? lcIdx : pIdx;

            rcIdx = getRightChlidIndex(pIdx);
            if(rcIdx != -1) {
                pickedIdx = compare(data[pickedIdx], data[rcIdx]) >= 0 ? rcIdx : pickedIdx;
            }

            if(pIdx != pickedIdx) { // now. this subtree doesn't satisfy max heap structure
                swap(pIdx, pickedIdx);
                pIdx = pickedIdx;
            } else {
                break;
            }
        }
    }

    private int getParentIndex(int index) {
        return (index - 1)/2;
    }

    private int getLeftChildIndex(int index) {
        int leftchildIndex = index * 2 + 1;
        return leftchildIndex > heapSize ? -1 : leftchildIndex;
    }

    private int getRightChlidIndex(int index) {
        int rightChildIndex = index * 2 + 2;
        return rightChildIndex > heapSize ? -1 : rightChildIndex;
    }

    /*
     * if element is larger than parent, then result is positive value
     * else if element is smaller than parent, then result is negative value
     * else if each value is same then return 0
     */
    private int compare(E parent, E element) {
        @SuppressWarnings("unchecked")
        Comparable<? super E> toCompare = (Comparable<? super E>) element;
        int result = toCompare.compareTo(parent);
        return asc ? result : -result;
    }

    private void swap(int first, int second) {
        E temp;
        temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }
}
