package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class RadixSort {
    public static void sort(int[] array) {
        int max = array[0];

        for (int i = 0; i < array.length; i++) { // find max element
            if (array[i] > max) {
                max = array[i];
            }
        }

        int digit = 0;
        while (max >= 1) { // find digit num
            max /= 10;
            digit++;
        }

        sort(array, digit);
    }

    private static void sort(int[] array, int d) { // d表示最大的数有多少位
        int[][] data = new int[10][array.length];

        int divisor = 1;

        for (int i = 0; i < d; i++) {
            int[] order = new int[10];

            // allocate the elements to the buckets
            for (int j = 0; j < array.length; j++) {
                int lsd = (array[j] / (divisor)) % 10; // calculate least significant digit
                data[lsd][order[lsd]] = array[j];
                order[lsd]++;
            }

            // collect the elements
            int a = 0;
            for (int j = 0; j < order.length; j++) { // j from 0 to 9
                for (int k = 0; k < order[j]; k++) {
                    array[a] = data[j][k];
                    a++;
                }
            }

            divisor *= 10;
        }
    }

    public static void main(String[] args) {
        int[] input = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
        sort(input);

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
