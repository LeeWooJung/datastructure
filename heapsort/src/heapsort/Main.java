package heapsort;

public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] array = {1,4,2,9,5,3,4,2};

        // ascending order
        HeapSort<Integer> x = new HeapSort<>(array);
        x.sort();

        StringBuilder sb = new StringBuilder();
        sb.append("Ascending Order : ");
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i] + " ");
        }
        System.out.println(sb.toString());

        // descending order
        HeapSort<Integer> y = new HeapSort<>(array, false);
        y.sort();
        
        sb = new StringBuilder();
        sb.append("Descending Order : ");
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i] + " ");
        }
        System.out.println(sb.toString());
    }
}
