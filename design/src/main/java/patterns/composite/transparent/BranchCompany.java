package patterns.composite.transparent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-09-11.
 */
public class BranchCompany extends Company {
    private List<Company> companyList = new ArrayList<>();

    public BranchCompany(String name) {
        super(name);
    }

    @Override
    public void add(Company company) {
        this.companyList.add(company);
    }

    @Override
    public void remove(Company company) {
        this.companyList.remove(company);
    }

    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print('-');
        }

        System.out.println(name);

        for (Company company : companyList) {
            company.display(depth + 1);
        }
    }
}
