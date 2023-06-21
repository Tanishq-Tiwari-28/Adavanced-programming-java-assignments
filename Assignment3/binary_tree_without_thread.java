package Assignment3;

import java.util.Arrays;
import java.util.Random;
class node {
    int data;
    node left, right;

    public node(int val) {
        data = val;
        left = right = null;
    }
}
class Binary_searchtree {
    node root;
    Binary_searchtree() {
        root = null;
    }
    node sorted_array_to_bst(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        node node = new node(arr[mid]);
        node.left = sorted_array_to_bst(arr, start, mid - 1);
        node.right = sorted_array_to_bst(arr, mid + 1, end);
        return node;
    }

    public int findHeight(node node) {
        if (node == null)
            return 0;
        else {
            int lDepth = findHeight(node.left);
            int rDepth = findHeight(node.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    public node search(node root, int key) {
        if (root.data == key) {
            return root;
        }
        if (root.data < key)
            return search(root.right, key);
        return search(root.left, key);
    }
}

class bst_without_threads {
    private int[] arr;

    public bst_without_threads(int[] arr) {
        this.arr = arr;
        // this.val = val;
    }

    public void func() {
        long start = System.nanoTime();
        Binary_searchtree bst = new Binary_searchtree();
        node root = bst.sorted_array_to_bst(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        int h = bst.findHeight(root);
        long start1 = System.nanoTime();
        bst.search(root, arr[(arr.length - 1)]);
        long end1 = System.nanoTime();
        System.out.println("The time for creation of bst: " + (end - start) + "ns");
        System.out.println("The height of tree : " + h);
        System.out.println("The time for searching : " + (end1 - start1) + "ns");
    }

}
public class binary_tree_without_thread{
    public static void main(String[] args) {
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
        bst_without_threads  t1 = new  bst_without_threads (arr1);
        bst_without_threads  t2 = new  bst_without_threads (arr2);
        bst_without_threads  t3 = new  bst_without_threads (arr3);
        t1.func();
        t2.func();
        t3.func();


    }
}
