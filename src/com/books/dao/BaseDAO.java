package com.books.dao;

import com.books.utils.CommonCUDR;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDAO<T> {
//    private  QueryRunner queryRunner = new QueryRunner();
    private Class<T> type;

    public BaseDAO() {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] types = parameterizedType.getActualTypeArguments();
        type = (Class<T>) types[0];
    }

//    //执行更新操作，返回影响行数
//    protected int executeUpdate(Connection conn, String sql, Object... params) {
//        try {
//            return queryRunner.update(conn, sql, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    //执行查询操作，返回单个实体对象
//    protected T getBean(Connection conn, String sql, Object... params) {
//        try {
//            return queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //执行查询操作，返回对象的list
//    protected List<T> getBeanList(Connection conn, String sql, Object... params) {
//        try {
//            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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
