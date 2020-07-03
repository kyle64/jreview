package sort;

/**
 * Created by ziheng on 2019/7/8.
 */
public class QuickSort {

    /**
     * @Description: find k-th smallest element
     *
     * 若切分后的左子数组的长度 > k，则第k大元素必出现在左子数组中；
     * 若切分后的左子数组的长度 = k-1，则第k大元素为pivot；
     * 若上述两个条件均不满足，则第k大元素必出现在右子数组中。
     *
     * @date 2019/7/8 下午4:34
     * @param array
     * @param k
     * @param left
     * @param right
     * @return void
     */
    public static int quickSelect(int[] array, int k, int left, int right) {
        if (left == right) return array[left];

        int pivotIndex = partition(array, left, right);

        if (k == pivotIndex - left + 1) {
            return array[pivotIndex];
        } else if (pivotIndex - left + 1 > k) {
            return quickSelect(array, k, left, pivotIndex - 1);
        } else {
            return quickSelect(array, k - (pivotIndex - left + 1), pivotIndex + 1, right);
        }


    }

    /**
     * @Description: 基本思路：每次都取数组的第一个元素作为比较标准（哨兵元素），凡是大于这个哨兵元素的都放在它的右边，凡是小于这个哨兵元素的都放在它的左边；
     *
     *         大概的步骤：
     *
     *         1、判断参数条件，其实这是递归的出口；
     *
     *        2、以数组的第一个元素为哨兵元素，让其他元素和它比较大小；(记住这时候第一个元素位置是口的，因为里面的值被作为哨兵元素保存起来了)
     *
     *         3、开始从数组尾部往前循环得到一个小于哨兵元素的  元素A ，把该  元素A  放到第一个元素位置（也就是哨兵元素位置上，因为哨兵元素位置是空的）；（这时候要记住 元素A  的位置是空的了）
     *
     *        4、开始从数组头部往后循环得到一个大于哨兵元素的   元素B ，把该  元素B  放在上一步中移出的  元素A  的位置上；
     *
     *        5、依次循环上面3、4步，直到最后一个元素为止，那么最后一个元素就存放哨兵元素了。
     *
     *        6、把小于哨兵元素的那一部分和大于哨兵元素的那一部分分别递归调用本函数，依次递归排序好所有元素；
     *
     *
     * 最优情况下时间复杂度
     *         快速排序最优的情况就是每一次取到的元素都刚好平分整个数组(很显然我上面的不是)；
     *         此时的时间复杂度公式则为：T[n] = 2T[n/2] + f(n)；T[n/2]为平分后的子数组的时间复杂度，f[n] 为平分这个数组时所花的时间；
     *         下面来推算下，在最优的情况下快速排序时间复杂度的计算(用迭代法)：
     *                                          T[n] =  2T[n/2] + n                                                                     ----------------第一次递归
     *                  令：n = n/2        =  2 { 2 T[n/4] + (n/2) }  + n                                               ----------------第二次递归
     *
     *                                             =  2^2 T[ n/ (2^2) ] + 2n
     *
     *                 令：n = n/(2^2)   =  2^2  {  2 T[n/ (2^3) ]  + n/(2^2)}  +  2n                         ----------------第三次递归  
     *
     *                                             =  2^3 T[  n/ (2^3) ]  + 3n
     *
     *                 ......................................................................................                        
     *
     *                 令：n = n/(  2^(m-1) )    =  2^m T[1]  + mn                                                  ----------------第m次递归(m次后结束)
     *
     *                当最后平分的不能再平分时，也就是说把公式一直往下迭代，到最后得到T[1]时，说明这个公式已经迭代完了（T[1]是常量了）。
     *
     *                得到：T[n/ (2^m) ]  =  T[1]    ===>>   n = 2^m   ====>> m = logn；
     *
     *                T[n] = 2^m T[1] + mn ；其中m = logn;
     *
     *                T[n] = 2^(logn) T[1] + nlogn  =  n T[1] + nlogn  =  n + nlogn  ；其中n为元素个数
     *
     *                又因为当n >=  2时：nlogn  >=  n  (也就是logn > 1)，所以取后面的 nlogn；
     *
     *                综上所述：快速排序最优的情况下时间复杂度为：O( nlogn )
     *
     *
     *
     * 最差情况下时间复杂度
     *         最差的情况就是每一次取到的元素就是数组中最小/最大的，这种情况其实就是冒泡排序了(每一次都排好一个元素的顺序)
     *
     *      这种情况时间复杂度就好计算了，就是冒泡排序的时间复杂度：T[n] = n * (n-1) = n^2 + n;
     *
     *      综上所述：快速排序最差的情况下时间复杂度为：O( n^2 )
     *
     *
     *
     * 平均时间复杂度
     *        快速排序的平均时间复杂度也是：O(nlogn)
     *
     * 空间复杂度
     *         其实这个空间复杂度不太好计算，因为有的人使用的是非就地排序，那样就不好计算了（因为有的人用到了辅助数组，所以这就要计算到你的元素个数了）；我就分析下就地快速排序的空间复杂度吧；
     *         首先就地快速排序使用的空间是O(1)的，也就是个常数级；而真正消耗空间的就是递归调用了，因为每次递归就要保持一些数据；
     *      最优的情况下空间复杂度为：O(logn)  ；每一次都平分数组的情况
     *      最差的情况下空间复杂度为：O( n )      ；退化为冒泡排序的情况
     *
     * ————————————————
     * 版权声明：本文为CSDN博主「庾志辉」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/yuzhihui_no1/java/article/details/44198701
     *
     * @date 2020/4/29 下午1:51
     * @param array
     * @param start
     * @param end
     * @return void
     */
    public static void sort(int[] array, int start, int end) {

        if (start < end) {
            int pivotLocation = partition(array, start, end);

            sort(array, start,  pivotLocation - 1);
            sort(array, pivotLocation + 1, end);
        }
    }

