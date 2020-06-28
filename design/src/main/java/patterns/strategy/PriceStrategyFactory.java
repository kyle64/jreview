package patterns.strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-09-17.
 */
public class PriceStrategyFactory {
    public static final String SCAN_PACKAGE = PriceStrategyFactory.class.getPackage().getName();

    private List<Class<? extends CallPrice>> callPrices = new ArrayList<>();

    private PriceStrategyFactory() {
        init();
    }

    public static PriceStrategyFactory getInstance() {
        return new PriceStrategyFactory();
    }

    public CallPrice getCallPrice(double totalAmount) {
        if (totalAmount < 5000) {
            return new NormalCustomer();
        } else if (totalAmount < 10000) {
            return new Member();
        } else if (totalAmount < 30000) {
            return new GoldMember();
        } else {
            return new DiamondMember();
        }
    }

    public CallPrice getCallPriceByAnnotation(double totalAmount) {
        try {
            for (Class<? extends CallPrice> callPriceClz : callPrices) {
                PriceRegion priceRegion = callPriceClz.getAnnotation(PriceRegion.class);
                if (totalAmount >= priceRegion.min() && totalAmount < priceRegion.max()) {
                    return callPriceClz.newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void init() {

//        String path = System.getProperty("user.dir") + File.separator + "design" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "" + SCAN_PACKAGE.replace(".", File.separator) + File.separator;
        String classPath = this.getClass().getResource("/").getPath();
        String path = classPath + SCAN_PACKAGE.replace(".", File.separator);

        File file = new File(path);
        String[] strs = file.list();

        try {
            for (String str : strs) {

                String forName = SCAN_PACKAGE + "." + str.replace(".class", "");

                Class<? extends CallPrice> clazz = (Class<? extends CallPrice>) Class.forName(forName);
                if (clazz.isAnnotationPresent(PriceRegion.class)) {
                    callPrices.add(clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
