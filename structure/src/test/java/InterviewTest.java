/**
 * Created by ziheng on 2020/7/27.
 */
public class InterviewTest {
    // 作者：那场烟雨遗留下的晴天
    // 链接：https://www.nowcoder.com/discuss/460111
    // 来源：牛客网
    //
    // 某个富翁招收保镖，为了提高吸引力，设置了一个特殊的工资，第一天保镖能获取1元报酬，随后两天能获得2元，在随后三天能够获得3元...以此类推，给定一个天数，得出能够获得的报酬
    //
    // 输入 3， 返回5   1+2+2 = 5
    // 输入 10，返回30  1+2+2+3+3+3+4+4+4+4 = 30
    //
    // # 首先用一层for循环解决，然后后面面试官说能不能推导出公式来
    public static int findKthSum(int k) {
        int sum = 0;
        int n = 1;
        for (int i = 1; i <= k; i++) {
            // 小于1到n的求和
            if (i <= n * (n + 1) / 2) {
                sum += n;
            } else {
                n++;
                sum += n;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(findKthSum(9));
    }
}
