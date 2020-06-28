package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class HeapSort {
    public static void sort(int[] array) {

        // 构建最大堆MaxHeap
        // 这里元素的索引是从0开始的,所以最后一个非叶子结点array.length/2 - 1
        // 1. build max heap
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);

        }

        print(array);
        System.out.println("\n============================");

        // 将堆顶元素弹出，依次与当前未排序的最小子节点的值交换，每次调整都确定当前最大值的位置
        // 2. swap top of maxHeap to the end of array
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i); // swap top heap to end
            adjustHeap(array, 0, i); // adjust heap with array length -1
            print(array);
            System.out.println("\n============================");
        }
    }

    private static void adjustHeap(int[] array, int i, int length) {

        while (i < length) {
            int k = 2 * i + 1; // left child index
//            int right = 2 * i + 2; // right child index

            // let k point to the larger child
            if (k + 1 < length && array[k + 1] > array[k]) {
                k++;
            }

            if (k < length && array[k] > array[i]) {
                swap(array, k, i); // swap child and parent if child is greater than parent
                i = k; // move to child node, check heap
            } else {
                break;
            }

        }
    }

    public static void adjustHeap1(int[] array, int i, int length) {
        // 先把当前元素取出来，因为当前元素可能要一直移动
        int temp = array[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {  //2*i+1为左子树i的左子树(因为i是从0开始的),2*k+1为k的左子树
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {  //如果有右子树,并且右子树大于左子树
                k++;
            }
            //如果发现结点(左右子结点)大于根结点，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会受到影响,所以，循环对子节点所在的树继续进行判断
                i = k;  //这一步决定了下一个步骤执行到的是左子树还是右子树
            } else {  //不用交换，直接终止循环
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void print(int[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(" " + input[i]);
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
