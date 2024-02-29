package heap;

public class HEAP<E> {
    
    public E[] data;
    private int size;
    private int heapSize;

    public HEAP() {
        this(30);
    }

    @SuppressWarnings("unchecked")
    public HEAP(int size) {
        this.heapSize = 0;
        this.size = size;
        this.data = (E[]) new Object[this.size];
    }

    public boolean insert(E element) {

        if(this.heapSize + 1 >= this.size) {
            resize(this.size * 2);
        }

        this.data[++heapSize] = element;

        for(int index = heapSize; index > 1; index /= 2) {
            E parent = this.data[index/2];
            if(compare(parent, element) > 0) {
                swap(index/2, index);
            } else {
                break;
            }
        }

        return true;
    }

    public E remove() {

        if(isEmpty()) {
            System.out.println("There is no element in this heap.");
            return null;
        }

        E element = this.data[1];
        E toReturn = element;

        if(heapSize == 1) {
            element = null;
            heapSize--;
        } else {
            E lastElem = this.data[heapSize];
            this.data[heapSize] = null;
            this.data[1] = lastElem;
            heapSize--;
            replace(1);
        }

        if(heapSize < this.size / 2) {
            resize(this.size / 2);
        }

        return toReturn;
    }

    private void replace(int pIndex) {

        int lIndex = pIndex * 2;
        int rIndex = pIndex * 2 + 1;

        boolean leftOver = lIndex > heapSize;
        boolean rightOver = rIndex > heapSize;

        E leftChild = null;
        E rightChild = null;

        if(!leftOver) leftChild = data[lIndex];
        if(!rightOver) rightChild = data[rIndex];

        if(leftChild == null && rightChild == null) {
            return;
        } else if(leftChild != null && rightChild == null) {
            if(compare(data[pIndex], data[lIndex]) > 0) { // left child is larger
                swap(pIndex, lIndex);
                replace(lIndex);
            } else return;
        } else if(leftChild == null && rightChild != null) { // right child is larger
            if(compare(data[pIndex], data[rIndex]) > 0) {
                swap(pIndex, rIndex);
                replace(rIndex);
            } else return;
        } else {
            int comp = compare(data[rIndex], data[lIndex]);
            if(comp > 0) { // left child is larger
                if(compare(data[pIndex], data[lIndex]) > 0) {
                    swap(pIndex, lIndex);
                    replace(lIndex);
                } else return;
            } else { // right child is larger
                if(compare(data[pIndex], data[rIndex]) > 0) {
                    swap(pIndex, rIndex);
                    replace(rIndex);
                } else return;
            }
        }
        return;
    }

    private void swap(int pIndex, int cIndex) {
        E temp;
        temp = this.data[pIndex];
        this.data[pIndex] = this.data[cIndex];
        this.data[cIndex] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {

        E[] newArray = (E[]) new Object[newSize];
        for(int index = 1; index < this.data.length; index++) {
            newArray[index] = this.data[index];
        }

        this.data = null;
        this.data = newArray;
        this.size = newSize;
    }

    /*
     * if element is larger than parent, then return positive
     * else if element is smaller than parent, then return negative
     * (this source code assumes that there is no element which has same value)
     */

    private int compare(E parent, E element) {

        @SuppressWarnings("unchecked")
        Comparable<? super E> toCompare = (Comparable<? super E>) element;
        int result;

        result = toCompare.compareTo(parent);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(isEmpty()) {
            sb.append("Empty!");
            return sb.toString();
        }

        for(int index = 1; index <= this.heapSize; index++) {
            sb.append(this.data[index] + " ");
        }

        return sb.toString();
    }

    public int size() {
        return this.heapSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E peek() {
        
        if(isEmpty()) {
            System.out.println("There is no element in this heap");
            return null;
        }

        return this.data[1];
    }
}

