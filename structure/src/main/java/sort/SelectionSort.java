package sort;

import java.util.Arrays;

/**
 * Created by ziheng on 2019/7/9.
 */
public class SelectionSort {
    public static void sort(int[] array) {
//        for (int i = 0; i < array.length; i++) {
//
//            int minIndex = i;
//            // unsorted array start from i
//            for (int j = i + 1; j < array.length; j++) {
//                if (array[j] < array[minIndex]) { // find min element from the unsorted array
//                    minIndex = j;
//                }
//            }
//
//            if (minIndex != i) {
//                // swap min element with arr[i]
//                int tmp = array[i];
//                array[i] = array[minIndex];
//                array[minIndex] = tmp;
//            }
//        }

        // 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
        // 然后，再从剩余未排序元素中继续寻找最小（大）元素，
        // 然后放到已排序序列的末尾。
        // 以此类推，直到所有元素均排序完毕。
        int currentMinIdx;
        boolean isChanged;
        for (int i = 0; i < array.length - 1; i++) {
            currentMinIdx = i;
            isChanged = false;
            // inner loop是未排序的部分
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[currentMinIdx]) {
                    currentMinIdx = j;
                    isChanged = true;
                }
            }
            // 把找到的最小值放到arr[i]
            if (isChanged) {
                int temp = array[i];
                array[i] = array[currentMinIdx];
                array[currentMinIdx] = temp;
            }
        }

        // 一共遍历了n + n-1 + n-2 + … + 2 + 1 = n * (n+1) / 2 = 0.5 * n ^ 2 + 0.5 * n，那么时间复杂度是O(N^2)
    }

    public static void main(String[] args) {
        int[] input = {9,7,2,8,3,1,5,5,4,6};
        sort(input);
//        Arrays.sort(new Integer[] {3,5,4});

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
