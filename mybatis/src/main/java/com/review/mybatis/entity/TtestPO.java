package com.review.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2020/5/15.
 */
@Getter
@Setter
public class TtestPO {
    Long id;
    Integer data;
    String expression;


    @Override
    public String toString() {
        return "TtestPO{" +
                "id=" + id +
                ", data=" + data +
                ", expression='" + expression + '\'' +
                '}';
    }
}
