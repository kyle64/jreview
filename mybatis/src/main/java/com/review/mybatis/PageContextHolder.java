package com.review.mybatis;

/**
 * Created by ziheng on 2020/6/9.
 */
public class PageContextHolder {
    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();

    public static Page get() {
        return PAGE_THREAD_LOCAL.get();
    }

    public static void set(Page page) {
        PAGE_THREAD_LOCAL.set(page);
    }

    public static void clear() {
        PAGE_THREAD_LOCAL.remove();
    }

    public static void setPage(Integer pageNum, Integer pageSize) {
        set(new Page(pageNum, pageSize));
    }
}
