package algorithm;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ziheng on 2020/5/9.
 */
public class ReservoirSampling {
    /**
     * @Description: 采样问题经常会被遇到，比如：
     *
     * 从 100000 份调查报告中抽取 1000 份进行统计。
     * 从一本很厚的电话簿中抽取 1000 人进行姓氏统计。
     * 从 Google 搜索 "Ken Thompson"，从中抽取 100 个结果查看哪些是今年的。
     *
     * 从一个字符流中进行采样，最后保留 10 个字符，而并不知道这个流什么时候结束，且须保证每个字符被采样到的几率相同。
     * 应用场景场景说明：在一个海量广告数据中抽样100个query，其中特征包含pv（query的搜索次数）、adpv（出广告的搜索次数）、adshow（出广告之后的总共ad展示量）、click（点击数量）
     *
     * @date 2020/5/9 上午9:56
     * @param
     * @return
     */

    private static Random random = new Random();
    private static ArrayList<Character> pool = new ArrayList<>();

    public static void addToPool(char inputChar) {
        pool.add(inputChar);
    }

    public static char[] sampling(int num) {
        char[] result = new char[num];

        // 前 K 个元素直接放入数组中
        for (int i = 0; i < num; i++) {
            result[i] = pool.get(i);
        }

        // 每次新增一个元素，第i个数（i<=k）被替换的概率是k/k+1 * 1/k = 1/k+1
        // 最终被保留的概率是k/n
        for (int i = num; i < pool.size(); i++) {
            int randomNumber = random.nextInt(i + 1); // r被替换的几率是1/i+1
            if (randomNumber < num) { // 第i个数被选中的纪律是k/i，下一次保留的几率是i/i+1
                result[randomNumber] = pool.get(i);
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        String inputString = "fehwquioncqweopncipoqrnpcuipqrvqrbewpiucqvneuiq098uf34j2190jfoqew-j0-f91342m0j809f412jmnci3";

        for (char c : inputString.toCharArray()) {
            addToPool(c);
        }
        String out = new String(sampling(10));
        System.out.println(out);

//        byte[] buf = new byte[1024];
//        ByteArrayInputStream bis = new ByteArrayInputStream(inputString.getBytes());
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        int bytesRead;
//        while ((bytesRead = bis.read(buf)) > 0) {
//            bos.write(buf, 0, bytesRead);
//        }
//
//        bis.close();
//        bos.close();
//
//        byte[] out = bos.toByteArray();
//
//        System.out.println(new String(out));
    }
}
