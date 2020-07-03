package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class MergeSort {
//    public static void sort(int[] array, int left, int right) {
//        if (left < right) {
//            int mid = (left + right) / 2;
//
//            sort(array, left, mid);
//            sort(array, mid + 1, right);
//
//            merge(array, left, mid, right);
//        }
//    }
//
//    private static void merge(int[] array, int left, int mid, int right) {
//        int[] n = new int[array.length];
//
//        // copy array
//        for (int i = left; i <= right; i++) {
//            n[i] = array[i];
//        }
//
//        int i = left, j = mid + 1, k = left;
//
//        while (i <= mid && j <= right) { // two pointers moving
//            // put the smaller element to original array
//            if (n[i] <= n[j]) {
//                array[k] = n[i++];
//                k++;
//            } else {
//                array[k] = n[j++];
//                k++;
//            }
//        }
//
//        while (i <= mid) { // if left pointer is still less/equal than mid (left array elements remained), put them in the array
//            array[k] = n[i++];
//            k++;
//        }
//
//        while (j <= right) { // if right pointer is still less or equal than right (right array elements remained)
//            array[k] = n[j++];
//            k++;
//        }
//
//    }

    public static void msort(int[] array) {
        msortHelper(array, 0, array.length - 1);
    }

    private static void msortHelper(int[] array, int left, int right) {
//        if (left < right) {
//            int mid = (left + right) / 2;
//
//            msortHelper(array, left, mid);
//            msortHelper(array, mid+1, right);
//        }

        int mid = (left + right) / 2;
        if (left < right) {
            msortHelper(array, left, mid);
            msortHelper(array, mid + 1, right);
            // 把两部分排序完之后，进行归并
            msortMerge(array, left, right);
        }
    }

    private static void msortMerge(int[] array, int left, int right) {
        int[] result = new int[right - left + 1];
        int mid = (left + right) / 2;
        // left到mid以及mid+1到right都是已排序的，合并
        int i = left, j = mid + 1;
        int pos = 0;

        // 两个指针分别指向arr[left,mid]和arr[mid+1,right]两个区间
        while (i <= mid && j <= right) {
            // 比较两个指针指向的数，将比较小的那个放入排序数组，移动指针 直到 其中一个区间内的数全部都放入已排序的数组为止
            if (array[i] < array[j]) {
                result[pos++] = array[i++];
            } else {
                result[pos++] = array[j++];
            }
        }

        // 如果[left,mid]还有剩余数字，加入已排序数组
        while (i <= mid) {
            result[pos++] = array[i++];
        }

        // 如果[mid+1,right]还有剩余数字，加入已排序数组
        while (j <= right) {
            result[pos++] = array[j++];
        }

        // 把result中的结果写回array，left到right已排序
        for (int k = 0; k < result.length; k++) {
            array[left++] = result[k];
        }
    }

    public static void main(String[] args) {
        int[] input = {9, 7, 2, 8, 3, 1, 5, 5, 4, 6};
//        sort(input, 0, input.length - 1);
        msort(input);

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
