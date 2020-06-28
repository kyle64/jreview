package patterns.adaptor;

/**
 * Created by ziheng on 2019-09-04.
 */
public interface PhoneLockAdapter {
    boolean support(Validation validation);
    void unlock(Validation validation);
//    void unlock(String validation); // validation不暴露的话才是更加典型的适配器模式，这种方式（类似spring AOP）更像是实现适配器
}
