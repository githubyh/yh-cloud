package com.yh.service;

import com.yh.page.SqlParam;

public interface IDALService extends IBasicService {


    int[] updateBatch(SqlParam[] var1) throws DALException;
}
