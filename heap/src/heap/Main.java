package heap;

public class Main {
    public static void main(String[] args) throws Exception {
        
        HEAP<Integer> heap = new HEAP<>(3);
        System.out.println("Heap size : " + heap.arraySize());

        System.out.println("================= Insert Elements =================");
        heap.insert(15);
        heap.insert(9);
        heap.insert(12);
        heap.insert(5);
        heap.insert(8);
        heap.insert(7);
        heap.insert(10);
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);

        System.out.println("================= Insertion Done =================");
        System.out.println("Heap size : " + heap.arraySize());

        System.out.println("================= Print Heap =================");
        System.out.println(heap.toString());

        System.out.println("================= Remove Element =================");
        System.out.println("Remove maximum value : " + heap.remove());
        System.out.println(heap.toString());
        System.out.println("Remove maximum value : " + heap.remove());
        System.out.println(heap.toString());
        System.out.println("Remove maximum value : " + heap.remove());
        System.out.println(heap.toString());
        System.out.println("Remove maximum value : " + heap.remove());
        System.out.println(heap.toString());
        System.out.println("Remove maximum value : " + heap.remove());
        System.out.println(heap.toString());
        System.out.println("================= Remove Done =================");
        System.out.println("Heap size : " + heap.arraySize());

        System.out.println("Maximum value : " + heap.peek());
        

    }
}
