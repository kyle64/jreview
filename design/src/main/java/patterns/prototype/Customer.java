package patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;

/**
 * Created by ziheng on 2019-09-02.
 */
@Getter
@Setter
@AllArgsConstructor
public class Customer implements Cloneable, Serializable {
    private Long id;
    private Account account;

    public Customer clone() {
        Customer clone = null;
        try {
            clone = (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    /*
     * ByteArrayOutputStream或ByteArrayInputStream是内存读写流，
     * 不同于指向硬盘的流，它内部是使用字节数组读内存的，这个字节数组是它的成员变量，
     * 当这个数组不再使用变成垃圾的时候，Java的垃圾回收机制会将它回收。所以不需要关流。
     * */
    public Customer deepClone() throws IOException, ClassNotFoundException {
        Customer clone = null;
        //写入字节流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        oos.close();
        //分配内存，写入原始对象，生成新对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        //返回生成的新对象
        clone = (Customer) ois.readObject();

        ois.close();

        return clone;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T object) {
        T clone = null;
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            clone = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clone;
    }
}
