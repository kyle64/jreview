package patterns.strategy;

/**
 * Created by ziheng on 2019-09-17.
 */
public class Customer {
}


// 普通用户
@PriceRegion(max = 5000)
class NormalCustomer implements CallPrice {
    @Override
    public double callPrice(double amount) {
        return amount;
    }
}

// 会员
@PriceRegion(min = 5000, max = 10000)
class Member implements CallPrice {
    @Override
    public double callPrice(double amount) {
        return amount * 0.95;
    }
}

// 金卡会员
@PriceRegion(min = 10000, max = 30000)
class GoldMember implements CallPrice {
    @Override
    public double callPrice(double amount) {
        return amount * 0.90;
    }
}

// 钻石会员
@PriceRegion(min = 30000)
class DiamondMember implements CallPrice {
    @Override
    public double callPrice(double amount) {
        return amount * 0.80;
    }
}