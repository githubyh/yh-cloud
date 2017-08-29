package com.yh.service;

import com.yh.page.SqlParam;

import java.util.List;
import java.util.Map;

public interface IBasicService {
    long insert(SqlParam var1) throws DALException;

    int update(SqlParam var1) throws DALException;

    int delete(SqlParam var1) throws DALException;

    Map<String, Object> find(SqlParam var1) throws DALException;

    List<Map<String, Object>> findAll(SqlParam var1) throws DALException;
}