    /**
     * @Description: element on the left side of pivot is less than pivot, right side is greater than pivot
     *
     * @date 2019/7/8 下午4:28
     * @param array
     * @param left
     * @param right
     * @return int (index of pivot)
     */
    private static int partition(int[] array, int left, int right) {
        int pivot = randomPivot(array, left, right);
//        int pivot = array[left];

        while (left < right) {
            while (left < right && array[right] >= pivot) { // skip element which is greater than pivot on the right
                right--; // find the first element less than pivot on the right
            }

//            int tmp = array[left];
            array[left] = array[right];
//            array[right] = tmp;

            while (left < right && array[left] <= pivot) { // skip element which is less than pivot on the left
                left++; // find the first element greater than pivot on the left
            }

//            tmp = array[right];
            array[right] = array[left];
//            array[left] = tmp;
        }

        array[left] = pivot;

        return left;
    }

    private static int randomPivot(int[] array, int left, int right) {
        int randomPosition = left + (int) ((Math.random()) * (right - left));
        // swap pivot & left
        int temp = array[left];
        array[left] = array[randomPosition];
        array[randomPosition] = temp;

        return array[left];
    }

    public static void qsort(int[] array) {
        qsortHelper(array, 0, array.length-1);
    }

    public static void qsortHelper(int[] array, int left, int right) {
        if (left < right) {
            int p = qsortPartition(array, left, right);
            qsortHelper(array, left, p-1);
            qsortHelper(array, p+1, right);
        }
    }

    public static int qsortPartition(int[] array, int left, int right) {
        int pivot = array[left];

        while (left < right) {
            // 左移right，找到第一个小于pivot的数
            while (left < right && array[right] >= pivot) right--;
            array[left] = array[right]; // first time left is pivot

            // 同上，右移right，找到第一个大于pivot的数
            while (left < right && array[left] <= pivot) left++;
            array[right] = array[left];
        }
        // pivot左边都小于他，右边都大于他
        array[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] input = {9,7,2,8,3,1,5,5,4,6};
//        sort(input, 0, input.length-1);
        qsort(input);

        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }

//        System.out.println(quickSelect(input, 7, 0, input.length-1));
    }
}
