package com.books.dao;

import com.books.utils.CommonCUDR;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

public class BaseDAO<T> {
    private final Class<T> type;

    public BaseDAO() {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] types = parameterizedType.getActualTypeArguments();
        type = (Class<T>) types[0];
    }


        protected int executeUpdate(Connection conn, String sql, Object... params) {
        return CommonCUDR.update(conn, sql, params);

    }

        protected T getBean(Connection conn, String sql, Object... params) {
        return CommonCUDR.queryBean(conn, type, sql, params);
    }

        protected List<T> getBeanList(Connection conn, String sql, Object... params) {
            return CommonCUDR.queryList(conn, type, sql, params);
    }

}
