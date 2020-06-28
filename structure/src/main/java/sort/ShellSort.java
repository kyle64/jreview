package sort;

/**
 * Created by ziheng on 2019/7/9.
 */
public class ShellSort {
    public static void  sort(int[] array) {
        int len = array.length / 2;

        while (len >= 1) {
            for (int i = 0; i < len; i++) {
                for (int j = i + len; j < array.length; j = j + len) {
                    int tmp = array[j];
                    int k;

                    for (k = j - len; k >= 0 && array[k] > tmp; k = k - len) {
                        array[k + len] = array[k];
                    }

                    array[k + len] = tmp;
                }
            }

            len /= 2;
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
