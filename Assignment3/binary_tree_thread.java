package Assignment3;
import java.util.Arrays;
import java.util.Random;
class node_ {
    int data;
    node_ left, right;

    public node_(int val) {
        data = val;
        left = right = null;
    }
}
class binarysearchtree {
    node_ root;

    binarysearchtree() {
        root = null;
    }

    node_ sorted_array_to_bst(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        node_ node_ = new node_(arr[mid]);
        node_.left = sorted_array_to_bst(arr, start, mid - 1);
        node_.right = sorted_array_to_bst(arr, mid + 1, end);
        return node_;
    }

    public int findHeight(node_ node_) {
        if (node_ == null)
            return 0;
        else {
            int lDepth = findHeight(node_.left);
            int rDepth = findHeight(node_.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public node_ search(node_ root, int key) {
        if (root.data == key) {
            return root;
        }
        if (root.data < key)
            return search(root.right, key);
        return search(root.left, key);
    }
}
class thread1 extends Thread {
    private int arr[];
    public thread1(int[] arr) {
        this.arr = arr;
    }
    @Override

    public void run() {
        long start_time = System.nanoTime();
        binarysearchtree bst = new binarysearchtree();
        node_ root = bst.sorted_array_to_bst(arr, 0, arr.length - 1);
        long end_time= System.nanoTime();
        int h1 = bst.findHeight(root);
        long start_time2 = System.nanoTime();
        bst.search(root, arr[(arr.length - 1)]);
        long end1_time2 = System.nanoTime();
        System.out.println("The time for creation of bst :" + (end_time - start_time) + "ns");
        System.out.println("The height of tree : " + h1);
        System.out.println("The time for searching : " + (end1_time2 - start_time2) + "ns");
        System.out.println(" ");

    }
}

public class binary_tree_thread{
    public static void main(String[] args) throws InterruptedException {
        int[] size_of_arrays = {10,1000,1000000};
        int[] arr1 = new int[10];
        int[] arr2 = new int[1000];
        int[] arr3 = new int[1000000];
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            arr1[i] = r.nextInt((int) (Math.pow(10,9)-Math.pow(10,-9)+Math.pow(10,-9)));
        }
        for (int i = 0; i < 1000; i++) {
            arr2[i] = r.nextInt((int) (Math.pow(10,9)-Math.pow(10,-9)+Math.pow(10,-9)));
        }
        for (int i = 0; i < 1000000; i++) {
            arr3[i] = r.nextInt((int) (Math.pow(10,9)-Math.pow(10,-9)+Math.pow(10,-9)));
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        thread1 t1 = new thread1(arr1);
        thread1 t2 = new thread1(arr1);
        thread1 t3 = new thread1(arr2);
        thread1 t4 = new thread1(arr2);
        thread1 t5 = new thread1(arr3);
        thread1 t6 = new thread1(arr3);


        thread1 t1_1 = new thread1(arr1);
        thread1 t2_1 = new thread1(arr1);
        thread1 t3_1 = new thread1(arr1);
        thread1 t4_1 = new thread1(arr1);

        thread1 t1_2 = new thread1(arr2);
        thread1 t2_2 = new thread1(arr2);
        thread1 t3_2 = new thread1(arr2);
        thread1 t4_2 = new thread1(arr2);

        thread1 t1_3 = new thread1(arr3);
        thread1 t2_3 = new thread1(arr3);
        thread1 t3_3 = new thread1(arr3);
        thread1 t4_3 = new thread1(arr3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t1_1.start();
        t2_1.start();
        t3_1.start();
        t4_1.start();
        t1_2.start();
        t2_2.start();
        t3_2.start();
        t4_2.start();
        t1_3.start();
        t2_3.start();
        t3_3.start();
        t4_3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}