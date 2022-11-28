//package com.example.lurenjiaspring.aop.readandwrite;
//
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.lang.Nullable;
//
//public class ReadWriteDataSource extends AbstractRoutingDataSource {
//    @Nullable
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return DsTypeHolder.getDsType();
//    }
//}
