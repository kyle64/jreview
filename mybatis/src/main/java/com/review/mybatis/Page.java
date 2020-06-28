package com.review.mybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ziheng on 2020/6/9.
 */
@Getter
@Setter
public class Page {
    private Integer pageNum;
    private Integer pageSize;

    public Page() {
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
