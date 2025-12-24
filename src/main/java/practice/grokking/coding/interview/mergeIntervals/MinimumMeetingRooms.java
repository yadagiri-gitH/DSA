package practice.grokking.coding.interview.mergeIntervals;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MinimumMeetingRooms {

    public static int findMinimumMeetingRooms(List<Meeting> meetings) {

        if (meetings == null || meetings.size() == 0)
            return 0;

        if (meetings.size() < 2)
            return meetings.size();

        // Sort once by start time
        meetings.sort((a, b) -> Integer.compare(a.start, b.start));

        // Choose ONE approach
        // return bruteForcefindMinimumRooms(meetings);
        // return arraySolutionfindMinimumRooms(meetings);
        return heapSolutionfindMinimumRooms(meetings);
    }

    // ===== 1️⃣ BRUTE FORCE (O(N^2)) =====
    public static int bruteForcefindMinimumRooms(List<Meeting> meetings) {

        int maxRooms = 0;

        for (int i = 0; i < meetings.size(); i++) {
            int end = meetings.get(i).end;
            int rooms = 1;

            for (int j = 0; j < i; j++) {
                if (meetings.get(j).start < end) {
                    rooms++;
                }
            }
            maxRooms = Math.max(maxRooms, rooms);
        }

        return maxRooms;
    }

    // ===== 2️⃣ TWO-ARRAY SOLUTION (O(N log N)) =====
    public static int arraySolutionfindMinimumRooms(List<Meeting> meetings) {

        int n = meetings.size();
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = meetings.get(i).start;
            ends[i] = meetings.get(i).end;
        }

        // IMPORTANT: ends MUST be sorted independently
        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j = 0;
        int rooms = 0, maxRooms = 0;

        while (i < n) {
            if (starts[i] < ends[j]) {
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                i++;
            } else {
                rooms--;
                j++;
            }
        }

        return maxRooms;
    }

    // ===== 3️⃣ MIN-HEAP SOLUTION (O(N log N)) =====
    public static int heapSolutionfindMinimumRooms(List<Meeting> meetings) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // First meeting
        heap.offer(meetings.get(0).end);

        for (int i = 1; i < meetings.size(); i++) {
            if (meetings.get(i).start >= heap.peek()) {
                heap.poll(); // reuse room
            }
            heap.offer(meetings.get(i).end);
        }

        return heap.size();
    }

    // ===== TESTING =====
    public static void main(String[] args) {

        List<Meeting> input = Arrays.asList(
                new Meeting(1, 4),
                new Meeting(2, 5),
                new Meeting(7, 9)
        );
        System.out.println(findMinimumMeetingRooms(input)); // 2

        input = Arrays.asList(
                new Meeting(6, 7),
                new Meeting(2, 4),
                new Meeting(8, 12)
        );
        System.out.println(findMinimumMeetingRooms(input)); // 1

        input = Arrays.asList(
                new Meeting(1, 4),
                new Meeting(2, 3),
                new Meeting(3, 6)
        );
        System.out.println(findMinimumMeetingRooms(input)); // 2

        input = Arrays.asList(
                new Meeting(1, 4),
                new Meeting(2, 3),
                new Meeting(3, 5),
                new Meeting(3, 6),
                new Meeting(4, 9),
                new Meeting(7, 8),
                new Meeting(7, 8),
                new Meeting(7, 8),
                new Meeting(8, 9)
        );
        System.out.println(findMinimumMeetingRooms(input)); // 4
    }
}

