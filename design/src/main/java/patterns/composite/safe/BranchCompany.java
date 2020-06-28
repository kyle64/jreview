package patterns.composite.safe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziheng on 2019-09-12.
 */
public class BranchCompany extends Company {
    private List<Company> companyList = new ArrayList<>();

    public BranchCompany(String name) {
        super(name);
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

    public void add(Company company) {
        this.companyList.add(company);
    }

    public void remove(Company company) {
        this.companyList.remove(company);
    }
}
