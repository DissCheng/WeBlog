package com.disscheng.weblog.context;



public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    public static void setUserId(Long userId) {
        threadLocal.set(userId);
    }
    public static Long getUserId() {
        return threadLocal.get();
    }
    public static void removeId() {
        threadLocal.remove();
    }
}
