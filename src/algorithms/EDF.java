package algorithms;
import java.util.Random;

public class EDF {

    private static int[] sequence;
    private static int seek_count;
    private static int[] arrDeadlines;

    public static void simulate(int arr[], int head, int deadlines)
    {


        arrDeadlines = new int[deadlines];
        int[] rest = new int[arr.length-arrDeadlines.length];

        int[] deadlineIdndices = new int[arrDeadlines.length];
        for (int i = 0; i< deadlineIdndices.length; i++){
            int random = getRandomNumberInRange(0, arr.length-1);
            while (contais(random,deadlineIdndices))
                random = getRandomNumberInRange(0,arr.length-1);
            deadlineIdndices[i] = random;
        }

        int indexD=0;
        int indexR=0;

        for (int i =0; i<arr.length;i++){
            if(contais(i,deadlineIdndices)){
                arrDeadlines[indexD]=arr[i];
                indexD++;
            }else {
                rest[indexR] = arr[i];
                indexR++;
            }
        }


        SSTF.simulate(arrDeadlines,head);
        int[] firstArr = SSTF.getSequence();
        int firstSeek = SSTF.getSeek_count();

        FCFS.simulate(rest,firstArr[firstArr.length-1]);
        int[] secondArr = FCFS.getSequence();
        int secondSeek = FCFS.getSeek_count();

        sequence = concat(firstArr,secondArr);
        seek_count = firstSeek + secondSeek;

//        sequence = new int[arr.length+1];
//        sequence[0] = head;
//        for (int i = 1; i < array.length+1; i++)
//        {
//            sequence[i] = array[i-1];
//        }

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