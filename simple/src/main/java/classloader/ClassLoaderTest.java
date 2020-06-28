package classloader;

/**
 * Created by ziheng on 2019-10-08.
 */
public class ClassLoaderTest {
    public static void showClassLoaderForeachPath(){

        System.out.println();
        //BoostrapClassLoader
        String[] split=System.getProperty("sun.boot.class.path").split(":");
        for(String data:split){
            System.out.println(data);
        }

        System.out.println("===================");
        //ExeClassLoader
        String[] split1=System.getProperty("java.ext.dirs").split(":");
        for(String data:split1){
            System.out.println(data);
        }




        System.out.println("===================");
        //AppClassLoader
        String[] split2=System.getProperty("java.class.path").split(":");
        for(String data:split2){
            System.out.println(data);
        }

        System.out.println("================");
    }
}
