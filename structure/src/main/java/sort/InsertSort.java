package sort;

/**
 * Created by ziheng on 2019/7/8.\
 * 插入排序
 */
public class InsertSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) { // first element is sorted
            for (int j = i + 1; j > 0; j--) {
                if (array[j-1] > array[j]) { // a[0] - a[j-1] is sorted, find position of a[j]
                    // move a[j-1] to next, swap
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
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
