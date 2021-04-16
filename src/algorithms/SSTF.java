package algorithms;

public class SSTF {

    public static int getSeek_count() {
        return seek_count;
    }

    public static int[] getSequence() {
        return seek_sequence;
    }
    private static int seek_count;
    private static int[] seek_sequence;

    public static void calculateDifference(int queue[], int head, Node diff[])
    {
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }

    public static int findMin(Node diff[])
    {
        int index = -1, minimum = Integer.MAX_VALUE;

        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {

                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void simulate(int request[], int head)

    {
        if (request.length == 0)
            return;

        Node diff[] = new Node[request.length];

        for (int i = 0; i < diff.length; i++)

            diff[i] = new Node();

        seek_count = 0;

        seek_sequence = new int[request.length + 1];

        for (int i = 0; i < request.length; i++) {

            seek_sequence[i] = head;
            calculateDifference(request, head, diff);

            int index = findMin(diff);

            diff[index].accessed = true;

            seek_count += diff[index].distance;

            head = request[index];
        }

        seek_sequence[seek_sequence.length - 1] = head;

    }
}