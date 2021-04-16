package algorithms;
import java.util.*;
public class FDSCAN {

    private static int[] sequence;
    private static int seek_count;
    private static int[] arrDeadlines;
    private static int[] deadlineSequence;
    private static int deadlineCount;
    private static int[] restSequence;
    private static int restCount;
    public static void simulate(int arr[], int head, String direction, int disk_size, int deadlines) {


        arrDeadlines = new int[deadlines];

        int[] rest = new int[arr.length - arrDeadlines.length];

        int[] deadlineIdndices = new int[arrDeadlines.length];
        for (int i = 0; i < deadlineIdndices.length; i++) {
            int random = getRandomNumberInRange(0, arr.length - 1);
            while (contais(random, deadlineIdndices))
                random = getRandomNumberInRange(0, arr.length - 1);
            deadlineIdndices[i] = random;
        }

        int indexD = 0;
        int indexR = 0;

        for (int i = 0; i < arr.length; i++) {
            if (contais(i, deadlineIdndices)) {
                arrDeadlines[indexD] = arr[i];
                indexD++;
            } else {
                rest[indexR] = arr[i];
                indexR++;
            }
        }


        SCAN.simulate(arrDeadlines,head,direction,disk_size);
        int[] par1 = SCAN.getSequence();
        int s1 = SCAN.getSeek_count();
        if(direction =="left")

            SCAN.simulate(rest, par1[par1.length - 1], "right", disk_size);
        else
            SCAN.simulate(rest, par1[par1.length - 1], "left", disk_size);

        int[] par2 = SCAN.getSequence();
        int s2 = SCAN.getSeek_count();
        sequence = concat(par1,par2);
        seek_count = s1 + s2;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static boolean contais(int x, int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==x)
                return true;
        }
        return false;
    }

    private static int[] concat (int[] arr1, int[] arr2){
        int l1= arr1.length;
        int l2 = arr2.length;
        int[] arr = new int[l1+l2-1];
        int i =0;
        for (;i<arr1.length;i++){
            arr[i]=arr1[i];
        }
        for(int j =0;j<arr2.length-1;j++){
            arr[j+i]=arr2[j+1];
        }
        return arr;
    }


    public static int[] getSequence() {
        return sequence;
    }

    public static int getSeek_count() {
        return seek_count;
    }

    public static int[] getArrDeadlines() {
        return arrDeadlines;
    }
}
