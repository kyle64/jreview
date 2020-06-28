package com.review.mybatis;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.*;

/**
 * Created by ziheng on 2020/6/8.
 */
@Intercepts(
        {
                @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        }
)
public class MyPageInterceptor implements Interceptor {
    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            Page page = PageContextHolder.get();

            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            Executor executor = (Executor) invocation.getTarget();
            BoundSql boundSql = ms.getBoundSql(parameter);
            CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);

            if (page == null) {
                page = new Page(0 , null);
            }

            if (page.getPageNum() <= 0) {
                return invocation.proceed();
            } else {
                Integer offset = (page.getPageNum() - 1) * page.getPageSize();
                Integer limit = page.getPageSize();

                String pageSql = new StringBuilder(boundSql.getSql()).append(" LIMIT ?, ? ").toString();

                List<ParameterMapping> newParameterMappings = new ArrayList<ParameterMapping>(boundSql.getParameterMappings());
                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), "OFFSET_PARAM", Integer.class).build());
                newParameterMappings.add(new ParameterMapping.Builder(ms.getConfiguration(), "LIMIT_PARAM", Integer.class).build());

                Map<String, Object> paramMap = null;
                if (parameter == null) {
                    paramMap = new HashMap<>();
                } else if (parameter instanceof Map) {
                    paramMap = new HashMap<String, Object>();
                    paramMap.putAll((Map) parameter);
                } else {
                    paramMap = new HashMap<String, Object>();
                    boolean hasTypeHandler = ms.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(parameter.getClass());
                    if (!hasTypeHandler) {
                        paramMap.putAll(objectToMap(parameter));
                    }
                }

                paramMap.put("OFFSET_PARAM", offset);
                paramMap.put("LIMIT_PARAM", limit);

                BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, newParameterMappings, paramMap);

                // 设置动态参数
                try {
                    Field f = BoundSql.class.getDeclaredField("additionalParameters");
                    f.setAccessible(true);
                    Map<String, Object> additionalParameters = (Map<String, Object>) f.get(boundSql);

                    for (String key : additionalParameters.keySet()) {
                        pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                CacheKey pageKey = cacheKey;
                return executor.query(ms, paramMap, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
            }
        } finally {
            PageContextHolder.clear();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.get(obj) != null) {
                map.put(field.getName(), field.get(obj));
            }
        }

        return map;
    }
}
