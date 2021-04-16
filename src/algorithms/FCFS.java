package algorithms;

public class FCFS {


    private static int[] sequence;
    private static int seek_count;

    public static void simulate(int arr[], int head)
    {
        sequence = new int[arr.length+1];
        sequence[0] = head;
        seek_count = 0;
        int distance, cur_track;

        for (int i = 0; i < arr.length; i++)
        {
            cur_track = arr[i];
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }

        for (int i = 1; i < arr.length+1; i++)
        {
           sequence[i] = arr[i-1];
        }

    }

    public static int[] getSequence() {
        return sequence;
    }

    public static int getSeek_count() {
        return seek_count;
    }

}