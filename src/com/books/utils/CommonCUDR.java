package com.books.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommonCUDR {
    public static int update(Connection conn, String sql, Object... params) {
        PreparedStatement preState = null;
        try {
            preState = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preState.setObject(i + 1, params[i]);
            }
            return preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(preState);
        }
        return 0;
    }

    public static <T> List<T> queryList(Connection conn, Class<T> clazz, String sql, Object... params) {
        ArrayList<T> list = new ArrayList<>();
        PreparedStatement preState = null;
        ResultSet rs = null;
        try {
            preState = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preState.setObject(i + 1, params[i]);
            }
            rs = preState.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(null, preState, rs);
        }
        return list;
    }

    public static <T> T queryBean(Connection conn, Class<T> clazz, String sql, Object... params) {
        PreparedStatement preState = null;
        ResultSet rs = null;
        try {
            preState = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preState.setObject(i + 1, params[i]);
            }
            rs = preState.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, value);
                }
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResources(null, preState, rs);
        }
        return null;
    }
}
