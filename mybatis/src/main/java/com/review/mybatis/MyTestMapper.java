package com.review.mybatis;

import com.review.mybatis.entity.TtestPO;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by ziheng on 2020/5/15.
 */
public interface MyTestMapper {
    List<TtestPO> selectT1Data();

    List<TtestPO> selectByLikePrefix(TtestPO t);

    int insertOne(TtestPO t);

    int batchInsert(List<TtestPO> testList);
}
