package patterns.composite;

import patterns.composite.transparent.BranchCompany;
import patterns.composite.transparent.Company;
import patterns.composite.transparent.FinanceDepartment;
import patterns.composite.transparent.HRDepartment;

/**
 * Created by ziheng on 2019-09-12.
 */
public class CompositeTest {
    public static void main(String[] args) {
        Company root = new BranchCompany("北京总公司");
        root.add(new HRDepartment("总公司人力资源部"));
        root.add(new FinanceDepartment("总公司财务部"));
        Company huadong = new BranchCompany("华东分公司");
        huadong.add(new HRDepartment("华东公司人力资源部"));
        huadong.add(new FinanceDepartment("华东公司财务部"));
        Company hangzhou = new BranchCompany("杭州分公司");
        hangzhou.add(new HRDepartment("杭州分公司人力资源部"));
        huadong.add(new FinanceDepartment("杭州分公司财务部"));

        Company nanjing = new BranchCompany("南京分公司");
        nanjing.add(new HRDepartment("南京分公司人力资源部"));
        nanjing.add(new FinanceDepartment("南京分公司财务部"));

        huadong.add(hangzhou);
        huadong.add(nanjing);
        root.add(huadong);

        root.display(0);
    }
}
