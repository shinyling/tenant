package com.shiny.tenant.interceptor;

import com.shiny.tenant.utils.ReflectHelper;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class MycatInterceptor implements Interceptor{

    private static final String SCHEMA_START="/*mycat:schema=";

    private static final String SCHEMA_END="*/";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof RoutingStatementHandler){
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectHelper.getFieldValue(statementHandler, "delegate");
            BoundSql boundSql = delegate.getBoundSql();
            //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
            String sql = boundSql.getSql();
            if(!sql.startsWith(SCHEMA_START)){
                StringBuilder stringBuilder=new StringBuilder(sql.length()+30);
                stringBuilder.append(SCHEMA_START);
                stringBuilder.append("mycat2");
                stringBuilder.append(SCHEMA_END);
                stringBuilder.append(sql);
                sql=stringBuilder.toString();
            }
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
            ReflectHelper.setFieldValue(boundSql, "sql", sql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
