package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-08-29.
 */
//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class MyGeneric<T> {

    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public MyGeneric(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public T genericMethod(Class<T> tClass) throws InstantiationException,
            IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

    /**
     * 类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。
     */
    public static void showKeyValue(MyGeneric<?> obj) {
        System.out.println("泛型测试: key value is " + obj.getKey());
    }

    //在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
    //public <T> T showKeyName(Generic<T extends Number> container)，编译器会报错："Unexpected bound"
    public static void showKeyValue1(MyGeneric<? extends Number> obj) {
        System.out.println("泛型测试: key value is " + obj.getKey());
    }

    public static void main(String[] args) {
//        MyGeneric<Integer> genericInteger = new MyGeneric<>(123456);
//        MyGeneric<String> genericString = new MyGeneric<>("string value");
//        System.out.println(genericString.getKey() + genericInteger.getKey());
//
//        MyGeneric myGeneric = new MyGeneric(12.3);
//        System.out.println(myGeneric.getKey());

        // 同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
        MyGeneric<Integer> gInt = new MyGeneric<>(123);
        MyGeneric<Number> gNumber = new MyGeneric<>(456);
        showKeyValue(gNumber);
        showKeyValue(gInt);

        MyGeneric<String> generic1 = new MyGeneric<String>("11111");
        MyGeneric<Integer> generic2 = new MyGeneric<Integer>(2222);
        MyGeneric<Float> generic3 = new MyGeneric<Float>(2.4f);
        MyGeneric<Double> generic4 = new MyGeneric<Double>(2.56);
        //这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
//showKeyValue1(generic1);
        showKeyValue1(generic2);
        showKeyValue1(generic3);
        showKeyValue1(generic4);


//        List<String>[] lsa = new List<String>[10]; // Not really allowed.
//        Object o = lsa;
//        Object[] oa = (Object[]) o;
//        List<Integer> li = new ArrayList<Integer>();
//        li.add(new Integer(3));
//        oa[1] = li; // Unsound, but passes run time store check
//        String s = lsa[1].get(0); // Run-time error: ClassCastException.

        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK

    }
}
