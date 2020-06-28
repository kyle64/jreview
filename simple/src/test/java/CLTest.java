import classloader.ClassLoaderTest;
import org.junit.Test;

/**
 * Created by ziheng on 2019-10-08.
 */
public class CLTest {

    /*
    * 可以看到我们自定义的类都是由AppClassLoader这个类加载器加载的，而AppClassLoader是谁由加载呢？
      在第二行代码地方能看到是ExtClassLoader加载的，注意这里再次强调类加载器层次非继承关系。
      然后我们接着看ExtClassLoader类加载器的父类，发现输出的是null，这在前面已经说了引导加载器是native实现的，所以在Java里面是访问不到的所以是null。
    * */
    @Test
    public void showClassLoaderPath(){

        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());
        System.out.println("------------------------------------");
        System.out.println(int.class.getClassLoader());
        System.out.println(Long.class.getClassLoader());

    }
}
