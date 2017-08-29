package com.yh.page;

public class PageParam {
    private SqlParam sqlParam;
    private Integer batchSize = Integer.valueOf(1000);
    private String countSql;
    private int totalRecord;

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public PageParam(SqlParam sqlParam) {
        this.sqlParam = sqlParam;
    }

    public PageParam(SqlParam sqlParam, Integer batchSize) {
        this.sqlParam = sqlParam;
        this.batchSize = batchSize;
    }

    public PageParam(SqlParam sqlParam, Integer batchSize, String countSql) {
        this.sqlParam = sqlParam;
        this.batchSize = batchSize;
        this.countSql = countSql;
    }

    public SqlParam getSqlParam() {
        return this.sqlParam;
    }

    public void setSqlParam(SqlParam sqlParam) {
        this.sqlParam = sqlParam;
    }

    public Integer getBatchSize() {
        return this.batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public String getCountSql() {
        return this.countSql;
    }

    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }

    public String toString() {
        return "PageParam [sqlParam=" + this.sqlParam + ", batchSize=" + this.batchSize + ", countSql=" + this.countSql + ", totalRecord=" + this.totalRecord + "]";
    }
}