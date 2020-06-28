package patterns.singleton;

import java.io.*;

/**
 * Created by ziheng on 2019-08-30.
 */
public enum EnumSingleton {
    INSTANCE;
    private  String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EnumSingleton s = EnumSingleton.INSTANCE;
        s.setContent("枚举单例序列化");
        System.out.println("枚举序列化前读取其中的内容：" + s.getContent());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EnumSingleton.obj"));
        oos.writeObject(s);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("EnumSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        EnumSingleton s1 = (EnumSingleton) ois.readObject();
        ois.close();
        System.out.println(s + "\n" + s1);
        System.out.println("枚举序列化后读取其中的内容：" + s1.getContent());
        System.out.println("枚举序列化前后两个是否同一个：" + (s == s1));
    }
}


