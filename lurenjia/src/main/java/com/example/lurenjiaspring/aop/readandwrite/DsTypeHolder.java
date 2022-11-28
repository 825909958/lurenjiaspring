package com.example.lurenjiaspring.aop.readandwrite;

public class DsTypeHolder {
    private static ThreadLocal<DsType> dsTypeThreadLocal = new ThreadLocal<>();

    public static void master() {
        dsTypeThreadLocal.set(DsType.MASTER);
    }

    public static void slave() {
        dsTypeThreadLocal.set(DsType.SLAVE);
    }

    public static DsType getDsType() {
        return dsTypeThreadLocal.get();
    }

    public static void clearDsType() {
        dsTypeThreadLocal.remove();
    }
}
