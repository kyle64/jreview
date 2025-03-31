package com.mytest.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2019-08-11.
 */
@Getter
@Setter
public class Profile {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
