package com.example.demo.dao.impl;

import com.example.demo.bean.SysLog;
import com.example.demo.dao.SysLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SysLogDaoImpl implements SysLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void saveSysLog(SysLog sysLog) {
        StringBuffer sql = new StringBuffer("insert into sys_log");
        sql.append("(id,username,operation,time,method,params,ip,create_time) values(seq_sys_log.nextval,:username,:operation,:time,:method,:params,:ip,:createTime)");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        namedParameterJdbcTemplate.update(sql.toString(),new BeanPropertySqlParameterSource(sysLog));

    }
}
