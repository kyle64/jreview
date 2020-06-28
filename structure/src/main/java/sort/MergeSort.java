package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class MergeSort {
    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(array, left, mid);
            sort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] n = new int[array.length];

        // copy array
        for (int i = left; i <= right; i++) {
            n[i] = array[i];
        }

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) { // two pointers moving
            // put the smaller element to original array
            if (n[i] <= n[j]) {
                array[k] = n[i++];
                k++;
            } else {
                array[k] = n[j++];
                k++;
            }
        }

        while (i <= mid) { // if left pointer is still less/equal than mid (left array elements remained), put them in the array
            array[k] = n[i++];
            k++;
        }

        while (j <= right) { // if right pointer is still less or equal than right (right array elements remained)
            array[k] = n[j++];
            k++;
        }

    }

    public static void main(String[] args) {
        int[] input = {9, 7, 2, 8, 3, 1, 5, 4, 6};
        sort(input, 0, input.length - 1);

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
