package algorithms;
import java.util.*;

public class CSCAN {

    private static int[] sequence;
    private static int seek_count;

    public static void simulate(int arr[], int head, String direction, int disk_size)
    {
        seek_count = 0;
        int distance, cur_track;
        int firstElement = head;
        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence
                = new Vector<Integer>();


        left.add(0);
        right.add(disk_size);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }
        Collections.sort(left);
        Collections.sort(right);

        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);
            seek_sequence.add(cur_track);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }

        head = 0;
        seek_count += (disk_size - 1);

        for (int i = 0; i < left.size(); i++) {
            cur_track = left.get(i);
            seek_sequence.add(cur_track);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }
        sequence = new int[seek_sequence.size()+2];
        sequence[0]=firstElement;
        for (int i = 1; i < seek_sequence.size()+1; i++)
        {
            sequence[i]=seek_sequence.get(i-1);
        }
        sequence[sequence.length-1] = firstElement;
    }


    public static int[] getSequence() {
        return sequence;
    }

    public static int getSeek_count() {
        return seek_count;
    }

}