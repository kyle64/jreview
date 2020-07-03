package sort;

/**
 * Created by ziheng on 2019/7/8.\
 * 插入排序
 */
public class InsertSort {
    public static void sort(int[] array) {
//        for (int i = 0; i < array.length - 1; i++) { // first element is sorted
//            for (int j = i + 1; j > 0; j--) {
//                if (array[j-1] > array[j]) { // a[0] - a[j-1] is sorted, find position of a[j]
//                    // move a[j-1] to next, swap
//                    int tmp = array[j];
//                    array[j] = array[j-1];
//                    array[j-1] = tmp;
//                }
//            }
//        }

        // 外层循环控制需要排序的趟数（从1开始因为0可以视作已排序）
        for (int i = 0; i < array.length - 1; i++) {
            // i+1个数已排序
            int temp = array[i + 1]; // 本次循环待排序的数
            int j;
            // 如果前一位（已排序的i-1个数）比当前的数要大，那么需要循环比较，寻找新插入的数的位置
            for (j = i; j >= 0; j--) {
                // 比temp大的数都后移
                if (array[j] > temp) {
                    array[j+1] = array[j];
                }
                // 找到比temp小的就退出循环
                else break;
            }
            // 结束inner循环，说明第i个数的位置已经找到
            array[j+1] = temp;
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
