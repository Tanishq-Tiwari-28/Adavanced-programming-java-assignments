package Assignment3;

import java.util.Random;

public class odd_even_sort{
    public static void sort(float a[], int size){

        boolean is_sorted= false;
        while(!is_sorted){
            is_sorted = true;
            float temp=0;
            float temp2=0;
            for(int i=0;i<=size-2;i=i+2){
                if(a[i]>a[i+1]){
                    temp=a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp;
                    is_sorted = false;
                }
            }
            for(int i=1;i<=size-2;i=i+2){
                if(a[i]>a[i+1]){
                    temp2=a[i];
                    a[i]=a[i+1];
                    a[i+1]=temp2;
                    is_sorted = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int[] size_of_array ={1,10,100,1000,10000};
        for(int x=0;x<size_of_array.length;x++){
            float arr[] = new float[10];
            for ( int i = 0; i < arr.length; i++ ) {
                float random= r.nextFloat() * (10) + 0;
                arr[i] = random;
            }
            long lStartTime = System.nanoTime();
            sort(arr,arr.length);
            long lEndTime = System.nanoTime();
            long output2 = lEndTime - lStartTime;
            System.out.println("Elapsed time to sort array of size "+size_of_array[x]+" is : " + output2 +" nanosecond.");
            System.out.println("");
        }

    }
}
