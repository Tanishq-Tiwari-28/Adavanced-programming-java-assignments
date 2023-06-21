package Assignment3;

import java.util.Random;

public class OddEvenSort_thread {
    public static void main(String[] args) {
        Random r = new Random();
        long lStartTime_arr = System.nanoTime();
        int[] size_of_array ={1,10,100,1000,10000};
        for(int x=0;x<size_of_array.length;x++){
            float arr[] = new float[size_of_array[x]];
            for ( int i = 0; i < arr.length; i++ ) {
                float random= r.nextFloat() * (10) + 0;
                arr[i] = random;
            }
            long lEndTime_arr = System.nanoTime();
            long output1 = lEndTime_arr - lStartTime_arr;
            //System.out.println("Elapsed time in microseconds: " + output1 / 1000);
            long lStartTime = System.nanoTime();
            sortArr(arr);
            long lEndTime = System.nanoTime();
            long output2 = lEndTime - lStartTime;
            System.out.println("Elapsed time to sort array of size "+size_of_array[x]+" is : " + output2 +" nanosecond.");
            System.out.println("");
        }
    }
    public static void sortArr(float[] arr) {
        int threadNum = (arr.length)/2;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(new swap(arr, 2*i + 1));
            threads[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}

class swap implements Runnable {
    private float[] arr;
    private int num;
    public swap(float[] arr, int num) {
        this.arr = arr;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[num-1] > arr[num]) {
                float t = arr[num - 1];
                arr[num - 1] = arr[num];
                arr[num] = t;
            }
            if (num + 1 < arr.length && arr[num] > arr[num + 1]) {
                float t = arr[num];
                arr[num] = arr[num + 1];
                arr[num + 1] = t;
            }
        }
    }
}