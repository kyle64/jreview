package bean;

import java.util.List;

/**
 * Created by ziheng on 2019-08-02.
 */
public class PersonBean implements IName {
    private ProfileBean profileBean;
    private List<String> titles;

    public PersonBean(ProfileBean profileBean, List<String> titles) {
        this.profileBean = profileBean;
        this.titles = titles;
    }

    public Integer getAge() {
        return this.profileBean.getAge();
    }

    public List<String> getTitles() {
        return titles;
    }

    @Override
    public String readName() {
        StringBuilder sb = new StringBuilder(profileBean.toString());
        for (String title : titles) {
            sb.append(", ");
            sb.append(title);
        }

        return sb.toString();
    }
}
