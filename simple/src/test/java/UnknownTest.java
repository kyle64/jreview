import org.junit.Test;

/**
 * Created by ziheng on 2019-08-21.
 */
public class UnknownTest {
    @Test
    public void testShift() {
        int a = 13;
        int b = Integer.highestOneBit((a - 1) << 1);

        System.out.println(Integer.toBinaryString(b));
    }

    @Test
    public void testInt() {
        Integer a = Integer.valueOf(127);
        Integer b = new Integer(127);
        System.out.println(a == b);
        int c = 127;
        System.out.println(a == c);
        System.out.println(b == c);
    }

    @Test
    public void testInt2() {
        Integer a = 127;
        Integer b = 127;
        System.out.println("等于127:");
        System.out.println(a == b);
        System.out.println("*****************");

        a = 128;
        b = 128;
        System.out.println("等于128:");
        System.out.println(a == b);
        System.out.println("*****************");

        a = -128;
        b = -128;
        System.out.println("等于-128:");
        System.out.println(a == b);
        System.out.println("*****************");

        a = -129;
        b = -129;
        System.out.println("等于-129:");
        System.out.println(a == b);
        System.out.println("*****************");

        // 测试Boolean
        System.out.println("测试Boolean");
        Boolean c = true;
        Boolean d = true;
        System.out.println(c == d);
        d = new Boolean(true);
        System.out.println(c == d);
    }

    @Test
    public void testStrIntern() {
        String str1 = "计算机";
        String str2 = "计算机";
        System.out.println("str1==str2:" + (str1 == str2));

        String str3 = new String("计算机");
        System.out.println("str1==str3:" + (str1 == str3));
        System.out.println("str1==str3.intern():" + (str1 == str3.intern()));
        System.out.println("str2==str3.intern():" + (str2 == str3.intern()));

        String str4 = new String("计算机");
        System.out.println("str3==str4:" + (str3 == str4));
        System.out.println("str3.intern()==str4.intern():" + (str3.intern() == str4.intern()));


        String str5 = new StringBuilder("软件").append("工程").toString();
        System.out.println("str5.intern() == str5:" + (str5.intern() == str5));

        String str6 = new String(new StringBuilder("物联网").append("工程").toString());
        System.out.println("str6.intern() == str6:" + (str6.intern() == str6));

        String str7 = new String("物联网");
        System.out.println("str7.intern() == str7:" + (str7.intern() == str7));

    }

    @Test
    public void testStrAppend() {
        int n=3;
        System.out.println(String.format("%0" + n + "d", 0).replace("0", "important"));

        System.out.println(String.format("%-10s", "abcd").replace(' ', '0'));
    }
}
