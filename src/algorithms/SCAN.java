package algorithms;
import java.util.*;
public class SCAN {

    private static int[] sequence;
    private static int seek_count;

    public static void simulate(int arr[], int head, String direction, int disk_size)
    {
        int firstElement = head;
        seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<>(),
                right = new Vector<>();
        Vector<Integer> seek_sequence = new Vector<>();

        if (direction == "left")
            left.add(0);
        else if (direction == "right")
            right.add(disk_size);

        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        Collections.sort(left);
        Collections.sort(right);

        int run = 2;
        while (run-- >0)
        {
            if (direction == "left")
            {
                for (int i = left.size() - 1; i >= 0; i--)
                {
                    cur_track = left.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "right";
            }
            else if (direction == "right")
            {
                for (int i = 0; i < right.size(); i++)
                {
                    cur_track = right.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "left";
            }
        }

        sequence = new int[seek_sequence.size()+2];
        sequence[0]=firstElement;
        for (int i = 1; i < seek_sequence.size()+1; i++)
        {
            sequence[i]=seek_sequence.get(i-1);
        }
        if(direction =="right")
            sequence[sequence.length-1] = 0;
        else sequence[sequence.length-1] = disk_size;

    }

    public static int[] getSequence() {
        return sequence;
    }

    public static int getSeek_count() {
        return seek_count;
    }
}
