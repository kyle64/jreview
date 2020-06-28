package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class SelectionSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {

            int minIndex = i;
            // unsorted array start from i
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) { // find min element from the unsorted array
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // swap min element with arr[i]
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {9,7,2,8,3,1,5,4,6};
        sort(input);

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
