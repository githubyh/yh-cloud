package com.yh.page;

import java.io.Serializable;
import java.util.Arrays;

public class SqlParam implements Serializable {
    private static final long serialVersionUID = 5767668339548836251L;
    private String sql;
    private Object[] params;
    private int[] inParamPositions;
    private boolean isCached = false;
    private boolean isCachedFirst = false;
    private ExpireTime expireTime;

    public int[] getInParamPositions() {
        return this.inParamPositions;
    }

    public void setInParamPositions(int[] inParamPositions) {
        this.inParamPositions = inParamPositions;
    }

    public ExpireTime getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(ExpireTime expireTime) {
        this.expireTime = expireTime;
    }

    public SqlParam() {
    }

    public SqlParam(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    public SqlParam(String sql, Object[] params, boolean isCached, boolean isCachedFirst) {
        this.sql = sql;
        this.params = params;
        this.isCached = isCached;
        this.isCachedFirst = isCachedFirst;
    }

    public SqlParam(String sql, Object[] params, int[] inParamPositions) {
        this.sql = sql;
        this.params = params;
        this.inParamPositions = inParamPositions;
    }

    public boolean isCached() {
        return this.isCached;
    }

    public void setCached(boolean isCached) {
        this.isCached = isCached;
    }

    public boolean isCachedFirst() {
        return this.isCachedFirst;
    }

    public void setCachedFirst(boolean isCachedFirst) {
        this.isCachedFirst = isCachedFirst;
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return this.params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String toString() {
        return "SqlParam [sql=" + this.sql + ", params=" + Arrays.toString(this.params) + "]";
    }
}