package sort;

/**
 * Created by ziheng on 2019/7/8.
 */
public class BubbleSort {

    /**
     * @Description: 遍历整个数组，比较相邻元素，如果a[i] < a[i+1]，交换两个元素，每次遍历保证最大的元素在数组末尾
     *
     * @date 2019/7/8 上午11:46
     * @param
     * @return void
     */
    public static void sort(int[] array) {
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 0; j < array.length - 1 - i; j++) {
//                if (array[j] > array[j+1]) {
//                    // swap
//                    int temp = array[j];
//                    array[j] = array[j+1];
//                    array[j+1] = temp;
//                }
//            }
//        }

        boolean isChanged;
        int temp;

        for (int i = 0; i < array.length - 1; i++) {
            // 第i次遍历变动的标志
            isChanged = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 大的数放后面
                if (array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    isChanged = true;
                }
            }
            // 如果没有发生交换，则说明已完成排序
            if (!isChanged) break;

            // inner-loop每迭代一次就排好一个数
            // 说明从后往前的第i个数已排序
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
